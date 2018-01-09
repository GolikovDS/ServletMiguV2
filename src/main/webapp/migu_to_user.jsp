<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<p class="add_user_title">Добавит или удалить привязку МИЖУ к пользователю</p>

<form method="post" action="/admin_select.do">
    <table>
        <tr>
            <td style="color: darkblue">
                Логин
            </td>
            <td>
                <select name="selectUser" size="1" id="selectUser" class="input_text_add">
                    <jsp:useBean id="user" class="java.util.ArrayList" scope="request">
                        <jsp:setProperty name="userName" property="name" value="<%=request.getAttribute(\"user\")%>"/>
                    </jsp:useBean>
                    <c:forEach var="item" items="${user}">
                        <option>${item.login}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td style="color: darkblue">
                Номер МИЖУ
            </td>
            <td>
                <select name="selectMigu" size="1" id="selectMigu" class="input_text_add">
                    <jsp:useBean id="migu" class="java.util.ArrayList" scope="request">
                        <jsp:setProperty name="miguName" property="name" value="<%=request.getAttribute(\"migu\")%>"/>
                    </jsp:useBean>
                    <c:forEach var="item1" items="${migu}">
                        <option>${item1.number}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td>
                <input type="submit" value="Добавить" name="miguToUserButton" class="btn_admin_add">
            </td>
            <td>
                <input type="submit" value="Удалить" name="removeMiguToUserButton" class="btn_admin_add">
            </td>
        </tr>
    </table>
</form>
<p class="add_user_title">Имеющиеся в системе привязки МИЖУ к пользователю</p>


<table class="simple-little-table">
    <thead>
    <tr>
        <th scope="col">Логин</th>
        <th scope="col">Номер МИЖУ</th>

    </tr>
    </thead>
    <jsp:useBean id="um" class="java.util.ArrayList" scope="request">
        <jsp:setProperty name="umName" property="name"  value="<%=request.getAttribute(\"um\")%>"/>
    </jsp:useBean>
    <c:forEach items="${um}" var="um">
        <tr>
            <td>${um.login}</td>
            <td>${um.numb}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
