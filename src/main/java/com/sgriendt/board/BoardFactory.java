package com.sgriendt.board;

import com.sgriendt.pit.BigPit;
import com.sgriendt.pit.Pit;
import com.sgriendt.pit.SmallPit;
import com.sgriendt.player.Player;

import java.util.LinkedList;

public class BoardFactory {

    private BoardFactory() {}

    public static final int DEFAULT_NUMBER_OF_PITS = 6;

    /**
     * Initializes the whole board with players, pits and links it circular
     * @return initialized board ready to play
     */

    public static Board createBoard() {
        return createBoard(DEFAULT_NUMBER_OF_PITS);
    }
    public static Board createBoard(int smallPits) {
        final Player p1 = new Player("Player 1");
        final Player p2 = new Player("Player 2");
        final LinkedList<Pit> pitP1Board = new LinkedList<>();
        final LinkedList<Pit> pitP2Board = new LinkedList<>();

        fillBoardWithPits(pitP1Board, p1, smallPits);
        fillBoardWithPits(pitP2Board, p2, smallPits);
        linkOppositePit(pitP1Board, pitP2Board);
        makeCircular(pitP1Board, pitP2Board);
        return new Board(pitP1Board, pitP2Board, p1, p2);
    }

    /**
     * Links opposite pits of each player
     * @param pitP1Board player 1 pits in a linkedlist
     * @param pitP2Board player 2 pits in a linkedlist
     */
    private static void linkOppositePit(LinkedList<Pit> pitP1Board, LinkedList<Pit> pitP2Board){
        for (int i = 0; i < DEFAULT_NUMBER_OF_PITS; i++) {
            final Pit pit = pitP1Board.get(i);
            final Pit oppositePit = pitP2Board.get(pitP2Board.size() - 2 - i);
            pit.setOppositePit(oppositePit);
            oppositePit.setOppositePit(pit);
        }
    }

    private static void makeCircular(LinkedList<Pit> pitP1Board, LinkedList<Pit> pitP2Board) {
        connectCircular(pitP1Board, pitP2Board);
        connectCircular(pitP2Board, pitP1Board);
    }

    private static void connectCircular(LinkedList<Pit> pitList1, LinkedList<Pit> pitList2) {
        pitList1.getLast().setNextPit(pitList2.getFirst());
    }

    private static void fillBoardWithPits(LinkedList<Pit> list, Player player, int numberOfPits) {
        Pit currentPit = new SmallPit(player);
        list.add(currentPit);
        for (int i = 0; i < numberOfPits - 1; i++) {
            Pit newPit = new SmallPit(player);
            currentPit.setNextPit(newPit);
            list.add(newPit);
            currentPit = newPit;

        }
        final BigPit bigPit = new BigPit(player);
        currentPit.setNextPit(bigPit);
        list.add(bigPit);

        player.setBigPit(bigPit);
    }
}
