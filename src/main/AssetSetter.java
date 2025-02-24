package main;

import entity.NPC_Shepheard;

public class AssetSetter {

	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {

	}

	public void setNPC() {

		gp.npc[0] = new NPC_Shepheard(gp);
		gp.npc[0].worldX = gp.tileSize * 21;
		gp.npc[0].worldY = gp.tileSize * 21;
	}
}
