package models;

/**
 * Class for water level
 */
public class Deluge {
    private int delugeLvl;

    /**
     * Constructor
     * 
     * @param niv
     */
    public Deluge(int niv) {
        delugeLvl = niv;
    }

    /**
     * return lvl
     * 
     * @return
     */
    public int getLvl() {
        return delugeLvl;
    }

    /**
     * incrementLvl
     * 
     */
    public void incrementLvl() {
        delugeLvl++;
    }

    /**
     * set Level
     * 
     * @param i
     */
    public void setLvl(int i) {
        this.delugeLvl = i;
    }

    /**
     * return indonation case
     * 
     * @return
     */
    public int innondationRate() {
        if (this.getLvl() < 2)
            return 2;
        else if (this.getLvl() < 5)
            return 3;
        else if (this.getLvl() < 7)
            return 4;
        return 5;
    }

    /**
     * check game over
     * 
     * @return
     */
    public boolean loose() {
        return this.getLvl() == 10;
    }
}
