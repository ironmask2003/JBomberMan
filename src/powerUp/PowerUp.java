package powerUp;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import stages.GamePanel;

public class PowerUp {
	
	// Dichiarazione dei campi
	
	// Pannello in cui appare il powerUP
	GamePanel gp;
	
	// Lista dei powerUp
	PowerUpPaths[] listPowerUp = PowerUpPaths.values();
	
	// Coordinate del powerUp
	public int worldX;
	public int worldY;
	
	// Immagine del powerUp
	BufferedImage im1, im2;
	
	// Variabile per decidere quale powerUp è stato droppato
	public int powerUp;
	
	int spriteNum = 1;
	int spriteCounter = 0;
	
	// Hitbox del powerUp per essere preso dal player
	Rectangle solidArea;
	
	// Variabile booleana per controlare se il powerUp è stato preso dal player
	public boolean taked = false;
	
	// Costruttore della classe
	public PowerUp(GamePanel gp, int worldX, int worldY, int powerUp) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.powerUp = powerUp;
		this.gp = gp;
		
		// Area dell'hitbox del power up
		solidArea = new Rectangle();
		solidArea.x = worldX;
		solidArea.y = worldY;
		solidArea.height = 48;
		solidArea.width = 48;
		
		getImagePowerUp();
	}
	
	// Metodo che prende l'immagine dalle risorse del progetto
	public void getImagePowerUp() {
		
		try { 
			im1 = ImageIO.read(getClass().getResourceAsStream(listPowerUp[powerUp].getPath() + ".png")); 
			im2 = ImageIO.read(getClass().getResourceAsStream(listPowerUp[powerUp].getPath() + "_2.png")); 
		}
		catch(IOException e) { e.printStackTrace(); }
		
	}
	
	// Metodo che controlla se il player ha preso il powerUp
	public void checkIsTaked() {
		// Aggiorna l'hitbox del player
		gp.ck.updateHitBox(gp.player);
		// Controlla se il player ha preso il powerUp
		if(gp.player.solidArea.intersects(solidArea)) { taked = true; gp.startSound(5); gp.player.getPowerUp(this); }
		// Reimposta l'hitBox del player
		gp.ck.resetHitBox(gp.player);
	}
	
	// Metodo che aggiorna l'immagine da disegnare
	public void update() {
		
		checkIsTaked();
		
		spriteCounter ++;
		
		if(spriteCounter > 5) {
			
			if(spriteNum == 1) spriteNum = 2;
			else if(spriteNum == 2) spriteNum = 1;
			
			spriteCounter = 0;
			
		}
		
	}
	
	// Metodo che disegna il powerUp nel campo di gioco
	public void draw(Graphics g) {
		
		BufferedImage image = switch(spriteNum) {
		case 1 -> im1;
		case 2 -> im2;
		default -> throw new IllegalArgumentException("Unexpected value: " + spriteNum);
		};
		
		// Disgeno del power up
		g.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, gp);
		
	}
	
}
