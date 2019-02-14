package be.howest.ti.pokedex.data;

import be.howest.ti.pokedex.domain.Trainer;

public interface TrainerRepository {

	int add(String username, String password, int numberOfPokeballs);

	Trainer getByName(String name);

	Trainer getById(int id);

	void save(Trainer trainer);
}
