package entita;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import menu.MenuPanel;

public class AirShip extends Entita {
	
	// Dichiarazione dei campi
	
	// Pannello in cui viene rappresentato l'entita
	MenuPanel mp;
	
	// Immagini dell'airShip
	BufferedImage air1, air2, air3, air4, air5, air6, air7, air8, air9, air10, air11, air12, air13;
	
	
	// Costruttore della classe
	public AirShip(MenuPanel mp) {
		this.mp = mp;
		
		getImageAirShip();
		setDefaultValues();
		
		spriteCounter = 1;
		
	}
	
	// Metodo che imposta le coordinate iniziali dell'entita
	public void setDefaultValues() {
		
		speed = 1;
		worldX = 600;
		worldY = 250;
		
	}
	
	// Metodo che prende le immagini dalle risore del progetto
	public void getImageAirShip(){
		
		try {
			
			// Air Ship
			air1 = ImageIO.read(getClass().getResourceAsStream("/tilesMenu/airShip/Tiles_airShip_1.png"));
			air2 = ImageIO.read(getClass().getResourceAsStream("/tilesMenu/airShip/Tiles_airShip_2.png"));
			air3 = ImageIO.read(getClass().getResourceAsStream("/tilesMenu/airShip/Tiles_airShip_3.png"));
			air4 = ImageIO.read(getClass().getResourceAsStream("/tilesMenu/airShip/Tiles_airShip_4.png"));
			air5 = ImageIO.read(getClass().getResourceAsStream("/tilesMenu/airShip/Tiles_airShip_5.png"));
			air6 = ImageIO.read(getClass().getResourceAsStream("/tilesMenu/airShip/Tiles_airShip_6.png"));
			air7 = ImageIO.read(getClass().getResourceAsStream("/tilesMenu/airShip/Tiles_airShip_7.png"));
			air8 = ImageIO.read(getClass().getResourceAsStream("/tilesMenu/airShip/Tiles_airShip_8.png"));
			air9 = ImageIO.read(getClass().getResourceAsStream("/tilesMenu/airShip/Tiles_airShip_9.png"));
			air10 = ImageIO.read(getClass().getResourceAsStream("/tilesMenu/airShip/Tiles_airShip_10.png"));
			air11 = ImageIO.read(getClass().getResourceAsStream("/tilesMenu/airShip/Tiles_airShip_11.png"));
			air12 = ImageIO.read(getClass().getResourceAsStream("/tilesMenu/airShip/Tiles_airShip_12.png"));
			air13 = ImageIO.read(getClass().getResourceAsStream("/tilesMenu/airShip/Tiles_airShip_13.png"));
			
		}catch(IOException e) { e.printStackTrace(); }
		
	}
	
	// Metodo che aggiorna le imamgini del Air Ship
	public void update() {
		
		spriteCounter ++;
		
		if(spriteCounter > 1) {
			
			spriteNum = switch(spriteNum) {
			case 1 -> 2;
			case 2 -> 3;
			case 3 -> 4;
			case 4 -> 5;
			case 5 -> 6;
			case 6 -> 7;
			case 7 -> 8;
			case 8 -> 9;
			case 9 -> 10;
			case 10 -> 11;
			case 11 -> 12;
			case 12 -> 13;
			case 13 -> 1;
			default -> throw new IllegalArgumentException("Unexpected value: " + spriteNum);
			};
			
			spriteCounter = 1;
		}
		
	}
	
	// Metodo che disegna l'airShip
	public void draw(Graphics g) {
		
		BufferedImage image = switch(spriteNum) {
		case 1 -> air1;
		case 2 -> air2;
		case 3 -> air3;
		case 4 -> air4;
		case 5 -> air5;
		case 6 -> air6;
		case 7 -> air7;
		case 8 -> air8;
		case 9 -> air9;
		case 10 -> air10;
		case 11 -> air11;
		case 12 -> air12;
		case 13 -> air13;
		default -> throw new IllegalArgumentException("Unexpected value: " + spriteNum);
		};
		
		g.drawImage(image, worldX, worldY, mp.tileSize * 4 + 48, mp.tileSize * 3, mp);
		worldX -= speed;
		
	}
	
	
}
