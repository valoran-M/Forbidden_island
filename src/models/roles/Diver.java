package models.roles;

import java.util.ArrayList;

import java.awt.Point;

import models.Model;
import models.Zone;

/**
 * Plongeur
 */
public class Diver extends Player {

    public Diver(String name, Zone zone) {
        super(name, zone);
        setRole(Role.Plongeur);
    }

    @Override
    public Boolean isNeight(Zone move, Zone base) {
        return Math.abs(move.getX() - base.getX()) +
                Math.abs(move.getY() - base.getY()) < 2;
    }

    @Override
    public int getWeightNeight(int weightNeight, int lastWeight, Zone z) {
        if (z.getWaterLvl() >= z.getMaxWaterLvl()) {
            return weightNeight;
        } else {
            return super.getWeightNeight(weightNeight, lastWeight, z);
        }
    }

    private int dist(int x1, int y1, Zone z2) {
        return Math.abs(x1 - z2.getX()) + Math.abs(y1 - z2.getY());
    }

    @Override
    public ArrayList<Point> neigboursMove(Model model) {
        ArrayList<Point> neigbours = new ArrayList<Point>();
        int nbAction[][] = model.nbAction(this);
        int minDist = 999;
        for (int y = 0; y < nbAction.length; y++) {
            for (int x = 0; x < nbAction[y].length; x++) {
                if (nbAction[y][x] == 1
                        && model.getIsland().getZone(x, y).getWaterLvl() < model.getIsland().getZone(x, y)
                                .getMaxWaterLvl()) {
                    if (dist(x, y, getPosition()) < minDist) {
                        neigbours = new ArrayList<Point>();
                        neigbours.add(new Point(x, y));
                        minDist = dist(x, y, getPosition());
                    } else if (dist(x, y, getPosition()) == minDist) {
                        neigbours.add(new Point(x, y));
                    }
                }
            }
        }
        return neigbours;
    }
}