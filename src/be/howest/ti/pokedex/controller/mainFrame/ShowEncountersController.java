package be.howest.ti.pokedex.controller.mainFrame;

import be.howest.ti.pokedex.data.Repositories;
import be.howest.ti.pokedex.domain.Encounter;
import be.howest.ti.pokedex.gui.PokeFrame;

import javax.swing.*;

class ShowEncountersController {
	private PokeFrameController pokeFrameController;
	private DefaultListModel<Encounter> encountersModel;
	private JList<Encounter> encounterList;

	ShowEncountersController(PokeFrameController pokeFrameController) {
		this.pokeFrameController = pokeFrameController;
		initVariables();
		initComponents();
	}

	private void initVariables() {
		PokeFrame pokeFrame = pokeFrameController.getPokeFrame();
		encounterList = pokeFrame.getEncounterList();
	}

	private void initComponents() {
		encounterList.setModel(getEncountersModel());
	}

	DefaultListModel<Encounter> getEncountersModel() {
		if (encountersModel == null) {
			encountersModel = new DefaultListModel<>();
			for (Encounter encounter : Repositories.encounterRepository.getAll()) {
				encountersModel.addElement(encounter);
			}
		}
		return encountersModel;
	}
}