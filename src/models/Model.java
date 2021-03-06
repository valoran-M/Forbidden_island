package models;

import java.util.ArrayList;
import java.io.IOException;
import java.util.logging.*;

import models.roles.Player;

import java.awt.Point;

/**
 * Models
 */
public class Model {
    public static enum State {
        SETUP,
        RUNNING,
        LOSE,
        VICTORY,
        SPE_CARD
    }

    private String map;
    private State state;
    private Island island;
    private Deluge delugeLvl;
    private ArrayList<Zone> temple;
    private ArrayList<Boolean> treasureState;
    private ArrayList<Player> players;
    private PiocheInnondation piocheWater;
    private int actPlayer;
    private Zone heliZone;
    private PiocheCard piocheCard;

    /**
     * Constructor
     * 
     * @param map
     */
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

        this.delugeLvl = new Deluge(0);
        this.map = map;
        this.state = State.SETUP;
        this.temple = new ArrayList<Zone>();
        this.players = new ArrayList<Player>();
        this.piocheWater = new PiocheInnondation(this.pileOfZone());
        this.piocheCard = new PiocheCard();
        this.treasureState = new ArrayList<Boolean>();
        for (int i = 0; i < 4; i++) {
            treasureState.add(false);
        }

        this.actPlayer = 0;

        for (int i = 0; i < 4; i++) {
            this.temple.add(this.getRandomValideCase());
        }
        this.heliZone = this.getRandomValideCase();
    }

    // Getter
    /**
     * get deluge level
     * @return
     */
    public Deluge getDelugeLvl() {
        return this.delugeLvl;
    }

    /**
     * get i th treasure state
     * 
     * @param i
     * @return
     */
    public Boolean getTreasureState(int i) {
        return this.treasureState.get(i);
    }

    /**
     * get every treasure state
     * 
     * @return
     */
    public ArrayList<Boolean> getTreasureState() {
        return this.treasureState;
    }

    /**
     * get actual state
     * 
     * @return
     */
    public State getState() {
        return this.state;
    }

    /**
     * get island
     * 
     * @return
     */
    public Island getIsland() {
        return this.island;
    }

    /**
     * get temples
     * 
     * @return
     */
    public ArrayList<Zone> getTemple() {
        return this.temple;
    }

    /**
     * get Players
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * get  helicopter zone
     * 
     */
    public Zone getHeliZone() {
        return this.heliZone;
    }

    /**
     * get act player id
     * @return
     */
    public int getActPlayerId() {
        return this.actPlayer;
    }

    /**
     * get Act player
     * 
     * @return
     */
    public Player getActPlayer() {
        return this.players.get(actPlayer);
    }

    /**
     * get Pioche Water
     * @return
     */
    public PiocheInnondation getPiocheWater() {
        return this.piocheWater;
    }

    /**
     * get Pioche Card
     * 
     * @return
     */
    public PiocheCard getPiocheCard() {
        return this.piocheCard;
    }

    // Setter

    /**
     * set State
     * 
     * @param state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * add player
     * 
     * @param player
     */
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    /**
     * change player
     * 
     */
    public void nextPlayer() {
        this.actPlayer = (this.actPlayer + 1) % this.players.size();
        getActPlayer().resetAction();
    }

    /**
     * get random valide case
     * 
     * @return
     */
    public Zone getRandomValideCase() {
        Zone pos;
        do {
            pos = this.island.getRandomCase();
        } while (this.temple.contains(pos));
        return pos;
    }

    /**
     * check if alle Case was visited
     * 
     * @param Boolean[][
     * @return Boolean
     */
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

    /**
     * get min case not visited
     * 
     * @param visitedCase
     * @param action
     * @return
     */
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


    /**
     * return action case with player power
     * 
     * @param player
     * @return
     */
    public int[][] nbAction(Player player) {
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
        Player playerForZ = this.getActPlayer();
        for (Player p : this.players) {
            if (p.getState() == Player.State.ESCAPE) {
                playerForZ = p;
            }
        }
        action[playerForZ.getPosition().getY()][playerForZ.getPosition().getX()] = 0;
        while (!allTraveled(visitedCase)) {
            Point p = getMinCase(visitedCase, action);
            visitedCase[p.y][p.x] = true;
            for (int j = -1; j <= 1; j++) {
                for (int i = -1; i <= 1; i++) {
                    if (island.getZone(p.x + i, p.y + j) != null) {
                        if (player.isNeight(island.getZone(p.x + i, p.y + j),
                                island.getZone(p.x, p.y))) {
                            action[p.y + j][p.x + i] = player.getWeightNeight(action[p.y][p.x],
                                    action[p.y + j][p.x + i], island.getZone(p.x + i, p.y + j));
                        }
                    }
                }

            }
        }
        return action;
    }

    /**
     * get nb action without player power
     * @param x
     * @param y
     * @return
     */
    public int[][] nbActionNormal(int x, int y) {
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
        action[y][x] = 0;
        while (!allTraveled(visitedCase)) {
            Point p = getMinCase(visitedCase, action);
            visitedCase[p.y][p.x] = true;
            for (int j = -1; j <= 1; j++) {
                for (int i = -1; i <= 1; i++) {
                    if (island.getZone(p.x + i, p.y + j) != null) {
                        if (Math.abs(i) + Math.abs(j) == 1) {
                            if (action[p.y + j][p.x + i] > action[p.y][p.x] + 1) {
                                action[p.y + j][p.x + i] = action[p.y][p.x] + 1;
                            }
                        }
                    }
                }
            }
        }
        return action;
    }

    /**
     * stacked zone
     * @return
     */
    public ArrayList<Zone> pileOfZone() {
        ArrayList<Zone> cards = new ArrayList<Zone>();
        for (int y = 0; y < this.getIsland().getHeight(); y++) {
            for (int x : this.getIsland().getCoordLine(y)) {
                cards.add(this.getIsland().getZone(x, y));
            }
        }
        return cards;
    }

    /**
     * reset model
     * 
     */
    public void reset() {
        this.state = State.SETUP;
        try {
            this.island = new Island(map);
        } catch (IOException e) {
            Logger logger = Logger.getLogger(
                    Model.class.getName());
            logger.setLevel(Level.WARNING);
            logger.warning(map + " not found default map used");
            this.island = new Island();
        }
        this.temple.clear();
        this.players.clear();
        this.piocheWater = new PiocheInnondation(this.pileOfZone());
        this.piocheCard = new PiocheCard();
        this.treasureState = new ArrayList<Boolean>();
        for (int i = 0; i < 4; i++) {
            treasureState.add(false);
        }

        this.actPlayer = 0;

        for (int i = 0; i < 4; i++) {
            this.temple.add(this.getRandomValideCase());
        }
        this.heliZone = this.getRandomValideCase();
    }
}