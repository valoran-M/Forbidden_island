package models.roles;

import models.Zone;

/**
 * Explorateur
 */
public class Explorateur extends Player {
    public Explorateur(String name, Zone zone) {
        super(name, zone);
        setRole(Role.Explorateur);
    }
}