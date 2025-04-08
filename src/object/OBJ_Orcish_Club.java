package object;

import entity.OBJ_Sword;
import main.GamePanel;

public class OBJ_Orcish_Club extends OBJ_Sword {
	
	public static final String objName = "Orcish Club";

	public OBJ_Orcish_Club(GamePanel gp) {
		super(gp);
		
		type = type_sword;
		name = objName;
		down1 = setup("/objects/orcish_club", gp.tileSize, gp.tileSize);
		attackValue = 4;
		attackArea.width = 40;
		attackArea.height = 40;
		description = "[" + name + "]\nA crude wooden club,\nheavy and battle-worn.";
		knockBackPower = 7;
		motion1_duration = 30;
		motion2_duration = 45;
		
		loadSprites("/player/pClub");
	}
}
