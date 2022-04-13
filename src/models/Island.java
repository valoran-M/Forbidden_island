package models;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Ile
 */
public class Island {

    private ArrayList<ArrayList<Zone>> grid;
    private Random rand;

    final int size;

    public Island(String map) throws IOException {
        BufferedReader lecteur = new BufferedReader(new FileReader(map));

        this.size = Integer.parseInt(lecteur.readLine());
        grid = new ArrayList<ArrayList<Zone>>();
        for (int j = 0; j < size; j++) {
            ArrayList<Zone> line = new ArrayList<Zone>();
            String lineMap = lecteur.readLine();
            for (int i = 0; i < size; i++) {
                if (lineMap.charAt(i) == '#') {
                    line.add(new Zone(i, j));
                } else {
                    line.add(null);
                }
            }
            grid.add(line);
        }

        lecteur.close();

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
        return pos.y >= 0 && pos.y < this.getGridSize() && pos.x >= 0 &&
                pos.x < this.grid.get(pos.y).size() &&
                this.grid.get(pos.y).get(pos.x) != null;
    }
}
