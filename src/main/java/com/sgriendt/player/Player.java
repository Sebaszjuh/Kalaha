package com.sgriendt.player;

import com.sgriendt.pit.Pit;

public class Player {

    private String name;
    private int stonesInHand;
    private Pit bigPit;
    private boolean extraTurn;

    public Player(String name) {
        this.name = name;
        this.stonesInHand = 0;
    }

    public int getStonesInHand() {
        return stonesInHand;
    }

    public void setStonesInHand(int stonesInHand) {
        this.stonesInHand = stonesInHand;
    }


    public void setExtraTurn() {
        this.extraTurn = true;
    }

    public String getName() {
        return name;
    }

    public Pit getBigPit() {
        return bigPit;
    }

    public void setBigPit(Pit bigPit) {
        this.bigPit = bigPit;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Player p)) {
            return false;
        }
        return p.name.equals(name) && p.hashCode() == this.hashCode();
    }

}
