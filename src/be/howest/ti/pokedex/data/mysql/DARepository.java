package be.howest.ti.pokedex.data.mysql;

import be.howest.ti.pokedex.util.SqlConstants;
import be.howest.ti.pokedex.util.StringConstants;
import be.howest.ti.pokedex.util.exceptions.PokedexException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DARepository {

	private static Connection THE_GLOBAL_CONNECTION;

	Connection getConnection() {
		if (THE_GLOBAL_CONNECTION == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				THE_GLOBAL_CONNECTION = DriverManager.getConnection(SqlConstants.URL, SqlConstants.USR, SqlConstants.PWD);
			} catch (SQLException e) {
				throw new PokedexException(StringConstants.UNABLE_TO_CREATE_DB_CONNECTION, e);
			} catch (ClassNotFoundException e) {
				throw new PokedexException(StringConstants.UNABLE_TO_REGISTER_DRIVER, e);
			}
		}
		return THE_GLOBAL_CONNECTION;
	}
}
