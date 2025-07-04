package title;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import continuePasswordPanel.ContinuePanel;

public class No extends Title {
	
	// Dichiarazione dei campi

	// Pannello in cui appare il titolo
	ContinuePanel cp;
	
	// Costruttore della classe
	public No(ContinuePanel cp) {
		this.cp = cp;
		
		setDefaultValues();
		getImageTitle();
	}
	
	// Metodo che imposta i valori di default
	public void setDefaultValues() {
		// Cooridnate 
		posX = 500;
		posY = 285;
	}
	
	// Metodo che prende le immagini dalle risorse del progetto
	public void getImageTitle() {
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/title/Title_No.png"));
			
		}catch(IOException e) { e.printStackTrace(); }
		
	}
	
	// Metodo che disegna il titolo enl pannello
	public void draw(Graphics g) {
		g.drawImage(image, posX, posY, cp.tileSize + 25, 30, cp);
	}
}
