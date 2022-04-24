package models;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Island model
 */
public class Island {

    private ArrayList<ArrayList<Zone>> grid;
    private Random rand;

    private final int width;
    private final int height;

    /**
     * Constructor with map
     * 
     * @param map
     * @throws IOException
     */
    public Island(String map) throws IOException {
        BufferedReader lecteur = new BufferedReader(new FileReader(map));

        this.width = Integer.parseInt(lecteur.readLine());
        this.height = Integer.parseInt(lecteur.readLine());
        grid = new ArrayList<ArrayList<Zone>>();
        for (int j = 0; j < height; j++) {
            ArrayList<Zone> line = new ArrayList<Zone>();
            String lineMap = lecteur.readLine();
            for (int i = 0; i < width; i++) {
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

    /**
     * Contructor with diamond
     * 
     */
    public Island() {
        this.width = 6;
        this.height = 6;
        grid = new ArrayList<ArrayList<Zone>>();
        for (int j = 0; j < height; j++) {
            ArrayList<Zone> line = new ArrayList<Zone>();
            for (int i = 0; i < width; i++) {
                if (Math.abs(i - (width - 1) / 2.) +
                        Math.abs(j - (height - 1) / 2.) <= height / 2.) {
                    line.add(new Zone(i, j));
                } else {
                    line.add(null);
                }
            }
            grid.add(line);
        }
        rand = new Random();
    }

    /**
     * get Zone
     * 
     * @param x
     * @param y
     * @return
     */
    public Zone getZone(int x, int y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            return null;
        }
        return grid.get(y).get(x);
    }

    /**
     * get Grid Size
     * 
     * @return Point
     */
    public Point getGridSize() {
        return new Point(this.width, this.height);
    }

    /**
     * get Grid
     * 
     * @return
     */
    public ArrayList<ArrayList<Zone>> getGrid() {
        return this.grid;
    }

    /**
     * get height
     * 
     * @return
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * get Width
     * 
     * @return
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * get coord line on line
     * 
     * @param y
     * @return
     */
    public ArrayList<Integer> getCoordLine(int y) {
        ArrayList<Integer> s = new ArrayList<Integer>();
        for (int index = 0; index < grid.get(y).size(); index++) {
            if (grid.get(y).get(index) != null) {
                s.add(index);
            }
        }
        return s;
    }

    /**
     * get a random case
     * @return
     */
    public Zone getRandomCase() {
        int y = rand.nextInt(this.getGridSize().y);
        int x = this.getCoordLine(y).get(rand.nextInt(this.getCoordLine(y).size()));
        return this.getZone(x, y);
    }

    /**
     * get zone'n naigbours 
     * 
     * @param p
     * @return
     */
    public ArrayList<Zone> neighbours(Zone p) {
        ArrayList<Zone> neighbours = new ArrayList<Zone>();
        neighbours.add(this.getZone(p.getCoord().x, p.getCoord().y + 1));
        neighbours.add(this.getZone(p.getCoord().x, p.getCoord().y - 1));
        neighbours.add(this.getZone(p.getCoord().x + 1, p.getCoord().y));
        neighbours.add(this.getZone(p.getCoord().x - 1, p.getCoord().y));
        return neighbours;
    }

    /**
     * check if Zone in map
     * 
     * @param pos
     * @return
     */
    public boolean inMap(Point pos) {
        return pos.y >= 0 && pos.y < getGridSize().y && pos.x >= 0 &&
                pos.x < getGridSize().x &&
                this.grid.get(pos.y).get(pos.x) != null;
    }
}