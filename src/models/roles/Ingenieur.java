package models.roles;

import models.Zone;

/**
 * Ingenieur
 */
public class Ingenieur extends Player {
    public Ingenieur(String name, Zone zone) {
        super(name, zone);
        setRole(Role.Ingenieur);
    }
}