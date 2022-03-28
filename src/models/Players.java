package models;

import views.Case;

import java.util.ArrayList;

/**
 * Players
 */
public class Players {
    private Case position;
    private String name;
    private ArrayList<Integer> card;
    private final int nbActions;

    // Constructeur
    public Players(String name, int x, int y) {
        this.position = new Case(x, y);
        this.name = name;
        this.card = new ArrayList<Integer>();
        this.nbActions = 3;
    }

    // Setter
    public void setPosition(int x, int y) {
        this.position = new Case(x, y);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addcard(int x) {
        this.card.add(x);
    }

    // Getter
    public Case getPosition() {
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
}