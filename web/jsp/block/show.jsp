<c:forEach items="${list}" var="user">

    <div id="edit-button" class="body-cicle">
        <ul>
            <li><p><img src="<c:url value="/img/pencil.gif"></c:url>"/>edit</p></li>
            <li><p>${user.id}</p></li>
            <li><p>${user.login}</p></li>
            <li><p>******</p></li>
            <li class="pass"><p>${user.password}</p></li>
            <li><p>${user.name}</p></li>
            <li><p>${user.birthday}</p></li>
            <li><p>${user.role}</p></li>
        </ul>
    </div>

</c:forEach>
