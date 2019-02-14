package be.howest.ti.pokedex.util.factory;

import be.howest.ti.pokedex.data.Repositories;
import be.howest.ti.pokedex.domain.Location;

public class LocationFactory {
	private int x;
	private int y;
	private int locationId;

	LocationFactory(int x, int y) {
		this.x = x;
		this.y = y;
		createLocation();
	}

	private void createLocation() {
		locationId = Repositories.locationRepository.add(x, y);
	}

	public Location getLocation() {
		return Repositories.locationRepository.getById(locationId);
	}
}
