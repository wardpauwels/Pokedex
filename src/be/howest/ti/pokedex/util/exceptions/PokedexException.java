package be.howest.ti.pokedex.util.exceptions;

public class PokedexException extends RuntimeException {

	public PokedexException(String message) {
		super(message);
	}

	public PokedexException(String message, Throwable cause) {
		super(message, cause);
	}
}