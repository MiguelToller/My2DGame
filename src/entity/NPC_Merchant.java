package entity;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;

public class NPC_Merchant extends Entity {
	
	public NPC_Merchant(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = 0;

		getImage();
		setDialogue();
		setItems();
	}

	public void getImage() {

		up1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
	}

	public void setDialogue() {

		dialogues[0] = "He he, so you found me. \nI have some good stuff. \nDo you want to trade?";
	}
	
	public void setItems() {
		
		inventory.add(new OBJ_Potion_Red(gp));
		inventory.add(new OBJ_Shield_Blue(gp));
		inventory.add(new OBJ_Axe(gp));
	}
	
	public void speak() {
		
		super.speak();
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
	}
}
