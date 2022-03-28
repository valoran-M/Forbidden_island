package views;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * ViewGame
 */
public class ViewGame extends JFrame {
    public ViewGame() {
        super("Ile interdite");
    }

    public void drawWin() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void closeWin(){
        setVisible(false);
        dispose();
    }

    public void addElem(JComponent element) {
        add(element);
    }
}