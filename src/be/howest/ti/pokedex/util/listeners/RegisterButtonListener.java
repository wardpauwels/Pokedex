package be.howest.ti.pokedex.util.listeners;

import be.howest.ti.pokedex.client.PokeClient;
import be.howest.ti.pokedex.controller.loginFrame.RegisterController;
import be.howest.ti.pokedex.gui.LoginFrame;
import be.howest.ti.pokedex.util.BCrypt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RegisterButtonListener implements ActionListener {
	private LoginFrame loginFrame;
	private PokeClient pokeClient;

	private boolean doesTrainerExist;
	private volatile boolean trainerChecked;

	public RegisterButtonListener(RegisterController registerController) {
		loginFrame = registerController.getLoginFrameController().getLoginFrame();
		pokeClient = registerController.getLoginFrameController().getPokeClient();
		doesTrainerExist = false;
		trainerChecked = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = loginFrame.getUsernameRegisterTextField().getText();
		char[] password = loginFrame.getPasswordTextField().getPassword();
		char[] repeatedPassword = loginFrame.getPasswordRepeatTextfield().getPassword();
		String encryptedPassword = BCrypt.hashpw(Arrays.toString(password), BCrypt.gensalt());

		if (username != null && username.length() >= 4) {
			if ((password != null && repeatedPassword != null) && (password.length >= 4 && repeatedPassword.length >= 4)) {
				if (!checkIfTrainerExists(username)) {
					if (Arrays.equals(password, repeatedPassword)) {
						pokeClient.addTrainer(username, encryptedPassword);
					} else
						loginFrame.getRegisterErrorLabel().setText("Passwords do not match.");
				} else
					loginFrame.getRegisterErrorLabel().setText("Username already registered.");
			} else
				loginFrame.getRegisterErrorLabel().setText("Passwords not given or to short.");
		} else
			loginFrame.getRegisterErrorLabel().setText("Username not given or to short.");
	}

	public boolean checkIfTrainerExists(String username) {
		doesTrainerExist = false;
		pokeClient.checkIfTrainerDoesExist(username);

		while (!trainerChecked) {
			Thread.onSpinWait();
		}
		trainerChecked = false;
		return doesTrainerExist;
	}

	public void setDoesTrainerExist(boolean doesTrainerExist) {
		this.doesTrainerExist = doesTrainerExist;
	}

	public void trainerChecked() {
		trainerChecked = true;
	}
}