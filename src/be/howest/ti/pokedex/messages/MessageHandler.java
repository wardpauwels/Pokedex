package be.howest.ti.pokedex.messages;

public interface MessageHandler {

	void handleNewTrainerReq(AddNewTrainerRequest msg);

	void handleDoesTrainerAlreadyExistReq(DoesTrainerAlreadyExistRequest msg);

	void handleGetTrainerByUsernameReq(GetTrainerByUsernameRequest msg);

	void handleClosedSocket(ClosedSocket msg);
}