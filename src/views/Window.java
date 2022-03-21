package views;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Window
 */
public class Window extends JFrame {

  private JPanel elements;

  public Window() {
    super("Ile Interdite");
    this.elements = new JPanel();
    this.add(elements);
  }

  public void drawWin() {
    this.pack();
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void addElem(JComponent element) {
    elements.add(element);
  }
}
