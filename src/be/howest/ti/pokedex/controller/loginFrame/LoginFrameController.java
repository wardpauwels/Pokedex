package be.howest.ti.pokedex.controller.loginFrame;

import be.howest.ti.pokedex.client.PokeClient;
import be.howest.ti.pokedex.gui.LoginFrame;

import javax.swing.*;

public class LoginFrameController {
	private PokeClient pokeClient;
	private LoginFrame loginFrame;
	private LoginController loginController;
	private RegisterController registerController;

	private LoginFrameController(PokeClient pokeClient) {
		this.pokeClient = pokeClient;
		initVariables();
	}

	public static LoginFrameController run(PokeClient pokeClient) {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(LoginFrameController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		return new LoginFrameController(pokeClient);
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

	private void initVariables() {
		loginFrame = new LoginFrame();
		loginController = new LoginController(this);
		registerController = new RegisterController(this);
	}

	public LoginFrame getLoginFrame() {
		return loginFrame;
	}

	public PokeClient getPokeClient() {
		return pokeClient;
	}

	public void trainerRegistered() {
		registerController.trainerAddedResetFields();
	}
}