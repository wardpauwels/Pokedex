package be.howest.ti.pokedex.data.mysql;

import be.howest.ti.pokedex.data.TrainerRepository;
import be.howest.ti.pokedex.domain.Trainer;
import be.howest.ti.pokedex.util.SqlConstants;
import be.howest.ti.pokedex.util.StringConstants;
import be.howest.ti.pokedex.util.exceptions.PokedexException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainerDA extends DARepository implements TrainerRepository {

	@Override
	public int add(String username, String password, int numberOfPokeballs) {
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(SqlConstants.INSERT_TRAINER, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setInt(3, numberOfPokeballs);
			preparedStatement.executeUpdate();
			ResultSet generatedKey = preparedStatement.getGeneratedKeys();

			if (generatedKey.next()) {
				return (generatedKey.getInt(1));
			} else throw new PokedexException(StringConstants.UNABLE_TO_GET_THIS_TRAINER);
		} catch (SQLException e) {
			throw new PokedexException(StringConstants.UNABLE_TO_GET_THIS_TRAINER, e);
		}
	}

	@Override
	public Trainer getByName(String name) {
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(SqlConstants.SELECT_TRAINER_BY_NAME);
			preparedStatement.setString(1, name);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return rsToTrainer(rs);
			} else return null;
		} catch (SQLException e) {
			throw new PokedexException(StringConstants.UNABLE_TO_GET_THIS_TRAINER, e);
		}
	}

	@Override
	public Trainer getById(int id) {
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(SqlConstants.SELECT_TRAINER_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return rsToTrainer(rs);
			} else throw new PokedexException(StringConstants.UNABLE_TO_GET_THIS_TRAINER);
		} catch (SQLException e) {
			throw new PokedexException(StringConstants.UNABLE_TO_GET_THIS_TRAINER, e);
		}
	}

	@Override
	public void save(Trainer trainer) {
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(SqlConstants.SAVE_TRAINER);
			preparedStatement.setInt(1, trainer.getNumberOfPokeballs());
			preparedStatement.setInt(2, trainer.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new PokedexException(StringConstants.UNABLE_TO_SAVE_TRAINER, e);
		}
	}

	private Trainer rsToTrainer(ResultSet rs) {
		try {
			return new Trainer(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getInt("numberOfPokeballs"));
		} catch (SQLException e) {
			throw new PokedexException(StringConstants.UNABLE_TO_CREATE_TRAINER, e);
		}
	}
}