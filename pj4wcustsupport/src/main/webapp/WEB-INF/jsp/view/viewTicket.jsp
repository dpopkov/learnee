<%--@elvariable id="ticketId" type="java.lang.String"--%>
<%--@elvariable id="ticket" type="learn.ee.pj4wcustsupport.Ticket"--%>
<template:basic htmlTitle="${ticket.subject}" bodyTitle="Ticket #${ticketId}: ${ticket.subject}">
    <i>Customer Name - <c:out value="${ticket.customerName}"/><br/>
        Created <pj4w:formatDate value="${ticket.dateCreated}" type="both" timeStyle="long" dateStyle="full"/></i>
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
</template:basic>
