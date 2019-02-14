package be.howest.ti.pokedex.controller.mainFrame;

import be.howest.ti.pokedex.data.Repositories;
import be.howest.ti.pokedex.domain.Encounter;
import be.howest.ti.pokedex.gui.PokeFrame;

import javax.swing.*;

public class ShowEncountersController {

	public DefaultListModel<Encounter> encountersModel;
	private PokeFrame pokeFrame;
	private JList<Encounter> encounterList;

	ShowEncountersController(PokeFrame pokeFrame) {
		this.pokeFrame = pokeFrame;
		initVariables();
		initComponents();
	}

	private void initVariables() {
		encounterList = pokeFrame.getEncounterList();
	}

	private void initComponents() {
		encounterList.setModel(getEncountersModel());
	}

	private ListModel<Encounter> getEncountersModel() {
		if (encountersModel == null) {
			encountersModel = new DefaultListModel<>();
			for (Encounter encounter : Repositories.encounterRepository.getAll()) {
				encountersModel.addElement(encounter);
			}
		}
		return encountersModel;
	}
}