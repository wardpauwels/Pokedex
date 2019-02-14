package be.howest.ti.pokedex.controller.mainFrame;

import be.howest.ti.pokedex.data.Repositories;
import be.howest.ti.pokedex.domain.Encounter;
import be.howest.ti.pokedex.domain.Pokemon;
import be.howest.ti.pokedex.domain.Trainer;
import be.howest.ti.pokedex.gui.PokeFrame;
import be.howest.ti.pokedex.util.StringConstants;
import be.howest.ti.pokedex.util.factory.EncounterFactory;

import javax.swing.*;

public class PokeFrameController {
	private PokeFrame pokeFrame;
	private JLabel pokemonHeadLabel;

	private Trainer trainer;

	private AddEncounterController addEncounterController;
	private ShowEncountersController showEncountersController;
	private TrainerProfilePanelController trainerProfilePanelController;

	public PokeFrameController(Trainer trainer) {
		initVariables(trainer);
		loadTrainerEncounters();
		addEncounterController = new AddEncounterController(this, trainer, pokeFrame);
		showEncountersController = new ShowEncountersController(pokeFrame);
		new MapEncountersController(this, pokeFrame);
		trainerProfilePanelController = new TrainerProfilePanelController(trainer, pokeFrame);
		loadHeadBarText();
		trainerProfilePanelController.resetPokeballsAndButtonsTrainerPanel();
	}

	public void showPokeFrameWindow() {
		pokeFrame.setVisible(true);
	}

	private void initVariables(Trainer trainer) {
		this.pokeFrame = new PokeFrame(Repositories.encounterRepository.getAll());
		this.trainer = trainer;
		this.pokemonHeadLabel = pokeFrame.getPokemonHeadLabel();
	}

	private void loadTrainerEncounters() {
		for (Encounter encounter : Repositories.encounterRepository.getByTrainer(trainer)) {
			trainer.addPokemon(encounter.getPokemon());
		}
	}

	private void loadHeadBarText() {
		pokemonHeadLabel.setText("Welcome to the pokedex, " + trainer.getName());
	}

	private ShowEncountersController getShowEncountersController() {
		return showEncountersController;
	}

	private TrainerProfilePanelController getTrainerProfilePanelController() {
		return trainerProfilePanelController;
	}

	public void tryToAddEncounter(Pokemon pokemon, int x, int y, boolean addEncounterWindow){
		if (trainer.getNumberOfPokeballs() > 0) {
			try {
				Encounter encounter = new EncounterFactory(x, y, trainer, pokemon).getEncounter();
				getShowEncountersController().encountersModel.addElement(encounter);
				getTrainerProfilePanelController().resetPokeballsAndButtonsTrainerPanel();
				getTrainerProfilePanelController().updateSeenPokemonModel();

				if (addEncounterWindow){
					pokeFrame.getErrorLabel().setText("Encounter Added!");
					addEncounterController.resetWindow();
				}
			} catch (NumberFormatException ex) {
				pokeFrame.getErrorLabel().setText(StringConstants.USE_INTS_FOR_LOCATION);
			}
		} else {
			JOptionPane.showMessageDialog(pokeFrame, StringConstants.ADD_POKEBALLS, StringConstants.NO_POKEBALLS, JOptionPane.ERROR_MESSAGE);
		}
	}
}