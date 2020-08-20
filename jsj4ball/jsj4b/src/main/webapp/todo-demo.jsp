<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>ToDo Demo</title>
</head>
<body>

<h2>ToDo Demo</h2>

<form action="todo-demo.jsp">
    <label>
        Add new item:
        <input type="text" name="theItem"/>
    </label>
    <input type="submit" value="Submit"/>
</form>
<br/>
<%
    @SuppressWarnings("unchecked")
    List<String> items = (List<String>) session.getAttribute("myToDoList");
    if (items == null) {
        items = new ArrayList<>();
        session.setAttribute("myToDoList", items);
    }
    String theItem = request.getParameter("theItem");
    if (theItem != null) {
        items.add(theItem);
    }
%>
<hr/>
<h3>ToDo List Items</h3>
<ol>
    <%
        for (String item : items) {
            out.println("<li>" + item + "</li>");
        }
    %>
</ol>

</body>
</html>
