package levels;

import main.GamePanel;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;


public class LevelManager {
	private GamePanel gamePanel;
	private BufferedImage[] dungeonSprite;
	private Level levelOne;
	private Levelv2 levelTest;
	private BufferedImage background;

	public LevelManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		//        this.levelSprite = LoadSave.GetSpriteAtlas(LoadSave.LevelOneData);
		importDungeonSprite();

		levelOne = new Level(LoadSave.GetLevelData());

		levelTest = new Levelv2(LoadSave.importLevelData());
	}

	public void importDungeonSprite() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LevelSprite);
		dungeonSprite =  new BufferedImage[(img.getHeight() / 16) * (img.getWidth() / 16)];
		for (int j = 0 ; j < 7; j++) {
			for (int i = 0 ; i <17; i++) {
				int index = j*17 + i;
				dungeonSprite[index] = img.getSubimage(i*16, j*16, 16, 16);
			}
		}

		background = LoadSave.GetSpriteAtlas(LoadSave.LevelOneBackground);
	}

	public void draw(Graphics g, int lvlOffset) {
		g.drawImage(background, 0 - lvlOffset, 0, (int) (background.getWidth()*gamePanel.tiles_size / 16), (int) (background.getHeight()*gamePanel.tiles_size / 16), null);
		
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 100; j++) {
				
				int index = levelTest.getSpriteIndex(j, i, levelTest.getMainLayer());
				g.drawImage(dungeonSprite[index-1], GamePanel.tiles_size * j - lvlOffset, i*GamePanel.tiles_size, GamePanel.tiles_size, GamePanel.tiles_size, null);
			}
		}
	}

	public void update() {

	}

	public Levelv2 getCurrentLevel() {
		return levelTest;
	}

}
