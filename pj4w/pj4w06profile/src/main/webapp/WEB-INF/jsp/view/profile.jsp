<%--@elvariable id="user" type="learn.ee.pj4w06profile.User"--%>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
</head>
<body>

<h2>User Profile</h2>

User ID: ${user.userId}<br />
Username: ${user.userName} (${user.userName.length()} characters)<br />
Full Name: ${fn:escapeXml(user.lastName) += ', ' += fn:escapeXml(user.firsName)}<br /><br />
<h4>Permissions (${fn:length(user.permissions)})</h4>
User: ${user.permissions["user"]}<br />
Moderator: ${user.permissions["moderator"]}<br />
Administrator: ${user.permissions["admin"]}<br />

</body>
</html>
