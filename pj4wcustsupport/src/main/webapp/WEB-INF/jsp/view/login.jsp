<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>

<h2>Login</h2>

<p>You must log in to access the customer support site.</p>
<%
    if ((Boolean) request.getAttribute("loginFailed")) {
%>
<p><strong>The username or password you entered are not correct. Please try again</strong></p>
<%
    }
%>
<form method="post" action="<c:url value="/login" />">
    <label>
        Username<br/>
        <input type="text" name="username"/>
    </label><br/><br/>
    <label>
        Password<br/>
        <input type="password" name="password"/>
    </label><br/><br/>
    <input type="submit" value="Log in"/>
</form>

</body>
</html>
