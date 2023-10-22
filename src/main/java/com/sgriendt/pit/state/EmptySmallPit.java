package com.sgriendt.pit.state;


import com.sgriendt.pit.Pit;
import com.sgriendt.player.Player;

public class EmptySmallPit implements PitState {

    @Override
    public void handleProcess(Pit pit, Player player) {
        final int stones = player.getStonesInHand();
        if (stones == 1 && pit.getStonesInPit() == 0) {
            pit.handleLastSow(player);
            nextState(pit);
            return;
        }
        pit.handleSimpleSow(player);
        nextState(pit);
    }

    @Override
    public void nextState(Pit pit){
        pit.setPitState(new FilledSmallPit());
    }
}
