package ru.artsok.controllers;

import org.apache.log4j.Logger;
import ru.artsok.dao.impl.MiguDaoImpl;
import ru.artsok.dao.impl.UMDaoImp;
import ru.artsok.entitys.Migu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static ru.artsok.util.ClassNameUtil.getCurrentClassName;

@WebServlet("/GetSelectMigu.do")
public class AjaxSelectMigu extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(getCurrentClassName());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
        req.setAttribute("context", "moon.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int selectNumb = Integer.parseInt(req.getParameter("numb"));
        String login = req.getParameter("name");
        req.setCharacterEncoding("UTF-8");
        if (login.equals("artsok")) {
            try {
                resp.getWriter().write(getStringSelectMigu(new MiguDaoImpl().getAll(), selectNumb));

            } catch (PropertyVetoException | SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                resp.getWriter().write(getStringSelectMigu(new UMDaoImp().getMigus(login), selectNumb));

            } catch (PropertyVetoException | SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private String getStringSelectMigu(List<Migu> migus, int selectNumb) {
        StringBuilder result = new StringBuilder();
        for (Migu migu : migus) {
            if (Integer.parseInt(migu.getNumber()) == selectNumb) {
                result.append("<label>").
                        append("<input type=\"radio\" class=\"option-input radio\" id=\"idNumberSelectMigu\" " +
                                "name=\"selectMigu\" checked value=\"").append(migu.getNumber()).append("\"/>").
                        append("<b style=\"color: ").append(migu.getColor()).append(";\">МИЖУ №").append(migu.getNumber()).append("</b>")
                        .append("</label><br/>");
            } else {
                result.append("<label>").
                        append("<input type=\"radio\" class=\"option-input radio\" id=\"idNumberSelectMigu\" " +
                                "name=\"selectMigu\" value=\"").append(migu.getNumber()).append("\"/>").
                        append("<b style=\"color: ").append(migu.getColor()).append(";\">МИЖУ №").append(migu.getNumber()).append("</b>")
                        .append("</label><br/>");
            }
        }

        return result.toString();
    }


}


//<div class="panelSelectMigu" id="SelectMigu">
//<c:forEach var="item" items="${migu}">
//<c:choose>
//<c:when test="${item.number == requestScope.get(\"numb\")}">
//<label>
//<input type="radio" class="option-input radio" id="idNumberSelectMigu" name="selectMigu"
//        checked value="${item.number}"/>
//<b style="color: ${item.color};">МИЖУ №${item.number}</b>
//</label><br/>
//</c:when>
//<c:otherwise>
//<label>
//<input type="radio" class="option-input radio" name="selectMigu"
//        onChange="onCloseMoon(); this.form.submit();"
//        value="${item.number}">
//<b style="color: ${item.color};">МИЖУ №${item.number}</b>
//</label><br/>
//</c:otherwise>
//</c:choose>
//</c:forEach>
//</div>

