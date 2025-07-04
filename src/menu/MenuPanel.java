package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.Thread.State;

import javax.imageio.ImageIO;
import javax.swing.*;

import audio.AudioManager;
import entita.AirBaloon;
import entita.AirShip;
import entita.AirShip2;
import title.BattleMode;
import title.LicenseTitle;
import title.NormalGame;
import title.Password;
import title.SuperBombermanTitle;

public class MenuPanel extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	// Dichiarazione dei campi
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 17;
	public final int maxScreenRow = 13;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeigth = tileSize * maxScreenRow;
	
	KeyHandlerMenu keyH = new KeyHandlerMenu();
	Thread menuThread;
	AudioManager audio = new AudioManager();
	
	// Air Ship
	AirShip airShip;
	
	// Air Ship 2
	AirShip2 airShipS;
	
	// Air Baloon
	AirBaloon airB;
	
	// Title normal game
	NormalGame normalGame;
	
	// Title battle mode
	BattleMode battleMode;
	
	// Title license
	LicenseTitle license;
	
	// Title password
	Password passwordTitle;
	
	// Title SuperBomberman
	SuperBombermanTitle title;
	
	public boolean endTransition = false;
	
	// Cursore
	Cursore cr = new Cursore(this, keyH);
	
	// FPS
	int FPS = 10;
	
	// Posizioni delle modalità
	int[] normal = cr.normalMode;
	int[] battle = cr.battleMode;
	int[] password = cr.password;
	
	// Modalità scelta
	int mod;
	
	// Image
	BufferedImage background;
	
	// Costruttore della classe
	public MenuPanel() {
		getImageBackground();
		this.setPreferredSize(new Dimension(screenWidth, screenHeigth));	// Imposta le dimensioni del pannello
		this.setBackground(Color.BLACK);									// Imposta lo sfondo di colore nero
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);											// Aggiungie la variabile che controlla i tasti premuti
		this.setFocusable(true);											// Prende i tasti premuti durante l'esecuzione del programma
		
		airShip = new AirShip(this);
		airShipS = new AirShip2(this);
		
		title = new SuperBombermanTitle(this);
		normalGame= new NormalGame(this);
		battleMode = new BattleMode(this);
		passwordTitle = new Password(this);
		airB = new AirBaloon(this);
		license = new LicenseTitle(this);
	}
	
	// Metodo che prende l'indirizzo dell'immagine dello sfondo
	public void getImageBackground() {
		try {
			
			// Sfondo
			background = ImageIO.read(getClass().getResourceAsStream("/tilesMenu/Tiles_Background.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// Metodo
	public void startMenuThread() {
		menuThread = new Thread(this);
		menuThread.start();
	}
	
	// Metodo che restituisce lo stato del thread
	public State getStateThread() { 
		try{
			return menuThread.getState();
		}catch(NullPointerException e) {
			return null;
		}
	}
	
	// Metodo che disegna il menu
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Disegno dello sfondo 
		g.drawImage(background, 0, 0, this);
		
		// Disegno del Air Ship
		airShip.draw(g);
		
		// Disegno dell'Air Baloon
		airB.draw(g);
		
		// Disegno del secondo Air Ship
		airShipS.draw(g);
		
		// Disegno del title super bomberman
		title.draw(g);
		
		if(title.endTransition) {
			
			if(endTransition) {
				
				// Disegno del cursore
				cr.draw(g);
				
				// Disegno del title normal game
				normalGame.draw(g);
				
				// Disegno del title battle mode
				battleMode.draw(g);
				
				// Disegno del title password
				passwordTitle.draw(g);
				
				// Disegno del title license
				license.draw(g);
				
			}
			else {
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, tileSize * tileSize, tileSize * tileSize);
			}
			
			endTransition = true;
		}
		
		g.dispose();
	}

	// Metodo dell'interfaccia Runnable
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(menuThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta >= 1) {
				
				update();
				repaint();
				
				delta --;
			}
		}
	}
	
	// Metodo che aggiorma la posizione del cursore
	public void update() {
		
		// Metodo che aggiorna la posizione del titolo "Super BomberMan"
		title.update();
		
		if(title.endTransition) {
			// Aggiorna la posizione del cursore
			cr.update();
		}
		
		// Aggiorna l'animazione dell'Air Ship
		airShip.update();
		
		// Aggiorna l'animazione 
		airB.update();
		
		// Controlla la modalità che è stata scelta
		mod = checkMode();
		
	}
	
	// Metodo che controlla se è stata premuta una modalità
	public int checkMode() {
		
		// Controlla se è stato premuto il tasto enter
		if(keyH.enter && title.endTransition) {
			audio.setFile(13);
			audio.start();
			if(cr.y == normal[1]) {
				menuThread = null;
				return 1;
			}
			else if(cr.y == battle[1]) {
				menuThread = null;
				return 2;
			}
			else if(cr.y == password[1]) {
				menuThread = null;
				return 3;
			}
		}
		return 0;
	}
	
}
