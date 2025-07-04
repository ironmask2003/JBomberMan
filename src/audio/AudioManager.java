package audio;

import java.net.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioManager {
	
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public AudioManager() {
		
		soundURL[0] = getClass().getResource("/audio/Level 1.wav");
		soundURL[1] = getClass().getResource("/audio/Place Bomb.wav");
		soundURL[2] = getClass().getResource("/audio/Walking 1.wav");
		soundURL[3] = getClass().getResource("/audio/Bomb Explodes.wav");
		soundURL[4] = getClass().getResource("/audio/Bomberman Dies.wav");
		soundURL[5] = getClass().getResource("/audio/Item Get.wav");
		soundURL[6] = getClass().getResource("/audio/Pause.wav");
		soundURL[7] = getClass().getResource("/audio/Stage intro.wav");
		soundURL[8] = getClass().getResource("/audio/Enemy Dies.wav");
		soundURL[9] = getClass().getResource("/audio/Stage Clear.wav");
		soundURL[10] = getClass().getResource("/audio/Time Up.wav");
		soundURL[11] = getClass().getResource("/audio/Menu.wav");
		soundURL[12] = getClass().getResource("/audio/Cursor.wav");
		soundURL[13] = getClass().getResource("/audio/Select.wav");
		soundURL[14] = getClass().getResource("/audio/Map.wav");
		soundURL[15] = getClass().getResource("/audio/Password.wav");
		
	}
	
	public void setFile(int i) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e) { }
		
	}
	
	public void start() {
		clip.start();
	}
		
	public void stop() {
		clip.stop();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
}
