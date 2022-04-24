package controllers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import models.Card;
import models.Model;
import views.View;

/**
 * ContrSearch 
 * use if the search button was clicked
 */
public class ContrSearch extends Controller implements ActionListener {

    public ContrSearch(Model model, View view) {
        super(model, view);
    }

    /**
     * test if a player was on temple zone
     * 
     * @return Boolean
     */
    public Boolean checkPosition() {
        int index = model.getTemple().indexOf(model.getActPlayer().getPosition());
        if (index != -1) {
            return true;
        }
        return false;

    }

    /**
     * call the search method of the model when 
     * the search button was clicked
     * 
     * @param ActionEvent
     * 
     */
    public void actionPerformed(ActionEvent e) {
        if (model.getActPlayer().getNbActions() > 0 && checkPosition()) {
            model.getActPlayer().setAction(
                    model.getActPlayer().getNbActions() - 1);
            int index = model.getTemple().indexOf(model.getActPlayer().getPosition());
            if(model.getActPlayer().getCards(Card.getCardTemple(index)) >= 4){
                model.getTemple().set(index, null);
                model.getTreasureState().set(index, true);
                view.repaint();
                model.getActPlayer().getAllCards().replace(Card.getCardTemple(index), model.getActPlayer().getCards(Card.getCardTemple(index)) - 4);
            }
        }
    }
}