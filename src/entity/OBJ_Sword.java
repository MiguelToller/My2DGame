package entity;

import main.GamePanel;

public class OBJ_Sword extends Entity {

	public OBJ_Sword(GamePanel gp) {
		super(gp);
		type = type_sword;
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
