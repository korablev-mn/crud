// Показать полупрозрачный DIV, чтобы затенить страницу
// (форма располагается не внутри него, а рядом, потому что она не должна быть полупрозрачной)
function showCoverL() {
    let coverDiv = document.createElement('div');
    coverDiv.id = 'login-div';
    // убираем возможность прокрутки страницы во время показа модального окна с формой
    document.body.style.overflowY = 'hidden';
    document.body.append(coverDiv);
}

function hideCoverL() {
    document.getElementById('login-div').remove();
    document.body.style.overflowY = '';
}

function showPromptL(text) {
    showCoverL();
    let form = document.getElementById('login-form');
    let container = document.getElementById('login-form-container');
    document.getElementById('login-message').innerHTML = text;
    form.login.value = '';

    function complete() {
        hideCoverL();
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

document.addEventListener("DOMContentLoaded", function(event) {
    let doc = document.getElementById('show-login-menu');
    if (doc.innerText == 'Login') {
        doc.onclick = function () {
             showPromptL("Login");
         }
    } else {
        doc.onclick = function () {
            document.getElementById('logout-form-btn').click();
        }
    }
});