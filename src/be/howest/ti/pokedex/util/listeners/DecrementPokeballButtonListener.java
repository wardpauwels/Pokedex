package be.howest.ti.pokedex.util.listeners;

import be.howest.ti.pokedex.controller.mainFrame.TrainerProfilePanelController;
import be.howest.ti.pokedex.domain.Trainer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DecrementPokeballButtonListener implements ActionListener {
	private TrainerProfilePanelController trainerProfilePanelController;
	private Trainer trainer;

	public DecrementPokeballButtonListener(TrainerProfilePanelController trainerProfilePanelController) {
		this.trainerProfilePanelController = trainerProfilePanelController;
		trainer = trainerProfilePanelController.getPokeFrameController().getTrainer();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		trainer.substractPokeball();
		trainerProfilePanelController.resetPokeballsAndButtonsTrainerPanel();
	}
}