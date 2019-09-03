<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 18.08.2019
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/style-head.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="header-panel" id="show-button">
    <div class="header-out"><img src="img/pencil.gif">
        <p>
            Add
        </p>
    </div>
</div>

<div id="prompt-form-container">
    <form action="/user/add" method="POST" id="prompt-form" name="val">
        <div id="prompt-message"></div>
        <div class="prompt-field"><label for="n">Name :</label> <input id="n" name="text" type="text"></div>
        <div class="prompt-field"><label for="p">Password :</label> <input id="p" name="password" type="password"></div>
        <div class="prompt-field"><label for="d">Birthday :</label> <input id="d" name="date" type="date"></div>
        <input class="btn" type="submit" name="cmd" value="OK">
        <input class="btn" type="button" name="cancel" value="CANCEL">
    </form>
</div>

<script>
    // Показать полупрозрачный DIV, чтобы затенить страницу
    // (форма располагается не внутри него, а рядом, потому что она не должна быть полупрозрачной)
    function showCover() {
        let coverDiv = document.createElement('div');
        coverDiv.id = 'cover-div';
        // убираем возможность прокрутки страницы во время показа модального окна с формой
        document.body.style.overflowY = 'hidden';
        document.body.append(coverDiv);
    }

    function hideCover() {
        document.getElementById('cover-div').remove();
        document.body.style.overflowY = '';
    }

    function showPrompt(text) {
        showCover();
        let form = document.getElementById('prompt-form');
        let container = document.getElementById('prompt-form-container');
        document.getElementById('prompt-message').innerHTML = text;
        form.text.value = '';

        function complete() {
            hideCover();
            container.style.display = 'none';
            document.onkeydown = null;
        }

        form.onsubmit = function () {
            let name = form.text.value;
            let pass = form.password.value;
            let date = form.date.value;
            if (name == '') return false; // игнорируем отправку пустой формы
            if (pass == '') return false;
            if (date == '') return false;
            complete();
            return true;
        };

        form.cancel.onclick = function () {
            complete(null);
        };

        document.onkeydown = function (e) {
            if (e.key == 'Escape') {
                complete(null);
            }
        };

        let lastElem = form.elements[form.elements.length - 1];
        let firstElem = form.elements[0];

        lastElem.onkeydown = function (e) {
            if (e.key == 'Tab' && !e.shiftKey) {
                firstElem.focus();
                return false;
            }
        };

        firstElem.onkeydown = function (e) {
            if (e.key == 'Tab' && e.shiftKey) {
                lastElem.focus();
                return false;
            }
        };

        container.style.display = 'block';
        form.elements.text.focus();
    }

    document.getElementById('show-button').onclick = function () {
        showPrompt("Add user");
    };
</script>
</body>
</html>