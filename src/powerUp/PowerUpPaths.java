package powerUp;

public enum PowerUpPaths {
	
	HEART("/powerUp/PowerUp_Heart"),
	ACCELERATOR("/powerUp/PowerUp_Accelerator"),
	EXTRABOMB("/powerUp/PowerUp_ExtraBomb");
	
	// Percorso dell'immagine
	String path;
	
	// Costruttore della classe
	PowerUpPaths(String path) { this.path = path; }
	
	// Metodo che ritorna la stringa
	public String getPath() { return path; }
}
