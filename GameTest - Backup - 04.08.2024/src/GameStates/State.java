package GameStates;

import java.awt.event.MouseEvent;

import UI.MenuButton;
import main.GamePanel;

public class State {
	protected GamePanel gamePanel;
	
	public State(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public boolean isIn(MouseEvent e, MenuButton mb) {
		
		return mb.getBounds().contains(e.getX(), e.getY());
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}
}
