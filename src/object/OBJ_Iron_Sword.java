package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Iron_Sword extends Entity {

	public OBJ_Iron_Sword(GamePanel gp) {
		super(gp);
		
		type = type_sword;
		name = "Iron Sword";
		down1 = setup("/objects/iron_sword", gp.tileSize, gp.tileSize);
		attackValue = 1;
		attackArea.width = 36;
		attackArea.height = 36;
		description = "[" + name + "]\nAn old sword.";
		knockBackPower = 2;
		motion1_duration = 10;
		motion2_duration = 25;
	}
	

}
