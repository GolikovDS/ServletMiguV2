package ru.artsok.controllers;


import org.apache.log4j.Logger;
import ru.artsok.dao.impl.MiguDaoImpl;
import ru.artsok.dao.impl.UserDaoImpl;
import ru.artsok.tcp.entity.interfaces.MiguHandle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

import static ru.artsok.util.ClassNameUtil.getCurrentClassName;

@WebServlet("/moon.do")
public class MoonServlet extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(getCurrentClassName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("selectMigu").equals("")) {
            req.setAttribute("numb", "1");
        } else {
            req.setAttribute("numb", req.getParameter("selectMigu"));
            if (MiguHandle.miguMap.getMap().get(Integer.valueOf(req.getParameter("selectMigu"))) != null) {
                LOGGER.debug(req.getParameter("selectMigu"));
                req.setAttribute("miguMap", MiguHandle.miguMap.getMap().get(Integer.valueOf(req.getParameter("selectMigu"))).toString());
            } else {
                req.setAttribute("miguMap", "Нет соеденения");
            }
        }

        try {
            req.setAttribute("user", new UserDaoImpl().getAll());
            req.setAttribute("migu", new MiguDaoImpl().getAll());

        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        }
        LOGGER.debug("IP: " + req.getRemoteAddr() + ", name: " + "TODO" + ", МИЖУ №");
        req.setAttribute("context", "moon.jsp");
        req.getRequestDispatcher("/isLogin.do").forward(req, resp);
    }
}
