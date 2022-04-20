package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controllers.ContrPlayer;
import models.Card;
import models.Model;
import models.roles.Player;
import models.roles.Role;

/**
 * ViewPlayer
 */
public class ViewPlayer extends JPanel implements MouseListener {
    private Model model;

    public ContrPlayer contrPlayer;

    public int width, height;
    public int sizeCase;

    private int pawnsSapcing;

    private ArrayList<Image> pawns;
    private ArrayList<Image> temples;
    private Image sandbag;
    private Image helicopter;

    public ViewPlayer(Model model, View view) {
        this.model = model;
        this.width = 300;
        this.height = view.grid.heightJpanel;
        this.sizeCase = view.grid.sizeCase;
        this.pawns = view.grid.pawns;

        this.contrPlayer = new ContrPlayer(model, view);
        view.grid.control.setContrPlayer(contrPlayer);
        view.grid.contrPlayer = contrPlayer;
        contrPlayer.contrEndTurn = view.contrEndTurn;

        this.pawnsSapcing = (this.width - 60) / 4;

        String path = "images/elements/";
        String pawnsPath[] = new String[] { path + "air.png", path + "earth.png", path + "fire.png",
                path + "water.png" };
        temples = new ArrayList<Image>();
        for (int i = 0; i < 4; i++) {
            Image img = new ImageIcon(pawnsPath[i]).getImage();
            img = img.getScaledInstance(sizeCase / 2, sizeCase / 2, Image.SCALE_DEFAULT);
            img.getHeight(null);
            temples.add(img);
        }

        sandbag = new ImageIcon("images/sandbag.png").getImage();
        sandbag = sandbag.getScaledInstance(sizeCase - 30, sizeCase - 30, Image.SCALE_DEFAULT);
        sandbag.getHeight(null);

        helicopter = new ImageIcon("images/heliport.png").getImage();
        helicopter = helicopter.getScaledInstance(sizeCase - 30, sizeCase - 30, Image.SCALE_DEFAULT);
        helicopter.getHeight(null);

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
        if (model.getActPlayer().getState() == Player.State.EXCHANGE && contrPlayer.selectedCard != null) {
            drawExchange(g);
        }
        drawActPlayer(g);
        drawSelectedPlayer(g);
    }

    private void drawPawnOutline(Graphics g, int player, Color color) {
        int midX = 30 + (pawnsSapcing + this.pawns.get(player).getWidth(null) / 2) * player
                + this.pawns.get(player).getWidth(null) / 2;
        int midY = 15 + this.pawns.get(player).getHeight(null) / 2;
        int size = this.pawns.get(player).getHeight(null) + 10;
        drawOutline(g, midX - size / 2, midY - size / 2, size, size, color);
    }

    private void drawPlayer(Graphics g) {
        for (int player = 0; player < this.pawns.size(); player++) {
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
            if ((model.getActPlayer().getPosition() == model.getPlayers().get(i).getPosition() &&
                    model.getActPlayerId() != i)
                    || (model.getActPlayer().getRole() == Role.Messager
                            && model.getActPlayer() != model.getPlayers().get(i))) {
                drawPawnOutline(g, i, new Color(255, 0, 0));
            }
        }
    }

    private void drawSelectedPlayer(Graphics g) {
        Color colorT = new Color(200, 200, 200);
        g.setColor(colorT);
        Player player = contrPlayer.selectedPlayer;
        int minY = 145;
        if (player != null) {
            int index = model.getPlayers().indexOf(player);
            g.drawImage(pawns.get(index), 20, minY + 20, null);
            g.drawString("" + player.getName(), 30 + pawns.get(index).getWidth(null),
                    minY + 20 + pawns.get(index).getHeight(null) - g.getFontMetrics().getHeight());
            g.drawString("" + player.getRole().toString(), 30 + pawns.get(index).getWidth(null),
                    minY + 20 + pawns.get(index).getHeight(null));

            int space = (this.width - minY - 60) / 4;
            for (int i = 0; i < temples.size(); i++) {
                int y = minY + 100 + (space + temples.get(i).getHeight(null)) * i;
                g.drawImage(temples.get(i), 20, y, null);
                Card card = Card.getCardTemple(i);
                g.drawString("x " + contrPlayer.selectedPlayer.getCards(card), 30 + temples.get(i).getWidth(null),
                        y + temples.get(i).getHeight(null) / 2 + g.getFontMetrics().getAscent() / 2);
                if (contrPlayer.selectedCard == card) {
                    g.setColor(new Color(185, 5, 26));
                    g.fillOval(5, y + temples.get(i).getWidth(null) / 2 - 5, 10, 10);
                    g.setColor(colorT);
                }
            }
            g.drawImage(sandbag, this.width / 2, minY + 100 + (space + sandbag.getHeight(null)), null);
            g.drawString("x " + contrPlayer.selectedPlayer.getCards(Card.SAC), this.width / 2 + sandbag.getWidth(null),
                    minY + 100 + (space + sandbag.getHeight(null)) + sandbag.getHeight(null) / 2
                            + g.getFontMetrics().getAscent() / 2);

            g.drawImage(helicopter, this.width / 2, minY + 100 + (space + helicopter.getHeight(null)) * 2, null);
            g.drawString("x " + contrPlayer.selectedPlayer.getCards(Card.HELICOPTERE),
                    this.width / 2 + helicopter.getWidth(null),
                    minY + 100 + (space + helicopter.getHeight(null)) * 2 + helicopter.getHeight(null) / 2
                            + g.getFontMetrics().getAscent() / 2);
            if (Card.SAC == contrPlayer.selectedCard || Card.HELICOPTERE == contrPlayer.selectedCard) {
                int i = 0;
                if (Card.SAC == contrPlayer.selectedCard) {
                    i = 1;
                } else if (Card.HELICOPTERE == contrPlayer.selectedCard) {
                    i = 2;
                }
                if (i != 0) {
                    g.setColor(new Color(185, 5, 26));
                    g.fillOval(this.width / 2 - 10,
                            minY + 100 + (space + helicopter.getHeight(null)) * i + helicopter.getHeight(null) / 2 - 5,
                            10, 10);
                    g.setColor(colorT);
                }
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        if ((e.getY() >= 15 && e.getY() <= 15 + this.pawns.get(0).getHeight(null))
                && model.getActPlayer().getState() != Player.State.THROW) {
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
        } else if (e.getY() >= 145) {
            contrPlayer.selectedCard = null;
            int minY = 145;
            int space = (this.width - minY - 60) / 4;
            for (int i = 0; i < 4; i++) {
                int y = minY + 100 + (space + temples.get(i).getHeight(null)) * i;
                if (y <= e.getY() && y + temples.get(i).getHeight(null) >= e.getY() && e.getX() >= 20
                        && e.getX() <= 20 + temples.get(i).getWidth(null)) {
                    contrPlayer.cardClick(Card.getCardTemple(i));
                    return;
                }
            }
            if (e.getX() >= this.width / 2 && e.getX() <= this.width / 2 + sandbag.getWidth(null)
                    && e.getY() >= minY + 100 + space + sandbag.getHeight(null)
                    && e.getY() <= minY + 100 + space + sandbag.getHeight(null) + sandbag.getHeight(null)) {
                contrPlayer.cardClick(Card.SAC);
            } else if (e.getX() >= this.width / 2 && e.getX() <= this.width / 2 + helicopter.getWidth(null)
                    && e.getY() >= minY + 100 + (space + helicopter.getHeight(null)) * 2
                    && e.getY() <= minY + 100 + (space + helicopter.getHeight(null)) * 2 + helicopter.getHeight(null)) {
                contrPlayer.cardClick(Card.HELICOPTERE);
            }
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