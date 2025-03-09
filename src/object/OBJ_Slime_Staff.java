package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Slime_Staff extends Entity {

	public OBJ_Slime_Staff(GamePanel gp) {
		super(gp);
		
		type = type_staff;
		name = "Slime Staff";
		down1 = setup("/objects/slime_staff", gp.tileSize, gp.tileSize);
		attackValue = 0;
		attackArea.width = 30;
		attackArea.height = 30;
		description = "[Slime Staff]\nA rare drop from slimes.";
	}
}
