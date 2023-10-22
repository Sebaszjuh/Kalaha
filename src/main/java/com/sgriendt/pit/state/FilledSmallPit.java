package com.sgriendt.pit.state;


import com.sgriendt.pit.Pit;
import com.sgriendt.player.Player;

public class FilledSmallPit implements PitState {

    @Override
    public void handleProcess(Pit pit, Player player) {
        pit.handleSimpleSow(player);
    }

    @Override
    public void nextState(Pit pit) {
        pit.setPitState(new EmptySmallPit());
    }
}
