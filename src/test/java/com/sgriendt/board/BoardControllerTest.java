package com.sgriendt.board;

import com.sgriendt.game.MultiplayerType;
import com.sgriendt.pit.Pit;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardControllerTest {

    @Test
    void testPlayerMoves() {
        BoardController boardController = new BoardController(BoardFactory.createBoard());
        Board board = boardController.board();
        boardController.playMove(board.getActivePlayerBoard().get(0));

        // After playing Pit 0, it ends up in the BigPit therefore player1 can go another turn
        assertEquals(board.getPlayer1(), board.getCurrentPlayer());
        checkPitStones(board.getPlayer1Board(), 0, 7, 7, 7, 7, 7, 1);
        checkPitStones(board.getPlayer2Board(), 6, 6, 6, 6, 6, 6, 0);

        boardController.playMove(board.getActivePlayerBoard().get(1));
        checkPitStones(board.getPlayer1Board(), 0, 0, 8, 8, 8, 8, 2);
        // After play pit 1 for player 1. It sows stones in p2 board
        checkPitStones(board.getPlayer2Board(), 7, 7, 6, 6, 6, 6, 0);
        assertEquals(board.getPlayer2(), board.getCurrentPlayer());

        boardController.playMove(board.getActivePlayerBoard().get(0));
        checkPitStones(board.getPlayer2Board(), 0, 8, 7, 7, 7, 7, 1);
        checkPitStones(board.getPlayer1Board(), 1, 0, 8, 8, 8, 8, 2);

        assertEquals(board.getPlayer1(), board.getCurrentPlayer());

        //Playing pit 0, by player1 means it captures 8 stones from the opponent which end up in the big pit
        //The way the array is visualized here is not corresponding with the path of the pits. For p2 the array is mirrored
        boardController.playMove(board.getActivePlayerBoard().get(0));
        checkPitStones(board.getPlayer1Board(), 0, 0, 8, 8, 8, 8, 10);
        checkPitStones(board.getPlayer2Board(), 0, 8, 7, 7, 0, 7, 1);

        assertEquals(board.getPlayer2(), board.getCurrentPlayer());
        boardController.playMove(board.getActivePlayerBoard().get(0));
    }

    private void checkPitStones(LinkedList<Pit> list, int... nums) {
        for(int i = 0;i < list.size();i++) {
            assertEquals(nums[i], list.get(i).getStonesInPit());
        }
    }
}
