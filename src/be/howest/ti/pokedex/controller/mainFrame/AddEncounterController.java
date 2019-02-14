package be.howest.ti.pokedex.controller.mainFrame;

import be.howest.ti.pokedex.data.Repositories;
import be.howest.ti.pokedex.domain.Pokemon;
import be.howest.ti.pokedex.domain.Trainer;
import be.howest.ti.pokedex.gui.PokeFrame;
import be.howest.ti.pokedex.util.listeners.AddButtonListener;

import javax.swing.*;

class AddEncounterController {

	private Trainer trainer;
	private PokeFrame pokeFrame;
	private PokeFrameController pokeFrameController;

	private JButton addButton;
	private JComboBox<Pokemon> newPokemonChooseComboBox;

	AddEncounterController(PokeFrameController pokeFrameController, Trainer trainer, PokeFrame pokeFrame) {
		this.pokeFrameController = pokeFrameController;
		this.trainer = trainer;
		this.pokeFrame = pokeFrame;
		initVariables();
		initListeners();
		initComponents();
	}

	private void initVariables() {
		addButton = pokeFrame.getAddButton();
		newPokemonChooseComboBox = pokeFrame.getNewPokemonChooseComboBox();
	}

	private void initListeners() {
		addButton.addActionListener(new AddButtonListener(pokeFrameController, pokeFrame));
	}

	private void initComponents() {
		newPokemonChooseComboBox.setModel(getPokemonComboBoxModel());
	}

	private ComboBoxModel<Pokemon> getPokemonComboBoxModel() {
		DefaultComboBoxModel<Pokemon> model = new DefaultComboBoxModel<>();
		for (Pokemon pokemon : Repositories.pokemonRepository.getAll()) {
			model.addElement(pokemon);
		}
		return model;
	}

	void resetWindow() {
		pokeFrame.getNewPokemonXPos().setText("0");
		pokeFrame.getNewPokemonYPos().setText("0");
		pokeFrame.getNewPokemonChooseComboBox().setSelectedIndex(0);
	}
}