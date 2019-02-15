package be.howest.ti.pokedex.messages;

import java.io.Serializable;

public abstract class Message implements Serializable {

	public abstract void handle(MessageHandler handler);

}