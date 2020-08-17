<%--suppress unchecked --%>
<%@ page import="learn.ee.pj4wcustsupport.Ticket" %>
<%@ page import="java.util.Map" %>
<%
    Map<Integer, Ticket> ticketDatabase = (Map<Integer, Ticket>) request.getAttribute("ticketDatabase");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>

<p><a href="<c:url value="login?logout" />">Logout</a></p>

<h2>Tickets</h2>

<p>
    <a href="<c:url value="tickets"><c:param name="action" value="create"/></c:url>">Create Ticket</a>
</p>
<%
    if (ticketDatabase.isEmpty()) {
%>
        <p><i>There are no tickets in the system</i></p>
<%
    } else {
        for (Integer id : ticketDatabase.keySet()) {
            String idString = id.toString();
            Ticket ticket = ticketDatabase.get(id);
%>
        Ticket #<%= idString %>: <a href="<c:url value="/tickets">
                <c:param name="action" value="view" />
                <c:param name="ticketId" value="<%= idString %>" />
            </c:url>"><%= ticket.getSubject() %>
        </a>(customer: <%= ticket.getCustomerName() %>)
        <br />
<%
        }
    }
%>
</body>
</html>
