package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.GamePanel;
import utilz.LoadSave;

public class LevelSelecter extends State implements StateMethods {
	BufferedImage background;
	BufferedImage previewLevelFrame;
	
	public LevelSelecter(GamePanel gamePanel) {
		super(gamePanel);
		loadStuff();
	}
	
	public void loadStuff() {
		background = LoadSave.GetSpriteAtlas(LoadSave.MenuBackground);
		previewLevelFrame = LoadSave.GetSpriteAtlas(LoadSave.Level1Preview);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(background, 0, 0, GamePanel.game_width, GamePanel.game_height, null);
		g.drawImage(previewLevelFrame, GamePanel.game_width / 3, 200, (int) (previewLevelFrame.getWidth() * GamePanel.scale), (int) (previewLevelFrame.getHeight() * GamePanel.scale), null);
	}
	
	
	
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
