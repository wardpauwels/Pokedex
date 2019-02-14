package be.howest.ti.pokedex.data.mysql;

import be.howest.ti.pokedex.data.PokemonRepository;
import be.howest.ti.pokedex.domain.Pokemon;
import be.howest.ti.pokedex.util.SqlConstants;
import be.howest.ti.pokedex.util.StringConstants;
import be.howest.ti.pokedex.util.exceptions.PokedexException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PokemonDA extends DARepository implements PokemonRepository {

	@Override
	public List<Pokemon> getAll() {
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(SqlConstants.SELECT_ALL_POKEMON);
			List<Pokemon> pokemons = new ArrayList<>();

			while (rs.next()) {
				pokemons.add(rsToPokemon(rs));
			}
			return pokemons;
		} catch (SQLException e) {
			throw new PokedexException(StringConstants.UNABLE_TO_GET_ALL_POKEMON, e);
		}
	}

	@Override
	public Pokemon getById(int id) {
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(SqlConstants.SELECT_POKEMON_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return rsToPokemon(rs);
			} else throw new PokedexException(StringConstants.UNABLE_TO_GET_THIS_POKEMON);
		} catch (SQLException e) {
			throw new PokedexException(StringConstants.UNABLE_TO_GET_THIS_POKEMON, e);
		}
	}

	private Pokemon rsToPokemon(ResultSet rs) {
		try {
			return new Pokemon(rs.getInt("id"), rs.getString("identifier"));
		} catch (SQLException e) {
			throw new PokedexException(StringConstants.UNABLE_TO_CREATE_POKEMON, e);
		}
	}
}
