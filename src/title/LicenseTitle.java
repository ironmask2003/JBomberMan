package title;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import menu.MenuPanel;

public class LicenseTitle extends Title{
	
	// Dichiarazione dei campi
	
	// Pannello in cui appare
	MenuPanel mp;
	
	// Costruttore della classe
	public LicenseTitle(MenuPanel mp) {
		this.mp = mp;
		
		getImageTitle();
		setDefaultValues();
	}
	
	@Override
	public void setDefaultValues() {
		
		posX = 210;
		posY = mp.screenHeigth - image.getHeight() - 30;
		
	}
	
	@Override
	public void getImageTitle() {
		
		try{
			
			image = ImageIO.read(getClass().getResourceAsStream("/title/Title_license.png"));
			
		}catch(IOException e) { e.printStackTrace(); }
		
	}
	
	@Override
	public void draw(Graphics g) {
		
		g.drawImage(image, posX, posY, mp.tileSize * 8, mp.tileSize, mp);
		
	}

}
