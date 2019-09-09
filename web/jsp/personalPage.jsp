<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 08.09.2019
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request"></c:set>
<html>
<head>
    <title>Personal page</title>
    <style>
        <%@include file="/css/style.css"%>
        <%@include file="/css/style-head.css"%>
        <%@include file="/css/style-body.css"%>
        <%@include file="/css/style-context.css"%>
    </style>
</head>
<body>
<%@ include file="header.jsp" %>
<%@ include file="context.jsp" %>
<%@ include file="head-table.jsp" %>
<%@ include file="show.jsp" %>
<%@ include file="edit-window-user.jsp" %>
</body>
<script>
    <%@include file="/js/edit.js"%>
    <%@include file="/js/login.js"%>
</script>
</html>
