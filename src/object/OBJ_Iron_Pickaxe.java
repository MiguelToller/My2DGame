package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Iron_Pickaxe extends Entity{

	public static final String objName = "Iron Pickaxe";

	public OBJ_Iron_Pickaxe(GamePanel gp) {
		super(gp);
		
		type = type_pickaxe;
		name = objName;
		down1 = setup("/objects/iron_pickaxe", gp.tileSize, gp.tileSize);
		attackValue = 3;
		attackArea.width = 30;
		attackArea.height = 30;
		description = "[Iron Pickaxe]\nYou will dig it!";
		price = 200;
		knockBackPower = 5;
		motion1_duration = 10;
		motion2_duration = 30;
	}
}
