package be.howest.ti.pokedex.controller.loginFrame;

import be.howest.ti.pokedex.controller.mainFrame.PokeFrameController;
import be.howest.ti.pokedex.domain.Trainer;
import be.howest.ti.pokedex.gui.LoginFrame;
import be.howest.ti.pokedex.util.listeners.LoginButtonListener;

import javax.swing.*;

public class LoginController {
	private JButton loginButton;

	private LoginFrame loginFrame;
	private LoginFrameController loginFrameController;

	LoginController(LoginFrame loginFrame, LoginFrameController loginFrameController) {
		this.loginFrame = loginFrame;
		this.loginFrameController = loginFrameController;

		initVariables();
		initListeners();
	}

	private void initVariables() {
		loginButton = loginFrame.getLoginButton();
	}

	private void initListeners() {
		loginButton.addActionListener(new LoginButtonListener(loginFrameController, loginFrame));
	}

	public void loginTrainer(Trainer trainer) {
		var pokeFrameController = new PokeFrameController(trainer);
		pokeFrameController.showPokeFrameWindow();
		loginFrame.dispose();
	}
}