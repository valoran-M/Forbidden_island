import static org.junit.Assert.*;
import org.junit.*;

import java.util.ArrayList;

import models.Pioche;
import models.Zone;
import models.Model;

public class Pioche_test {
    private Pioche pioche;
    private Model model = new Model("map/default.map");

    @Before
    public void Pioche_init() {
        this.pioche = new Pioche(model.pileOfZone());
    }

    @Test
    public void testGetter() {
        assertEquals(this.pioche.getPioche().size(), 24);
        assertTrue(this.pioche.getDefausse().isEmpty());
        assertEquals(this.pioche.getNbCarte(), 24);
    }

    @Test
    public void testSetter() {
        ArrayList<Zone> card = new ArrayList<Zone>();
        this.pioche.setPioche(card);
        assertTrue(this.pioche.getPioche().isEmpty());
        card.add(new Zone(1, 1));
        this.pioche.setPioche(card);
        assertEquals(this.pioche.getPioche(), card);

        this.pioche.addCardDefausse(card.get(0));
        assertEquals(this.pioche.getDefausse(), card);
    }

    @Test
    public void testMethod() {
        ArrayList<Zone> card = new ArrayList<Zone>();
        card.add(new Zone(1, 1));
        this.pioche.setPioche(card);
        Zone p = card.get(0);
        assertEquals(this.pioche.draw(), p);

        this.pioche.resetPioche();
        assertEquals(this.pioche.getNbCarte(), 23);
        assertTrue(this.pioche.getDefausse().isEmpty());
        assertEquals(this.pioche.getPioche().size(), 1);
    }
}
