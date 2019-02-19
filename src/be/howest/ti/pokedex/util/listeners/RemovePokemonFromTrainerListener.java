package be.howest.ti.pokedex.util.listeners;

import be.howest.ti.pokedex.controller.mainFrame.TrainerProfilePanelController;
import be.howest.ti.pokedex.domain.Pokemon;
import be.howest.ti.pokedex.gui.PokeFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemovePokemonFromTrainerListener implements ActionListener {
	private PokeFrame pokeFrame;

	public RemovePokemonFromTrainerListener(TrainerProfilePanelController trainerProfilePanelController) {
		pokeFrame = trainerProfilePanelController.getPokeFrameController().getPokeFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Pokemon pokemon = (Pokemon) pokeFrame.getSeenPokemonList().getSelectedValue();
	}
}