package ru.artsok.controllers;

import org.json.JSONObject;
import ru.artsok.tcp.entity.Migu;
import ru.artsok.tcp.entity.interfaces.MiguHandle;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@ServerEndpoint("/websocket")
public class WebSocketMigu {

    List<String> isOpen = new ArrayList<>();

    //Запускаеться при открытии сокета
    @OnOpen
    public void onOpen(Session session) throws InterruptedException, IOException {
        isOpen.add(session.getId());
//        System.out.println("Client connected");
        session.getBasicRemote().sendText(session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        isOpen.remove(session);
//        System.out.println("Connect is close" + Thread.currentThread().getName());
    }

    //Вызываеться при отправке сообщения со страницы методом socket.send(text)
    @OnMessage
    public void onMessage(String message, Session session) throws IOException,
            InterruptedException {
        if (message != null) {
            int selectNumb = Integer.parseInt(message);
            if (MiguHandle.miguMap.getMap().get(selectNumb) != null) {
                String ajaxGetUserServletResponseText = "";
                String ajaxGetUserServletResponseImgText = "";
                String ajaxGetUserServletResponseEventText = "";
                String oldAjaxGetUserServletResponseText = "";
                String oldAjaxGetUserServletResponseImgText = "";
                String oldAjaxGetUserServletResponseEventText = "";
                System.out.println("message text - " + message);
                sendAjax(MiguHandle.miguMap.getMap().get(selectNumb), session);
                while (isOpen.contains(session.getId())) {
                    JSONObject jsonObject = new JSONObject();
                    if (MiguHandle.miguMap.getMap().get(selectNumb).getStates().isMiguIsRespond()) {
                        Migu migu = MiguHandle.miguMap.getMap().get(selectNumb);

                        ajaxGetUserServletResponseText = "<p class=\"mass\">" + String.format("Масса:<br>%.0f кг", migu.getStates().getMass()) +
                                "</p>\n" + "<p class=\"press\">" + String.format("Давление:<br>%.2f МПа", migu.getStates().getPressure()) + "</p>\n" +
                                "<p class=\"setting_value\">" +
                                String.format("Масса ГОТВ %.0f кг <br>Заданная масса резервуара %.0f кг<br>Заданная масса ГОТВ %.0f кг<br>Утечка %.0f кг",
                                        migu.getStates().getMass(), migu.getStates().getDesiredMassOfEmptyPackaging(),
                                        migu.getStates().getSetWeightGOTV(), migu.getStates().getLeakGOTV()) + "<br></p><br>"
                                + getStringChanel(migu);

                        ajaxGetUserServletResponseImgText = getStringImage(migu);
                        ajaxGetUserServletResponseEventText = getStringEvent(migu);


                        if (!ajaxGetUserServletResponseText.equals(oldAjaxGetUserServletResponseText))
                            jsonObject.put("text", ajaxGetUserServletResponseText);
                        else
                            jsonObject.put("text", "");

                        if (!ajaxGetUserServletResponseImgText.equals(oldAjaxGetUserServletResponseImgText))
                            jsonObject.put("img", ajaxGetUserServletResponseImgText);
                        else
                            jsonObject.put("img", "");

                        if (!ajaxGetUserServletResponseEventText.equals(oldAjaxGetUserServletResponseEventText))
                            jsonObject.put("eventm", ajaxGetUserServletResponseEventText);
                        else
                            jsonObject.put("eventm", "");

                        jsonObject.put("time", "<p class=\"time\">" + migu.getStates().getTime() + "</p>");

                        jsonObject.put("ses", session.getId());

                        session.getBasicRemote().sendText(jsonObject.toString());

                        oldAjaxGetUserServletResponseText = ajaxGetUserServletResponseText;
                        oldAjaxGetUserServletResponseImgText = ajaxGetUserServletResponseImgText;
                        oldAjaxGetUserServletResponseEventText = ajaxGetUserServletResponseEventText;
                        Thread.sleep(2000);

                        System.out.println("message send Message " + Thread.currentThread().getName());
                    } else {
                        jsonObject.put("text", "<p style=\"font-size: 80px; color: #ff0000; padding-left: 200px; padding-top: 200px;\">Нет связи с МИЖУ</p> ");
                        jsonObject.put("img", " ");
                        jsonObject.put("eventm", " ");
                        jsonObject.put("ses", session.getId());
                        jsonObject.put("time", "<p class=\"time\">" + "</p>");
                        session.getBasicRemote().sendText(jsonObject.toString());
                    }
                }
            }
        }
    }

    private void sendAjax(Migu migu, Session session) throws IOException {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("text", "<p class=\"mass\">" + String.format("Масса:<br>%.0f кг", migu.getStates().getMass()) +
                "</p>\n" + "<p class=\"press\">" + String.format("Давление:<br>%.2f МПа", migu.getStates().getPressure()) + "</p>\n" +
                "<p class=\"setting_value\">" +

                String.format("Масса ГОТВ %.0f кг <br>Заданная масса резервуара %.0f кг<br>Заданная масса ГОТВ %.0f кг<br>Утечка %.0f кг",
                        migu.getStates().getMass(), migu.getStates().getDesiredMassOfEmptyPackaging(),
                        migu.getStates().getSetWeightGOTV(), migu.getStates().getLeakGOTV()) + "<br></p><br>"
                + getStringChanel(migu));

        jsonObject.put("time", "<p class=\"time\">" + migu.getStates().getTime() + "</p>");
        jsonObject.put("img", getStringImage(migu));
        jsonObject.put("eventm", getStringEvent(migu));
        session.getBasicRemote().sendText(jsonObject.toString());
    }


    private String getStringChanel(Migu migu) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 10; i++)
            result.append("<p style=\"position: absolute; top: ").append(284 + i * 25.5)
                    .append("px; left: 77; width: 80px; ")
                    .append("height: 22px; padding-left: 5; font-size: 16pt; font-weight: bold; background-color: ")
                    .append(migu.getStates().getStyleWeightGOTVChannel()[i])
                    .append(";\">").append((int) migu.getStates().getInstalledWeightGotvChannel()[i]).append("</p>\n");
        return result.toString();
    }

    private String getStringImage(Migu migu) {
        String sufix = " width=1200 height=650 style=\"position: absolute; z-index: 0\"";
        String prefix = "<img src=/resources/images/view/";

        List<Integer> list;
        StringBuilder result = new StringBuilder();

        list = migu.getStates().getErrOrEventNumber();

        result.append(prefix).append("main.gif").append(sufix).append(">\n");

        if (list.contains(82) || list.contains(83) || list.contains(84) || list.contains(87)) {
            result.append(prefix).append("XA/1/XA1_err.gif").append(sufix).append(">\n");
        } else if (list.contains(111)) {
            result.append(prefix).append("XA/1/XA1_work.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("XA/1/XA1.gif").append(sufix).append(">\n");
        }

        if (list.contains(92) || list.contains(93) || list.contains(94) || list.contains(97)) {
            result.append(prefix).append("XA/2/XA2_err.gif").append(sufix).append(">\n");
        } else if (list.contains(111)) {
            result.append(prefix).append("XA/2/XA2_work.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("XA/2/XA2.gif").append(sufix).append(">\n");
        }

        if (list.contains(111)) {
            result.append(prefix).append("EH/1/EH1_err.gif").append(sufix).append(">\n");
        } else if (list.contains(111)) {
            result.append(prefix).append("EH/1/EH1_work.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("EH/1/EH1.gif").append(sufix).append(">\n");
        }
        if (list.contains(112)) {
            result.append(prefix).append("EH/2/EH2_err.gif").append(sufix).append(">\n");
        } else if (list.contains(112)) {
            result.append(prefix).append("EH/2/EH2_work.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("EH/2/EH2.gif").append(sufix).append(">\n");
        }

        if (list.contains(22) || list.contains(21)) {
            result.append(prefix).append("cylinder/cilinder_err.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("cylinder/cilinder.gif").append(sufix).append(">\n");
        }

        if (list.contains(20)) {
            result.append(prefix).append("manometer/manometr_error.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("manometer/manometr.gif").append(sufix).append(">\n");
        }

        if (list.contains(122)) {
            result.append(prefix).append("valve/valve_error.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("valve/valve.gif").append(sufix).append(">\n");
        }

        if (list.contains(123)) {
            result.append(prefix).append("valve/valve_outlet.gif").append(sufix).append(">\n");
        }

        if (list.contains(121)) {
            result.append(prefix).append("мembrane/мembrane_error.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("мembrane/мembrane.gif").append(sufix).append(">\n");
        }

        if (list.contains(124)) {
            result.append(prefix).append("мembrane/membrane_outlet.gif").append(sufix).append(">\n");
        }

        if (list.contains(135) && list.contains(137)) {
            result.append(prefix).append("zpu/zpu_starup.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("zpu/zpu.gif").append(sufix).append(">\n");
        }

        if (list.contains(11) || list.contains(12) || list.contains(13) || list.contains(14) || list.contains(15) || list.contains(24) || list.contains(23)) {
            result.append(prefix).append("zpu/zpu_el_pusk/zpu_el_pusk_error.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("zpu/zpu_el_pusk/zpu_el_pusk.gif").append(sufix).append(">\n");
        }

        if (list.contains(13) || list.contains(14) || list.contains(15) || list.contains(24) || list.contains(23)) {
            result.append(prefix).append("zpu/zpu_el_tap/zpu_el_tap_error.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("zpu/zpu_el_tap/zpu_el_tap.gif").append(sufix).append(">\n");
        }

        if (list.contains(17) || list.contains(18) || list.contains(19)) {
            result.append(prefix).append("zpu/zpu_tap/zpu_tap_error.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("zpu/zpu_tap/zpu_tap.gif").append(sufix).append(">\n");
        }

        if (list.contains(86) || list.contains(87)) {
            result.append(prefix).append("XA/1/cb/XA1_CD_error.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("XA/1/cb/XA1_CD.gif").append(sufix).append(">\n");
        }

        if (list.contains(83) || list.contains(84)) {
            result.append(prefix).append("XA/1/k1/XA1_k_error.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("XA/1/k1/XA1_k.gif").append(sufix).append(">\n");
        }

        if (list.contains(81)) {
            result.append(prefix).append("XA/1/PD11/XA1_PD11_error.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("XA/1/PD11/XA1_PD11.gif").append(sufix).append(">\n");
        }

        if (list.contains(82)) {
            result.append(prefix).append("XA/1/PD12/XA1_PD12_error.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("XA/1/PD12/XA1_PD12.gif").append(sufix).append(">\n");
        }

        if (list.contains(96) || list.contains(97)) {
            result.append(prefix).append("XA/2/cb2/XA2_CD_error.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("XA/2/cb2/XA2_CD.gif").append(sufix).append(">\n");
        }

        if (list.contains(93) || list.contains(84)) {
            result.append(prefix).append("XA/2/k2/XA2_K1_error.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("XA/2/k2/XA2_K1.gif").append(sufix).append(">\n");
        }

        if (list.contains(91)) {
            result.append(prefix).append("XA/2/PD21/XA2_PD21_error.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("XA/2/PD21/XA2_PD21.gif").append(sufix).append(">\n");
        }

        if (list.contains(92)) {
            result.append(prefix).append("XA/2/PD22/XA2_PD22_error.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("XA/2/PD22/XA2_PD22.gif").append(sufix).append(">\n");
        }

        if (list.contains(1)) {
            result.append(prefix).append("power/power_error.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("power/power.gif").append(sufix).append(">\n");
        }

        if (list.contains(3)) {
            result.append(prefix).append("power/akb_error.gif").append(sufix).append(">\n");
        } else {
            result.append(prefix).append("power/akb.gif").append(sufix).append(">\n");
        }

        return result.toString();
    }

    private String getStringEvent(Migu migu) {

        StringBuilder result = new StringBuilder();
//        result.append("<p class=\"errorMessage\" onscroll=\"scrollPos();\" id=\"eventArea\">");

        for (String str : migu.getStates().getErrOrEvent()) {
            result.append(str + "<br>");
        }
//        result.append("</p>\n");
        return result.toString();
    }


}
