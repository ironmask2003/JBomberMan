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

public class EnemyPakupa extends Enemy {
	
	// Dichiarazione dei campi
	
	// Pannello in cui appare il nemico
	GamePanel gp;
	
	// Variabili di controllo per l'animazione del nemico quando muore
	int counter = 0;
	boolean apparead = true;
	int remove = 0;
	
	// Aggiungia delle variabili per gli sprites
	BufferedImage left5, left6;
	BufferedImage down5, down6, down7,  down8, down9, down10;
	BufferedImage right5, right6;
	
	// PowerUp che nasconde il nemico appena ucciso
	PowerUp powerUp;
	int power;
	
	// Lista delle direzioni
	String[] directions = new String[] {"up", "left", "down", "rigth"};
	
	// Variabile che segna il valore dei punti da dare al player nel caso in cui viene ucciso
	int point = 400;
	
	String directionTemp;
	
	// Costruttore della classe
	public EnemyPakupa(GamePanel gp) {
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
	
	// Costruttore con le coordinate in input
	public EnemyPakupa(GamePanel gp, int worldX, int worldY) {
		
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
	
	// Metodo che imposta i valori di default
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
		
		directionTemp = "up";
	}
	
	// Metodo che prende le immagini dalle risorse del progetto
	public void getImageEnemy() {
		
		try {
			
			// Immagini di quando il nemico si sposta verso l'alto
			up1 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_up_2.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_up_3.png"));
			up4 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_up_4.png"));
			
			// Immagini di quando il nemico si sposta verso il basso
			down1 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_down_2.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_down_3.png"));
			down4= ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_down_4.png"));
			down5 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_down_5.png"));
			down6 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_down_6.png"));
			down7 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_down_7.png"));
			down8 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_down_8.png"));
			down9 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_down_9.png"));
			down10 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_down_10.png"));
			
			// Immagini di quando il nemico si sta spostando verso destra
			rigth1 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_right_1.png"));
			rigth2 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_right_2.png"));
			rigth3 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_right_3.png"));
			rigth4 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_right_4.png"));
			right5 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_right_5.png"));
			right6 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_right_6.png"));
			
			// Immagini di quando il nemico si sta spostando verso destra
			left1 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_left_2.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_left_3.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_left_4.png"));
			left5 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_left_5.png"));
			left6 = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_left_6.png"));
			
			// Immagine dei punti 
			imagePoint = ImageIO.read(getClass().getResourceAsStream("/pakupa/Enemy3_Point.png"));
			
		}catch(IOException e) { e.printStackTrace(); } 
		
	}
	
	// Metodo che aggiorna l'immagine del nemico quando si sposta verso l'alto
	public void updateUp() {
		spriteCounter ++;
		if(spriteCounter > 6) {
			if(spriteNum == 1) spriteNum = 2;
			else if(spriteNum == 2) spriteNum = 3;
			else if(spriteNum == 3) spriteNum = 4;
			else if(spriteNum == 4) spriteNum = 1;
			spriteCounter = 0;
		}
	}
	
	// Metodo che aggiorna l'immagine del nemico quando si sposta verso il basso
	public void updateDown() {
		spriteCounter ++;
		if(spriteCounter > 6) {
			if(spriteNum == 1) spriteNum = 2;
			else if(spriteNum == 2) spriteNum = 3;
			else if(spriteNum == 3) spriteNum = 4;
			else if(spriteNum == 4) spriteNum = 5;
			else if(spriteNum == 5) spriteNum = 6;
			else if(spriteNum == 6) spriteNum = 7;
			else if(spriteNum == 7) spriteNum = 8;
			else if(spriteNum == 8) spriteNum = 9;
			else if(spriteNum == 9) spriteNum = 10;
			else if(spriteNum == 10) spriteNum = 1;
			spriteCounter = 0;
		}
	}
	
	// Metodo che aggiorna l'immagine del nemico quando si sposta verso destra o sinistra
	public void updateLeftRight() {
		spriteCounter ++;
		if(spriteCounter > 6) {
			if(spriteNum == 1) spriteNum = 2;
			else if(spriteNum == 2) spriteNum = 3;
			else if(spriteNum == 3) spriteNum = 4;
			else if(spriteNum == 4) spriteNum = 1;
			else if(spriteNum == 5) spriteNum = 1;
			else if(spriteNum == 6) spriteNum = 1;
			spriteCounter = 0;
		}
	}
	
	// Metodo che controlla in quale direzione sta andando e aggiorna gli sprites per quella direzione
	public void updateAnimation() {
		
		// Controlla se è cambiata la direzione, se sì reimposta i valori per il controllo degli sprite
		if(directionTemp != direction) {
			spriteNum = 1;
			spriteCounter = 0;
			directionTemp = direction;
		}
		
		switch(direction) {
		case "up" : updateUp(); break;
		case "down" : updateDown(); break;
		case "left" : updateLeftRight(); break;
		case "rigth" : updateLeftRight(); break;
		}
	}
	
	// Metodo che aggiorna l'immagine e lo spostamento del nemico prima che viene sconfitto dal player
	public void updateBeforeDeath() {
		
		collision = false;
		gp.ck.checkTile(this);
		
		// Cambio di direzione in caso di collisione
		ArrayList<String> temp = new ArrayList<String>();
		if(collision) {
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
		
		updateAnimation();
		
	}
	
	// Metodo che aggiorna gli sprites del nemico quando viene sconfitto in base alla direzione in cui si trova
	public void updateAfterDeath() {
		updateAnimation();
	}
	
	// Metodo che aggiorna il nemico
	public void update() {
		
		if(hitted > 30) { death = true;}
		
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
	
	// Metodo che restituisce l'immagine da disegnare se il nemico si sta spostando verso sopra
	public BufferedImage imageUp() {
		
		BufferedImage image = switch(spriteNum) {
		case 1 -> up1;
		case 2 -> up2;
		case 3 -> up3;
		case 4 -> up4;
		default -> null;
		};
		
		return image;
	}
	
	// Metodo che restituisce l'immagine da disegnare se il nemico si sta spostando verso destra
	public BufferedImage imageRight() {
		
		BufferedImage image = switch(spriteNum) {
		case 1 -> rigth1;
		case 2 -> rigth2;
		case 3 -> rigth3;
		case 4 -> rigth4;
		case 5 -> right5;
		case 6 -> right6;
		default -> null;
		};
		
		return image;
		
	}
	
	// Metodo che restituisce l'immagine da disegnare se il nemico si sta spostando verso sinistra
	public BufferedImage imageLeft() {
		
		BufferedImage image = switch(spriteNum) {
		case 1 -> left1;
		case 2 -> left2;
		case 3 -> left3;
		case 4 -> left4;
		case 5 -> left5;
		case 6 -> left6;
		default -> null;
		};
		
		return image;
		
	}
	
	// Metodo che restituisce l'immagine da disegnare se il nemico si sta sposyando verso il basso
	public BufferedImage imageDown() {
		
		BufferedImage image = switch(spriteNum) {
		case 1 -> down1;
		case 2 -> down2;
		case 3 -> down3;
		case 4 -> down4;
		case 5 -> down5;
		case 6 -> down6;
		case 7 -> down7;
		case 8 -> down8;
		case 9 -> down9;
		case 10 -> down10;
		default -> null;
		};
		
		return image;
		
	}
	
	// Metodo disegna il nemico prima che viene sconfitto dal player
	public void drawBeforeDeath(Graphics2D g2) {
		
		BufferedImage image = switch(direction) {
		case "up" -> imageUp();
		case "rigth" -> imageRight();
		case "left" -> imageLeft();
		case "down" -> imageDown();
		default -> null;
		};
		
		g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, gp);
	}
	
	// Metodo che disegna il nemico quando viene sconfitto
	public void drawAfterDeath(Graphics2D g2) {
		
		BufferedImage image = switch(direction) {
		case "up" -> imageUp();
		case "rigth" -> imageRight();
		case "left" -> imageLeft();
		case "down" -> imageDown();
		default -> null;
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
			g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, gp);
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
	
	// Metodo che disegna il nemico
	public void draw(Graphics2D g2) {
		// Controlla se il nemico è stato sconfitto o no
		if(death == true) { drawAfterDeath(g2); }
		else { drawBeforeDeath(g2); }
	}
	
	// Metodo che controlla se il nemico preso in input si trova nella stessa posizione di un altro nemico
	public boolean equalsCoordinate(Enemy enemy) {
		for(Enemy enemys : gp.allEnemyList) {
			if(enemys.row == row && enemys.col == col) { return true; }
		}
		return false;
	}
}
