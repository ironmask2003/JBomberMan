package title;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import continuePasswordPanel.ContinuePanel;

public class Continue extends Title {
	
	// Dichiarazione dei campi
	
	// Pannello in cui appare la scritta
	ContinuePanel cp;
	
	
	// Costruttore della classe
	public Continue(ContinuePanel cp) {
		this.cp = cp;
		
		setDefaultValues();
		getImageTitle();
	}
	
	// Metodo che imposta i valori di default
	public void setDefaultValues() {
		// Cooridnate 
		posX = 285;
		posY = 200;
	}
	
	// Metodo che prende le immagini dalle risorse del progetto
	public void getImageTitle() {
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/title/Title_Continue.png"));
			
		}catch(IOException e) { e.printStackTrace(); }
		
	}
	
	// Metodo che disegna il titolo enl pannello
	public void draw(Graphics g) {
		g.drawImage(image, posX, posY, cp.tileSize * 5, 35, cp);
	}
}
