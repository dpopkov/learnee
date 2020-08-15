<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Registration Confirmation</title>
</head>
<body>

<h3>Student Registration Confirmation</h3>

<p>The student is confirmed: ${param.firstName} ${param.lastName}</p>
<p>The student's favorite languages:</p>
<ul>
<%
    String[] languages = request.getParameterValues("favoriteLanguage");
    if (languages != null && languages.length > 0) {
        for (String language : languages) {
            out.println("    <li>" + language + "</li>");
        }
    }
%>
</ul>

</body>
</html>
