package com.sgriendt.board;

import com.sgriendt.pit.BigPit;
import com.sgriendt.pit.Pit;
import org.junit.jupiter.api.Test;


import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardTest {

    @Test
    void testCreatingBoardWith6Small1BigPerPlayer() {
        Board board = BoardFactory.createBoard();
        assertEquals(board.getCurrentPlayer(), board.getP1());
        assertEquals(7, board.getActivePlayerBoard().size());
        assertEquals(BoardStatus.ACTIVE, board.getGameStatus());

        LinkedList<Pit> list1 = board.getP1Board();
        LinkedList<Pit> list2 = board.getP2Board();
        Pit p1BigPit = list1.get(list1.size() - 1);
        Pit p2BigPit = list2.get(list2.size() - 1);

        assertTrue(p1BigPit instanceof BigPit);
        assertTrue(p2BigPit instanceof BigPit);
    }

    @Test
    void testSwitchingCurrentPlayer() {
        BoardController board = new BoardController(BoardFactory.createBoard());
        assertEquals(board.board().getP1(), board.board().getCurrentPlayer());
        board.updateCurrentPlayer();
        assertEquals(board.board().getP2(), board.board().getCurrentPlayer());
    }

    @Test
    void testPlayWhenGameIsFinishedIsDraw() {
        BoardController board = new BoardController(BoardFactory.createBoard());
        board.board().setGameStatus(BoardStatus.FINISHED);
        board.playGame();
        final int totalNumberOfStones = 6 * 6;
        assertEquals(BoardStatus.DRAW, board.board().getGameStatus());
        assertEquals(totalNumberOfStones, board.board().getP1().getBigPit().getStonesInPit());
        assertEquals(totalNumberOfStones, board.board().getP2().getBigPit().getStonesInPit());
    }
}