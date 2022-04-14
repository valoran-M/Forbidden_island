package models;

import java.awt.Point;

/**
 * Zone
 */
public class Zone {

    private Point c;
    private int waterLvl;
    private final int maxWaterLvl;

    public Zone(int x, int y) {
        this.c = new Point(x, y);
        this.waterLvl = 0;
        this.maxWaterLvl = 3;
    }

    public Point getCoord() {
        return this.c;
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