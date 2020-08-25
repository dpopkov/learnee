<%--@elvariable id="STUDENT" type="learn.ee.jsj4bstudenttracker.Student"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Student</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>FooBar University</h2>
    </div>
</div>
<div id="container">
    <div id="content">
        <h3>Update Student</h3>
        <form action="students" method="get">
            <input type="hidden" name="command" value="UPDATE"/>
            <input type="hidden" name="studentId" value="${STUDENT.id}"/>
            <table>
                <tbody>
                <tr>
                    <td><label for="firstName">First name:</label></td>
                    <td><input type="text" id="firstName" name="firstName" value="${STUDENT.firstName}"></td>
                </tr>
                <tr>
                    <td><label for="lastName">Last name:</label></td>
                    <td><input type="text" id="lastName" name="lastName" value="${STUDENT.lastName}"></td>
                </tr>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="text" id="email" name="email" value="${STUDENT.email}"></td>
                </tr>
                <tr>
                    <td><label for="submit"></label></td>
                    <td><input type="submit" id="submit" value="Update" class="save"></td>
                </tr>
                </tbody>
            </table>
        </form>
        <div style="clear: both"></div>
        <p>
            <a href="students">Back to List</a>
        </p>
    </div>
</div>

</body>
</html>
