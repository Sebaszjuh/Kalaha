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
        assertEquals(board.getCurrentPlayer(), board.getPlayer1());
        assertEquals(7, board.getActivePlayerBoard().size());
        assertEquals(BoardStatus.ACTIVE, board.getGameStatus());

        LinkedList<Pit> list1 = board.getPlayer1Board();
        LinkedList<Pit> list2 = board.getPlayer2Board();
        Pit p1BigPit = list1.get(list1.size() - 1);
        Pit p2BigPit = list2.get(list2.size() - 1);

        assertTrue(p1BigPit instanceof BigPit);
        assertTrue(p2BigPit instanceof BigPit);
    }

    @Test
    void testSwitchingCurrentPlayer() {
        BoardController board = new BoardController(BoardFactory.createBoard());
        assertEquals(board.board().getPlayer1(), board.board().getCurrentPlayer());
        board.updateCurrentPlayer();
        assertEquals(board.board().getPlayer2(), board.board().getCurrentPlayer());
    }

    @Test
    void testPlayWhenGameIsFinishedIsDraw() {
        BoardController board = new BoardController(BoardFactory.createBoard());
        board.board().setGameStatus(BoardStatus.FINISHED);
        board.cleanupBoard();
        board.updateBoardStatusWithWinner();
        final int totalNumberOfStones = 6 * 6;
        assertEquals(BoardStatus.DRAW, board.board().getGameStatus());
        assertEquals(totalNumberOfStones, board.board().getPlayer1().getBigPit().getStonesInPit());
        assertEquals(totalNumberOfStones, board.board().getPlayer2().getBigPit().getStonesInPit());
    }

    @Test
    void testIsPlayer1Finished() {
        BoardController boardController = new BoardController(BoardFactory.createBoard());
        Board board = boardController.board();
        assertEquals(BoardStatus.ACTIVE, board.getGameStatus());
        boardController.updateBoardStatusIfFinished();
        assertEquals(BoardStatus.ACTIVE, board.getGameStatus());

        board.getPlayer1Board().subList(0, board.getPlayer1Board().size() - 1).forEach(x -> x.setStonesInPit(0));
        boardController.updateBoardStatusIfFinished();
        assertEquals(BoardStatus.FINISHED, board.getGameStatus());
    }

}