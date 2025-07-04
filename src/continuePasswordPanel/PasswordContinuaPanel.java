package continuePasswordPanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PasswordContinuaPanel{
	
	// Dichiarazione dei campi
	
	// Pannello in cui appaiono i numeri
	ContinuePanel cp;
	
	// Lista dei numeri per la password
	PasswordNumbersPaths[] numbers = PasswordNumbersPaths.values();
	
	// Password
	public String password = "";
	
	// Immaggini dei numeri da disegnare
	BufferedImage im1, im2, im3, im4;
	
	// Costruttore della classe
	public PasswordContinuaPanel(ContinuePanel cp) {
		this.cp = cp;
	}
	
	// Scelta randomica dei numeri
	public void getPassword() {
		while (password.length() < 4) {
			int num = (int) (Math.random() * numbers.length);
			password += num;
			
			try {
				switch(password.length()) {
				case 1 : im1 = ImageIO.read(getClass().getResourceAsStream(numbers[num].getPath())); break;
				case 2 : im2 = ImageIO.read(getClass().getResourceAsStream(numbers[num].getPath())); break;
				case 3 : im3 = ImageIO.read(getClass().getResourceAsStream(numbers[num].getPath())); break;
				case 4 : im4 = ImageIO.read(getClass().getResourceAsStream(numbers[num].getPath())); break;
				}
			}catch(IOException e) { e.printStackTrace(); }
		}
	}
	
	// Metodo che disegna
	public void draw(Graphics g) {
		
		g.drawImage(im1, 235, 420,cp.tileSize, cp.tileSize, cp);
		g.drawImage(im2, 335, 420,cp.tileSize, cp.tileSize, cp);
		g.drawImage(im3, 435, 420,cp.tileSize, cp.tileSize, cp);
		g.drawImage(im4, 535, 420,cp.tileSize, cp.tileSize, cp);
		
	}
}
