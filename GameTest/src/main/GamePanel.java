package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import utilz.Constants.*;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

//import Inputs.KeyboardInputs;

public class GamePanel extends JPanel implements Runnable{
	int screenX = 1280;
	int screenY = 800;

	int frame = 0;
	int fps = 120;
	long lastCheck;


	//	Player shit
	float playerX = 100;
	float playerY = 100;
	float xDir = 10, yDir = 10;
	int playerSpeed = 10;
	int playerAction = Idle;
	int playerDir = -1;
	boolean moving = false;


	Thread gameThread;
	KeyboardInputs ki = new KeyboardInputs(this);
	MouseInputs mi = new MouseInputs(this);

	//	Animations variables shit
	BufferedImage[] runAni;
	ArrayList<BufferedImage> Animations;
	int aniTick, aniIndex, aniSpeed = 10;


	public GamePanel() {
		this.setPreferredSize(new Dimension(screenX, screenY));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.addKeyListener(ki);
		//		this.addMouseMotionListener(mi);
		importImg();
		loadAnimations();
	}

	public void startThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}


	@Override
	public void run() {
		double timePerFrame = 1000000000 / fps;
		long lastFrame = System.nanoTime();
		long now;

		while (gameThread != null) {
			now = System.nanoTime();

			if (now - lastFrame >= timePerFrame) {
				updateAnimations();
				repaint();
				lastFrame = now;
				frame++;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("Fps: "+frame);
				frame = 0;
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawRect((int) playerX, (int) playerY, 240, 160);
		g.setColor(Color.white);

		
		setAnimation();
		updatePos();
		g.drawImage(runAni[aniIndex], (int) playerX, (int) playerY, 240, 160, null);
		g.dispose();
	}

	public void setDirection(int dir) {
		playerDir = dir;
		moving = true;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public void updatePos() {
		if (moving) {
			switch (playerDir) {
				case Up:
					playerY -= playerSpeed;
					break;
				case Down:
					playerY += playerSpeed;
					break;
				case Left:
					playerX -= playerSpeed;
					break;
				case Right:
					playerX += playerSpeed;
					break;
			}
		}
	}

	public void setAnimation() {
		if (moving) {
			playerAction = Run;
		} else {
			playerAction = Idle;
		}
	}

	public void setRectPose(int x, int y) {
		playerX = x;
		playerY = y;
	}

	public void importImg() {

		BufferedImage img = null;		

		String[] actions = {
				"Idle", "Run", "Jump", "Attack"
		};
		Animations = new ArrayList<BufferedImage>();
		for (int i = 0; i < actions.length; i++) {

			String path = "/_"+actions[i]+".png";
			InputStream is = getClass().getResourceAsStream(path);

			try {
				img = ImageIO.read(is);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			Animations.add(img);
		}		
	}

	public void loadAnimations() {
		runAni = new BufferedImage[10];
		for (int i = 0; i < GetAnimationIndex(playerAction); i++) {
			runAni[i] = Animations.get(playerAction).getSubimage(i*120, 0, 120, 80);
		}
	}

	public void attack(int keyCode) {
		if (keyCode == 90) {
			playerAction = Attack;
			aniTick = 0;
		}
		loadAnimations();
	}

	public void updateAnimations() {
		loadAnimations();
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetAnimationIndex(playerAction)) {
				aniIndex = 0;
			}
		}
	}
}
