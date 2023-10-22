package com.sgriendt.player;

import com.sgriendt.pit.BigPit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PlayerTest {

    @Test
    void testStonesInHand() {
        Player player = new Player("Test player 1");
        assertEquals(0, player.getStonesInHand());

        player.setStonesInHand(5);
        assertEquals(5, player.getStonesInHand());

        for (int i = 0; i < 5; i++) {
            player.decrementStonesInHand();
            assertEquals(5 - i - 1, player.getStonesInHand());
        }

        assertThrows(IllegalStateException.class, player::decrementStonesInHand);
    }

    @Test
    void testPlayerEquals() {
        Player player = new Player("Test player 1");
        Player player1 = new Player("Test player 1");
        Player player2 = player;
        BigPit bigPit = new BigPit(player);

        assertTrue(player.equals(player));
        assertTrue(player.equals(player2));
        assertFalse(player.equals(player1));
        assertFalse(player.equals(bigPit));

    }
}