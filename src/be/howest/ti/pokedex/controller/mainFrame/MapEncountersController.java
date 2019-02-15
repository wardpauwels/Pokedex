package be.howest.ti.pokedex.controller.mainFrame;

import be.howest.ti.pokedex.gui.components.WorldMapJPanel;
import be.howest.ti.pokedex.util.listeners.MapClickedListener;

public class MapEncountersController {
	private PokeFrameController pokeFrameController;
	private WorldMapJPanel worldMapJPanel;

	MapEncountersController(PokeFrameController pokeFrameController) {
		this.pokeFrameController = pokeFrameController;
		initVariables();
		initListeners();
	}

	private void initVariables() {
		this.worldMapJPanel = pokeFrameController.getPokeFrame().getWorldMapJPanel();
	}

	private void initListeners() {
		worldMapJPanel.addMouseListener(new MapClickedListener(this));
	}

	public PokeFrameController getPokeFrameController() {
		return pokeFrameController;
	}
}