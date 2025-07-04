package entita;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import stages.GamePanel;
import tile.TileManagerStage1_1;

public class BombExplosion extends Entita {
	
							//	------	Diciharazione dei campi	------	\\
	
	//-> Pannello del campo di gioco
	GamePanel gp;
	
	//-> Lista degli ostacoli rimovibili con la bomba
	ArrayList<Obstacle> obs;
	
	//-> Variabile di controllo per indicare quando finisce l'esplosione
	public boolean endExplosion = false;
	
	//-> Immagine della parte centrale sell'esplosione della prima, seconda e terza immagine
	BufferedImage centerExp1, centerExp2, centerExp3;
	
	//-> Contatore per far sparire l'esplosione
	public int counterExp;
	
	//-> Variabile di controllo per indicare che l'esplosione ha colpito il portale e deve spawnare un nemico
	public boolean spawnEnemy = false;
	
	double delayExplosion =  (5 * 1 * Math.pow(10, 9));
	double delaySystem = 0;
	
	//-> Lista dei nemici
	ArrayList<Enemy> listEnemy;
	
							//	------	Costruttore della classe ------	\\
	
	public BombExplosion(int x, int y, GamePanel gp) {
		
		//-> Pannello del campo di gioco
		this.gp = gp;
		obs = gp.obs;
		listEnemy = gp.allEnemyList;
		
		counterExp = 0;
		
		//-> HitBox dell'esplosione
		solidArea = new Rectangle();
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.height = 48;
		solidArea.width = 48;
		
		//-> Assegnazione delle coordinate dell'esplosione nel campo di gioco
		worldX = x;
		worldY = y;
		
		//-> Metodo che prende le immagini dalle risorse del progetto
		getImageExplosion();
	}
	
	
							//	------	Metodi della classe ------	\\
	
	
	//-> Metodo che prende le immagini dell'esplosione dalle risore dell'oggetto
	public void getImageExplosion() {
		try {
			
			//-> Immagini della prima esplosione
			up1 = ImageIO.read(getClass().getResourceAsStream("/tilesBomb/Tiles_Exp1_up.png"));							//-> Immagine Superiore
			left1 = ImageIO.read(getClass().getResourceAsStream("/tilesBomb/Tiles_Exp1_left.png"));						//-> Immagine di Sinistra
			centerExp1 = ImageIO.read(getClass().getResourceAsStream("/tilesBomb/Tiles_Exp1_center.png"));		//-> Immagine Centrale
			rigth1 = ImageIO.read(getClass().getResourceAsStream("/tilesBomb/Tiles_Exp1_rigth.png"));					//-> Immagine di Destra
			down1 = ImageIO.read(getClass().getResourceAsStream("/tilesBomb/Tiles_Exp1_down.png"));				//-> Immagine Inferiore
			
			//-> Immagini della seconda esplosione
			up2 = ImageIO.read(getClass().getResourceAsStream("/tilesBomb/Tiles_Exp2_up.png"));							//-> Immagine Superiore
			left2 = ImageIO.read(getClass().getResourceAsStream("/tilesBomb/Tiles_Exp2_left.png"));						//-> Immagine di Sinistra
			centerExp2 = ImageIO.read(getClass().getResourceAsStream("/tilesBomb/Tiles_Exp2_center.png"));		//-> Immagine Centrale
			rigth2 = ImageIO.read(getClass().getResourceAsStream("/tilesBomb/Tiles_Exp2_rigth.png"));					//-> Immagine di Destra
			down2 = ImageIO.read(getClass().getResourceAsStream("/tilesBomb/Tiles_Exp2_down.png"));				//-> Immagine Inferiore
			
			//-> Immagini della terza esplosione
			up3 = ImageIO.read(getClass().getResourceAsStream("/tilesBomb/Tiles_Exp3_up.png"));							//-> Immagine Superiore
			left3 = ImageIO.read(getClass().getResourceAsStream("/tilesBomb/Tiles_Exp3_left.png"));						//-> Immagine di Sinistra
			centerExp3 = ImageIO.read(getClass().getResourceAsStream("/tilesBomb/Tiles_Exp3_center.png"));		//-> Immagine Centrale
			rigth3 = ImageIO.read(getClass().getResourceAsStream("/tilesBomb/Tiles_Exp3_rigth.png"));					//-> Immagine di Destra
			down3 = ImageIO.read(getClass().getResourceAsStream("/tilesBomb/Tiles_Exp3_down.png"));				//-> Immagine Inferiore
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//-> Metodo che aggiorna le immagini da prendere
	public void update() {
		
		 /** Suono dell'esplosione della bomba*/ 
		if(System.nanoTime() > delaySystem) {
			gp.startSound(3);			
			delaySystem = System.nanoTime() + delayExplosion;
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
			else if(spriteNum == 3) spriteNum = 1; counterExp += 1;
			
			// Azzera il counter
			spriteCounter = 0;
		}
		
	}
	
	//-> Metodo che rappresenta l'esplosione nel campo di gioco
	public void draw(Graphics2D g2) {
		
		//-> Scelta dell'immagine da mostarte sulla finestra della parte superiore dell'esplosione
		BufferedImage up = selectImage(up1, up2, up3);
		
		//-> Scelta dell'immagine da mostarte sulla finestra della parte di sinistra dell'esplosione
		BufferedImage left = selectImage(left1, left2, left3);
		
		//-> Scelta dell'immagine da mostarte sulla finestra della parte centrale dell'esplosione
		BufferedImage centerExp = selectImage(centerExp1, centerExp2, centerExp3);
		
		//-> Scelta dell'immagine da mostarte sulla finestra della parte di destra dell'esplosione
		BufferedImage rigth = selectImage(rigth1, rigth2, rigth3);
		
		//-> Scelta dell'immagine da mostarte sulla finestra della parte inferiore dell'esplosione
		BufferedImage down = selectImage(down1, down2, down2);
		
		// Controlla se il counter è minore di 3, se non lo è fa sparire l'esplosione
		if(counterExp < 3) {
			
			// Disegno della pate sinistra dell'esplosione
			drawExp(left, worldX - gp.tileSize, worldY, g2);
			
			// Disegno della parte superiore dell'esplosione
			drawExp(up, worldX, worldY - gp.tileSize, g2);
			
			// Disegno della parte centrale dell'esplosione
			drawExp(centerExp, worldX, worldY, g2);
			
			// Disegno della parte inferiore dell'esplosione
			drawExp(down, worldX, worldY + gp.tileSize, g2);
			
			// Disegno della parte destra dell'esplosione
			drawExp(rigth, worldX + gp.tileSize, worldY, g2);
			
		}
		else {
			endExplosion = true;
			// Controlla se durante l'esplosione è stato colpito il portale, se si spawna un altro nemico
			if(spawnEnemy == true) gp.portale.spawnEnemy = true;
		}
		
	}
	
	//-> Metodo che prende in ingresso un'immagine e controlla quale immagine deve essere caricata sulla variabile 
	public BufferedImage selectImage(BufferedImage firstCase, BufferedImage secCase, BufferedImage thirdCase) {
		
		BufferedImage image = switch(spriteNum) {
		case 1 -> firstCase;
		case 2 -> secCase;
		case 3 -> thirdCase;
		default -> throw new IllegalArgumentException("Unexpected value: " + spriteNum);
		};
		
		return image;
	}
	
	//-> Metodo che controlla se l'esplosione ha colpito un nemico
	public void checkIsEnemy(Rectangle solidArea, Enemy enemy) {
		//-> Aggiornamneto dell'hitbox del nemico
		gp.ck.updateHitBox(enemy);
		//-> Controlla se il nemico sta andando contro l'esplosione
		if(enemy.solidArea.intersects(solidArea)) enemy.hitted += 1;
		//-> Reimposta l'hitbox del nemico
		gp.ck.resetHitBox(enemy);
	}
	
	//-> Metodo che controlla se l'esplosione ha colpito il portale
	public void chekIsPortal(int col, int row) {
		if(gp.portale.row == row && gp.portale.col == col) { spawnEnemy = true; }
	}
	
	//-> Metodo che controlla se l'esplosione ha colpito il player
	public void checkIsPlayer(Rectangle solidArea) {
		//-> Aggiorna l'hitbox del player
		gp.ck.updateHitBox(gp.player);
		//-> Controlla se il player sta andando contro l'esplosione
		if(gp.player.solidArea.intersects(solidArea)) gp.player.death = true;
		//-> Reimposta l'hitbox del player
		gp.ck.resetHitBox(gp.player);
	}
	
	//-> Metodo che prende data un'immagine in input e le coordinare, rappresenta nel campo di gioco l'immagine nelle coordinate prese in input
	public void drawExp(BufferedImage image, int worldX, int worldY, Graphics2D g2) {
		
		//-> Calcola la riga e la colonna in cui deve essere rappresentata l'immaigne
		int row = worldY/gp.tileSize;
		int col = worldX/gp.tileSize;
		
		// Aggiorna l'hitbox dell'esplosione
		this.solidArea.x = this.solidArea.x + worldX;
		this.solidArea.y = this.solidArea.y + worldY;
		
		int check1 = 0; int check2 = 0;
		
		int obstacle = 20;
		
		if(gp.tile instanceof TileManagerStage1_1) { check1 = 2; check2 = 1; obstacle = 17; }
		
		//-> Controlla se la casella è libera
		if(gp.tile.mapTileNum[col][row - 2] == check1 || gp.tile.mapTileNum[col][row - 2] == check2) {
			
			//-> Se lo è rappresenta l'immagine
			g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, gp);
			
			//-> E controlla se in quella casella si trova il player
			checkIsPlayer(solidArea);
			
			//-> E controlla se in quella casella si trovano dei nemici
			for(Enemy enemy : listEnemy){
				checkIsEnemy(solidArea, enemy);
			}
		}
		//-> Altrimenti controlla se in quella casella troviamo un ostacolo
		if(gp.tile.mapTileNum[col][row - 2] == obstacle) {
			
			//-> Se si trova un ostacolo viene distrutto
			for(Obstacle o: obs) {
				if(o.worldX == worldX && o.worldY == worldY) {
					o.setTrueDestroyed();
					break;}}
			
			//-> E viene rimosso dalla mappa
			gp.tile.removeObstacle(row - 2, col);
		}
		//-> E controlla se in quella casella si trova il portale
		chekIsPortal(col, row);
		
		//-> Reimposta i valori dell'hitbox dell'esplosione
		this.solidArea.x = 0;
		this.solidArea.y = 0;
	}
	
}
