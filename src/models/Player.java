package models;

import java.util.ArrayList;

/**
 * Players
 */
public class Player {
    private final int nbActions;

    private Zone position;
    private String name;
    private ArrayList<Integer> card;

    // Constructeur
    public Player(String name, Zone zone) {
        this.name = name;
        this.card = new ArrayList<Integer>();
        this.nbActions = 3;
        position = zone;
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
}