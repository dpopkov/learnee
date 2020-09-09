package learn.ee.pj4w10game.game;

import java.util.Random;

public enum Player {
    PLAYER1,
    PLAYER2;

    private static final Random rand = new Random();

    public static Player random() {
        return rand.nextBoolean() ? PLAYER1 : PLAYER2;
    }
}
