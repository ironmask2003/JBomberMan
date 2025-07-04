package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.Thread.State;

import audio.AudioManager;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class NormalModeMap extends JPanel implements Runnable{
	
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
	
	int playerCounter = 0;
	boolean apparead = true;
	
	int spriteNum = 1;
	int spriteCounter = 0;
	
	// Audio di sottofondo
	AudioManager back = new AudioManager();
	AudioManager select = new AudioManager();
	
	// FPS
	int FPS = 60;
	
	KeyHandlerMenu keyH = new KeyHandlerMenu();
	Thread map;
	
	// Image
	BufferedImage mapImage, playerMap;
	
	// Image per l'animazione dell'acqua
	BufferedImage water, water1, water2, water3, water4, water5, water6;
	
	// Background
	BufferedImage back1, back2, back3, back4, back5;
	int spriteNumBack = 1;
	int spriteCounterBack = 0;
	
	// Costruttore della classe
	public NormalModeMap() {
		select.setFile(13);
		back.setFile(14);
		this.setPreferredSize(new Dimension(screenWidth, screenHeigth));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
		// Metodo che prende l'immagine della mappa
		getImgaeMap();
	}

	// Metodo che prende l'immagine dalle risore del progetto
	public void getImgaeMap() {
		try{
			
			mapImage = ImageIO.read(getClass().getResourceAsStream("/maps/Map_NormalMode.png"));
			playerMap = ImageIO.read(getClass().getResourceAsStream("/maps/MenuMap_Player.png"));
			
			water = ImageIO.read(getClass().getResourceAsStream("/maps/Water1.png"));
			water1 = ImageIO.read(getClass().getResourceAsStream("/maps/Water2.png"));
			water2 = ImageIO.read(getClass().getResourceAsStream("/maps/Water3.png"));
			water3 = ImageIO.read(getClass().getResourceAsStream("/maps/Water4.png"));
			water4 = ImageIO.read(getClass().getResourceAsStream("/maps/Water5.png"));
			water5 = ImageIO.read(getClass().getResourceAsStream("/maps/Water6.png"));
			water6 = ImageIO.read(getClass().getResourceAsStream("/maps/Water7.png"));
			
			back1 = ImageIO.read(getClass().getResourceAsStream("/background/Background_Black_1.png"));
			back2 = ImageIO.read(getClass().getResourceAsStream("/background/Background_Black_2.png"));
			back3 = ImageIO.read(getClass().getResourceAsStream("/background/Background_Black_3.png"));
			back4 = ImageIO.read(getClass().getResourceAsStream("/background/Background_Black_4.png"));
			back5 = ImageIO.read(getClass().getResourceAsStream("/background/Background_Black_5.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setThreadMap() {
		map = new Thread(this);
		map.start();
		back.start();
	}
	
	// Metodo dell'interfaccia Runnable
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(map != null) {
			
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
	
	public State getStateThread() {
		try {
			return map.getState();			
		}catch(NullPointerException e) {
			return null;
		}
	}
	
	public void updateBack() {
		
		spriteCounterBack ++;
		
		if(spriteCounterBack > 5) {
			
			if(spriteNumBack == 1) spriteNumBack = 2;
			else if(spriteNumBack == 2) spriteNumBack = 3;
			else if(spriteNumBack == 3) spriteNumBack = 4;
			else if(spriteNumBack == 4) spriteNumBack = 5;
			else if(spriteNumBack == 5) spriteNumBack = 0;
			
			spriteCounterBack = 0;
		}
		
	}
	
	public void update() {
		
		if(keyH.enter == true) {
			back.stop();
			select.start();
			map = null;
		}
		
		updateBack();
		
		// Dopo ogni movimento aumenta la variabile in modo da cambiare l'immagine dell'acqua e creare un'animazione
		spriteCounter ++;
		
		// Controlla se la variabile è maggio di un numero in modo da cambiare più lentamente l'immagine dell'acqua
		if(spriteCounter > 8) {
			
			// Se è alla prima immagine del movimento che sta compiendo, passa alla seconda immagine
			if(spriteNum == 1) spriteNum = 2;
			// Se è alla seconda immagine passa alla terza
			else if(spriteNum == 2) spriteNum = 3;
			// Se è alla terza immagine ritorna alla prima
			else if(spriteNum == 3) spriteNum = 4;
			else if(spriteNum == 4) spriteNum = 5;
			else if(spriteNum == 5) spriteNum = 6;
			else if(spriteNum == 6) spriteNum = 7;
			else if(spriteNum == 7) spriteNum = 1;
			
			// Azzera il counter
			spriteCounter = 0;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawWater(g);
		
		g.drawImage(mapImage, 0, 0, this);
		
		drawPlayer(g);
		
		drawBackground(g);
		
		g.dispose();
	}
	
	public void drawBackground(Graphics g) {
		
		BufferedImage image = switch(spriteNumBack) {
		case 0 -> null;
		case 1 -> back1;
		case 2 -> back2;
		case 3 -> back3;
		case 4 -> back4;
		case 5 -> back5;
		default -> throw new IllegalArgumentException("Unexpected value: " + spriteNumBack);
		};
		
		g.drawImage(image, 0, 0, tileSize * 20, tileSize * 20, this);
		
	}
	
	//-> Metodo che disegna il player sulla mappa
	public void drawPlayer(Graphics g) {
		
		if(playerCounter < 20 && apparead == true) {
			g.drawImage(playerMap, (3*tileSize)+20, 9*tileSize, this);
		}
		else if(playerCounter < 20 && apparead == false){}
		else {
			if(apparead == true) { apparead = false; }
			else { apparead = true; }
			playerCounter = 0;
		}
		
		playerCounter += 1;
		
	}
	
	//-> metodo che anima l'acqua attorno alla mappa
	public void drawWater(Graphics g) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < maxScreenCol && row < maxScreenRow ) {
			
			BufferedImage image = switch(spriteNum) {
			case 1 -> water;
			case 2 -> water1;
			case 3 -> water2;
			case 4 -> water3;
			case 5 -> water4;
			case 6 -> water5;
			case 7 -> water6;
			default -> water;
			};
			
			g.drawImage(image, x, y, tileSize, tileSize, null);
			
			col++;
			x += tileSize;
			if(col == maxScreenCol) {
				col = 0;
				x = 0;
				row ++;
				y += tileSize;
			}
			
		}
		
	}
	
	// Crea il pannelo in cui mostra la mappa della normal mode prima del gameplay
	public void createPanel() {
		JFrame window = new JFrame();
		NormalModeMap mp = new NormalModeMap();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("JBomberMan");
		window.add(mp);
		window.pack();
		start(window, mp);
	}
	
	// Rende visibile la finestra al centro dello schermo avviando il thread
	public void start(JFrame window, NormalModeMap mp) {
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		mp.setThreadMap();
		
		// Controlla se il thread è ancora attivo
		while(mp.getStateThread() != null) {
			continue;
		}
		
		// Appena viene annullato il thread (Ovvero quando viene premuto il tasto ENTER) chiude la finestra
		close(window);
	}
	
	// Metodo che chiude la finestra
	public void close(JFrame window) {
		window.dispose();
	}
}
