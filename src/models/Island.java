package models;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;

/**
 * Ile
 */
public class Island {

    private ArrayList<ArrayList<Zone>> grid;
    private ArrayList<Zone> temple;
    private ArrayList<Player> player;
    private Point heliZone;

    public Island(int size) {
        Random rand = new Random();
        int x, y;
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

        while(this.temple.size() <= 4){
            x = rand.nextInt(size);
            y = rand.nextInt(size - x);
            if (!this.temple.contains(this.getZone(x, y))){
                this.temple.add(this.getZone(x, y));
            }
        }

        x = rand.nextInt(size);
        y = rand.nextInt(size - x);
        this.heliZone = new Point(x,y);
    }

    public void setPlayers(){

    }

    public Zone getZone(int x, int y) {
        return grid.get(y).get(x);
    }

    public int getGridSize() {
        return grid.size();
    }

    public ArrayList<Integer> getCoordLine(int y) {
        ArrayList<Integer> s = new ArrayList<Integer>();
        for (int index = 0; index < grid.size(); index++) {
            if (grid.get(y).get(index) != null) {
                s.add(index);
            }
        }
        return s;
    }

    public ArrayList<Zone> neighbours(Zone p){
        ArrayList<Zone> neighbor = new ArrayList<Zone>();
        for(int i = 0; i < 2; i++){
            neighbor.add(getZone(p.getCoord().x + i, p.getCoord().y));
            neighbor.add(getZone(p.getCoord().x + i, p.getCoord().y + 1));
        }
        return neighbor;
    }
}
