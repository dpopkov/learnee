<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Personalize The Site</title>
</head>
<body>

<h2>Training Portal</h2>
<%
    Cookie[] cookies = request.getCookies();
    String favLanguage = "unknown";
    if (cookies != null) {
        for (Cookie c : cookies) {
            if (c.getName().equals("myApp.favoriteLanguage")) {
                favLanguage = c.getValue();
                break;
            }
        }
    }
%>
<h3>New Books for <%= favLanguage%> </h3>
<ul>
    <li>book 1</li>
    <li>book 2</li>
</ul>
<h3>New News for <%= favLanguage%> </h3>
<ul>
    <li>news 1</li>
    <li>news 2</li>
</ul>
<h3>New Jobs for <%= favLanguage%> </h3>
<ul>
    <li>job 1</li>
    <li>job 2</li>
</ul>
<hr />
<p><a href="cookies-personalize-form.html">Personalize this page</a></p>

</body>
</html>
