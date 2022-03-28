package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import controllers.Controller;

/**
 * Window
 */
public class ViewSetup extends JFrame {
	private JSlider slider;

	private int widthSetup = 500;
	private int heightSetup = 400;

	private boolean isSetup;

	private ArrayList<String> names;

	/**
	 * 
	 */
	public ViewSetup() {
	}

	/**
	 * 
	 * @return isSetup
	 */
	public boolean isSetup() {
		return isSetup;
	}

	/**
	 * 
	 * @return ArrayList<String> names
	 */
	public ArrayList<String> getNames() {
		return names;
	}

	/**
	 * 
	 */
	public void drawWin() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * 
	 */
	public void setUpMenu(Controller controller) {
		isSetup = false;
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
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				names = new ArrayList<String>();
				for (int i = 0; i < slider.getValue(); i++) {
					names.add(texts.get(i).getText());
				}
				isSetup = true;
			}
		});
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
