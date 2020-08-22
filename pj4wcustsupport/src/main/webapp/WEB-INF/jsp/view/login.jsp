<%--@elvariable id="loginFailed" type="java.lang.Boolean"--%>
<template:loggedOut htmlTitle="Log In" bodyTitle="Log in">
    <p>You must log in to access the customer support site.</p>
    <c:if test="${loginFailed}">
        <p><strong>The username or password you entered are not correct. Please try again</strong></p>
    </c:if>
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
</template:loggedOut>
