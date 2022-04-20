package controllers;

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
    public Card selectedCard;

    public ContrPlayer(Model model, View view) {
        super(model, view);
    }

    public void playerClick(Player player) {
        Player lastPlayer = this.selectedPlayer;
        this.selectedPlayer = player;
        if (this.selectedPlayer != null && this.model.getActPlayer().getState() == Player.State.EXCHANGE
                && (this.selectedPlayer.getPosition() == this.model.getActPlayer().getPosition()
                        && this.selectedPlayer != this.model.getActPlayer())
                || (this.selectedPlayer.getRole() == Role.Messager
                        && this.selectedPlayer != this.model.getActPlayer())) {
            if (this.selectedCard != null && this.model.getActPlayer().getCards(this.selectedCard) >= 1) {
                this.model.getActPlayer().getAllCards().replace(this.selectedCard,
                        this.model.getActPlayer().getCards(this.selectedCard) - 1);
                this.selectedPlayer.getAllCards().replace(this.selectedCard,
                        this.selectedPlayer.getCards(this.selectedCard) + 1);
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

    public void cardClick(Card card) {
        this.selectedCard = card;
        view.repaint();
    }
}