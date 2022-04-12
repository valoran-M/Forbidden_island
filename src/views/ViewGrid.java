package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;


import controllers.ContrGrid;
import models.Island;
import models.Model;

/**
 * Grid
 */
public class ViewGrid extends JPanel implements MouseListener{
    private ContrGrid control;

    private Model model;

    final public int sizeJpanel;
    final public int sizeCase = 60;
    final public int sizeBorder = 5;

    public ViewGrid(Model m) {
        this.model = m;
        int sizeGrid = m.getIsland().getGridSize();
        sizeJpanel = sizeGrid * sizeCase + (sizeGrid + 1) * sizeBorder;
        this.setPreferredSize(new java.awt.Dimension(
                sizeJpanel, sizeJpanel));
        
        this.setLayout(null);
        this.setBounds(30, 30, sizeJpanel, sizeJpanel);
        this.setBackground(new Color(0, 0, 128));
        this.addMouseListener(this);

        this.control = new ContrGrid(m);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        Island island = this.model.getIsland();
        for (int y = 0; y < island.getGridSize(); y++) {
            for (int x = 0; x < island.getGridSize(); x++) {
                if (island.getZone(x, y) != null) {
                    int x_case = x * sizeCase + x * sizeBorder;
                    int y_case = y * sizeCase + y * sizeBorder;
                    g.setColor(Color.GREEN);
                    g.fillRect(x_case, y_case, sizeCase, sizeCase);
                    g.setColor(Color.RED);
                    g.drawRect(x_case, y_case, sizeCase + 1, sizeCase + 1);
                }
            }
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int x_case = x / (sizeCase + sizeBorder);
        int y_case = y / (sizeCase + sizeBorder);
        this.control.click(x_case, y_case);
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }
}