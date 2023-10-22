package com.sgriendt.pit;


import com.sgriendt.pit.state.BigPitState;
import com.sgriendt.pit.state.PitState;
import com.sgriendt.player.Player;

public class BigPit extends Pit {

    private static final int DEFAULT_NUMBER_BIG_PIT_STONES = 0;

    public BigPit(Player player) {
        this(DEFAULT_NUMBER_BIG_PIT_STONES, player, new BigPitState());
    }

    public BigPit(int stones, Player player, PitState pitState) {
        super(stones, player, pitState);
    }


    @Override
    public void handleLastSow(Player currentPlayer) {
        handleSimpleSow(currentPlayer);
        currentPlayer.setExtraTurn();
    }

    @Override
    public int takeStones() {
        return 0;
    }

    @Override
    public boolean canSow(Player player) {
        return player.equals(super.getOwnerOfPit());
    }

    @Override
    public boolean isSmallPitEmpty() {
        return false;
    }
}
