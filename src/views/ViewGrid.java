package views;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import models.Model;

/**
 * Grid
 */
public class ViewGrid extends JPanel {
    private Model mode;

    final int sizeCase = 60;
    final int sizeBorder = 5;

    public ViewGrid(Model m) {
        this.mode = m;
        int sizeGrid = m.getIsland().getGridSize();
        int sizeJpanel = sizeGrid * sizeCase + (sizeGrid + 1) * sizeBorder;
        this.setPreferredSize(new java.awt.Dimension(
                sizeJpanel, sizeJpanel));
        System.out.println(sizeJpanel);
        this.setBounds(0, 0, sizeJpanel, sizeJpanel);
        this.setBackground(new Color(0, 0, 128));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
    }

    @Override
    public void repaint(){
        super.repaint();
    }
}