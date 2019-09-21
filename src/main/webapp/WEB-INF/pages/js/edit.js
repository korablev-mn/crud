
    f = function (event) {
        event = event || window.event;
        let id = event.currentTarget.children[0].children[1].textContent;
        let login = event.currentTarget.children[0].children[2].textContent;
        let pass = event.currentTarget.children[0].children[4].textContent;
        let name = event.currentTarget.children[0].children[5].textContent;
        let date = event.currentTarget.children[0].children[6].textContent;
        let role = event.currentTarget.children[0].children[7].textContent;
        showEdit("Update / Delete user", id, login, name, pass, date, role);
    }

    let mod = document.querySelectorAll('#edit-button');
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

    function showEdit(text, id, login, name, pass, date, role) {
        showCover();
        let form = document.getElementById('edit-form');
        let container = document.getElementById('edit-form-container');
        document.getElementById('edit-message').innerHTML = text;
        document.getElementById('name-id').value = id;
        document.getElementById('login-e').value = login;
        document.getElementById('name-e').value = name;
        document.getElementById('pass-e').value = pass;
        document.getElementById('date-e').value = !date ? date : new Date(date).toISOString().substring(0, 10);
        document.getElementById('role-e').value = role;

        function complete() {
            hideCover();
            container.style.display = 'none';
            document.onkeydown = null;
        }

        form.onsubmit = function () {
            let login = form.login.value;
            let pass = form.password.value;
            if (login == '') return false; // игнорируем отправку пустой формы
            if (pass == '') return false;
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
        form.elements.login.focus();
    }