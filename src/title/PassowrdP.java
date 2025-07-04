package title;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import continuePasswordPanel.PasswordPanel;

public class PassowrdP extends Title{
	
	// Dichiarazione dei campi
	
	// Pannello in cui appare il titolo
	PasswordPanel pps;
	
	// Costruttore della classe
	public PassowrdP(PasswordPanel pps) {
		this.pps = pps;
		
		// Metodo che prende le imamgini
		getImageTitle();
		
		setDefaultValues();
	}
	
	// Metodo che imposta le coordinate dell'immagine
	public void setDefaultValues() {
		// Coordinate
		posX = 285;
		posY = 250;
	}
	
	// Metodo che prende le immagini dalle risorse del progettos
	public void getImageTitle() {
		try{
			
			image = ImageIO.read(getClass().getResourceAsStream("/title/Title_Password_2.png"));
			
		}catch(IOException e) { e.printStackTrace(); }
	}
	
	// Metodo che disegna il titolo
	public void draw(Graphics g) {
		g.drawImage(image, posX, posY, pps.tileSize * 5, 35, pps);
	}
}
