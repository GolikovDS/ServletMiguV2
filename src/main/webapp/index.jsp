<%@ page import="java.util.Locale" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <link rel="stylesheet" href="resources/css/reset.css" type="text/css">
    <link rel="stylesheet" href="resources/css/style.css" type="text/css">


    <script type="text/javascript" src="resources/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="resources/js/cufon-yui.js"></script>
    <script type="text/javascript" src="resources/js/Humanst521_BT_400.font.js"></script>
    <script type="text/javascript" src="resources/js/Humanst521_Lt_BT_400.font.js"></script>
    <script type="text/javascript" src="resources/js/roundabout.js"></script>
    <script type="text/javascript" src="resources/js/roundabout_shapes.js"></script>
    <script type="text/javascript" src="resources/js/gallery_init.js"></script>
    <script type="text/javascript" src="resources/js/cufon-replace.js"></script>

    <title>МИЖУ</title>
</head>
<body>

<form action="/isLogin.do" method="post" class="over_head">
    <table style="width: 100%;">
        <tr>
            <td style="padding-left: 250px">
                <a href="index.jsp">
                    <img src="resources/images/LOGO_ARTSOK.png" width="60" height="50" align="left">
                </a>
                <p style="color: aliceblue; font-size: 30px; padding-top: 7px; font-family: Tahoma; padding-left: 50px;">ЗАО АРТСОК</p></td>
            <td align="right">
                <p name="name" style="color: aliceblue; font-size: 20px; padding-top: 15px; padding-right: 1px; font-family: Tahoma;">${requestScope.get("userName")}</p>
            </td>
            <td align="right">
                <input type="submit" class="btn_enter" value="Личный кабинет"/>
            </td>
        </tr>
    </table>
</form>

<!-- #gallery -->
<section id="gallery">
    <div class="container">
        <ul id="myRoundabout">
            <li><img src="resources/images/s3.jpg" alt=""></li>
            <li><img src="resources/images/s2.jpg" alt=""></li>
            <li><img src="resources/images/s5.jpg" alt=""></li>
            <li><img src="resources/images/s1.JPG" alt=""></li>
            <li><img src="resources/images/s4.JPG" alt=""></li>
        </ul>
    </div>
</section>
<!-- /#gallery -->

<div class="shell">
    <p>МИЖУ предназначен для противопожарной защиты помещений и технологического оборудования в составе установок
        газового пожаротушения двуокисью углерода и обеспечивает:
        – многократную подачу любого количества ЖУ из резервуара МИЖУ через ЗПУ;
        – заправку, дозаправку и слив ЖУ;
        – длительное бездренажное хранение основного и резервного запасов ЖУ в резервуаре при давлении от 1,95 до 2,05
        МПа (для МИЖУ-28/3,3 – от 2,95 до 3,05 МПа), при периодически работающих ХА или ЭН;
        <img src="resources/images/s3.jpg" width="250" height="180" align="right" vspace="5" hspace="5">
        МИЖУ представляет собой комплексный агрегат, состоящий из установки длительного хранения для жидкой двуокиси
        углерода типа УДХ (далее – «резервуар») с запорной и регулирующей арматурой, КИП и электронагревателями (ЭН),
        запорно-пускового устройства (ЗПУ) с побудительным баллоном (ПБ), блока холодильных агрегатов (ХА) с комплектом
        оборудования холодильного контура, шкафа управления (ШУ) и четырех тензорезисторных датчиков.
        МИЖУ поставляется в виде комплекта составных частей, окончательная сборка (монтаж) и наладка которого
        осуществляется на объекте эксплуатации в соответствии с разделом 2.2 настоящего Руководства по эксплуатации.
    </p>

</div>
<div class="address">
    <table style="left: 5%;">
        <tr>
            <td>
                <img src="resources/images/LOGO_ARTSOK.png" width="120" height="90" align="left">
            </td>
            <td>
                <p class="down_text">Разработка, производство, проектирование, поставка, монтаж, техническое обслуживание систем пожаротушения.
                Система менеджмента качества соответствует требованиям ISO 9001:2008 и ГОСТ ISO 9001-2011.
                Оборудование сертифицировано в Российской Федерации, Республике Беларусь, Республике Казахстан, ЕАЭС и ЕС.
                Лицензия Ростехнадзора на конструирование и изготовление оборудования для атомных станций.
                Свидетельство о признании изготовителя Российским морским регистром судоходства.</p>
            </td>
        </tr>
        <tr>
            <td>
                <img src="resources/images/iso.jpg" width="80" height="40" align="left">
                <img src="resources/images/sea.png" width="40" height="40" align="left">
            </td>
            <td>
                <p class="down_text">Тел. +7 (495) 775-27-96; +7 (495) 745-74-34 e-mail: postmaster@artsok.com http://artsok.com, http://артсок.рф<br/>
                142771,г.Москва, ул. Адмирала Корнилова (п. Мосрентген) вл. 28</p>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
