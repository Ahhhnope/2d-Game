package UI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import GameStates.GameState;
import GameStates.Playing;
import main.GamePanel;
import utilz.LoadSave;

import static utilz.Constants.UI.PauseButton.*;
import static utilz.Constants.UI.UtilButton.*;
import static utilz.Constants.UI.VolumeButtons.*;

public class PauseOverlay {
	
	private Playing playing;
	private BufferedImage panel;
	private int panelX, panelY, panelW, panelH;
	private SoundButton musicButton, sfxButton;
	private UtilityButton menuButton, replayButton, unpauseButton;
	private VolumeButton volumeButton;

	public PauseOverlay(Playing playing) {
		this.playing = playing;
		loadStuff();
		initButtons();
	}
	
	public void initButtons() {
//		Sound buttons
		int soundButtonX = (int) (450 * GamePanel.scale);
		int musicY = (int) (140 * GamePanel.scale);
		int sfxY = (int) (186 * GamePanel.scale);
		musicButton = new SoundButton(soundButtonX, musicY, Sound_Button_Size, Sound_Button_Size);
		sfxButton = new SoundButton(soundButtonX, sfxY, Sound_Button_Size, Sound_Button_Size);

//		Volume slider
		int vX = (int) (309 * GamePanel.scale);
		int vY = (int) (278 * GamePanel.scale);
		volumeButton = new VolumeButton(vX, vY, Volume_Slider_Width, Volume_Height);
		
//		Utility buttons
		int menuX = (int) (313 * GamePanel.scale);
		int replayX = (int) (387 * GamePanel.scale);
		int unpauseX = (int) (462 * GamePanel.scale);
		int buttonY = (int) (325 * GamePanel.scale);
		
		unpauseButton = new UtilityButton(unpauseX, buttonY, Util_Button_Size, Util_Button_Size, 0);
		replayButton = new UtilityButton(replayX, buttonY, Util_Button_Size, Util_Button_Size, 1);
		menuButton = new UtilityButton(menuX, buttonY, Util_Button_Size, Util_Button_Size, 2);
		
	}
	
	public void loadStuff() {
		panel = LoadSave.GetSpriteAtlas(LoadSave.PausedMenuPanel);
		panelW = (int) (panel.getWidth() * GamePanel.scale);
		panelH = (int) (panel.getHeight() * GamePanel.scale);
		panelX = GamePanel.game_width / 2 - panelW / 2;
		panelY = (int) (20 * GamePanel.scale);
	}

	public void update() {
		musicButton.update();
		sfxButton.update();
		
		unpauseButton.update();
		replayButton.update();
		menuButton.update();
		volumeButton.update();
	}

	public void draw(Graphics g) {
		g.drawImage(panel, panelX, panelY, panelW, panelH, null);
		
		musicButton.draw(g);
		sfxButton.draw(g);
		
		unpauseButton.draw(g);
		replayButton.draw(g);
		menuButton.draw(g);
		volumeButton.draw(g);
	}

	public void mouseDragged(MouseEvent e) {
		if (volumeButton.isMousePressed()) {
			volumeButton.changeX(e.getX());
		}
	}

	public void mousePressed(MouseEvent e) {
		if (isIn(e, musicButton)) {
			musicButton.setMousePressed(true);
		}
		
		else if (isIn(e, sfxButton)) {
			sfxButton.setMousePressed(true);
		}
		
		else if (isIn(e, unpauseButton)) {
			unpauseButton.setMousePressed(true);
		}
		
		else if (isIn(e, replayButton)) {
			replayButton.setMousePressed(true);
		}
		
		else if (isIn(e, menuButton)) {
			menuButton.setMousePressed(true);
		}
		
		else if (isIn(e, volumeButton)) {
			volumeButton.setMousePressed(true);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (isIn(e, musicButton)) {
			if (musicButton.isMousePressed()) {
				musicButton.setMuted(!musicButton.isMuted());
			}
		}
		
		else if (isIn(e, sfxButton)) {
			if (sfxButton.isMousePressed()) {
				sfxButton.setMuted(!sfxButton.isMuted());
			}
		}
		
		else if (isIn(e, menuButton)) {
			if (menuButton.isMousePressed()) {
				GameState.state = GameState.Menu;
				playing.unpauseGame();
			}
		}
		
		else if (isIn(e, replayButton)) {
			if (replayButton.isMousePressed()) {
				System.out.println("Replay is a work in progress ;-;");
			}
		}
		
		else if (isIn(e, unpauseButton)) {
			if (unpauseButton.isMousePressed()) {
				playing.unpauseGame();
			}
		}
		
		musicButton.resetBools();
		sfxButton.resetBools();
		
		menuButton.resetBools();
		replayButton.resetBools();
		unpauseButton.resetBools();
		volumeButton.resetBools();
		
	}

	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);
		
		menuButton.setMouseOver(false);
		replayButton.setMouseOver(false);
		unpauseButton.setMouseOver(false);
		volumeButton.setMouseOver(false);
		
		if (isIn(e, musicButton)) {
			musicButton.setMouseOver(true);
		}
		
		else if (isIn(e, sfxButton)) {
			sfxButton.setMouseOver(true);
		}
		
		else if (isIn(e, menuButton)) {
			menuButton.setMouseOver(true);
		}
		
		else if (isIn(e, replayButton)) {
			replayButton.setMouseOver(true);
		}
		
		else if (isIn(e, unpauseButton)) {
			unpauseButton.setMouseOver(true);
		}
		
		else if (isIn(e, volumeButton)) {
			volumeButton.setMouseOver(true);
		}
	}
	
	private boolean isIn(MouseEvent e, PauseButton pb) {
		return pb.getBounds().contains(e.getX(), e.getY());
	}
}
