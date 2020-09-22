<%--@elvariable id="todoItem" type="learn.spr.jstmlsf5.todolist.model.TodoItem"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="learn.spr.jstmlsf5.todolist.util.Mappings" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>View Item</title>
</head>
<body>

<div align="center">
    <table border="1">
        <tr>
            <td>ID:</td>
            <td><c:out value="${todoItem.id}"/></td>
        </tr>
        <tr>
            <td>Title:</td>
            <td><c:out value="${todoItem.title}"/></td>
        </tr>
        <tr>
            <td>Details:</td>
            <td><c:out value="${todoItem.details}"/></td>
        </tr>
        <tr>
            <td>Deadline:</td>
            <td><c:out value="${todoItem.deadline}"/></td>
        </tr>
    </table>
    <c:url var="itemsLink" value="${Mappings.ITEMS}" />
    <p><a href="${itemsLink}">Show All Todo Items</a></p>
</div>

</body>
</html>
