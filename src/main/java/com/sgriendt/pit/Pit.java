package com.sgriendt.pit;


import com.sgriendt.pit.state.PitState;
import com.sgriendt.player.Player;

public abstract class Pit {

    private int stonesInPit;
    private PitState pitState;
    private Player ownerOfPit;
    private Pit nextPit;
    private Pit oppositePit;

    Pit (int stones, Player ownerOfPit, PitState pitState) {
        this.stonesInPit = stones;
        this.ownerOfPit = ownerOfPit;
        this.pitState = pitState;
    }

    public abstract boolean canSow(Player player);
    public abstract void handleLastSow(Player currentPlayer);

    public abstract boolean isSmallPitEmpty();

    public void updatePitProcess(Player currentPlayer) {
        if (currentPlayer.getStonesInHand() <= 0) {
            throw new IllegalStateException("Cant process move, when hand is empty");
        }
        if (!canSow(currentPlayer)) {
            return;
        }
        pitState.handleProcess(this, currentPlayer);
    }

    public void handleSimpleSow(Player currentPlayer) {
        sow();
        currentPlayer.decrementStonesInHand();
    }

    public abstract int takeStones();

    public void sow(){
        stonesInPit++;
    }


    public int getStonesInPit() {
        return stonesInPit;
    }

    public void setStonesInPit(int stonesInPit) {
        this.stonesInPit = stonesInPit;
    }

    public void addStonesToPit(int addedStones){
        setStonesInPit(getStonesInPit() + addedStones);
    }

    public Pit getOppositePit() {
        return oppositePit;
    }

    public Pit getNextPit() {
        return nextPit;
    }

    public void setNextPit(Pit nextPit) {
        this.nextPit = nextPit;
    }

    public void setOppositePit(Pit oppositePit) {
        this.oppositePit = oppositePit;
    }


    public Player getOwnerOfPit() {
        return ownerOfPit;
    }

    public void setOwnerOfPit(Player ownerOfPit) {
        this.ownerOfPit = ownerOfPit;
    }

    public void setPitState(PitState pitState) {
        this.pitState = pitState;
    }

    public PitState getPitState() {
        return pitState;
    }
}
