package entita;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import stages.GamePanel;

public abstract class Enemy extends Entita {
	
	// Dichiarazione dei campi
	
	// Pannello in cui appaiono i nemici
	GamePanel gp;
	
	// Valore dei punti da dare al player nel momento in cui sconfigge il nemico
	int point;
	
	// Valore booleano per controllare se il nemico è morto o no
	public boolean death = false;
	
	// Variabile che controlla quante volte il nemico è stato colpito dalla bomba
	public int hitted = 0;
	
	// Riga e colonna in cui si trova il nemico
	public int row;
	public int col;
	
	// Immagine dei punti
	BufferedImage imagePoint;
	int pointimageY;		// Posizione di partenza per l'imamgine dei punti
	int incImageY = 1; 	// Variabile per incrementare l'altezza delle coordinate dell'immagine per creare l'animazione del punteggio
	int spriteCounterPointimage = 0;

	public boolean removeEnemy = false;
	
	public void setDeath(boolean death) { this.death = death; }

	public void update() {}

	public void draw(Graphics2D g2) {}
}
