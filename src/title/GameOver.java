package title;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import pointBar.PointPanel;

public class GameOver extends Title {
	
	// Dichiarazione dei campi
	
	// Pannello in cui appare la scritta
	PointPanel pp;
	
	// Costruttore della classe
	public GameOver(PointPanel pp) {
		this.pp = pp;
		
		setDefaultValues();
		getImageTitle();
	}
	
	// Metodo che prende le immagini dalle risorse del progetto
	@Override
	public void getImageTitle() {
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/title/Title_GameOver.png"));
			
		}catch (IOException e) { e.printStackTrace();}
		
	}
	
	// Metodo che imposta le coordinate dell'immagine
	@Override
	public void setDefaultValues() {
		
		// Coordinate
		posX = 513;
		posY = 26;
	}
	
	public void draw(Graphics g, int y) {
		
		// Disegno del titolo
		g.drawImage(image, posX, posY + y, pp.tileSize * 6, pp.tileSize - 5, pp);
		
	}

}
