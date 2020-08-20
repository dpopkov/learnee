<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Registration Confirmation</title>
</head>
<body>

	<h3>Student Registration Confirmation</h3>

	<h4> Scriptlet form </h4>
	<p>The student is confirmed:
		<%= request.getParameter("firstName")%> <%= request.getParameter("lastName")%>
	</p>
	<h4> Alternate shortcut syntax </h4>
	<p>The student is confirmed: ${param.firstName} ${param.lastName}</p>

</body>
</html>
