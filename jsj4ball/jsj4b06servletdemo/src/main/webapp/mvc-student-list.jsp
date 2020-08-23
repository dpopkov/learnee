<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>MVC Students</title>
</head>
<body>

<h2>MVC Students</h2>

<table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
    </tr>
    <%--@elvariable id="student_list" type="java.util.List<learn.ee.jsj4b06servletdeom.mvc2.Student"--%>
    <c:forEach var="s" items="${student_list}">
        <tr>
            <td>${s.firstName}</td>
            <td>${s.lastName}</td>
            <td>${s.email}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
