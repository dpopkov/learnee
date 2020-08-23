<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>View Students</title>
</head>
<body>

<h2>View Students</h2>
<ul>
    <%--@elvariable id="student_list" type="java.lang.String[]"--%>
    <c:forEach var="tempStudent" items="${student_list}" >
        <li>${tempStudent}</li>
    </c:forEach>
</ul>
</body>
</html>
