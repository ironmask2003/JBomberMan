package pointBar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import stages.GamePanel;
import title.GameOver;
import title.PresStart;
import title.ScorePlayer;

/**	--------	Classe che rappresenta la barra dei punti e il timer	--------	*/

public class PointPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	// Dichiarazione dei campi	
	
	// Impostazioni del pannello
	final int originalTileSize = 16;
	final int scale = 3;
	public final int tileSize = originalTileSize * scale;
	
	// Pannello in cui appare la barra
	GamePanel gp;
	
	// Title score "SC"
	ScorePlayer sc;
	
	// Title press start
	PresStart ps;
	
	// Vite del player
	LifePlayer life;
	
	// Punti del player
	PointPlayer pointP;
	
	// Title game overo
	GameOver gv;
	
	// Variabile di controllo per la fine della partita
	public boolean endTimer = false;
	
	// Orologio
	public Clock ck;
	
	public KeyHandlerMenu keyH = new KeyHandlerMenu();
	
	//-> Image
	BufferedImage background, pauseImage, back;
	
	// Delay per il tasto premuto
	double delay = 0.2 * Math.pow(10, 9);
	double delayPressed = 0;
	
	//-> Punto di partenza per l'immagine della barra dei punti
	int k = -2 * 48;
	
	//-> Punto di partenza per l'immagine di pausa
	int kp = 2 * 48;
	
	//-> Variabile booleana che controlla se il gioco è in pausa
	public int stop = 0;
	
	//-> Variabile che controlla se è stato appena avviato il gioco e mostrare solo l'immagine dei punti
	public int startMethod = 0;
	
	//-> Costruttore della classe
	public PointPanel(GamePanel gp) {
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
		// Pannello di gioco
		this.gp = gp;
		
		// Orologio con timer
		ck = new Clock(this);
		
		// Vita del player
		life = new LifePlayer(gp, this);
		
		// Scritta score player
		sc = new ScorePlayer(this);
		
		// Scritta Press start
		ps = new PresStart(this);
		
		// Punteggio del player
		pointP = new PointPlayer(this, gp);
		
		// Scritta Game Over
		gv = new GameOver(this);
		
		//-> Metodo che prende le immagini dalle risorse del progetto
		getImageBackground();
	}
	
	//-> Metodo che imposta il timer
	public void setTimer() {
		
		//-> Reimposta il timer
		ck.resetTime();
	}
	
	//-> Metodo che prende l'indirizzo dell'immagine della barra dei punti e dell'immagine pause
	public void getImageBackground() {
		try {
			
			background = ImageIO.read(getClass().getResourceAsStream("/tilesPoint/Tiles_mainBar.png"));
			pauseImage = ImageIO.read(getClass().getResourceAsStream("/tilesPoint/Tiles_pause.png"));
			back = ImageIO.read(getClass().getResourceAsStream("/tilesPoint/Background.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//-> Metodo che imposta il background della barra dei punti
	public void draw(Graphics g) {
		
		//-> Controlla se il gioco è in pausa
		if(stop == 0) {
			
			BufferedImage image = background;
			
			//-> Controlla se il livello è stato appena avviato
			if(startMethod == 0) {
				
				// Se è stato appena chiamato fa apparire dall'alto la barra dei punti
				g.drawImage(image, 0, k, this);
				
				ck.draw(g, 16/2 * tileSize - 4, 16 + k);
				
				life.draw(g, k);
				
				sc.draw(g, k);
				
				pointP.draw(g, k);
				
				if(gp.player.life <= 0) { gv.draw(g, k); }
				else { ps.draw(g, k); }
				
				if(k < 0){ k += 4; }
				else startMethod = 1;
				
			}
			//-> Altrimneti porta in alto la barra dei punti e fa apparire la scritta pause
			else {
				
				g.drawImage(image, 0, k, this);
				
				g.drawImage(pauseImage, 0, kp, this);
				
				ck.draw(g, 16/2 * tileSize - 4, 16 + k);
				
				life.draw(g, k);
				
				sc.draw(g, k);
				
				pointP.draw(g, k);
				
				if(gp.player.life <= 0) { gv.draw(g, k); }
				else { ps.draw(g, k); }
				
				if(k < 0 && kp < 2 * 48) {
					k += 4;
					kp += 4;
				}
				
			}
			
		}
		//-> Se il gioco non è più in pausa
		else {
			
			BufferedImage image = background;		//-> Salva in un'altra variabile l'imamgine della barra dei putni
			
			//-> Porta in basso l'immagine pause e fa ri-apparire la barra dei punti
			g.drawImage(image, 0, k, this);
			
			ck.draw(g, 16/2 * tileSize - 4, 16 + k);
			
			g.drawImage(pauseImage, 0, kp, this);
			
			life.draw(g, k);
			
			sc.draw(g, k);
			
			pointP.draw(g, k);
			
			if(gp.player.life <= 0) { gv.draw(g, k); }
			else { ps.draw(g, k); }
			
			if(k > -2*48 && kp > 0) {
				k -= 4;
				kp -= 4;
			}
		}
	}
	
	//-> Metodo che aggiorna il timer e controlla se il gioco è in pausa o no
	public void update() {
		
		pointP.update();
		
		//-> Controlla se non è in pausa e se non lo è aggiorna il timer
		if(stop == 0) { ck.update(); }
		
		//-> Controlla se è stato premuto il tasto per mettere in pausa il gioco
		if(keyH.pause == true && System.nanoTime() > delayPressed) {
			
			gp.startSound(6);
			
			//-> Se è stato premuto aggiorna la variabile delaypressed per mettere un delay tra ogni pressione del tasto
			delayPressed = delay + System.nanoTime();
			
			//-> Controlla se il gioco è in paua
			if(stop == 1) {
				
				//-> Se è in pausa imposta la variabile a 0 per avviarlo
				stop = 0;
			}
			//-> Se non è in pausa lo mette in pausa
			else if(stop == 0) { stop = 1; }
		}
	}
	
}
