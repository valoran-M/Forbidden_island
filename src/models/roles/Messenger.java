package models.roles;

import models.Zone;

/**
 * Messager
 */
public class Messenger extends Player{

    public Messenger(String name, Zone zone) {
        super(name, zone);
        setRole(Role.Messager);
    }

    
}