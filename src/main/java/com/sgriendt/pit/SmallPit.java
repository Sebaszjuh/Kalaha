package com.sgriendt.pit;


import com.sgriendt.exception.KalahaIllegalMoveException;
import com.sgriendt.pit.state.PitState;
import com.sgriendt.player.Player;

public class SmallPit extends Pit {

    private static final int NUMBER_OF_STONES = 6;

    public SmallPit(Player player) {
        this(NUMBER_OF_STONES, player, null);
    }

    SmallPit(int stones, Player player, PitState state) {
        super(stones, player, state);
    }

    @Override
    public void handleLastSow(Player currentPlayer) {
        if (getStones() > 0) {
            throw new KalahaIllegalMoveException("Invalid move, pit must have 0 stones to make this move");
        }
        final Pit opponentPit = getOppositePit();
        final int opponentStones = opponentPit.takeStones();

        currentPlayer.decrementStonesInHand();

        final Pit bigPit = currentPlayer.getBigPit();
        bigPit.addStonesToPit(opponentStones + 1);

        opponentPit.getPitState().nextState(opponentPit);
    }

    @Override
    public boolean canTake() {
        return true;
    }

    @Override
    public boolean canSow(Player player) {
        return true;
    }

    @Override
    public boolean isSmallPitEmpty() {
        return super.getStones() <= 0;
    }
}
