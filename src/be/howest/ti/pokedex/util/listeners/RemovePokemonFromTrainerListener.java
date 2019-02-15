package be.howest.ti.pokedex.util.listeners;

import be.howest.ti.pokedex.controller.mainFrame.TrainerProfilePanelController;
import be.howest.ti.pokedex.domain.Pokemon;
import be.howest.ti.pokedex.domain.Trainer;
import be.howest.ti.pokedex.gui.PokeFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemovePokemonFromTrainerListener implements ActionListener {
	private TrainerProfilePanelController trainerProfilePanelController;
	private PokeFrame pokeFrame;
	private Pokemon pokemon;
	private Trainer trainer;

	public RemovePokemonFromTrainerListener(TrainerProfilePanelController trainerProfilePanelController) {
		this.trainerProfilePanelController = trainerProfilePanelController;
		pokeFrame = trainerProfilePanelController.getPokeFrameController().getPokeFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		pokemon = (Pokemon) pokeFrame.getSeenPokemonList().getSelectedValue();
	}
}