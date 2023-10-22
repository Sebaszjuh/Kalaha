package com.sgriendt.game;

import com.sgriendt.board.BoardController;
import com.sgriendt.pit.Pit;

public class MultiplayerType extends PlayType {

    @Override
    public void playGame(BoardController boardController) {
        final Pit pit = getPlayerPit(boardController);
        boardController.playMove(pit);
    }
}
