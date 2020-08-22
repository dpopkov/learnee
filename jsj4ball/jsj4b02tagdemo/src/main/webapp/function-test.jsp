<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <title>Function Test</title>
</head>
<body>

<h2>Function Test</h2>
<c:set var="data" value="ServletsAndJSP" />
<p>Length of the string <b>${data}</b>: ${fn:length(data)}</p>
<p>Uppercase version of the string <b>${data}</b>: ${fn:toUpperCase(data)}</p>
<p>Does the string <b>${data}</b> start with <b>Servlets</b>?: ${fn:startsWith(data, 'Servlets')}</p>

</body>
</html>
