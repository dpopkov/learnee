<%--@elvariable id="x" type="learn.ee.pj4w06profile.User"--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="learn.ee.pj4w06profile.User" %><%
    ArrayList<User> users = new ArrayList<>();
    users.add(new User(19384L, "Coder314", "John", "Smith"));
    users.add(new User(19383L, "geek12", "Joe", "Smith"));
    users.add(new User(19382L, "jack123", "Jack", "Johnson"));
    users.add(new User(19385L, "farmer-dude", "Adam", "Fisher"));
    request.setAttribute("users", users);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Collections and Streams</title>
</head>
<body>

<h2>Collections and Streams</h2>

${users.stream()
    .filter(u -> fn:contains(u.userName, '1'))
    .sorted((u1, u2) -> (x = u1.lastName.compareTo(u2.lastName); x == 0 ? u1.firstName.compareTo(u2.firstName) : x))
    .map(u -> {'username' : u.userName, 'first' : u.firstName, 'last' : u.lastName})
    .toList()}

</body>
</html>
