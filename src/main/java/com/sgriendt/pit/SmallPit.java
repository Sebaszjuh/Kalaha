package com.sgriendt.pit;


import com.sgriendt.exception.KalahaIllegalMoveException;
import com.sgriendt.pit.state.FilledSmallPit;
import com.sgriendt.pit.state.PitState;
import com.sgriendt.player.Player;

public class SmallPit extends Pit {

    private static final int DEFAULT_NUMBER_OF_PITS = 6;

    public SmallPit(Player player) {
        this(DEFAULT_NUMBER_OF_PITS, player, new FilledSmallPit());
    }

    SmallPit(int stones, Player player, PitState state) {
        super(stones, player, state);
    }

    @Override
    public void handleLastSow(Player currentPlayer) {
        if (getStonesInPit() > 0) {
            throw new KalahaIllegalMoveException("Invalid move, pit must have 0 stones to make this move");
        }
        final Pit opponentPit = getOppositePit();
        final int opponentStones = opponentPit.takeStones();

        currentPlayer.decrementStonesInHand();

        final Pit bigPit = currentPlayer.getBigPit();
        bigPit.addStonesToPit(opponentStones + 1);
    }

    @Override
    public int takeStones() {
        int nrOfStones = getStonesInPit();
        if (nrOfStones == 0) {
            return nrOfStones;
        }
        setStonesInPit(0);
        getPitState().nextState(this);
        return nrOfStones;
    }

    @Override
    public boolean canSow(Player player) {
        return true;
    }

}
