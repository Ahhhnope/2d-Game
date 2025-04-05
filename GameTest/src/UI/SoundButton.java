package UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;

import static utilz.Constants.UI.PauseButton.*;

public class SoundButton extends PauseButton{
	private BufferedImage[][] buttonImgs;
	private boolean mouseOver, mousePressed;
	private boolean muted;
	private int rowIndex, colIndex;
	
	public SoundButton(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		loadStuff();
	}
	
	public void loadStuff() {
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SoundButtons);
		buttonImgs = new BufferedImage[2][3];
		
		for (int j = 0; j < buttonImgs.length; j++) {
			for (int i = 0; i < buttonImgs[j].length; i++) {
				buttonImgs[j][i] = temp.getSubimage(i * Sound_Button_Default_Size, j * Sound_Button_Default_Size, Sound_Button_Default_Size, Sound_Button_Default_Size);
			}
		}
	}
	
	public void update() {
		if (muted) {
			rowIndex = 1;
		} else {
			rowIndex = 0;
		}
		
		colIndex = 0;
		if (mouseOver) {
			colIndex = 1;
		}
		if (mousePressed) {
			colIndex = 2;
		}
		
	}
	
	public void draw(Graphics g) {
		g.drawImage(buttonImgs[rowIndex][colIndex], x, y, width, height, null);
	}
	
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mosueOver) {
		this.mouseOver = mosueOver;
	}

	public boolean isMuted() {
		return muted;
	}

	public void setMuted(boolean muted) {
		this.muted = muted;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getColIndex() {
		return colIndex;
	}

	public void setColIndex(int colIndex) {
		this.colIndex = colIndex;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	
}
