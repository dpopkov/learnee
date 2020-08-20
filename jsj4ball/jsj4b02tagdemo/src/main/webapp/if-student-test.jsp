<%--suppress HtmlDeprecatedAttribute --%>
<%@ page import="java.util.List" %>
<%@ page import="learn.ee.jsj4b.tagdemo.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    List<Student> data = new ArrayList<>();
    data.add(new Student("John", "Doe", false));
    data.add(new Student("Maxwell", "Johnson", false));
    data.add(new Student("Mary", "Public", true));
    pageContext.setAttribute("myStudents", data);
%>
<!DOCTYPE html>
<html>
<head>
    <title>If Student Test</title>
</head>
<body>

<h2>If Student Test</h2>

<table border="1">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Gold Customer</th>
    </tr>
    <c:forEach var="tempStudent" items="${myStudents}">
        <tr>
            <td>${tempStudent.firstName}</td>
            <td>${tempStudent.lastName}</td>
            <td>
                <c:if test="${tempStudent.goldCustomer}">
                    Special Discount
                </c:if>
                <c:if test="${not tempStudent.goldCustomer}">
                    -
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
