package models;

import java.util.ArrayList;
import java.util.Random;

/**
 * Players
 */
public class Player {
    private int nbActions;

    private Zone position;
    private String name;
    private ArrayList<Integer> card;
    private ArrayList<Integer> artefact;

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

    public void setAction(){
        this.nbActions = 3;
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

    //méthode
    public void move(int x, int y){
        this.position.modifie(x, y);
        this.nbActions -= 1;
    }

    public void dig(){
        Random rand = new Random();
        int val = rand.nextInt(10);
        if(val <= 3){
            this.getcard(rand.nextInt(4));
        } else if (val > 7){
            this.position.drown();
        }
        this.nbActions -= 1;
    }

    public void takeArtefact(){
    }
}