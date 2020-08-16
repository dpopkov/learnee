<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<h2>Create a Ticket</h2>

<form method="post" action="tickets" enctype="multipart/form-data">
    <input type="hidden" name="action" value="create"/>
    <label>
        Your Name<br/>
        <input type="text" name="customerName"/>
    </label><br/><br/>
    <label>
        Subject<br/>
        <input type="text" name="subject"/>
    </label><br/><br/>
    <label>
        Body<br/>
        <textarea name="body" rows="5" cols="30"></textarea>
    </label><br/><br/>
    <b>Attachments</b><br/>
    <input type="file" name="file1"/><br/><br/>
    <input type="submit" value="Submit"/>
</form>

</body>
</html>
