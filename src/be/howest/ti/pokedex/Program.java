package be.howest.ti.pokedex;

import be.howest.ti.pokedex.client.PokeClient;
import be.howest.ti.pokedex.server.PokeServer;

public class Program {
	public static void main(String[] args) {
//		var loginFrameController = new LoginFrameController();
//		loginFrameController.showLoginFrameWindow();

		Thread s = new Thread(new PokeServer());
		s.start();
		Thread c = new Thread(new PokeClient());
		c.start();
	}
}