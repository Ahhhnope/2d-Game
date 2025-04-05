package entities;

public abstract class Enemy extends Entity{
	private int enemyState, enemyType;
	private int aniIndex, aniTick, aniSpeed = 10;
	
	
	public Enemy(int x, int y, int width, int height, int enemyType) {
		super(x, y, width, height);
		this.enemyType = enemyType;
		initHitbox(x, y, width, height);
	}
	
	public void updateAnimationTick() {
		aniTick++;

		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= 9) {
				aniIndex = 0;
			}
		}
	}
	
	public void update() {
		updateAnimationTick();
	}
	
	public int getAniIndex() {
		return aniIndex;
	}
	
	public int getEnemyState() {
		return enemyState;
	}
}
