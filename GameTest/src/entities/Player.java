package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.GamePanel;
import utilz.LoadSave;
import static utilz.HelpMethods.canMoveHere;

public class Player extends Entity{

	BufferedImage[] runAni;
	BufferedImage[][] Animations;

	float playerSpeed = 1.5f * GamePanel.scale;
	int playerAction = Idle;
	private boolean moving = false, attacking = false;

	int aniTick, aniIndex, aniSpeed = 25;
	int attackSpeed = 20;
//	Lower is faster for some reason ;-;
	
	boolean movingLeft, movingRight, jumping;
	public int[][] levelData;
	
	public float xHitBoxOffset = 14 * GamePanel.scale;
	public float yHitBoxOffset = 8 * GamePanel.scale;
	
	
//	Jump and Gravity stuff
	public float airSpeed = 0f;
	public float gravity = 0.04f * GamePanel.scale;
	public float jumpSpeed = -2.25f * GamePanel.scale;
	public float fallSpeedAfterCollision = 0.05f * GamePanel.scale;
	public boolean inAir = false;
	
	
	
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitbox(x, y, 32*GamePanel.scale, 56*GamePanel.scale);
	}

	public void update() {
		updatePos();
		updateHitbox();
		updateAnimations();
		setAnimation();
	}

	public void render(Graphics g, int lvlOffset) {
		g.drawImage(Animations[playerAction][aniIndex], (int) (hitbox.x - xHitBoxOffset) - lvlOffset, (int) (hitbox.y - yHitBoxOffset), width, height, null);
//		drawHitBox(g);
	}
	
	public void loadLevelData(int[][] levelData) {
		this.levelData = levelData;
		
		if (!isEntityOnFloor(hitbox, levelData)) {
			inAir = true;
		}
	}

	public void updatePos() {
		moving = false;
		if (jumping) {
			jump();
		}

		
		if (!inAir && !movingLeft && !movingRight) {
			return;
		}
		
		
		if (!inAir) {
			if(!isEntityOnFloor(hitbox, levelData)) {
				inAir = true;
			}
		}
		
		
		float xSpeed = 0;
		
		if (movingRight) {
			xSpeed += playerSpeed;
		}
		else if (movingLeft) {
			xSpeed -= playerSpeed;
		}
		
		if (inAir) {
			if (canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			} else {
				hitbox.y = GetYPosInAir(hitbox, airSpeed);
				if (airSpeed > 0) {
					resetInAir();
				} else {
					airSpeed = fallSpeedAfterCollision;
				}
				updateXPos(xSpeed);
			}
			
		} else {
			updateXPos(xSpeed);
		}
		
		
		moving = true;
	}
	
	public void updateXPos(float xSpeed) {
		if (canMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
			hitbox.x += xSpeed;
		}else {
			hitbox.x = GetXPosNextToWall(hitbox, xSpeed);
		}
	}
	
	public float GetX() {
		return hitbox.x;
	}
	
	public void jump() {
		if (inAir) {
			return;
		}
		
		inAir = true;
		airSpeed = jumpSpeed;
	}
	
	public void resetInAir() {
		inAir = false;
		airSpeed = 0;
	}

	public void setAnimation() {
		int currAction = playerAction;

		if (moving) {
			playerAction = Run;
		}
		else {
			playerAction = Idle;
		}
		
		if (inAir) {
			if(airSpeed < 0) {
				playerAction = Jump;
			}
			else {
				playerAction = Falling;
			}
		}

		if (attacking) {
			playerAction = Attack;
		}

		if (currAction != playerAction) {
			aniTick = 0;
			aniIndex = 0;
		}
	}

	public void updateAnimations() {
		int actionSpeed = 0;
		
		if (playerAction == Attack) {
			actionSpeed = attackSpeed;
		} else {
			actionSpeed = aniSpeed;
		}
		
		aniTick++;
		if (aniTick >= actionSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetAnimationIndex(playerAction)) {
				aniIndex = 0;
				attacking = false;
			}
		}
	}

	public void loadAnimations() {
		Animations = new BufferedImage[9][8];
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.playerSprite);

		for (int n = 0; n < Animations.length; n++) {
			for (int m = 0; m < Animations[n].length; m++) {
				Animations[n][m] = img.getSubimage(m*32, n*32, 32, 32);
			}
		}


	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}
	
	public void setJump(boolean jump) {
		this.jumping = jump;
	}

	public void resetMovement() {
		movingLeft = false;
		movingRight = false;
	}
}
