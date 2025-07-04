package stages;

import entita.Enemy;
import entita.EnemyCuppen;
import entita.EnemyPakupa;
import entita.Entita;
import entita.Player;
import entita.PlayerBomb;

public class CollisionChecker {
	
	// Dichiarazione dei campi
	GamePanel gp;
	
	// Costruttore della classe
	public CollisionChecker(GamePanel gp) { this.gp = gp; }
	
	// Controllo collisioni per il player
	public void checkTile(Entita entity) {
		
		// Hitbox dell'entita
		updateHitBox(entity);
		
		// Coordinate del bordo dell'entita
		int entityLeftWorldX = entity.worldX + entity.solidAreaD.x;
		int entityRigthWorldX = entity.worldX + entity.solidAreaD.x + entity.solidAreaD.width;
		int entityTopWorldY = entity.worldY + entity.solidAreaD.y;
		int entityBottomWorldY = entity.worldY + entity.solidAreaD.y + entity.solidAreaD.height;
		
		// Cacolco della riga e colonna
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRigthCol = entityRigthWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		//-> Switch che controlla in base alla direzione dell'entita cosa ha difronte e controlla se è un ostacolo della mappa
		switch(entity.direction) {
		
		//-> Caso in cui si sta spostando verso sopra
		case "up":
			
			//-> Calcola la riga in cui si sta per spostare l'entita
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			
			//-> Calcoloa dello spostamento dell'hitbox
			entity.solidArea.y -= entity.speed;
			
			//-> Prende dalla mappa quali elementi si trovano nella riga in cui si sta spostando l'entita
			tileNum1 = gp.tile.mapTileNum[entityLeftCol][entityTopRow - 2];
			tileNum2 = gp.tile.mapTileNum[entityRigthCol][entityTopRow - 2];
			
			// Controlla se gli elementi di fronte a lui sono oggetti con la collisione attiva
			if(gp.tile.tile[tileNum1].collision || gp.tile.tile[tileNum2].collision) {
				if(entity instanceof EnemyCuppen) {
					if(tileNum1 >= 4 || tileNum2 >= 4) { entity.collision = true; }
				}
				else {
					// Se lo sono attiva la collisione dell'entita
					entity.collision = true;					
				}
			}
			
			// Controlla se l'entita è il player
			if(entity instanceof Player) {
				checkCollisionPlayer((Player) entity);
			}
			else if(entity instanceof EnemyPakupa) {
				checkCollisionEnemyPakupa(entity, "up");
			}
			else {
			// Controlla se l'entita è un nemico e le vaire collisioni del nemico
			chekcCollisionEnemy(entity); }
			
			break;
			
		//-> Caso in cui si sta spostando sotto
		case "down":
			
			//-> Calcola la riga in cui si sta per spostare l'entita
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			
			//-> Calcola l'hitbox dopo lo spostamento 
			entity.solidArea.y += entity.speed;
			
			//-> Prende dalla mappa quali elementi si trovano nella riga in cui si sta spostando l'entita
			tileNum1 = gp.tile.mapTileNum[entityLeftCol][entityBottomRow - 2];
			tileNum2 = gp.tile.mapTileNum[entityRigthCol][entityBottomRow - 2];
			
			// Controlla se gli elementi di fronte a lui sono oggetti con la collisione attiva
			if(gp.tile.tile[tileNum1].collision || gp.tile.tile[tileNum2].collision) {
				if(entity instanceof EnemyCuppen) {
					if(tileNum1 >= 4 || tileNum2 >= 4) { entity.collision = true; }
				}
				else {
					// Se lo sono attiva la collisione dell'entita
					entity.collision = true;					
				}
			}
			
			// Controlla se l'entita è il player
			if(entity instanceof Player) {
				checkCollisionPlayer((Player) entity);
			}
			else if(entity instanceof EnemyPakupa) {
				checkCollisionEnemyPakupa(entity, "down");
			}
			else {
			// Controlla se l'entita è un nemico e le vaire collisioni del nemico
			chekcCollisionEnemy(entity); }
			
			break;
			
		//-> Caso in cui si sta spostando verso sinistra
		case "left":
			
			//-> Calcola la colonna in cui si sta per spostare l'entita
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			
			//-> Calcola l'hitbox dopo lo spostamento
			entity.solidArea.x -= entity.speed;
			
			//-> Prende dalla mappa quali elementi si trovano nella colonna in cui si sta spostando l'entita
			tileNum1 = gp.tile.mapTileNum[entityLeftCol][entityTopRow - 2];
			tileNum2 = gp.tile.mapTileNum[entityLeftCol][entityBottomRow - 2];
			
			// Controlla se gli elementi di fronte a lui sono oggetti con la collisione attiva
			if(gp.tile.tile[tileNum1].collision || gp.tile.tile[tileNum2].collision) {
				if(entity instanceof EnemyCuppen) {
					if(tileNum1 >= 4 || tileNum2 >= 4) { entity.collision = true; }
				}
				else {
					// Se lo sono attiva la collisione dell'entita
					entity.collision = true;					
				}
			}
			
			// Controlla se l'entita è il player
			if(entity instanceof Player) {
				checkCollisionPlayer((Player) entity);
			}
			else if(entity instanceof EnemyPakupa) {
				checkCollisionEnemyPakupa(entity, "left");
			}
			else {
			// Controlla se l'entita è un nemico e le vaire collisioni del nemico
			chekcCollisionEnemy(entity); }
			
			break;
			
		//-> Caso in sui si sta spostando verso destra
		case "rigth":
			
			//-> Calcola la colonna in cui si sta per spostare l'entita
			entityRigthCol = (entityRigthWorldX + entity.speed)/gp.tileSize;
			
			//-> Calcola l'hitbox dopo lo spostamento
			entity.solidArea.x += entity.speed;
			
			//-> Prende dalla mappa quali elementi si trovano nella colonna in cui si sta spostando l'entita
			tileNum1 = gp.tile.mapTileNum[entityRigthCol][entityTopRow - 2];
			tileNum2 = gp.tile.mapTileNum[entityRigthCol][entityBottomRow - 2];
			
			// Controlla se gli elementi di fronte a lui sono oggetti con la collisione attiva
			if(gp.tile.tile[tileNum1].collision || gp.tile.tile[tileNum2].collision) {
				if(entity instanceof EnemyCuppen) {
					if(tileNum1 >= 4 || tileNum2 >= 4) { entity.collision = true; }
				}
				else {
					// Se lo sono attiva la collisione dell'entita
					entity.collision = true;					
				}
			}
			
			// Controlla se l'entita è il player
			if(entity instanceof Player) {
				checkCollisionPlayer((Player) entity);
			}
			else if(entity instanceof EnemyPakupa) {
				checkCollisionEnemyPakupa(entity, "rigth");
			}
			else {
			// Controlla se l'entita è un nemico e le vaire collisioni del nemico
			chekcCollisionEnemy(entity); }
			
			break;
		}
		
		// Reimposta le hitbox a quelle di default
		resetHitBox(entity);
	}
	
	// Metodo che controlla le collisioni per il player
	public void checkCollisionPlayer(Player entity) {
		
		// Cilco che itera sulla lista delle bombe piazzare dal player
		for(PlayerBomb bombe : gp.player.bombe) {
			// Aggiorna l'hitbox della bomba
			updateHitBox(bombe);
			// Controlla se il nemico sta andando contro il nemico
			if(entity.solidArea.intersects(bombe.solidArea) && bombe.placed == true) entity.collision = true;
			// Reimposta l'hitbox del nemico
			resetHitBox(bombe);
		}
		
	}
	
	// Metodo che controlla se l'entita presa in input è un nemico e controlla le varie collisioni
	public void chekcCollisionEnemy(Entita entity) {
		
		if(entity instanceof Enemy) {
			// Aggiorna l'hitbox del player
			updateHitBox(gp.player);
			// Controlla se il nemico sta andando contro il player
			if(entity.solidArea.intersects(gp.player.solidArea) && System.nanoTime() > gp.player.immortal && gp.player.death == false) gp.player.setDeath(true);
			// Reimposta l'hitbox del player
			resetHitBox(gp.player);
			
			// Cilco che itera nella lista dei nemici
			for(Enemy enemy : gp.allEnemyList) {
				if(enemy.equals(entity)) continue;
				// Aggiorna l'hitbox del nemico
				updateHitBox(enemy);
				// Controlla se un nemico sta andando contro un altro nemico
				if(entity.solidArea.intersects(enemy.solidArea)) entity.collision = true;
				// Reimposta l'hitbox del nemico
				resetHitBox(enemy);
			}
			
			// Cilco che itera sulla lista delle bombe piazzare dal player
			for(PlayerBomb bombe : gp.player.bombe) {
				// Aggiorna l'hitbox della bomba
				updateHitBox(bombe);
				// Controlla se il nemico sta andando contro il nemico
				if(entity.solidArea.intersects(bombe.solidArea)) entity.collision = true;
				// Reimposta l'hitbox del nemico
				resetHitBox(bombe);
			}
		}
		
	}
	
	// Controllo delle collisioni per il nemico pakupa
	public void checkCollisionEnemyPakupa(Entita entity, String direction) {
		
		// Aggiorna l'hitbox del player
		updateHitBox(gp.player);
		// Controlla se il nemico sta andando contro il player
		if(entity.solidArea.intersects(gp.player.solidArea) && System.nanoTime() > gp.player.immortal && gp.player.death == false) gp.player.setDeath(true);
		// Reimposta l'hitbox del player
		resetHitBox(gp.player);
		
		// Cilco che itera sulla lista delle bombe piazzare dal player
		for(PlayerBomb bombe : gp.player.bombe) {
			// Aggiorna l'hitbox della bomba
			updateHitBox(bombe);
			// Controlla se il nemico sta andando contro il nemico
			if(entity.solidArea.intersects(bombe.solidArea) && bombe.placed == false) { bombe.ex.endExplosion = true;}
			// Reimposta l'hitbox del nemico
			resetHitBox(bombe);
		}
		
		// Cilco che itera nella lista dei nemici
		for(Enemy enemy : gp.allEnemyList) {
			if(enemy.equals(entity)) continue;
			// Aggiorna l'hitbox del nemico
			updateHitBox(enemy);
			// Controlla se un nemico sta andando contro un altro nemico
			if(entity.solidArea.intersects(enemy.solidArea)) entity.collision = true;
			// Reimposta l'hitbox del nemico
			resetHitBox(enemy);
		}
		
	}
	
	// Funzione che presa un'entita in input calcola l'hitbox in base a dove si trova il player
	public void updateHitBox(Entita entita) {
		entita.solidArea.x = entita.worldX + entita.solidArea.x;
		entita.solidArea.y = entita.worldY + entita.solidArea.y;
	}
	
	// Funzione che presa un'entita in input gli reimpsta l'hitbox
	public void resetHitBox(Entita entita) {
		entita.solidArea.x = entita.solidAreaD.x;
		entita.solidArea.y = entita.solidAreaD.y;
	}
	
	
}
