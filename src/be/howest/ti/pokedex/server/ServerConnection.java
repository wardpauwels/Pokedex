package be.howest.ti.pokedex.server;

import be.howest.ti.pokedex.data.Repositories;
import be.howest.ti.pokedex.domain.Trainer;
import be.howest.ti.pokedex.messages.*;
import be.howest.ti.pokedex.util.StringConstants;
import be.howest.ti.pokedex.util.communication.MessageIO;
import be.howest.ti.pokedex.util.exceptions.PokedexException;
import be.howest.ti.pokedex.util.factory.TrainerFactory;

import java.net.Socket;

public class ServerConnection implements Runnable, MessageHandler {

	private final PokeServer caller;
	private final MessageIO messageIO;
	private final int id;

	public ServerConnection(Socket socket, PokeServer caller, int id) {
		this.caller = caller;
		this.messageIO = new MessageIO(socket);
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Connection " + id + " started");
		while (!messageIO.isClosed()) {
			Message msg = messageIO.read();
			msg.handle(this);
		}
		close();
	}

	public boolean isClosed() {
		return messageIO.isClosed();
	}

	public void close() {
		messageIO.close();
	}

	@Override
	public void handleNewTrainerReq(AddNewTrainerRequest msg) {
		Trainer trainer = null;
		try {
			trainer = new TrainerFactory(msg.getUsername(), msg.getPassword(), 5).getTrainer();
		} catch (PokedexException e) {
			throw new PokedexException(StringConstants.UNABLE_TO_CREATE_TRAINER);
		} finally {
			msg.setAdded(true);
			msg.setTrainer(trainer);
		}
		messageIO.send(msg);
	}

	@Override
	public void handleDoesTrainerAlreadyExistReq(DoesTrainerAlreadyExistRequest msg) {
		Trainer trainer = Repositories.trainerRepository.getByName(msg.getUsername());
		if (trainer != null) {
			msg.setDoesAlreadyExist(true);
		}
		messageIO.send(msg);
	}

	@Override
	public void handleGetTrainerByUsernameReq(GetTrainerByUsernameRequest msg) {
		Trainer trainer = Repositories.trainerRepository.getByName(msg.getUsername());
		if (trainer != null) {
			msg.setTrainer(trainer);
		}
		messageIO.send(msg);
	}

	@Override
	public void handleClosedSocket(ClosedSocket msg) {

	}
}