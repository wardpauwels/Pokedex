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

	public PokeClient() {
		try {
			Socket socket = new Socket(StringConstants.SERVER_ADDRESS, StringConstants.SERVER_PORT);
			this.messageIO = new MessageIO(socket);
			this.isClosed = false;
		} catch (IOException ex) {
			Logger.getLogger(PokeClient.class.getName()).log(Level.SEVERE, null, ex);
		}
		loginFrameController = LoginFrameController.run(this);
		loginFrameController.showLoginFrameWindow();
	}

	public static void main(String[] args) {
		Thread c = new Thread(new PokeClient());
		c.start();
	}

	@Override
	public void run() {
		while (loginFrameController.getLoginFrame().isVisible() && !messageIO.isClosed()) {
			messageIO.read().handle(this);
		}
		this.close();
	}

	private void close() {
		if (!isClosed) {
			this.isClosed = true;
			messageIO.close();
		}
	}

	@Override
	public void handleNewTrainerReq(AddNewTrainerRequest msg) {
		if (!isClosed) {
			if (msg.isAdded()) {
				loginFrameController.trainerRegistered();
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
	public void handleGetTrainerByUsernameReq(GetTrainerByUsernameRequest msg) {
		if (!isClosed) {
			if (msg.getTrainer() != null) {
				loginFrameController.getLoginController().getLoginButtonListener().setTrainer(msg.getTrainer());
			}
			loginFrameController.getLoginController().getLoginButtonListener().setTrainerReceived(true);
		}
	}

	@Override
	public void handleClosedSocket(ClosedSocket msg) {
		System.out.println("Disconnected from server.");
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

	public void getTrainerByUsername(String username) {
		if (!isClosed) {
			Message req = new GetTrainerByUsernameRequest(username);
			messageIO.send(req);
		}
	}
}