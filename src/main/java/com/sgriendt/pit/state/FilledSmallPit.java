package com.sgriendt.pit.state;


import com.sgriendt.exception.KalahaPitStateException;
import com.sgriendt.pit.Pit;
import com.sgriendt.player.Player;

public class FilledSmallPit implements PitState {

    @Override
    public void handleProcess(Pit pit, Player player) {
        pit.handleSimpleSow(player);
    }

    @Override
    public void nextState(Pit pit) {
        if (pit.getStonesInPit() > 0) {
            throw new KalahaPitStateException("Can't update state to empty, Pit has stones remaining");
        }
        pit.setPitState(new EmptySmallPit());
    }
}
