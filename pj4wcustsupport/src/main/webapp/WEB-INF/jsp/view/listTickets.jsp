<%--@elvariable id="ticketDatabase" type="java.util.Map<java.lang.Integer, learn.ee.pj4wcustsupport.Ticket>"--%>
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
<c:choose>
    <c:when test="${fn:length(ticketDatabase) == 0}">
        <p><i>There are no tickets in the system</i></p>
    </c:when>
    <c:otherwise>
        <c:forEach var="entry" items="${ticketDatabase}">
            Ticket #${entry.key}: <a href="<c:url value="/tickets">
                <c:param name="action" value="view" />
                <c:param name="ticketId" value="${entry.key}" />
            </c:url>">
            <c:out value="${entry.value.subject}"/>
        </a> (customer: <c:out value="${entry.value.customerName}"/>)<br/>
        </c:forEach>
    </c:otherwise>
</c:choose>

</body>
</html>
