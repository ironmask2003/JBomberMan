package pointBar;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandlerMenu implements KeyListener{
	
	// Dichiarazione dei campi
	public boolean pause;

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_9) pause = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_9) pause = false;
	}

}
