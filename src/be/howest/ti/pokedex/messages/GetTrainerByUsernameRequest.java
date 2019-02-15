package be.howest.ti.pokedex.messages;

import be.howest.ti.pokedex.domain.Trainer;

public class GetTrainerByUsernameRequest extends Message {
	private String username;
	private Trainer trainer;

	public GetTrainerByUsernameRequest(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	@Override
	public void handle(MessageHandler handler) {
		handler.handleGetTrainerByUsernameReq(this);
	}
}
