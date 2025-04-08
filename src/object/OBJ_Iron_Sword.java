package object;

import entity.OBJ_Sword;
import main.GamePanel;

public class OBJ_Iron_Sword extends OBJ_Sword {
	
	public static final String objName = "Iron Sword";

	public OBJ_Iron_Sword(GamePanel gp) {
		super(gp);
		
		type = type_sword;
		name = objName;
		down1 = setup("/objects/iron_sword", gp.tileSize, gp.tileSize);
		attackValue = 2;
		attackArea.width = 36;
		attackArea.height = 36;
		description = "[" + name + "]\nAn old sword.";
		knockBackPower = 2;
		motion1_duration = 10;
		motion2_duration = 25;
		
		loadSprites("/player/pAttack");
	}
}
