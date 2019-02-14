package be.howest.ti.pokedex.util.listeners;

import be.howest.ti.pokedex.controller.mainFrame.TrainerProfilePanelController;
import be.howest.ti.pokedex.domain.Trainer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DecrementPokeballButtonListener implements ActionListener {

	private Trainer trainer;
	private TrainerProfilePanelController trainerProfilePanelController;

	public DecrementPokeballButtonListener(TrainerProfilePanelController trainerProfilePanelController, Trainer trainer) {
		this.trainer = trainer;
		this.trainerProfilePanelController = trainerProfilePanelController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		trainer.substractPokeball();
		trainerProfilePanelController.resetPokeballsAndButtonsTrainerPanel();
	}
}