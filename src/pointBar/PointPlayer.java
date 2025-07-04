package pointBar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import stages.GamePanel;

public class PointPlayer {
	
	// Dichiarazione dei campi
	
	// Pannello in cui appare
	PointPanel pp;
	
	// Lista dei percorsi delle immagini
	Numbers[] listNumberPath = Numbers.values();
	
	// Pannello in cui si trova il player
	GamePanel gp;
	
	// Grandezza dei numeri
	int sizeX;
	int sizeY;
	
	// Posizioni di partenza
	int posX = 335;
	int posY = 30;
	
	// Punti sotto forma di stringa
	String point;
	
	// Costruttore della classe
	public PointPlayer(PointPanel pp, GamePanel gp) {
		this.pp = pp;
		this.gp = gp;
		
		setDefaultValues();
	}
	
	// Metodo che imposta la grandezza di default
	public void setDefaultValues() {
		
		// Granzedda dei numeri
		sizeX = pp.tileSize - 24;
		sizeY = pp.tileSize - 12;
	}
	
	public void update() { point = gp.player.point + ""; }
	
	public void draw(Graphics g, int y) {
		
		// Posizione di partenza
		posX = 335;
		
		// Immagine da disegnare
		BufferedImage image;
		
		try {
			// Ciclo for per iterare sul punteggio in modo da disegnare un numero alla volta
			for(int i = point.length() - 1; i >= 0; i --) {
				
				// Prende il numero nella posizione i
				int pos = Character.getNumericValue(point.charAt(i));
				
				// Prende dalla lista dei numeri 
				String path = listNumberPath[pos].path;
				
				// Prende l'immagine dalle risorse del progetto
				image = ImageIO.read(getClass().getResourceAsStream(path));
				
				// Disegno del punteggio
				g.drawImage(image, posX, posY + y, sizeX, sizeY, pp);
				
				// Cambia posizione per stampare il numero successivo
				posX -= sizeX;
			}
		}catch(IOException e) { e.printStackTrace(); }
		
	}
	
}
