package be.howest.ti.pokedex.util.listeners;

import be.howest.ti.pokedex.controller.loginFrame.LoginFrameController;
import be.howest.ti.pokedex.data.Repositories;
import be.howest.ti.pokedex.domain.Trainer;
import be.howest.ti.pokedex.gui.LoginFrame;
import be.howest.ti.pokedex.util.BCrypt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginButtonListener implements ActionListener {
	private LoginFrameController loginFrameController;
	private LoginFrame loginFrame;

	public LoginButtonListener(LoginFrameController loginFrameController, LoginFrame loginFrame) {
		this.loginFrameController = loginFrameController;
		this.loginFrame = loginFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = loginFrame.getUsernameLoginTextField().getText();
		char[] password = loginFrame.getPasswordLoginTextField().getPassword();

		if (username != null && username.length() >= 4) {
			Trainer trainer = Repositories.trainerRepository.getByName(username);
			if (trainer != null) {
				if (password != null && password.length >= 4) {
					if (BCrypt.checkpw(Arrays.toString(password), trainer.getPassword())) {
						loginFrameController.getLoginController().loginTrainer(trainer);
					}
				} else loginFrame.getLoginErrorLabel().setText("No password given or password too short.");
			} else loginFrame.getLoginErrorLabel().setText("Trainer not found.");
		} else loginFrame.getLoginErrorLabel().setText("Username not given or to short.");
	}
}