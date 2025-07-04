package entita;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import menu.MenuPanel;

public class AirBaloon extends Entita{
	
	// Dichiarazione dei campi
	
	// Pannello in cui appare l'entita
	MenuPanel mp;
	
	// Image
	BufferedImage image;
	
	// Costruttore della classe
	public AirBaloon(MenuPanel mp) {
		this.mp = mp;
		
		getImageAirBaloon();
		setDefaultValues();
	}
	
	// Metodo che prende le immagini dalle risore del progetto
	public void getImageAirBaloon() {
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/tilesMenu/airShip/Tiles_airBaloon.png"));
			
		}catch(IOException e) { e.printStackTrace(); }
		
	}
	
	// Metodo che imposta le coordinate dell'entita
	public void setDefaultValues() {
		
		worldX = 10;
		worldY = 50;
		speed = 1;
		
	}
	
	// Metodo che aggiorna la posizione dell'entita
	public void update() {
		
		worldX += speed;
		
	}
	
	public void draw(Graphics g) {
		
		g.drawImage(image, worldX, worldY, mp.tileSize * 3, mp.tileSize * 5 + 32, mp);
		
	}
	
}
