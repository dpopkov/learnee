<%--@elvariable id="pendingGames" type="java.util.Map<long, java.Lang.String>"--%>
<!DOCTYPE html>
<html>
<head>
    <title>Game Site :: Tic Tac Toe</title>
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <%--<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>--%>
</head>
<body>
<h2>Tic Tac Toe</h2>
<a href="javascript:void 0;" onclick="startGame();">Start a Game</a><br/>
<br/>
<c:choose>
    <c:when test="${fn:length(pendingGames) == 0}">
        <i>There are no existing games to join.</i>
    </c:when>
    <c:otherwise>
        Join a game waiting for a second player:<br />
        <c:forEach items="${pendingGames}" var="e">
            <a href="javascript:void 0;" onclick="joinGame(${e.key});">User: ${e.value}</a><br/>
        </c:forEach>
    </c:otherwise>
</c:choose>

<script type="text/javascript">
    let startGame, joinGame;
    $(document).ready(function () {
        let url = '<c:url value="/ticTacToe" />';

        startGame = function () {
            let username = prompt('Enter a username to start a game.', '');
            if (username != null && username.trim().length > 0 && validateUsername(username)) {
                post({action: 'start', username: username});
            }
        };

        joinGame = function (gameId) {
            let username = prompt('Enter a username to join this game.', '');
            if (username != null && username.trim().length > 0 && validateUsername(username)) {
                post({action: 'join', username: username, gameId: gameId});
            }
        };

        let validateUsername = function (username) {
            let valid = username.match(/^[a-zA-Z0-9_]+$/) != null;
            if (!valid) {
                alert('User names can only contain letters, numbers and underscores.');
            }
            return valid;
        };

        let post = function (fields) {
            let form = $('<form id="mapForm" method="post"></form>')
                .attr({action: url, style: 'display: none;'});
            for (let key in fields) {
                if (fields.hasOwnProperty(key))
                    form.append($('<input type="hidden">').attr({
                        name: key, value: fields[key]
                    }));
            }
            $('body').append(form);
            form.submit();
        };
    });
</script>
</body>
</html>
