<%--@elvariable id="date" type="java.util.Date"--%>
<%--@elvariable id="calendar" type="java.util.Calendar"--%>
<%--@elvariable id="instant" type="java.time.Instant"--%>
<template:main htmlTitle="Displaying Dates Properly">
    <p>
        <b>Date:</b>
        <pj4w:formatDate value="${date}" type="both" dateStyle="full" timeStyle="full"/>
    </p>
    <p>
        <b>Date, time first with separator:</b>
        <pj4w:formatDate value="${date}" type="both" dateStyle="full" timeStyle="full"
                         timeFirst="true" separateDateTimeWith=" on "/>
    </p>
    <p>
        <b>Calendar:</b>
        <pj4w:formatDate value="${calendar}" type="both" dateStyle="full" timeStyle="full"/>
    </p>
    <p>
        <b>Instant:</b>
        <pj4w:formatDate value="${instant}" type="both" dateStyle="full" timeStyle="full"/>
    </p>
    <p>
        <b>Instant, time first with separator:</b>
        <pj4w:formatDate value="${instant}" type="both" dateStyle="full" timeStyle="full"
                         timeFirst="true" separateDateTimeWith=" on "/>
    </p>
</template:main>
