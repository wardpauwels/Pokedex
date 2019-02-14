package be.howest.ti.pokedex.domain;

public class Encounter {
	private final Pokemon pokemon;
	private final Location location;
	private final Trainer trainer;
	private int id;

	public Encounter(Pokemon pokemon, Location location, Trainer trainer) {
		this.pokemon = pokemon;
		this.location = location;
		this.trainer = trainer;
	}

	public Encounter(int id, Pokemon pokemon, Location location, Trainer trainer) {
		this(pokemon, location, trainer);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public Location getLocation() {
		return location;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	@Override
	public String toString() {
		return String.format("%s seen at %s", getPokemon().getName(), getLocation() + " by " + trainer.getName());
	}
}
