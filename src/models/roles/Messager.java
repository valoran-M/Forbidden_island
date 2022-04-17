package models.roles;

import models.Zone;

/**
 * Messager
 */
public class Messager extends Player{

    public Messager(String name, Zone zone) {
        super(name, zone);
        setRole(Role.Messager);
    }

    
}