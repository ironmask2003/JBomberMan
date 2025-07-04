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

public class EnemyPuropen extends Enemy{
	
	// --- Dichiarazione dei campi --- //
	
	// Variabili di controllo per l'animazione del nemico quando muore
	int counter = 0;
	boolean apparead = true;
	int remove = 0;
	
	// PowerUp che nasconde il nemico appena ucciso
	PowerUp powerUp;
	int power;
	
	// Lista delle direzioni
	String[] directions = new String[] {"up", "left", "down", "rigth"};
	
	// Variabile che segna il valore dei punti da dare al player nel caso in cui viene ucciso
	public int point = 100;
	
	// --- Costruttore della classe --- //
	public EnemyPuropen(GamePanel gp) {
		
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
	public EnemyPuropen(GamePanel gp, int worldX, int worldY) {
		
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

	// Metodo che imposta le coordindate iniaziali
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
			
			// Immagini di quando l'entità si muove verso sopra
			up1 = ImageIO.read(getClass().getResourceAsStream("/puropen/Enemy1_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/puropen/Enemy1_up_2.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/puropen/Enemy1_up_3.png"));
			up4 = ImageIO.read(getClass().getResourceAsStream("/puropen/Enemy1_up_4.png"));
			
			// Immagini di quando l'entità si muove verso il basso
			down1 = ImageIO.read(getClass().getResourceAsStream("/puropen/Enemy1_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/puropen/Enemy1_down_2.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/puropen/Enemy1_down_3.png"));
			down4 = ImageIO.read(getClass().getResourceAsStream("/puropen/Enemy1_down_4.png"));			
			
			// Immagini di quando l'entità si muove verso il basso
			rigth1 = ImageIO.read(getClass().getResourceAsStream("/puropen/Enemy1_rigth_1.png"));
			rigth2 = ImageIO.read(getClass().getResourceAsStream("/puropen/Enemy1_rigth_2.png"));
			rigth3 = ImageIO.read(getClass().getResourceAsStream("/puropen/Enemy1_rigth_3.png"));
			rigth4 = ImageIO.read(getClass().getResourceAsStream("/puropen/Enemy1_rigth_4.png"));
			
			// Immagini di quando l'entità si muove verso il basso
			left1 = ImageIO.read(getClass().getResourceAsStream("/puropen/Enemy1_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/puropen/Enemy1_left_2.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/puropen/Enemy1_left_3.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/puropen/Enemy1_left_4.png"));			
			
			// Immagine dei punti
			imagePoint = ImageIO.read(getClass().getResourceAsStream("/puropen/Enemy1_Point.png"));
			
		}catch(IOException e) { e.printStackTrace(); }
		
	}
	
	// Metodo che aggiorna la posizione dell'entità e l'animazione
	public void update() {
		
		if(hitted >= 1) {
			death = true;
		}
		
		// Calcolo della riga e colonna in cui si trova
		row  = worldY/gp.tileSize;
		col  = worldX/gp.tileSize;
		
		// Controlla se il nemico è morto o no
		if(death == true) {
			updateAfterDeath(); 
			pointimageY = worldY - incImageY;
			// Animazione per l'immagine dei punti
			spriteCounterPointimage ++;
			// Dealy per incrementare le coordinate dell'immagine
			if(spriteCounterPointimage > 80) incImageY += 1;
		}
		else updateBeforeDeath();
	}
	
	// Metodo che aggiorna l'animazione dell'entità quando muore
	public void updateAfterDeath() {
		
		// Aggiornamento delle animazioni
		spriteCounter ++;
		
		if(spriteCounter > 1) {
			
			if(spriteNum == 1) spriteNum = 2;
			else if(spriteNum == 2) spriteNum = 3;
			else if(spriteNum == 3) spriteNum = 4;
			else if(spriteNum == 4) spriteNum = 1;
			
			spriteCounter = 0;
		}
		
	}
	
	// Metodo aggiorna la posizione dell'entità se ancora non è morta
	public void updateBeforeDeath() {
		
		// Controllo collisioni
		collision = false;
		gp.ck.checkTile(this);
		
		// Dopo il controllo delle colliosni se ha trovato un ostacolo sceglie randomicamente un'altra direzione
		ArrayList<String> temp = new ArrayList<String>();
		if(collision == true ) { 
			while(true) {
				// Sceglie un altra direzione
				direction = directions[(int) (Math.random() * 4)];
				// Controlla se la lista delle direzioni controllate contiene già tuttte le direzion
				if (temp.size() == 4) { break; }
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
		
		// Aggiornamento delle animazioni
		spriteCounter ++;
		
		if(spriteCounter > 1) {
			
			if(spriteNum == 1) spriteNum = 2;
			else if(spriteNum == 2) spriteNum = 3;
			else if(spriteNum == 3) spriteNum = 4;
			else if(spriteNum == 4) spriteNum = 1;
			
			spriteCounter = 0;
		}
	}
	
	// Metodo che disegna l'entità nel campo di gioco
	public void draw(Graphics2D g2) {
		if(death == true) {drawAfterDeath(g2);}
		else drawBeforeDeath(g2);
	}
	
	// Metodo che disegna l'entità nel campo di gioco quando muore
	public void drawAfterDeath(Graphics2D g2) {
		
		BufferedImage image = switch(direction) {
		// Caso in cui si sta muovendo verso sopra
		case "up" -> switch(spriteNum) {
		case 1 -> up1;
		case 2 -> up2;
		case 3 -> up3;
		case 4 -> up4;
		default -> null;
		};
		// Caso in cui si sta muovendo verso il basso
		case "down" -> switch(spriteNum) {
		case 1 -> down1;
		case 2 -> down2;
		case 3 -> down3;
		case 4 -> down4;
		default -> null;
		};
		// Caso in cui si sta muovendo verso destra
		case "rigth" -> switch(spriteNum) {
		case 1 -> rigth1;
		case 2 -> rigth2;
		case 3 -> rigth3;
		case 4 -> rigth4;
		default -> null;
		};
		// Caso in cui si sta muovendo verso sinistra
		case "left" -> switch(spriteNum) {
		case 1 -> left1;
		case 2 -> left2;
		case 3 -> left3;
		case 4 -> left4;
		default -> null;
		};
		// Default
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
	
	// Metodo che disegna l'entità nel campo di gioco se ancora non è morta
	public void drawBeforeDeath(Graphics2D g2) {
		
		BufferedImage image = switch(direction) {
		// Caso in cui si sta muovendo verso sopra
		case "up" -> switch(spriteNum) {
		case 1 -> up1;
		case 2 -> up2;
		case 3 -> up3;
		case 4 -> up4;
		default -> throw new IllegalArgumentException("Unexpected value: " + spriteNum);
		};
		// Caso in cui si sta muovendo verso il basso
		case "down" -> switch(spriteNum) {
		case 1 -> down1;
		case 2 -> down2;
		case 3 -> down3;
		case 4 -> down4;
		default -> throw new IllegalArgumentException("Unexpected value: " + spriteNum);
		};
		// Caso in cui si sta muovendo verso destra
		case "rigth" -> switch(spriteNum) {
		case 1 -> rigth1;
		case 2 -> rigth2;
		case 3 -> rigth3;
		case 4 -> rigth4;
		default -> throw new IllegalArgumentException("Unexpected value: " + spriteNum);
		};
		// Caso in cui si sta muovendo verso sinistra
		case "left" -> switch(spriteNum) {
		case 1 -> left1;
		case 2 -> left2;
		case 3 -> left3;
		case 4 -> left4;
		default -> throw new IllegalArgumentException("Unexpected value: " + spriteNum);
		};
		// Default
		default -> throw new IllegalArgumentException("Unexpected value: " + direction);
		};
		
		// Disegno del nemico
		g2.drawImage(image, worldX, worldY - 15, gp.tileSize, gp.tileSize + 15, gp);
	}
	
	// Metodo che controlla se il nemico preso in input si trova nella stessa posizione di un altro nemico
	public boolean equalsCoordinate(Enemy enemy) {
		for(Enemy enemys : gp.allEnemyList) {
			if(enemys.row == row && enemys.col == col) { return true; }
		}
		return false;
	}
	
}
