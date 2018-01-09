package ru.artsok.controllers;


import org.apache.log4j.Logger;
import ru.artsok.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

import static ru.artsok.util.ClassNameUtil.getCurrentClassName;


@WebServlet("/Logout.do")
public class LogoutServlet extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(getCurrentClassName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("context", "moon.jsp");
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("BtnLogOut") != null) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userName")) {
                        try {
                            new UserDaoImpl().removeSessionForUser(URLDecoder.decode(cookie.getValue(), "UTF-8"));
                        } catch (PropertyVetoException | SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            Cookie cookie = new Cookie("userName", null);
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
            cookie = new Cookie("DBJSESSIONID", null);
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
            req.removeAttribute("userName");
            req.getSession(true).invalidate();
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
