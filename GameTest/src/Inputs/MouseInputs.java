package Inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import GameStates.GameState;
import entities.Player;
import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener{
	
	private GamePanel gamePanel;
	public MouseInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		switch (GameState.state) {
		case Playing:
			gamePanel.getPlaying().mouseDragged(e);
			break;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (GameState.state) {
		case LevelSelecter:
			gamePanel.getLevelSelecter().mouseMoved(e);
			break;
		
		case Menu:
			gamePanel.getMenu().mouseMoved(e);
			break;
		
		case Playing:
			gamePanel.getPlaying().mouseMoved(e);
			break;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (GameState.state) {
			case LevelSelecter:
				gamePanel.getLevelSelecter().mousePressed(e);
				break;
			
			case Menu:
				gamePanel.getMenu().mousePressed(e);
				break;
			
			case Playing:
				gamePanel.getPlaying().mousePressed(e);
				break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (GameState.state) {
			case LevelSelecter:
				gamePanel.getLevelSelecter().mouseReleased(e);
				break;
			
			case Menu:
				gamePanel.getMenu().mouseReleased(e);
				break;
			
			case Playing:
				gamePanel.getPlaying().mouseReleased(e);
				break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
