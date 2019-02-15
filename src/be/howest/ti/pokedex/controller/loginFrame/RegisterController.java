package be.howest.ti.pokedex.controller.loginFrame;

import be.howest.ti.pokedex.gui.LoginFrame;
import be.howest.ti.pokedex.util.listeners.RegisterButtonListener;

import javax.swing.*;

public class RegisterController {
	private JTextField usernameRegisterTextField;
	private JPasswordField passwordTextField;
	private JPasswordField passwordRepeatTextfield;
	private JButton registerButton;
	private JLabel registerErrorLabel;

	private LoginFrameController loginFrameController;
	private LoginFrame loginFrame;

	private RegisterButtonListener registerButtonListener;

	RegisterController(LoginFrameController loginFrameController) {
		this.loginFrameController = loginFrameController;

		initVariables();
		initListeners();
	}

	private void initVariables() {
		this.loginFrame = loginFrameController.getLoginFrame();

		usernameRegisterTextField = loginFrame.getUsernameRegisterTextField();
		passwordTextField = loginFrame.getPasswordTextField();
		passwordRepeatTextfield = loginFrame.getPasswordRepeatTextfield();
		registerButton = loginFrame.getRegisterButton();
		registerErrorLabel = loginFrame.getRegisterErrorLabel();

		registerButtonListener = new RegisterButtonListener(this);
	}

	private void initListeners() {
		registerButton.addActionListener(registerButtonListener);
	}

	public void trainerAddedResetFields() {
		registerErrorLabel.setText("New trainer succesfully registered!");
		usernameRegisterTextField.setText("");
		passwordTextField.setText("");
		passwordRepeatTextfield.setText("");
	}

	public LoginFrameController getLoginFrameController() {
		return loginFrameController;
	}

	public RegisterButtonListener getRegisterButtonListener() {
		return registerButtonListener;
	}
}