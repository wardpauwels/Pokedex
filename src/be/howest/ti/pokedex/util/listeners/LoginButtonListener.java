package be.howest.ti.pokedex.util.listeners;

import be.howest.ti.pokedex.client.PokeClient;
import be.howest.ti.pokedex.controller.loginFrame.LoginController;
import be.howest.ti.pokedex.domain.Trainer;
import be.howest.ti.pokedex.gui.LoginFrame;
import be.howest.ti.pokedex.util.BCrypt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginButtonListener implements ActionListener {
	private PokeClient pokeClient;
	private LoginController loginController;
	private LoginFrame loginFrame;
	private volatile boolean trainerReceived;
	private Trainer trainer;

	public LoginButtonListener(LoginController loginController) {
		this.loginController = loginController;
		pokeClient = loginController.getLoginFrameController().getPokeClient();
		loginFrame = loginController.getLoginFrameController().getLoginFrame();
		trainerReceived = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = loginFrame.getUsernameLoginTextField().getText();
		char[] password = loginFrame.getPasswordLoginTextField().getPassword();
		getTrainerByUsername(username);

		if (username != null && username.length() >= 4) {
			if (trainer != null) {
				if (password != null && password.length >= 4) {
					if (BCrypt.checkpw(Arrays.toString(password), trainer.getPassword())) {
						loginController.loginTrainer(trainer);
					}
				} else
					loginFrame.getLoginErrorLabel().setText("No password given or password too short.");
			} else
				loginFrame.getLoginErrorLabel().setText("Trainer not found.");
		} else
			loginFrame.getLoginErrorLabel().setText("Username not given or too short.");
	}

	private void getTrainerByUsername(String username) {
		pokeClient.getTrainerByUsername(username);
		while (!trainerReceived) {
			Thread.onSpinWait();
		}
		trainerReceived = false;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public void setTrainerReceived(boolean trainerReceived) {
		this.trainerReceived = trainerReceived;
	}
}