<%--@elvariable id="ticketDatabase" type="java.util.Map<java.lang.Integer, learn.ee.pj4wcustsupport.Ticket>"--%>
<template:basic htmlTitle="Tickets" bodyTitle="Tickets">
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
                <c:out value="${pj4w:abbreviateString(entry.value.subject, 60)}"/>
            </a> (customer: <c:out value="${entry.value.customerName}"/> created ticket
                <pj4w:formatDate value="${entry.value.dateCreated}" type="both" timeStyle="short" dateStyle="medium"/>)
                <br/>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</template:basic>
