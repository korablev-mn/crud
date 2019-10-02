

<c:forEach items="${list}" var="user">

    <div id="edit-button" class="body-cicle">
        <ul>
            <li><p><img src="img/pencil.gif"/>edit</p></li>
            <li><p>${user.id}</p></li>
            <li><p>${user.login}</p></li>
            <li><p>******</p></li>
            <li class="pass"><p>${user.password}</p></li>
            <li><p>${user.name}</p></li>
            <li><p>${user.birthday}</p></li>
            <li><p>

    <c:forEach items="${user.roles}" var="roles">

            <c:out value="${roles['role']}"/>

    </c:forEach>

            </p></li>
        </ul>
    </div>

</c:forEach>
