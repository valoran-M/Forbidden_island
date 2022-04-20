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
    public ContrEndTurn contrEndTurn;

    public ContrPlayer(Model model, View view) {
        super(model, view);
    }

    public void playerClick(Player player) {
        Player lastPlayer = this.selectedPlayer;
        this.selectedPlayer = player;

        if (this.selectedPlayer != null
                && this.model.getActPlayer().getState() == Player.State.EXCHANGE
                && (this.selectedPlayer.getPosition() == this.model.getActPlayer().getPosition()
                        && this.selectedPlayer != this.model.getActPlayer())
                || (this.selectedPlayer.getRole() == Role.Messager
                        && this.selectedPlayer != this.model.getActPlayer())) {

            if (this.selectedCard != null && this.model.getActPlayer().getCards(this.selectedCard) >= 1) {

                this.model.getActPlayer().useCard(this.selectedCard);;
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

    private void throwClick(Card card){
        this.selectedCard = null;
        if(model.getActPlayer().getCards(card) >= 1){
            model.getActPlayer().useCard(card);
            if(model.getActPlayer().getNbCards() <= maxCard){
                contrEndTurn.nexTurn();
            }
        }
    }

    public void cardClick(Card card) {
        this.selectedCard = card;
        if(model.getActPlayer().getState() == Player.State.THROW){
            throwClick(card);
        }
        view.repaint();
    }
}