package views;

import javax.swing.JPanel;

import models.Model;

/**
 * ViewPanel
 */
public abstract class ViewPanel extends JPanel {
    public Model model;

    public ViewPanel(Model m) {
        super();
        this.model = m;
    }
}