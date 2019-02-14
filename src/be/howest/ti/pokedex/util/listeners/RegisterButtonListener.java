package be.howest.ti.pokedex.util.listeners;

import be.howest.ti.pokedex.controller.loginFrame.LoginFrameController;
import be.howest.ti.pokedex.data.Repositories;
import be.howest.ti.pokedex.gui.LoginFrame;
import be.howest.ti.pokedex.util.BCrypt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RegisterButtonListener implements ActionListener {
	private LoginFrameController loginFrameController;
	private LoginFrame loginFrame;

	public RegisterButtonListener(LoginFrameController loginFrameController, LoginFrame loginFrame) {
		this.loginFrameController = loginFrameController;
		this.loginFrame = loginFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = loginFrame.getUsernameRegisterTextField().getText();
		char[] password = loginFrame.getPasswordTextField().getPassword();
		char[] repeatedPassword = loginFrame.getPasswordRepeatTextfield().getPassword();
		String encryptedPassword = BCrypt.hashpw(Arrays.toString(password), BCrypt.gensalt());

		if (username != null && username.length() >= 4) {
			if ((password != null && repeatedPassword != null) && (password.length >= 4 && repeatedPassword.length >= 4)) {
				if (Repositories.trainerRepository.getByName(username) == null) {
					if (Arrays.equals(password, repeatedPassword)) {
						loginFrameController.getRegisterController().registerTrainer(username, encryptedPassword);
					} else loginFrame.getRegisterErrorLabel().setText("Passwords do not match.");
				} else loginFrame.getRegisterErrorLabel().setText("Username already registered.");
			} else loginFrame.getRegisterErrorLabel().setText("Passwords not given or to short.");
		} else loginFrame.getRegisterErrorLabel().setText("Username not given or to short.");
	}
}