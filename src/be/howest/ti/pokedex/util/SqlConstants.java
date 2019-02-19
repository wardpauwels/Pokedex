package be.howest.ti.pokedex.util;

public class SqlConstants {
	public static final String URL = "jdbc:mysql://localhost/pokemon?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	public static final String USR = "wardpauwels";
	public static final String PWD = "mKliEzBYva0K8e6m";

	public static final String SELECT_ALL_ENCOUNTERS = "SELECT * FROM encounter;";
	public static final String SELECT_ENCOUNTERS_BY_TRAINER = "SELECT * FROM encounter WHERE trainerid = ?;";
	public static final String SELECT_ENCOUNTERS_BY_ID = "SELECT * FROM encounter WHERE id = ?;";
	public static final String INSERT_ENCOUNTER = "INSERT INTO encounter (pokemonid, locationid, trainerid) VALUES (?,?,?);";

	public static final String SELECT_ALL_POKEMON = "SELECT id,identifier FROM pokemon;";
	public static final String SELECT_POKEMON_BY_ID = "SELECT id,identifier FROM pokemon WHERE id = ?;";

	public static final String SELECT_TRAINER_BY_ID = "SELECT * FROM trainer WHERE id = ?;";
	public static final String SELECT_TRAINER_BY_NAME = "SELECT * FROM trainer WHERE username LIKE ?";
	public static final String INSERT_TRAINER = "INSERT INTO trainer (username, password, numberOfPokeballs) VALUES(?,?,?);";
	public static final String SAVE_TRAINER = "UPDATE trainer SET numberOfPokeballs = ? WHERE id = ?";

	public static final String SELECT_LOCATION_BY_ID = "SELECT * FROM location WHERE id = ?;";
	public static final String INSERT_LOCATION = "INSERT INTO location (xpos, ypos) VALUES (?,?);";
}