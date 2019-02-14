package be.howest.ti.pokedex.controller.loginFrame;

import be.howest.ti.pokedex.gui.LoginFrame;

public class LoginFrameController {
	private LoginFrame loginFrame;

	private LoginController loginController;
	private RegisterController registerController;

	public LoginFrameController() {
		initVariables();
	}

	private void initVariables() {
		loginFrame = new LoginFrame();
		loginController = new LoginController(loginFrame, this);
		registerController = new RegisterController(loginFrame, this);
	}

	public void showLoginFrameWindow() {
		loginFrame.setVisible(true);
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public RegisterController getRegisterController() {
		return registerController;
	}
}