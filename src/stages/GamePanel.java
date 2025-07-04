package stages;

import javax.swing.JPanel;

import audio.AudioManager;

import java.awt.*;
import java.lang.Thread.State;
import java.util.ArrayList;

import entita.Enemy;
import entita.EnemyCuppen;
import entita.EnemyDenkyun;
import entita.EnemyPakupa;
import entita.EnemyPuropen;
import entita.Obstacle;
import entita.Player;
import entita.Portal;
import pointBar.PointPanel;
import powerUp.PowerUp;
import stageStart.FirstStage;
import stageStart.SecondStage;
import stageStart.StartStage;
import tile.TileManager;
import tile.TileManagerStage1_1;
import tile.TileManagerStage1_2;

public class GamePanel extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	// Dichiarazione dei campi
	final int originalTileSize = 16;
	final int scale = 3;
	
	// Impostazioni della finestra
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 17;
	public final int maxScreenRow = 15;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeigth = tileSize * maxScreenRow;
	
	// Impostazioni del campo di gioco
	public final int worldMaxCol = 17;
	public final int worldMaxRow = 13;
	public final int worldWidth = tileSize * worldMaxCol;
	public final int worldHeigth = tileSize * worldMaxRow;
	
	// Scirtta inizio stage
	StartStage titleStage;
	
	// Audio manager
	AudioManager audio = new AudioManager();
	
	public AudioManager back = new AudioManager();
	
	// Variabile che conta in quale livello è arrivato il player
	public int level = 1;
	
	// Numero di nemici
	public int numEnemy;
	
	// Variabile di controllo per chiudere il pannello
	public boolean close = false;
	
	// Lista di nemici
	public ArrayList<EnemyPuropen> listPuropen;
	
	// Portale per il prossimo stage
	public Portal portale;
	
	// FPS
	int FPS = 60;
	
	// Oggetto che controlla i tasti premuti
	KeyHandler keyH = new KeyHandler();
	
	// Thread del pannello
	Thread gameThread;
	
	// Pannello dei punti
	public PointPanel pp;
	
	// Player
	public Player player = new Player(this, keyH);
	
	// Tiles
	public TileManager tile;
	
	// Lista degli ostacoli
	public ArrayList<Obstacle> obs;
	
	// Secondo nemico
	public ArrayList<EnemyDenkyun> listDenkyun;
	
	// Terzo nemico
	public ArrayList<EnemyPakupa> listPakupa;
	
	// Quarto nemico
	public ArrayList<EnemyCuppen> listCuppen;
	
	// Lista dei powerUp droppati
	public ArrayList<PowerUp> listPowerUpDropped;
	
	// Lista completa dei nemici
	public ArrayList<Enemy> allEnemyList;
	
	// Controllo collisioni
	public CollisionChecker ck = new CollisionChecker(this);
	
	// Costruttore della classe
	public GamePanel() {
		this.pp = new PointPanel(this);
		this.setPreferredSize(new Dimension(screenWidth, screenHeigth));	// Imposta le dimensioni del pannello
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);																		// Aggiungie la variabile che controlla i tasti premuti
		this.addKeyListener(pp.keyH);
		this.setFocusable(true);																			// Prende i tasti premuti durante l'esecuzione del programma
		this.setBackground(Color.BLACK);															// Imposta lo sfondo di colore nero
		this.allEnemyList = new ArrayList<Enemy>();
		back.setFile(0);
		
		// Caricamento del primo stage
		loadStage1_1();
	}
	
	// Costruttore della classe che ciene utilizzato quando viene caricato l'ultima partita salvata
	public GamePanel(int level) {
		this.pp = new PointPanel(this);
		this.setPreferredSize(new Dimension(screenWidth, screenHeigth));	// Imposta le dimensioni del pannello
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);																		// Aggiungie la variabile che controlla i tasti premuti
		this.addKeyListener(pp.keyH);
		this.setFocusable(true);																			// Prende i tasti premuti durante l'esecuzione del programma
		this.setBackground(Color.BLACK);															// Imposta lo sfondo di colore nero
		this.allEnemyList = new ArrayList<Enemy>();
		this.level = level;
		back.setFile(0);
		
		// Controlla qualle livello era stato salvato e lo avvia
		switch(level) {
		case 1 : loadStage1_1(); break;
		case 2 : loadStage1_2(); break;
		}
	}
	
	// Metodo che restituisce lo stato del Thread
	public State getStateThread() {
		try {
			return gameThread.getState();
		}catch(NullPointerException e) {
			return null;
		}
	}
	
	// Metodo che carica gli oggetti del primo stage
	public void loadStage1_1() {
		
		// Mappa
		tile = new TileManagerStage1_1(this);
		
		obs = tile.obs;																								// Lista degli ostacoli
		
		titleStage = new FirstStage(this);																// Scritta inizio stage
		
		portale = new Portal(this, 11 * tileSize, 9 * tileSize);
		
		// Lista dei power up
		listPowerUpDropped = new ArrayList<PowerUp>();
		
		// Primo nemico
		listDenkyun = new ArrayList<EnemyDenkyun>();
		listDenkyun.add(new EnemyDenkyun(this));
		listDenkyun.add(new EnemyDenkyun(this));
		
		// Secondo nemico
		listPuropen = new ArrayList<EnemyPuropen>();
		listPuropen.add(new EnemyPuropen(this));
		listPuropen.add(new EnemyPuropen(this));
		
		allEnemyList.addAll(listDenkyun);
		allEnemyList.addAll(listPuropen);
	}
	
	// Metodo che carica gli oggetti del secondo stage
	public void loadStage1_2() {
		
		startSound(7);
		
		// Mappa
		tile = new TileManagerStage1_2(this);
		
		pp.ck.resetTime();
		
		level = 2;
		
		player.setDefaultValues();
		
		obs = tile.obs;																								// Lista degli ostacoli
		
		titleStage = new SecondStage(this);														// Scritta inizio stage
		
		portale = new Portal(this, 13 * tileSize, 5 * tileSize);
		
		// Terzo nemico
		listPakupa = new ArrayList<EnemyPakupa>();
		listPakupa.add(new EnemyPakupa(this));
		listPakupa.add(new EnemyPakupa(this));
		
		// Quarto nemico
		listCuppen = new ArrayList<EnemyCuppen>();
		listCuppen.add(new EnemyCuppen(this));
		listCuppen.add(new EnemyCuppen(this));
		
		allEnemyList.addAll(listPakupa);
		allEnemyList.addAll(listCuppen);
		
		// Lista dei power up
		listPowerUpDropped = new ArrayList<PowerUp>();
	}
	
	// Metodo che avvia il thread del pannello
	public void startGameThread() {
		
		back.setFile(0);
		
		// Creazione del Thread
		gameThread = new Thread(this);
		
		// Avvio del Thread
		gameThread.start();
		
		//-> Avvia il timer
		pp.setTimer();
	}
	
	// Metodo dell'interfaccia Runnable
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null && player.life >= 0) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta >= 1) {
				
				// Aggiorna la posizone del giocatore
				update();
				
				// Lo aggiorna nella finsetra
				repaint();
				
				delta --;
			}
		}
		
		back.stop();
		close = true;
		gameThread = null;
	}
	
	// Metodo che aggiorna le entita in campo
	public void update() {

		if(portale.continueStage == true) {
			switch(level) {
			case 1: loadStage1_2(); break;
			case 2 : 
				System.exit(0);
				break;
			}
		}
		
		if(titleStage.endSecondTransition) {
			
			if(portale.continueStage == true) {
				tile = new TileManagerStage1_2(this);
			}
			
			if(pp.endTimer == true) {
				pp.ck.resetTime();
				player.setDeath(true);
			}
			
			// Aggiorna l'animazione della scritta di inizio stage
			titleStage.updateBack();
			
			if(player.life != -1 && pp.ck.counterT < 28 && pp.stop != 1 && pp.ck.end == 0) {
				// Aggiornamento del player
				player.update();
				
				// Aggiornamento del nemico
				for(int ind = 0; ind < allEnemyList.size(); ind ++) {
					if(allEnemyList.get(ind).removeEnemy == true) { allEnemyList.remove(ind); ind --; continue; }
					allEnemyList.get(ind).update();
				}
			}
			
			for(int ind = 0; ind < listPowerUpDropped.size(); ind ++) {
				if(listPowerUpDropped.get(ind).taked == true) { listPowerUpDropped.remove(ind); ind --; continue; }
				listPowerUpDropped.get(ind).update();
			}
			
			// Aggiornamento del portale
			portale.update();
			
			// Aggiornamento degli ostacoli
			for(int ind = 0; ind < obs.size(); ind ++) {
				if(obs.get(ind).remove == true) { obs.remove(ind); ind --; continue; }
				obs.get(ind).update();
			}
			
			// Aggiorna gli ostacoli con la prossima immagina da visualizzare
			for(int k = 0; k < obs.size(); k ++) {
				obs.get(k).update();
			}
			
			// Aggiornamento delle immagini delle bombe
			player.updateBomb();
			
			// Aggiorna il pannello dei punti
			pp.update();
		}
		
		// Numero dei nemici in gioco
		numEnemy = allEnemyList.size();
	}
	
	// Metodo che rappresenta i vari oggetto 
	// all'interno del pannello 
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		// Controlla se la scritta dell'inizio stage è apparasa
		if(titleStage.endTransition) {
			
			// Controlla se la scritta dell'inizio stage ha iniziato la transazione per scomparire
			if(titleStage.endSecondTransition) {
				
				// Pannello dei punti
				pp.draw(g);
				
				Graphics2D g2 = (Graphics2D)g;
				
				// Mappa
				if(level == 1) { ((TileManagerStage1_1) tile).draw(g2); }
				else if (level == 2) { ((TileManagerStage1_2) tile).draw(g2); }
				
				// PowerUP
				for(PowerUp powerUp : listPowerUpDropped) {
					powerUp.draw(g);
				}
				
				// Portale
				portale.draw(g2);
				
				// Ostacoli
				for(int k = 0; k < obs.size(); k ++) {
					Obstacle o = obs.get(k);
					o.draw(g2);
				}
				
				// Bombe sganciate dal player
				player.drawBomb(g2);
				
				// Nemici
				for(Enemy enemy : allEnemyList) { enemy.draw(g2); }
				
				// Player
				player.draw(g2);
				
				// Scritta inizio stage
				titleStage.draw(g, titleStage.endTransition);
				titleStage.drawBackground(g);
				
				g2.dispose();
				
				return;
			}
			
			// Scritta inizio stage
			titleStage.draw(g, true);
			
			return;
			
		}
		
		// Scritta inizio stage
		titleStage.draw(g);
		
	}
	
	/** Metodo che avvia la musica */
	public void startSound(int i) {
		audio.setFile(i);
		audio.start();
	}
	
	/** Metodo che stoppa la musica */
	public void stopSound(int i) {
		audio.stop();
	}
	
	/** Metodo che manda in loop */
	public void loopSound(int i) {
		audio.setFile(i);
		audio.loop();
	}
	

}
