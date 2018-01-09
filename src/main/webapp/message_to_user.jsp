<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="resources/css/message.css" type="text/css">

    <title></title>
</head>
<body>
<p class="add_user_title">Сообщения</p>

<form method="post" action="/admin_select.do">

</form>

<form method="post" action="/admin_select.do">
    <table border="1">
        <c:choose>
            <c:when test="${requestScope.get(\"userName\") == \"artsok\"}">
                <tr>
                    <td style="color: darkblue">
                        Логин
                    </td>
                    <td>

                        <select name="selectUser" size="1" id="selectUser" class="input_text_add"
                                onchange="this.form.submit();">

                            <jsp:useBean id="user" class="java.util.ArrayList" scope="request">
                                <jsp:setProperty name="userName" property="name"
                                                 value="<%=request.getAttribute(\"user\")%>"/>
                            </jsp:useBean>

                            <c:forEach var="item" items="${user}">
                                <c:choose>
                                    <c:when test="${item.login == requestScope.get(\"selected\")}">
                                        <option selected>${item.login}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option>${item.login}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>


                    </td>
                </tr>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>
        <tr>
            <td style="color: darkblue">
                Текст сообщения
            </td>
        </tr>

    </table>

    <label>
        <textarea rows="5" cols="45" style="resize: none; overflow: auto" name="text_message"></textarea>
    </label>
    <input type="submit" value="Отправить" name="messageToUserButton" class="btn_admin_add">
</form>
<div id="history_div">
    <h1>История сообщений</h1>

    <p>${requestScope.get("messages")}</p>


</div>

</body>
</html>
