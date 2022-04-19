import static org.junit.Assert.*;
import org.junit.*;

import models.Zone;
import models.roles.Player;
import models.roles.Engineer;
import models.roles.Player.State;

public class Player_test {
    private Player p;
    private Zone z;


    public Player_test(){
        this.z = new Zone(3, 3);
        this.p = new Engineer("Joueur-1", this.z);
    }

    private void testGetter(){
        assertEquals(this.p.getName(), "Joueur-1");
        assert this.p.getNbActions() == 3;
        assert this.p.getPosition() == this.z;
        assert this.p.getState() == State.MOVING;
    }

    private void testSetter(){
        this.p.setName("gravil");
        this.p.setPosition(new Zone(2, 3));
        //this.p.setRole(new Role("Pilote", Role.Pilote));
    }

    private void testMethod(){
    }

    public void test(){
        this.testGetter();
        this.testSetter();
        this.testMethod();
        System.out.println("test Player : OK");
    }
} 
