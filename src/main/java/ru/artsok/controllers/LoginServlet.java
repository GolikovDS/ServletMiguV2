package ru.artsok.controllers;


import org.apache.log4j.Logger;
import ru.artsok.dao.impl.MiguDaoImpl;
import ru.artsok.dao.impl.UMDaoImp;
import ru.artsok.dao.impl.UserDaoImpl;
import ru.artsok.entitys.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import static ru.artsok.util.ClassNameUtil.getCurrentClassName;


@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(getCurrentClassName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("context", "moon.jsp");
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("loginInput");
        String userPassword = req.getParameter("passwordInput");

        if (req.getAttribute("isLogin").equals("false")) {

            try {
                LOGGER.debug("loginInput | passwordInput /     / " + userName + " | " + userPassword);
                User user = new UserDaoImpl().get(userName);
                if (req.getParameter("btnEnter") != null || userName.equals("")) {
                    if (user != null && userPassword.equals(user.getPassword())) {
                    /*Есть пользователь*/
                        if (req.getParameter("saveMi") != null) {
                            new UserDaoImpl().putSessionForUser(userName, req.getSession().getId());
                        }
                        LOGGER.debug("LOGIN is Exist");
                        Cookie cookie = new Cookie("userName", URLEncoder.encode(userName, "utf-8"));
                        cookie.setMaxAge(860000);
                        resp.addCookie(cookie);
                        cookie = new Cookie("DBJSESSIONID", URLEncoder.encode(req.getSession().getId(), "utf-8"));
                        cookie.setMaxAge(860000);
                        resp.addCookie(cookie);

                        forwardLogin(userName, req, resp);


                    } else {
                        LOGGER.debug("No LOGIN Exist");
                        req.setAttribute("errLogin", "Неверный логин/пароль");
                        req.getRequestDispatcher("login.jsp").forward(req, resp);
                    }
                }
            } catch (PropertyVetoException | SQLException e) {
                LOGGER.debug(e.getMessage());
            }
        } else {
            forwardLogin(userName, req, resp);

        }
    }

    private void forwardLogin(String login, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("context", "moon.jsp");

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
        req.getRequestDispatcher("admin.jsp").forward(req, resp);
    }
}
