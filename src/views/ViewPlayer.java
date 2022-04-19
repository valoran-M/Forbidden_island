package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import controllers.ContrExchange;
import controllers.ContrPlayer;
import models.Model;
import models.roles.Player;

/**
 * ViewPlayer
 */
public class ViewPlayer extends JPanel implements MouseListener {
    private Model model;

    private ContrPlayer contrPlayer;

    public int width, height;
    public int sizeCase;

    private int pawnsSapcing;

    private ArrayList<Image> pawns;

    public ViewPlayer(Model model, View view, ContrExchange contrExchange) {
        this.model = model;
        this.width = 300;
        this.height = view.grid.heightJpanel;
        this.sizeCase = view.grid.sizeCase;
        this.pawns = view.grid.pawns;

        this.contrPlayer = new ContrPlayer(model, view, contrExchange);

        this.pawnsSapcing = (this.width - 60) / 4;

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
        drawOutline(g, 0, 0, this.width, 140, new Color(124, 78, 40));
        drawOutline(g, 0, 145, this.width, this.height - 145, new Color(124, 78, 40));
        if (this.pawns != null) {
            drawPlayer(g);
        }
        if (model.getActPlayer().getState() == Player.State.EXCHANGE) {
            drawExchange(g);
        }
        drawActPlayer(g);
    }

    private void drawPawnOutline(Graphics g, int player, Color color) {
        int midX = 30 + (pawnsSapcing + this.pawns.get(player).getWidth(null) / 2) * player
                + this.pawns.get(player).getWidth(null) / 2;
        int midY = 15 + this.pawns.get(player).getHeight(null) / 2;
        int size = this.pawns.get(player).getHeight(null) + 10;
        drawOutline(g, midX - size / 2, midY - size / 2, size, size, color);
    }

    private void drawPlayer(Graphics g) {
        for (int player = 0; player < this.model.getPlayers().size(); player++) {
            g.drawImage(this.pawns.get(player),
                    30 + (pawnsSapcing + this.pawns.get(player).getWidth(null) / 2) * player, 15, null);

            if (model.getPlayers().get(player) == contrPlayer.selectedPlayer) {
                int midX = 30 + (pawnsSapcing + this.pawns.get(player).getWidth(null) / 2) * player
                        + this.pawns.get(player).getWidth(null) / 2;
                int midY = 15 + this.pawns.get(player).getHeight(null) / 2;
                g.setColor(new Color(185, 5, 26));
                g.fillOval(midX - 5, midY * 2, 10, 10);
            }
        }
        drawPawnOutline(g, model.getActPlayerId(), new Color(255, 255, 255));
    }

    private void drawActPlayer(Graphics g) {
        g.setColor(new Color(200, 200, 200));
        Font currentFont = g.getFont();
        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        g.drawString("" + model.getActPlayer().getNbActions(), this.width - 50, 130);
        g.setFont(currentFont);
        g.drawString(model.getActPlayer().getName(), 20,
                130 - g.getFontMetrics().getHeight());
        g.drawString(model.getActPlayer().getRole().toString(), 20, 130);
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
        if (e.getY() >= 15 && e.getY() <= 15 + this.pawns.get(0).getHeight(null)) {
            for (int player = 0; player < model.getPlayers().size(); player++) {
                int size = this.pawns.get(player).getHeight(null) + 10;
                if (e.getX() >= 30 + (pawnsSapcing + this.pawns.get(player).getWidth(null) / 2) * player - size / 2
                        && e.getX() <= 30 + (pawnsSapcing + this.pawns.get(player).getWidth(null) / 2) * (player + 1)
                                - size / 2) {
                    contrPlayer.playerClick(model.getPlayers().get(player));
                    return;
                }
            }
            contrPlayer.playerClick(null);
        }
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