<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.02.2017
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="resources/css/context.css" type="text/css">
    <title></title>
</head>
<body>

<p class="add_user_title">Удалить пользователя</p>

<div>
    <jsp:useBean id="user" class="java.util.ArrayList" scope="request">
        <jsp:setProperty name="userName" property="name" value="<%=request.getAttribute(\"user\")%>"/>
    </jsp:useBean>
    <form method="post" action="/admin_select.do">
        <table>
            <tr>
                <td style="color: darkblue">Логин</td>
                <%--<td><input type="text" name="inputLoginToRemove" class="input_text_add"></td>--%>
                <td><select name="inputLoginToRemove" size="1" id="selectUser" class="input_text_add">

                    <c:forEach var="item" items="${user}">
                        <option>${item.login}</option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td><input type="submit" value="Удалить" name="removeUserButton" class="btn_admin_add"></td>
            </tr>
        </table>
    </form>
</div>

<br/>
<p class="add_user_title">Пользователи имеющиеся в системе</p>

<table class="simple-little-table">
    <thead>
    <tr>
        <th scope="col">Логин</th>
        <th scope="col">Пароль</th>
        <th scope="col">Организация</th>
    </tr>
    </thead>

    <c:forEach items="${user}" var="user">
        <tr>
            <td><%--@declare id="user"--%><label for="user">${user.login}</label></td>
            <td>${user.password}</td>
            <td>${user.organization}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
