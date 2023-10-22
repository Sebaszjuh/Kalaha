package com.sgriendt.pit;


import com.sgriendt.player.Player;

public abstract class Pit {

    private int stones;
    private PitState pitState;
    private Player player;
    private Pit next;
    private Pit oppositePit;

    Pit (int stones, Player player, PitState pitState) {
        this.stones = stones;
        this.player = player;
        this.pitState = pitState;
    }

    public abstract boolean canTake();
    public abstract boolean canSow(Player player);
    public abstract void handleLastSow(Player currentPlayer);

    public abstract boolean isSmallPitEmpty();


    public int getStones() {
        return stones;
    }

    public void setStones(int stones) {
        this.stones = stones;
    }

    public void addStonesToPit(int addedStones){
    }

    public Pit getOppositePit() {
        return oppositePit;
    }

    public Pit getNext() {
        return next;
    }

    public void setNext(Pit next) {
        this.next = next;
    }

    public void setOppositePit(Pit oppositePit) {
        this.oppositePit = oppositePit;
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPitState(PitState pitState) {
        this.pitState = pitState;
    }

    public PitState getPitState() {
        return pitState;
    }
}
