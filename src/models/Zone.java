package models;

import java.awt.Point;

/**
 * Zone
 */
public class Zone {

    private Point c;
    private int waterLvl;
    private static int maxWaterLvl = 2;

    public Zone(int x, int y) {
        this.c = new Point(x, y);
        this.waterLvl = 0;
    }

    public Point getCoord() {
        return this.c;
    }

    public int getX() {
        return this.c.x;
    }

    public int getY() {
        return this.c.y;
    }

    public int getWaterLvl() {
        return this.waterLvl;
    }

    public int getMaxWaterLvl(){
        return maxWaterLvl;
    }

    public void dry() {
        if (waterLvl > 0)
            this.waterLvl -= 1;
    }

    public void drown() {
        if (waterLvl < maxWaterLvl)
            this.waterLvl += 1;
    }
}