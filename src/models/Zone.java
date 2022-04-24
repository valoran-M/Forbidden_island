package models;

import java.awt.Point;

/**
 * Zone model
 */
public class Zone {

    private Point c;
    private int waterLvl;
    private static int maxWaterLvl = 2;

    /**
     * contrsuctor
     * 
     * @param x
     * @param y
     */
    public Zone(int x, int y) {
        this.c = new Point(x, y);
        this.waterLvl = 0;
    }

    /**
     * get coord of zone
     * @return
     */
    public Point getCoord() {
        return this.c;
    }

    /**
     * get x coord
     */
    public int getX() {
        return this.c.x;
    }

    /**
     * get y coord
     * @return
     */
    public int getY() {
        return this.c.y;
    }

    /**
     * get water lvl
     * 
     * @return int
     */
    public int getWaterLvl() {
        return this.waterLvl;
    }

    /**
     * get max water lvl
     * 
     * @return int
     */
    public int getMaxWaterLvl(){
        return maxWaterLvl;
    }

    /**
     * see if we can move on the square
     * 
     * @return
     */
    public boolean moove(){
        return maxWaterLvl > this.waterLvl;
    }

    /**
     * see if we can dry the square
     * 
     * @return
     */
    public boolean dryable(){
        return moove() && this.waterLvl > 0;
    }

    /**
     * dry zone
     * 
     */
    public void dry() {
        if (waterLvl > 0)
            this.waterLvl -= 1;
    }

    /**
     * drown zone
     * 
     */
    public void drown() {
        if (waterLvl < maxWaterLvl)
            this.waterLvl += 1;
    }
}