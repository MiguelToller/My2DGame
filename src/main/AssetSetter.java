package main;

import entity.NPC_Merchant;
import entity.NPC_Shepheard;
import monster.MON_GreenSlime;
import monster.MON_Orc;
import object.OBJ_Axe;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Inferno_Fork;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Slime_Staff;
import object.OBJ_Tent;
import tile_interactive.IT_DryTree;

public class AssetSetter {

	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {
		
		int mapNum = 0;
		int i = 0;
		
		gp.obj[mapNum][i] = new	OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*12;
		gp.obj[mapNum][i].worldY = gp.tileSize*12;
		i++;

		gp.obj[mapNum][i] = new	OBJ_Key(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*21;
		gp.obj[mapNum][i].worldY = gp.tileSize*19;
		i++;
		
		gp.obj[mapNum][i] = new	OBJ_Lantern(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*18;
		gp.obj[mapNum][i].worldY = gp.tileSize*20;
		i++;
		
		gp.obj[mapNum][i] = new	OBJ_Tent(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*17;
		gp.obj[mapNum][i].worldY = gp.tileSize*20;
		i++;
		
		gp.obj[mapNum][i] = new	OBJ_Chest(gp, new OBJ_Inferno_Fork(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*30;
		gp.obj[mapNum][i].worldY = gp.tileSize*29;
		i++;
		
		gp.obj[mapNum][i] = new	OBJ_Axe(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*31;
		gp.obj[mapNum][i].worldY = gp.tileSize*21;
		i++;
		
		gp.obj[mapNum][i] = new	OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*22;
		gp.obj[mapNum][i].worldY = gp.tileSize*27;
		i++;
	}

	public void setNPC() {

		int mapNum = 0;
		int i = 0;
		
		// MAP 0
		gp.npc[mapNum][i] = new NPC_Shepheard(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 21;
		gp.npc[mapNum][i].worldY = gp.tileSize * 21;
		i++;
		
		// MAP 1
		mapNum = 1;
		i = 0;
		
		gp.npc[mapNum][i] = new NPC_Merchant(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 12;
		gp.npc[mapNum][i].worldY = gp.tileSize * 7;
		i++;
	}
	
	public void setMonster() {
		
		int mapNum = 0;
		int i = 0;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*23;
		gp.monster[mapNum][i].worldY = gp.tileSize*36;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*23;
		gp.monster[mapNum][i].worldY = gp.tileSize*37;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*24;
		gp.monster[mapNum][i].worldY = gp.tileSize*37;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*34;
		gp.monster[mapNum][i].worldY = gp.tileSize*42;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*38;
		gp.monster[mapNum][i].worldY = gp.tileSize*42;
		i++;
		
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*12;
		gp.monster[mapNum][i].worldY = gp.tileSize*33;
		i++;
	}
	
	public void setInteractiveTile() {
		
		int mapNum = 0;
		int i = 0;
		
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 28, 12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 32, 12); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 33, 12); i++;
		
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 25, 27); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 26, 27); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 27); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 28); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 29); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 30); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 31); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 28, 31); i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 31); i++;
	}
}
