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
import java.util.ArrayList;
import java.util.List;

import static ru.artsok.util.ClassNameUtil.getCurrentClassName;

@WebServlet("/GetUserServletImage")
public class AjaxServletImge extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(getCurrentClassName());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String sufix = " width=1200 height=650 style=\"position: absolute; z-index: 0\"";
        String prefix = "<img src=/resources/images/view/";
        Migu migu;
        int selectNumb = Integer.parseInt(req.getParameter("numb"));
        List<Integer> list = new ArrayList<>();

        if (MiguHandle.miguMap.getMap().get(selectNumb) != null) {
            req.setAttribute("numb", req.getParameter("selectMigu"));
            migu = MiguHandle.miguMap.getMap().get(selectNumb);
            list = migu.getStates().getErrOrEventNumber();

            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/plain");
            resp.getWriter().write(prefix + "main.gif" + sufix + ">\n");

            if (list.contains(81) || list.contains(82) || list.contains(83) || list.contains(84) || list.contains(86) || list.contains(87) || list.contains(88)) {
                resp.getWriter().write(prefix + "XA/1/XA1_err.gif" + sufix + ">\n");
            } else if (list.contains(111)) {
                resp.getWriter().write(prefix + "XA/1/XA1_work.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "XA/1/XA1.gif" + sufix + ">\n");
            }
            if (list.contains(91) || list.contains(92) || list.contains(93) || list.contains(94) || list.contains(96) || list.contains(97) || list.contains(98)) {
                resp.getWriter().write(prefix + "XA/2/XA2_err.gif" + sufix + ">\n");
            } else if (list.contains(111)) {
                resp.getWriter().write(prefix + "XA/2/XA2_work.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "XA/2/XA2.gif" + sufix + ">\n");
            }

            if (list.contains(111)) {
                resp.getWriter().write(prefix + "EH/1/EH1_err.gif" + sufix + ">\n");
            } else if (list.contains(111)) {
                resp.getWriter().write(prefix + "EH/1/EH1_work.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "EH/1/EH1.gif" + sufix + ">\n");
            }
            if (list.contains(112)) {
                resp.getWriter().write(prefix + "EH/2/EH2_err.gif" + sufix + ">\n");
            } else if (list.contains(112)) {
                resp.getWriter().write(prefix + "EH/2/EH2_work.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "EH/2/EH2.gif" + sufix + ">\n");
            }

            if (list.contains(22) || list.contains(21)) {
                resp.getWriter().write(prefix + "cylinder/cilinder_err.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "cylinder/cilinder.gif" + sufix + ">\n");
            }

            if (list.contains(20)) {
                resp.getWriter().write(prefix + "manometer/manometr_error.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "manometer/manometr.gif" + sufix + ">\n");
            }

            if (list.contains(122)) {
                resp.getWriter().write(prefix + "valve/valve_error.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "valve/valve.gif" + sufix + ">\n");
            }

            if (list.contains(123)) {
                resp.getWriter().write(prefix + "valve/valve_outlet.gif" + sufix + ">\n");
            }

            if (list.contains(121)) {
                resp.getWriter().write(prefix + "мembrane/мembrane_error.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "мembrane/мembrane.gif" + sufix + ">\n");
            }

            if (list.contains(124)) {
                resp.getWriter().write(prefix + "мembrane/membrane_outlet.gif" + sufix + ">\n");
            }

            if (list.contains(135) && list.contains(137)) {
                resp.getWriter().write(prefix + "zpu/zpu_starup.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "zpu/zpu.gif" + sufix + ">\n");
            }

            if (list.contains(11) || list.contains(12) || list.contains(13) || list.contains(14) || list.contains(15) || list.contains(24) || list.contains(23)) {
                resp.getWriter().write(prefix + "zpu/zpu_el_pusk/zpu_el_pusk_error.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "zpu/zpu_el_pusk/zpu_el_pusk.gif" + sufix + ">\n");
            }

            if (list.contains(13) || list.contains(14) || list.contains(15) || list.contains(24) || list.contains(23)) {
                resp.getWriter().write(prefix + "zpu/zpu_el_tap/zpu_el_tap_error.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "zpu/zpu_el_tap/zpu_el_tap.gif" + sufix + ">\n");
            }

            if (list.contains(17) || list.contains(18) || list.contains(19)) {
                resp.getWriter().write(prefix + "zpu/zpu_tap/zpu_tap_error.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "zpu/zpu_tap/zpu_tap.gif" + sufix + ">\n");
            }

            if (list.contains(86) || list.contains(87)) {
                resp.getWriter().write(prefix + "XA/1/cb/XA1_CD_error.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "XA/1/cb/XA1_CD.gif" + sufix + ">\n");
            }

            if (list.contains(83) || list.contains(84)) {
                resp.getWriter().write(prefix + "XA/1/k1/XA1_k_error.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "XA/1/k1/XA1_k.gif" + sufix + ">\n");
            }

            if (list.contains(81)) {
                resp.getWriter().write(prefix + "XA/1/PD11/XA1_PD11_error.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "XA/1/PD11/XA1_PD11.gif" + sufix + ">\n");
            }

            if (list.contains(82)) {
                resp.getWriter().write(prefix + "XA/1/PD12/XA1_PD12_error.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "XA/1/PD12/XA1_PD12.gif" + sufix + ">\n");
            }

            if (list.contains(96) || list.contains(97)) {
                resp.getWriter().write(prefix + "XA/2/cb2/XA2_CD_error.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "XA/2/cb2/XA2_CD.gif" + sufix + ">\n");
            }

            if (list.contains(93) || list.contains(84)) {
                resp.getWriter().write(prefix + "XA/2/k2/XA2_K1_error.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "XA/2/k2/XA2_K1.gif" + sufix + ">\n");
            }

            if (list.contains(91)) {
                resp.getWriter().write(prefix + "XA/2/PD21/XA2_PD21_error.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "XA/2/PD21/XA2_PD21.gif" + sufix + ">\n");
            }

            if (list.contains(92)) {
                resp.getWriter().write(prefix + "XA/2/PD22/XA2_PD22_error.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "XA/2/PD22/XA2_PD22.gif" + sufix + ">\n");
            }

            if (list.contains(1)) {
                resp.getWriter().write(prefix + "power/power_error.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "power/power.gif" + sufix + ">\n");
            }

            if (list.contains(3)) {
                resp.getWriter().write(prefix + "power/akb_error.gif" + sufix + ">\n");
            } else {
                resp.getWriter().write(prefix + "power/akb.gif" + sufix + ">\n");
            }
        }

    }
}
