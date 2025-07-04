package entita;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import observer.Observer;
import powerUp.PowerUp;
import stages.GamePanel;
import stages.KeyHandler;

public class Player extends Entita implements Observer{
	
	// Dichiarazione dei campi
	GamePanel gp;										// Pannello del campo di gioco
	KeyHandler keyH;									// Oggetto che controlla i tasti premuti
	
	// Punti del player
	public int point = 0;
	
	// Lista delle bombe rilascate dal personaggio
	public ArrayList<PlayerBomb> bombe;
	
	// Variabile che limita la quantita di bombe che possono essere sganciate
	public int limitBomb = 1;
	
	// Immagini di quando muore il player
	BufferedImage death1, death2, death3, death4, death5, death6;
	
	//-> Immagini di quando il player si trova sopra il portale
	BufferedImage portal1, portal2, portal3, portal4, portal5, portal6, portal7, portal8, portal9;
	
	// Lista dei powerUp del player
	ArrayList<PowerUp> listPowerUp;
	
	// Delay audio passi
	double delaySoundStep;
	double delaySound = 0;
	
	// Delay per la pressione del tasto per lo sganciamento della bomba
	double delayBomb = (0.1 * 1 * Math.pow(10, 9));
	double delay = 0;
	
	// Vite del player
	public int life = 5;
	
	// Variabile booleana che controlla se il player è morto o no
	public boolean death = false;
	
	//-> Livelli di velocità
	final int speedLevelOne = 2;
	final int speedLevelTwo = 3;
	final int speedLevelThree = 4;
	
	//-> Variabile booleana per controllare se avviare o no l'animaizione per passare dal portale
	public boolean portal = false;
	
	//-> Timer di immortalità del player per quando spawna
	double timeImmortal = (10 * 1 * Math.pow(10, 9));
	public double immortal;
	
	// Costruttore della classe
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		// Lista delle bombe sganciate dal player
		bombe = new ArrayList<PlayerBomb>();
		
		// Lista dei power up presi dal player
		listPowerUp = new ArrayList<PowerUp>();
		
		// Creazione dell'area di impatto del player di Defaults
		solidAreaD = new Rectangle();
		solidAreaD.x = 13;
		solidAreaD.y = 16;
		solidAreaD.height = 30;
		solidAreaD.width = 32;
		
		solidArea = new Rectangle();
		solidArea.x = 13;
		solidArea.y = 16;
		solidArea.height = 30;
		solidArea.width = 32;
		
		// Metodo che imposta la posizione di partenza del player
		setDefaultValues();
		
		// Metodo che prende le immagini del player dalle risorse del progetto
		getPlayerImage();
	}
	
	// Metodo che imposta le coordinate iniziali e la velocità del player
	public void setDefaultValues() {
		speed = speedLevelOne;
		worldX = gp.tileSize * 2;
		worldY = gp.tileSize * 3;
		
		portal = false;
		spriteCounter = 0;
		spriteNum = 1;
		
		// Tempo in cui il player rimane immortale
		immortal = System.nanoTime() + timeImmortal;
		
		// Direzione di partenza
		direction = "down";
	}
	
	// Metodo che assegna alle direzione le relative immagini
	public void getPlayerImage() {
		try{
			
			// Immagini di quando il player si muove verso sopra
			up1 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_up_2.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_up_1.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_up_3.png"));
			
			// Immagini di quando il player si muove verso sotto
			down1 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_down_2.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_down_1.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_down_3.png"));
			
			// Immagini di quando il player si muove verso sinistra
			left1 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_left_2.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_left_1.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_left_3.png"));
			
			// Immagini di quando il player si muobe verso destra
			rigth1 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_rigth_2.png"));
			rigth2 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_rigth_1.png"));
			rigth3 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_rigth_3.png"));
			
			// Immagini di quando muore il player
			death1 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_death1.png"));
			death2 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_death2.png"));
			death3 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_death3.png"));
			death4 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_death4.png"));
			death5 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_death5.png"));
			death6 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_death6.png"));
			
			// Immagini di quando si trova sopra il portale
			portal1 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_portal1.png"));
			portal2 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_portal2.png"));
			portal3 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_portal3.png"));
			portal4 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_portal4.png"));
			portal5 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_portal5.png"));
			portal6 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_portal6.png"));
			portal7 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_portal7.png"));
			portal8 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_portal8.png"));
			portal9 = ImageIO.read(getClass().getResourceAsStream("/player1/Player1_portal9.png"));
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// Metodo che aggiorna le immagini e la posizione del personaggio
	@Override
	public void update() {
		
		// Calcolo della riga e colonna in cui si trova il player
		row = worldY/gp.tileSize;
		col = worldX/gp.tileSize;
		
		//-> Controlla se il player si trova sopra il portale
		if(portal == true) { updatePortal(); }
		//-> Controlla se il player è appena spawnato
		else if( System.nanoTime() < immortal ) { updateBeforeDeath(); }
		//-> Controlla se il player è ancora in vita
		else if( death == false) { updateBeforeDeath();}
		//-> Altrimenti passa all'animazione della morte del player
		else if (death == true ) { updateDeath(); }
	}
	
	// Metodo che aggiorna l'animazione quando il player si trova sopra il portale e tutti i nemici sono stati sconfitti
	public void updatePortal() {
		
		// Dopo ogni movimento aumenta la variabile in modo da cambiare l'immagine del personaggio e creare un'animazione
		spriteCounter ++;
		
		// Controlla se la variabile è maggio di un numero in modo da cambiare più lentamente l'immagine del personaggio
		if(spriteCounter > 25) {
			
			if(spriteNum == 1) spriteNum = 2;
			else if(spriteNum == 2) spriteNum = 3;
			else if(spriteNum == 3) spriteNum = 4;
			else if(spriteNum == 4) spriteNum = 5;
			else if(spriteNum == 5) spriteNum = 6;
			else if(spriteNum == 6) spriteNum = 7;
			else if(spriteNum == 7) spriteNum = 8;
			else if(spriteNum == 8) spriteNum = 9;
			else if(spriteNum == 9) spriteNum = 0;
			else if(spriteNum == 0) {
				setDefaultValues();
				gp.portale.continueStage = true;
			}
			
			// Azzera il counter
			spriteCounter = 0;
		}
		
	}
	
	// Metodo che aggiorna le immagini del personaggio quando muore
	public void updateDeath() {
		
		collision = true;
		// Dopo ogni movimento aumenta la variabile in modo da cambiare l'immagine del personaggio e creare un'animazione
		spriteCounter ++;
		
		// Controlla se la variabile è maggio di un numero in modo da cambiare più lentamente l'immagine del personaggio
		if(spriteCounter > 16) {
			
			// Se è alla prima immagine del movimento che sta compiendo, passa alla seconda immagine
			if(spriteNum == 1) spriteNum = 2;
			// Se è alla seconda immagine passa alla terza
			else if(spriteNum == 2) spriteNum = 3;
			// Se è alla terza immagine passa alla quarta
			else if(spriteNum == 3) spriteNum = 4;
			// Se è alla quarta immagine passa alla quinta
			else if(spriteNum == 4) spriteNum = 5;
			// Se è alla quinta immagine passa alla sesta
			else if(spriteNum == 5) spriteNum = 6;
			// Se è alla sesta immagine azzera il counter
			else if(spriteNum == 6) spriteNum = 7;
			else if(spriteNum == 7) spriteNum = 0;
			
			// Azzera il counter
			spriteCounter = 0;
		}
		
	}
	
	// Metodo che aggiorna le immagini e la posiozione del personaggio se ancora in vita
	public void updateBeforeDeath() {
		
		// Cambio del delay del suono in base alla velocità del player
		switch(speed) {
		case speedLevelOne: delaySoundStep = (0.3 * 1 * Math.pow(10, 9)); break;
		case speedLevelTwo: delaySoundStep = (0.2 * 1 * Math.pow(10, 9)); break;
		case speedLevelThree: delaySoundStep = (0.15 * 1 * Math.pow(10, 9)); break;
		}
		
		// Controlla se è stato premuto un tasto
		if(keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed || keyH.close || keyH.bomb) {
			
			// Controlla quale tasto è stato premuto
			if(keyH.upPressed == true) {
				direction = "up";
				if(System.nanoTime() > delaySound) {
					gp.startSound(2);
					delaySound = System.nanoTime() + delaySoundStep;
				}
			}
			else if(keyH.downPressed == true) {
				direction = "down"; 
				if(System.nanoTime() > delaySound) {
					gp.startSound(2);
					delaySound = System.nanoTime() + delaySoundStep;
				}
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
				if(System.nanoTime() > delaySound) {
					gp.startSound(2);
					delaySound = System.nanoTime() + delaySoundStep;
				}
			}
			else if(keyH.rightPressed == true) {
				direction = "rigth";
				if(System.nanoTime() > delaySound) {
					gp.startSound(2);
					delaySound = System.nanoTime() + delaySoundStep;
				}
			}
			// Se viene premuto il tasto ESC chiude il gioco
			else if(keyH.close) {
				System.exit(0);
			}
			if(keyH.bomb && bombe.size() < limitBomb && System.nanoTime() > delay) {
				direction = "bomb";
				delay = System.nanoTime() + delayBomb;
				PlayerBomb bomb = new PlayerBomb(worldX, worldY, gp);
				bombe.add(bomb);
			}
			
			// Controllo collisioni
			collision = false;
			gp.ck.checkTile(this);
			
			// Se la variabile collision è su false il player si può muovere
			if(collision == false) {
				
				switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "rigth":
					worldX += speed;
					break;
				case "bomb":
					break;
				}
				
			}
			
			// Dopo ogni movimento aumenta la variabile in modo da cambiare l'immagine del personaggio e creare un'animazione
			spriteCounter ++;
			
			// Controlla se la variabile è maggio di un numero in modo da cambiare più lentamente l'immagine del personaggio
			if(spriteCounter > 8) {
				
				// Se è alla prima immagine del movimento che sta compiendo, passa alla seconda immagine
				if(spriteNum == 1) spriteNum = 2;
				// Se è alla seconda immagine passa alla terza
				else if(spriteNum == 2) spriteNum = 3;
				// Se è alla terza immagine ritorna alla prima
				else if(spriteNum == 3) spriteNum = 1;
				
				// Azzera il counter
				spriteCounter = 0;
			}
		}
	}
	
	
	// Metodo che rappresenta il personaggio mentre è in vita
	public void drawBeforeDeath(Graphics2D g2) {
		
		// switch che controlla quale immagine deve essere visualizzata
		BufferedImage image = switch(direction) {
		// Caso in cui il personaggio si sta muovemnto verso sopra
		case "up" -> switch(spriteNum) {
			case 1 -> up1;			// Prima immagine di questo caso
			case 2 -> up2;		// Seconda immagine di questo caso 
			case 3 -> up3;		// Terza immagina di questo caso
		default -> up1;
		};
		// Caso in cui il personaggio si sta muovemnto verso sotto
		case "down" -> switch(spriteNum) {
			case 1 -> down1;	// Prima immagine di questo caso
			case 2 -> down2;	// Seconda immagine di questo caso
			case 3 -> down3;	// Terza immagine di questo caso
			default -> down1;
		};
		// Caso in cui il personaggio si sta muovemnto verso sinistra
		case "left" -> switch(spriteNum) {
			case 1 -> left1;		// Prima immagine di questo caso
			case 2 -> left2;		// Seconda immagine di questo caso
			case 3 -> left3;		// Terza immagine di questo caso
			default -> left1;
		};
		// Caso in cui il personaggio si sta muovendo verso destra
		case "rigth" -> switch(spriteNum) {
			case 1 -> rigth1;		// Prima immagine di questo caso
			case 2 -> rigth2;	// Seconda immagine di questo caso
			case 3 -> rigth3;	// Terza immagine di questo caso
			default -> rigth1;
		};
		default -> down1;		// Immagine di default
		};
		
		// Aggiorna l'immagine del personaggio e la posizione
		g2.drawImage(image, worldX, worldY, gp.tileSize + 10, gp.tileSize + 5, null);
	}
	
	// Metodo che rappresenta il personaggio quando è morto
	public void drawDeath(Graphics2D g2) {
		
		BufferedImage image;
		
		// Switch per scegliere quale immagine da rappresentare per l'animazione della morte del player
		switch(spriteNum) {
		case 1: image = death1; break;
		case 2: image = death2; break;
		case 3: image = death3; break;
		case 4: image = death4; break;
		case 5: image = death5; break;
		case 6: image = death6; break;
		case 7: image = null; break;
		default:
			// Quando muore scomprare e ritorna nella posizione iniziale impostando su false la variabile death
			image = null;
			death = false;
			setDefaultValues();
			decraseLife();
			//-> Quanfo il player muore reimposta il timer
			gp.pp.setTimer();
		}
		
		// Aggiorna l'immagine del personaggio e la posizione
		g2.drawImage(image, worldX, worldY, gp.tileSize + 10, gp.tileSize + 5, null);
		
	}
	
	// Metodo che disegna il player quando si trova sopra il portale e i nemici sono stati sconfitti
	public void drawPortal(Graphics2D g2) {
		
		BufferedImage image = switch(spriteNum) {
		case 1 -> portal1;
		case 2 -> portal2;
		case 3 -> portal3;
		case 4 -> portal4;
		case 5 -> portal5;
		case 6 -> portal6;
		case 7 -> portal7;
		case 8 -> portal8;
		case 9 -> portal9;
		default -> null;
		};
		
		g2.drawImage(image, worldX + 5, worldY, gp.tileSize - 10, gp.tileSize, null);
		
	}
	
	// Metodo che rappresenta il personaggio nello schermo
	public void draw(Graphics2D g2) {
		
		//-> Controlla se il player è sopra il portale
		if(portal == true) { drawPortal(g2); return; }
		//-> Controlla se il player è appena spawnato
		else if( System.nanoTime() < immortal ) { drawBeforeDeath(g2); death = false; }
		//-> Controlla se il player è ancora in vita
		else if( death == false ) { drawBeforeDeath(g2); death = false; }
		//-. Altrimenti passa all'animazione della morte del player
		else drawDeath(g2);
		
	}
	
	// Metodo che aggiorna le imamgini delle bombe
	public void updateBomb() {
		// Aggiornamento delle immagini delle bombe
		for(int k = 0; k < bombe.size(); k ++) {
			if(bombe.get(k).ex.endExplosion) { bombe.remove(k); k --; continue;}
			bombe.get(k).update();
		}
	}
	
	// Metodo
	public void drawBomb(Graphics2D g2) {
		// Aggiornamento delle immagini delle bombe
		for(int k = 0; k < bombe.size(); k ++) {
			bombe.get(k).draw(g2);
		}
	}
	
	// Metodo che prende il power up
	public void getPowerUp(PowerUp powerUp) {
		// Metodo che aggiungie il powerUp alla lista dei powerUp del player
		listPowerUp.add(powerUp);
		// Controlla quale powerUp è stato preso
		if(powerUp.powerUp == 0) { if(life < 9) life += 1; }												// Power Up della vita
		else if(powerUp.powerUp == 1) {																			// Power Up della velocità
			speed = switch(speed) {
			case speedLevelOne -> speedLevelTwo;
			case speedLevelTwo -> speedLevelThree;
			case speedLevelThree -> speedLevelThree;
			default -> 1;
			};
		}
		else if(powerUp.powerUp == 2) { if(limitBomb < 10) limitBomb += 1; }				// Power Up per quante bombe possono essere sganciate
	}
	
	// Metodo che imposta la variabile booleana portal a true e avviando l'animaizione per il cambio di livello
	public void setPortal() {
		portal = true; 
		worldX = gp.portale.worldX;
		worldY = gp.portale.worldY;
		spriteNum = 1;
		spriteCounter = 0;
	}
	
	// Metodo che aggiorna i punti del player
	public void increasePoint(int p) { point += p; }
	
	// Metodo che diminuisce la vita del player
	public void decraseLife() { life -= 1; }
	
	// Metodo che imposta il valore della variabile death
	public void setDeath(boolean death) { this.death = death; spriteNum = 1; gp.startSound(4);}
}
