
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<p class="add_user_title">Добавить МИЖУ</p>

<div>
  <form method="post" action="/admin_select.do">
    <table>
      <tr>
        <td style="color: darkblue">Зав. номер</td>
        <td><input type="text" name="inputNumber" class="input_text_add"></td>
      </tr>
      <tr>
        <td><input type="submit" value="Добавить" name="addMiguButton" class="btn_admin_add"></td>
      </tr>
    </table>
  </form>
</div>
<p class="add_user_title">МИЖУ имеющиеся в системе</p>

<table class="simple-little-table">
  <thead>
  <tr>
    <th scope="col">Зав. номер</th>
  </tr>
  </thead>
  <jsp:useBean id="migu" class="java.util.ArrayList" scope="request">
    <jsp:setProperty name="miguName" property="name"
                     value="<%=request.getAttribute(\"migu\")%>"/>
  </jsp:useBean>
  <c:forEach items="${migu}" var="migu">
    <tr>
      <td>${migu.number}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
