<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 18.08.2019
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request"></c:set>

<html>
<head>
    <title>User</title>
    <style>
        <%@include file="css/style.css"%>
        <%@include file="css/style-body.css"%>
        <%@include file="css/style-head.css"%>
        <%@include file="css/style-context.css"%>
<%--<c:import  url="/pages/css/style.css" />--%>
<%--<c:import  url="/pages/css/style-head.css" />--%>
<%--<c:import  url="/pages/css/style-body.css" />--%>
<%--<c:import  url="/pages/css/style-context.css" />--%>
    </style>
</head>
<body>

<%@ include file="block/header.jsp" %>
<%@ include file="block/context.jsp" %>
<%@ include file="block/head-table.jsp" %>
<%@ include file="block/show.jsp" %>
<%@ include file="block/add-window.jsp" %>
<%@ include file="block/edit-window.jsp" %>
</body>
<script>
    <%@include file="js/add.js"%>
    <%@include file="js/edit.js"%>
    <%@include file="js/login.js"%>
    <%@include file="js/select.js"%>
<%--<c:import url="/pages/js/add.js" />--%>

<%--<c:import url="/pages/js/edit.js" />--%>

<%--<c:import url="/pages/js/login.js" />--%>

</script>
</html>