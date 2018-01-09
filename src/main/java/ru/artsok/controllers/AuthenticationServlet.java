package ru.artsok.controllers;


import org.apache.log4j.Logger;
import ru.artsok.dao.impl.MiguDaoImpl;
import ru.artsok.dao.impl.UMDaoImp;
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
import java.net.URLEncoder;
import java.sql.SQLException;

import static ru.artsok.util.ClassNameUtil.getCurrentClassName;


@WebServlet("/isLogin.do")
public class AuthenticationServlet extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(getCurrentClassName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Cookie[] cookie = req.getCookies();
        String idSessionInCookie = "";
        String nameInCookie = "";
        for (Cookie cookie1 : cookie) {
            if (cookie1.getName().equals("DBJSESSIONID")) {
                idSessionInCookie = cookie1.getValue();
//                LOGGER.debug("Cookie session-" + idSessionInCookie);
            }
            if (cookie1.getName().equals("userName")) {
                nameInCookie = URLDecoder.decode(cookie1.getValue(), "UTF-8");
//                LOGGER.debug("Cookie user name-" + nameInCookie);
            }
        }

        if (req.getAttribute("isLogin").equals("false")) {

            if (!nameInCookie.equals("")) {
                try {
                    String idSession = new UserDaoImpl().get(nameInCookie).getSession();
                    if (idSessionInCookie.equals(idSession)) {
                        req.setAttribute("userName", nameInCookie);
                        req.setAttribute("isLogin", "true");
//                        LOGGER.debug("User in database-");

                        req.getSession().setAttribute("JSESSIONID", idSessionInCookie);
                        Cookie cookieSession = new Cookie("DBJSESSIONID", URLEncoder.encode(req.getSession().getId(), "utf-8"));
                        cookieSession.setMaxAge(860000);
                        resp.addCookie(cookieSession);
                        forwardLogin(nameInCookie, req, resp);

                    } else {
                        req.setAttribute("userName", null);
                        req.setAttribute("isLogin", "false");
                        Cookie cookie1 = new Cookie("userName", "");
                        cookie1.setMaxAge(0);
                        resp.addCookie(cookie1);
//                        LOGGER.debug("NO USER");
                        req.getRequestDispatcher("login.jsp").forward(req, resp);
                    }
                } catch (Exception e) {
                    LOGGER.debug(e.getMessage());
                }
            } else {
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } else {
            forwardLogin(nameInCookie, req, resp);
        }
    }

    private void forwardLogin(String login, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (login.equals("artsok")) {
            try {
                req.setAttribute("migu", new MiguDaoImpl().getAll());
                req.setAttribute("user", new UserDaoImpl().getAll());
                req.setAttribute("userName", login);
            } catch (PropertyVetoException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                req.setAttribute("migu", new UMDaoImp().getMigus(login));
                req.setAttribute("user", new UserDaoImpl().getAll());
                req.setAttribute("userName", login);

            } catch (PropertyVetoException | SQLException e) {
                e.printStackTrace();
            }
        }
        if(req.getAttribute("context") == null)
            req.setAttribute("context", "moon.jsp");

        req.getRequestDispatcher("admin.jsp").forward(req, resp);
    }
}