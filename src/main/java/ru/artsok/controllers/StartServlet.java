package ru.artsok.controllers;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/index")
public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("index.jsp").forward(req, resp);
//        try {
//            req.setAttribute("user", new UserDaoImpl().getAll());
//            req.setAttribute("migu", new MiguDaoImpl().getAll());
//        } catch (PropertyVetoException | SQLException e) {
//            e.printStackTrace();
//        }
//        req.setAttribute("context", "add_user.jsp");
//        req.getRequestDispatcher("admin.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
