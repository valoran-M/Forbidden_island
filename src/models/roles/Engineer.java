package models.roles;

import models.Zone;

/**
 * Ingenieur
 */
public class Engineer extends Player {
    public Engineer(String name, Zone zone) {
        super(name, zone);
        setRole(Role.Ingenieur);
    }

    @Override
    public void dryUp() {
        if (getPower()) {
            this.nbActions -= 1;
            powerDown();
        } else {
            powerUp();
        }
    }
}