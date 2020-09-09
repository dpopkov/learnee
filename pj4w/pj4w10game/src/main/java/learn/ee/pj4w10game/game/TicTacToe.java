package learn.ee.pj4w10game.game;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TicTacToe {
    private static final Map<Long, String> pendingGames = new ConcurrentHashMap<>();
    private static final Map<Long, TicTacToe> activeGames = new ConcurrentHashMap<>();
    private static long gameIdSequence = 1L;

    private final long id;
    private final String player1;
    private final String player2;
    private final Player[][] grid = new Player[3][3];
    private Player nextMove = Player.random();
    private boolean gameOver;
    private boolean gameDraw;
    private Player winner;

    public TicTacToe(long id, String player1, String player2) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
    }

    public long getId() {
        return id;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public String getNextMoveBy() {
        return nextMove == Player.PLAYER1 ? player1 : player2;
    }

    public boolean isOver() {
        return gameOver;
    }

    public boolean isDraw() {
        return gameDraw;
    }

    public Player getWinner() {
        return winner;
    }

    @JsonIgnore
    public synchronized void move(Player player, int row, int column) {
        if (player != nextMove) {
            throw new IllegalArgumentException("It is not your turn!");
        }

        if (row >= 3 || column >= 3) {
            throw new IllegalArgumentException("Row and column must be 0-2.");
        }

        if (grid[row][column] != null) {
            throw new IllegalArgumentException("Move already made at " + row + "," + column);
        }

        grid[row][column] = player;
        nextMove = nextMove == Player.PLAYER1 ? Player.PLAYER2 : Player.PLAYER1;
        winner = calculateWinner();
        if (getWinner() != null || isDraw()) {
            gameOver = true;
        }
        if (isOver()) {
            TicTacToe.activeGames.remove(this.id);
        }
    }

    public synchronized void forfeit(Player player) {
        TicTacToe.activeGames.remove(this.id);
        winner = player == Player.PLAYER1 ? Player.PLAYER2 : Player.PLAYER1;
        gameOver = true;
    }

    private Player calculateWinner() {
        boolean draw = true;
        // horizontal wins
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == null || grid[i][1] == null || grid[i][2] == null) {
                draw = false;
            }
            if (grid[i][0] != null && grid[i][0] == grid[i][1] && grid[i][0] == grid[i][2]) {
                return grid[i][0];
            }
        }
        // vertical wins
        for (int i = 0; i < 3; i++) {
            if (grid[0][i] != null && grid[0][i] == grid[1][i] && grid[0][i] == grid[2][i]) {
                return grid[0][i];
            }
        }
        // diagonal wins
        if (grid[0][0] != null && grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2]) {
            return grid[0][0];
        }
        if (grid[0][2] != null && grid[0][2] == grid[1][1] && grid[0][2] == grid[2][0]) {
            return grid[0][2];
        }
        gameDraw = draw;
        return null;
    }

    public static Map<Long, String> getPendingGames() {
        return new ConcurrentHashMap<>(TicTacToe.pendingGames);
    }

    public static long queueGame(String user) {
        long id = TicTacToe.gameIdSequence++;
        TicTacToe.pendingGames.put(id, user);
        return id;
    }

    public static void removeQueuedGame(long queuedId) {
        TicTacToe.pendingGames.remove(queuedId);
    }

    public static TicTacToe startGame(long queuedId, String user2) {
        String user1 = TicTacToe.pendingGames.remove(queuedId);
        TicTacToe game = new TicTacToe(queuedId, user1, user2);
        TicTacToe.activeGames.put(queuedId, game);
        return game;
    }

    public static TicTacToe getActiveGame(long gameId) {
        return TicTacToe.activeGames.get(gameId);
    }
}
