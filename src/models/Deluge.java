package models;

import java.util.ArrayList;

public class Deluge {
    private ArrayList<Boolean> delugeLvl;

    public Deluge(int niv) {
        this.delugeLvl = new ArrayList<Boolean>(10);
        for (int i = 0; i < 10; i++) {
            this.delugeLvl.add(false);
        }
        this.delugeLvl.set(niv, true);
    }

    public int getLvl() {
        return delugeLvl.indexOf(true);
    }

    public void incrementLvl() {
        setLvl(getLvl() + 1);
    }

    public void setLvl(int i) {
        this.delugeLvl.set(this.getLvl(), false);
        this.delugeLvl.set(i, true);
    }

    public int innondationRate() {
        if (this.getLvl() < 2)
            return 2;
        else if (this.getLvl() < 5)
            return 3;
        else if (this.getLvl() < 7)
            return 4;
        return 5;
    }

    public boolean loose() {
        return this.getLvl() == 10;
    }
}
