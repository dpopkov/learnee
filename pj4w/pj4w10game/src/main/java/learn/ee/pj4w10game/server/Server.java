package learn.ee.pj4w10game.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.ee.pj4w10game.game.Player;
import learn.ee.pj4w10game.game.TicTacToe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/ticTacToe/{gameId}/{username}")
public class Server {
    private static final Logger log = LoggerFactory.getLogger(Server.class);

    private static final Map<Long, Game> games = new ConcurrentHashMap<>();
    private static final ObjectMapper mapper = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session, @PathParam("gameId") long gameId, @PathParam("username") String username) {
        log.debug("onOpen(), gameId={}, username={}", gameId, username);
        try {
            TicTacToe ticTacToe = TicTacToe.getActiveGame(gameId);
            if (ticTacToe != null) {
                log.debug("Closing session");
                session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION,
                        "This game has already started"));
            }
            List<String> actions = session.getRequestParameterMap().get("action");
            if (actions != null && actions.size() == 1) {
                String action = actions.get(0);
                log.debug("action = {}", action);
                if ("start".equalsIgnoreCase(action)) {
                    Game game = new Game();
                    game.gameId = gameId;
                    game.player1 = session;
                    Server.games.put(gameId, game);
                } else if ("join".equalsIgnoreCase(action)) {
                    Game game = Server.games.get(gameId);
                    game.player2 = session;
                    game.ticTacToe = TicTacToe.startGame(gameId, username);
                    sendJsonMessage(game.player1, game, new GameStartedMessage(game.ticTacToe));
                    sendJsonMessage(game.player2, game, new GameStartedMessage(game.ticTacToe));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, e.toString()));
            } catch (IOException ignore) {
            }
        }
    }

    @OnMessage
    public void onMessage(Session session, String message, @PathParam("gameId") long gameId) {
        Game game = Server.games.get(gameId);
        boolean isPlayer1 = session == game.player1;
        try {
            Move move = Server.mapper.readValue(message, Move.class);
            game.ticTacToe.move(
                    isPlayer1 ? Player.PLAYER1 : Player.PLAYER2,
                    move.getRow(), move.getColumn()
            );
            this.sendJsonMessage((isPlayer1 ? game.player2 : game.player1), game,
                    new OpponentMadeMoveMessage(move));
            if (game.ticTacToe.isOver()) {
                if (game.ticTacToe.isDraw()) {
                    this.sendJsonMessage(game.player1, game, new GameIsDrawMessage());
                    this.sendJsonMessage(game.player2, game, new GameIsDrawMessage());
                } else {
                    boolean wasPlayer1 = game.ticTacToe.getWinner() == Player.PLAYER1;
                    this.sendJsonMessage(game.player1, game, new GameOverMessage(wasPlayer1));
                    this.sendJsonMessage(game.player2, game, new GameOverMessage(!wasPlayer1));
                }
                game.player1.close();
                game.player2.close();
            }
        } catch (IOException e) {
            this.handleException(e, game);
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("gameId") long gameId) {
        Game game = Server.games.get(gameId);
        if (game == null) {
            return;
        }
        boolean isPlayer1 = session == game.player1;
        if (game.ticTacToe == null) {
            TicTacToe.removeQueuedGame(game.gameId);
        } else if (!game.ticTacToe.isOver()) {
            game.ticTacToe.forfeit(isPlayer1 ? Player.PLAYER1 : Player.PLAYER2);
            Session opponent = (isPlayer1 ? game.player2 : game.player1);
            sendJsonMessage(opponent, game, new GameForfeitedMessage());
            try {
                opponent.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendJsonMessage(Session session, Game game, Message message) {
        try {
            session.getBasicRemote().sendText(Server.mapper.writeValueAsString(message));
        } catch (IOException e) {
            this.handleException(e, game);
        }
    }

    private void handleException(Throwable t, Game game) {
        t.printStackTrace();
        String message = t.toString();
        try {
            game.player1.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, message));
        } catch (IOException ignore) {
        }
        try {
            game.player2.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, message));
        } catch (IOException ignore) {
        }
    }

    private static class Game {
        private long gameId;
        private Session player1;
        private Session player2;
        private TicTacToe ticTacToe;
    }

    /**
     * Messages sent from the browser to the server.
     */
    @SuppressWarnings("unused")
    public static class Move {
        private int row;
        private int column;

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }
    }

    /**
     * Messages sent from the server to the browser.
     */
    public static abstract class Message {
        private final String action;

        public Message(String action) {
            this.action = action;
        }

        public String getAction() {
            return action;
        }
    }

    public static class GameStartedMessage extends Message {
        private final TicTacToe game;

        public GameStartedMessage(TicTacToe game) {
            super("gameStarted");
            this.game = game;
        }

        public TicTacToe getGame() {
            return game;
        }
    }

    public static class OpponentMadeMoveMessage extends Message {
        private final Move move;

        public OpponentMadeMoveMessage(Move move) {
            super("opponentMadeMove");
            this.move = move;
        }

        public Move getMove() {
            return move;
        }
    }

    public static class GameOverMessage extends Message {
        private final boolean winner;

        public GameOverMessage(boolean winner) {
            super("gameOver");
            this.winner = winner;
        }

        public boolean isWinner() {
            return winner;
        }
    }

    public static class GameIsDrawMessage extends Message {
        public GameIsDrawMessage() {
            super("gameIsDraw");
        }
    }

    public static class GameForfeitedMessage extends Message {
        public GameForfeitedMessage() {
            super("gameForfeited");
        }
    }
}
