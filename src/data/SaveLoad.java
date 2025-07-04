package data;

import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;

import continuePasswordPanel.ContinuePanel;

import java.io.ObjectInputStream;

import stages.GamePanel;

public class SaveLoad {
	
	// Dichiarazione dei campi
	
	// Pannello in cui si trova i dati da salvare
	GamePanel gp;
	
	// Pannello in cui si trova la password
	ContinuePanel cp;
	
	// Costruttore della classe
	public SaveLoad(GamePanel gp, ContinuePanel cp) { this.gp = gp; this.cp = cp; }
	
	// Metodo che salva i dati
	public void save() {
		try { 
			
			// Creazione del file di salvataggio
			ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			
			DataStorage ds = new DataStorage();
			
			// Dati da salvare nel file
			ds.level = gp.level;
			ds.password = cp.password.password;
			
			outputFile.writeObject(ds);
			
		}catch(Exception e) { e.printStackTrace(); }
	}
	
	// Metodo che carica i dati
	public DataStorage load() {
		try {
			
			ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			
			// Lettura dei dati salvati nel file
			DataStorage ds = (DataStorage) inputFile.readObject();
			
			return ds;
			
		}catch(Exception e) { e.printStackTrace(); }
		return null;
	}
}
