package views;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import models.Model;

/**
 * ViewLevel
 */
public class ViewLevel extends ViewPanel {

    public Image Stick;
    public Image Level;

    public ViewLevel(Model model, View view) {
        super(model);
        this.setBackground(view.background);
        this.setPreferredSize(new Dimension(200, view.grid.heightJpanel));

        Stick = new ImageIcon("images/stick.png").getImage();
        Stick = Stick.getScaledInstance(Stick.getWidth(null) / 4, Stick.getHeight(null) / 4, Image.SCALE_SMOOTH);
        Stick.getHeight(null);
        
        Level = new ImageIcon("images/level.png").getImage();
        Level = Level.getScaledInstance(Level.getWidth(null) / 2, Level.getHeight(null) / 2, Image.SCALE_SMOOTH);
        Level.getHeight(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(Level, this.getWidth() / 2 - Level.getWidth(null) / 2, this.getHeight() / 2 - Level.getHeight(null) / 2, null);
        int minY = this.getHeight() / 2 + Level.getHeight(null) / 2 - 73;
        int space = 34;
        g.drawImage(Stick, this.getWidth() / 2 - Level.getWidth(null) / 2 - 20, minY - space * model.getDelugeLvl().getLvl(), null);
    }
}