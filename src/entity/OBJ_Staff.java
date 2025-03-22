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
        	
            for (int i = 0; i < gp.projectile[gp.currentMap].length; i++) {
                if (gp.projectile[gp.currentMap][i] == null) {
                    gp.projectile[gp.currentMap][i] = newProjectile;
                    break;
                }
            }
        	
            projectile.subtractResource(user);
        } else {
        	user.attacking = false;
        }
    }
    
    public void loadSprites(String spritePath) {
        attackUp1 = setup(spritePath + "_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup(spritePath + "_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup(spritePath + "_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup(spritePath + "_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup(spritePath + "_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup(spritePath + "_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup(spritePath + "_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup(spritePath + "_right_2", gp.tileSize * 2, gp.tileSize);
    }
}