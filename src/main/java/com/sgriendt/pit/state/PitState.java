package com.sgriendt.pit.state;

import com.sgriendt.pit.Pit;
import com.sgriendt.player.Player;

public interface PitState {

    void handleProcess(Pit pit, Player player);

    void nextState(Pit pit);
}
