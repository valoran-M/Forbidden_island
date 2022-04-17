package models.roles;

import models.Zone;

/**
 * Plongeur
 */
public class Plongeur extends Player {

    public Plongeur(String name, Zone zone) {
        super(name, zone);
        setRole(Role.Plongeur);
    }
}