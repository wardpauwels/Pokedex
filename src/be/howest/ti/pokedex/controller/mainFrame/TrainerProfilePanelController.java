package be.howest.ti.pokedex.controller.mainFrame;

import be.howest.ti.pokedex.data.Repositories;
import be.howest.ti.pokedex.domain.Pokemon;
import be.howest.ti.pokedex.domain.Trainer;
import be.howest.ti.pokedex.gui.PokeFrame;
import be.howest.ti.pokedex.util.listeners.DecrementPokeballButtonListener;
import be.howest.ti.pokedex.util.listeners.IncrementPokeballButtonListener;

import javax.swing.*;

public class TrainerProfilePanelController {
	private JButton decrementPokeballButton;
	private JButton incrementPokeballButton;
	private JList seenPokemonList;
	private JLabel trainerNameLabel;
	private JLabel numberOfPokeballsLabel;

	private Trainer trainer;
	private PokeFrame pokeFrame;
	private DefaultListModel<Pokemon> seenPokemonModel = new DefaultListModel<>();

	TrainerProfilePanelController(Trainer trainer, PokeFrame pokeFrame) {
		this.trainer = trainer;
		this.pokeFrame = pokeFrame;
		initVariables();
		initListeners();
		loadTrainerDetails();
	}

	private void initVariables() {
		trainerNameLabel = pokeFrame.getTrainerNameLabel();
		numberOfPokeballsLabel = pokeFrame.getNumberOfPokeballsLabel();
		seenPokemonList = pokeFrame.getSeenPokemonList();
		decrementPokeballButton = pokeFrame.getDecrementPokeballButton();
		incrementPokeballButton = pokeFrame.getIncrementPokeballButton();
	}

	private void initListeners() {
		decrementPokeballButton.addActionListener(new DecrementPokeballButtonListener(this, trainer));
		incrementPokeballButton.addActionListener(new IncrementPokeballButtonListener(this, trainer));
	}

	private void loadTrainerDetails() {
		trainerNameLabel.setText(trainer.getName());
		numberOfPokeballsLabel.setText(String.valueOf(trainer.getNumberOfPokeballs()));
		seenPokemonList.setModel(updateSeenPokemonModel());
	}

	public void resetPokeballsAndButtonsTrainerPanel() {
		Repositories.trainerRepository.save(trainer);
		numberOfPokeballsLabel.setText(String.valueOf(trainer.getNumberOfPokeballs()));
		decrementPokeballButton.setEnabled(trainer.getNumberOfPokeballs() > 0);
	}

	public ListModel<Pokemon> updateSeenPokemonModel() {
		seenPokemonModel.removeAllElements();
		for (Pokemon pokemon : trainer.getPokemons()) {
			seenPokemonModel.addElement(pokemon);
		}
		return seenPokemonModel;
	}
}