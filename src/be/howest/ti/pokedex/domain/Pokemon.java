package be.howest.ti.pokedex.domain;

import java.util.Objects;

public class Pokemon {

	private final int id;
	private String name;

	public Pokemon(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("%s (%d)", getName(), getId());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pokemon pokemon = (Pokemon) o;
		return id == pokemon.id &&
				name.equals(pokemon.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
