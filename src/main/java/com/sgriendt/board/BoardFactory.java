package com.sgriendt.board;

import com.sgriendt.pit.BigPit;
import com.sgriendt.pit.Pit;
import com.sgriendt.pit.SmallPit;
import com.sgriendt.player.Player;

import java.util.LinkedList;

public class BoardFactory {
    public static final int NUMBER_OF_SMALL_PITS = 6;

    /**
     * Initializes the whole board with players, pits and links it circular
     * @return initialized board ready to play
     */
    public static Board createBoard() {
        final Player p1 = new Player("Player 1");
        final Player p2 = new Player("Player 2");
        final LinkedList<Pit> pitP1Board = new LinkedList<>();
        final LinkedList<Pit> pitP2Board = new LinkedList<>();

        fillBoardWithPits(pitP1Board, p1);
        fillBoardWithPits(pitP2Board, p2);
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
        for (int i = 0; i < NUMBER_OF_SMALL_PITS; i++) {
            final Pit pit = pitP1Board.get(i);
            final Pit oppositePit = pitP2Board.get(pitP2Board.size() - 2 - i);
            pit.setOppositePit(oppositePit);
            oppositePit.setOppositePit(pit);
        }
    }

    private static void makeCircular(LinkedList<Pit> pitP1Board, LinkedList<Pit> pitP2Board) {
        pitP1Board.getLast().setNextPit(pitP2Board.getFirst());
        pitP2Board.getLast().setNextPit(pitP1Board.getFirst());
    }

    private static void fillBoardWithPits(LinkedList<Pit> list, Player player) {
        Pit pit = new SmallPit(player);
        list.addFirst(pit);
        for (int i = 0; i < NUMBER_OF_SMALL_PITS - 1; i++) {
            Pit newPit = new SmallPit(player);
            list.getLast().setNextPit(newPit);
            list.add(newPit);
        }
        final BigPit bigPit = new BigPit(player);
        list.getLast().setNextPit(bigPit);
        list.add(bigPit);
        player.setBigPit(bigPit);
    }
}
