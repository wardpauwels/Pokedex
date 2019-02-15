package be.howest.ti.pokedex.server;

import be.howest.ti.pokedex.util.StringConstants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PokeServer implements Runnable {

	private ServerSocket serverSocket;
	private int connections;

	public PokeServer() {
		connections = 0;
		try {
			serverSocket = new ServerSocket(StringConstants.SERVER_PORT);

		} catch (IOException ex) {
			Logger.getLogger(PokeServer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] args) {
		Thread s = new Thread(new PokeServer());
		s.start();
	}

	public void run() {
		System.out.println("Server started and waiting for connections.");
		while (!serverSocket.isClosed()) {
			try {
				Socket socket = serverSocket.accept();
				System.out.println("Server got new connection");
				ServerConnection serverConnection = new ServerConnection(socket, this, connections + 1);
				new Thread(serverConnection).start();
				connections++;
			} catch (IOException ex) {
				Logger.getLogger(PokeServer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}