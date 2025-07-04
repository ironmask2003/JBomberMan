package menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cursore {
	
	// Dichiarazione dei campi
	public int x;
	public int y;
	
	MenuPanel mp;
	KeyHandlerMenu keyH;
	
	BufferedImage image;
	
	// posizioni del cursore
	int[] normalMode;
	int[] battleMode;
	int[] password;
	
	// Costruttore della classe
	public Cursore(MenuPanel mp, KeyHandlerMenu keyH) {
		this.mp = mp;
		this.keyH = keyH;
		
		normalMode = new int[] {(4 * mp.tileSize) - 4, (9 * mp.tileSize) - 12};
		battleMode = new int[] {normalMode[0], normalMode[1] + (mp.tileSize - 12)};
		password =  new int[] {battleMode[0], battleMode[1] + (mp.tileSize - 12) + 5};
		
		setDefaultPosition();
		getImage();
	}
	
	// Metodo che prende l'indirizzo dell'immagine del cursore
	public void getImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/tilesMenu/Tile_Cursore.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// Metodo che imposta la posizione iniziale del cursore
	public void setDefaultPosition() {
		x = normalMode[0];
		y = normalMode[1];
	}
	
	// Metodo che aggiorna la posizione del cursore
	public void update() {
		
		// Controlla se è stato premuto un tasto
		if(keyH.downPressed || keyH.upPressed) {
			mp.audio.setFile(12);
			mp.audio.start();
			if(keyH.upPressed) {
				if(y == normalMode[1]) y = password[1];
				else if(y == battleMode[1]) y = normalMode[1];
				else if(y == password[1]) y = battleMode[1];
			}
			else if(keyH.downPressed) {
				if(y == normalMode[1]) y = battleMode[1];
				else if(y == battleMode[1]) y = password[1];
				else if(y == password[1]) y = normalMode[1];
			}
		}
	}
	
	// Metodo che rappresenta nella finestra il cursore
	public void draw(Graphics g) {
		g.drawImage(image, x, y, mp.tileSize + 32, mp.tileSize + 32, null);
	}
}
