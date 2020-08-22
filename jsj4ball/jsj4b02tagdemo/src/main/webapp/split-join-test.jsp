<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <title>Split Join Test</title>
</head>
<body>

<h2>Split Join Test</h2>
<c:set var="data" value="London,Tokio,Mumbai,Singapure" />

<h3>Split Demo</h3>
<c:set var="citiesArray" value="${fn:split(data, ',')}"/>
<ul>
    <c:forEach var="city" items="${citiesArray}">
        <li>${city}</li>
    </c:forEach>
</ul>

<h3>Join Demo</h3>
<c:set var="citiesJoined" value="${fn:join(citiesArray, '*')}"/>
<p>Result fo joining: ${citiesJoined}</p>

</body>
</html>
