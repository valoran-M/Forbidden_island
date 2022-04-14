package views;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controllers.ContrGrid;
import models.Island;
import models.Model;
import models.Player;
import models.Zone;

/**
 * Grid
 */
public class ViewGrid extends JPanel implements MouseListener {
    private ContrGrid control;

    private Model model;

    private ArrayList<Image> pawns;

    final public int widthJpanel;
    final public int heightJpanel;
    final public int sizeCase = 80;
    final public int sizeBorder = 5;
    final private int pawnHeight = 60 / 2;

    public ViewGrid(Model m) {
        this.model = m;
        int width = m.getIsland().getGridSize().x;
        int height = m.getIsland().getGridSize().y;
        this.widthJpanel = width * sizeCase + (width + 1) * sizeBorder;
        this.heightJpanel = height * sizeCase + (height + 1) * sizeBorder;

        this.setPreferredSize(new java.awt.Dimension(
                widthJpanel, heightJpanel));

        this.setLayout(null);
        this.setBounds(30, 30, widthJpanel, heightJpanel);
        this.setBackground(new Color(1, 138, 204));
        this.addMouseListener(this);

        this.control = new ContrGrid(m, this);
    }

    public void initPawn() {
        String path = "images/pawns/";
        String pawnsPath[] = new String[] { path + "greenPawn.png", path + "bluePawn.png", path + "redPawn.png",
                path + "yellowPawn.png" };
        pawns = new ArrayList<Image>();
        for (int i = 0; i < model.getPlayers().size(); i++) {
            Image img = new ImageIcon(pawnsPath[i]).getImage();
            int width = img.getWidth(null);
            int height = img.getHeight(null);
            double coef = (double) height / (double) width;
            img = img.getScaledInstance((int) (pawnHeight / coef), pawnHeight, Image.SCALE_SMOOTH);
            img.getWidth(null);
            img.getHeight(null);
            pawns.add(img);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        Island island = this.model.getIsland();
        for (int y = 0; y < island.getGridSize().y; y++) {
            for (int x = 0; x < island.getGridSize().x; x++) {
                if (island.inMap(new Point(x, y))) {
                    setColor(g, island.getZone(x, y));
                    int x_case = x * (sizeCase + sizeBorder) + sizeBorder;
                    int y_case = y * (sizeCase + sizeBorder) + sizeBorder;
                    g.fillRect(x_case, y_case, sizeCase, sizeCase);
                }
            }
        }
        int playerIter = 0;
        for (Player player : model.getPlayers()) {
            Point pos = player.getPosition().getCoord();
            draw_pawn(g, (int) pos.getX(), (int) pos.getY(), playerIter, model.getPlayers().indexOf(player));
            playerIter++;
        }
        colorMove(g);
    }

    private void draw_pawn(Graphics g, int x, int y, int i, int player) {
        x = x * (sizeCase + sizeBorder) + sizeBorder;
        y = y * (sizeCase + sizeBorder) + sizeBorder;
        switch (i) {
            case 0:
                x += sizeBorder;
                y += sizeBorder;
                break;
            case 1:
                x += sizeCase - sizeBorder - pawns.get(player).getWidth(null);
                y += sizeBorder;
                break;

            case 2:
                x += sizeBorder;
                y += sizeCase - sizeBorder - pawns.get(player).getHeight(null);
                break;

            case 3:
                x += sizeCase - sizeBorder - pawns.get(player).getWidth(null);
                y += sizeCase - sizeBorder - pawns.get(player).getHeight(null);
                break;

            default:
                break;
        }

        g.drawImage(pawns.get(player), x, y, null);
    }

    private void setColor(Graphics g, Zone zone) {
        g.setColor(new Color(200, 200, 200));
        if (zone == model.getHeliZone()) {
            g.setColor(Color.YELLOW);
        }

        int i = model.getTemple().indexOf(zone);
        setColorTemple(g, i);
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

    private void colorMove(Graphics g) {
        g.setColor(Color.BLACK);
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