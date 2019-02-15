package be.howest.ti.pokedex.client;

import be.howest.ti.pokedex.controller.loginFrame.LoginFrameController;
import be.howest.ti.pokedex.messages.*;
import be.howest.ti.pokedex.util.StringConstants;
import be.howest.ti.pokedex.util.communication.MessageIO;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PokeClient implements Runnable, MessageHandler {

	private final LoginFrameController loginFrameController;
	private MessageIO messageIO;
	private boolean isClosed;
	private Socket socket;

	public PokeClient() {
		try {
			socket = new Socket(StringConstants.SERVER_ADDRESS, StringConstants.SERVER_PORT);
			this.messageIO = new MessageIO(socket);
			this.isClosed = false;
		} catch (IOException ex) {
			Logger.getLogger(PokeClient.class.getName()).log(Level.SEVERE, null, ex);
		}
		loginFrameController = LoginFrameController.run(this);
		loginFrameController.showLoginFrameWindow();
	}

	public static void main(String[] args) {
		PokeClient pokeClient = new PokeClient();
		pokeClient.run();
	}

	@Override
	public void run() {
		while (loginFrameController.getLoginFrame().isVisible() && !messageIO.isClosed()) {
			messageIO.read().handle(this);
		}
		this.close();
	}

	public void close() {
		if (!isClosed) {
			this.isClosed = true;
			messageIO.close();
		}
	}

	public boolean isclosed() {
		return isClosed;
	}

	@Override
	public void handleNewTrainerReq(AddNewTrainerRequest msg) {
		if (!isClosed) {
			if (msg.isAdded()) {
				loginFrameController.trainerRegistered(msg);
			}
		} else {
			handleClosedSocket(new ClosedSocket("No connection to server"));
		}
	}

	@Override
	public void handleDoesTrainerAlreadyExistReq(DoesTrainerAlreadyExistRequest msg) {
		if (!isClosed) {
			loginFrameController.getRegisterController().getRegisterButtonListener().setDoesTrainerExist(msg.isDoesAlreadyExist());
			loginFrameController.getRegisterController().getRegisterButtonListener().trainerChecked();
		} else {
			handleClosedSocket(new ClosedSocket("No connection to server"));
		}
	}

	@Override
	public void handleClosedSocket(ClosedSocket msg) {

	}

	public void checkIfTrainerDoesExist(String username) {
		if (!isClosed) {
			Message req = new DoesTrainerAlreadyExistRequest(username);
			messageIO.send(req);
		}
	}

	public void addTrainer(String username, String hashpw) {
		if (!isClosed) {
			Message req = new AddNewTrainerRequest(username, hashpw);
			messageIO.send(req);
		}
	}
}
