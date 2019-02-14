package be.howest.ti.pokedex.data;

import be.howest.ti.pokedex.domain.Location;

public interface LocationRepository {

	Location getById(int id);

	int add(int x, int y);

}
