<div class="header-panel">
    <div class="header-out" id="show-login-menu">
        ${inOut}
    </div>
    <div><img src="img/logo.gif"/>
        <p>${status}</p>
    </div>
    <div class="info">
        <p>${info}</p>
    </div>
</div>

<div id="login-form-container">
    <form action="/login" method="POST" id="login-form" name="val">
        <div id="login-message"></div>
        <div class="login-field"><label >Login :</label> <input name="login" type="text" autocomplete=username></div>
        <div class="login-field"><label >Password :</label> <input name="password" type="password" autocomplete=current-password>
        </div>
        <input class="login-btn" type="submit" name="cmd" value="OK">
        <input class="login-btn" type="button" name="cancel" value="CANCEL">
        <div><a href="/register">Registartion</a></div>
    </form>
</div>

<div id="logout-form" class="hide">
    <form action="/logout" method="POST" name="logoutForm">
        <button type="submit" name="submit" id="logout-form-btn">Submit</button>
    </form>
</div>