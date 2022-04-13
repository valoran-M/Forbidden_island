package models;

import java.util.ArrayList;
import java.awt.Point;
import java.io.IOException;
import java.util.logging.*;

/**
 * Models
 */
public class Model {
    private Island island;
    private ArrayList<Zone> temple;
    private ArrayList<Player> players;
    private int actPlayer;
    private Zone heliZone;

    public Model(String map) {
        try {
            this.island = new Island(map);
        } catch (IOException e) {
            Logger logger = Logger.getLogger(
                    Model.class.getName());
            logger.setLevel(Level.WARNING);
            logger.warning(map + " not found default map used");
            this.island = new Island();
        }
        this.temple = new ArrayList<Zone>();
        this.players = new ArrayList<Player>();

        this.actPlayer = 0;

        for (int i = 0; i < 4; i++) {
            this.temple.add(this.getRandomValideCase());
        }
        this.heliZone = this.getRandomValideCase();
    }

    // Getter
    public Island getIsland() {
        return this.island;
    }

    public ArrayList<Zone> getTemple() {
        return this.temple;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public Zone getHeliZone() {
        return this.heliZone;
    }

    public int getActPlayer() {
        return this.actPlayer;
    }

    // Setter
    public void setPlayer(ArrayList<String> names) {
        for (String name : names) {
            Player joueur = new Player(name, this.island.getRandomCase());
            this.players.add(joueur);
        }
    }

    public void nextPlayer() {
        this.actPlayer = (this.actPlayer + 1) % this.players.size();
    }

    public Zone getRandomValideCase() {
        Zone pos;
        do {
            pos = this.island.getRandomCase();
        } while (this.temple.contains(pos));
        return pos;
    }

    public ArrayList<Zone> accessiblZones(Player joueur) {
        ArrayList<Zone> Case = new ArrayList<Zone>();
        for (Point position : joueur.surroundingZone()) {
            if (this.island.inMap(position)) {
                Case.add(this.island.getZone((int) position.getX(), (int) position.getY()));
            }
        }
        return Case;
    }
}