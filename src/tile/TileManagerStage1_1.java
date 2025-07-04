package tile;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entita.Obstacle;
import entita.ObstacleStage1_1;
import stages.GamePanel;

public class TileManagerStage1_1 extends TileManager{
	
	// Dichiarazione dei campi
	
	// Costruttore della classe
	public TileManagerStage1_1(GamePanel gp) {
		super(gp);
		
		// Creazione della lista degli oggetti del livello
		tile = new Tile[19];
		
		// Creazione dell'ArrayList
		obs = new ArrayList<Obstacle>();
		
		// Creazione della matrice di interi
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		coordinateCaselle = new String[gp.maxScreenCol][gp.maxScreenRow];
		
		// Metodo che prende l'indirzzo delle immagini degli oggetti che compongono la mappa
		getTileImage();
		
		// Metodo che carica la mappa da un file txt alla matrice di interi -> mapTileNum
		loadMap("/maps/Stage 1 - 1.txt");
		
		// Metodo che piazza casualemente gli ostacoli
		setObstacleOnMap();
	}
	
	// Metodo che carica gli indirizzi delle immagini degli elementi della mappa in una lista
	public void getTileImage() {
		try {
			
			// Ciclo for che aggiungie tutti gli oggetti della mappa
			for(int k = 1; k < 17; k ++) {
				tile[k] = new Tile();
				tile[k].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tiles" + k + "_Mappa1.png"));
				if(k > 2) {
					tile[k].collision = true;
				}
			}
			
			// Caricamento del muro dello stage 1 - 1
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tiles_Mappa1.png"));
			tile[0].collision = true;
			
			// Caricamento dell'ostacolo rimovibile con la bomba
			tile[17] = new Tile();
			tile[17].collision = true;
			
			// Caricamento del portale
			tile[18] = new Tile();
			
		}catch(IOException e) { e.printStackTrace(); }
	}
	
	// Metodo che piazza casualmente gli ostacoli
	public void setObstacleOnMap() {
		
		for(int k = 0; k < 33; k ++) {
			
			// Sceglie randomicamente la riga e la colonna
			int r = (int) (Math.random() * mapTileNum.length);
			int c = (int) (Math.random() * mapTileNum[0].length);
			
			// Controlla se la casella è libera
			if((mapTileNum[r][c] == 2 || mapTileNum[r][c] == 1)) {
				
				// Controlla se la casella scelta non è troppo vicino al punto di partenza del player
				if((r == 2 && c == 1) || (r == 2 && c == 2) || (r == 3 && c == 1)) {
					
					// Se lo è ripete il ciclo
					k --;
					continue;
				}
				// Altrimenti piazza l'ostacolo e ne crea l'oggetto aggiungiendolo alla lista degli ostacoli
				mapTileNum[r][c] = 17;
				obs.add(new ObstacleStage1_1(r * gp.tileSize, c * gp.tileSize, gp));
				continue;
			}
			// Se la casella non è libera ripete il ciclo
			k --;
		}
	}
	
	// Metodo che riempe la mappa degli elementi di sfondo e gli ostacoli
	public void draw(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 2 * gp.tileSize;
		
		while(col < gp.worldMaxCol && row < gp.worldMaxRow ) {
			
			int TileNum = mapTileNum[col][row];
			
			if(TileNum < 3 || TileNum > 16) { coordinateCaselle[col][row] = x + " " + y;}
			
			if(TileNum != 17 && TileNum != 18) { g2.drawImage(tile[TileNum].image, x, y, gp.tileSize, gp.tileSize, null); }
			
			col++;
			x += gp.tileSize;
			if(col == gp.worldMaxCol) {
				col = 0;
				x = 0;
				row ++;
				y += gp.tileSize;
			}
			
		}
	}
	
	public void removeObstacle(int row, int col) {
		if(row == 1) mapTileNum[col][row] = 1;
		else mapTileNum[col][row] = 2;
	}
}
