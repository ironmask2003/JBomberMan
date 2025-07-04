package title;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import menu.MenuPanel;

public class NormalGame extends Title{
	
	// Dichiarazione dei campi
	
	// Pannello in cui appare il titolo
	MenuPanel mp;
	
	// Costruttore della classe
	public NormalGame(MenuPanel mp) {
		this.mp = mp;
		
		setDefaultValues();
		getImageTitle();
	}
	
	// Metodo che prende l'immagine dalle risorde del progetto
	@Override
	public void getImageTitle() {
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/title/Title_NormalGame.png"));
			
		}catch(IOException e) { e.printStackTrace(); }
		
	}
	
	@Override
	public void setDefaultValues() {
		posX = 248;
		posY = 440;
	}
	
	@Override
	public void draw(Graphics g) {
		
		g.drawImage(image, posX, posY, mp.tileSize * 7, mp.tileSize - 8, mp);
		
	}
}
