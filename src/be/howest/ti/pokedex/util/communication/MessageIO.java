package be.howest.ti.pokedex.util.communication;


import be.howest.ti.pokedex.client.PokeClient;
import be.howest.ti.pokedex.messages.ClosedSocket;
import be.howest.ti.pokedex.messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageIO {

	ObjectInputStream in;
	ObjectOutputStream out;
	Socket socket;

	public MessageIO(Socket socket) {
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			this.socket = socket;
		} catch (IOException ex) {
			Logger.getLogger(PokeClient.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void send(Message msg) {
		try {
			out.writeObject(msg);
		} catch (IOException ex) {
			Logger.getLogger(MessageIO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public Message read() {
		if (!socket.isClosed()) {
			try {
				return (Message) in.readObject();
			} catch (IOException | ClassNotFoundException ex) {
				Logger.getLogger(MessageIO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return new ClosedSocket("Connection closed");
	}

	public boolean isClosed() {
		return socket.isClosed();
	}

	public void close() {
		try {
			socket.close();
		} catch (IOException ex) {
			Logger.getLogger(MessageIO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}