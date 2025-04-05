package levels;

import java.util.List;

import utilz.LoadSave;

public class Levelv2 {
	private List<Layer> layers;
	private int mainLayer = 2;

	public Levelv2(List<Layer> layers) {
		this.layers = layers;
	}
	
	public void setMainLayer(int mainLayer) {
		this.mainLayer = mainLayer;
		
	}
	
	public List<Layer> getLayers() {
		return layers;
	}
	
	public int getMainLayer() {
		return mainLayer;
	}
	
	public Layer getLayer(int i) {
		return layers.get(i);
	}
	
	
	public int getSpriteIndex(int x, int y, int layerIndex) {
		int[][] temp = LoadSave.getLevelDataTest(layerIndex, layers);
        return temp[y][x];
    }

}
