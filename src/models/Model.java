package models;

import java.util.ArrayList;

/**
 * Models
 */
public class Model {
    private Island island;
    private ArrayList<Zone> temple;
    private ArrayList<Player> players;
    private int actPlayer;
    private Zone heliZone;

    public Model(int size) {
        this.island = new Island(size);
        this.temple = new ArrayList<Zone>();
        this.actPlayer = 0;

        for (int i = 0; i < 4; i++) {

            this.temple.add(this.getRandomValideCase());
        }

        this.heliZone = this.getRandomValideCase();
    }

    public Island getIsland() {
        return this.island;
    }

    public ArrayList<Zone> getTemple() {
        return this.temple;
    }

    public Zone getHeliZone() {
        return this.heliZone;
    }

    public void nextPlayer() {
        this.actPlayer = (this.actPlayer + 1) % this.players.size();
    }

    public void setPlayer(ArrayList<String> names) {
        for (String name : names) {
            Player joueur = new Player(name, this.island.getRandomCase());
            this.players.add(joueur);
        }
    }

    public Zone getRandomValideCase() {
        Zone pos;
        do {
            pos = this.island.getRandomCase();
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