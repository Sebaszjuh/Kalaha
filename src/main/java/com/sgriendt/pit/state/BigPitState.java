package com.sgriendt.pit.state;


import com.sgriendt.pit.Pit;
import com.sgriendt.player.Player;

public class BigPitState implements PitState {

    @Override
    public void handleProcess(Pit pit, Player player) {
        final int stones = player.getStonesInHand();
        if (stones <= 1) {
            pit.handleLastSow(player);
            return;
        }
        pit.handleSimpleSow(player);
    }

    @Override
    public void nextState(Pit pit) {
        return;
    }
}
