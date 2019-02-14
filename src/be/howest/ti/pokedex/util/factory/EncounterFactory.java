package be.howest.ti.pokedex.util.factory;

import be.howest.ti.pokedex.data.Repositories;
import be.howest.ti.pokedex.domain.Encounter;
import be.howest.ti.pokedex.domain.Location;
import be.howest.ti.pokedex.domain.Pokemon;
import be.howest.ti.pokedex.domain.Trainer;

public class EncounterFactory {
	private int x;
	private int y;
	private Trainer trainer;
	private Pokemon pokemon;
	private int encounterId;

	public EncounterFactory(int x, int y, Trainer trainer, Pokemon pokemon) {
		this.x = x;
		this.y = y;
		this.trainer = trainer;
		this.pokemon = pokemon;

		this.createEncounter();
	}

	private void createEncounter() {
		Location location = new LocationFactory(x, y).getLocation();
		encounterId = Repositories.encounterRepository.add(trainer, location, pokemon);
		trainer.addPokemon(pokemon);
		trainer.substractPokeball();
	}

	public Encounter getEncounter() {
		return Repositories.encounterRepository.getById(encounterId);
	}
}
