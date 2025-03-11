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

    public void use(Entity user) {
        if (projectile != null && projectile.haveResource(user)) {
            projectile.set(user.worldX, user.worldY, user.direction, true, user);
            gp.projectileList.add(projectile);
        } else {
        	user.attacking = false;
        }
    }
}