package be.howest.ti.pokedex.gui;

import be.howest.ti.pokedex.domain.Encounter;
import be.howest.ti.pokedex.gui.components.WorldMapJPanel;

import javax.swing.*;
import java.util.List;

public class PokeFrame extends JFrame {
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;

	private JLabel pokemonHeadLabel;
	private JPanel mainPanel;
	private JTextField newPokemonXPos;
	private JTextField newPokemonYPos;
	private JButton addButton;
	private JComboBox newPokemonChooseComboBox;
	private JLabel errorLabel;
	private JList encounterList;
	private JLabel trainerNameLabel;
	private JLabel numberOfPokeballsLabel;
	private JList seenPokemonList;
	private JButton decrementPokeballButton;
	private JButton incrementPokeballButton;
	private WorldMapJPanel worldMapJPanel;
	private JPanel addEncounterPanel;
	private JPanel showEncountersPanel;
	private JPanel mapEncountersPanel;
	private JPanel trainerProfilePanel;
	private JTabbedPane mainTabbedPanel;
	private JLabel newPokemonChooseLabel;
	private JLabel locationNewPokemonLabel;
	private JButton pokemonLostButton;

	private List<Encounter> encounters;

	public PokeFrame(List<Encounter> encounters) {
		this.encounters = encounters;
		setTitle("Pokedex");
		setSize(WIDTH, HEIGHT);
		setContentPane(mainPanel);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public JLabel getPokemonHeadLabel() {
		return pokemonHeadLabel;
	}

	public JComboBox getNewPokemonChooseComboBox() {
		return newPokemonChooseComboBox;
	}

	public JTextField getNewPokemonXPos() {
		return newPokemonXPos;
	}

	public JTextField getNewPokemonYPos() {
		return newPokemonYPos;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JLabel getErrorLabel() {
		return errorLabel;
	}

	public JList getEncounterList() {
		return encounterList;
	}

	public JLabel getTrainerNameLabel() {
		return trainerNameLabel;
	}

	public JLabel getNumberOfPokeballsLabel() {
		return numberOfPokeballsLabel;
	}

	public JList getSeenPokemonList() {
		return seenPokemonList;
	}

	public JButton getDecrementPokeballButton() {
		return decrementPokeballButton;
	}

	public JButton getIncrementPokeballButton() {
		return incrementPokeballButton;
	}

	public WorldMapJPanel getWorldMapJPanel() {
		return worldMapJPanel;
	}

	private void createUIComponents() {
		worldMapJPanel = new WorldMapJPanel(encounters);
	}
}
