package be.howest.ti.pokedex.data;

import be.howest.ti.pokedex.domain.Encounter;
import be.howest.ti.pokedex.domain.Location;
import be.howest.ti.pokedex.domain.Pokemon;
import be.howest.ti.pokedex.domain.Trainer;

import java.util.List;

public interface EncounterRepository {

	List<Encounter> getAll();

	List<Encounter> getByTrainer(Trainer trainer);

	Encounter getById(int encounterId);

	int add(Trainer trainer, Location location, Pokemon pokemon);
}