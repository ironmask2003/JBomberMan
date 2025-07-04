package continuePasswordPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.Thread.State;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import audio.AudioManager;
import data.DataStorage;
import data.SaveLoad;
import title.PassowrdP;

public class PasswordPanel extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	
	// Dichiarazione dei campi
	
	// Impostazioni del pannello
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 17;
	public final int maxScreenRow = 14;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeigth = tileSize * maxScreenRow;
	
	KeyHandlerContinuePage keyH = new KeyHandlerContinuePage();
	Thread panelThread;
	AudioManager back = new AudioManager();
	
	// Password da mostrare a schermo
	Password password;
	
	// Delay per la pressione del tasto
	double delay = 0.1 * Math.pow(10, 9);
	double delaySystem;
	
	// Title Password
	PassowrdP titlePassword;
	
	// FPS
	int FPS = 60;
	
	// Immagine del bordo della pagina
	BufferedImage border;
	
	// Backrgound Nero 
	BufferedImage black1, black2, black3, black4, black5;
	
	// Animazione del background Nero
	int backNum = 1;
	int backCounter = 0;
	
	// Immagine di background
	BufferedImage back1, back2, back3, back4, back5, back6, back7, back8, back9;
	
	// Animazione del background
	int spriteNumBack = 1;
	int spriteCounterBack = 0;
	
	// Oggetto pre caricare i dati dell'ultimo salvataggio
	SaveLoad load;
	
	// Variabile booleana che controlla se la password inserita è corretta
	public boolean correct = false;
	
	// Ultimo salvataggio
	DataStorage ds;
	
	// Livello salvato nel file di salvataggio
	public int level;
	
	// Cursore
	ContinuePageCursore cursorPage;
	
	// Costruttore della classe
	public PasswordPanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeigth));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		load = new SaveLoad(null, null);
		
		back.setFile(15);
		ds = load.load();
		level = 0;
		password = new Password(this, keyH, ds.password);
		cursorPage = new ContinuePageCursore(this, keyH);
		titlePassword = new PassowrdP(this);
		getImagePage();
	}
	
	public void setThreadPanel() {
		panelThread = new Thread(this);
		panelThread.start();
		back.loop();
	}
	
	// Metodo che prende le immagini dalle risorse del progetto
	public void getImagePage() {
		
		try {
			
			// Sfondo del pannello
			back1 = ImageIO.read(getClass().getResourceAsStream("/continuePage/Background.png"));
			back2 = ImageIO.read(getClass().getResourceAsStream("/continuePage/Background2.png"));
			back3 = ImageIO.read(getClass().getResourceAsStream("/continuePage/Background3.png"));
			back4 = ImageIO.read(getClass().getResourceAsStream("/continuePage/Background4.png"));
			back5 = ImageIO.read(getClass().getResourceAsStream("/continuePage/Background5.png"));
			back6 = ImageIO.read(getClass().getResourceAsStream("/continuePage/Background6.png"));
			back7 = ImageIO.read(getClass().getResourceAsStream("/continuePage/Background7.png"));
			back8 = ImageIO.read(getClass().getResourceAsStream("/continuePage/Background8.png"));
			back9 = ImageIO.read(getClass().getResourceAsStream("/continuePage/Background9.png"));
			
			// Sfondo nero
			black1 = ImageIO.read(getClass().getResourceAsStream("/background/Background_Black_1.png"));
			black2 = ImageIO.read(getClass().getResourceAsStream("/background/Background_Black_2.png"));
			black3 = ImageIO.read(getClass().getResourceAsStream("/background/Background_Black_3.png"));
			black4 = ImageIO.read(getClass().getResourceAsStream("/background/Background_Black_4.png"));
			black5 = ImageIO.read(getClass().getResourceAsStream("/background/Background_Black_5.png"));
			
			// Corince
			border = ImageIO.read(getClass().getResourceAsStream("/continuePage/Border_continuePage.png"));
			
		}catch(IOException e ) { e.printStackTrace(); }
		
	}
	
	// Metodo che aggiorna la pagina
	public void update() {
		
		updateBlackBack();
		
		password.update();
		
		if(keyH.enter && System.nanoTime() > delaySystem) {
			cursorPage.select.start();
			if(password.passwordString.equals(ds.password)) {
				level = ds.level;
			}
			back.stop();
			panelThread = null;
			delaySystem = System.nanoTime() + delay;
		}
		
		cursorPage.update();
		
		updateBackground();
	}
	
	// Metodo che aggiorna l'animazione del background nero
	public void updateBlackBack() {
		
		backCounter ++;
		
		if(backCounter > 6) {
			
			if(backNum == 1) backNum = 2;
			else if(backNum == 2) backNum = 3;
			else if(backNum == 3) backNum = 4;
			else if(backNum == 4) backNum = 5;
			else if(backNum == 5) backNum = 0;
			
			backCounter = 0;
			
		}
		
	}
	
	// Metodo che aggiorna l'animazione del background
	public void updateBackground() {
		
		spriteCounterBack ++;
		
		if(spriteCounterBack > 1) {
			
			if(spriteNumBack == 1) spriteNumBack = 2;
			else if(spriteNumBack == 2) spriteNumBack = 3;
			else if(spriteNumBack == 3) spriteNumBack = 4;
			else if(spriteNumBack == 4) spriteNumBack = 5;
			else if(spriteNumBack == 5) spriteNumBack = 6;
			else if(spriteNumBack == 6) spriteNumBack = 7;
			else if(spriteNumBack == 7) spriteNumBack = 8;
			else if(spriteNumBack == 8) spriteNumBack = 9;
			else if(spriteNumBack == 9) spriteNumBack = 1;
			
			spriteCounterBack = 0;
			
		}
		
	}
	
	// Metodo che disegna gli elementi del pannello
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Disegno dello sfondo
		drawBackground(g);
		
		// Disegno del bordo
		g.drawImage(border, 121, 97, tileSize * 12, tileSize * 10, this);
		
		// Disegno del cursore
		cursorPage.draw(g);
		
		// Disegno del title password
		titlePassword.draw(g);
		
		// Disegno della password
		password.draw(g);
		
		// Disegno dell'immagine nera per la transazione iniziale
		drawBlackBack(g);
		
		g.dispose();
	}
	
	// Metodo che disegna il background nero
	public void drawBlackBack(Graphics g) {
		
		// Scelta dell'immagine in base alla variabile backNum per creare un'animazione
		BufferedImage image = switch(backNum) {
		case 1 -> black1;
		case 2 -> black2;
		case 3 -> black3;
		case 4 -> black4;
		case 5 -> black5;
		default -> null;
		};
		
		// Disegno dell'immagine
		g.drawImage(image, 0, 0, tileSize * tileSize, tileSize * tileSize, null);
		
	}

	// Metodo che disegna il background del pannello
	public void drawBackground(Graphics g) {
		
		// Controlla quale immagine scegliere per creazre l'animazione
		BufferedImage image = switch(spriteNumBack) {
		case 1 -> back1;
		case 2 -> back2;
		case 3 -> back3;
		case 4 -> back4;
		case 5 -> back5;
		case 6 -> back6;
		case 7 -> back7;
		case 8 -> back8;
		case 9 -> back9;
		default -> throw new IllegalArgumentException("Unexpected value: " + spriteNumBack);
		};
		
		// Itera sul pannello per riempire il background dell'immagine scelta
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		// Ciclo che si ripete fino a quando arriva all'ulitma colonna dell'ultima riga
		while(col < maxScreenCol && row < maxScreenRow ) {
			
			// Disegno dell'immagine
			g.drawImage(image, x, y, tileSize, tileSize, null);
			
			// Incremento della colonna
			col++;
			// Incremento della posizione x per disegnare l'immagine 
			x += tileSize;
			// Controlla se è arrivato all'ultima colonna della riga
			if(col == maxScreenCol) {
				// Reimposta le colonne a 0
				col = 0;
				// Reimposta la coordinata x a 0
				x = 0;
				// Incrementa la riga
				row ++;
				// Incrementa la coordinata y
				y += tileSize;
			}
			
		}
	}
	
	// metodo che resituisce lo stato del Thread
	public State getStateThread() {
		try { return panelThread.getState();			
		}catch(NullPointerException e) { return null; }
	}

	// Metodo dell'interfaccia Runnable
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(panelThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if(delta >= 1) {
				
				// Aggiorna la posizone del giocatore
				update();
				repaint();
				
				delta --;
			}
		}
		
	}
	
	// Crea il pannelo in cui mostra la mappa della normal mode prima del gameplay
	public int createPanel() {
		JFrame window = new JFrame();
		PasswordPanel mp = new PasswordPanel();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("JBomberMan");
		window.add(mp);
		window.pack();
		return start(window, mp);
	}
	
	// Rende visibile la finestra al centro dello schermo avviando il thread
	public int start(JFrame window, PasswordPanel mp) {
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		mp.setThreadPanel();
		
		// Controlla se il thread è ancora attivo
		while(mp.getStateThread() != null) {
			continue;
		}
		
		// Appena viene annullato il thread (Ovvero quando viene premuto il tasto ENTER) chiude la finestra
		close(window);
		
		return mp.level;
	}

	// Metodo che chiude la finestra
	public void close(JFrame window) {
		window.dispose();
	}
}
