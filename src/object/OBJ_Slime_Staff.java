package object;

import entity.Entity;
import entity.OBJ_Staff;
import main.GamePanel;

public class OBJ_Slime_Staff extends OBJ_Staff {
	
	public static final String objName = "Slime Staff";
	
	GamePanel gp;

	public OBJ_Slime_Staff(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		projectile = new OBJ_Rock(gp);
		type = type_staff;
		name = objName;
		down1 = setup("/objects/slime_staff", gp.tileSize, gp.tileSize);
		description = "[Slime Staff]\nA rare drop from slimes.";
		motion1_duration = 10;
		motion2_duration = 25;
		
		loadSprites("/player/pSStaff");
	}
	
	public boolean use(Entity entity) {
		
        // SET DEFAULT COORDINATES, DIRECTION AND USER
		if(shotAvailableCounter >= 20) {
			
			OBJ_Rock newProjectile = new OBJ_Rock(gp);
			newProjectile.set(entity.worldX, entity.worldY, entity.direction, true, entity);
			
            for (int i = 0; i < gp.projectile[gp.currentMap].length; i++) {
                if (gp.projectile[gp.currentMap][i] == null) {
                    gp.projectile[gp.currentMap][i] = newProjectile;
                    break;
                }
            }
			
            newProjectile.subtractResource(entity);
            shotAvailableCounter = 0;
            gp.playSE(9);
            
            return true;
		}
		  if (shotAvailableCounter < 20) 
			  shotAvailableCounter++;
		  return false;
	}
}