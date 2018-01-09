package ru.artsok.controllers;

import ru.artsok.dao.impl.MessageToUserImpl;
import ru.artsok.dao.impl.MiguDaoImpl;
import ru.artsok.dao.impl.UMDaoImp;
import ru.artsok.dao.impl.UserDaoImpl;
import ru.artsok.entitys.MessageToUser;
import ru.artsok.entitys.Migu;
import ru.artsok.entitys.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@WebServlet("/admin_select.do")
public class AdminPanelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
        req.setAttribute("context", "moon.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("moon") != null) {
            req.setAttribute("context", "moon.jsp");
        }


        if (req.getParameter("addUser") != null) {
            req.setAttribute("context", "add_user.jsp");
        }

        if (req.getParameter("messageToUser") != null) {
            String name = (String)req.getAttribute("userName");
            if(!name.equals("artsok")){
                req.setAttribute("messages", new MessageToUserImpl().stringMessages(name));
            }
            req.setAttribute("context", "message_to_user.jsp");
        }

        if (req.getParameter("addMigu") != null) {
            req.setAttribute("context", "add_migu.jsp");
        }


        if (req.getParameter("MiguToUser") != null) {
            try {
                req.setAttribute("um", new UMDaoImp().getLoginAndNumb());
            } catch (PropertyVetoException | SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("context", "migu_to_user.jsp");
        }

        if (req.getParameter("selectUser") != null) {
            String selectUser = req.getParameter("selectUser");
            req.setAttribute("messages", new MessageToUserImpl().stringMessages(selectUser));
            //todo
            req.setAttribute("selected", selectUser);
            req.setAttribute("context", "message_to_user.jsp");
        }

        if (req.getParameter("removeMigu") != null) {
            req.setAttribute("context", "remove_migu.jsp");
        }

        if (req.getParameter("removeUser") != null) {
            try {
                req.setAttribute("user", new UserDaoImpl().getAll());
            } catch (PropertyVetoException | SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("context", "remove_user.jsp");
        }

        if (req.getParameter("addUserButton") != null) {
            try {
                new UserDaoImpl().add(new User(req.getParameter("inputLogin"), req.getParameter("inputPassword"), req.getParameter("inputOrg"), ""));
                req.setAttribute("context", "add_user.jsp");

            } catch (PropertyVetoException | SQLException e) {
                e.printStackTrace();
            }
        }

        if (req.getParameter("removeUserButton") != null) {

            try {
                new UserDaoImpl().remove(req.getParameter("inputLoginToRemove"));
                req.setAttribute("context", "remove_user.jsp");

            } catch (PropertyVetoException | SQLException e) {
                e.printStackTrace();
            }
        }

        if (req.getParameter("addMiguButton") != null) {
            try {
                new MiguDaoImpl().add(new Migu(req.getParameter("inputNumber")));
                req.setAttribute("context", "add_migu.jsp");

            } catch (PropertyVetoException | SQLException e) {
                e.printStackTrace();
            }
        }

        if (req.getParameter("removeMiguButton") != null) {
            try {
                new MiguDaoImpl().remove(req.getParameter("inputNumbMiguToRemove"));
                req.setAttribute("context", "remove_migu.jsp");
            } catch (PropertyVetoException | SQLException e) {
                e.printStackTrace();
            }
        }

        if (req.getParameter("miguToUserButton") != null) {
            try {
                new UMDaoImp().add(req.getParameter("selectUser"), req.getParameter("selectMigu"));
                req.setAttribute("um", new UMDaoImp().getLoginAndNumb());
            } catch (PropertyVetoException | SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("context", "migu_to_user.jsp");
        }

        if (req.getParameter("messageToUserButton") != null) {
            String name = (String)req.getAttribute("userName");
            try {
                if(!name.equals("artsok")){
                    new MessageToUserImpl().add(new MessageToUser(name,
                            new SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis()),
                            req.getParameter("text_message")));
                }else {
                    new MessageToUserImpl().add(new MessageToUser(req.getParameter("selectUser"),
                            new SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis()),
                            req.getParameter("text_message")));
                }
                if(!name.equals("artsok")){
                    req.setAttribute("messages", new MessageToUserImpl().stringMessages(name));
                }
            } catch (PropertyVetoException | SQLException e) {
                e.printStackTrace();
            }
//todo
            req.setAttribute("context", "message_to_user.jsp");
        }
        if (req.getParameter("answerButton") != null) {
            new MessageToUserImpl().answer(4, "");
            //todo
        }

        if (req.getParameter("removeMiguToUserButton") != null) {
            try {
                new UMDaoImp().remove(req.getParameter("selectUser"), req.getParameter("selectMigu"));
                req.setAttribute("um", new UMDaoImp().getLoginAndNumb());
            } catch (PropertyVetoException | SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("context", "migu_to_user.jsp");
        }

        try {
            req.setAttribute("um", new UMDaoImp().getLoginAndNumb());
            req.setAttribute("migu", new MiguDaoImpl().getAll());
            req.setAttribute("user", new UserDaoImpl().getAll());
        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/isLogin.do").forward(req, resp);
    }
}
