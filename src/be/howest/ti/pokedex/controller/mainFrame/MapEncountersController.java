package be.howest.ti.pokedex.controller.mainFrame;

import be.howest.ti.pokedex.gui.PokeFrame;
import be.howest.ti.pokedex.gui.components.WorldMapJPanel;
import be.howest.ti.pokedex.util.listeners.MapClickedListener;

class MapEncountersController {
	private PokeFrame pokeFrame;
	private PokeFrameController pokeFrameController;
	private WorldMapJPanel worldMapJPanel;

	MapEncountersController(PokeFrameController pokeFrameController, PokeFrame pokeFrame) {
		this.pokeFrame = pokeFrame;
		this.pokeFrameController = pokeFrameController;

		initVariables();
		initListeners();
	}

	private void initVariables() {
		this.worldMapJPanel = pokeFrame.getWorldMapJPanel();
	}

	private void initListeners() {
		worldMapJPanel.addMouseListener(new MapClickedListener(pokeFrameController));
	}
}