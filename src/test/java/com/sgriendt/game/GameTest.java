package com.sgriendt.game;

import com.sgriendt.board.BoardStatus;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testGameType(){
        Game game = new Game();
        assertTrue(game.getPlayType(1) instanceof MultiplayerType);
        assertTrue(game.getPlayType(2) instanceof RandomIntType);
        assertTrue(game.getPlayType(3) instanceof SinglePlayer);
        assertThrows(IllegalArgumentException.class, ()-> game.getPlayType(4));
    }
}
