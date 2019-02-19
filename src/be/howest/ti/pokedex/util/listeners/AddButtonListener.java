package be.howest.ti.pokedex.util.listeners;

import be.howest.ti.pokedex.controller.mainFrame.AddEncounterController;
import be.howest.ti.pokedex.controller.mainFrame.PokeFrameController;
import be.howest.ti.pokedex.domain.Pokemon;
import be.howest.ti.pokedex.gui.PokeFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonListener implements ActionListener {
	private PokeFrameController pokeFrameController;
	private PokeFrame pokeFrame;

	public AddButtonListener(AddEncounterController addEncounterController) {
		pokeFrameController = addEncounterController.getPokeFrameController();
		pokeFrame = pokeFrameController.getPokeFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Pokemon pokemon = (Pokemon) pokeFrame.getNewPokemonChooseComboBox()
				.getItemAt(pokeFrame.getNewPokemonChooseComboBox().getSelectedIndex());
		int x = Integer.parseInt(pokeFrame.getNewPokemonXPos().getText());
		int y = Integer.parseInt(pokeFrame.getNewPokemonYPos().getText());

		pokeFrameController.tryToAddEncounter(pokemon, x, y, true);
	}
}