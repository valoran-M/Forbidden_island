package models.roles;

import models.Zone;

/**
 * Pilote
 */
public class Pilot extends Player {

    public Pilot(String name, Zone zone) {
        super(name, zone);
        setRole(Role.Pilote);
    }
}