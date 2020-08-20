<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Personalize The Site</title>
</head>
<body>

<h2>Confirmation</h2>
<%
    String favoriteLanguage = request.getParameter("favoriteLanguage");
    Cookie theCookie = new Cookie("myApp.favoriteLanguage", favoriteLanguage);
    theCookie.setMaxAge(60 * 30);
    response.addCookie(theCookie);
%>
<p>Thanks! We set your favorite language to: ${param.favoriteLanguage} </p>

<p><a href="cookies-homepage.jsp">Return to homepage</a></p>

</body>
</html>
