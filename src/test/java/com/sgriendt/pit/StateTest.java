package com.sgriendt.pit;

import com.sgriendt.exception.KalahaPitStateException;
import com.sgriendt.pit.state.BigPitState;
import com.sgriendt.pit.state.EmptySmallPit;
import com.sgriendt.pit.state.FilledSmallPit;
import com.sgriendt.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StateTest {

    Player p1 = new Player("Test player 1");
    Player p2 = new Player("Test player 2");

    @Test
    void testEmptySmallPit() {
        Pit smallPit = setUpSmallPit(0, 2);
        assertThrows(IllegalStateException.class, () -> smallPit.updatePitProcess(p1));
        p1.setStonesInHand(6);
        smallPit.updatePitProcess(p1);

        assertEquals(5, p1.getStonesInHand());
        assertEquals(1, smallPit.getStonesInPit());
        assertEquals(2, smallPit.getOppositePit().getStonesInPit());
        assertTrue(smallPit.getPitState() instanceof FilledSmallPit);
    }

    @Test
    void testEmptySmallPitLastStone() {
        Pit smallPit = setUpSmallPit(0, 2);
        p1.setStonesInHand(1);
        smallPit.updatePitProcess(p1);

        assertEquals(0, p1.getStonesInHand());
        assertEquals(0, smallPit.getStonesInPit());
        assertEquals(0, smallPit.getOppositePit().getStonesInPit());
        assertEquals(3, p1.getBigPit().getStonesInPit());
        assertTrue(smallPit.getPitState() instanceof EmptySmallPit);
    }

    @Test
    void testFilledSmallPitLastStone() {
        Pit smallPit = setUpSmallPit(2, 2);
        p1.setStonesInHand(1);
        smallPit.updatePitProcess(p1);

        assertEquals(0, p1.getStonesInHand());
        assertEquals(3, smallPit.getStonesInPit());
        assertEquals(2, smallPit.getOppositePit().getStonesInPit());
        assertEquals(0, p1.getBigPit().getStonesInPit());
        assertTrue(smallPit.getPitState() instanceof FilledSmallPit);
    }

    @Test
    void testFilledSmallPit() {
        Pit smallPit = setUpSmallPit(0, 2);
        p1.setStonesInHand(5);
        smallPit.updatePitProcess(p1);

        assertEquals(4, p1.getStonesInHand());
        assertEquals(1, smallPit.getStonesInPit());
        assertEquals(2, smallPit.getOppositePit().getStonesInPit());
        assertEquals(0, p1.getBigPit().getStonesInPit());
        assertTrue(smallPit.getPitState() instanceof FilledSmallPit);
    }

    @Test
    void testBigPitOwner() {
        Pit bigPit = new BigPit(p1);
        p1.setStonesInHand(5);
        bigPit.updatePitProcess(p1);

        assertEquals(4, p1.getStonesInHand());
        assertEquals(1, bigPit.getStonesInPit());

        assertTrue(bigPit.getPitState() instanceof BigPitState);
    }

    @Test
    void testBigPitLastStoneOwner() {
        Pit bigPit = new BigPit(p1);
        p1.setStonesInHand(1);
        bigPit.updatePitProcess(p1);

        assertEquals(0, p1.getStonesInHand());
        assertEquals(1, bigPit.getStonesInPit());
        assertTrue(p1.isExtraTurn());
        assertTrue(bigPit.getPitState() instanceof BigPitState);
    }

    @Test
    void testBigPitLastStoneNotOwner() {
        Pit bigPit = new BigPit(p2);
        p1.setStonesInHand(1);
        bigPit.updatePitProcess(p1);

        assertEquals(1, p1.getStonesInHand());
        assertEquals(0, bigPit.getStonesInPit());
        assertFalse(p1.isExtraTurn());
        assertTrue(bigPit.getPitState() instanceof BigPitState);
    }

    @Test
    void testNextState() {
        Pit smallPit = new SmallPit(p1);
        assertThrows(KalahaPitStateException.class, () -> smallPit.getPitState().nextState(smallPit));
        smallPit.setStonesInPit(0);
        smallPit.getPitState().nextState(smallPit);
        assertTrue(smallPit.getPitState() instanceof EmptySmallPit);

        Pit emptySmallPit = new SmallPit(0, p1, new EmptySmallPit());
        assertThrows(KalahaPitStateException.class, () -> emptySmallPit.getPitState().nextState(emptySmallPit));
        emptySmallPit.setStonesInPit(1);
        emptySmallPit.getPitState().nextState(emptySmallPit);
        assertTrue(emptySmallPit.getPitState() instanceof FilledSmallPit);
    }

    public Pit setUpSmallPit(int pitStones, int oppositePitStones) {
        p1.setBigPit(new BigPit(p1));
        SmallPit pit = new SmallPit(pitStones, p1, pitStones > 0 ? new FilledSmallPit() : new EmptySmallPit());

        SmallPit oppositePit = new SmallPit(oppositePitStones, p2, oppositePitStones > 0 ? new FilledSmallPit() : new EmptySmallPit());
        pit.setOppositePit(oppositePit);
        oppositePit.setOppositePit(pit);
        return pit;
    }
}
