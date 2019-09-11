<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 05.09.2019
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration</title>
    <style>
        <%@include file="/css/style.css"%>
        <%@include file="/css/style-head.css"%>
        <%@include file="/css/style-register.css"%>
        <%@include file="/css/style-context.css"%>
    </style>
</head>
<body>
<div id="add-button"></div>
<%@ include file="block/header.jsp" %>
<%@ include file="block/context.jsp" %>
<%@ include file="block/add-window.jsp" %>
</body>
<script defer>
    <%@include file="/js/add.js"%>
    <%@include file="/js/register.js"%>
    <%@include file="/js/login.js"%>
</script>
</html>