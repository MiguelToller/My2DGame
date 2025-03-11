package object;

import entity.Entity;
import entity.OBJ_Staff;
import main.GamePanel;

public class OBJ_Fire_Staff extends OBJ_Staff {
	
	GamePanel gp;

	public OBJ_Fire_Staff(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		projectile = new OBJ_Fireball(gp);
		type = type_staff;
		name = "Fire Staff";
		down1 = setup("/objects/slime_staff", gp.tileSize, gp.tileSize);
		description = "[Fire Staff]\nFire Force.";
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
