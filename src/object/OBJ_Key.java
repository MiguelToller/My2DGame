package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {
	
	public static final String objName = "Key";

	GamePanel gp;
	
	public OBJ_Key(GamePanel gp) {
		super(gp);
		this.gp = gp;

		type = type_consumable;
		name = objName;
		down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\nIt unlocks something.";
		price = 500;
		stackable = true;
		
		setDialogue();
	}
	
	public void setDialogue() {
		
		dialogues[0][0] = "The " + name + " turns with a soft click. The door is now open.";
		dialogues[1][0] = "What are you doing?";
	}
	
	public boolean use(Entity entity) {
		
		int objIndex = getDetected(entity, gp.obj, "Door");
		
		if (objIndex != 999) {
			startDialogue(this, 0);
			gp.playSE(2);
			gp.obj[gp.currentMap][objIndex] = null;
			return true;
		}
		else {
			startDialogue(this, 1);
			return false;
		}
	}
}
