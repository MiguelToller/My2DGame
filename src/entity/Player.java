package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Shield_Wood;
import object.OBJ_Iron_Sword;

public class Player extends Entity {

	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public boolean attackCanceled = false;
	public boolean lightUpdated = false;

	public Player(GamePanel gp, KeyHandler keyH) {

		super(gp);
		this.keyH = keyH;

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		// SOLID AREA
		solidArea = new Rectangle();
		solidArea.x = 12;
		solidArea.y = 24;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 24;
		solidArea.height = 20;
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		setItems();
	}

	public void setDefaultValues() {

		worldX = gp.tileSize * 23; // player position
		worldY = gp.tileSize * 21;
//		worldX = gp.tileSize * 12; // house
//		worldY = gp.tileSize * 12;
//		gp.currentMap = 1;
		defaultSpeed = 4;
		speed = defaultSpeed;
		direction = "right";

		// PLAYER STATUS
		level = 1;
		maxLife = 6;
		life = maxLife;
		maxMana = 4;
		mana = maxMana;
		ammo = 10;
		strength = 1; // more strength, more damage he gives
		dexterity = 1; // more dexterity, less damage he receives
		exp = 0;
		nextLevelExp = 5;
		coin = 500;
		currentWeapon = new OBJ_Iron_Sword(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		attack = getAttack();
		defense = getDefense();
		
		if (currentWeapon instanceof OBJ_Staff) {
		    projectile = ((OBJ_Staff) currentWeapon).createProjectile();
		}
	}
	
	public void setDefaultPositions() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		direction = "down";
	}
	
	public void restoreLifeAndMana() {
		
		life = maxLife;
		mana = maxMana;
		invincible = false;
	}
	
	public void setItems() {
		
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
	}
	
	public int getAttack() {
		
		attackArea = currentWeapon.attackArea;
		return attack = strength * currentWeapon.attackValue;
	}
	
	public int getDefense() {
		return defense = dexterity * currentShield.defenseValue;
	}
	
	public void getPlayerImage() {

		up1 = setup("/player/player_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/player/player_up_2", gp.tileSize, gp.tileSize);
		up3 = setup("/player/player_up_3", gp.tileSize, gp.tileSize);
		down1 = setup("/player/player_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/player/player_down_2", gp.tileSize, gp.tileSize);
		down3 = setup("/player/player_down_3", gp.tileSize, gp.tileSize);
		left1 = setup("/player/player_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/player/player_left_2", gp.tileSize, gp.tileSize);
		left3 = setup("/player/player_left_3", gp.tileSize, gp.tileSize);
		right1 = setup("/player/player_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/player/player_right_2", gp.tileSize, gp.tileSize);
		right3 = setup("/player/player_right_3", gp.tileSize, gp.tileSize);
	}

	public void getPlayerAttackImage() {
		
		if(currentWeapon.type == type_sword) {
			attackUp1 = setup("/player/pAttack_up_1", gp.tileSize, gp.tileSize * 2);
			attackUp2 = setup("/player/pAttack_up_2", gp.tileSize, gp.tileSize * 2);
			attackDown1 = setup("/player/pAttack_down_1", gp.tileSize, gp.tileSize * 2);
			attackDown2 = setup("/player/pAttack_down_2", gp.tileSize, gp.tileSize * 2);
			attackLeft1 = setup("/player/pAttack_left_1", gp.tileSize * 2, gp.tileSize);
			attackLeft2 = setup("/player/pAttack_left_2", gp.tileSize * 2, gp.tileSize);
			attackRight1 = setup("/player/pAttack_right_1", gp.tileSize * 2, gp.tileSize);
			attackRight2 = setup("/player/pAttack_right_2", gp.tileSize * 2, gp.tileSize);
		}
		
		if(currentWeapon.type == type_axe) {
			attackUp1 = setup("/player/pAxe_up_1", gp.tileSize, gp.tileSize * 2);
			attackUp2 = setup("/player/pAxe_up_2", gp.tileSize, gp.tileSize * 2);
			attackDown1 = setup("/player/pAxe_down_1", gp.tileSize, gp.tileSize * 2);
			attackDown2 = setup("/player/pAxe_down_2", gp.tileSize, gp.tileSize * 2);
			attackLeft1 = setup("/player/pAxe_left_1", gp.tileSize * 2, gp.tileSize);
			attackLeft2 = setup("/player/pAxe_left_2", gp.tileSize * 2, gp.tileSize);
			attackRight1 = setup("/player/pAxe_right_1", gp.tileSize * 2, gp.tileSize);
			attackRight2 = setup("/player/pAxe_right_2", gp.tileSize * 2, gp.tileSize);
		}
		
		if (currentWeapon instanceof OBJ_Staff) {
	        OBJ_Staff staff = (OBJ_Staff) currentWeapon;
	        attackUp1 = staff.attackUp1;
	        attackUp2 = staff.attackUp2;
	        attackDown1 = staff.attackDown1;
	        attackDown2 = staff.attackDown2;
	        attackLeft1 = staff.attackLeft1;
	        attackLeft2 = staff.attackLeft2;
	        attackRight1 = staff.attackRight1;
	        attackRight2 = staff.attackRight2;
	    }
	}

	public void update() {
		
		if (attacking == true) {
			attacking();
		}
			
		else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true
				|| keyH.enterPressed == true) {

			if (keyH.upPressed == true) {
				direction = "up";
			} else if (keyH.downPressed == true) {
				direction = "down";
			} else if (keyH.leftPressed == true) {
				direction = "left";
			} else if (keyH.rightPressed == true) {
				direction = "right";
			}

			// CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);

			// CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);

			// CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);

			// CHECK MONSTER COLLISION
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			// CHECK INTERACTIVE TILES COLLISION
			gp.cChecker.checkEntity(this, gp.iTile);

			// CHECK EVENT
			gp.eHandler.checkEvent();

			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if (collisionOn == false && keyH.enterPressed == false) {

				switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			if (keyH.enterPressed == true && attackCanceled == false) {
				attacking = true;
				spriteCounter = 0;
			}

			attackCanceled = false;
			gp.keyH.enterPressed = false;

			spriteCounter++;
			if (spriteCounter > 10) {
				if (spriteNum == 1)
					spriteNum = 2;
				else if (spriteNum == 2)
					spriteNum = 3;
				else if (spriteNum == 3)
					spriteNum = 1;
				spriteCounter = 0;
			}
		}
		
		// This needs to be outside of key if statement!
		if (invincible == true) {
			invincibleCounter++;
			if (invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		
		if(life > maxLife) 
			life = maxLife;
		
		if(mana > maxMana) 
			mana = maxMana;
		
		if (life <= 0) {
			gp.gameState = gp.gameOverState;
			gp.ui.commandNum = -1;
			gp.stopMusic();
			gp.playSE(11);
		}
	}
	
	public void attacking() {
		
		spriteCounter++;
		
		if (spriteCounter <= 10) {
			spriteNum = 1;
		}
		if (spriteCounter > 10 && spriteCounter <= 40) {
			spriteNum = 2;
			
			if (attacking && currentWeapon instanceof OBJ_Staff) {
			    OBJ_Staff staff = (OBJ_Staff) currentWeapon;
			    
			    if (staff.getProjectile().haveResource(this)) {
			    	Projectile newProjectile = staff.createProjectile();
			    	
			        newProjectile.set(this.worldX, this.worldY, this.direction, true, this);
			        staff.use(this);
			        
			        // CHECK VACANCY
			        for(int i = 0; i < gp.projectile[1].length; i++) {
			        	if (gp.projectile[gp.currentMap][i] == null) {
			        		gp.projectile[gp.currentMap][i] = projectile;
			        		break;
			        	}
			        }
			    }
			} else {
				
				// Save the current worldX, worldY, solidArea
				int currentWorldX = worldX;
				int currentWorldY = worldY;
				int solidAreaWidth = solidArea.width;
				int solidAreaHeight = solidArea.height;
				
				// Adjust player's worldX/Y for the attackArea
				switch (direction) {
				case "up": worldY -= attackArea.height; break;
				case "down": worldY += attackArea.height; break;
				case "left": worldX -= attackArea.width; break;
				case "right": worldX += attackArea.width; break;
				}
				
				// attackArea becomes solidArea
				solidArea.width = attackArea.width;
				solidArea.height = attackArea.height;
				
				// Check monster collision with the updated worldX, worldY and solidArea
				int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
				damageMonster(monsterIndex, attack, currentWeapon.knockBackPower);
				
				int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
				damageInteractiveTile(iTileIndex);
				
				int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
				damageProjectile(projectileIndex);
				
				// After checking collision, restore the original data
				worldX = currentWorldX;
				worldY = currentWorldY;
				solidArea.width = solidAreaWidth;
				solidArea.height = solidAreaHeight;
				}
			}
		
		if (spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}

	public void pickUpObject(int i) {

		if (i != 999) {
			
			// PICKUP ONLY ITEMS
			if(gp.obj[gp.currentMap][i].type == type_pickupOnly) {
				
				gp.obj[gp.currentMap][i].use(this);
				gp.obj[gp.currentMap][i] = null;
			}
			
			// OBSTACLE
			else if (gp.obj[gp.currentMap][i].type == type_obstacle) {
				if (keyH.enterPressed == true) {
					attackCanceled = true;
					gp.obj[gp.currentMap][i].interact();
				}
			}
			
			// INVENTORY ITEMS
			else {
				String text;
				
				if(canObtainItem(gp.obj[gp.currentMap][i]) == true) {
					gp.playSE(1);
					text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
				}
				else {
					text = "You cannot carry any more!";
				}
				gp.ui.addMessage(text);
				gp.obj[gp.currentMap][i] = null;
			}
		}
	}

	public void interactNPC(int i) {

		if (gp.keyH.enterPressed == true) {

			if (i != 999) {
				attackCanceled = true;
				gp.gameState = gp.dialogueState;
				gp.npc[gp.currentMap][i].speak();
			}
		}
	}

	public void contactMonster(int i) {

		if (i != 999) {
			if (invincible == false && gp.monster[gp.currentMap][i].dying == false) {
				gp.playSE(6);
				
				int damage = gp.monster[gp.currentMap][i].attack - defense;
				if (damage < 0) {
					damage = 0;
				}
				
				life -= damage;
				invincible = true;
			}
		}
	}
	
	public void damageMonster(int i, int attack, int knockBackPower) {
		
		if (i != 999) {
			
			if (gp.monster[gp.currentMap][i].invincible == false) {
				
				gp.playSE(5);
				
				if (knockBackPower > 0) {
					knockBack(gp.monster[gp.currentMap][i], knockBackPower);
				}
				
				int damage = attack - gp.monster[gp.currentMap][i].defense;
				if (damage < 0) {
					damage = 0;
				}
				
				gp.monster[gp.currentMap][i].life -= damage;
				gp.ui.addMessage(damage + " damage!");
				
				gp.monster[gp.currentMap][i].invincible = true;
				gp.monster[gp.currentMap][i].damageReaction();
				
				if (gp.monster[gp.currentMap][i].life <= 0) {
					gp.monster[gp.currentMap][i].dying = true;
					gp.ui.addMessage("killed the " + gp.monster[gp.currentMap][i].name + "!");
					gp.ui.addMessage("Exp + " + gp.monster[gp.currentMap][i].exp);
					exp += gp.monster[gp.currentMap][i].exp;
					checkLevelUp();
				}
			}
		}
	}
	
	public void knockBack(Entity entity, int knockBackPower) {
		
		entity.direction = direction;
		entity.speed += knockBackPower;
		entity.knockBack = true;
	}
	
	public void damageInteractiveTile(int i) {
		
		if (i != 999 && gp.iTile[gp.currentMap][i].destructible == true 
				&& gp.iTile[gp.currentMap][i].isCorrectItem(this) == true && gp.iTile[gp.currentMap][i].invincible == false) {
			gp.iTile[gp.currentMap][i].playSE();
			gp.iTile[gp.currentMap][i].life--;
			gp.iTile[gp.currentMap][i].invincible = true;
			
			// Generate particle
			generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
			
			if(gp.iTile[gp.currentMap][i].life == 0)
				gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
		}
	}
	
	public void damageProjectile(int i) {
		
		if (i != 999) {
			Entity projectile = gp.projectile[gp.currentMap][i];
			projectile.alive = false;
			generateParticle(projectile, projectile);
		}
	}

	public void checkLevelUp() {
		
		if(exp >= nextLevelExp) {
			
			level++;
			nextLevelExp = nextLevelExp * 2;
			maxLife += 2;
			strength++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();
			
			gp.playSE(7);
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "You are level " + level + " now!\n"
					+ "You feel stronger!";
		}
	}
	
	public void selectItem() {
		
		int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
		
		if(itemIndex < inventory.size()) {
			
			Entity selectedItem = inventory.get(itemIndex);
			
			if(selectedItem.type == type_sword || selectedItem.type == type_axe) {
				
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			if(selectedItem.type == type_shield) {
				
				currentShield = selectedItem;
				defense = getDefense();
			}
			if(selectedItem.type == type_consumable) {
				if (selectedItem.use(this) == true) {
					if (selectedItem.amount > 1)
						selectedItem.amount--;
					else 
						inventory.remove(itemIndex);
				}
			}
			if(selectedItem.type == type_staff) {
				currentWeapon = selectedItem;
				getPlayerAttackImage();
			}
			if(selectedItem.type == type_light) {
				if(currentLight == selectedItem) {
					currentLight = null;
				}
				else {
					currentLight = selectedItem;
				}
				lightUpdated = true;
			}
		}
	}
	
	public int searchItemInInventory(String itemName) {
		
		int itemIndex = 999;
		
		for(int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).name.equals(itemName)) {
				itemIndex = i;
				break;
			}
		}
		return itemIndex;
	}
	
	public boolean canObtainItem(Entity item) {
		
		boolean canObtain = false;
		
		// CHECK IF STACKABLE
		if (item.stackable == true) {
			
			int index = searchItemInInventory(item.name);
			
			if (index != 999) {
				inventory.get(index).amount++;
				canObtain = true;
			}
			else { // New item so need to check vacancy
				if (inventory.size() != maxInventorySize) {
					inventory.add(item);
					canObtain = true;
				}
			}
		}
		else { // Not stackable so check vacancy
			if (inventory.size() != maxInventorySize) {
				inventory.add(item);
				canObtain = true;
			}
		}
		return canObtain;
	}
	
	public void draw(Graphics2D g2) {

		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;

		switch (direction) {
		case "up":
			if (attacking == false) {
				if (spriteNum == 1) image = up1;
				if (spriteNum == 2) image = up2;
				if (spriteNum == 3) image = up3;
			}
			if (attacking == true) {
				tempScreenY = screenY - gp.tileSize;
				if (spriteNum == 1) image = attackUp1;
				if (spriteNum == 2) image = attackUp2;
			}
			break;
		case "down":
			if (attacking == false) {
				if (spriteNum == 1) image = down1;
				if (spriteNum == 2) image = down2;
				if (spriteNum == 3) image = down3;
			}
			if (attacking == true) {
				if (spriteNum == 1) image = attackDown1;
				if (spriteNum == 2) image = attackDown2;
			}
			break;
		case "left":
			if (attacking == false) {
				if (spriteNum == 1) image = left1;
				if (spriteNum == 2) image = left2;
				if (spriteNum == 3) image = left3;
			}
			if (attacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if (spriteNum == 1) image = attackLeft1;
				if (spriteNum == 2) image = attackLeft2;
			}
			break;
		case "right":
			if (attacking == false) {
				if (spriteNum == 1) image = right1;
				if (spriteNum == 2) image = right2;
				if (spriteNum == 3) image = right3;
			}
			if (attacking == true) {
				if (spriteNum == 1) image = attackRight1;
				if (spriteNum == 2) image = attackRight2;
			}
			break;
		}

		if (invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		
		g2.drawImage(image, tempScreenX, tempScreenY, null);	

		// Reset alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

		// DEBUG
//		g2.setFont(new Font("Arial", Font.PLAIN, 26));
//		g2.setColor(Color.white);
//		g2.drawString("Invincible:"+invincibleCounter, 10, 400);
	}

}
