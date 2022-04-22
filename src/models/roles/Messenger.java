package models.roles;

import models.Card;
import models.Zone;

/**
 * Messager
 */
public class Messenger extends Player {

    public Messenger(String name, Zone zone) {
        super(name, zone);
        setRole(Role.Messager);
    }

    @Override
    public boolean possibleExchange(Player player, Card card) {
        return getState() == State.EXCHANGE && player != this
                && getCards(card) > 0;
    }
}