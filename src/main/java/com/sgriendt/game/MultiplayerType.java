package com.sgriendt.game;

import com.sgriendt.board.BoardController;
import com.sgriendt.exception.KalahaIllegalMoveException;
import com.sgriendt.pit.Pit;
import com.sgriendt.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MultiplayerType implements PlayType {

    @Override
    public void playGame(BoardController boardController) {
        final Pit pit = getPit(boardController);
        boardController.playMove(pit);
    }

    private Pit getPit(BoardController boardController) {
        final Player currentPlayer = boardController.board().getCurrentPlayer();
        final int pickedPitNumber = getInputFromPlayer(currentPlayer);
        final Pit pit = boardController.board().getActivePlayerBoard().get(pickedPitNumber);
        if (pit == null || pit.getStonesInPit() == 0) {
            System.out.printf("You picked %s this is an incorrect number", currentPlayer);
            getInputFromPlayer(currentPlayer);
        }
        return pit;
    }

    private int getInputFromPlayer(Player player) {
        try {
            final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            System.out.printf("%s please insert pit number between 0 and 5 to play\n", player.getName());
            String s = reader.readLine();
            if (InputValidation.validNumberInput(s) && InputValidation.isValidRange(s)){
                return Integer.parseInt(s);
            }
            getInputFromPlayer(player);
        } catch (IOException e) {
            throw new KalahaIllegalMoveException("Incorrect input must be number between 0 and 6");
        }
        return 0;
    }
}
