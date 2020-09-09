<%--@elvariable id="action" type="java.lang.String"--%>
<%--@elvariable id="gameId" type="long"--%>
<%--@elvariable id="username" type="java.lang.String"--%>
<!DOCTYPE html>
<html>
<head>
    <title>Game Site :: Tic Tac Toe</title>
    <link rel="stylesheet" href="<c:url value="/resource/stylesheet/ticTacToe.css" />" />
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/css/bootstrap.min.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>

    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
          crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>--%>
</head>
<body>
<h2>Tic Tac Toe</h2>
<span class="player-label">You:</span> ${username}<br />
<span class="player-label">Opponent:</span>
<span id="opponent"><i>Waiting</i></span>
<div id="status">&nbsp;</div>
<div id="gameContainer">
    <div class="row">
        <div id="r0c0" class="game-cell" onclick="move(0, 0);">&nbsp;</div>
        <div id="r0c1" class="game-cell" onclick="move(0, 1);">&nbsp;</div>
        <div id="r0c2" class="game-cell" onclick="move(0, 2);">&nbsp;</div>
    </div>
    <div class="row">
        <div id="r1c0" class="game-cell" onclick="move(1, 0);">&nbsp;</div>
        <div id="r1c1" class="game-cell" onclick="move(1, 1);">&nbsp;</div>
        <div id="r1c2" class="game-cell" onclick="move(1, 2);">&nbsp;</div>
    </div>
    <div class="row">
        <div id="r2c0" class="game-cell" onclick="move(2, 0);">&nbsp;</div>
        <div id="r2c1" class="game-cell" onclick="move(2, 1);">&nbsp;</div>
        <div id="r2c2" class="game-cell" onclick="move(2, 2);">&nbsp;</div>
    </div>
</div>
<div id="modalWaiting" class="modal hide fade">
    <div class="modal-header"><h3>Please Wait...</h3></div>
    <div class="modal-body" id="modalWaitingBody">&nbsp;</div>
</div>
<div id="modalError" class="modal hide fade">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;
        </button>
        <h3>Error</h3>
    </div>
    <div class="modal-body" id="modalErrorBody">A blah error occurred.
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" data-dismiss="modal">OK</button>
    </div>
</div>
<div id="modalGameOver" class="modal hide fade">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;
        </button>
        <h3>Game Over</h3>
    </div>
    <div class="modal-body" id="modalGameOverBody">&nbsp;</div>
    <div class="modal-footer">
        <button class="btn btn-primary" data-dismiss="modal">OK</button>
    </div>
</div>
<script type="text/javascript">
    let move;
    $(document).ready(function() {
        let modalError = $("#modalError");
        let modalErrorBody = $("#modalErrorBody");
        let modalWaiting = $("#modalWaiting");
        let modalWaitingBody = $("#modalWaitingBody");
        let modalGameOver = $("#modalGameOver");
        let modalGameOverBody = $("#modalGameOverBody");
        let opponent = $("#opponent");
        let status = $("#status");
        let opponentUsername;
        let username = '<c:out value="${username}" />';
        let myTurn = false;

        $('.game-cell').addClass('span1');

        if(!("WebSocket" in window))
        {
            modalErrorBody.text('WebSockets are not supported in this ' +
                'browser. Try Internet Explorer 10 or the latest ' +
                'versions of Mozilla Firefox or Google Chrome.');
            modalError.modal('show');
            return;
        }

        modalWaitingBody.text('Connecting to the server.');
        modalWaiting.modal({ keyboard: false, show: true });

        let server;
        try {
            server = new WebSocket('ws://' + window.location.host +
                '<c:url value="/ticTacToe/${gameId}/${username}">
            <c:param name="action" value="${action}" />
            </c:url>');
        } catch(error) {
            console.log("Error:", error);
            console.log(error);
            modalWaiting.modal('hide');
            modalErrorBody.text(error);
            modalError.modal('show');
            return;
        }

        server.onopen = function(event) {
            modalWaitingBody
                .text('Waiting on your opponent to join the game.');
            modalWaiting.modal({ keyboard: false, show: true });
        };

        window.onbeforeunload = function() {
            server.close();
        };

        server.onclose = function(event) {
            if(!event.wasClean || event.code !== 1000) {
                toggleTurn(false, 'Game over due to error!');
                modalWaiting.modal('hide');
                modalErrorBody.text('Code ' + event.code + ': ' +
                    event.reason);
                modalError.modal('show');
            }
        };

        server.onerror = function(event) {
            modalWaiting.modal('hide');
            modalErrorBody.text(event.data);
            modalError.modal('show');
        };

        server.onmessage = function(event) {
            let message = JSON.parse(event.data);
            if(message.action === 'gameStarted') {
                if(message.game.player1 === username)
                    opponentUsername = message.game.player2;
                else
                    opponentUsername = message.game.player1;
                opponent.text(opponentUsername);
                toggleTurn(message.game.nextMoveBy === username);
                modalWaiting.modal('hide');
            } else if(message.action === 'opponentMadeMove') {
                $('#r' + message.move.row + 'c' + message.move.column)
                    .unbind('click')
                    .removeClass('game-cell-selectable')
                    .addClass('game-cell-opponent game-cell-taken');
                toggleTurn(true);
            } else if(message.action === 'gameOver') {
                toggleTurn(false, 'Game Over!');
                if(message.winner) {
                    modalGameOverBody.text('Congratulations, you won!');
                } else {
                    modalGameOverBody.text('User "' + opponentUsername +
                        '" won the game.');
                }
                modalGameOver.modal('show');
            } else if(message.action === 'gameIsDraw') {
                toggleTurn(false, 'The game is a draw. ' +
                    'There is no winner.');
                modalGameOverBody.text('The game ended in a draw. ' +
                    'Nobody wins!');
                modalGameOver.modal('show');
            } else if(message.action === 'gameForfeited') {
                toggleTurn(false, 'Your opponent forfeited!');
                modalGameOverBody.text('User "' + opponentUsername +
                    '" forfeited the game. You win!');
                modalGameOver.modal('show');
            }
        };

        let toggleTurn = function(isMyTurn, message) {
            myTurn = isMyTurn;
            if(myTurn) {
                status.text(message || 'It\'s your move!');
                $('.game-cell:not(.game-cell-taken)')
                    .addClass('game-cell-selectable');
            } else {
                status.text(message ||'Waiting on your opponent to move.');
                $('.game-cell-selectable')
                    .removeClass('game-cell-selectable');
            }
        };

        move = function(row, column) {
            if(!myTurn) {
                modalErrorBody.text('It is not your turn yet!');
                modalError.modal('show');
                return;
            }
            if(server != null) {
                server.send(JSON.stringify({ row: row, column: column }));
                $('#r' + row + 'c' + column).unbind('click')
                    .removeClass('game-cell-selectable')
                    .addClass('game-cell-player game-cell-taken');
                toggleTurn(false);
            } else {
                modalErrorBody.text('Not connected to came server.');
                modalError.modal('show');
            }
        };
    });
</script>
</body>
</html>
