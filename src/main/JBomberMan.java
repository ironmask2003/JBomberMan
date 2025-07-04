package main;


import continuePasswordPanel.ContinuePanel;
import continuePasswordPanel.PasswordPanel;
import menu.Menu;
import menu.NormalModeMap;
import stages.Gameplay;

public class JBomberMan implements Runnable{
	
	// Dichiarazione dei capi
	
	// Menu Map Normal Mode
	NormalModeMap normalMode;
	
	// World_1
	Gameplay stages; 
	
	// Pannello Menu
	Menu menu;
	
	// Pannello continue
	ContinuePanel cp;
	
	// Pannello password
	PasswordPanel pps;
	
	public JBomberMan() {
		stages = new Gameplay();
		cp = new ContinuePanel(stages.gp);
		pps = new PasswordPanel();
		menu = new Menu();
		menu.createPanel();
	}
	
	@Override
	public void run() {
		
		int mode = menu.start();
		
		switch(mode) {
		case 1:
			menuMap();
			game();
			break;
		case 2:
			System.exit(0);
			break;
		case 3:
			int level = PasswordPage();
			if (level != 0) {
				stages = new Gameplay(level);
				game();
			}
			else {
				menu.createPanel();
				run();
			}
			break;
		}
		
	}
	
	//-> Metodo che avvia il gioco
	public void game() {
		cp = new ContinuePanel(stages.gp);
		startStage();
		int level = ContinuePage();
		if(level != 0) {
			stages = new Gameplay(level);
			game();
		}
		else System.exit(0);
	}
	
	//-> Metodo che avvia il primo stage
	public void startStage() {
		stages.run();
	}
	
	//-> Metodo che avvia la finestra con la mappa della normal mode
	public void menuMap() {
		normalMode = new NormalModeMap();
		normalMode.createPanel();
	}
		
	//-> Metodo che avvia la schermata per mettere la password
	public int PasswordPage() {
		return pps.createPanel();
	}
	
	//-> Metodo che avvia la scheramat continue
	public int ContinuePage() {
		return cp.createPanel();
	}
	
	public static void main(String[] args) {
		new JBomberMan().run();
	}
	
}
