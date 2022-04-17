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
}