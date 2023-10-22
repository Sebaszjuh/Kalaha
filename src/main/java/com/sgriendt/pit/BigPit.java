package com.sgriendt.pit;


import com.sgriendt.player.Player;

public class BigPit extends Pit {

    private static final int DEFAULT_NUMBER_BIG_PIT_STONES = 0;

    public BigPit(Player player) {
        this(DEFAULT_NUMBER_BIG_PIT_STONES, player, null);
    }

    public BigPit(int stones, Player player, PitState pitState) {
        super(stones, player, pitState);
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
        return false;
    }
}