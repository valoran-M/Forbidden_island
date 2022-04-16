package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import controllers.ContrExchange;
import controllers.ContrPlayer;
import models.Model;
import models.Player;

/**
 * ViewPlayer
 */
public class ViewPlayer extends JPanel implements MouseListener {
    private Model model;

    private ContrPlayer contrPlayer;

    public int width, height;
    public int sizeCase;

    private ArrayList<Image> pawns;

    public ViewPlayer(Model model, View view, ContrExchange contrExchange) {
        this.model = model;
        this.width = 300;
        this.height = view.grid.heightJpanel;
        this.sizeCase = view.grid.sizeCase;
        this.pawns = view.grid.pawns;

        this.contrPlayer = new ContrPlayer(model, view, contrExchange);

        setPreferredSize(new java.awt.Dimension(width, height));
        setBackground(view.background);
        addMouseListener(this);
    }

    private void drawOutline(Graphics g, int x, int y, int sizeW, int sizeH, Color color) {
        g.setColor(color);
        for (int i = 0; i < 3; i++) {
            g.drawRect(x + i, y + i, sizeW - i * 2, sizeH - i * 2);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawOutline(g, 0, 0, this.width, this.height, new Color(124, 78, 40));
        drawPlayer(g);
        if (model.getActPlayer().getState() == Player.State.EXCHANGE) {
            drawExchange(g);
        }
    }

    private void drawPawnOutline(Graphics g, int player, Color color) {
        int pawnsSapcing = (this.width - 60) / this.model.getPlayers().size();
        int midX = 30 + (pawnsSapcing + this.pawns.get(player).getWidth(null) / 2) * player
                + this.pawns.get(player).getWidth(null) / 2;
        int midY = 15 + this.pawns.get(player).getHeight(null) / 2;
        int size = this.pawns.get(player).getHeight(null) + 10;
        drawOutline(g, midX - size / 2, midY - size / 2, size, size, color);
    }

    private void drawPlayer(Graphics g) {
        int pawnsSapcing = (this.width - 60) / this.model.getPlayers().size();
        for (int i = 0; i < this.model.getPlayers().size(); i++) {
            g.drawImage(this.pawns.get(i), 30 + (pawnsSapcing + this.pawns.get(i).getWidth(null) / 2) * i, 15, null);
        }
        drawPawnOutline(g, model.getActPlayerId(), new Color(255, 255, 255));
    }

    private void drawExchange(Graphics g) {
        for (int i = 0; i < this.model.getPlayers().size(); i++) {
            if (model.getActPlayer().getPosition() == model.getPlayers().get(i).getPosition() &&
                    model.getActPlayerId() != i) {
                drawPawnOutline(g, i, new Color(255, 0, 0));
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        contrPlayer.playerClick(model.getActPlayer());
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