package be.howest.ti.pokedex.messages;

import be.howest.ti.pokedex.domain.Trainer;

public class AddNewTrainerRequest extends Message {
	private String username;
	private String password;
	private boolean added;
	private Trainer trainer;

	public AddNewTrainerRequest(String username, String password) {
		this.username = username;
		this.password = password;
		this.added = false;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAdded() {
		return added;
	}

	public void setAdded(boolean added) {
		this.added = added;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	@Override
	public void handle(MessageHandler handler) {
		handler.handleNewTrainerReq(this);
	}
}