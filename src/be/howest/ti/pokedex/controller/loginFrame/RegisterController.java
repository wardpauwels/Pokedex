package be.howest.ti.pokedex.controller.loginFrame;

import be.howest.ti.pokedex.gui.LoginFrame;
import be.howest.ti.pokedex.util.factory.TrainerFactory;
import be.howest.ti.pokedex.util.listeners.RegisterButtonListener;

import javax.swing.*;

public class RegisterController {
	private JTextField usernameRegisterTextField;
	private JPasswordField passwordTextField;
	private JPasswordField passwordRepeatTextfield;
	private JButton registerButton;
	private JLabel registerErrorLabel;

	private LoginFrame loginFrame;
	private LoginFrameController loginFrameController;

	RegisterController(LoginFrame loginFrame, LoginFrameController loginFrameController) {
		this.loginFrame = loginFrame;
		this.loginFrameController = loginFrameController;

		initVariables();
		initListeners();
	}

	private void initVariables() {
		usernameRegisterTextField = loginFrame.getUsernameRegisterTextField();
		passwordTextField = loginFrame.getPasswordTextField();
		passwordRepeatTextfield = loginFrame.getPasswordRepeatTextfield();
		registerButton = loginFrame.getRegisterButton();
		registerErrorLabel = loginFrame.getRegisterErrorLabel();
	}

	private void initListeners() {
		registerButton.addActionListener(new RegisterButtonListener(loginFrameController, loginFrame));
	}

	public void registerTrainer(String username, String encryptedPassword) {
		new TrainerFactory(username, encryptedPassword, 5);
		registerErrorLabel.setText("New trainer succesfully registered!");
		resetRegisterFields();
	}

	private void resetRegisterFields() {
		usernameRegisterTextField.setText("");
		passwordTextField.setText("");
		passwordRepeatTextfield.setText("");
	}
}