<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <link rel="stylesheet" href="resources/css/style.css" type="text/css">
    <link rel="stylesheet" href="resources/css/reset.css" type="text/css">
    <link rel="stylesheet" href="resources/css/leftPanel.css" type="text/css">
    <link rel="stylesheet" href="resources/css/moonStyle.css" type="text/css">

    <%--<script src="resources/js/jquery-1.10.2.js" type="text/javascript"></script>--%>

    <%--<script type="text/javascript">--%>

    <%--console.log(document.getElementById("idNumberSelectMigu"));--%>
    <%--setInterval(function () {--%>
    <%--$.ajax({--%>
    <%--type: "POST",--%>
    <%--url: '/migu_select.do',--%>
    <%--data: {numb: 1},--%>
    <%--success: function (data) {--%>
    <%--$('#SelectMigu').html(data);--%>
    <%--}--%>
    <%--});--%>
    <%--}, 2000);--%>

    <%--</script>--%>

    <script type="text/javascript">


        //        setInterval(function () {
        //            onCloseMoon()
        //        }, 60000);
        var webSocket = new WebSocket('ws://192.168.55.145:8080/websocket');

        webSocket.onopen = function (event) {
            onOpen(event)
        };

        webSocket.onmessage = function (event) {
            onMessage(event)
        };

        webSocket.onclose = function (event) {
            onClose();
        };

        //Принимает значения от onMessage
        function onMessage(event) {
            var response = JSON.parse(event.data);

            if (response.text)
                document.getElementById('ajaxGetUserServletResponse').innerHTML = response.text;
            if (response.img)
                document.getElementById('ajaxGetUserServletResponseImg').innerHTML = response.img;
            if (response.eventm)
                document.getElementById('eventArea').innerHTML = response.eventm;
            if (response.time)
                document.getElementById('ajaxGetUserServletTime').innerHTML = response.time;

        }
        //Принимает значения от onOpen
        function onOpen(event) {
            console.log("Open connect");
            startOnMessage();
        }
        //Принимает значения от onClose
        function onClose(event) {

        }

        function startOnMessage() {
            webSocket.send(document.getElementById("idNumberSelectMigu").value); //вызывает метод onMessage
        }

        function onCloseMoon() {
            onClose("");
            f = document.getElementById("moonForm");
            f.action = '/moon.do';
            f.submit();
        }


    </script>
    <title>МИЖУ</title>
</head>
<body>
<%--<form method="post" action="/Logout.do">--%>
<%--<input type="submit" value="Выход" name="BtnLogOut">--%>
<%--</form>--%>
<form action="/Logout.do" method="post" class="over_head">
    <table style="width: 100%;">
        <tr>
            <td style="padding-left: 250px">
                <a href="index.jsp">
                    <img src="resources/images/LOGO_ARTSOK.png" width="60" height="50" align="left">
                </a>

                <p style="color: aliceblue; font-size: 30px; padding-top: 7px; "> ЗАО АРТСОК</p>
            </td>
            <td align="right">
                <p name="name" id="user_name"
                   style="color: aliceblue; font-size: 20px; padding-top: 15px; ">${requestScope.get("userName")}</p>
            </td>
            <td align="right">
                <input type="submit" name="BtnLogOut" class="btn_enter" value="Выход "
                       + ${requestScope.get("userName")}/>
            </td>
        </tr>
    </table>
</form>

<div class="left_admin_panel" style="padding: 0">

    <form method="post" action="/admin_select.do">
        <input type="submit" value="Сообщения" name="messageToUser" class="btn_admin_left_panel">
        <c:choose>
            <c:when test="${requestScope.get(\"userName\") == \"artsok\"}">
                <input type="submit" value="Добавить пользователя" name="addUser" class="btn_admin_left_panel">
                <input type="submit" value="Удалить пользователя" name="removeUser" class="btn_admin_left_panel">
                <input type="submit" value="Привязать МИЖУ к пользователю" name="MiguToUser"
                       class="btn_admin_left_panel" style="font-size: 11px">
                <input type="submit" value="Добавить МИЖУ" name="addMigu" class="btn_admin_left_panel">
                <input type="submit" value="Удалить МИЖУ" name="removeMigu" class="btn_admin_left_panel">
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>
    </form>

    <br/>
    <table>
        <tr>
            <td style="color: darkblue; text-align: center; font-size: 24px; padding-left: 25px; font-family: Arial;">
                Мониторинг
            </td>
        </tr>
    </table>


    <div class="panelSelectMigu" id="SelectMigu">
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
    </div>


</div>

<div class="center_admin_panel">
    <jsp:include page="${requestScope.get('context')}"/>
</div>
</body>
</html>
