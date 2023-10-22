package com.sgriendt.game;

import com.sgriendt.board.BoardController;
import com.sgriendt.pit.Pit;

public class RandomIntType extends PlayType {

    @Override
    public void playGame(BoardController boardController) {
        final Pit pit = getRandomGeneratedValidPit(boardController);
        boardController.playMove(pit);
    }

}
