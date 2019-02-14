package be.howest.ti.pokedex;

import be.howest.ti.pokedex.controller.loginFrame.LoginFrameController;

public class Program {
	public static void main(String[] args) {
		var loginFrameController = new LoginFrameController();
		loginFrameController.showLoginFrameWindow();
	}
}