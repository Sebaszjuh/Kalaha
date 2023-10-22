package com.sgriendt.board;


import com.sgriendt.game.PlayType;
import com.sgriendt.pit.Pit;
import com.sgriendt.player.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is the class that interacts with all moving pieces, it handles the logic for the board, how to move, where to move etc.
 * @param board data class that holds all data that represents the board
 */
public record BoardController(Board board) {

    public void playMove(Pit pit) {
        processPlayerMove(pit);
        updateCurrentPlayer();
    }

    public void processPlayerMove(Pit pit) {
        final Player player = board.getCurrentPlayer();
        player.setStonesInHand(pit.takeStones());
        Pit temp = pit;
        while (player.getStonesInHand() > 0) {
            temp = temp.getNextPit();
            temp.updatePitProcess(player);
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
        String TEMPLATE = """
                       Player Two
             | %02d | %02d | %02d | %02d | %02d | %02d |
        (%02d)                                 (%02d)
             | %02d | %02d | %02d | %02d | %02d | %02d |
                       Player One
        """;
        List<Pit> pits = new ArrayList<>();
        LinkedList<Pit> listP2 = new LinkedList<>(board.getPlayer2Board());
        LinkedList<Pit> listP1 = new LinkedList<>(board.getPlayer1Board());
        Pit bigPit = listP2.pollLast();
        for (int i = 0; i < board.getPlayer2Board().size()-1; i++) {
            pits.add(listP2.pollLast());
        }
        pits.add(bigPit);
        pits.add(listP1.pollLast());
        for(int i = 0; i < board.getPlayer1Board().size() -1; i++) {
            pits.add(listP1.pollFirst());
        }
        System.out.printf(TEMPLATE, pits.stream().map(Pit::getStonesInPit).toArray());
    }

    public void cleanupBoard() {
        int p1Sum = board.getPlayer1Board().stream().filter(pit -> pit.getOwnerOfPit().equals(board.getPlayer1())).mapToInt(Pit::takeStones).sum();
        int p2Sum = board.getPlayer2Board().stream().filter(pit -> pit.getOwnerOfPit().equals(board.getPlayer2())).mapToInt(Pit::takeStones).sum();
        board.getPlayer1().getBigPit().addStonesToPit(p1Sum);
        board.getPlayer2().getBigPit().addStonesToPit(p2Sum);
    }

    public void updateBoardStatusWithWinner() {
        int p1Stones = board.getPlayer1().getBigPit().getStonesInPit();
        int p2Stones = board.getPlayer2().getBigPit().getStonesInPit();
        if (p1Stones == p2Stones) {
            board.setGameStatus(BoardStatus.DRAW);
            return;
        }
        if (p1Stones > p2Stones) {
            board.setGameStatus(BoardStatus.WIN_PLAYER_ONE);
        } else {
            board.setGameStatus(BoardStatus.WIN_PLAYER_TWO);
        }
    }

    public void updateBoardStatusIfFinished() {
        boolean p1Finished = board.getPlayer1Board().stream().limit(board.getPlayer1Board().size() - 1).allMatch(Pit::isSmallPitEmpty);
        boolean p2Finished = board.getPlayer2Board().stream().limit(board.getPlayer2Board().size() - 1).allMatch(Pit::isSmallPitEmpty);
        if (p1Finished || p2Finished) {
            board.setGameStatus(BoardStatus.FINISHED);
        }
    }

    public void updateCurrentPlayer() {
        Player currentPlayer = board.getCurrentPlayer();
        if (currentPlayer.isExtraTurn()) {
            return;
        }
        board.setCurrentPlayer(currentPlayer.equals(board.getPlayer1()) ? board.getPlayer2() : board.getPlayer1());
    }
}

