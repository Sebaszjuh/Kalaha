package com.sgriendt.game;

import com.sgriendt.board.Board;
import com.sgriendt.board.BoardController;
import com.sgriendt.pit.Pit;

public class SinglePlayer extends PlayType {

    @Override
    public void playGame(BoardController boardController) {
        final Pit pit;
        final Board board = boardController.board();
        if (board.getCurrentPlayer().equals(board.getPlayer2())){
            pit = getRandomGeneratedValidPit(boardController);
        } else {
            pit = getPlayerPit(boardController);
        }
        boardController.playMove(pit);
    }

}
