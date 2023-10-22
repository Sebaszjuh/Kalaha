package com.sgriendt.pit;

import com.sgriendt.exception.KalahaIllegalMoveException;
import com.sgriendt.player.Player;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class PitTest {

    final static Player p1 = new Player("Test player 1");
    final static Player p2 = new Player("Test player 2");

    @Test
    void testOppositePitSmallPit() {
        SmallPit smallPit = new SmallPit(p1);
        SmallPit oppositePit = new SmallPit(p2);
        oppositePit.setOppositePit(smallPit);
        smallPit.setOppositePit(oppositePit);

        assertNotNull(smallPit.getOppositePit());
        assertNotNull(oppositePit.getOppositePit());

        assertEquals(oppositePit, smallPit.getOppositePit());
        assertEquals(smallPit, oppositePit.getOppositePit());
    }

    @Test
    void testOppositePitBigPit() {
        BigPit bigPit = new BigPit(p1);
        BigPit oppositePit = new BigPit(p2);
        assertNull(bigPit.getOppositePit());
        assertNull(oppositePit.getOppositePit());
    }

    @Test
    void testSmallPitTakeStonesUpdatesStatus() {
        SmallPit smallPit = new SmallPit(p1);

        assertEquals(6, smallPit.takeStones());

        BigPit bigPit = new BigPit(p1);
        assertEquals(0, bigPit.takeStones());
    }

    @Test
    void testSow() {
        SmallPit smallPit = new SmallPit(p1);
        int defaultStoneNumber = 6;
        assertEquals(defaultStoneNumber, smallPit.getStonesInPit());
        for(int i = 1; i < 100; i++){
            smallPit.sow();
            assertEquals(defaultStoneNumber + i, smallPit.getStonesInPit());
        }
    }

    @Test
    void testSmallPitHandleLastSowEmptyBigPit() {
        SmallPit smallPit = new SmallPit(p1);
        SmallPit oppositePit = new SmallPit(p2);
        BigPit bigPit = new BigPit(p1);
        smallPit.setOppositePit(oppositePit);
        p1.setBigPit(bigPit);
        p1.setStonesInHand(1);

        oppositePit.setOppositePit(smallPit);

        int defaultStoneNumber = 6;
        assertEquals(defaultStoneNumber, smallPit.getStonesInPit());
        assertEquals(1, p1.getStonesInHand());

        assertThrows(KalahaIllegalMoveException.class, ()-> smallPit.handleLastSow(p1));
        assertEquals(defaultStoneNumber, smallPit.getStonesInPit());
        assertEquals(1, p1.getStonesInHand());
    }

    @Test
    void testSmallPitHandleLastSowFilledBigPit() {
        SmallPit smallPit = new SmallPit(p1);
        smallPit.setStonesInPit(0);
        SmallPit oppositePit = new SmallPit(p2);
        BigPit bigPit = new BigPit(p1);
        bigPit.setStonesInPit(5);
        smallPit.setOppositePit(oppositePit);
        p1.setBigPit(bigPit);
        p1.setStonesInHand(1);

        oppositePit.setOppositePit(smallPit);

        assertEquals(0, smallPit.getStonesInPit());
        assertEquals(1, p1.getStonesInHand());

        smallPit.handleLastSow(p1);
        assertEquals(12, bigPit.getStonesInPit());
        assertEquals(0, p1.getStonesInHand());
        assertEquals(0, oppositePit.getStonesInPit());

    }

    @Test
    void testBigPitHandleLastSowEmptyBigPit() {
        BigPit bigPit = new BigPit(p1);
        p1.setBigPit(bigPit);
        p1.setStonesInHand(1);
        assertEquals(1, p1.getStonesInHand());
        assertEquals(0, bigPit.getStonesInPit());

        bigPit.handleLastSow(p1);

        assertEquals(0, p1.getStonesInHand());
        assertEquals(1, bigPit.getStonesInPit());
    }

    @Test
    void testBigPitHandleLastSowFilledBigPit() {
        BigPit bigPit = new BigPit(p1);
        bigPit.setStonesInPit(5);
        p1.setBigPit(bigPit);
        p1.setStonesInHand(1);
        assertEquals(1, p1.getStonesInHand());
        assertEquals(5, bigPit.getStonesInPit());

        bigPit.handleLastSow(p1);

        assertEquals(0, p1.getStonesInHand());
        assertEquals(6, bigPit.getStonesInPit());
    }

    @Test
    void testCanSow() {
        BigPit bigPit = new BigPit(p1);
        assertTrue(bigPit.canSow(p1));
        assertFalse(bigPit.canSow(p2));

        SmallPit smallPit = new SmallPit(p1);
        assertTrue(smallPit.canSow(p1));
        assertTrue(smallPit.canSow(p2));
    }

    @Test
    void testCanTake() {
        Pit pit1 = new BigPit(p1);
        Pit pit2 = new SmallPit(p1);
        assertEquals(0, pit1.takeStones());
        assertEquals(6, pit2.takeStones());
    }


}
