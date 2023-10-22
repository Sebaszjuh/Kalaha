package com.sgriendt.board;


import com.sgriendt.exception.KalahaIllegalMoveException;
import com.sgriendt.pit.Pit;
import com.sgriendt.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is the class that interacts with all moving pieces, it handles the logic for the board, how to move, where to move etc.
 * @param board data class that holds all data that represents the board
 */
public record BoardController(Board board) {

    public void playGame() {
        while (!BoardStatus.isGameEnd(board.getGameStatus())) {
            final Pit pit = pickPit(pickRandomPitNumber());
            processPlayerMove(pit);
            updateCurrentPlayer();
        }
        cleanupBoard();
        updateBoardStatusWithWinner();
        printBoard();
        printWinner();
    }

    public void playMultiPlayerGame() {
        while (!BoardStatus.isGameEnd(board.getGameStatus())) {
            final Pit pit = pickPit(getInputFromPlayer(board.getCurrentPlayer()));
            processPlayerMove(pit);
            updateCurrentPlayer();
        }
        cleanupBoard();
        updateBoardStatusWithWinner();
        printBoard();
        printWinner();
    }

    private int getInputFromPlayer(Player player) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            System.out.println(String.format("%s please insert pit number between 0 and 6 to play\n", player.getName()));
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            throw new KalahaIllegalMoveException("Incorrect input must be number between 0 and 6");
        }
    }

    public void processPlayerMove(Pit pit) {
        final Player player = board.getCurrentPlayer();
        player.setStonesInHand(pit.takeStones());
        Pit temp = pit;
        while (player.getStonesInHand() > 0) {
            temp = temp.getNext();
            temp.processMove(player);
        }
        printBoard();
        updateBoardStatusIfFinished();
    }

    public void printWinner() {
        System.out.println("-----------------------");
        System.out.printf("The winner is.... %s\n", board.getGameStatus());
        System.out.println("-----------------------");
    }

    public void printBoard() {

    }

    public Pit pickPit(int randomNumber) {
        Pit pit = board.getActivePlayerBoard().get(randomNumber);
        if (pit.getStones() <= 0) {
            pickPit(pickRandomPitNumber());
        }
        return pit;
    }

    public int pickRandomPitNumber() {
        return ThreadLocalRandom.current().nextInt(0, 6);
    }

    public void cleanupBoard() {

    }

    public void updateBoardStatusWithWinner() {

    }

    public void updateBoardStatusIfFinished() {

    }

    public void updateCurrentPlayer() {
        Player currentPlayer = board.getCurrentPlayer();
        if (currentPlayer.isExtraTurn()) {
            return;
        }
        board.setCurrentPlayer(currentPlayer.equals(board.getP1()) ? board.getP2() : board.getP1());
    }
}

