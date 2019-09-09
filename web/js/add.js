// Показать полупрозрачный DIV, чтобы затенить страницу
// (форма располагается не внутри него, а рядом, потому что она не должна быть полупрозрачной)
function showCoverAdd() {
    let coverDiv = document.createElement('div');
    coverDiv.id = 'add-div';
    // убираем возможность прокрутки страницы во время показа модального окна с формой
    document.body.style.overflowY = 'hidden';
    document.body.append(coverDiv);
}

function hideCoverAdd() {
    document.getElementById('add-div').remove();
    document.body.style.overflowY = '';
}

function showPromptAdd(text) {
    showCoverAdd();
    let form = document.getElementById('add-form');
    let container = document.getElementById('add-form-container');
    document.getElementById('add-message').innerHTML = text;
     form.login.value = '';

    function complete() {
        hideCoverAdd();
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

document.getElementById('add-button').onclick = function () {
    showPromptAdd("Add user");
};