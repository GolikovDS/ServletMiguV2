<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>


<html>

<head>

    <link rel="stylesheet" href="resources/css/loginStyle.css" type="text/css">

    <title>АИСТ</title>

</head>

<body>
<div>
    <form method="post" action="index.jsp">
        <input type="submit" name="btnBack" class="btn btn-primary btn-block btn-large" value="Назад" tabindex="3">
    </form>
</div>
<div class="login_array">
    <form method="post" action="login.do">
        <table>
            <tr>
                <td>
                    <label>Логин</label>
                </td>
                <td>
                    <input type="text" name="loginInput" tabindex="1" placeholder="Логин" required>
                </td>
            </tr>

            <tr>
                <td>
                    <label>Пароль</label>
                </td>
                <td>
                    <input type="password" name="passwordInput" placeholder="Пароль" tabindex="2" required>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="checkbox" name="saveMi" class="checkBoxInput" id="cb">
                </td>
                <td>
                    <label for="cb">Запомнить</label>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <input type="submit" name="btnEnter" class="btn btn-primary btn-block btn-large" value="Вход"
                           tabindex="3">

                    <p style="color: #ff0000; font-size: 10px;">${requestScope.get("errLogin")}</p>
                </td>
            </tr>
        </table>
    </form>

</div>

</body>
</html>
