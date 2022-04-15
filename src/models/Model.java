package models;

import java.util.ArrayList;
import java.io.IOException;
import java.util.logging.*;

import java.awt.Point;

/**
 * Models
 */
public class Model {
    private Island island;
    private ArrayList<Zone> temple;
    private ArrayList<Player> players;
    private Pioche pioche;
    private int actPlayer;
    private int nbInondation;
    private Player escape;
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
        this.pioche = new Pioche(this.pileOfZone());
        this.escape = null;
        this.nbInondation = 3;

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

    public int getActPlayerId() {
        return this.actPlayer;
    }

    public Player getEscape() {
        return this.escape;
    }

    public Player getActPlayer() {
        return this.players.get(actPlayer);
    }

    public Pioche getPioche() {
        return this.pioche;
    }

    public int getNbInondation() {
        return this.nbInondation;
    }

    // Setter

    public void pick() {
        this.nbInondation--;
    }

    public void resetNbInondation() {
        this.nbInondation = 3;
    }

    public void setEscape() {
        Player escape = null;
        for (Player player : players) {
            if (player.getState() == Player.State.ESCAPE) {
                escape = player;
            }
        }
        this.escape = escape;
    }

    public void setPlayer(ArrayList<String> names) {
        for (String name : names) {
            Player joueur = new Player(name, this.island.getRandomCase(), this);
            this.players.add(joueur);
        }
    }

    public void nextPlayer() {
        this.actPlayer = (this.actPlayer + 1) % this.players.size();
        getActPlayer().resetAction();
    }

    public Zone getRandomValideCase() {
        Zone pos;
        do {
            pos = this.island.getRandomCase();
        } while (this.temple.contains(pos));
        return pos;
    }

    private Boolean allTraveled(Boolean[][] visitedCase) {
        for (int i = 0; i < visitedCase.length; i++) {
            for (int j = 0; j < visitedCase[i].length; j++) {
                if (!visitedCase[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private Point getMinCase(Boolean[][] visitedCase, int[][] action) {
        Point p = new Point(0, 0);
        int min = 999;
        for (int j = 0; j < action.length; j++) {
            for (int i = 0; i < action[j].length; i++) {
                if (!visitedCase[j][i] && action[j][i] <= min) {
                    min = action[j][i];
                    p = new Point(i, j);
                }
            }
        }
        return p;
    }

    public int[][] nbActionMove() {
        Boolean[][] visitedCase = new Boolean[island.getHeight()][island.getWidth()];
        int[][] action = new int[island.getHeight()][island.getWidth()];
        for (int j = 0; j < action.length; j++) {
            for (int i = 0; i < action[j].length; i++) {
                action[j][i] = 999;
                if (island.getZone(i, j) == null) {
                    visitedCase[j][i] = true;
                } else {
                    visitedCase[j][i] = false;
                }
            }
        }

        action[getActPlayer().getPosition().getY()][getActPlayer().getPosition().getX()] = 0;
        while (!allTraveled(visitedCase)) {
            Point p = getMinCase(visitedCase, action);
            visitedCase[p.y][p.x] = true;
            for (int j = -1; j <= 1; j++) {
                for (int i = -1; i <= 1; i++) {
                    if (island.getZone(p.x + i, p.y + j) != null) {
                        int weight = getActPlayer().getWeight(i, j, p.x, p.y);
                        if (action[p.y + j][p.x + i] > action[p.y][p.x] + weight) {
                            action[p.y + j][p.x + i] = action[p.y][p.x] + weight;
                        }
                    }
                }

            }
        }
        return action;
    }

    public ArrayList<Zone> pileOfZone() {
        ArrayList<Zone> cards = new ArrayList<Zone>();
        for (int y = 0; y < this.getIsland().getHeight(); y++) {
            for (int x : this.getIsland().getCoordLine(y)) {
                cards.add(this.getIsland().getZone(x, y));
            }
        }
        return cards;
    }
}