package controllers;

import java.util.ArrayList;

import models.Card;
import models.Model;
import models.roles.Player;
import models.roles.Role;
import views.View;

/**
 * ContrPlayer
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

    public void playerClick(Player player) {
        if (this.model.getState() == Model.State.SPE_CARD) {
            selectedPlayerHeli(player);
            return;
        }

        Player lastPlayer = this.selectedPlayer;
        this.selectedPlayer = player;

        if (this.selectedPlayer != null
                && this.model.getActPlayer().getState() == Player.State.EXCHANGE
                && (this.selectedPlayer.getPosition() == this.model.getActPlayer().getPosition()
                        && this.selectedPlayer != this.model.getActPlayer())
                || (this.selectedPlayer.getRole() == Role.Messager
                        && this.selectedPlayer != this.model.getActPlayer())) {
            if (this.selectedCard != null && this.model.getActPlayer().getCards(this.selectedCard) >= 1) {

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

    private void throwClick(Card card) {
        this.selectedCard = null;
        if (model.getActPlayer().getCards(card) >= 1) {
            model.getActPlayer().useCard(card);
            if (model.getActPlayer().getNbCards() <= contrEndTurn.maxCard) {
                contrEndTurn.nexTurn();
            }
        }
    }

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