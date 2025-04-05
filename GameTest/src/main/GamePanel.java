package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import GameStates.GameState;
import GameStates.LevelSelecter;
import GameStates.Menu;
import GameStates.Playing;
import GameStates.StartScreen;
import Inputs.KeyboardInputs;
import Inputs.MouseInputs;


public class GamePanel extends JPanel implements Runnable{
	
	private Playing playing;
	private Menu menu;
	private StartScreen startScreen;
	private LevelSelecter levelSelecter;
	
	
	public final static int tiles_default_size = 32;
	public final static float scale = 1.5f;
	public final static int tiles_in_width = 26;
	public final static int tiles_in_height = 14;
	public final static int tiles_size = (int) (tiles_default_size * scale);
	public final static int game_width = tiles_size * tiles_in_width;
	public final static int game_height = tiles_size * tiles_in_height;

	int setFPS = 120;
	int setUPS = 200;
	long lastCheck;
	
	Thread gameThread;
	KeyboardInputs ki = new KeyboardInputs(this);
	MouseInputs mi = new MouseInputs(this);

	public GamePanel() {
		this.setPreferredSize(new Dimension(game_width, game_height));
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.addKeyListener(ki);
		this.addMouseListener(mi);
		this.addMouseMotionListener(mi);
		
		initClasses();
	}

	public void initClasses() {
		startScreen = new StartScreen(this);
		levelSelecter = new LevelSelecter(this);
		menu = new Menu(this);
		playing = new Playing(this);
	}
	
	public void update() {
		switch (GameState.state) {
			case StartScreen:
				startScreen.update();
				break;
				
			case LevelSelecter:
				levelSelecter.update();
				break;
				
			case Menu:
				menu.update();
				break;
				
			case Playing:
				playing.update();
				break;
				
			case Option:
			case Quit:
			default:
				System.exit(0);
				break;
		}
	}
	
	public void render(Graphics g) {
		switch (GameState.state) {
			case StartScreen:
				startScreen.draw(g);
				break;
				
			case LevelSelecter:
				levelSelecter.draw(g);
				break;
				
			case Menu:
				menu.draw(g);
				break;
				
			case Playing:
				playing.draw(g);
				break;
			}
		g.dispose();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		render(g);
	}

	public void startThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}


	@Override
	public void run() {
		double timePerFrame = 1000000000 / setFPS;
		double timePerUpdate = 1000000000 / setUPS;
		
		long previousUpdate = System.nanoTime(); 

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();
		
		double updateTime = 0;
		double frameTime = 0;
		
		
		while (true) {
			long currentTime = System.nanoTime();
			updateTime += (currentTime - previousUpdate) / timePerUpdate;
			frameTime += (currentTime - previousUpdate) / timePerFrame;
			
			previousUpdate = currentTime;
			
			if (updateTime >= 1) {
				update();
				updates++;
				updateTime--;
			}
			
			if (frameTime >= 1) {
				repaint();
				frames++;
				frameTime--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("Fps: "+frames+" | Updates: "+updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	public void windowFocusLost() {
		if (GameState.state == GameState.Playing) {
			playing.getPlayer().resetMovement();
		}
	}
	
	public StartScreen getStartScreen() {
		return startScreen;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public LevelSelecter getLevelSelecter() {
		return levelSelecter;
	}
	
	public Playing getPlaying() {
		return playing;
	}

}
