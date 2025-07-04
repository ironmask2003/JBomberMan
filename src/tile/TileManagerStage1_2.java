package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entita.Obstacle;
import entita.ObstacleStage1_2;
import stages.GamePanel;

public class TileManagerStage1_2 extends TileManager{
	
	// Dichiarazione dei campi
	
	// Costruttore della classe
	public TileManagerStage1_2(GamePanel gp) {
		super(gp);
		
		// Creazione della lista degli oggetti del livello
		tile = new Tile[21];
		
		// Creazione dell'ArrayList
		obs = new ArrayList<Obstacle>();
		
		// Creazione della matrice di interi
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		coordinateCaselle = new String[gp.maxScreenCol][gp.maxScreenRow];
		
		// Metodo che prende l'indirzzo delle immagini degli oggetti che compongono la mappa
		getTileImage();
		
		// Metodo che carica la mappa da un file txt alla matrice di interi -> mapTileNum
		loadMap("/maps/Stage 1 - 2.txt");
		
		// Piazza gli ostacoli nella mappa
		setObstacleOnMap();
	}
	
	// Metodo che carica gli indirizzi delle immagini degli elementi della mappa in una lista
	public void getTileImage() {
		try {
			// Ciclo for che aggiungie tutti gli oggetti della mappa
			for(int k = 0; k < 20; k ++) {
				tile[k] = new Tile();
				tile[k].image = ImageIO.read(getClass().getResourceAsStream("/tilesStage1_2/Tiles" + k + "_Mappa2.png"));
				if(k > 0) { tile[k].collision = true; }
			}
			
			// Caricamento dell'ostacolo
			tile[20] = new Tile();
			tile[20].collision = true;
			
		}catch(IOException e) { e.printStackTrace(); }
	}
	
	// Metodo che piazza casualmente gli ostacoli
	public void setObstacleOnMap() {
		
		for(int k = 0; k < 33; k ++) {
			
			// Sceglie randomicamente la riga e la colonna
			int r = (int) (Math.random() * mapTileNum.length);
			int c = (int) (Math.random() * mapTileNum[0].length);
			
			// Controlla se la casella è libera
			if((mapTileNum[r][c] == 0)) {
				
				// Controlla se la casella scelta non è troppo vicino al punto di partenza del player
				if((r == 2 && c == 1) || (r == 2 && c == 2) || (r == 3 && c == 1)) {
					
					// Se lo è ripete il ciclo
					k --;
					continue;
				}
				// Altrimenti piazza l'ostacolo e ne crea l'oggetto aggiungiendolo alla lista degli ostacoli
				mapTileNum[r][c] = 20;
				obs.add(new ObstacleStage1_2(r * gp.tileSize, c * gp.tileSize, gp));
				continue;
			}
			// Se la casella non è libera ripete il ciclo
			k --;
		}
	}
	
	// Metodo che carica gli elementi della mappa in una lista
	public void loadMap(String mapPath) {
		try {
			
			InputStream is = getClass().getResourceAsStream(mapPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.worldMaxCol && row < gp.worldMaxRow) {
				String line = br.readLine();
				while(col < gp.worldMaxCol) {
					String number[] = line.split(" ");
					int num = Integer.parseInt(number[col]);
					mapTileNum[col][row] = num;
					col ++;
				}
				if(col == gp.worldMaxCol) { col = 0; row ++; }
			}
			
			br.close();
			
		}catch(Exception e) { e.printStackTrace(); }
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
			
			if(TileNum != 20) { g2.drawImage(tile[TileNum].image, x, y, gp.tileSize, gp.tileSize, null); }
			
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
		mapTileNum[col][row] = 0;
	}
}
