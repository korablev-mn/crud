<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 06.09.2019
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request"></c:set>
<html>
<head>
    <title>Welcome</title>
    <style>
        <%@include file="/css/style.css"%>
        <%@include file="/css/style-head.css"%>
        <%@include file="/css/style-context.css"%>
    </style>
</head>
<body>
    <%@ include file="block/header.jsp" %>
    <%@ include file="block/context.jsp" %>
</body>
<script>
    <%@include file="/js/login.js"%>
</script>
</html>