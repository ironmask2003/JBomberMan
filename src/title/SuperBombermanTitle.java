package title;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import menu.MenuPanel;

public class SuperBombermanTitle extends Title {
	
	// Dichiarazione dei campi
	
	// Pannelo in cui appare il titolo
	MenuPanel mp;
	
	// Seconda immagine
	BufferedImage title1, title2;
	
	// Coordinate iniziali
	int startPosX1 = 850;
	int startPosX2 = -700;
	
	int speed = 200;
	
	// Valore booleano che controlla se l'animazione del titolo è finita
	public boolean endTransition = false;
	
	// Costruttore della classe
	public SuperBombermanTitle(MenuPanel mp) {
		
		this.mp = mp;
		
		setDefaultValues();
		getImageTitle();
		
	}
	
	// Metodo che imposta le coordinate del titolo
	@Override
	public void setDefaultValues() {
		
		posX = 65;
		posY = 45;
		
	}
	
	// Metodo che prende l'imamgine del titolo dalle risorse del progetto
	@Override
	public void getImageTitle() {
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/title/Title_Menu.png"));
			title1 = ImageIO.read(getClass().getResourceAsStream("/title/Title_Menu_1.png"));
			title2 = ImageIO.read(getClass().getResourceAsStream("/title/Title_Menu_2.png"));
			
		}catch(IOException e) { e.printStackTrace(); }
	}
	
	// Metodo che aggiorna la posizione del titolo
	public void update() { 
		
		if(startPosX1 > posX) {
			startPosX1 -= speed; 			
		}
		if(startPosX2 < posX) {
			startPosX2 += speed;
		}
	}
	
	// Metodo che disegna il titolo 
	@Override
	public void draw(Graphics g) {
		
		if(startPosX1 > posX) { g.drawImage(title1, startPosX1, 45, mp.tileSize * 14, mp.tileSize * 8, mp); }
		if(startPosX2 < posX) { g.drawImage(title2, startPosX2, 45, mp.tileSize * 14, mp.tileSize * 8, mp); }
		else {
			g.drawImage(image, posX, 45, mp.tileSize * 14, mp.tileSize * 8, mp);
			endTransition = true;
		}
		
	}
}
