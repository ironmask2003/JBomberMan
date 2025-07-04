package pointBar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Timer {
	
	//-> Dichiarazione dei campi <-//
	
	// Pannello dei punti
	PointPanel pp;
	
	// Immagine del rettangolo
	BufferedImage white, black;
	
	// Coordinate
	int worldX;
	int worldY;
	
	//-> Costruttore della classe <-//
	public Timer(PointPanel pp, int worldX, int worldY) {
		
		this.pp = pp;
		this.worldX = worldX;
		this.worldY = worldY;
		
		getImageBlockTimer();
		
	}
	
	// Metodo che prende le immagini dalle risore del progetto
	public void getImageBlockTimer() {
		
		try {
			
			white = ImageIO.read(getClass().getResourceAsStream("/tilesPoint/Tiles_Timer_W.png"));
			black = ImageIO.read(getClass().getResourceAsStream("/tilesPoint/Tiles_Timer_B.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//-> Metodi che impostano le coordinate dell'oggetto
	public void setWorldX(int worldX) { this.worldX = worldX; }
	public void setWorldY(int worldY) { this.worldY = worldY; }
	
	//-> Metodo che raprpesenta i quadrati bianchi
	public void drawWhite(Graphics g) { g.drawImage(white, worldX, worldY, pp.tileSize, pp.tileSize, pp); }
	
	//-> Metodo che rappresenta i qudrati neri
	public void drawBlack(Graphics g) { g.drawImage(black, worldX, worldY, pp.tileSize, pp.tileSize, pp); }
}
