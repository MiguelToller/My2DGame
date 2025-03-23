package object;

import entity.Entity;
import entity.OBJ_Staff;
import main.GamePanel;

public class OBJ_Inferno_Fork extends OBJ_Staff {
	
	GamePanel gp;

	public OBJ_Inferno_Fork(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		projectile = new OBJ_Fireball(gp);
		type = type_staff;
		name = "Inferno Fork";
		down1 = setup("/objects/inferno_fork", gp.tileSize, gp.tileSize);
		description = "[Inferno Fork]\nHellfire.";
		
		loadSprites("/player/pIFork");
	}
	
	public boolean use(Entity entity) {
		
        // SET DEFAULT COORDINATES, DIRECTION AND USER
		if(shotAvailableCounter >= 20) {
			
			OBJ_Fireball newProjectile = new OBJ_Fireball(gp);
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
