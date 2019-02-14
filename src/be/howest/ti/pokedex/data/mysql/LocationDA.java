package be.howest.ti.pokedex.data.mysql;

import be.howest.ti.pokedex.data.LocationRepository;
import be.howest.ti.pokedex.domain.Location;
import be.howest.ti.pokedex.util.SqlConstants;
import be.howest.ti.pokedex.util.StringConstants;
import be.howest.ti.pokedex.util.exceptions.PokedexException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocationDA extends DARepository implements LocationRepository {

	@Override
	public Location getById(int id) {
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(SqlConstants.SELECT_LOCATION_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return rsToLocation(rs);
			} else throw new PokedexException(StringConstants.UNABLE_TO_GET_THIS_LOCATION);
		} catch (SQLException e) {
			throw new PokedexException(StringConstants.UNABLE_TO_GET_THIS_LOCATION, e);
		}
	}

	@Override
	public int add(int xPos, int yPos) {
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(SqlConstants.INSERT_LOCATION, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, xPos);
			preparedStatement.setInt(2, yPos);
			preparedStatement.executeUpdate();
			ResultSet generatedKey = preparedStatement.getGeneratedKeys();

			if (generatedKey.next()) {
				return (generatedKey.getInt(1));
			} else throw new PokedexException(StringConstants.UNABLE_TO_CREATE_LOCATION);
		} catch (SQLException e) {
			throw new PokedexException(StringConstants.UNABLE_TO_CREATE_LOCATION, e);
		}
	}

	private Location rsToLocation(ResultSet rs) {
		try {
			return new Location(rs.getInt("id"), rs.getInt("xpos"), rs.getInt("ypos"));
		} catch (SQLException e) {
			throw new PokedexException(StringConstants.UNABLE_TO_CREATE_LOCATION, e);
		}
	}
}