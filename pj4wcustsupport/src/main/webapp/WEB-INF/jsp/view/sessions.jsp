<%--@elvariable id="numberOfSession" type="java.lang.Integer"--%>
<%@ page import="java.util.List" %>
<%!
    private static String formatInterval(long timeInterval) {
        if (timeInterval < 1_000) {
            return "less than one second";
        } else if (timeInterval < 60_000) {
            return (timeInterval / 1_000) + " seconds";
        } else {
            return "about " + (timeInterval / 60_000) + " minutes";
        }
    }
%>
<%
    @SuppressWarnings("unchecked")
    List<HttpSession> sessions = (List<HttpSession>) request.getAttribute("sessionList");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>

<p><a href="<c:url value="login?logout" />">Logout</a></p>

<h2>Sessions</h2>

<p>There are a total of ${numberOfSession} active sessions in this application</p>
<%
    long timestamp = System.currentTimeMillis();
    for (HttpSession hs : sessions) {
        out.print(hs.getId() + " - " + hs.getAttribute("username"));
        if (hs.getId().equals(session.getId())) {
            out.print(" (you)");
        }
        out.print(" - last active " + formatInterval(timestamp - hs.getLastAccessedTime()));
        out.println(" ago<br />");
    }
%>

</body>
</html>
