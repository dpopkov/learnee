<template:basic htmlTitle="Create a Ticket" bodyTitle="Create a Ticket" >
    <form method="post" action="tickets" enctype="multipart/form-data">
        <input type="hidden" name="action" value="create"/>
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
</template:basic>
