package be.howest.ti.pokedex.controller.loginFrame;

import be.howest.ti.pokedex.controller.mainFrame.PokeFrameController;
import be.howest.ti.pokedex.domain.Trainer;
import be.howest.ti.pokedex.gui.LoginFrame;
import be.howest.ti.pokedex.util.listeners.LoginButtonListener;

import javax.swing.*;

public class LoginController {
	private JButton loginButton;
	private LoginFrameController loginFrameController;
	private LoginFrame loginFrame;
	private LoginButtonListener loginButtonListener;

	LoginController(LoginFrameController loginFrameController) {
		this.loginFrameController = loginFrameController;

		initVariables();
		initListeners();
	}

	private void initVariables() {
		loginFrame = loginFrameController.getLoginFrame();
		loginButton = loginFrame.getLoginButton();

		loginButtonListener = new LoginButtonListener(this);
	}

	private void initListeners() {
		loginButton.addActionListener(loginButtonListener);
	}

	public void loginTrainer(Trainer trainer) {
		var pokeFrameController = new PokeFrameController(trainer);
		pokeFrameController.showPokeFrameWindow();
		loginFrame.dispose();
	}

	public LoginFrameController getLoginFrameController() {
		return loginFrameController;
	}
}