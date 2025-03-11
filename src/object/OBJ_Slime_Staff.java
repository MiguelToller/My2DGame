package object;

import entity.Entity;
import entity.OBJ_Staff;
import main.GamePanel;

public class OBJ_Slime_Staff extends OBJ_Staff {
	
	GamePanel gp;

	public OBJ_Slime_Staff(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		projectile = new OBJ_Rock(gp);
		type = type_staff;
		name = "Slime Staff";
		down1 = setup("/objects/slime_staff", gp.tileSize, gp.tileSize);
		description = "[Slime Staff]\nA rare drop from slimes.";
	}
	
	public void use(Entity entity) {
		
        // SET DEFAULT COORDINATES, DIRECTION AND USER
		if(shotAvailableCounter >= 20 && entity != null) {
			
			projectile.set(entity.worldX, entity.worldY, entity.direction, true, entity);
            gp.projectileList.add(projectile);
            projectile.subtractResource(entity);
            shotAvailableCounter = 0;
            gp.playSE(9);
		}
		  if (shotAvailableCounter < 20) 
			  shotAvailableCounter++;
	}
}
