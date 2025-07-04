package entita;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import powerUp.PowerUp;
import stages.GamePanel;
import tile.TileManagerStage1_1;

public class EnemyDenkyun extends Enemy {
	
	// Dichiarazione dei campi
	
	// Variabili di controllo per l'animazione del nemico quando muore
	int counter = 0;
	boolean apparead = true;
	int remove = 0;
	
	// Immagini del nemico
	BufferedImage im1, im2, im3, im4, im5, im6;
	
	// PowerUp che nasconde il nemico appena ucciso
	PowerUp powerUp;
	int power;
	
	// Lista delle direzioni
	String[] directions = new String[] {"up", "left", "down", "rigth"};
	
	// Variabile che segna il valore dei punti da dare al player nel caso in cui viene ucciso
	public int point = 400;
	
	// Costruttore della classe
	public EnemyDenkyun(GamePanel gp) {
		this.gp = gp;
		
		// Creazione dell'area di impatto dell'entità di Default
		solidAreaD = new Rectangle();
		solidAreaD.x = 3;
		solidAreaD.y = 3;
		solidAreaD.height = 44;
		solidAreaD.width = 44;
		
		// Creazione dell'area di impatto dell'entità di Default
		solidArea = new Rectangle();
		solidArea.x = 3;
		solidArea.y = 3;
		solidArea.height = 44;
		solidArea.width = 44;
		
		getImageEnemy();
		setDefaulValues();
	}
	
	// Costruttore che prende anche le coordinate in input
	public EnemyDenkyun(GamePanel gp, int worldX, int worldY) {
		
		this.gp = gp;
		this.worldX = worldX;
		this.worldY = worldY;
		
		// Creazione dell'area di impatto dell'entità di Default
		solidAreaD = new Rectangle();
		solidAreaD.x = 3;
		solidAreaD.y = 3;
		solidAreaD.height = 44;
		solidAreaD.width = 44;
		
		// Creazione dell'area di impatto dell'entità di Default
		solidArea = new Rectangle();
		solidArea.x = 3;
		solidArea.y = 3;
		solidArea.height = 44;
		solidArea.width = 44;
		
		// Velocità con la quale si sposta
		speed = 2;
		
		// Direzione di partenza
		direction = "up";
		
		getImageEnemy();
		
	}
	
	// Metodo che imposta i valori di defeault del nemico 
	public void setDefaulValues() {
		
		// Posizionamento casuale del nemico
		while(true) {
			
			int r = (int) (Math.random() * gp.worldMaxRow);
			int c = (int) (Math.random() * gp.worldMaxCol);
			
			int check1 = 0; int check2 = 0;
			
			if(gp.tile instanceof TileManagerStage1_1) { check1 = 1; check2 = 2; }
			
			if(gp.tile.mapTileNum[c][r] == check1 || gp.tile.mapTileNum[c][r] == check2) {
				if((r == 2 && c == 1) || (r == 2 && c == 2) || (r == 3 && c == 1)) { continue; }
				row = r + 2;
				col = c;
				if(equalsCoordinate(this)) { continue; }
				worldX = c * gp.tileSize;
				worldY = (r + 2) * gp.tileSize;
				break;
			}
			
		}
		
		// Velocità con la quale si sposta
		speed = 2;
		
		// Direzione di partenza
		direction = "up";
		
	}
	
	// Metodo che prende le immagini dalle risorse del progetto
	public void getImageEnemy() {
		
		try {
			
			im1 = ImageIO.read(getClass().getResourceAsStream("/denkyun/Enemy2_im1.png"));
			im2 = ImageIO.read(getClass().getResourceAsStream("/denkyun/Enemy2_im2.png"));
			im3= ImageIO.read(getClass().getResourceAsStream("/denkyun/Enemy2_im3.png"));
			im4 = ImageIO.read(getClass().getResourceAsStream("/denkyun/Enemy2_im4.png"));
			im5 = ImageIO.read(getClass().getResourceAsStream("/denkyun/Enemy2_im5.png"));
			im6 = ImageIO.read(getClass().getResourceAsStream("/denkyun/Enemy2_im6.png"));
			
			// Immagine dei punti
			imagePoint = ImageIO.read(getClass().getResourceAsStream("/denkyun/Enemy2_Point.png"));
			
		}catch(IOException e) { e.printStackTrace(); }
		
	}
	
	// Metodo che aggiorna la posizione dell'entità e l'animazione
	public void update() {
		
		if(hitted > 30) { death = true; }
		
		// Calcolo della riga e della colonna in si trova
		row = worldY/gp.tileSize;
		col = worldX/gp.tileSize;
		
		// Controlla se il nemico è morto o no
		if(death == true) {
			updateAfterDeath(); 
			pointimageY = worldY - incImageY;
			// Animazione per l'immagine dei punti
			spriteCounterPointimage ++;
			// Dealy per incrementare le coordinate dell'immagine
			if(spriteCounterPointimage > 80) incImageY += 1;
		}
		else { updateBeforeDeath(); }
	}
	
	// Metodo che aggiorna l'animazione del nemico quando viene sconfitto
	public void updateAfterDeath() {
		updateAnimation();
	}
	
	// Metodo che aggiorna l'animazione dell'entità quando è in vita
	public void updateBeforeDeath() {
		
		// Controlla le collisioni
		collision = false;
		gp.ck.checkTile(this);
		
		// Dopo il controllo delle colliosni se ha trovato un ostacolo sceglie randomicamente un'altra direzione
		ArrayList<String> temp = new ArrayList<String>();
		if(collision == true ) { 
			while(true) {
				// Sceglie un altra direzione
				direction = directions[(int) (Math.random() * 4)];
				// Controlla se la lista delle direzioni controllate contiene già tuttte le direzion
				if (temp.size() == 4) { 
					updateAnimation();
					return;
				}
				// Altrimenti controlla se la direzione è già stata controllata
				else if(temp.contains(direction)) { continue; }
				// Altrimenti controlla la direzione e la aggiunge alla lista
				else {
					temp.add(direction);
					collision = false;
					gp.ck.checkTile(this);
					// Se la direzione ha un ostacolo ripete il ciclo
					if(collision == true) { continue; }
				}
				break;
			}
		}
		
		// Se invece non trova un ostacolo esegue il movimento
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
			}
		}
		
		// Aggiorna l'animazione del nemico
		updateAnimation();
	}
	
	// Metodo che disegna il nemico
	public void draw(Graphics2D g2) {
		// Controlla se il nemico è stato sconfitto o no
		if(death == true) { drawAfterDeath(g2); }
		else { drawBeforeDeath(g2); }
	}
	
	// Metodo che disegna il nemico quando viene sconfitto
	public void drawAfterDeath(Graphics2D g2) {
		
		BufferedImage image = switch(spriteNum) {
		case 1 -> im1;
		case 2 -> im2;
		case 3 -> im3;
		case 4 -> im4;
		case 5 -> im5;
		case 6 -> im6;
		default -> throw new IllegalArgumentException("Unexpected value: " + spriteNum);
		};
		
		// Disegno del punteggio quando muore il nemico
		g2.drawImage(imagePoint, worldX, pointimageY, gp.tileSize, gp.tileSize - 5, gp);
		
		// Quando muore il nemico aggiorna il punteggio del player
		if(remove == 20 * 3) { 
			gp.startSound(8);
			// Aumento del punteggio del player
			gp.player.increasePoint(point); 
			// Drop del powerUp
			power = (int) (Math.random() * 100);
			if(power <= 10) { powerUp = new PowerUp(gp, worldX, worldY, 0); gp.listPowerUpDropped.add(powerUp); }
			else if(power > 10 && power <= 20) { powerUp = new PowerUp(gp, worldX, worldY, 1); gp.listPowerUpDropped.add(powerUp); }
			else if(power > 20 && power <= 30) { powerUp = new PowerUp(gp, worldX, worldY, 2); gp.listPowerUpDropped.add(powerUp); }
			// Rimozione del nemico
			removeEnemy = true;
		}
		
		// Animaizone della morte del nemico
		if(counter < 20 && apparead == true) {
			// Disegno del nemio
			g2.drawImage(image, worldX, worldY - 15, gp.tileSize, gp.tileSize + 15, gp);
			remove++;
		}
		else if(counter < 20 && apparead == false){}
		else {
			if(apparead == true) { apparead = false; }
			else { apparead = true; }
			counter = 0;
		}
		
		counter += 1;

	}
	
	// Metodo che disegna il nemico se ancora in vita
	public void drawBeforeDeath(Graphics2D g2) {
		
		// Scelta dell'imamgine da disegnare
		BufferedImage image = switch(spriteNum) {
		case 1 -> im1;
		case 2 -> im2;
		case 3 -> im3;
		case 4 -> im4;
		case 5 -> im5;
		case 6 -> im6;
		default -> throw new IllegalArgumentException("Unexpected value: " + spriteNum);
		};
		
		// Disegno del nemico
		g2.drawImage(image, worldX, worldY - 15, gp.tileSize, gp.tileSize + 15, gp);
	}
	
	// Metodo che aggiorna l'animazione
	public void updateAnimation () {
		
		// Aggiornamento delle animazioni
		spriteCounter ++;
		
		if(spriteCounter > 10) {
			
			if(spriteNum == 1) spriteNum = 2;
			else if(spriteNum == 2) spriteNum = 3;
			else if(spriteNum == 3) spriteNum = 4;
			else if(spriteNum == 4) spriteNum = 5;
			else if(spriteNum == 5) spriteNum = 6;
			else if(spriteNum == 6) spriteNum = 1;
			
			spriteCounter = 0;
		}
	}
	
	// Metodo che controlla se il nemico preso in input si trova nella stessa posizione di un altro nemico
	public boolean equalsCoordinate(Enemy enemy) {
		for(Enemy enemys : gp.allEnemyList) {
			if(enemys.row == row && enemys.col == col) { return true; }
		}
		return false;
	}
	
}
