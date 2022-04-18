package models.roles;

import java.awt.Point;
import java.util.ArrayList;

import models.Zone;

/**
 * Explorateur
 */
public class Explorer extends Player {
    public Explorer(String name, Zone zone) {
        super(name, zone);
        setRole(Role.Explorateur);
    }

    @Override
    public Boolean isNeight(Zone move, Zone base) {
        return move.getWaterLvl() < move.getMaxWaterLvl() && Math.abs(move.getX() - base.getX()) +
                Math.abs(move.getY() - base.getY()) <= 2;
    }

    @Override
    public ArrayList<Point> neigbours() {
        ArrayList<Point> neigbours = new ArrayList<Point>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if(i == 0 && j == 0) {
                    continue;
                }
                neigbours.add(new Point(i + getPosition().getX(), j + getPosition().getY()));
            }
            
        }
        return neigbours;
    }
}