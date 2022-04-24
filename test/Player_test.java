import static org.junit.Assert.*;
import org.junit.*;

import models.Zone;
import models.roles.Player;
import models.roles.Engineer;
import models.roles.Player.State;

public class Player_test {
    private Player p;
    private Zone z;

    @Before
    public void player_setup() {
        this.z = new Zone(3, 3);
        this.p = new Engineer("Joueur-1", this.z);
    }

    @Test
    public void testGetter() {
        assertEquals(this.p.getName(), "Joueur-1");
        assertEquals(this.p.getNbActions(), 3);
        assertEquals(this.p.getPosition(), this.z);
        assertEquals(this.p.getState(), State.MOVING);
    }

    @Test
    public void testSetter() {
        this.p.setName("gravil");
        this.p.changePosition(z);
        // this.p.setRole(new Role("Pilote", Role.Pilote));
    }

    @Test
    public void testMethod() {
    }
}
