package com.sgriendt.game;

import com.sgriendt.board.BoardController;
import com.sgriendt.exception.KalahaIllegalMoveException;
import com.sgriendt.pit.Pit;
import com.sgriendt.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;

public abstract class PlayType {

    abstract void playGame(BoardController boardController);

    public int pickRandomPitNumber() {
        return ThreadLocalRandom.current().nextInt(0, 6);
    }

    protected Pit getPlayerPit(BoardController boardController) {
        final Player currentPlayer = boardController.board().getCurrentPlayer();
        final int pickedPitNumber = getInputFromPlayer(currentPlayer);
        Pit pit = boardController.board().getActivePlayerBoard().get(pickedPitNumber);
        while (pit == null || pit.getStonesInPit() == 0) {
            System.out.printf("You picked %s this is an incorrect number", currentPlayer.getName());
            pit = boardController.board().getActivePlayerBoard().get(getInputFromPlayer(currentPlayer));
        }
        return pit;
    }

    protected int getInputFromPlayer(Player player) {
        try {
            final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            System.out.printf("%s please insert pit number between 0 and 5 to play\n", player.getName());
            String s = reader.readLine();
            if (InputValidation.validNumberInput(s) && InputValidation.isValidRange(s)){
                return Integer.parseInt(s);
            }
            System.out.println("Invalid input, try again....");
            getInputFromPlayer(player);
        } catch (IOException e) {
            throw new KalahaIllegalMoveException("Incorrect input must be number between 0 and 5");
        }
        return 0;
    }

    protected Pit getRandomGeneratedValidPit(BoardController boardController) {
        Pit pit = boardController.board().getActivePlayerBoard().get(pickRandomPitNumber());
        while (pit.getStonesInPit() == 0) {
            pit = boardController.board().getActivePlayerBoard().get(pickRandomPitNumber());
        }
        return pit;
    }
}
