package title;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import pointBar.PointPanel;

public class PresStart extends Title {
	
	// Dichiarazione dei campi
	PointPanel pp;

	// Costruttore della classe
	public PresStart(PointPanel pp) {
		this.pp = pp;
		
		setDefaultValues();
		getImageTitle();
	}

	@Override
	public void getImageTitle() {
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/Title/Title_presStart.png"));
			
		}catch(IOException e) { e.printStackTrace(); }
		
	}

	@Override
	public void setDefaultValues() {
		posX = 513;
		posY = 26;
	}

	public void draw(Graphics g, int y) {
		
		g.drawImage(image, posX, posY + y, pp.tileSize * 6, pp.tileSize - 5, pp);
		
	}
	
	
}
