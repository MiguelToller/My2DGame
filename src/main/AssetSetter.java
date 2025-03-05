package main;

import entity.NPC_Shepheard;
import monster.MON_GreenSlime;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {
		
		gp.obj[0] = new	OBJ_Door(gp);
		gp.obj[0].worldX = gp.tileSize*10;
		gp.obj[0].worldY = gp.tileSize*12;
		
		gp.obj[1] = new	OBJ_Key(gp);
		gp.obj[1].worldX = gp.tileSize*25;
		gp.obj[1].worldY = gp.tileSize*19;
	}

	public void setNPC() {

		gp.npc[0] = new NPC_Shepheard(gp);
		gp.npc[0].worldX = gp.tileSize * 21;
		gp.npc[0].worldY = gp.tileSize * 21;
	}
	
	public void setMonster() {
		
		int i = 0;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize*23;
		gp.monster[i].worldY = gp.tileSize*36;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize*23;
		gp.monster[i].worldY = gp.tileSize*37;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize*24;
		gp.monster[i].worldY = gp.tileSize*37;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize*34;
		gp.monster[i].worldY = gp.tileSize*42;
		i++;
		
		gp.monster[i] = new MON_GreenSlime(gp);
		gp.monster[i].worldX = gp.tileSize*38;
		gp.monster[i].worldY = gp.tileSize*42;
		i++;
	}
}
