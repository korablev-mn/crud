<%-- диалоговое окно редактирования User--%>
<div id="edit-form-container">
    <form action="/user/update" method="POST" id="edit-form" name="val">
        <div id="edit-message"></div>
        <div class="edit-field-hidden"><label for="name-id">id :</label> <input id="name-id" name="id" type="text" value=""></div>
        <div class="edit-field"><label for="login-e">Login :</label> <input id="login-e" name="login" type="text" value=""></div>
        <div class="edit-field"><label for="pass-e">Password :</label> <input id="pass-e" name="password" type="text" value=""></div>
        <div class="edit-field"><label for="name-e">Name :</label> <input id="name-e" name="text" type="text" value=""></div>
        <div class="edit-field"><label for="date-e">Birthday :</label> <input id="date-e" name="date" type="date"></div>
        <div class="edit-field role-personal"><label for="role-e">Role :</label>
            <select id="role-e" name="role">
                <option value="user" selected>user</option>
<%--                <option value="admin">admin</option>--%>
            </select>
        </div>
        <div class="edit-line">
            <input class="edit-btn" type="submit" name="cmd" value="UPDATE">
            <input class="edit-btn" type="submit" name="cmd" value="DELETE">
            <input class="edit-btn" type="button" name="cancel" value="CANCEL">
        </div>
    </form>
</div>
