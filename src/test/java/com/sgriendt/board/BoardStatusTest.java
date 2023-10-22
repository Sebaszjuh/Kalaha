package com.sgriendt.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardStatusTest {

    @Test
    void isGameEnd() {
        assertTrue(BoardStatus.isGameEnd(BoardStatus.DRAW));
        assertTrue(BoardStatus.isGameEnd(BoardStatus.WIN_PLAYER_TWO));
        assertTrue(BoardStatus.isGameEnd(BoardStatus.WIN_PLAYER_TWO));
        assertTrue(BoardStatus.isGameEnd(BoardStatus.FINISHED));
        assertFalse(BoardStatus.isGameEnd(BoardStatus.ACTIVE));
    }
}