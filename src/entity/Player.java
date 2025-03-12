package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Shield_Wood;
import object.OBJ_Slime_Staff;
import object.OBJ_Fire_Staff;
import object.OBJ_Iron_Sword;
import object.OBJ_Rock;

public class Player extends Entity {

	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public boolean attackCanceled = false;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;

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
		speed = 4;
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
		coin = 0;
		currentWeapon = new OBJ_Iron_Sword(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		attack = getAttack();
		defense = getDefense();
		
		if (currentWeapon instanceof OBJ_Staff) {
		    projectile = ((OBJ_Staff) currentWeapon).createProjectile();
		}
	}
	
	public void setItems() {
		
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
		
		if(currentWeapon.type == type_staff) {
			attackUp1 = setup("/player/pSStaff_up_1", gp.tileSize, gp.tileSize * 2);
			attackUp2 = setup("/player/pSStaff_up_2", gp.tileSize, gp.tileSize * 2);
			attackDown1 = setup("/player/pSStaff_down_1", gp.tileSize, gp.tileSize * 2);
			attackDown2 = setup("/player/pSStaff_down_2", gp.tileSize, gp.tileSize * 2);
			attackLeft1 = setup("/player/pSStaff_left_1", gp.tileSize * 2, gp.tileSize);
			attackLeft2 = setup("/player/pSStaff_left_2", gp.tileSize * 2, gp.tileSize);
			attackRight1 = setup("/player/pSStaff_right_1", gp.tileSize * 2, gp.tileSize);
			attackRight2 = setup("/player/pSStaff_right_2", gp.tileSize * 2, gp.tileSize);
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
		
		if(life > maxLife) {
			life = maxLife;
		}
		if(mana > maxMana) {
			mana = maxMana;
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
			        gp.projectileList.add(newProjectile);
			        staff.use(this);
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
				damageMonster(monsterIndex, attack);
				
				int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
				damageInteractiveTile(iTileIndex);
				
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
			if(gp.obj[i].type == type_pickupOnly) {
				
				gp.obj[i].use(this);
				gp.obj[i] = null;
			}
			
			// INVENTORY ITEMS
			else {
				String text;
				
				if(inventory.size() != maxInventorySize) {
					inventory.add(gp.obj[i]);
					gp.playSE(1);
					text = "Got a " + gp.obj[i].name + "!";
				}
				else {
					text = "You cannot carry any more!";
				}
				gp.ui.addMessage(text);
				gp.obj[i] = null;
			}
		}
	}

	public void interactNPC(int i) {

		if (gp.keyH.enterPressed == true) {

			if (i != 999) {
				attackCanceled = true;
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
	}

	public void contactMonster(int i) {

		if (i != 999) {
			if (invincible == false && gp.monster[i].dying == false) {
				gp.playSE(6);
				
				int damage = gp.monster[i].attack - defense;
				if (damage < 0) {
					damage = 0;
				}
				
				life -= damage;
				invincible = true;
			}
		}
	}
	
	public void damageMonster(int i, int attack) {
		
		if (i != 999) {
			
			if (gp.monster[i].invincible == false) {
				
				gp.playSE(5);
				
				int damage = attack - gp.monster[i].defense;
				if (damage < 0) {
					damage = 0;
				}
				
				gp.monster[i].life -= damage;
				gp.ui.addMessage(damage + " damage!");
				
				gp.monster[i].invincible = true;
				gp.monster[i].damageReaction();
				
				if (gp.monster[i].life <= 0) {
					gp.monster[i].dying = true;
					gp.ui.addMessage("killed the " + gp.monster[i].name + "!");
					gp.ui.addMessage("Exp + " + gp.monster[i].exp);
					exp += gp.monster[i].exp;
					checkLevelUp();
				}
			}
		}
	}
	
	public void damageInteractiveTile(int i) {
		
		if (i != 999 && gp.iTile[i].destructible == true 
				&& gp.iTile[i].isCorrectItem(this) == true && gp.iTile[i].invincible == false) {
			gp.iTile[i].playSE();
			gp.iTile[i].life--;
			gp.iTile[i].invincible = true;
			
			// Generate particle
			generateParticle(gp.iTile[i], gp.iTile[i]);
			
			if(gp.iTile[i].life == 0)
				gp.iTile[i] = gp.iTile[i].getDestroyedForm();
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
		
		int itemIndex = gp.ui.getItemIndexOnSlot();
		
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
				
				selectedItem.use(this);
				inventory.remove(itemIndex);
			}
			if(selectedItem.type == type_staff) {
				currentWeapon = selectedItem;
				getPlayerAttackImage();
			}
		}
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
