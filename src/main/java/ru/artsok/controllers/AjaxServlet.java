package ru.artsok.controllers;

import org.apache.log4j.Logger;
import ru.artsok.tcp.entity.Migu;
import ru.artsok.tcp.entity.interfaces.MiguHandle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.artsok.util.ClassNameUtil.getCurrentClassName;

@WebServlet("/GetUserServlet")
public class AjaxServlet extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(getCurrentClassName());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Migu migu = new Migu();
        int selectNumb = Integer.parseInt(req.getParameter("numb"));

        if (MiguHandle.miguMap.getMap().get(selectNumb) != null) {
            req.setAttribute("numb", req.getParameter("selectMigu"));
            migu = MiguHandle.miguMap.getMap().get(selectNumb);
        }

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain");

        resp.getWriter().write("<p class=\"mass\">" + String.format("Масса:<br>%.0f кг", migu.getStates().getMass()) + "</p>\n");
        resp.getWriter().write("<p class=\"press\">" + String.format("Давление:<br>%.2f МПа", migu.getStates().getPressure()) + "</p>\n");

        for (int i = 0; i < 10; i++)
            resp.getWriter().write(
                    "<p style=\"position: absolute; top: " + (284 + i * 25.5) + "px; left: 77; width: 80px; " +
                            "height: 22px; padding-left: 5; font-size: 16pt; font-weight: bold; background-color: " +
                            migu.getStates().getStyleWeightGOTVChannel()[i] + ";\">" +
                            migu.getStates().getInstalledWeightGotvChannel()[i] + "</p>\n");


        resp.getWriter().write("<p class=\"time\">" +
                String.format("Масса ГОТВ %.0f кг <br>Заданная масса резервуара %.0f кг<br>Заданная масса ГОТВ %.0f кг<br>Утечка %.0f кг",
                        migu.getStates().getMass(), migu.getStates().getDesiredMassOfEmptyPackaging(), migu.getStates().getSetWeightGOTV(), migu.getStates().getLeakGOTV()) + "<br>" +
                migu.getStates().getTime() + "</p><br>");
//        resp.getWriter().write("<p class=\"mass\">" + String.format("Масса:<br>%.0f кг", migu.getStates().getMass()) + "</p>\n");

        String out = "<p class=\"mass\">" + String.format("Масса:<br>%.0f кг", migu.getStates().getMass()) +
                "</p>\n" + "<p class=\"press\">" + String.format("Давление:<br>%.2f МПа", migu.getStates().getPressure()) + "</p>\n" +
                "<p class=\"time\">" +
                String.format("Масса ГОТВ %.0f кг <br>Заданная масса резервуара %.0f кг<br>Заданная масса ГОТВ %.0f кг<br>Утечка %.0f кг",
                        migu.getStates().getMass(), migu.getStates().getDesiredMassOfEmptyPackaging(), migu.getStates().getSetWeightGOTV(), migu.getStates().getLeakGOTV()) + "<br>" +
                migu.getStates().getTime() + "</p><br>";

    }
}
