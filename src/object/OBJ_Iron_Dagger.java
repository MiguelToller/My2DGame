package object;

import entity.OBJ_Sword;
import main.GamePanel;

public class OBJ_Iron_Dagger extends OBJ_Sword{
	
	public OBJ_Iron_Dagger(GamePanel gp) {
		super(gp);
		
		type = type_sword;
		name = "Iron Dagger";
		down1 = setup("/objects/iron_dagger", gp.tileSize, gp.tileSize);
		attackValue = 1;
		attackArea.width = 26;
		attackArea.height = 26;
		description = "[" + name + "]\nLight, sharp, and \neasy to handle..";
		knockBackPower = 0;
		motion1_duration = 5;
		motion2_duration = 15;
		
		loadSprites("/player/pDagger");
	}

}
