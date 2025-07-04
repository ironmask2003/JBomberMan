package menu;

import javax.swing.*;
import audio.AudioManager;

public class Menu{
	
						//-> Dichiarazione dei campi <-//
	
	// Modalità scelta
	public int mode;
	
	// Audio background
	AudioManager back = new AudioManager();
	
	// Menu
	MenuPanel mp;
	
	// Finestra
	JFrame window;
	
						//-> Costruttore della classe <-//
	public Menu() {}
	
	public void createPanel() {
		window = new JFrame();
		mp = new MenuPanel();
		window.add(mp);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("JBomberMan");
		back.setFile(11);
		back.start();
	}

	public int start() {
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		mp.startMenuThread();
		modality();
		back.stop();
		close();
		
		return mode;
	}
	
	//-> Metodo che avvia la modalità scelta
	public void modality() {
		while(mp.getStateThread() != null) {
			continue;
		}
		mode = checkMode();
	}
	
	//-> Metodo per chiudere la finestra
	public void close() { window.dispose(); }
	
	//-> Metodo che controlla la modalità scelto
	public int checkMode() {
		if(mp.checkMode() == 1) {
			return 1;
		}
		else if(mp.checkMode() == 3) {
			return 3;
		}
		return 0;
	}
}
