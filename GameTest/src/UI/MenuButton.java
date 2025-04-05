package UI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameStates.GameState;
import main.GamePanel;
import utilz.LoadSave;

import static utilz.Constants.UI.Buttons.*;

public class MenuButton {
	private int xPos, yPos, rowIndex, index;
	private int xOffsetCenter = Button_Width / 2;
	private GameState state;
	private BufferedImage[] imgArr;
	private boolean mouseOver, mousePressed;
	private Rectangle ButtonBounds;
	
	public MenuButton(int xPos, int yPos, int rowIndex, GameState state) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.rowIndex = rowIndex;
		this.state = state;
		importImg();
		initBounds();
	}
	
	private void initBounds() {
		ButtonBounds = new Rectangle(xPos - xOffsetCenter, yPos, Button_Width, Button_Height);
	}
	
	private void importImg() {
		imgArr = new BufferedImage[3];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MenuButtonsSprite);
		
		for (int i = 0; i < imgArr.length; i++) {
			imgArr[i] = temp.getSubimage(i * Button_Default_Width, rowIndex * Button_Default_Height, Button_Default_Width, Button_Default_Height);
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(imgArr[index], xPos - xOffsetCenter, yPos, Button_Width, Button_Height, null);
	}
	
	public void update() {
		index = 0;
		if (mouseOver) {
			index = 1;
		}
		if (mousePressed) {
			index = 2;
		}
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	public Rectangle getBounds() {
		return ButtonBounds;
	}
	
	public void applyGameState() {
		GameState.state = state;
	}
	
	public void resetMouseBools() {
		mouseOver = false;
		mousePressed = false;
	}
}
