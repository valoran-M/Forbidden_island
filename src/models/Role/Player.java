package models.Role;

import java.awt.Point;

import java.util.ArrayList;

import models.Zone;

import java.lang.Math;

/**
 * Players
 */
public class Player {
    public static enum State {
        MOVING,
        DIGGING,
        EXCHANGE,
        ESCAPE
    }

    private Zone position;

    private int nbActions;
    private State state;
    private String name;
    private ArrayList<Integer> card;
    private ArrayList<Integer> artefact;
    private Role role;

    // Constructeur
    public Player(String name, Zone zone) {
        this.name = name;
        this.card = new ArrayList<Integer>();
        this.nbActions = 3;
        position = zone;
        this.state = State.MOVING;
    }

    // Setter
    public void setPosition(Zone z) {
        this.position = z;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addcard(int x) {
        this.card.add(x);
    }

    public void addArtefact(int x) {
        this.artefact.add(x);
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

    // Getter
    public void setRole(Role r) {
        this.role = r;
    }

    public Zone getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name;
    }

    public int getcard(int num) {
        return this.card.get(num);
    }

    public ArrayList<Integer> getAllCards() {
        return this.card;
    }

    public int getNbActions() {
        return this.nbActions;
    }

    // méthode
    public void changePosition(Zone z) {
        this.position = z;
    }

    public void takeArtefact(int x) {
        for (int i = 0; i < 2; i++) {
            this.card.remove(x);
        }
        this.addArtefact(x);
        this.nbActions -= 1;
    }

    public void dryUp() {
        this.nbActions -= 1;
    }

    public Boolean isNeight(Zone move, Zone base) {
        return move.getWaterLvl() < move.getMaxWaterLvl() && Math.abs(move.getX() - base.getX()) +
                Math.abs(move.getY() - base.getY()) < 2;
    }

    public ArrayList<Point> neigbours() {
        ArrayList<Point> neigbours = new ArrayList<Point>();
        neigbours.add(new Point(getPosition().getX() - 1, getPosition().getY()));
        neigbours.add(new Point(getPosition().getX() + 1, getPosition().getY()));
        neigbours.add(new Point(getPosition().getX(), getPosition().getY() - 1));
        neigbours.add(new Point(getPosition().getX(), getPosition().getY() + 1));
        return neigbours;
    }
}