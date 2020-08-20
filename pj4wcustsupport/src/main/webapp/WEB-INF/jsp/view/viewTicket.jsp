<%--@elvariable id="ticketId" type="java.lang.String"--%>
<%--@elvariable id="ticket" type="learn.ee.pj4wcustsupport.Ticket"--%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>

<p><a href="<c:url value="login?logout" />">Logout</a></p>

<h2>Ticket #${ticketId}: <c:out value="${ticket.subject}"/></h2>
<i>Customer Name - <c:out value="${ticket.customerName}"/></i>
<br/><br/>
<c:out value="${ticket.body}"/>
<br/><br/>

<c:if test="${ticket.numberOfAttachments > 0}">
    Attachments:
    <c:forEach var="theAttachment" items="${ticket.attachments}" varStatus="itemStatus">
        <c:if test="${not itemStatus.first}">, </c:if>
        <a href="<c:url value="/tickets">
                    <c:param name="action" value="download" />
                    <c:param name="ticketId" value="${ticketId}" />
                    <c:param name="attachment" value="${theAttachment.name}" />
                </c:url>"><c:out value="${theAttachment.name}"/>
        </a>
    </c:forEach>
</c:if>
<br/><br/>
<a href="<c:url value="tickets" />">Return to list tickets</a>

</body>
</html>
