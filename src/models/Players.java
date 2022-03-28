package models;

import views.ViewCase;

import java.util.ArrayList;

/**
 * Players
 */
public class Players {
    private ViewCase position;
    private String name;
    private ArrayList<Integer> card;
    private final int nbActions;

    // Constructeur
    public Players(String name) {
        this.name = name;
        this.card = new ArrayList<Integer>();
        this.nbActions = 3;
        this.position = new ViewCase(0, 0); 
    }

    // Setter
    public void setPosition(int x, int y) {
        this.position = new ViewCase(x, y);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addcard(int x) {
        this.card.add(x);
    }

    // Getter
    public ViewCase getPosition() {
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