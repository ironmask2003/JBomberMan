package title;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import pointBar.PointPanel;

public class ScorePlayer extends Title {
	
	// Dichiarazione dei campi 
	
	// Pannello in cui appare la scritta
	PointPanel pp;
	
	// Costruttore della classe
	public ScorePlayer(PointPanel pp) {
		
		this.pp = pp;
		
		setDefaultValues();
		getImageTitle();
	}
	
	@Override
	public void setDefaultValues() {
		posX = 104;
		posY = 26;
	}
	
	@Override
	public void getImageTitle(){
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/title/Title_score.png"));
			
		}catch(IOException e) { e.printStackTrace(); }
		
	}
	
	public void draw(Graphics g, int y) {
		
		g.drawImage(image, posX, posY + y, pp.tileSize, pp.tileSize,  pp);
		
	}
}
