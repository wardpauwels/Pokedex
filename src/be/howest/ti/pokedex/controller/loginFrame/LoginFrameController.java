package be.howest.ti.pokedex.controller.loginFrame;

import be.howest.ti.pokedex.client.PokeClient;
import be.howest.ti.pokedex.gui.LoginFrame;
import be.howest.ti.pokedex.messages.Message;

public class LoginFrameController {
	private PokeClient pokeClient;
	private LoginFrame loginFrame;
	private LoginController loginController;
	private RegisterController registerController;

	public LoginFrameController(PokeClient pokeClient) {
		this.pokeClient = pokeClient;
		initVariables();
	}

	public static LoginFrameController run(PokeClient pokeClient) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(LoginFrameController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(LoginFrameController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(LoginFrameController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
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

	public void trainerRegistered(Message req) {
		registerController.trainerAddedResetFields();
	}
}