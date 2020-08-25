<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Student</title>
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
        <h3>Add Student</h3>
        <form action="students" method="get">
            <input type="hidden" name="command" value="add"/>
            <table>
                <tbody>
                <tr>
                    <td><label for="firstName">First name:</label></td>
                    <td><input type="text" id="firstName" name="firstName"></td>
                </tr>
                <tr>
                    <td><label for="lastName">Last name:</label></td>
                    <td><input type="text" id="lastName" name="lastName"></td>
                </tr>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="text" id="email" name="email"></td>
                </tr>
                <tr>
                    <td><label for="submit"></label></td>
                    <td><input type="submit" id="submit" value="Save" class="save"></td>
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
