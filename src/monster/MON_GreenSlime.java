package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;
import object.OBJ_Slime_Staff;

public class MON_GreenSlime extends Entity {
	
	GamePanel gp;

	public MON_GreenSlime(GamePanel gp) {
		super(gp);
		
		this.gp = gp;

		type = type_monster;
		name = "Green Slime";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 4;
		life = maxLife;
		attack = 5;
		defense = 0;
		exp = 2;
		projectile = new OBJ_Rock(gp);

		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		getImage();
	}

	public void getImage() {

		up1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
	}
	
	public void update() {
		
		applyKnockback();
		super.update();
		
		int xDistance = Math.abs(worldX - gp.player.worldX);
		int yDistance = Math.abs(worldY - gp.player.worldY);
		int tileDistance = (xDistance + yDistance) / gp.tileSize;
		
		if (onPath == false && tileDistance < 5) {
			
			int i = new Random().nextInt(100)+1;
			if (i > 50)
				onPath = true;
		}
		
		if (onPath == true && tileDistance > 20) {
			onPath = false;
		}
	}

	public void setAction() {

		actionLockCounter++;

		if (onPath == true) {
			
			int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
			int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
			
			searchPath(goalCol, goalRow);
			
			// ROCK -- EXPERIMENTAL 50
			int i = new Random().nextInt(200)+1;
			if(i > 197 && projectile.alive == false && shotAvailableCounter == 50) {
				
				OBJ_Rock newProjectile = new OBJ_Rock(gp);
				newProjectile.set(worldX, worldY, direction, true, this);
				
				// CHECK VACANCY
				for (int ii = 0; ii < gp.projectile[1].length; ii++) {
					if (gp.projectile[gp.currentMap][ii] == null) {
						gp.projectile[gp.currentMap][ii] = newProjectile;
						break;
					}
				}
				
				shotAvailableCounter = 0;
			}
		}
		else {
			
			actionLockCounter++;

			if (actionLockCounter == 120) {

				Random random = new Random();
				int i = random.nextInt(100) + 1; // pick up a number from 1 to 100

				if (i <= 25)
					direction = "up";
				if (i > 25 && i <= 50)
					direction = "down";
				if (i > 50 && i <= 75)
					direction = "left";
				if (i > 75 && i <= 100)
					direction = "right";

				actionLockCounter = 0;
			}
		}
		
		if (shotAvailableCounter < 50)
			shotAvailableCounter++;
	}
	
	public void damageReaction() {
		
		actionLockCounter = 0;
		onPath = true;
	}

	public void checkDrop() {
		
		// CAST A DIE
		int i = new Random().nextInt(120)+1;
		
		// SET THE MONSTER DROP
		if(i < 50) {
			dropItem(new OBJ_Coin_Bronze(gp));
		}
		if(i >= 50 && i < 75) {
			dropItem(new OBJ_Heart(gp));
		}
		if(i >= 75 && i < 100) {
			dropItem(new OBJ_ManaCrystal(gp));
		}
		if (i >= 100 && i < 120) {
			dropItem(new OBJ_Slime_Staff(gp));
		}
	}
}
