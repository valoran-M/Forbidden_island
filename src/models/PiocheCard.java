package models;

import java.util.ArrayList;
import java.util.Collections;

public class PiocheCard {
    private ArrayList<Card> pioche;
    private ArrayList<Card> defausse;

    public PiocheCard() {
        this.pioche = new ArrayList<Card>();
        this.defausse = new ArrayList<Card>();
        for (int i = 0; i < 5; i++) {
            this.pioche.add(Card.AIR);
            this.pioche.add(Card.FEU);
            this.pioche.add(Card.EAU);
            this.pioche.add(Card.TERRE);
            if (i > 1) {
                this.pioche.add(Card.DELUGE);
                this.pioche.add(Card.HELICOPTERE);
            }
            if (i > 2) {
                this.pioche.add(Card.SAC);
            }
        }
        Collections.shuffle(this.pioche);
    }

    public ArrayList<Card> getPioche() {
        return this.pioche;
    }

    public ArrayList<Card> getDefausse() {
        return this.defausse;
    }

    public void setPioche(ArrayList<Card> cards) {
        this.pioche = cards;
    }

    public void sendToDefausse(Card c){
        this.defausse.add(c);
        this.pioche.remove(c);
    }

    public Card pick() {
        if (this.pioche.size() == 0) {
            resetPioche();
        }
        Card c = this.getPioche().get(0);
        this.defausse.add(c);
        this.pioche.remove(this.pioche.get(0));
        return c;
    }

    public void resetPioche() {
        this.setPioche(new ArrayList<Card>(defausse));
        Collections.shuffle(this.pioche);
        this.defausse.removeAll(this.defausse);
    }
}
