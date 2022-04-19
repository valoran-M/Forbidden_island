package controllers;

import models.Model;
import models.roles.Player;
import views.View;

/**
 * ContrPlayer
 */
public class ContrPlayer extends Controller {
    public ContrExchange contrExchange;
    public Player selectedPlayer;

    public ContrPlayer(Model model, View view, ContrExchange contrExchange) {
        super(model, view);
        this.contrExchange = contrExchange;
    }

    public void playerClick(Player player) {
        this.selectedPlayer = player; 
        view.repaint();
    }
}