package be.howest.ti.pokedex.controller.mainFrame;

import be.howest.ti.pokedex.data.Repositories;
import be.howest.ti.pokedex.domain.Pokemon;
import be.howest.ti.pokedex.domain.Trainer;
import be.howest.ti.pokedex.gui.PokeFrame;
import be.howest.ti.pokedex.util.listeners.DecrementPokeballButtonListener;
import be.howest.ti.pokedex.util.listeners.IncrementPokeballButtonListener;
import be.howest.ti.pokedex.util.listeners.RemovePokemonFromTrainerListener;

import javax.swing.*;

public class TrainerProfilePanelController {
	private JButton decrementPokeballButton;
	private JButton incrementPokeballButton;
	private JList seenPokemonList;
	private JLabel trainerNameLabel;
	private JLabel numberOfPokeballsLabel;
	private JButton pokemonLostButton;

	private PokeFrameController pokeFrameController;
	private Trainer trainer;
	private DefaultListModel<Pokemon> seenPokemonModel = new DefaultListModel<>();

	TrainerProfilePanelController(PokeFrameController pokeFrameController) {
		this.pokeFrameController = pokeFrameController;
		initVariables();
		initListeners();
		loadTrainerDetails();
	}

	private void initVariables() {
		PokeFrame pokeFrame = pokeFrameController.getPokeFrame();
		trainer = pokeFrameController.getTrainer();
		trainerNameLabel = pokeFrame.getTrainerNameLabel();
		numberOfPokeballsLabel = pokeFrame.getNumberOfPokeballsLabel();
		seenPokemonList = pokeFrame.getSeenPokemonList();
		decrementPokeballButton = pokeFrame.getDecrementPokeballButton();
		incrementPokeballButton = pokeFrame.getIncrementPokeballButton();
		pokemonLostButton = pokeFrame.getPokemonLostButton();
	}

	private void initListeners() {
		decrementPokeballButton.addActionListener(new DecrementPokeballButtonListener(this));
		incrementPokeballButton.addActionListener(new IncrementPokeballButtonListener(this));
		pokemonLostButton.addActionListener(new RemovePokemonFromTrainerListener(this));
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

	ListModel<Pokemon> updateSeenPokemonModel() {
		seenPokemonModel.removeAllElements();
		for (Pokemon pokemon : trainer.getPokemons()) {
			seenPokemonModel.addElement(pokemon);
		}
		return seenPokemonModel;
	}

	public PokeFrameController getPokeFrameController() {
		return pokeFrameController;
	}
}