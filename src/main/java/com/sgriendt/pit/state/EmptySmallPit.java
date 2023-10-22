package com.sgriendt.pit.state;


import com.sgriendt.pit.Pit;
import com.sgriendt.player.Player;

public class EmptySmallPit implements PitState {

    @Override
    public void handleProcess(Pit pit, Player player) {
        final int stones = player.getStonesInHand();
        if (isAbleToCaptureStones(stones, pit, player)) {
            pit.handleLastSow(player);
            return;
        }
        pit.handleSimpleSow(player);
        nextState(pit);
    }

    private boolean isAbleToCaptureStones(int stones, Pit pit, Player player){
        return stones == 1 && pit.getStonesInPit() == 0 && pit.getOwnerOfPit().equals(player);
    }

    @Override
    public void nextState(Pit pit){
        pit.setPitState(new FilledSmallPit());
    }
}
