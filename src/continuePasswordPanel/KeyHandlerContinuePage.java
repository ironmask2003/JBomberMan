package continuePasswordPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandlerContinuePage implements KeyListener{
	
	// Dichiarazione dei campi
	public boolean enter, esc;
	
	public boolean rigthPressed, leftPressed;
	
	public boolean upPressed, downPressed;

	@Override
	public void keyTyped(KeyEvent e) {	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_ENTER) { enter = true; }
		else if(code == KeyEvent.VK_ESCAPE) { esc = true; }
		else if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) { leftPressed = true; }
		else if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) { rigthPressed = true; }
		else if(code == KeyEvent.VK_UP) { upPressed = true; }
		else if(code == KeyEvent.VK_DOWN) { downPressed = true; }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_ENTER) { enter = false; }
		else if(code == KeyEvent.VK_ESCAPE) { esc = false; }
		else if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) { leftPressed = false; }
		else if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) { rigthPressed = false; }
		else if(code == KeyEvent.VK_UP) { upPressed = false; }
		else if(code == KeyEvent.VK_DOWN) { downPressed = false; }
		
	}

}
