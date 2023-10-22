package com.sgriendt.board;

import java.util.List;

public enum BoardStatus {
    ACTIVE,
    FINISHED,
    DRAW,
    WIN_PLAYER_ONE,
    WIN_PLAYER_TWO;

    public final static List<BoardStatus> END_STATUS = List.of(DRAW, FINISHED, WIN_PLAYER_ONE, WIN_PLAYER_TWO);

    public static boolean isGameEnd(BoardStatus status) {
        return END_STATUS.contains(status);
    }
}
