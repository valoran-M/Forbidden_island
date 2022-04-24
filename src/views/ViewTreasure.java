package views;

import java.util.ArrayList;

import java.awt.Graphics;
import java.awt.Image;

import models.Model;

/**
 * ViewTreasure
 */
public class ViewTreasure extends ViewPanel {
    public ArrayList<Image> temples;

    public ViewTreasure(Model model, View view, ViewGrid viewGrid) {
        super(model);
        this.temples = viewGrid.temples;
        setBackground(view.background);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int pawnsSapcing = (this.getWidth() - 60) / this.model.getTemple().size();
        for (int i = 0; i < this.model.getTemple().size(); i++) {
            if (model.getTreasureState(i)) {
                g.drawImage(this.temples.get(i), 30 + (pawnsSapcing + this.temples.get(i).getWidth(null) / 2) * i, 15,
                        null);
            }
        }
    }
}