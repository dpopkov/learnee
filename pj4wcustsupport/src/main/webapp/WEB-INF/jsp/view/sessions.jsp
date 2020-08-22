<%--@elvariable id="numberOfSession" type="java.lang.Integer"--%>
<%--@elvariable id="sessionList" type="java.util.List<javax.servlet.http.HttpSession>"--%>
<%--@elvariable id="timestamp" type="java.lang.Long"--%>
<template:basic htmlTitle="Active Sessions" bodyTitle="Active Sessions">
    <p>There are a total of ${numberOfSession} active sessions in this application</p>

    <c:forEach var="s" items="${sessionList}">
        <c:out value="${s.id} - ${s.getAttribute('username')}"/>
        <c:if test="${s.id == pageContext.session.id}">&nbsp;(you)</c:if>
        &nbsp;- last active
        <c:out value="${pj4w:timeIntervalToString(timestamp - s.lastAccessedTime)} ago"/>
        <br/>
    </c:forEach>
</template:basic>
