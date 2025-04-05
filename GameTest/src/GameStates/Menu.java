package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import UI.MenuButton;
import main.GamePanel;
import utilz.LoadSave;

public class Menu extends State implements StateMethods{
	
	private MenuButton[] buttons = new MenuButton[3];
	private BufferedImage backgroundImg, panel;
	private int menuPanelX, menuPanelY, menuWidth, menuHeight;
	
	public Menu(GamePanel gamePanel) {
		super(gamePanel);
		loadButtons();
		loadStuff();
	}

	private void loadStuff() {
		panel = LoadSave.GetSpriteAtlas(LoadSave.MenuPanel);
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MenuBackground);
		
		menuWidth = (int) (panel.getWidth() * GamePanel.scale);
		menuHeight = (int) (panel.getHeight() * GamePanel.scale);
		menuPanelX = GamePanel.game_width / 2 - menuWidth / 2;
		menuPanelY = (int) (60 * GamePanel.scale);
	}
	
	private void loadButtons() {
		buttons[0] = new MenuButton(gamePanel.game_width / 2, (int) (165 * gamePanel.scale), 0, GameState.Playing);
		buttons[1] = new MenuButton(gamePanel.game_width / 2, (int) (235 * gamePanel.scale), 1, GameState.Option);
		buttons[2] = new MenuButton(gamePanel.game_width / 2, (int) (305 * gamePanel.scale), 2, GameState.Quit);

	}
	
	private void resetButtons() {
		for (MenuButton mb : buttons) {
			mb.resetMouseBools();
		}
	}

	@Override
	public void update() {
		for (MenuButton mb : buttons) {
			mb.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(backgroundImg, 0, 0, GamePanel.game_width, GamePanel.game_height, null);
		g.drawImage(panel, menuPanelX, menuPanelY, menuWidth, menuHeight, null);
		
		for (MenuButton mb : buttons) {
			mb.draw(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (MenuButton mb : buttons) {
			if (isIn(e, mb)) {
				mb.setMousePressed(true);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (MenuButton mb : buttons) {
			if (isIn(e, mb) && mb.isMousePressed()) {
				mb.applyGameState();
			}
		}
		
		resetButtons();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (MenuButton mb : buttons) {
			mb.setMouseOver(false);
		}
		for (MenuButton mb : buttons) {
			if (isIn(e, mb)) {
				mb.setMouseOver(true);
				break;
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			GameState.state = GameState.StartScreen;
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			GameState.state = GameState.Playing;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		
	}

}
