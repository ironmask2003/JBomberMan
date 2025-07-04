package tile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import entita.Obstacle;
import stages.GamePanel;

public abstract class TileManager {
	
	// Dichiatazione dei campi
	
	// Pannello in cui appare la mappa
	GamePanel gp;
	
	//Lista degli ostacoli
	public ArrayList<Obstacle> obs;
	
	// Lista degli oggetti del livello
	public Tile tile[];
	
	// Matrice di interi che rappresenta la mappa -> Ogni intero rappresenta un oggetto diverso che compone la mappa
	public int mapTileNum[][];
		
	// Lista di stringhe che in ogni colonna salva le coordinate x y della mappa di gioco
	public String[][] coordinateCaselle;
	
	// Costruttore della classe
	public TileManager(GamePanel gp) { this.gp = gp; }
	
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
	
	// Metodo che dopo che è stato distrutto un ostacolo lo rimuove dalla mappa e lo sostituisce con il pavimento
	public void removeObstacle(int row, int col) {}

}
