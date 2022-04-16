package controllers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import models.Model;
import views.View;

/**
 * ContrSearch
 */
public class ContrSearch extends Controller implements ActionListener {

    public ContrSearch(Model model, View view) {
        super(model, view);
    }

    public Boolean canTakeTresure() {
        int index = model.getTemple().indexOf(model.getActPlayer().getPosition());
        if (index != -1) {
            return true;
        }
        return false;

    }

    public void actionPerformed(ActionEvent e) {
        if (model.getActPlayer().getNbActions() > 0 && canTakeTresure()) {
            model.getActPlayer().setAction(
                    model.getActPlayer().getNbActions() - 1);
            int index = model.getTemple().indexOf(model.getActPlayer().getPosition());
            model.getTemple().set(index, null);
            model.getTreasureState().set(index, true);
            view.repaint();
        }
    }

}