<%--@elvariable id="shortText" type="java.lang.String"--%>
<%--@elvariable id="longText" type="java.lang.String"--%>
<template:main htmlTitle="Abbreviating Text">
    <p>
        <b>Short text:</b>
        ${pj4w:abbreviateString(shortText, 32)}
    </p>
    <p>
        <b>Long text:</b>
            ${pj4w:abbreviateString(longText, 32)}
    </p>
</template:main>
