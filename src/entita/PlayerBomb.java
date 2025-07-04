package entita;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import stages.GamePanel;

public class PlayerBomb extends Entita{
	
	// Dichiarazione dei campi
	
	//-> Pannello del campo di gioco
	GamePanel gp;
	
	//-> Variabile di controllo per controllare quando iniziare l'esplosione
	public int startExp;
	
	double timer = (1.3 * 1 * Math.pow(10, 9));
	double delay = 0;
	
	//-> Variabile che indica che la bomba è stata piazzata
	public boolean placed = false;
	
	//-> Esplosione della bomba
	public BombExplosion ex;
	
	// Costruttore della classe
	public PlayerBomb(int x, int y, GamePanel gp) {
		this.gp = gp;
		
		// Coordinate della bomba
		worldX = x;
		worldY = y;
		
		delay = System.nanoTime() + timer;
		
		// Riga e colonna in cui si trova la bomba
		row = worldY/gp.tileSize;
		col = worldX/gp.tileSize;
		
		// Hitbox della bomba
		solidAreaD = new Rectangle();
		solidAreaD.x = 0;
		solidAreaD.y = 0;
		solidAreaD.height = 48;
		solidAreaD.width = 48;
		
		// Hitbox della bomba
		solidArea = new Rectangle();
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.height = 48;
		solidArea.width = 48;
		
		ex = new BombExplosion(worldX, worldY, gp);
		
		startExp = 0;
		
		//-> Metodo che prende le immagini della bomba
		getImageBomb();
		
		/** Suono piazzamento della bomba */
		gp.startSound(1);
		
	}
	
	//-> Metodo che prende le immagini della bomba prima dell'esplosione
	public void getImageBomb() {
		try {
			
			// Immagini della bomba
			up1 = ImageIO.read(getClass().getResourceAsStream("/tilesBomb/Tiles_Bomb1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/tilesBomb/Tiles_Bomb2.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/tilesBomb/Tiles_Bomb3.png"));
			
		}
		catch(IOException e) { e.printStackTrace(); }
	}
	
	//-> Metodo che aggiorna le immagini della bomba
	public void update() {
		
		if(System.nanoTime() > delay) {
			placed = true; 			
		}
		
		//-> Se la variabile startExp è minore di 6 continua con l'animazione della bomba
		if(startExp < 6) {
			
			//-> Dopo ogni movimento aumenta la variabile in modo da cambiare l'immagine della bomba e creare un'animazione
			spriteCounter ++;
			
			// Controlla se la variabile è maggio di un numero in modo da cambiare più lentamente l'immagine della bomba
			if(spriteCounter > 12) {
				
				// Se è alla prima immagine del movimento che sta compiendo, passa alla seconda immagine
				if(spriteNum == 1)  spriteNum = 2; 
				// Se è alla seconda immagine passa alla terza
				else if(spriteNum == 2) spriteNum = 3; 
				// Se è alla terza immagine ritorna alla prima
				else if(spriteNum == 3) spriteNum = 1; startExp += 1;  ex = new BombExplosion(worldX, worldY, gp);
				
				// Azzera il counter
				spriteCounter = 0;
			}
		}
		//-> Altrimenti passa all'esplisione
		else {
			placed = false;
			
			//-> Inizia ad aggiornare le immagini dell'esplosione
			ex.update();
		}
	}
	
	//-> Metodo che rappresenta nella finestra le immagini
	public void draw(Graphics2D g2) {
		
		//-> Se la variabile startExp è minore di 6 continua con l'animazione della bomba
		if(startExp < 6) {
			
			//-> Controlla quale animazione deve rappresentare nella finestra con uno switch
			BufferedImage image = switch(spriteNum) {
			case 1 -> up1;
			case 2 -> up2;
			case 3 -> up3;
			default -> null;
			};
			
			
			//-> Se le coordinate della bomba sono esattamente al centro della casella rappresenta la bomba
			if(worldX % 48 == 0 && worldY % 48 == 0) { 
				g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null); 
			}
			//-> Altrimenti controlla in quale casella si trova e la posiziona al centro di quella casella
			else {
				
				//-> Metodo che calcola le coordinate in cui piazzare la bomba
				position(this);
				
				//-> Metodo che piazza la bomba al centro della casella in cui si trova il player
				placeBomb(g2, image);
				
			}
		}
		// Altrimenti rappresenta l'esplosione
		else {  ex.draw(g2); }
	}
	
	// Metodo che preso un numero in input lo sostituisce a worldX
	public void setWorldX(int worldX) { this.worldX = worldX; }
	
	// Metodo che preso un numero in input lo sostituisce a worldY
	public void setWorldY(int worldY) { this.worldY = worldY; }
	
	//-> Metodo che piazza la bomba al centro della casella in cui si trova il player
	public void placeBomb(Graphics2D g2, BufferedImage image) {
		
		// Ciclo for che itera su una matrice in cui sono salvate le varie coordinate delle caselle in modo da piazzare le bombe al centro della casella
		for(int r = 0; r < gp.tile.coordinateCaselle.length; r ++) {
			for(int c = 0; c < gp.tile.coordinateCaselle[r].length; c ++) {
				try {
					
					// Split per divedere le coordinate di x e y
					String[] pos = gp.tile.coordinateCaselle[r][c].split(" ");
					
					//-> Calcola i bordi di ogni casella
					int rigth = Integer.parseInt(pos[0]) + (48/2);
					int left = Integer.parseInt(pos[0]) - (48/2);
					int up = Integer.parseInt(pos[1]) - (48/2);
					int down = Integer.parseInt(pos[1]) + (48/2);
					
					/* Controlla se le coordinate della bomba rientrano nella casella 
					 * e se lo sono sostituisce le con le coordinate centrali della casella */
					if(worldX <= rigth && worldX >= left && worldY <= down && worldY >= up) {
						
						// Imposta le nuove coordinate
						worldX = Integer.parseInt(pos[0]);
						worldY = Integer.parseInt(pos[1]);
						r = worldX/gp.tileSize;
						c = worldY/gp.tileSize;
						
						// Disegna la bomba
						g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
						
					}
				}catch(NullPointerException e) { continue; }
			}
		}
	}
	
	//-> Metodo che in base alla direzione del player ricalcola la posizone per piazzare la bomba dietro al player
	public void position(PlayerBomb bomb) {
		
		//-> Coordinate del bordo dell'entita
		int entityLeftWorldX = bomb.worldX + bomb.gp.player.solidArea.x;
		int entityRigthWorldX = bomb.worldX + bomb.gp.player.solidArea.x + bomb.gp.player.solidArea.width;
		int entityTopWorldY = bomb.worldY + bomb.gp.player.solidArea.y;
		int entityBottomWorldY = bomb.worldY + bomb.gp.player.solidArea.y + bomb.gp.player.solidArea.height;
		
		//-> Cacolco della riga e colonna
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRigthCol = entityRigthWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		//-> Switch che controlla in quale direzione si sta spostando il player e calcola le nuove coordinate
		switch(bomb.gp.player.direction) {
		case "up" : 
			
			entityBottomRow = (entityBottomWorldY - bomb.gp.player.speed)/gp.tileSize;
			bomb.setWorldX(entityBottomRow * gp.tileSize);
			break;
		
		case "down" :
			
			entityTopRow = (entityTopWorldY - bomb.gp.player.speed)/gp.tileSize;
			bomb.setWorldX(entityTopRow * gp.tileSize);
			break;
			
		case "left" :
			
			entityRigthCol = (entityRigthWorldX - bomb.gp.player.speed)/gp.tileSize;
			bomb.setWorldY(entityRigthCol * gp.tileSize);
			break;
			
		case "rigth" :
			
			entityLeftCol = (entityLeftWorldX - bomb.gp.player.speed)/gp.tileSize;
			bomb.setWorldY(entityLeftCol * gp.tileSize);
			break;
		}
	}
}
