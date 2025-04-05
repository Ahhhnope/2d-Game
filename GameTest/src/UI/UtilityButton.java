package UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.Constants.UI.UtilButton.*;

public class UtilityButton extends PauseButton{
	private BufferedImage[] imgs;
	private int rowIndex, index;
	private boolean mouseOver, mousePressed;
	
	public UtilityButton(int x, int y, int width, int height, int rowIndex) {
		super(x, y, width, height);
		this.rowIndex = rowIndex;
		loadStuff();
	}
	
	public void loadStuff() {
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.UtilButtons);
		imgs = new BufferedImage[3];
		
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = temp.getSubimage(i * Util_Button_Default_Size, rowIndex * Util_Button_Default_Size, Util_Button_Default_Size, Util_Button_Default_Size);
		}
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
	
	public void draw(Graphics g) {
		g.drawImage(imgs[index], x, y, Util_Button_Size, Util_Button_Size, null);
	}
	
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
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
	
}
