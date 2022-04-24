package models;

public class Deluge {
    private int delugeLvl;

    public Deluge(int niv) {
        delugeLvl = niv;
    }

    public int getLvl() {
        return delugeLvl;
    }

    public void incrementLvl() {
        delugeLvl++;
    }

    public void setLvl(int i) {
        this.delugeLvl = i;
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
