package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Inputs.KeyboardInputs;


public class GamePanelv2 extends JPanel implements Runnable {
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	int frame;
	long lastCheck;
	Thread gameThread;

//	KeyboardInputs ki = new KeyboardInputs(this);
	
	
	public GamePanelv2() {
		this.setPreferredSize(new Dimension(1000, 800));

		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
//		this.addKeyListener(ki);
	}


	public void startThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}


	@Override
	public void run() {
		while (gameThread != null) {
			update();
			repaint();

			frame++;
			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("Fps: "+frame);
				frame = 0;
			}

			try {
				gameThread.sleep((long) 16.666);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void update() {
//		if (ki.upPressed) {
//			playerY -= playerSpeed;
//		}
//		
//		else if (ki.downPressed) {
//			playerY += playerSpeed;
//		}
//		
//		else if (ki.leftPressed) {
//			playerX -= playerSpeed;
//		}
//		
//		else if (ki.rightPressed) {
//			playerX += playerSpeed;
//		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		g.fillRect(playerX, playerY, 48, 48);
		g.dispose();
	}

}
