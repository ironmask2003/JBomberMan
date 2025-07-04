package entita;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import powerUp.PowerUp;
import stages.GamePanel;

public class ObstacleStage1_1 extends Obstacle {
	
	// Dichiarazione dei campi
	
	// Immagini dell'ostacolo
	BufferedImage up1, left1, rigth1, down1;
	
	//-> Costruttore della classe
	public ObstacleStage1_1(int x, int y, GamePanel gp) {
		super(gp, x, y);
		
		//-> Metodo che prende le immagini dell'ostacolo
		getObstacleImage();
		
	}
	
	//-> Metodo che prende le immagini dell'ostacolo
	public void getObstacleImage() {
		try{
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/tilesObstacle/Tiles1_ob1.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/tilesObstacle/Tiles1_ob2.png"));
			rigth1 = ImageIO.read(getClass().getResourceAsStream("/tilesObstacle/Tiles1_ob3.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/tilesObstacle/Tiles1_ob4.png"));
			
			exp = ImageIO.read(getClass().getResourceAsStream("/tilesObstacle/Tiles1_ob1_exp1.png"));
			exp1 = ImageIO.read(getClass().getResourceAsStream("/tilesObstacle/Tiles1_ob1_exp2.png"));
			exp2 = ImageIO.read(getClass().getResourceAsStream("/tilesObstacle/Tiles1_ob1_exp3.png"));
			exp3 = ImageIO.read(getClass().getResourceAsStream("/tilesObstacle/Tiles1_ob1_exp4.png"));
			exp4 = ImageIO.read(getClass().getResourceAsStream("/tilesObstacle/Tiles1_ob1_exp5.png"));
			exp5 = ImageIO.read(getClass().getResourceAsStream("/tilesObstacle/Tiles1_ob1_exp6.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//-> Metodo che aggiorna
	public void update() {
		
		//-> Controlla se l'ostacolo è stato distrutto, se non è stato distrutto aggiorna le immagini dell'ostacolo
		if(destroyed == false) {
			
			// Dopo ogni movimento aumenta la variabile in modo da cambiare l'immagine del personaggio e creare un'animazione
			spriteCounter ++;
			
			// Controlla se la variabile è maggio di un numero in modo da cambiare più lentamente l'immagine del personaggio
			if(spriteCounter > 8) {
				
				// Se è alla prima immagine del movimento che sta compiendo, passa alla seconda immagine
				if(spriteNum == 1) spriteNum = 2;
				// Se è alla seconda immagine passa alla terza
				else if(spriteNum == 2) spriteNum = 3;
				// Se è alla terza immagine passa alla quarta
				else if(spriteNum == 3) spriteNum = 4;
				// Se è alla quarta immagine ritorna alla prima
				else if(spriteNum == 4) spriteNum = 1;
				
				// Azzera il counter
				spriteCounter = 0;
			}
			
		}
		//-> Altrimenti se è stato distrutto passa alle immagini in cui viene distrutto
		else {
			
			// Dopo ogni movimento aumenta la variabile in modo da cambiare l'immagine del personaggio e creare un'animazione
			spriteCounter ++;
			
			// Controlla se la variabile è maggio di un numero in modo da cambiare più lentamente l'immagine del personaggio
			if(spriteCounter > 8) {
				
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
				// Se è alla sesta immagine ferma l'animazione
				else if(spriteNum == 6) spriteNum = 0;
				
				// Azzera il counter
				spriteCounter = 0;
			}
			
		}
	}
	
	//-> Metodo che rappresenta nella finestra le immagini
	public void draw(Graphics2D g2) {
		
		BufferedImage image;
		
		//-> Se l'ostacolo non è stato distrutto continua con le immagini prima della distruzione
		if(destroyed == false) {
			
			//-> Switch che sceglie l'immagine da rappresentare
			image = switch(spriteNum) {
			case 1 -> up1;
			case 2 -> left1;
			case 3 -> rigth1;
			case 4 -> down1;
			default -> up1;
			};
			
			g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
			
		}
		//-> Altrimenti rappresenta le immagini dell'ostacolo che viene distrutto
		else {
			
			//-> Swtich che sceglie l'immagine da rappresentare
			switch(spriteNum) {
			case 1 : image = exp; break;
			case 2 : image = exp1; break;
			case 3 : image = exp2; break;
			case 4 : image = exp3; break;
			case 5 : image = exp4; break;
			case 6 : image = exp5; break;
			//-> Dopo l'ultima immagine piazza al posto dell'ostacolo il pavimento
			default :
				// Controlla in che riga si trova l'ostacolo e lo sostiuisce con un pavimento
				if((worldY/gp.tileSize) - 2 == 1) { image = gp.tile.tile[1].image; }
				else { image = gp.tile.tile[2].image; }
				// Imposta la variabili a true per poterlo rimuovere dalla lista degli oggetti nel gamepanel
				remove = true;
				// Drop del powerUp
				power = (int) (Math.random() * 100);
				if(power <= 10) { powerUp = new PowerUp(gp, worldX, worldY, 0); gp.listPowerUpDropped.add(powerUp); }
				else if(power > 10 && power <= 20) { powerUp = new PowerUp(gp, worldX, worldY, 1); gp.listPowerUpDropped.add(powerUp); }
				else if(power > 20 && power <= 30) { powerUp = new PowerUp(gp, worldX, worldY, 2); gp.listPowerUpDropped.add(powerUp); }
				break;
			};
			
			g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
		}
		
	}
}
