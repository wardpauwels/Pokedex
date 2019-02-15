package be.howest.ti.pokedex.messages;

public class ClosedSocket extends Message {
	public String message;

	public ClosedSocket(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void handle(MessageHandler handler) {
		handler.handleClosedSocket(this);
	}
}