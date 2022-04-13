package models;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;

/**
 * Ile
 */
public class Island {

    private ArrayList<ArrayList<Zone>> grid;
    private Random rand;

    final int size;

    public Island(int size) {
        this.size = size;
        grid = new ArrayList<ArrayList<Zone>>();
        for (int j = 0; j < size; j++) {
            ArrayList<Zone> line = new ArrayList<Zone>();
            for (int i = 0; i < size; i++) {
                if (Math.abs(i - (size - 1) / 2.) +
                        Math.abs(j - (size - 1) / 2.) <= size / 2.) {
                    line.add(new Zone(i, j));
                } else {
                    line.add(null);
                }
            }
            grid.add(line);
        }
        rand = new Random();
    }

    public Zone getZone(int x, int y) {
        return grid.get(y).get(x);
    }

    public int getGridSize() {
        return size;
    }

    private ArrayList<Integer> getCoordLine(int y) {
        ArrayList<Integer> s = new ArrayList<Integer>();
        for (int index = 0; index < grid.size(); index++) {
            if (grid.get(y).get(index) != null) {
                s.add(index);
            }
        }
        return s;
    }

    public Zone getRandomCase() {
        int y = rand.nextInt(this.getGridSize());
        int x = this.getCoordLine(y).get(rand.nextInt(this.getCoordLine(y).size()));
        return this.getZone(x, y);
    }

    public ArrayList<Zone> neighbours(Zone p) {
        ArrayList<Zone> neighbor = new ArrayList<Zone>();
        for (int i = 0; i < 2; i++) {
            neighbor.add(this.getZone(p.getCoord().x + i, p.getCoord().y));
            neighbor.add(this.getZone(p.getCoord().x + i, p.getCoord().y + 1));
        }
        return neighbor;
    }

    public boolean inMap(Point pos) {
        return (Math.abs(pos.getX() - (size - 1) / 2.) + Math.abs(pos.getY() - (size - 1) / 2.) <= size / 2.);
    }
}
