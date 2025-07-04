package pointBar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import stages.GamePanel;

public class LifePlayer {
	
	// Dichiarazione dei campi
	
	// Pannello in cui appare il numero
	PointPanel pp;
	
	// Pannello in cui si trova il player
	GamePanel gp;
	
	// Lista dei numeri
	Numbers[] listNumber = Numbers.values();
	
	// Costruttore della classe
	public LifePlayer(GamePanel gp, PointPanel pp) {
		
		this.gp = gp;
		this.pp = pp;
		
	}
	
	public void draw(Graphics g, int y) {
		
		try{
			
			BufferedImage image = switch(gp.player.life) {
			case 0 -> ImageIO.read(getClass().getResourceAsStream(listNumber[0].path));				// Immagine del numero 0
			case 1 -> ImageIO.read(getClass().getResourceAsStream(listNumber[1].path));					// Immagine del numero 1
			case 2 -> ImageIO.read(getClass().getResourceAsStream(listNumber[2].path));				// Immagine del numero 2
			case 3 -> ImageIO.read(getClass().getResourceAsStream(listNumber[3].path));				// Immagine del numero 3
			case 4 -> ImageIO.read(getClass().getResourceAsStream(listNumber[4].path));				// Immagine del numero 4
			case 5 -> ImageIO.read(getClass().getResourceAsStream(listNumber[5].path));				// Immagine del numero 5
			case 6 -> ImageIO.read(getClass().getResourceAsStream(listNumber[6].path));				// Immagine del numero 6
			case 7 -> ImageIO.read(getClass().getResourceAsStream(listNumber[7].path));					// Immagine del numero 7
			case 8 -> ImageIO.read(getClass().getResourceAsStream(listNumber[8].path));				// Immagine del numero 8
			case 9 -> ImageIO.read(getClass().getResourceAsStream(listNumber[9].path));				// Immagine del numero 9
			default ->  ImageIO.read(getClass().getResourceAsStream(listNumber[0].path));				// Immagine del numero 0
			};
			
			g.drawImage(image, 78, 30 + y, pp.tileSize - 24, pp.tileSize - 12, pp);
			
		}catch(IOException e) { e.printStackTrace(); }
		
	}

}
