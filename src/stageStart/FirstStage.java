package stageStart;

import java.awt.Graphics;
import java.io.IOException;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import stages.GamePanel;

public class FirstStage extends StartStage {
	
	// Dichiarazione dei campi
	
	// Pannello in cui appare la scritta
	public GamePanel gp;
	
	double delayTransition = (2 * 1 * Math.pow(10, 9));
	double delay = 0;
	
	int x = 550;
	
	// Costruttore della classe
	public FirstStage(GamePanel gp) {
		super();
		
		this.gp = gp;
	}
	
	@Override
	public void getImageNumbers() {
		
		try {
			
			super.getImageNumbers();
			number1 = ImageIO.read(getClass().getResourceAsStream("/numbersStage/Tiles_one.png"));
			number2 = ImageIO.read(getClass().getResourceAsStream("/numbersStage/Tiles_one.png"));
			
			
		}catch(IOException e) { e.printStackTrace(); }
		
	}
	
	@Override
	public void draw(Graphics g) {
		
		if(x == 0) {
			
			// Disegno primo numero
			g.drawImage(number1, posXnum1 + x, posYnum1, gp.tileSize * 2, gp.tileSize * 3, gp);
			
			// Disegno secondo numero
			g.drawImage(number2, posXnum2 + x, posYnum2, gp.tileSize * 2, gp.tileSize * 3, gp);
			
			// Disegno scritta start
			g.drawImage(start, posXstart + x, posYstart, gp.tileSize * 6, gp.tileSize * 2, gp);
			
			// Disegno trattino in mezzo ai numero
			g.drawImage(dash, posXdash + x, posYdash, gp.tileSize, gp.tileSize, gp);
			
			endTransition = true;
			
			delay = System.nanoTime() + delayTransition;
			
			return;
			
		}
		
		// Disegno primo numero
		g.drawImage(number1, posXnum1 - x, posYnum1, gp.tileSize * 2, gp.tileSize * 3, gp);
		
		// Disegno secondo numero
		g.drawImage(number2, posXnum2 - x, posYnum2, gp.tileSize * 2, gp.tileSize * 3, gp);
		
		// Disegno scritta start
		g.drawImage(start, posXstart - x, posYstart, gp.tileSize * 6, gp.tileSize * 2, gp);
		
		// Disegno trattino in mezzo ai numero
		g.drawImage(dash, posXdash - x, posYdash, gp.tileSize, gp.tileSize, gp);
		
		x -= speed;
		
	}
	
	public void draw(Graphics g, boolean ok) {
		
		if(System.nanoTime() > delay) {
			
			// Disegno primo numero
			g.drawImage(number1, posXnum1 + x, posYnum1, gp.tileSize * 2, gp.tileSize * 3, gp);
			
			// Disegno secondo numero
			g.drawImage(number2, posXnum2 + x, posYnum2, gp.tileSize * 2, gp.tileSize * 3, gp);
			
			// Disegno scritta start
			g.drawImage(start, posXstart + x, posYstart, gp.tileSize * 6, gp.tileSize * 2, gp);
			
			// Disegno trattino in mezzo ai numero
			g.drawImage(dash, posXdash + x, posYdash, gp.tileSize, gp.tileSize, gp);
			
			x += speed;
			
			if(x == 600) { 
				endSecondTransition = true;
				gp.back.loop();
			} // Se la scritta è scompara imposta la variabile a true
			
			return;
			
		}
		
		// Disegno primo numero
		g.drawImage(number1, posXnum1 + x, posYnum1, gp.tileSize * 2, gp.tileSize * 3, gp);
		
		// Disegno secondo numero
		g.drawImage(number2, posXnum2 + x, posYnum2, gp.tileSize * 2, gp.tileSize * 3, gp);
		
		// Disegno scritta start
		g.drawImage(start, posXstart + x, posYstart, gp.tileSize * 6, gp.tileSize * 2, gp);
		
		// Disegno trattino in mezzo ai numero
		g.drawImage(dash, posXdash + x, posYdash, gp.tileSize, gp.tileSize, gp);
		
	}
	
	public void drawBackground(Graphics g) {
		
		BufferedImage image = switch(spriteNum) {
		case 0 -> null;
		case 1 -> back1;
		case 2 -> back2;
		case 3 -> back3;
		case 4 -> back4;
		case 5 -> back5;
		default -> throw new IllegalArgumentException("Unexpected value: " + spriteNum);
		};
		
		g.drawImage(image, 0, 0, gp.tileSize * 20, gp.tileSize * 20, gp);
		
	}
}
