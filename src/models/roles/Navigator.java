package models.roles;

import models.Zone;

/**
 * Navigateur
 */
public class Navigator extends Player {

    public Navigator(String name, Zone zone) {
        super(name, zone);
        setRole(Role.Navigateur);
    }
}