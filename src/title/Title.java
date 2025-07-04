package title;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Title {
	
	// Dichiarazione dei campi
	
	// Coordinate dei titoli
	public int posX;
	public int posY;
	
	// Image
	BufferedImage image;
	
	// Metodo che prende l'immagine dalle risore del progetto
	public void getImageTitle() {}
	
	// Metodo che imposta le coordinate del title
	public void setDefaultValues() {}
	
	// Metodo che disegna i titoli
	public void draw(Graphics g) {}
}
