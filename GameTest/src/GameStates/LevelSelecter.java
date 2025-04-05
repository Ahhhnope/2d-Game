package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import UI.MenuButton;
import main.GamePanel;
import utilz.LoadSave;

public class LevelSelecter extends State implements StateMethods {
	BufferedImage background;
	BufferedImage previewLevel1;
	BufferedImage previewLevel2;
	BufferedImage previewLevel3;
	int level1PreviewX, level2PreviewX, level3PreviewX;
	
	MenuButton[] buttons = new MenuButton[3];
	MenuButton playLvl1, playLvl2, playLvl3;
	
	public LevelSelecter(GamePanel gamePanel) {
		super(gamePanel);
		loadStuff();
	}
	
	public void loadStuff() {
		background = LoadSave.GetSpriteAtlas(LoadSave.MenuBackground);
		previewLevel1 = LoadSave.GetSpriteAtlas(LoadSave.Level1Preview);
		previewLevel2 = LoadSave.GetSpriteAtlas(LoadSave.Level2Preview);
		previewLevel3 = LoadSave.GetSpriteAtlas(LoadSave.Level3Preview);
				
		level1PreviewX = (int) (80 * GamePanel.scale);
		level2PreviewX = (int) (320 * GamePanel.scale);
		level3PreviewX = (int) (560 * GamePanel.scale);
		
		buttons[0] = new MenuButton((int) (175 * GamePanel.scale), (int) (300 * gamePanel.scale), 0, GameState.Playing);
		buttons[1] = new MenuButton((int) (417 * GamePanel.scale), (int) (300 * gamePanel.scale), 0, GameState.Playing);
		buttons[2] = new MenuButton((int) (655 * GamePanel.scale), (int) (300 * gamePanel.scale), 0, GameState.Playing);
		System.out.println(buttons[0].getBounds().width);
		
	}
	
	@Override
	public void update() {
		for (MenuButton mb : buttons) {
			mb.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(background, 0, 0, GamePanel.game_width, GamePanel.game_height, null);
		g.drawImage(previewLevel1, level1PreviewX, 200, (int) (previewLevel1.getWidth() * 1.2 * GamePanel.scale), (int) (previewLevel1.getHeight() * 1.2 * GamePanel.scale), null);
		g.drawImage(previewLevel2, level2PreviewX, 200, (int) (previewLevel2.getWidth() * 1.2 * GamePanel.scale), (int) (previewLevel2.getHeight() * 1.2 * GamePanel.scale), null);
		g.drawImage(previewLevel3, level3PreviewX, 200, (int) (previewLevel3.getWidth() * 1.2 * GamePanel.scale), (int) (previewLevel3.getHeight() * 1.2 * GamePanel.scale), null);
		
		for (MenuButton mb : buttons) {
			mb.draw(g);
		}
		
	}
	
	
	
	
	private void resetButtons() {
		for (MenuButton mb : buttons) {
			mb.resetMouseBools();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
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
			GameState.state = GameState.Menu;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
