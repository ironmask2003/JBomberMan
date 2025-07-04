package stages;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	// Dichiarazione dei campi
	public boolean upPressed, downPressed, rightPressed, leftPressed, close;
	public boolean bomb;
	

	@Override
	public void keyTyped(KeyEvent e) {}

	// Metodo che controlla quale casto è stato premuto
	@Override
	public void keyPressed(KeyEvent e) {
		
		// Prende il codice del tasto che è stato premuto
		int code = e.getKeyCode();
		
		// Controlla quali dei sequenti tasti è stato premuto
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) upPressed = true;				// Quando viene premuto il tasto W
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) downPressed = true;			// Quando viene premuto il tasto S
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) leftPressed = true;			// Quando viene premuto il tasto A
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) rightPressed = true;			// Quando viene premuto il tasto D
		if(code == KeyEvent.VK_ESCAPE) close = true;										// Quando viene premuto il tasto ESC
		if(code == KeyEvent.VK_E) { bomb = true; }										// Quando viene premuto il tasto E
		
	}

	// Metodo che controlla quale casto è stato rilasciato
	@Override
	public void keyReleased(KeyEvent e) {
		
		// Prende il codice del tasto che è stato premuto
		int code = e.getKeyCode();
		
		// Controlla quali dei seguenti tasti è stato rilasciato
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) upPressed = false;				// Quando viene rilasciato il tasto W
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) downPressed = false;			// Quando viene rilasciato il tasto S
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) leftPressed = false;			// Quando viene rilasciato il tasto A
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) rightPressed = false;		// Quando viene rilasciato il tasto D
		if(code == KeyEvent.VK_ESCAPE) close = false;										// Quando viene rilasciato il tasto ESC
		if(code == KeyEvent.VK_E) bomb = false;												// Quando viene rilasciato il tasto E
	}
	

}
