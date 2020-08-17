<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="learn.ee.pj4w.PageVisit" %>
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
    final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Session Activity Tracker</title>
</head>
<body>

<h2>Session Properties</h2>

Session ID: <%= session.getId() %><br/>
Session is new: <%= session.isNew() %><br/>
Session created: <%= dateFormat.format(new Date(session.getCreationTime()))%><br/>

<h2>Page Activity This Session</h2>
<%
    @SuppressWarnings("unchecked")
    List<PageVisit> visits = (List<PageVisit>) session.getAttribute("activity");
    for (PageVisit visit : visits) {
        out.print(visit.getRequest());
        if (visit.getIpAddress() != null) {
            out.print(" from ID " + visit.getIpAddress().getHostAddress());
        }
        out.print(" (" + dateFormat.format(new Date(visit.getEnteredTimestamp())));
        if (visit.getLeftTimestamp() != null) {
            out.print(", stayed for " + formatInterval(visit.getLeftTimestamp() - visit.getEnteredTimestamp()));
        }
        out.println(")<br />");
    }
%>

</body>
</html>
