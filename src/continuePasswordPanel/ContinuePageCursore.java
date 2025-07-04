package continuePasswordPanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import audio.AudioManager;

public class ContinuePageCursore {
	
	// Dichiarazione dei campi
	public int x;
	public int y;
	
	AudioManager select = new AudioManager();
	AudioManager movement = new AudioManager();
	
	ContinuePanel cp;
	KeyHandlerContinuePage keyH;
	PasswordPanel pps;
	
	BufferedImage image;
	
	// Delay per la pressione del tasto
	double delay = 0.2 * Math.pow(10, 9);
	double delaySystem;
	
	// posizioni del cursore
	int[] yesTitle;
	int[] noTitle;
	
	// Posizione del cursore nel caso siamo nel pannello della password
	int[] num1;
	int[] num2;
	int[] num3;
	int[] num4;
	
	// Direzione
	String direction = "left";
	
	// Variabile per indicare se si trova nel title yes o no
	public String title = "yes";
	
	// Variabile per indicare quale numero sta andando modificare se si trova nel pannello in cui mettere la password
	String pos = "1";
	
	// Costruttore della classe
	public ContinuePageCursore(ContinuePanel cp, KeyHandlerContinuePage keyH) {
		select.setFile(13);
		
		this.cp = cp;
		this.keyH = keyH;
		
		yesTitle = new int[] {190, 262};
		noTitle = new int[] {440, 262};
		
		setDefaultPosition();
		getImage();
	}
	
	public ContinuePageCursore(PasswordPanel pps, KeyHandlerContinuePage keyH) {
		select.setFile(13);
		
		this.pps = pps;
		this.keyH = keyH;
		
		num1 = new int[] {185, 345};
		num2 = new int[] {285, 345};
		num3 = new int[] {385, 345};
		num4 = new int[] {485, 345};
		
		setDefaultPositionPasswordPanel();
		getImage();
	}
	
	// Metodo che prende l'indirizzo dell'immagine del cursore
	public void getImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/tilesMenu/Tile_Cursore.png"));
		}catch(IOException e) { e.printStackTrace(); }
	}
	
	// Metodo che imposta la posizione iniziale del cursore
	public void setDefaultPosition() {
		x = yesTitle[0];
		y = yesTitle[1];
	}
	
	// Metodo che imposta la posizione iniziale del cursore
	public void setDefaultPositionPasswordPanel() {
		x = num1[0];
		y = num1[1];
	}
	
	// Metodo che aggiorna il cursore
	public void update() {
		if(cp == null) {updatePasswordPanel();}
		else {updateContinuePanel();}
	}
	
	// Metodo che aggiorna la posizione del cursore quando si trova nel pannello password
	public void updatePasswordPanel() {
		
		if((keyH.leftPressed || keyH.rigthPressed) && System.nanoTime() > delaySystem) {
			movement.setFile(12);
			movement.start();
			
			if(keyH.leftPressed) { direction = "left"; }
			else if(keyH.rigthPressed) { direction = "rigth"; }
			
			switch(direction) {
			case "rigth":
				switch(pos) {
				case "1" : 
					x = num2[0];
					y = num2[1];
					pos = "2";
					break;
				case "2" : 
					x = num3[0];
					y = num3[1];
					pos = "3";
					break;
				case "3" : 
					x = num4[0];
					y = num4[1];
					pos = "4";
					break;
				case "4" : 
					x = num1[0];
					y = num1[1];
					pos = "1";
					break;
				}
				break;
			case "left":
				switch(pos) {
				case "1" : 
					x = num4[0];
					y = num4[1];
					pos = "4";
					break;
				case "2" : 
					x = num1[0];
					y = num1[1];
					pos = "1";
					break;
				case "3" : 
					x = num2[0];
					y = num2[1];
					pos = "2";
					break;
				case "4" : 
					x = num3[0];
					y = num3[1];
					pos = "3";
					break;
				}
				break;
			}
			
			delaySystem = delay + System.nanoTime();
			
		}
		
	}
	
	// Metodo che aggiorna la posizione del cursore quando si trova nel pannello continue
	public void updateContinuePanel() {
		
		// Controlla se è stato premuto un tasto
		if((keyH.leftPressed || keyH.rigthPressed) && System.nanoTime() > delaySystem) {
			movement.setFile(12);
			movement.start();
			
			switch(direction) {
			case "rigth" : 
				x = yesTitle[0];
				y = yesTitle[1];
				direction = "left";
				title = "yes";
				break;
			case "left" : 
				x = noTitle[0];
				y = noTitle[1];
				direction = "rigth";
				title = "no";
				break;
			}
			
			delaySystem = delay + System.nanoTime();
			
		}
		
	}
	
	// Metodo che rappresenta nella finestra il cursore
	public void draw(Graphics g) {
		if(cp == null) {
			g.drawImage(image, x, y, pps.tileSize + 15, pps.tileSize + 15, null); return; 
		}
		g.drawImage(image, x, y, cp.tileSize + 32, cp.tileSize + 32, null);
	}

}
