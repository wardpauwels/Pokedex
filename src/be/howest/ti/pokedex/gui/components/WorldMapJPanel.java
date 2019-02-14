package be.howest.ti.pokedex.gui.components;

import be.howest.ti.pokedex.domain.Encounter;
import be.howest.ti.pokedex.util.StringConstants;
import be.howest.ti.pokedex.util.exceptions.WorldMapException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class WorldMapJPanel extends JPanel {
	private BufferedImage image;
	private List<Encounter> encounters;

	public WorldMapJPanel(List<Encounter> encounters) {
		try {
			image = ImageIO.read(new File("./resources/world.png"));
			this.encounters = encounters;
		} catch (IOException ex) {
			throw new WorldMapException(StringConstants.READ_MAP_FAILED, ex);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int height = this.getHeight();
		int width = image.getWidth() / (image.getHeight() / height);
		g.drawImage(image, 0, 0, width, height, this);
		g.setColor(Color.RED);
		for (Encounter encounter : encounters) {
			g.fillOval(encounter.getLocation().getxPos(), encounter.getLocation().getyPos(), 5, 5);
		}
	}
}