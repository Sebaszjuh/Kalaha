package com.sgriendt.board;

import com.sgriendt.pit.Pit;
import com.sgriendt.player.Player;

import java.util.LinkedList;

/**
 * Board is a data entity that holds all variables to play the game, the logic to use the board will be in the board controller
 */
public class Board {

    private final LinkedList<Pit> p2Board;
    private final LinkedList<Pit> p1Board;
    private final Player p1;
    private final Player p2;
    private Player currentPlayer;
    private BoardStatus gameStatus;

    Board(LinkedList<Pit> boardP1, LinkedList<Pit> boardP2, Player p1, Player p2) {
        this.p1Board = boardP1;
        this.p2Board = boardP2;
        this.p1 = p1;
        this.p2 = p2;
        this.currentPlayer = p1;
        this.gameStatus = BoardStatus.ACTIVE;
    }

    public LinkedList<Pit> getActivePlayerBoard() {
        return currentPlayer.equals(p1) ? p1Board : p2Board;
    }

    public LinkedList<Pit> getPlayer2Board() {
        return p2Board;
    }

    public LinkedList<Pit> getPlayer1Board() {
        return p1Board;
    }

    public Player getPlayer1() {
        return p1;
    }

    public Player getPlayer2() {
        return p2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public BoardStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(BoardStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}

