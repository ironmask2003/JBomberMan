package pointBar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//--> Classe che rappresenta l'orologio nella barra dei punti <--//

public class Clock {
	
						//-> Dichiarazione dei campi <-//
	
	// Pannello dei punti in cui inserire l'immagine
	PointPanel pp;
	
	// Immagini dell'orologio
	BufferedImage im1, im2, im3, im4, im5, im6, im7, im8;
	BufferedImage bottomClock;
	BufferedImage end1, end2, end3, end4;
	
	double delaySound = (5 * 1 * Math.pow(10, 9));
	double delay;
	
	public int end;
	
	// Timer
	ArrayList<Timer> timers;
	
	// Contatore per l'animazione dell'orologio
	int spriteNum = 1;
	int spriteCounter = 0;
	
	public int counterT = -2;
	
						//-> Costruttore della classe <-//
	public Clock(PointPanel pp) {
		this.pp = pp;
		
		// Creazione dell'arrayList
		timers = new ArrayList<Timer>();
		
		// Coordinate dei quadrati che segnano il timer
		int posXCounter = 29;
		int posY = 42;
		
		// Inizializza gli oggetti dell'arrayList
		for(int counterTimer = 0; counterTimer < 14; counterTimer ++) {
			timers.add(new Timer(pp, posXCounter, posY));
			posXCounter += 16 + 9;
		}
		
		posXCounter += 48 + 16;
		
		for(int counter = 14; counter < 28; counter ++) {
			
			timers.add(new Timer(pp, posXCounter, posY));
			posXCounter += 16 + 9;
		}
		
		// Metodo che prende le immagini dell'orologio
		getClockImage();
		
	}
	
	public void resetTime() {
		counterT = -2;
		spriteCounter = 0;
		spriteNum = 1;
		end = 0;
		pp.endTimer = false;
	}
	
	// Metodo che prende le immagini dalle risorse del progetto
	public void getClockImage() {
		
		try{
			
			//-> Immagini dell'orologio quando scorre il tempo
			im1 = ImageIO.read(getClass().getResourceAsStream("/tilesPoint/Tiles_Clock_1.png"));
			im2 = ImageIO.read(getClass().getResourceAsStream("/tilesPoint/Tiles_Clock_2.png"));
			im3 = ImageIO.read(getClass().getResourceAsStream("/tilesPoint/Tiles_Clock_3.png"));
			im4 = ImageIO.read(getClass().getResourceAsStream("/tilesPoint/Tiles_Clock_4.png"));
			im5 = ImageIO.read(getClass().getResourceAsStream("/tilesPoint/Tiles_Clock_5.png"));
			im6 = ImageIO.read(getClass().getResourceAsStream("/tilesPoint/Tiles_Clock_6.png"));
			im7 = ImageIO.read(getClass().getResourceAsStream("/tilesPoint/Tiles_Clock_7.png"));
			im8 = ImageIO.read(getClass().getResourceAsStream("/tilesPoint/Tiles_Clock_8.png"));
			
			//-> Immagini dell'orologio quando è finito il tempo
			end1 = ImageIO.read(getClass().getResourceAsStream("/tilesPoint/Tiles_ClockEnd_1.png"));
			end2 = ImageIO.read(getClass().getResourceAsStream("/tilesPoint/Tiles_ClockEnd_2.png"));
			end3 = ImageIO.read(getClass().getResourceAsStream("/tilesPoint/Tiles_ClockEnd_3.png"));
			end4 = ImageIO.read(getClass().getResourceAsStream("/tilesPoint/Tiles_ClockEnd_4.png"));
			
			//-> Parte inferiore dell'orlogio
			bottomClock = ImageIO.read(getClass().getResourceAsStream("/tilesPoint/Tiles_ClockBottom.png"));
			
		}catch(IOException e) { e.printStackTrace(); }
		
	}
	
	public void update() {
		
		if(counterT < 14) updateTimer();
		else { 
			if(System.nanoTime() > delay) {
				pp.gp.startSound(10);
				delay = System.nanoTime() + delaySound;
			}
			updateEndTimer(); 
		}
	}
	
	public void updateEndTimer() {
		
		if(end > 3) { pp.endTimer = true; }
		
		// Dopo ogni movimento aumenta la variabile in modo da cambiare l'immagine dell'orologio e creare un'animazione
		spriteCounter ++;
		
		// Controlla se la variabile è maggio di un numero in modo da cambiare più lentamente l'immagine dell'orologio
		if(spriteCounter > 15) {
			
			// Se è alla prima immagine del movimento che sta compiendo, passa alla seconda immagine
			if(spriteNum == 1) spriteNum = 2;
			// Se è alla seconda immagine passa alla terza
			else if(spriteNum == 2) spriteNum = 3;
			// Se è alla terza immagine passa alla quarta
			else if(spriteNum == 3) spriteNum = 4;
			// Se è alla terza immagine ritorna alla prima
			else if(spriteNum >= 4) { spriteNum = 1; end += 1; }
			
			// Azzera il counter
			spriteCounter = 0;
		}
	}
	
	public void updateTimer() {
		
		// Dopo ogni movimento aumenta la variabile in modo da cambiare l'immagine dell'orologio e creare un'animazione
		spriteCounter ++;
		
		// Controlla se la variabile è maggio di un numero in modo da cambiare più lentamente l'immagine dell'orologio
		if(spriteCounter > 150) {
			
			// Se è alla prima immagine del movimento che sta compiendo, passa alla seconda immagine
			if(spriteNum == 1) { 
				counterT += 1; 
				if(counterT < 14) spriteNum = 2;
				}
			// Se è alla seconda immagine passa alla terza
			else if(spriteNum == 2) spriteNum = 3;
			// Se è alla terza immagine passa alla quarta
			else if(spriteNum == 3) spriteNum = 4;
			// Se è alla terza immagine passa alla quinta
			else if(spriteNum == 4) spriteNum = 5;
			// Se è alla terza immagine passa alla sesta
			else if(spriteNum == 5) spriteNum = 6;
			// Se è alla terza immagine passa alla settima
			else if(spriteNum == 6) spriteNum = 7;
			// Se è alla terza immagine passa all'ottava
			else if(spriteNum == 7) spriteNum = 8;
			// Se è alla terza immagine ritorna alla prima
			else if(spriteNum == 8) spriteNum = 1;
			
			// Azzera il counter
			spriteCounter = 0;
		}
		
	}
	
	public void draw(Graphics g, int worldX, int worldY) {
		
		//-> Controlla se il tempo è finito
		if(counterT < 14) drawTimer(g, worldX, worldY);
		else drawEndTimer(g, worldX, worldY);
		
	}
	
	public void drawEndTimer(Graphics g, int worldX, int worldY) {
		
		//-> Switch per scelier l'immagine da rappresentare a schermo
		BufferedImage image = switch(spriteNum) {
		case 1 -> end1;
		case 2 -> end2;
		case 3 -> end3;
		case 4 -> end4;
		default -> throw new IllegalArgumentException("Unexpected value: " + spriteNum);
		};
		
		// Disegno dell'orologio
		g.drawImage(image, worldX, worldY, pp.tileSize + 8, pp.tileSize + 7, pp);
		g.drawImage(bottomClock, worldX, worldY + 15, pp.tileSize + 8, pp.tileSize + 15, pp);
		
		// Disegno dei quadrati del timer
		for(int counter = 0; counter < 14; counter ++) {
			timers.get(counter).setWorldY(worldY + 28);
			timers.get(counter).drawBlack(g);
		}
		
		for(int counter = 14; counter < 28; counter ++) {
			timers.get(counter).setWorldY(worldY + 28);
			timers.get(counter).drawWhite(g);
		}
	}
	
	public void drawTimer(Graphics g, int worldX, int worldY) {
		
		//-> Switch per scegliere l'immagine da rappresentare a schermo
		BufferedImage image = switch(spriteNum) {
		case 1 -> im1;
		case 2 -> im2;
		case 3 -> im3;
		case 4 -> im4;
		case 5 -> im5;
		case 6 -> im6;
		case 7 -> im7;
		case 8 -> im8;
		default -> throw new IllegalArgumentException("Unexpected value: " + spriteNum);
		};
		
		// Disegno dell'orologio
		g.drawImage(image, worldX, worldY, pp.tileSize + 8, pp.tileSize + 7, pp);
		g.drawImage(bottomClock, worldX, worldY + 15, pp.tileSize + 8, pp.tileSize + 15, pp);
		
		// Disegno dei quadrati del timer
		for(int counter = 0; counter < 28; counter ++) {
			
			timers.get(counter).setWorldY(worldY + 28);
			timers.get(counter).drawWhite(g);
		}
		
		// Cambia i quadrati che segnano in timer da bianco a nero indicando lo scadere del tempo
		if(counterT >= 0 && counterT < 28) for(int counter = 0; counter <= counterT; counter ++) timers.get(counter).drawBlack(g);
		
	}
}
