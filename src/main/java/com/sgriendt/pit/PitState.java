package com.sgriendt.pit;

import com.sgriendt.player.Player;

public interface PitState {

    void handleProcess(Pit pit, Player player);

    void nextState(Pit pit);
}
