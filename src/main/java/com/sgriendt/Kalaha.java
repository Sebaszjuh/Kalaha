package com.sgriendt;

import com.sgriendt.board.BoardController;
import com.sgriendt.board.BoardFactory;

public class Kalaha {
    public static void main(String[] args) {
        final BoardController kalahaGame = new BoardController(BoardFactory.createBoard());
        kalahaGame.playMultiPlayerGame();
    }
}