package be.howest.ti.pokedex.data.mysql;

import be.howest.ti.pokedex.data.EncounterRepository;
import be.howest.ti.pokedex.data.Repositories;
import be.howest.ti.pokedex.domain.Encounter;
import be.howest.ti.pokedex.domain.Location;
import be.howest.ti.pokedex.domain.Pokemon;
import be.howest.ti.pokedex.domain.Trainer;
import be.howest.ti.pokedex.util.SqlConstants;
import be.howest.ti.pokedex.util.StringConstants;
import be.howest.ti.pokedex.util.exceptions.PokedexException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EncounterDA extends DARepository implements EncounterRepository {

	@Override
	public List<Encounter> getAll() {
		try {
			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(SqlConstants.SELECT_ALL_ENCOUNTERS);
			List<Encounter> encounters = new ArrayList<>();

			while (rs.next()) {
				encounters.add(rsToEncounter(rs));
			}
			return encounters;
		} catch (SQLException e) {
			throw new PokedexException(StringConstants.UNABLE_TO_GET_ALL_POKEMON, e);
		}
	}

	@Override
	public List<Encounter> getByTrainer(Trainer trainer) {
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(SqlConstants.SELECT_ENCOUNTERS_BY_TRAINER);
			return getEncountersList(preparedStatement, trainer.getId());
		} catch (SQLException e) {
			throw new PokedexException(StringConstants.UNABLE_TO_GET_ENCOUNTERS, e);
		}
	}

	@Override
	public Encounter getById(int encounterId) {
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(SqlConstants.SELECT_ENCOUNTERS_BY_ID, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, encounterId);
			preparedStatement.executeQuery();
			ResultSet rs = preparedStatement.getResultSet();

			if (rs.next()) {
				return (rsToEncounter(rs));
			} else throw new PokedexException(StringConstants.UNABLE_TO_GET_THIS_ENCOUNTERS);
		} catch (SQLException e) {
			throw new PokedexException(StringConstants.UNABLE_TO_GET_THIS_ENCOUNTERS, e);
		}
	}

	@Override
	public int add(Trainer trainer, Location location, Pokemon pokemon) {
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(SqlConstants.INSERT_ENCOUNTER, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, pokemon.getId());
			preparedStatement.setInt(2, location.getId());
			preparedStatement.setInt(3, trainer.getId());
			preparedStatement.executeUpdate();
			ResultSet generatedKey = preparedStatement.getGeneratedKeys();

			if (generatedKey.next()) {
				return (generatedKey.getInt(1));
			} else throw new PokedexException(StringConstants.UNABLE_TO_ADD_ENCOUNTER);
		} catch (SQLException e) {
			throw new PokedexException(StringConstants.UNABLE_TO_ADD_ENCOUNTER, e);
		}
	}

	private Encounter rsToEncounter(ResultSet rs) {
		try {
			int id = rs.getInt("id");
			int pokemonId = rs.getInt("pokemonid");
			int locationId = rs.getInt("locationid");
			int trainerId = rs.getInt("trainerid");
			Pokemon p = Repositories.pokemonRepository.getById(pokemonId);
			Location l = Repositories.locationRepository.getById(locationId);
			Trainer t = Repositories.trainerRepository.getById(trainerId);
			return new Encounter(id, p, l, t);
		} catch (SQLException e) {
			throw new PokedexException(StringConstants.UNABLE_TO_CREATE_ENCOUNTER, e);
		}
	}

	private List<Encounter> getEncountersList(PreparedStatement preparedStatement, int id) throws SQLException {
		List<Encounter> encounters = new ArrayList<>();
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			encounters.add(rsToEncounter(rs));
		}
		return encounters;
	}
}
