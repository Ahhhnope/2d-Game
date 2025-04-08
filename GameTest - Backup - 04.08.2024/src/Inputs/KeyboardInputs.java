package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import GameStates.GameState;

import static utilz.Constants.Directions.*;
import main.GamePanel;

public class KeyboardInputs implements KeyListener {
	private GamePanel gamePanel;

	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;

	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (GameState.state) {
		case StartScreen:
			gamePanel.getStartScreen().keyPressed(e);
			break;
			
		case LevelSelecter:
			gamePanel.getLevelSelecter().keyPressed(e);
			break;
			
		case Menu:
			gamePanel.getMenu().keyPressed(e);
			break;
		
		case Playing:
			gamePanel.getPlaying().keyPressed(e);;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (GameState.state) {
		case StartScreen:
			gamePanel.getStartScreen().keyReleased(e);
			break;
		
		case LevelSelecter:
			gamePanel.getLevelSelecter().keyPressed(e);
			break;
			
		case Menu:
			gamePanel.getMenu().keyReleased(e);
			break;
		
		case Playing:
			gamePanel.getPlaying().keyReleased(e);
			break;
		}
	}

}
