package entity;
import main.GamePanel;

public class OBJ_Staff extends Entity {
    
    protected Projectile projectile;

    public OBJ_Staff(GamePanel gp) {
        super(gp);
        type = type_staff;
    }

    public Projectile getProjectile() {
        return projectile;
    }
    
    public Projectile createProjectile() {
    	return new Projectile(gp);
    }

    public void use(Entity user) {
    	
        if (projectile != null && projectile.haveResource(user)) {
        	Projectile newProjectile = createProjectile();
        	newProjectile.set(user.worldX, user.worldY, user.direction, true, user);
            gp.projectileList.add(newProjectile);
            projectile.subtractResource(user);
        } else {
        	user.attacking = false;
        }
    }
}