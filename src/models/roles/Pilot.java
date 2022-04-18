package models.roles;

import java.util.ArrayList;

import java.awt.Point;

import models.Model;
import models.Zone;

/**
 * Pilote
 */
public class Pilot extends Player {

    public Pilot(String name, Zone zone) {
        super(name, zone);
        power = true;
        setRole(Role.Pilote);
    }

    @Override
    public Boolean isNeight(Zone move, Zone base) {
        if (power) {
            return move.getWaterLvl() < move.getMaxWaterLvl();
        } else {
            return super.isNeight(move, base);
        }
    }

    @Override
    public int getWeightNeight(int weightNeight, int lastWeight, Zone z) {
        if (power && 1 < lastWeight) {
            return 1;
        } else {
            return super.getWeightNeight(weightNeight, lastWeight, z);
        }
    }

    @Override
    public ArrayList<Point> neigboursMove(Model model) {
        if (power) {
            ArrayList<Point> neigbours = new ArrayList<Point>();
            int nbAction[][] = model.nbAction(this);
            for (int y = 0; y < nbAction.length; y++) {
                for (int x = 0; x < nbAction[y].length; x++) {
                    if(nbAction[y][x] == 1) {
                        neigbours.add(new Point(x, y));
                    }
                }
            }
            return neigbours;
        } else {
            return super.neigboursMove(model);
        }
    }

    @Override
    public ArrayList<Point> neigboursDry(Model model) {
        return super.neigboursMove(model);
    }
}