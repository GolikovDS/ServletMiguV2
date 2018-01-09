<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
<form method="post" id="moonForm" action="/moon.do">
  <%--<input type="submit" value="Соеденить" name="moon" class="btn_admin_left_panel">--%>

  <jsp:useBean id="migu" class="java.util.ArrayList" scope="request">
    <jsp:setProperty name="miguName" property="name" value="<%=request.getAttribute(\"migu\")%>"/>
  </jsp:useBean>



    <c:forEach var="item" items="${migu}">
      <c:choose>
        <c:when test="${item.number == requestScope.get(\"numb\")}">
          <label>
            <input type="radio" class="option-input radio" id="idNumberSelectMigu" name="selectMigu"
                   checked value="${item.number}"/>
            <b style="color: ${item.color};">МИЖУ №${item.number}</b>
          </label><br/>
        </c:when>
        <c:otherwise>
          <label>
            <input type="radio" class="option-input radio" name="selectMigu"
                   onChange="onCloseMoon(); this.form.submit();"
                   value="${item.number}">
            <b style="color: ${item.color};">МИЖУ №${item.number}</b>
          </label><br/>
        </c:otherwise>
      </c:choose>
    </c:forEach>



</form>
</body>
</html>
