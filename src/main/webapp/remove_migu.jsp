<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<p class="add_user_title">Удалить МИЖУ</p>

<div>
  <form method="post" action="/admin_select.do">
    <jsp:useBean id="migu" class="java.util.ArrayList" scope="request">
      <jsp:setProperty name="miguName" property="name" value="<%=request.getAttribute(\"migu\")%>"/>
    </jsp:useBean>
    <table>
      <tr>
        <td style="color: darkblue">Зав. номер</td>

        <td><select name="inputNumbMiguToRemove" size="1" id="selectMigu" class="input_text_add">

          <c:forEach var="item1" items="${migu}">
            <option>${item1.number}</option>
          </c:forEach>
        </select></td>
      </tr>
      <tr>
        <td><input type="submit" value="Удалить" name="removeMiguButton" class="btn_admin_add"></td>
      </tr>
    </table>
  </form>
  <p class="add_user_title">МИЖУ имеющиеся в системе</p>


  <table class="simple-little-table">
    <thead>
    <tr>
      <th scope="col">Зав. номер</th>
    </tr>
    </thead>
    <c:forEach items="${migu}" var="migu">
      <tr>
        <td>${migu.number}</td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>
