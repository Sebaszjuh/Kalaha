package com.sgriendt.game;

import com.sgriendt.board.BoardStatus;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GameTest {

    @Test
    void randomPlayTest() {
        System.setIn(new ByteArrayInputStream("2".getBytes()));
        Game game = new Game();
        game.start();

        assertNotEquals(BoardStatus.ACTIVE, game.getBoardController().board().getGameStatus());
    }

    @Test
    void callCleanUpWhileGameIsActive() {
        System.setIn(new ByteArrayInputStream("2".getBytes()));
        Game game = new Game();
        game.start();

        assertNotEquals(BoardStatus.ACTIVE, game.getBoardController().board().getGameStatus());
    }
}
