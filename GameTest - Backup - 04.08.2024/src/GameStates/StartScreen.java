package GameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.GamePanel;
import utilz.LoadSave;

public class StartScreen extends State implements StateMethods{
	private BufferedImage background;
	private BufferedImage gameTitle;
	int titleWidth, titleHeight;
	
	public StartScreen(GamePanel gamePanel) {
		super(gamePanel);
		loadStuff();
	}
	
	public void loadStuff() {
		background = LoadSave.GetSpriteAtlas(LoadSave.StartScreenBackground);
		gameTitle = LoadSave.GetSpriteAtlas(LoadSave.GameTitle);
		
		
		titleWidth = (int) (gameTitle.getWidth() * GamePanel.scale);
		titleHeight = (int) (gameTitle.getHeight() * GamePanel.scale);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(background, 0, 0, GamePanel.game_width, GamePanel.game_height, null);
		g.drawImage(gameTitle, (int) ((GamePanel.game_width - titleWidth) / 2), 135, titleWidth, titleHeight, null);
		g.setColor(Color.white);
		g.drawString("Press Enter to start game",(int) GamePanel.game_width/ 2, (int) (GamePanel.game_height*0.6));
		g.dispose();
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
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			GameState.state = GameState.Menu;
		}
		else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			GameState.state = GameState.Quit;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
