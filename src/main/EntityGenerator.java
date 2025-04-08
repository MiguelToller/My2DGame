package main;

import entity.Entity;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Coin_Bronze;
import object.OBJ_Door;
import object.OBJ_Fireball;
import object.OBJ_Heart;
import object.OBJ_Inferno_Fork;
import object.OBJ_Iron_Dagger;
import object.OBJ_Iron_Sword;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_ManaCrystal;
import object.OBJ_Orcish_Club;
import object.OBJ_Potion_Red;
import object.OBJ_Rock;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Slime_Staff;
import object.OBJ_Tent;

public class EntityGenerator {
	
	GamePanel gp;
	
	public EntityGenerator(GamePanel gp) {
		this.gp = gp;
	}
	
	public Entity getObject(String itemName) {
		
		Entity obj = null;
		
		switch(itemName) {
		case OBJ_Axe.objName: obj = new OBJ_Axe(gp); break;
		case OBJ_Boots.objName: obj = new OBJ_Boots(gp); break;
		case OBJ_Chest.objName: obj = new OBJ_Chest(gp); break;
		case OBJ_Coin_Bronze.objName: obj = new OBJ_Coin_Bronze(gp); break;
		case OBJ_Door.objName: obj = new OBJ_Door(gp); break;
		case OBJ_Fireball.objName: obj = new OBJ_Fireball(gp); break;
		case OBJ_Heart.objName: obj = new OBJ_Heart(gp); break;
		case OBJ_Inferno_Fork.objName: obj = new OBJ_Inferno_Fork(gp); break;
		case OBJ_Iron_Dagger.objName: obj = new OBJ_Iron_Dagger(gp); break;
		case OBJ_Iron_Sword.objName: obj = new OBJ_Iron_Sword(gp); break;
		case OBJ_Key.objName: obj = new OBJ_Key(gp); break;
		case OBJ_Lantern.objName: obj = new OBJ_Lantern(gp); break;
		case OBJ_ManaCrystal.objName: obj = new OBJ_ManaCrystal(gp); break;
		case OBJ_Orcish_Club.objName: obj = new OBJ_Orcish_Club(gp); break;
		case OBJ_Potion_Red.objName: obj = new OBJ_Potion_Red(gp); break;
		case OBJ_Rock.objName: obj = new OBJ_Rock(gp); break;
		case OBJ_Shield_Blue.objName: obj = new OBJ_Shield_Blue(gp); break;
		case OBJ_Shield_Wood.objName: obj = new OBJ_Shield_Wood(gp); break;
		case OBJ_Slime_Staff.objName: obj = new OBJ_Slime_Staff(gp); break;
		case OBJ_Tent.objName: obj = new OBJ_Tent(gp); break;
		}
		return obj;
	}
}
