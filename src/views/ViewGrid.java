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
public class ViewGrid extends JPanel implements MouseListener {
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
        this.setBackground(new Color(1, 138, 204));
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
                    g.setColor(new Color(200, 200, 200));
                    if (island.getZone(x, y) == model.getHeliZone()) {
                        g.setColor(Color.YELLOW);
                    }
                    int i = model.getTemple().indexOf(island.getZone(x, y));
                    setColorTemple(g, i);
                    int x_case = x * sizeCase + x * sizeBorder + sizeBorder;
                    int y_case = y * sizeCase + y * sizeBorder + sizeBorder;
                    g.fillRect(x_case, y_case, sizeCase, sizeCase);
                    g.setColor(Color.BLACK);
                    g.drawRect(x_case, y_case, sizeCase, sizeCase);
                }
            }
        }
    }

    private void setColorTemple(Graphics g, int i) {
        switch (i) {
            case 0:
                g.setColor(Color.BLUE);
                break;
            case 1:
                g.setColor(Color.GREEN);
                break;
            case 2:
                g.setColor(new Color(84, 47, 38));
                break;
            case 3:
                g.setColor(Color.RED);
                break;
            default:
                break;
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