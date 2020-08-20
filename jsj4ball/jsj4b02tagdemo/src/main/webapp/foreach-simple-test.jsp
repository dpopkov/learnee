<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String[] cities = {"Mumbai", "Singapore", "Philadelphia"};
    pageContext.setAttribute("myCities", cities);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Foreach Simple Test</title>
</head>
<body>

<h2>Foreach Simple Test</h2>

<ul>
<c:forEach var="tempCity" items="${myCities}" >
    <li>${tempCity}</li>
</c:forEach>
</ul>

</body>
</html>
