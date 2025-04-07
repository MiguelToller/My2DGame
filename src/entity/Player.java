package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

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
	}

	public void setDefaultValues() {

		worldX = gp.tileSize * 23; // player position
		worldY = gp.tileSize * 21;
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
		currentLight = null;
		attack = getAttack();
		defense = getDefense();
		
		getImage();
		getAttackImage();
		getGuardImage();
		setItems();
		setDialogue();
	}
	
	public void setDefaultPositions() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		direction = "down";
	}
	
	public void setDialogue() {
		
		dialogues[0][0] = "You are level " + level + " now!\n"
				+ "You feel stronger!";
	}
	
	public void restoreStatus() {
		
		life = maxLife;
		mana = maxMana;
		speed = defaultSpeed;
		invincible = false;
		transparent = false;
		attacking = false;
		guarding = false;
		knockBack = false;
		lightUpdated = true;
	}
	
	public void setItems() {
		
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
	}
	
	public int getAttack() {
		
		attackArea = currentWeapon.attackArea;
		motion1_duration = currentWeapon.motion1_duration;
		motion2_duration = currentWeapon.motion2_duration;
		return attack = strength * currentWeapon.attackValue;
	}
	
	public int getDefense() {
		return defense = dexterity * currentShield.defenseValue;
	}
	
	public int getCurrentWeaponSlot() {
		int currentWeaponSlot = 0;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == currentWeapon) {
				currentWeaponSlot = i;
			}
		}
		return currentWeaponSlot;
	}
	
	public int getCurrentShieldSlot() {
		int currentShieldSlot = 0;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == currentShield) {
				currentShieldSlot = i;
			}
		}
		return currentShieldSlot;
	}
	
	public void getImage() {

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
	
	public void getSleepImage(BufferedImage image) {
		up1 = image;
		up2 = image;
		up3 = image;
		down1 = image;
		down2 = image;
		down3 = image;
		left1 = image;
		left2 = image;
		left3 = image;
		right1 = image;
		right2 = image;
		right3 = image;
	}

	public void getAttackImage() {
		
		if (currentWeapon instanceof OBJ_Sword) {
	        OBJ_Sword sword = (OBJ_Sword) currentWeapon;
	        attackUp1 = sword.attackUp1;
	        attackUp2 = sword.attackUp2;
	        attackDown1 = sword.attackDown1;
	        attackDown2 = sword.attackDown2;
	        attackLeft1 = sword.attackLeft1;
	        attackLeft2 = sword.attackLeft2;
	        attackRight1 = sword.attackRight1;
	        attackRight2 = sword.attackRight2;
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
	
	public void getGuardImage() {
		
		guardUp = setup("/player/player_guard_up", gp.tileSize, gp.tileSize);
		guardDown = setup("/player/player_guard_down", gp.tileSize, gp.tileSize);
		guardLeft = setup("/player/player_guard_left", gp.tileSize, gp.tileSize);
		guardRight = setup("/player/player_guard_right", gp.tileSize, gp.tileSize);
	}

	public void update() {
		
	    if(knockBack == true) {
	    	
			collisionOn = false;
			gp.cChecker.checkTile(this);
			gp.cChecker.checkObject(this, true);
			gp.cChecker.checkEntity(this, gp.npc);
			gp.cChecker.checkEntity(this, gp.monster);
			gp.cChecker.checkEntity(this, gp.iTile);
	    	
	    	if(collisionOn == true) {
	    		knockBackCounter = 0;
	    		knockBack = false;
	    		speed = defaultSpeed;
	    	}
	    	
	    	else if(collisionOn == false) {
	            switch (knockBackDirection) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
	            }
	    	}
	    	
	    	knockBackCounter++;
	    	if(knockBackCounter == 10) {
	    		knockBackCounter = 0;
	    		knockBack = false;
	    		speed = defaultSpeed;
	    	}
	    }
		
	    else if (attacking == true) {
			attacking();
		}
		
		else if(keyH.guardPressed == true) {
			guarding = true;
			guardCounter++;
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
			guarding = false;
			guardCounter = 0;

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
			guarding = false;
			guardCounter = 0;
		}
		
		// This needs to be outside of key if statement!
		if (invincible == true) {
			invincibleCounter++;
			if (invincibleCounter > 60) {
				invincible = false;
				transparent = false;
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

		if (i != 999) {
			
			if (gp.keyH.enterPressed == true) {
				attackCanceled = true;
				gp.npc[gp.currentMap][i].speak();
			}
		}
	}

	public void contactMonster(int i) {

		if (i != 999) {
			if (invincible == false && gp.monster[gp.currentMap][i].dying == false) {
				gp.playSE(6);
				
				int damage = gp.monster[gp.currentMap][i].attack - defense;
				if (damage < 1) {
					damage = 1;
				}
				
				life -= damage;
				invincible = true;
				transparent = true;
			}
		}
	}
	
	public void damageMonster(int i, Entity attacker, int attack, int knockBackPower) {
		
		if (i != 999) {
			
			if (gp.monster[gp.currentMap][i].invincible == false) {
				
				gp.playSE(5);
				
				if (knockBackPower > 0) {
					setKnockBack(gp.monster[gp.currentMap][i], attacker, knockBackPower);
				}
				
				if(gp.monster[gp.currentMap][i].offBalance == true) {
					attack *= 5;
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
			startDialogue(this, 0);
		}
	}
	
	public void selectItem() {
		
		int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
		
		if(itemIndex < inventory.size()) {
			
			Entity selectedItem = inventory.get(itemIndex);
			
			if(selectedItem.type == type_sword || selectedItem.type == type_axe) {
				
				currentWeapon = selectedItem;
				attack = getAttack();
				getAttackImage();
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
				getAttackImage();
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
			if(guarding == true) 
				image = guardUp;
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
			if(guarding == true) 
				image = guardDown;
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
			if(guarding == true) 
				image = guardLeft;
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
			if(guarding == true)
				image = guardRight;
			break;
		}

		if (transparent == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
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
