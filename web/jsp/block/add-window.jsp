<%-- диалоговое окно добавления User--%>
<div id="add-form-container">
    <form action="/admin/add" method="POST" id="add-form" name="val">
        <div id="add-message"></div>
        <div class="add-field"><label for="l">Login :</label> <input id="l" name="login" type="text"></div>
        <div class="add-field"><label for="p">Password :</label> <input id="p" name="password" type="password"></div>
        <div class="add-field"><label for="n">Name :</label> <input id="n" name="text" type="text"></div>
        <div class="add-field"><label for="d">Birthday :</label> <input id="d" name="date" type="date"></div>
        <input class="add-btn" type="submit" name="cmd" value="OK">
        <input class="add-btn" type="button" name="cancel" value="CANCEL">
    </form>
</div>
