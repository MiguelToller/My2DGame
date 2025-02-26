package main;

import entity.NPC_Shepheard;
import object.OBJ_Door;

public class AssetSetter {

	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {
		
		gp.obj[0] = new	OBJ_Door(gp);
		gp.obj[0].worldX = gp.tileSize*10;
		gp.obj[0].worldY = gp.tileSize*12;
		
	}

	public void setNPC() {

		gp.npc[0] = new NPC_Shepheard(gp);
		gp.npc[0].worldX = gp.tileSize * 21;
		gp.npc[0].worldY = gp.tileSize * 21;
	}
}
