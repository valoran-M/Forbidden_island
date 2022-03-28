package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * Window
 */
public class Window extends JFrame {

	private JPanel elements;

	/**
	 * 
	 */
	public Window() {
		super("Ile Interdite");
	}

	/**
	 * 
	 */
	public void drawWin() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * @param JComponent component
	 */
	public void addElem(JComponent element) {
		elements.add(element);
	}

	private JSlider slider;

	int widthSetup = 500;
	int heightSetup = 400;

	/**
	 * 
	 */
	public void setUpMenu() {
		elements = new JPanel(new SpringLayout());
		setSize(widthSetup, heightSetup);
		setTitle("Players Selection");

		// Add the elements for number of players
		JPanel nbPlayers = new JPanel();
		add(nbPlayers, BorderLayout.NORTH);

		slider = new JSlider(2, 4);
		slider.setValue(4);
		slider.setPaintTrack(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setMinorTickSpacing(1);
		slider.setLabelTable(slider.createStandardLabels(1));

		// Add the name input for players
		JPanel namePlayers = new JPanel(new GridBagLayout());
		ArrayList<JTextField> texts = new ArrayList<JTextField>();
		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		for (int i = 0; i < 4; i++) {
			JLabel label = new JLabel("Player " + (i + 1) + " name : ");
			JTextField text = new JTextField(10);
			labels.add(label);
			texts.add(text);

		}

		drawInName(labels, texts, namePlayers, slider.getValue());

		add(namePlayers, BorderLayout.CENTER);

		slider.addChangeListener(l -> {
			drawInName(labels, texts, namePlayers, slider.getValue());
		});

		JLabel playerNumber = new JLabel("Number of players : ");
		playerNumber.setLabelFor(slider);

		nbPlayers.add(playerNumber);
		nbPlayers.add(slider);

		// Start button
		JPanel StartB = new JPanel();

		add(StartB, BorderLayout.SOUTH);
		JButton start = new JButton("Start");
		StartB.add(start);

	}

	private void drawInName(ArrayList<JLabel> labels, ArrayList<JTextField> texts, JPanel namePlayers, int nb) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		namePlayers.removeAll();
		for (int i = 0; i < nb; i++) {
			constraints.gridx = 0;
			constraints.gridy = i;
			namePlayers.add(labels.get(i), constraints);
			constraints.gridx = 1;
			namePlayers.add(texts.get(i), constraints);
		}
		namePlayers.repaint();
	}
}
