package be.howest.ti.pokedex.util.factory;

import be.howest.ti.pokedex.data.Repositories;
import be.howest.ti.pokedex.domain.Trainer;

public class TrainerFactory {
	private String username;
	private String password;
	private int numberOfPokeballs;
	private int trainerId;

	public TrainerFactory(String username, String password, int numberOfPokeballs) {
		this.username = username;
		this.password = password;
		this.numberOfPokeballs = numberOfPokeballs;
		this.createTrainer();
	}

	private void createTrainer() {
		trainerId = Repositories.trainerRepository.add(username, password, numberOfPokeballs);
	}

	public Trainer getTrainer() {
		return Repositories.trainerRepository.getById(trainerId);
	}


}
