package be.howest.ti.pokedex.messages;

public interface MessageHandler {

	void handleNewTrainerReq(AddNewTrainerRequest msg);

	void handleDoesTrainerAlreadyExistReq(DoesTrainerAlreadyExistRequest msg);

	void handleClosedSocket(ClosedSocket msg);
}