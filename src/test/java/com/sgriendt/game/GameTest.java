package com.sgriendt.game;

import com.sgriendt.board.Board;
import com.sgriendt.board.BoardStatus;
import com.sgriendt.pit.BigPit;
import com.sgriendt.pit.Pit;
import com.sgriendt.pit.state.EmptySmallPit;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    void randomPlayTest() {
        System.setIn(new ByteArrayInputStream("2".getBytes()));
        Game game = new Game();
        game.start();
        Board board = game.getBoardController().board();
        assertNotEquals(BoardStatus.ACTIVE, board.getGameStatus());

        Pit pit = board.getPlayer1Board().stream().filter(x -> x.getStonesInPit() > 0).findFirst().orElse(null);
        assertNotNull(pit);
        assertTrue(pit instanceof BigPit);

        Pit pit2 = board.getPlayer2Board().stream().filter(x -> x.getStonesInPit() > 0).findFirst().orElse(null);
        assertNotNull(pit2);
        assertTrue(pit2 instanceof BigPit);

        List<Pit> emptySmallPits = board.getPlayer1Board().subList(0, board.getPlayer1Board().size() - 1);
        List<Pit> emptySmallPits2 = board.getPlayer2Board().subList(0, board.getPlayer2Board().size() - 1);

        assertEquals(0, emptySmallPits.stream().mapToInt(Pit::getStonesInPit).sum());
        assertEquals(0, emptySmallPits2.stream().mapToInt(Pit::getStonesInPit).sum());

        assertTrue(emptySmallPits.stream().map(Pit::getPitState).allMatch(state -> state instanceof EmptySmallPit));
        assertTrue(emptySmallPits2.stream().map(Pit::getPitState).allMatch(state -> state instanceof EmptySmallPit));
    }

    @Test
    void callCleanUpWhileGameIsActive() {
        System.setIn(new ByteArrayInputStream("2".getBytes()));
        Game game = new Game();
        game.start();

        assertNotEquals(BoardStatus.ACTIVE, game.getBoardController().board().getGameStatus());
    }

    @Test
    void testGameType() {
        Game game = new Game();
        assertTrue(game.getPlayType(1) instanceof MultiplayerType);
        assertTrue(game.getPlayType(2) instanceof RandomIntType);
        assertTrue(game.getPlayType(3) instanceof SinglePlayer);
        assertThrows(IllegalArgumentException.class, () -> game.getPlayType(4));
    }
}
