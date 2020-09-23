<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="learn.spr.jstmlsf5.todolist.util.AttributeNames " %>
<html>
<head>
    <title>Add Item</title>
</head>
<body>

<div align="center">
    <%--@elvariable id="${AttributeNames.TODO_ITEM}" type="learn.spr.jstmlsf5.todolist.model.TodoItem"--%>
    <form:form method="post" modelAttribute="${AttributeNames.TODO_ITEM}">
        <table>
            <tr>
                <td><label>ID</label></td>
                <td><form:input path="id" disabled="true"/></td>
            </tr>
            <tr>
                <td><label>Title</label></td>
                <td><form:input path="title"/></td>
            </tr>
            <tr>
                <td><label>Deadline</label></td>
                <td><form:input path="deadline"/></td>
            </tr>
            <tr>
                <td><label>Details</label></td>
                <td><form:textarea path="details"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Submit"></td>
            </tr>
        </table>
    </form:form>
</div>

</body>
</html>
