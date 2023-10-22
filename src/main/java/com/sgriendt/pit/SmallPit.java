package com.sgriendt.pit;


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

    }

    @Override
    public boolean canTake() {
        return false;
    }

    @Override
    public boolean canSow(Player player) {
        return false;
    }

    @Override
    public boolean isSmallPitEmpty() {
        return super.getStones() <= 0;
    }
}
