package stageStart;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class StartStage {
	
	// Dichiarazione dei campi
	
	// Animazione background
	int spriteNum = 1;
	int spriteCounter = 0;
	
	// Coordinate del primo numero
	int posXnum1;
	int posYnum1;
	
	// Coordinate del secondo numero
	int posXnum2;
	int posYnum2;
	
	// Coordinate del trattino
	int posXdash;
	int posYdash;
	
	// Coordinate della scritta start
	int posXstart;
	int posYstart;
	
	// Velocità transazione
	int speed;
	
	// Images
	BufferedImage number1, number2, start, dash;
	
	// Background
	BufferedImage back1, back2, back3, back4, back5;
	
	// Variabile booleana che controlla se l'animazione della scritta è finita
	public boolean endTransition = false;
	public boolean endSecondTransition = false;
	
	// Costruttore
	public StartStage() {
		
		setDefaultValues();
		getImageNumbers();
		
	}
	
	// Metodo che imposta le coordinate iniziali delle imamgini
	public void setDefaultValues() {
		speed = 25;
		
		// Coordinate del primo numero
		posXnum1 = 210;
		posYnum1 = 300;
		
		// Coordinate del secondo numero
		posXnum2 = 470;
		posYnum2 = 300;
		
		// Coordinate del trattino
		posXdash = 360;
		posYdash = 350;
		
		// Coordinate della scritta start
		posXstart = 250;
		posYstart = 450;
	}
	
	// Animazione background
	public void updateBack() {
		
		spriteCounter ++;
		
		if(spriteCounter > 5) {
			
			if(spriteNum == 1) spriteNum = 2;
			else if(spriteNum == 2) spriteNum = 3;
			else if(spriteNum == 3) spriteNum = 4;
			else if(spriteNum == 4) spriteNum = 5;			
			else if(spriteNum == 5) spriteNum = 0;			
			
			spriteCounter = 0;
		}
		
		
	}
	
	// Metodo che prende le immagini dalle risorse del progetto
	public void getImageNumbers() {
		try {
			
			start = ImageIO.read(getClass().getResourceAsStream("/numbersStage/Tiles_start.png"));
			dash = ImageIO.read(getClass().getResourceAsStream("/numbersStage/Tiles_dash.png"));
			
			back1 = ImageIO.read(getClass().getResourceAsStream("/background/Background_Black_1.png"));
			back2 = ImageIO.read(getClass().getResourceAsStream("/background/Background_Black_2.png"));
			back3 = ImageIO.read(getClass().getResourceAsStream("/background/Background_Black_3.png"));
			back4 = ImageIO.read(getClass().getResourceAsStream("/background/Background_Black_4.png"));
			back5 = ImageIO.read(getClass().getResourceAsStream("/background/Background_Black_5.png"));
			
		}catch(IOException e) { e.printStackTrace(); }
	}
	
	// Metodo che disegna le immagini nel pannello
	public void draw(Graphics g) {}

	public void draw(Graphics g, boolean endTransition2) {}

	public void drawBackground(Graphics g) {}
}
