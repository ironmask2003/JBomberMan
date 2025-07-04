package stages;

import javax.swing.JFrame;

public class Gameplay implements Runnable{
	
	// Dichiarazione dei campi
	JFrame window;
	public GamePanel gp;
	
	// Costruttore della classe
	public Gameplay() {
		window = new JFrame();																	// Creazione della finestra
		gp = new GamePanel();																		// Creazione del pannello del campo di gioco
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// Imposta il metoto di default per chiudere la finestra
		window.setResizable(false);																// Non rende possibile la possibilità di ingrandire con il cursore la finestra
		window.setTitle("JBomberMan");														// Imposta il titolo che verrà visualizzato nella barra della finestra
		window.add(gp);																					// Aggiunge il pannello del campo di gioco nella parte inferiore della finestra
		window.pack();
		
	}
	
	// Costruttore della classe che prendo il livello da cui iniziare in input
	public Gameplay(int level) {
		window = new JFrame();																	// Creazione della finestra
		gp = new GamePanel(level);																// Creazione del pannello del campo di gioco
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// Imposta il metoto di default per chiudere la finestra
		window.setResizable(false);																// Non rende possibile la possibilità di ingrandire con il cursore la finestra
		window.setTitle("JBomberMan");														// Imposta il titolo che verrà visualizzato nella barra della finestra
		window.add(gp);																					// Aggiunge il pannello del campo di gioco nella parte inferiore della finestra
		window.pack();
	}
	
	// Metodo dell'interfaccia Runnable
	@Override
	public void run() {
		gp.startSound(7);
		window.setLocationRelativeTo(null);							// Apre la finestra al centro dello schermo
		window.setVisible(true);											// Rende visibile la finestra
		
		
		gp.startGameThread();												// Chiama un metodo di GamePanel che avvai il thread del pannello
		
		// Controlla se il thread è ancora attivo
		while(gp.getStateThread() != null) {
			continue;
		}
		
		// Appena viene annullato il thread (Ovvero quando viene premuto il tasto ENTER) chiude la finestra
		close(window);
	}

	//Metodo che chiude la finestra
	public void close(JFrame window) {
		window.dispose();
	}
}
