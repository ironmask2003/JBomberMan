package entita;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import observer.Observer;
import stages.GamePanel;
import tile.TileManagerStage1_1;

public class Portal extends Entita implements Observer{
	
	// Dichiarazione dei campi
	
	// Variabile di controllo per passare al prossimo stage
	public boolean continueStage = false;
	
	// Variabile di controllo per far spawnare un nuovo nemico
	public boolean spawnEnemy = false;
	
	// Pannello in cui appare il portale
	GamePanel gp;
	
	// Costruttore della classe
	public Portal(GamePanel gp, int worldX, int worldY) {
		this.gp = gp;
		
		// Coordinate in cui si trova il portale
		this.worldX = worldX;
		this.worldY = worldY;
		
		solidArea = new Rectangle();
		solidArea.x = worldX;
		solidArea.y = worldY;
		solidArea.height = 48;
		solidArea.width = 48;
		
		// Riga e colonna in cui si trova il portale
		row = this.worldY/gp.tileSize;
		col = this.worldX/gp.tileSize;
		
		getImagePortal();
	}
	
	// Metodo che prende le immagini dalle risporse
	public void getImagePortal() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/tiles/Tiles_Portal_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/tiles/Tiles_Portal_2.png"));
			
		}catch (IOException e) { e.printStackTrace(); }
	}
	
	public void update() {
				
		chekEnemy();
		
		// Controlla se deve essere spwnato un nemico
		if(spawnEnemy == true) { 
			
			// Fa spwanare un nemico e riporta la variabile booleana a false
			spawnEnemy();
			spawnEnemy = false;
		}
		
		// Aggiornamento delle animazioni
		spriteCounter ++;
		
		if(spriteCounter > 1) {
			
			if(spriteNum == 1) spriteNum = 2;
			else if(spriteNum == 2) spriteNum = 1;
			
			spriteCounter = 0;
		}
	}
	
	public void draw(Graphics2D g2) {;
		
		BufferedImage image = switch(spriteNum) {
		case 1 -> up1;
		case 2 -> up2;
		default -> throw new IllegalArgumentException("Unexpected value: " + spriteNum);
		};
		
		// Disegno del portale
		g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
		
	}
	
	// Metodo che fa spawnare nuovi nemici se il portale è nel range dell'esplosione della bomba
	public void spawnEnemy() {
		
		// Lista delle posizione da controllare per piazzare il nemico
		int[][] checkPos = {{0, 1}, {1, 0}, {1, 1}, {-1, 0}, {0, -1}, {-1, -1}, {1, -1}, {-1, 1}};
		
		// Controlla se le caselle vicino al portale sono libere
		for(int[] pos : checkPos) {
			
			// Calcola la riga e la colonna da controllare
			int r = row + pos[0];
			int c = col + pos[1];
			
			// 	Variabili per controlla se la casella è libera
			int check1 = 0; int check2 = 0;								//-> Caso in cui siamo al secondo livello
			
			if(gp.tile instanceof TileManagerStage1_1) { check1 = 1; check2 = 2; }							//-> Caso in cui siamo nel primo livello
			
			// Controlla se la casella è libera, se lo è aggiungie un nemico alla lista di nemici
			if(gp.tile.mapTileNum[c][r - 2] == check1 || gp.tile.mapTileNum[c][r - 2] == check2) {
				int enemy = (int) (Math.random() * 3) + 1;
				switch(enemy) {
				case 1 : gp.allEnemyList.add(new EnemyPuropen(gp, c * gp.tileSize, r * gp.tileSize)); break;
				case 2 : gp.allEnemyList.add(new EnemyDenkyun(gp, c * gp.tileSize, r * gp.tileSize)); break;
				case 3 : gp.allEnemyList.add(new EnemyPakupa(gp, c * gp.tileSize, r * gp.tileSize)); break;
				case 4 : gp.allEnemyList.add(new EnemyCuppen(gp, c * gp.tileSize, r * gp.tileSize)); break;
				}
				break;
			}
		}
		
		
	}
	
	// Metodo che controlla se tutti i nemici sono stati sconfitti e che il player sia nel portale
	public void chekEnemy() {
		// Aggiornamento dell'hitbox del player
		gp.ck.updateHitBox(gp.player);
		if (gp.numEnemy == 0 && gp.player.solidArea.intersects(solidArea) && gp.player.portal == false) { 
			gp.player.setPortal();
			gp.back.stop();
			gp.startSound(9);
		}
		// Reimposta l'hitbox del player
		gp.ck.resetHitBox(gp.player);
	}
}
