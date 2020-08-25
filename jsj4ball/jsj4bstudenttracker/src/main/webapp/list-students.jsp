<%--@elvariable id="studentsList" type="java.util.List<learn.ee.jsj4bstudenttracker.Student>"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Students</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>FooBar University</h2>
    </div>
</div>
<div id="container">
    <div id="content">
        <input type="button" value="Add Student"
               onclick="window.location.href='add-student-form.jsp'; return false"
               class="add-student-button"
        />
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
            </tr>
            <c:forEach var="st" items="${studentsList}">
                <tr>
                    <td>${st.firstName}</td>
                    <td>${st.lastName}</td>
                    <td>${st.email}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
