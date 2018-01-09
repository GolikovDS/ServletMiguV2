package ru.artsok.controllers;

import org.apache.log4j.Logger;
import ru.artsok.tcp.entity.MiguState;
import ru.artsok.tcp.entity.interfaces.MiguHandle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.artsok.util.ClassNameUtil.getCurrentClassName;

@WebServlet("/GetUserServletErrorAndEvent")
public class AjaxServletErrorAndEvent extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(getCurrentClassName());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int selectNumb = Integer.parseInt(req.getParameter("numb"));
        LOGGER.debug("IP: " + req.getRemoteAddr() + ", name: " + "TODO" + ", МИЖУ №" + selectNumb);
        MiguState state = MiguHandle.miguMap.getMap().get(selectNumb).getStates();
        if (MiguHandle.miguMap.getMap().get(selectNumb) != null) {
            req.setAttribute("numb", req.getParameter("selectMigu"));
        }

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain");

        resp.getWriter().write("<p class=\"errorMessage\">");
        if (MiguHandle.miguMap.getMap().get(selectNumb) != null) {
            for (String str : state.getErrOrEvent()) {
                resp.getWriter().write(str + "<br>");
            }
        }

        resp.getWriter().write("</p>\n");


    }
}
