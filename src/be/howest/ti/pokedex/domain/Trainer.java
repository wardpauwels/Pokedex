package be.howest.ti.pokedex.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Trainer implements Serializable {
	private final String name;
	private final String password;
	private final Set<Pokemon> pokemons;
	private int id;
	private int numberOfPokeballs;

	public Trainer(String name, String password, int numberOfPokeballs) {
		this.name = name;
		this.password = password;
		this.numberOfPokeballs = numberOfPokeballs;
		this.pokemons = new HashSet<>();
	}

	public Trainer(int id, String name, String password, int numberOfPokeballs) {
		this(name, password, numberOfPokeballs);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void addPokemon(Pokemon pokemon) {
		pokemons.add(pokemon);
	}

	public void removePokemon(Pokemon pokemon) {
		pokemons.remove(pokemon);
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public int getNumberOfPokeballs() {
		return numberOfPokeballs;
	}

	public Set<Pokemon> getPokemons() {
		return pokemons;
	}

	public void substractPokeball() {
		if (numberOfPokeballs > 0) numberOfPokeballs--;
	}

	public void addPokeball() {
		numberOfPokeballs++;
	}
}