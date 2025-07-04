package entita;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import menu.MenuPanel;

public class AirShip2 extends Entita{
	
	// Dichiarazione dei campi
	
	// Pannello in cui appare l'immagine
	MenuPanel mp;
	
	// Image
	BufferedImage airShipIm;
	
	// Costruttore della classe
	public AirShip2(MenuPanel mp) {
		this.mp = mp;
		
		getImageAirShip();
		setDefaultValues();
		
		spriteCounter = 1;
	}
	
	// Metodo che imposta i valori iniziali dell'entita
	public void setDefaultValues() {
		
		worldX = 150;
		worldY = 360;
		speed = 1;
		
	}
	
	// Metodo che prende le del airShip dalle risore del progetto
	public void getImageAirShip() {
		
		try {
			
			airShipIm = ImageIO.read(getClass().getResourceAsStream("/tilesMenu/airShip/Tiles_airShip2.png"));
			
		}catch(IOException e) { e.printStackTrace(); }
		
	}
	
	// Metodo che disegna l'airShip
	public void draw(Graphics g) {
		
		g.drawImage(airShipIm, worldX, worldY, mp.tileSize * 2, mp.tileSize * 2, mp);
		worldX += speed;
		
	}
}
