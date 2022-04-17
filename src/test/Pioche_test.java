package test;

import java.util.ArrayList;

import models.Pioche;
import models.Zone;
import models.Model;

public class Pioche_test {
    private Pioche pioche;
    private Model model = new Model("6\n6\n..##..\n.####.\n######\n######\n.####.\n..##..");

    public Pioche_test(){
        this.pioche = new Pioche(model.pileOfZone());
    }

    private void testGetter(){
        assert this.pioche.getPioche().size() == 24;
        assert this.pioche.getPioche().get(1) == this.model.getIsland().getZone(2, 0);
        assert this.pioche.getDefausse().isEmpty();
        assert this.pioche.getNbCarte() == 24;
    }

    private void testSetter(){
        ArrayList<Zone> card = new ArrayList<Zone>();
        this.pioche.setPioche(card);
        assert this.pioche.getPioche().isEmpty();
        card.add(new Zone(1,1));
        this.pioche.setPioche(card);
        assert this.pioche.getPioche() == card;

        this.pioche.addCardDefausse(card.get(0));
        assert this.pioche.getDefausse() == card;
    }

    private void testMethod(){
        ArrayList<Zone> card = new ArrayList<Zone>();
        card.add(new Zone(1,1));
        this.pioche.setPioche(card);
        assert this.pioche.draw() == card.get(0);

        this.pioche.resetPioche();
        assert this.pioche.getNbCarte() == 1;
        assert this.pioche.getDefausse().isEmpty();
        assert this.pioche.getPioche().size() == 1;
    }

    public void test(){
        this.testGetter();
        this.testSetter();
        this.testMethod();
    }
}
