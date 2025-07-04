package entita;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import powerUp.PowerUp;
import stages.GamePanel;

public abstract class Obstacle {
	
	// Dichiarazione dei campi
	
	// Coordinate degli ostacoli
	public int worldX;
	public int worldY;
	
	// Pannello in cui appaiono gli ostacoli
	GamePanel gp;
	
	//-> Imagine degli ostacoli che esplodono
	BufferedImage exp, exp1,exp2, exp3, exp4, exp5;
	
	//-> Controllo se l'ostacolo è stato distrutto
	public boolean destroyed;
	
	//-> Controlla se l'ostacolo è rimovibile dal campo di gioco
	public boolean remove = false;
	
	//-> PowerUp che nasconde l'ostacolo appena viene distrutto
	PowerUp powerUp;
	int power;
	
	//-> Animazione degli ostacoli
	int spriteNum = 1;
	int spriteCounter = 0;
	
	// Costruttore della classe
	public Obstacle(GamePanel gp, int worldX, int worldY) {
		this.gp = gp;
		this.worldX = worldX;
		this.worldY = worldY + (2 * gp.tileSize);
	}
	
	//-> Metodo che imposta a true la variabile destroyed
	public void setTrueDestroyed() { destroyed = true; spriteNum = 1; }
	
	public void update() {}
	
	public void draw(Graphics2D g2) {}

}
