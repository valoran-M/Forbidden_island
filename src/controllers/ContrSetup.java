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
 */
public class ContrSetup extends Controller implements ActionListener {

    public ContrSetup(Model model, View view) {
        super(model, view);
    }

    private Player createPlayer(Role role, String name, Zone zone) {
        Player player = null;
        switch (role) {
            case Explorateur:
                player = new Explorateur(name, zone);
                break;
            case Ingenieur:
                player = new Ingenieur(name, zone);
                break;

            case Messager:
                player = new Messager(name, zone);
                break;

            case Pilote:
                player = new Pilote(name, model.getHeliZone());
                break;

            case Navigateur:
                player = new Navigateur(name, zone);
                break;

            case Plongeur:
                player = new Plongeur(name, zone);
                break;
            default:
                break;
        }
        return player;
    }

    private void setPlayer(ArrayList<String> names) {
        ArrayList<Role> roles = new ArrayList<Role>();
        roles.add(Role.Explorateur);
        roles.add(Role.Ingenieur);
        roles.add(Role.Messager);
        roles.add(Role.Navigateur);
        roles.add(Role.Pilote);
        roles.add(Role.Plongeur);
        Collections.shuffle(roles);
        for (String name: names){
            Player player = createPlayer(roles.get(0), name, model.getIsland().getRandomCase());
            model.addPlayer(player);
            roles.remove(0);
        }
    }

    public void actionPerformed(ActionEvent e) {
        ArrayList<String> names = view.getViewSetup().getNamePlayer();
        setPlayer(names);
        view.start();
    }
}