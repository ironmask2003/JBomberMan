package title;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import menu.MenuPanel;

public class BattleMode extends Title{
	
	// Dichiarazione dei campi
	
	// Pannello in cui appare il glory
	MenuPanel mp;
	
	// Costruttore della classe
	public BattleMode(MenuPanel mp) {
		this.mp = mp;
		
		getImageTitle();
		setDefaultValues();
	}
	
	@Override
	public void getImageTitle() {
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/title/Title_BattleMode.png"));
			
		}catch(IOException e ) { e.printStackTrace(); }
	}
	
	@Override
	public void setDefaultValues() {
		posX = 248;
		posY = 480;
	}
	
	@Override
	public void draw(Graphics g) {
		
		g.drawImage(image, posX, posY, mp.tileSize * 7, mp.tileSize - 8, mp);
		
	}
}
