package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import controllers.ContrSetup;
import models.Model;

/**
 * Window
 */
public class ViewSetup extends JPanel {
	private ContrSetup control;

	private JSlider slider;
	public JSlider levelSlider;

	private ArrayList<String> names;
	private ArrayList<JLabel> labels;
	private ArrayList<JTextField> texts;
	private JPanel namePlayers;

	/**
	 * 
	 */
	public ViewSetup(Model model, View view) {
		this.control = new ContrSetup(model, view);
		setUpMenu();
	}

	/**
	 * 
	 */
	private void setUpMenu() {

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
		namePlayers = new JPanel(new GridBagLayout());
		texts = new ArrayList<JTextField>();
		labels = new ArrayList<JLabel>();
		for (int i = 0; i < 4; i++) {
			JLabel label = new JLabel("Player " + (i + 1) + " name : ");
			JTextField text = new JTextField(10);
			labels.add(label);
			texts.add(text);

		}

		drawInName(slider.getValue());

		add(namePlayers, BorderLayout.CENTER);

		slider.addChangeListener(l -> {
			drawInName(slider.getValue());
		});

		JLabel playerNumber = new JLabel("Number of players : ");
		playerNumber.setLabelFor(slider);

		nbPlayers.add(playerNumber);
		nbPlayers.add(slider);

		// Start button
		JPanel StartB = new JPanel();

		JButton start = new JButton("Start");
		start.addActionListener(control);
		StartB.add(start);
		add(StartB, BorderLayout.CENTER);

		// Level
		JPanel level = new JPanel();
		
		this.levelSlider = new JSlider(1, 4);
		levelSlider.setValue(1);
		levelSlider.setPaintTrack(true);
		levelSlider.setPaintTicks(true);
		levelSlider.setPaintLabels(true);
		levelSlider.setSnapToTicks(true);
		levelSlider.setMinorTickSpacing(1);
		levelSlider.setLabelTable(levelSlider.createStandardLabels(1));

		JLabel difficultyLabel = new JLabel("Difficulty : ");
		difficultyLabel.setLabelFor(levelSlider);
		level.add(difficultyLabel);
		level.add(this.levelSlider);

		add(level);

	}

	private void drawInName(int nb) {
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

	public ArrayList<String> getNamePlayer() {
		names = new ArrayList<String>();
		for (int i = 0; i < slider.getValue(); i++) {
			if (texts.get(i).getText().isEmpty()) {
				texts.get(i).setText("Player " + (i + 1));
			}
			names.add(texts.get(i).getText().toString());
		}
		return names;
	}
}
