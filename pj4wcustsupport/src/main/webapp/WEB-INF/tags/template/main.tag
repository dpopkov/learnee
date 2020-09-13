<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="htmlTitle" type="java.lang.String" rtexprvalue="true" required="true" %>
<%@ attribute name="bodyTitle" type="java.lang.String" rtexprvalue="true" required="true" %>
<%@ attribute name="headContent" fragment="true" required="false" %>
<%@ attribute name="navigationContent" fragment="true" required="true" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Support :: <c:out value="${fn:trim(htmlTitle)}"/></title>
    <link rel="stylesheet" href="<c:url value="/resource/stylesheet/bootstrap.min.css" />" />
    <link rel="stylesheet" href="<c:url value="/resource/stylesheet/main.css" />">
    <script src="<c:url value="/resource/js/jquery-1.9.1.js" />"></script>
    <script src="<c:url value="/resource/js/moment.min.js" />"></script>
    <script>
        const postInvisibleForm = function(url, fields) {
            const form = $('<form id="mapForm" method="post"></form>')
                .attr({ action: url, style: 'display: none;' });
            for (let key in fields) {
                if (fields.hasOwnProperty(key)) {
                    form.append($('<input type="hidden">').attr({name: key, value: fields[key]}));
                }
            }
            $('body').append(form);
            form.submit();
        }
        const newChat = function() {
            postInvisibleForm('<c:url value="/chat" />', { action: 'new'});
        };
    </script>
    <jsp:invoke fragment="headContent" />
</head>
<body>
    <h1>Multinational Widget Corporation</h1>
    <table border="0" id="bodyTable">
        <tbody>
            <tr>
                <td class="sidebarCell">
                    <jsp:invoke fragment="navigationContent" />
                </td>
                <td class="contentCell">
                    <h2><c:out value="${fn:trim(bodyTitle)}" /></h2>
                    <jsp:doBody />
                </td>
            </tr>
        </tbody>
    </table>
</body>
</html>
