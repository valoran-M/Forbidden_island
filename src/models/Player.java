package models;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

/**
 * Players
 */
public class Player {
    public static enum State {
        MOVING,
        DIGGING,
        EXCHANGE
    }

    private Zone position;

    private int nbActions;
    private Model model;
    private State state;
    private String name;
    private ArrayList<Integer> card;
    private ArrayList<Integer> artefact;

    // Constructeur
    public Player(String name, Zone zone, Model model) {
        this.name = name;
        this.card = new ArrayList<Integer>();
        this.nbActions = 3;
        this.model = model;
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

    // Getter
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

    public void dig() {
        Random rand = new Random();
        int val = rand.nextInt(10);
        if (val <= 3) {
            this.getcard(rand.nextInt(4));
        } else if (val > 7) {
            this.position.drown();
        }
        this.nbActions -= 1;
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
        this.position.dry();
    }

    public int getWeight(int x, int y, int xbase, int ybase) {
        if (Math.abs(x) + Math.abs(y) == 2) {
            return 999;
        } else {
            if (this.model.getIsland().getZone(x + xbase, y + ybase).getWaterLvl() >= this.model.getIsland()
                    .getZone(x + xbase, y + ybase).getMaxWaterLvl()) {
                return 999;
            }
            return 1;
        }
    }
}