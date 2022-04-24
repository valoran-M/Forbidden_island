package models.roles;

import java.awt.Point;

import java.util.ArrayList;
import java.util.HashMap;

import models.Model;
import models.Zone;
import models.Card;

import java.lang.Math;

/**
 * Players model
 */
public abstract class Player {
    public static enum State {
        MOVING,
        DRY,
        EXCHANGE,
        ESCAPE,
        THROW
    }

    protected Zone position;
    protected int nbActions;
    protected State state;
    protected String name;
    protected HashMap<Card, Integer> cards;
    protected Role role;
    protected boolean power;

    // Constructeur
    /**
     * constructor
     * 
     * @param name
     * @param zone
     */
    public Player(String name, Zone zone) {
        this.name = name;
        this.cards = new HashMap<Card, Integer>();
        this.cards.put(Card.AIR, 0);
        this.cards.put(Card.EAU, 0);
        this.cards.put(Card.FEU, 0);
        this.cards.put(Card.TERRE, 0);
        this.cards.put(Card.HELICOPTERE, 0);
        this.cards.put(Card.SAC, 0);
        this.nbActions = 3;
        this.position = zone;
        this.state = State.MOVING;
    }

    // Setter
    /**
     * power down
     * 
     */
    public void powerDown() {
        power = false;
    }

    /**
     * power up
     * 
     */
    public void powerUp() {
        power = true;
    }

    /**
     * set name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * reset action
     * 
     */
    public void resetAction() {
        this.nbActions = 3;
    }

    /**
     * set action
     * 
     * @param n
     */
    public void setAction(int n) {
        this.nbActions = n;
    }

    /**
     * set state
     * 
     * @param s
     */
    public void setState(State s) {
        this.state = s;
    }

    /**
     * get State
     * 
     * @return state
     */
    public State getState() {
        return this.state;
    }

    /**
     * get role
     * 
     * @return role
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * set role
     * 
     * @param r
     */
    public void setRole(Role r) {
        this.role = r;
    }

    /**
     * add card tu his inventory
     * 
     * @param c
     */
    public void addcard(Card c) {
        this.cards.put(c, this.cards.get(c) + 1);
    }

    // Getter
    /**
     * get power
     * 
     * @return power
     */
    public boolean getPower() {
        return this.power;
    }

    /**
     * get position
     * 
     * @return position
     */
    public Zone getPosition() {
        return this.position;
    }

    /**
     * get name
     * 
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * get car inventory
     * 
     * @return
     */
    public HashMap<Card, Integer> getAllCards() {
        return this.cards;
    }

    /**
     * get nb action
     * 
     * @return nbAction
     */
    public int getNbActions() {
        return this.nbActions;
    }

    /**
     * get number of Card c
     * 
     * @param c
     * @return int
     */
    public int getCards(Card c) {
        return this.cards.get(c);
    }

    /**
     * get number of Card
     * 
     * @return int
     */
    public int getNbCards() {
        int total = 0;
        for (Integer nbCard : this.cards.values()) {
            total += nbCard;
        }
        return total;
    }

    // méthode
    /**
     * see if swapping is possible
     * 
     * @param player
     * @param card
     * @return
     */
    public boolean possibleExchange(Player player, Card card) {
        return getState() == State.EXCHANGE
                && player.getPosition() == getPosition()
                && player != this
                && getCards(card) > 0;
    }

    /**
     * change player position
     * 
     * @param z
     */
    public void changePosition(Zone z) {
        this.position = z;
    }

    /**
     * remove one action when player dry
     * 
     */
    public void dryUp() {
        this.nbActions -= 1;
    }

    /**
     * Use card c
     * 
     * @param c
     */
    public void useCard(Card c) {
        this.cards.put(c, this.cards.get(c) - 1);
    }

    /**
     * check if move is neight to base
     * 
     * @param move
     * @param base
     * @return
     */
    public Boolean isNeight(Zone move, Zone base) {
        return move.getWaterLvl() < move.getMaxWaterLvl() && Math.abs(move.getX() - base.getX()) +
                Math.abs(move.getY() - base.getY()) < 2;
    }

    /**
     * get good weight for dijstra algo
     * 
     * @param weightNeight
     * @param lastWeight
     * @param z
     * @return
     */
    public int getWeightNeight(int weightNeight, int lastWeight, Zone z) {
        if (weightNeight + 1 < lastWeight) {
            return weightNeight + 1;
        } else {
            return lastWeight;
        }
    }

    /**
     * return neigbours Case for move
     * 
     * @param model
     * @return
     */
    public ArrayList<Point> neigboursMove(Model model) {
        ArrayList<Point> neigbours = new ArrayList<Point>();
        neigbours.add(new Point(getPosition().getX() - 1, getPosition().getY()));
        neigbours.add(new Point(getPosition().getX() + 1, getPosition().getY()));
        neigbours.add(new Point(getPosition().getX(), getPosition().getY() - 1));
        neigbours.add(new Point(getPosition().getX(), getPosition().getY() + 1));
        return neigbours;
    }

    /**
     * retunr niegbours for dry
     * 
     * @param model
     * @return
     */
    public ArrayList<Point> neigboursDry(Model model) {
        return neigboursMove(model);
    }
}