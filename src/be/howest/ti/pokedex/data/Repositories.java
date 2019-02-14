package be.howest.ti.pokedex.data;

import be.howest.ti.pokedex.data.mysql.EncounterDA;
import be.howest.ti.pokedex.data.mysql.LocationDA;
import be.howest.ti.pokedex.data.mysql.PokemonDA;
import be.howest.ti.pokedex.data.mysql.TrainerDA;

public class Repositories {

	public static final PokemonRepository pokemonRepository = new PokemonDA();
	public static final EncounterRepository encounterRepository = new EncounterDA();
	public static final LocationRepository locationRepository = new LocationDA();
	public static final TrainerRepository trainerRepository = new TrainerDA();
}
