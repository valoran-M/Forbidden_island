package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import models.Model;

/**
 * ViewPlayer
 */
public class ViewPlayer extends JPanel implements MouseListener {
    private Model model;
    public int width, height;
    public int sizeCase;

    private ArrayList<Image> pawns;

    public ViewPlayer(Model model, View view) {
        this.model = model;
        this.width = 300;
        this.height = view.grid.heightJpanel;
        this.sizeCase = view.grid.sizeCase;
        this.pawns = view.grid.pawns;

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
    }

    private void drawPlayer(Graphics g){
        int pawnsSapcing = (this.width - 60) / this.model.getPlayers().size();
        for (int i = 0; i < this.model.getPlayers().size(); i++) {
            g.drawImage(this.pawns.get(i), 30 + (pawnsSapcing + this.pawns.get(i).getWidth(null) / 2) * i, 15, null);
        }
        int actPlayer = model.getActPlayerId();
        int midX = 30 + (pawnsSapcing + this.pawns.get(actPlayer).getWidth(null) / 2) * actPlayer + this.pawns.get(actPlayer).getWidth(null) / 2;
        int midY = 15 + this.pawns.get(actPlayer).getHeight(null) / 2;
        int size = this.pawns.get(actPlayer).getHeight(null) + 10;
        drawOutline(g, midX - size / 2, midY - size / 2, size, size, new Color(255, 255, 255));
    }

    public void mouseClicked(MouseEvent e) {
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