package utilz;


import java.awt.geom.Rectangle2D;

import main.GamePanel;

public class HelpMethods {
	public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData) {
		
		if (!isSolid(x, y, lvlData) &&
			!isSolid(x+width, y+height/2, lvlData) &&
			!isSolid(x, y+height/2, lvlData) &&
			!isSolid(x+width, y+height, lvlData) &&
			!isSolid(x+width, y, lvlData) &&
			!isSolid(x, y+height, lvlData)) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isSolid(float x, float y, int[][] lvlData) {
		int maxWidth = lvlData[0].length * GamePanel.tiles_size;
		if (x < 0 || x >= maxWidth) {
			return true;
		}
		if (y < 0 || y >= GamePanel.game_height ) {
			return true;
		}
		
		float xIndex = x / GamePanel.tiles_size;
		float yIndex = y / GamePanel.tiles_size;
		
		int value = lvlData[(int) yIndex][(int) xIndex];
		
//		System.out.println(value);
		
		int[] nonTile = {52, 53, 54, 72, 73, 82, 83, 84, 98, 116, 117, 118, 119};
		if (value >= 364 || value < 0) {
			return true;
		}
		
		boolean temp = false;
		for (int i : nonTile) {
			if (value == i) {
				temp = true;
			}
		}
		
		if (temp) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public static float GetXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
		int currTile = (int) (hitbox.x / GamePanel.tiles_size);
		int tileXPos = currTile * GamePanel.tiles_size;
		if (xSpeed > 0) {
			int xOffset = (int) (GamePanel.tiles_size - hitbox.width);
			return tileXPos + GamePanel.tiles_size - 1;
		} else {
			return tileXPos;
		}
	}
	
	public static float GetYPosInAir(Rectangle2D.Float hitbox, float ySpeed) {
		int currTile = (int) (hitbox.y / GamePanel.tiles_size);
		int tileYPos = currTile * GamePanel.tiles_size;
		if (ySpeed > 0) { 
			return tileYPos + (8 * GamePanel.scale) - 1;
		} else {
			return tileYPos;
		}	
	}
	
	public static boolean isEntityOnFloor(Rectangle2D.Float hitbox, int[][] levelData) {
		if (!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, levelData)) {
			if (!isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, levelData)) {
				return false;
			}
		}
		
		
		return true;
	}
}
