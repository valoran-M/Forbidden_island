package models.roles;

import models.Zone;

/**
 * Navigateur
 */
public class Navigateur extends Player {

    public Navigateur(String name, Zone zone) {
        super(name, zone);
        setRole(Role.Navigateur);
    }
}