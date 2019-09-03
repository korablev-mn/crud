<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 18.08.2019
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/style-body.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="body-cicle color">
    <ul>
        <li class="body-c "><p><img src="img/pencil.gif"></p></li>
        <li class="body-c id"><p>id</p></li>
        <li class="body-c name"><p>name</p></li>
        <li class="body-c pass"><p>password</p></li>
        <li class="body-c bday"><p>birthday</p></li>
    </ul>
</div>
<c:forEach items="${list}" var="user">
    <div class="body-cicle" id="edit">
        <ul>
            <li class="body-c"><p><img src="img/pencil.gif"></p></li>
            <li class="body-c"><p id="edit-id">${user.id}</p></li>
            <li class="body-c"><p id="edit-name">${user.name}</p></li>
            <li class="body-c"><p id="edit-pass">${user.password}</p></li>
            <li class="body-c"><p id="edit-bday">${user.birthday}</p></li>
        </ul>
    </div>
</c:forEach>
<%-- диалоговое окно--%>
<div id="edit-form-container">
    <form action="/user/update" method="POST" id="edit-form" name="val">
        <div id="edit-message"></div>
        <div class="edit-field-hidden"><label for="name-id">Name :</label> <input id="name-id" name="id" type="text"
                                                                                  value=""></div>
        <div class="edit-field"><label for="name-e">Name :</label> <input id="name-e" name="text" type="text" value="">
        </div>
        <div class="edit-field"><label for="pass-e">Password :</label> <input id="pass-e" name="password"
                                                                              type="password" value=""></div>
        <div class="edit-field"><label for="date-e">Birthday :</label> <input id="date-e" name="date" type="date"></div>
        <div class="edit-line">
            <input class="edit-btn" type="submit" name="cmd" value="UPDATE">
            <input class="edit-btn" type="submit" name="cmd" value="DELETE">
            <input class="edit-btn" type="button" name="cancel" value="CANCEL">
        </div>
    </form>
</div>

<script>
    f = function (event) {
        event = event || window.event;
        let id = event.currentTarget.children[0].children[1].textContent;
        let name = event.currentTarget.children[0].children[2].textContent;
        let pass = event.currentTarget.children[0].children[3].textContent;
        let date = event.currentTarget.children[0].children[4].textContent;
        showEdit("Update / Delete user", id, name, pass, date);
        //console.log(date);
    }
    let mod = document.querySelectorAll('#edit');
    for (let i = 0; i < mod.length; i++) {
        mod[i].onclick = f;
    }

    function showCover() {
        let coverDiv = document.createElement('div');
        coverDiv.id = 'edit-div';
        document.body.style.overflowY = 'hidden';
        document.body.append(coverDiv);
    }

    function hideCover() {
        document.getElementById('edit-div').remove();
        document.body.style.overflowY = '';
    }

    function showEdit(text, id, name, pass, date) {
        showCover();
        let form = document.getElementById('edit-form');
        let container = document.getElementById('edit-form-container');
        document.getElementById('edit-message').innerHTML = text;
        document.getElementById('name-id').value = id;
        document.getElementById('name-e').value = name;
        document.getElementById('pass-e').value = pass;
        document.getElementById('date-e').value = new Date(date).toISOString().substring(0, 10);

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
</script>
</body>
</html>