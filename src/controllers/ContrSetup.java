package controllers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.Collections;

import models.Model;
import models.Zone;
import models.roles.*;
import views.View;

/**
 * ContrSetup
 * setup model when start button was clicked
 */
public class ContrSetup extends Controller implements ActionListener {

    public ContrSetup(Model model, View view) {
        super(model, view);
    }

    /**
     * return Player object with correct role
     * 
     * @param role
     * @param name
     * @param zone
     * @return Player
     */
    private Player createPlayer(Role role, String name, Zone zone) {
        Player player = null;
        switch (role) {
            case Explorateur:
                player = new Explorer(name, zone);
                break;
            case Ingenieur:
                player = new Engineer(name, zone);
                break;

            case Messager:
                player = new Messenger(name, zone);
                break;

            case Pilote:
                player = new Pilot(name, model.getHeliZone());
                break;

            case Navigateur:
                player = new Navigator(name, zone);
                break;

            case Plongeur:
                player = new Diver(name, zone);
                break;
            default:
                break;
        }
        return player;
    }

    /**
     * create players and add them to model
     * 
     * @param players player name
     */
    private void setPlayer(ArrayList<String> names) {
        ArrayList<Role> roles = new ArrayList<Role>();
        roles.add(Role.Explorateur);
        roles.add(Role.Ingenieur);
        roles.add(Role.Messager);
        roles.add(Role.Navigateur);
        roles.add(Role.Pilote);
        roles.add(Role.Plongeur);
        Collections.shuffle(roles);
        for (String name : names) {
            Player player = createPlayer(roles.get(0), name, model.getIsland().getRandomCase());
            model.addPlayer(player);
            roles.remove(0);
        }
    }

    /**
     * function called when start button was clicked
     * 
     * @param ActionEvent e
     */
    public void actionPerformed(ActionEvent e) {
        ArrayList<String> names = view.setup.getNamePlayer();
        setPlayer(names);
        model.getDelugeLvl().setLvl(this.view.setup.levelSlider.getValue() - 1);
        view.start();
    }
}