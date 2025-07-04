package entita;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entita {
	
	// Dichiarazione dei campi
	
	//-> Coordinate
	public int worldX;
	public int worldY;
	
	//-> Riga e Colonna in cui si trova l'entita
	public int row;
	public int col;
	
	//-> Velocità con la quale si muove l'entita
	public int speed;
	
	//-> Sprites
	public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4, rigth1, rigth2, rigth3, rigth4;
	
	//-> Indica la direzione nella quale si sta muovendo l'entita
	public String direction;
	
	//-> Variabili utilizzati per la ripetizione della immagini per creare l'animazione
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	//-> Area di impatto delle entita per le collisioni di default
	public Rectangle solidAreaD;
	
	//-> Area di impatto che segue l'entita
	public Rectangle solidArea;
	
	//-> Variabile di controllo che determina se l'entita è in collisione con un ostacolo della mappa o con un altra entita
	public boolean collision = false;
}
