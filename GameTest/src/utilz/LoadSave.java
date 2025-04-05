package utilz;

import main.GamePanel;

import static utilz.Constants.PlayerConstants.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import levels.Layer;
import levels.Levelv2;

public class LoadSave {
//	Player stuff
	public static final int[] playerActionsIndex = {Idle, Run, Attack, Jump, Falling};
	public static final String playerSprite = "PlayerStuff/Player_Sprite.png";
	
//	Start screen stuff
	public static final String StartScreenBackground = "MenuStuff/test.png";
	public static final String GameTitle = "MenuStuff/Game-tit.png";
	
//	Menu stuff
	public static final String MenuBackground = "MenuStuff/background.jpg";
	public static final String MenuButtonsSprite = "MenuStuff/buttons.png";
	public static final String MenuPanel = "MenuStuff/panel.png";
	
//	Level selection stuff
	public static final String Level1Preview = "LevelPreview/lvl1.png";
	public static final String Level2Preview = "LevelPreview/lvl2.png";
	public static final String Level3Preview = "LevelPreview/lvl3.png";
	
//	Pause menu stuff
	public static final String PausedMenuPanel = "MenuStuff/pause_menu_panel.png";
	public static final String SoundButtons = "MenuStuff/sound_buttons.png";
	public static final String UtilButtons = "MenuStuff/utility_buttons.png";
	public static final String SoundSlider = "MenuStuff/volume_buttons.png";
	
//	Level stuff
	public static final String Level1TileSet = "Pixel_Woods_Tileset.png";
	public static final String Level2TileSet = "Dungeon_Tile_Sprite.png";
	public static final String LevelOneData = "level_one_data.png";
	public static final String LevelOneTest = "level_one_data.png";
	public static final String LevelOneBackground = "LongMapTestBackground.png";
	
	
	
	public static final String test = "yeh.png";

	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		String path = "/"+fileName;
		InputStream is = LoadSave.class.getResourceAsStream(path);

		try {
			img = ImageIO.read(is);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				is.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}


	@SuppressWarnings("deprecation")
	public static int[][] GetLevelData() {
		FileReader fr = null;
		JsonParser parser = null;
		JsonArray tilemap = null;

		try {
			parser = new JsonParser();
			fr = new FileReader("res/lvl1.json");

			JsonObject jsonObject = parser.parse(fr).getAsJsonObject();
			JsonArray layers = jsonObject.getAsJsonArray("layers");
			JsonObject firsLayer = layers.get(0).getAsJsonObject();
			JsonArray data = firsLayer.getAsJsonArray("data");


			tilemap = data;

			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		int[][] lvlData = new int[GamePanel.tiles_in_height][GamePanel.tiles_in_width];
		int index = 0;
		for(int j = 0 ; j < GamePanel.tiles_in_height; j++) {
			for (int i = 0; i < GamePanel.tiles_in_width; i++) {
				if (tilemap.get(index).getAsInt() == 0) {
					lvlData[j][i] = 52;
				} else {
					lvlData[j][i] = tilemap.get(index).getAsInt();
				}
				//				System.out.print(lvlData[j][i]+" ");
				index++;
			}
			//			System.out.println();
		}
		return lvlData;
	}
	
	
	

	public static List<Layer> importLevelData() {
		List<Layer> layers = null;
		Gson gson = new Gson();
		FileReader fr;
		try {
			fr = new FileReader(new File("res/LongMapTest.json"));
			Levelv2 lvl = gson.fromJson(fr, Levelv2.class);
			fr.close();
			layers = lvl.getLayers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return layers;
	}

	public static int[][] getLevelDataTest(int index, List<Layer> layers) {
		int[][] layerData;
		Layer selectedLayer = layers.get(index);
		layerData = new int[selectedLayer.getHeight()][selectedLayer.getWidth()];

		int count = 0;
		for (int i = 0; i < selectedLayer.getHeight(); i++) {
			for (int j = 0; j < selectedLayer.getWidth(); j++) {
				if (selectedLayer.getLayerData()[count] == 0) {
					layerData[i][j] = 52;	
				} else {
					layerData[i][j] = selectedLayer.getLayerData()[count];

				}
				count++;
			}
		}
		return layerData;
	}
}
