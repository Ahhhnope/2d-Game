package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static utilz.Constants.Directions.*;
import main.GamePanel;
import main.GamePanelv2;

public class KeyboardInputs implements KeyListener {
	private GamePanel gamePanel;
	public boolean attacking;

	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;

	}

	public void attackChange() {
		attacking = false;
	}


	@Override
	public void keyTyped(KeyEvent e) {
	}



	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_W: {
			gamePanel.setDirection(Up);
			break;
		}
		case KeyEvent.VK_A: {
			gamePanel.setDirection(Left);
			break;
		}
		case KeyEvent.VK_S: {
			gamePanel.setDirection(Down);
			break;
		}
		case KeyEvent.VK_D: {
			gamePanel.setDirection(Right);
			break;
		}
		case KeyEvent.VK_Z: {
			attacking = true;
			gamePanel.attack(code);
			break;
		}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
			case KeyEvent.VK_W:
			case KeyEvent.VK_A:
			case KeyEvent.VK_S:
			case KeyEvent.VK_D:
				gamePanel.setMoving(false);
				break;
	
			case KeyEvent.VK_Z: {
				gamePanel.attack(code);
				break;
			}
		}
	}

}
