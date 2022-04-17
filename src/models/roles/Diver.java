package models.roles;

import models.Zone;

/**
 * Plongeur
 */
public class Diver extends Player {

    public Diver(String name, Zone zone) {
        super(name, zone);
        setRole(Role.Plongeur);
    }
}