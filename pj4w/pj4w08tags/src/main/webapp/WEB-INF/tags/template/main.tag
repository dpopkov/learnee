<%@ tag body-content="scriptless" dynamic-attributes="dynamicAttributes" trimDirectiveWhitespaces="true" %>
<%@ attribute name="htmlTitle" type="java.lang.String" rtexprvalue="true" required="true" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<!DOCTYPE html>
<html <c:forEach var="a" items="${dynamicAttributes}">
    <c:out value=' ${a.key}="${fn:escapeXml(a.value)}"' escapeXml="false"/>
</c:forEach>>
    <head>
        <title><c:out value="${fn:trim(htmlTitle)}"/></title>
    </head>
    <body>
        <h2><c:out value="${fn:trim(htmlTitle)}"/></h2>
        <jsp:doBody/>
    </body>
</html>
