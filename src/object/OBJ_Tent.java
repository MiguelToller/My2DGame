package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity{
	
	public static final String objName = "Tent";
	
	GamePanel gp;

	public OBJ_Tent(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		down1 = setup("/objects/tent", gp.tileSize, gp.tileSize);
		description = "[Tent]\nYou can sleep until\nnext morning.";
		price = 300;
		stackable = true;
	}
	
	public boolean use(Entity entity) {
		
		gp.gameState = gp.sleepState;
		gp.playSE(13);
		gp.player.life = gp.player.maxLife;
		gp.player.mana = gp.player.maxMana;
		gp.player.getSleepImage(down1);
		return true;
	}

}
