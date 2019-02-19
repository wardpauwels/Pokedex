package be.howest.ti.pokedex.util.listeners;

import be.howest.ti.pokedex.controller.mainFrame.MapEncountersController;
import be.howest.ti.pokedex.controller.mainFrame.PokeFrameController;
import be.howest.ti.pokedex.data.Repositories;
import be.howest.ti.pokedex.domain.Pokemon;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class MapClickedListener implements MouseListener {
	private PokeFrameController pokeFrameController;

	public MapClickedListener(MapEncountersController mapEncountersController) {
		pokeFrameController = mapEncountersController.getPokeFrameController();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		pokeFrameController.tryToAddEncounter(openPokemonChooseWindow(), x, y, false);
	}

	private Pokemon openPokemonChooseWindow() {
		List<Pokemon> pokemons = Repositories.pokemonRepository.getAll();
		return (Pokemon) JOptionPane.showInputDialog(null, "What Pokemon did you see?", "New Encounter",
				JOptionPane.QUESTION_MESSAGE, null, pokemons.toArray(), null);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}