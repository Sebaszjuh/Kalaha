package com.sgriendt.board;

import com.sgriendt.game.MultiplayerType;
import org.junit.jupiter.api.Test;

public class BoardControllerTest {

    @Test
    void testPlayerMoves() {
        BoardController boardController = new BoardController(BoardFactory.createBoard());
        boardController.playMove(boardController.board().getActivePlayerBoard().get(0));

    }
}
