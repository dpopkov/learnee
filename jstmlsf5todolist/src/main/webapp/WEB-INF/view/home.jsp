<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="learn.spr.jstmlsf5.todolist.util.Mappings" %>
<html>
<head>
    <title>Todo List Application</title>
</head>
<body>

<div align="center">
    <c:url var="itemsLink" value="${Mappings.ITEMS}" />
    <h2><a href="${itemsLink}">Show Todo Items</a></h2>
</div>

</body>
</html>
