package controllers;

import java.util.ArrayList;

import models.Card;
import models.Model;
import models.roles.Player;
import views.View;

/**
 * Use for viewPlayer
 */
public class ContrPlayer extends Controller {
    public Player selectedPlayer;
    public ArrayList<Player> playersHeli;
    public Card selectedCard;
    public ContrEndTurn contrEndTurn;

    public ContrPlayer(Model model, View view) {
        super(model, view);
        this.playersHeli = new ArrayList<Player>();
    }

    /**
     * Call the method of the model when player click on a pawn
     * 
     * The function is used to interpret 
     * the click according to the state of the game
     * 
     * @param player
     */
    public void playerClick(Player player) {
        if (this.model.getState() == Model.State.SPE_CARD) {
            selectedPlayerHeli(player);
            return;
        }

        Player lastPlayer = this.selectedPlayer;
        this.selectedPlayer = player;

        if (this.selectedPlayer != null && this.selectedCard != null
                && this.model.getActPlayer().possibleExchange(this.selectedPlayer, this.selectedCard)) {
            if (this.model.getActPlayer().getCards(this.selectedCard) >= 1) {
                this.model.getActPlayer().useCard(this.selectedCard);
                this.selectedPlayer.addcard(this.selectedCard);

                this.model.getActPlayer().setState(Player.State.MOVING);
                this.selectedCard = null;
                this.selectedPlayer = lastPlayer;
                view.repaint();
                return;
            }
        }
        this.selectedCard = null;
        view.repaint();
    }

    /**
     * Handles player selection for the helicopter 
     * card when players click on a pawn
     *
     * @param player
     */
    private void selectedPlayerHeli(Player player) {
        if (this.playersHeli.contains(player)) {
            this.playersHeli.remove(player);
        } else {
            for (Player p : this.playersHeli) {
                if (p.getPosition() != player.getPosition()) {
                    view.repaint();
                    return;
                }
            }
            this.playersHeli.add(player);
        }
        view.repaint();
    }
    /**
     * Discard a select card when the 
     * player has too many cards
     * 
     * @param card
     */
    private void throwClick(Card card) {
        this.selectedCard = null;
        if (model.getActPlayer().getCards(card) >= 1) {
            model.getActPlayer().useCard(card);
            if (model.getActPlayer().getNbCards() <= contrEndTurn.maxCard) {
                contrEndTurn.nexTurn();
            }
        }
    }

    /**
     * Check if the victory conditions are met
     * 
     * @return
     */
    private Boolean victoryCheck() {
        boolean ownH = false;
        for (Player p : model.getPlayers()) {
            if (p.getCards(Card.HELICOPTERE) > 0) {
                ownH = true;
            }
            if (p.getPosition() != model.getHeliZone()) {
                return false;
            }
        }
        return !model.getTreasureState().contains(false) && ownH;
    }

    /**
     * Called when a map is clicked and manages 
     * the map according to game state
     * 
     * @param card
     */
    public void cardClick(Card card) {
        this.selectedCard = card;
        if (model.getState() == Model.State.SPE_CARD) {
            if (this.selectedPlayer != null && this.selectedPlayer.getCards(card) >= 1) {
                if (card == Card.HELICOPTERE) {
                    if (victoryCheck()) {
                        model.setState(Model.State.VICTORY);
                        this.view.gameOver();
                        return;
                    }
                    this.selectedCard = Card.HELICOPTERE;
                } else if (card == Card.SAC) {
                    this.selectedCard = Card.SAC;
                }
            }
        } else if (model.getActPlayer().getState() == Player.State.THROW) {
            throwClick(card);
        }
        view.repaint();
    }
}