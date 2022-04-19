package models.roles;

import java.awt.Point;

import java.util.ArrayList;
import java.util.HashMap;

import models.Model;
import models.Zone;
import models.Card;

import java.lang.Math;

/**
 * Players
 */
public abstract class Player {
    public static enum State {
        MOVING,
        DRY,
        EXCHANGE,
        ESCAPE
    }

    protected Zone position;
    protected int nbActions;
    protected State state;
    protected String name;
    protected HashMap<Card, Integer> cards;
    protected Role role;
    protected boolean power;
    // Constructeur
    public Player (String name, Zone zone) {
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
    public void powerDown() {
        power = false;
    }

    public void powerUp() {
        power = true;
    }

    public void setPosition(Zone z) {
        this.position = z;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void resetAction() {
        this.nbActions = 3;
    }

    public void setAction(int n) {
        this.nbActions = n;
    }

    public void setState(State s) {
        this.state = s;
    }

    public State getState() {
        return this.state;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role r) {
        this.role = r;
    }

    public void addcard(Card c) {
        this.cards.put(c, this.cards.get(c) + 1);
    }

    // Getter
    public boolean getPower() {
        return this.power;
    }

    public Zone getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name;
    }

    public HashMap<Card, Integer> getAllCards() {
        return this.cards;
    }

    public int getNbActions() {
        return this.nbActions;
    }

    public Integer getCards(Card c){
        return this.cards.get(c);
    }


    // méthode
    public void changePosition(Zone z) {
        this.position = z;
    }

    public void dryUp() {
        this.nbActions -= 1;
    }

    public void useCard(Card c){
        this.cards.put(c, this.cards.get(c) - 1);
    }

    public Boolean isNeight(Zone move, Zone base) {
        return move.getWaterLvl() < move.getMaxWaterLvl() && Math.abs(move.getX() - base.getX()) +
                Math.abs(move.getY() - base.getY()) < 2;
    }

    public int getWeightNeight(int weightNeight, int lastWeight, Zone z) {
        if (weightNeight + 1 < lastWeight) {
            return weightNeight + 1;
        } else {
            return lastWeight;
        }
    }

    public ArrayList<Point> neigboursMove(Model model) {
        ArrayList<Point> neigbours = new ArrayList<Point>();
        neigbours.add(new Point(getPosition().getX() - 1, getPosition().getY()));
        neigbours.add(new Point(getPosition().getX() + 1, getPosition().getY()));
        neigbours.add(new Point(getPosition().getX(), getPosition().getY() - 1));
        neigbours.add(new Point(getPosition().getX(), getPosition().getY() + 1));
        return neigbours;
    }

    public ArrayList<Point> neigboursDry(Model model) {
        return neigboursMove(model);
    }
}