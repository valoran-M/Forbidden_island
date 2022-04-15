package models;

import java.util.ArrayList;
import java.util.Collections;

public class Pioche {
    private ArrayList<Zone> pioche;
    private ArrayList<Zone> defausse;
    private int nbCartePioche;

    public Pioche(ArrayList<Zone> p) {
        this.pioche = p;
        Collections.shuffle(this.pioche);
        this.defausse = new ArrayList<Zone>();
        this.nbCartePioche = this.pioche.size();
    }

    // Getter
    public ArrayList<Zone> getPioche() {
        return this.pioche;
    }

    public ArrayList<Zone> getDefausse() {
        return this.defausse;
    }

    public int getNbCarte() {
        return this.nbCartePioche;
    }

    public void addCardDefausse(Zone p) {
        this.defausse.add(p);
    }

    public void setPioche(ArrayList<Zone> cards) {
        this.pioche = cards;
    }

    // Méthode
    public Zone draw() {
        Zone z = this.getPioche().get(0);
        if (z.getWaterLvl() != z.getMaxWaterLvl()) {
            this.addCardDefausse(z);
        }
        this.pioche.remove(0);
        this.nbCartePioche--;
        return z;
    }

    public void resetPioche() {
        this.setPioche(new ArrayList<Zone>(defausse));
        Collections.shuffle(this.pioche);
        this.nbCartePioche = this.getNbCarte();
        this.defausse.removeAll(this.defausse);
    }
}