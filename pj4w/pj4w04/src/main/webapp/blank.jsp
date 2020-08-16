<%@ page contentType="text/html;charset=UTF-8"
%><%@ page import="java.util.Date,java.util.Random"
%><!DOCTYPE html>
<html>
<head>
    <title>Hello World Application</title>
</head>
<body>
<%!
    Random rand = new Random();

    boolean commented() {
        return rand.nextBoolean();
    }
%>
<h2>Hello World Blank!</h2>

<h3>Usage of HTML and JSP comments in JSP pages</h3>

<!-- This comment was interpreted at <%= new Date() %> -->

<%
    boolean commentedOut = commented();
%>
<p>Next output is <%= commentedOut ? "commented out" : "not commented out" %></p>

<%= commentedOut ? "<!--" : "" %>This text may be a comment at random <%= commentedOut ? "-->" : "" %>

<!-- Usual HTML comment -->
<%-- JSP comment will not be seen in HTML --%>

</body>
</html>