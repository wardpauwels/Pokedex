package be.howest.ti.pokedex.messages;

public class DoesTrainerAlreadyExistRequest extends Message {
	private String username;
	private boolean doesAlreadyExist;

	public DoesTrainerAlreadyExistRequest(String username) {
		this.username = username;
		this.doesAlreadyExist = false;
	}

	public String getUsername() {
		return username;
	}

	public boolean isDoesAlreadyExist() {
		return doesAlreadyExist;
	}

	public void setDoesAlreadyExist(boolean doesAlreadyExist) {
		this.doesAlreadyExist = doesAlreadyExist;
	}

	@Override
	public void handle(MessageHandler handler) {
		handler.handleDoesTrainerAlreadyExistReq(this);
	}
}
