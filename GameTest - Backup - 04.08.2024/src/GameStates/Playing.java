package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import UI.PauseOverlay;
import entities.Player;
import levels.LevelManager;
import main.GamePanel;
import utilz.LoadSave;

public class Playing extends State implements StateMethods{
	private Player player;
	private LevelManager levelManager;
	private PauseOverlay pauseOverlay;
	
	private boolean paused = false;
	
	private int xLvlOffset;
	private int leftBorder = (int) (0.2 * GamePanel.game_width);
	private int rightBorder = (int) (0.8 * GamePanel.game_width);
	private int lvlTilesWide = LoadSave.getLevelDataTest(0, LoadSave.importLevelData())[0].length;
	private int maxTileOffset = lvlTilesWide - GamePanel.tiles_in_width;
	private int maxLvlOffsetX = maxTileOffset * gamePanel.tiles_size;
		
	
	public Playing(GamePanel gamePanel) {
		super(gamePanel);
		initClasses();
		System.out.println(lvlTilesWide);
	}
	
//	TODO: Change the player's sprite and hitbox size to 2 tiles height, god fuck this ;-;
	
	public void initClasses() {
		levelManager = new LevelManager(gamePanel);
		player = new Player(200, 200, (int) (64 * gamePanel.scale), (int) (64 * gamePanel.scale));
		player.loadLevelData(LoadSave.getLevelDataTest(levelManager.getCurrentLevel().getMainLayer(), LoadSave.importLevelData()));
		pauseOverlay = new PauseOverlay(this);

	}

	@Override
	public void update() {
		if (!paused) {
			levelManager.update();
			player.update();
			checkCloseToBorder();
		}
		else {
			pauseOverlay.update();
		}
		
		
	}


	@Override
	public void draw(Graphics g) {
		levelManager.draw(g, xLvlOffset);
		player.render(g, xLvlOffset);

		if (paused) {			
			pauseOverlay.draw(g);			
		}
		
		
	}
	
	public void checkCloseToBorder() {
		int playerX = (int) player.getHitBox().x;
		int diff = playerX - xLvlOffset;
		
		if (diff > rightBorder) {
			xLvlOffset += diff - rightBorder;
		} else if (diff < leftBorder) {
			xLvlOffset += diff - leftBorder;
		}
		
		if (xLvlOffset > maxLvlOffsetX) {
			xLvlOffset = maxLvlOffsetX;
		} else if (xLvlOffset < 0) {
			xLvlOffset = 0;
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mousePressed(MouseEvent e) {
		if (paused) {
			pauseOverlay.mousePressed(e);
		}
		
		if (e.getButton() == MouseEvent.BUTTON1) {
			player.setAttacking(true);
		}
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		if (paused) {
			pauseOverlay.mouseReleased(e);
		}
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		if (paused) {
			pauseOverlay.mouseMoved(e);
		}
		
	}
	
	public void mouseDragged(MouseEvent e) {
		if (paused) {
			pauseOverlay.mouseDragged(e);
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
			case KeyEvent.VK_SPACE: {
				player.setJump(true);
				break;
			}
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:{
				player.setMovingLeft(true);
				break;
			}
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:{
				player.setMovingRight(true);
				break;
			}
			case KeyEvent.VK_ESCAPE: {
				paused = !paused;
			}
		}
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
			case KeyEvent.VK_SPACE: {
				player.setJump(false);
				break;
			}
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:{
				player.setMovingLeft(false);
				break;
			}
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:{
				player.setMovingRight(false);
				break;
			}
		}
		
	}
	
	public void windowFocusLost() {
		player.resetMovement();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void unpauseGame() {
		paused = false;
	}
}
