package models.roles;

import models.Zone;

/**
 * Pilote
 */
public class Pilote extends Player {

    public Pilote(String name, Zone zone) {
        super(name, zone);
        setRole(Role.Pilote);
    }
}