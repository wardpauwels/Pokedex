package be.howest.ti.pokedex.domain;

public class Location {
	private final int xPos;
	private final int yPox;
	private int id;

	public Location(int xPos, int yPox) {
		this.xPos = xPos;
		this.yPox = yPox;
	}

	public Location(int id, int xPos, int yPox) {
		this(xPos, yPox);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPox;
	}

	@Override
	public String toString() {
		return String.format("%3d, %3d", xPos, yPox);
	}

}
