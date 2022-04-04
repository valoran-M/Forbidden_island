package models;

import java.awt.Point;

/**
 * Zone
 */
public class Zone {
    private Point c;

    public Zone(int x, int y){
        this.c = new Point(x, y);
    }

    public Point getCoord(){
        return this.c;
    }
}