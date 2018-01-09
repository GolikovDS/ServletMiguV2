package ru.artsok.controllers;

import ru.artsok.dao.impl.MiguDaoImpl;
import ru.artsok.dao.impl.UMDaoImp;
import ru.artsok.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/migu_select.do")
public class MiguSelectPanel extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("select_migu.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = "";
        String numb = req.getParameter("numb");
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
        req.setAttribute("numb", numb);
        req.getRequestDispatcher("select_migu.jsp").forward(req, resp);
    }
}
