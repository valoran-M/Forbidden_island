package models;

import java.util.ArrayList;
import java.util.Random;

/**
 * Models
 */
public class Model {
    private Island island;
    private ArrayList<Zone> temple;
    private ArrayList<Player> players;
    private Zone heliZone;

    public Model(int size) {
        this.island = new Island(size);
        this.temple = new ArrayList<Zone>();

        for (int i = 0; i < 4; i++) {

            this.temple.add(this.getRandomCase());
        }

        this.heliZone = this.getRandomCase();
    }

    public Island getIsland() {
        return this.island;
    }

    public void setPlayer(ArrayList<String> names){
        for(String name : names){
            Player joueur = new Player(name, this.getRandomCase());
            this.players.add(joueur);
        }
    }

    public Zone getRandomCase() {
        Random rand = new Random();
        int y = rand.nextInt(this.island.getGridSize());
        int x = this.island.getCoordLine(y).get(rand.nextInt(this.island.getCoordLine(y).size()));
        return this.island.getZone(x, y);
    }

    public Zone getRandomValideCase() {
        Zone pos;
        do {
            pos = this.getRandomCase();
        } while (this.temple.contains(pos));
        return pos;
    }

    public ArrayList<Zone> neighbours(Zone p) {
        ArrayList<Zone> neighbor = new ArrayList<Zone>();
        for (int i = 0; i < 2; i++) {
            neighbor.add(this.island.getZone(p.getCoord().x + i, p.getCoord().y));
            neighbor.add(this.island.getZone(p.getCoord().x + i, p.getCoord().y + 1));
        }
        return neighbor;
    }
}