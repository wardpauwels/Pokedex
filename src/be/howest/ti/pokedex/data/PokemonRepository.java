package be.howest.ti.pokedex.data;

import be.howest.ti.pokedex.domain.Pokemon;

import java.util.List;

public interface PokemonRepository {

	List<Pokemon> getAll();

	Pokemon getById(int id);
}