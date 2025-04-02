package object;

import entity.OBJ_Sword;
import main.GamePanel;

public class OBJ_Orcish_Club extends OBJ_Sword {

	public OBJ_Orcish_Club(GamePanel gp) {
		super(gp);
		
		type = type_sword;
		name = "Orcish Club";
		down1 = setup("/objects/orcish_club", gp.tileSize, gp.tileSize);
		attackValue = 3;
		attackArea.width = 36;
		attackArea.height = 36;
		description = "[" + name + "]\nA crude wooden club,\nheavy and battle-worn.";
		knockBackPower = 7;
		motion1_duration = 30;
		motion2_duration = 45;
		
		loadSprites("/player/pClub");
	}
}
