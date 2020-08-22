<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="theLocale" value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${theLocale}" />
<fmt:setBundle basename="mylabels" />
<!DOCTYPE html>
<html>
<head>
    <title>i18n Messages Test</title>
</head>
<body>

<a href="i18n-messages-test.jsp?theLocale=en_US">English (US)</a> |
<a href="i18n-messages-test.jsp?theLocale=es_ES">Spanish (ES)</a> |
<a href="i18n-messages-test.jsp?theLocale=de_DE">German (DE)</a>
<hr />

<h2>i18n Messages Test</h2>

<p><fmt:message key="label.greeting" /></p>
<p><fmt:message key="label.firstname" />: <i>John</i></p>
<p><fmt:message key="label.lastname" />: <i>Doe</i></p>
<p><fmt:message key="label.welcome" /></p>

<hr />
<p>Selected locale: ${theLocale}</p>

</body>
</html>
