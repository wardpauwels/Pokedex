package be.howest.ti.pokedex.util.listeners;

import be.howest.ti.pokedex.controller.mainFrame.TrainerProfilePanelController;
import be.howest.ti.pokedex.domain.Trainer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncrementPokeballButtonListener implements ActionListener {

	private Trainer trainer;
	private TrainerProfilePanelController trainerProfilePanelController;

	public IncrementPokeballButtonListener(TrainerProfilePanelController trainerProfilePanelController, Trainer trainer) {
		this.trainer = trainer;
		this.trainerProfilePanelController = trainerProfilePanelController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		trainer.addPokeball();
		trainerProfilePanelController.resetPokeballsAndButtonsTrainerPanel();
	}
}