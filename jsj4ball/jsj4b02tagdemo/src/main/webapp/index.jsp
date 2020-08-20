<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>

<h2>JSTL test</h2>

<c:set var="stuff" value="<%= new java.util.Date() %>" />

Time on the server is ${stuff}

</body>
</html>
