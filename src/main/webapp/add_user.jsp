<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>


    <title></title>
</head>
<body>

<div>
    <p class="add_user_title">Добавить пользователя</p>
    <form method="post" action="/admin_select.do">
        <table>
            <tr>
                <td style="color: darkblue">Логин</td>
                <td><input type="text" name="inputLogin" class="input_text_add"></td>
            </tr>
            <tr>
                <td style="color: darkblue">Пароль</td>
                <td><input type="text" name="inputPassword" class="input_text_add"></td>
            </tr>
            <tr>
                <td style="color: darkblue">Организация</td>
                <td><input type="text" name="inputOrg" class="input_text_add"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Добавить" name="addUserButton" class="btn_admin_add"></td>
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
    <jsp:useBean id="user" class="java.util.ArrayList" scope="request">
        <jsp:setProperty name="userName" property="name"
                         value="<%=request.getAttribute(\"user\")%>"/>
    </jsp:useBean>
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
