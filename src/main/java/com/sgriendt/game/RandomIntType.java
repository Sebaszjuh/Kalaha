package com.sgriendt.game;

import com.sgriendt.board.BoardController;
import com.sgriendt.pit.Pit;

import java.util.concurrent.ThreadLocalRandom;

public class RandomIntType implements PlayType {

    @Override
    public void playGame(BoardController boardController) {
        final Pit pit = getValidPit(boardController);
        boardController.playMove(pit);
    }

    public Pit getValidPit(BoardController boardController) {
        Pit pit = boardController.board().getActivePlayerBoard().get(pickRandomPitNumber());
        while (pit.getStonesInPit() == 0){
            pit = boardController.board().getActivePlayerBoard().get(pickRandomPitNumber());
        }
        return pit;
    }

    public int pickRandomPitNumber() {
        return ThreadLocalRandom.current().nextInt(0, 6);
    }
}
