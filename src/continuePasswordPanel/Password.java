package continuePasswordPanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import audio.AudioManager;

public class Password {
	
	// Dichiarazione dei campi
	
	// Pannello in cui appare la password
	PasswordPanel pps;
	
	// Delay per la pressione del tasto
	double delay = 0.1 * Math.pow(10, 9);
	double delaySystem;
	
	double delaySound = 0.5 * Math.pow(10, 9);
	double delaySoundSystem = 0;
	
	AudioManager movement = new AudioManager();
	
	// KeyHandler
	KeyHandlerContinuePage keyH;
	
	// Array e Stringa che rappresentano la password da disegnare nel pannello
	int[] password;
	public String passwordString = "";
	
	// Lista dei numeri per la password
	PasswordNumbersPaths[] numbersPath = PasswordNumbersPaths.values();
	
	// Imagini
	BufferedImage im1, im2, im3, im4;
	
	// Costruttore della classe
	public Password(PasswordPanel pps, KeyHandlerContinuePage keyH, String p) { 
		this.pps = pps; 
		this.keyH  = keyH;
		password =  new int[] {0, 0, 0, 0};
		for(int ind = 0; ind < 4; ind ++) {
			int num = Integer.parseInt(p.charAt(ind) + "");
			password[ind] = num;
		}
		
		getImage();
	}
	
	// Metodo che aggiorna l'immagine della password
	public void update() {
		passwordString = "";
		
		if(keyH.upPressed || keyH.downPressed && System.nanoTime() > delaySystem) {
			
			if(System.nanoTime() > delaySoundSystem) {
				movement.setFile(12);
				movement.start();
				delaySoundSystem = delaySound + System.nanoTime();
			}
			
			switch(pps.cursorPage.pos) {
			case "1": 
				if(keyH.upPressed && System.nanoTime() > delaySystem) { 
					if(password[0] == 7) { password[0] = 0; }
					else { password[0] += 1; }
					break;
					}
				else if(keyH.downPressed && System.nanoTime() > delaySystem) { 
					if(password[0] == 0) { password[0] = 7; }
					else { password[0] -= 1; }
					}
				break;
			case "2": 
				if(keyH.upPressed && System.nanoTime() > delaySystem) { 
					if(password[1] == 7) { password[1] = 0; }
					else { password[1] += 1; }
					break;
					}
				else if(keyH.downPressed && System.nanoTime() > delaySystem) { 
					if(password[1] == 0) { password[1] = 7; }
					else { password[1] -= 1; }
					}
				break;
			case "3":
				if(keyH.upPressed && System.nanoTime() > delaySystem) { 
					if(password[2] == 7) { password[2] = 0; }
					else { password[2] += 1; }
					break;
					}
				else if(keyH.downPressed && System.nanoTime() > delaySystem) { 
					if(password[2] == 0) { password[2] = 7; }
					else { password[2] -= 1; }
					}
				break;
			case "4": 
				if(keyH.upPressed && System.nanoTime() > delaySystem) { 
					if(password[3] == 7) { password[3] = 0; }
					else { password[3] += 1; }
					break;
					}
				else if(keyH.downPressed && System.nanoTime() > delaySystem) { 
					if(password[3] == 0) { password[3] = 7; }
					else { password[3] -= 1; }
					}
				break;
			}
			delaySystem = System.nanoTime() + delay;
		}
		
		for(int ind = 0; ind < password.length; ind ++) {
			passwordString += password[ind];
		}
		
		getImage();
		
	}
	
	// Metodo che controlla quali immagini prendere dalle risorse del progetto
	public void getImage() {
		try {	
			
			for(int ind = 0; ind < password.length; ind ++) {
				switch(ind) {
				case 0 : 
					im1 = ImageIO.read(getClass().getResourceAsStream(numbersPath[password[ind]].getPath()));
					break;
				case 1 : 
					im2 = ImageIO.read(getClass().getResourceAsStream(numbersPath[password[ind]].getPath()));
					break;
				case 2 : 
					im3 = ImageIO.read(getClass().getResourceAsStream(numbersPath[password[ind]].getPath()));
					break;
				case 3 : 
					im4 = ImageIO.read(getClass().getResourceAsStream(numbersPath[password[ind]].getPath()));
					break;
				}
			}
			
		}catch(IOException e) { e.printStackTrace(); }
	}
	
	// Metodo che disegna i numeri nel pannello
	public void draw(Graphics g) {
		
		g.drawImage(im1, 235, 350,pps.tileSize + 5, pps.tileSize + 10, pps);
		g.drawImage(im2, 335, 350,pps.tileSize + 5, pps.tileSize + 10, pps);
		g.drawImage(im3, 435, 350,pps.tileSize + 5, pps.tileSize + 10, pps);
		g.drawImage(im4, 535, 350,pps.tileSize + 5, pps.tileSize + 10, pps);
		
	}
}
