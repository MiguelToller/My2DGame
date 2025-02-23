package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_Boots;
import object.OBJ_Key;

public class UI {

	GamePanel gp;
	Font arial_40, arial_80B;
	BufferedImage keyImage, bootsImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public boolean getTreasure = false;
	public boolean treasureSoundPlayed = false;

	public UI(GamePanel gp) {
		this.gp = gp;

		arial_40 = new Font("Arial", Font.PLAIN, 40); // font name, font style and font size.
		arial_80B = new Font("Arial", Font.BOLD, 80);
		OBJ_Key key = new OBJ_Key();
		keyImage = key.image;
		OBJ_Boots boots = new OBJ_Boots();
		bootsImage = boots.image;
	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {

		// GET TREASURE
		if (getTreasure == true) {
			
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			
			String text;
			int textLenght, x, y;
			
			// DRAW MESSAGE
			text = "You found a treasure!";
			textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // Return lenght
			x = gp.screenWidth/2 - textLenght/2;
			y = gp.screenHeight/2 - (gp.tileSize*3);
			g2.drawString(text, x, y);
			
			// DRAW ITEM NAME
			g2.setFont(arial_80B);
			g2.setColor(Color.yellow);
			text = "Boots";
			textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // Return lenght
			x = gp.screenWidth/2 - textLenght/2;
			y = gp.screenHeight/2 + (gp.tileSize*3);
			g2.drawString(text, x, y);
			
			// DRAW THE ITEM -> BOOTS
			int size = gp.tileSize*2;
			g2.drawImage(bootsImage, gp.screenWidth/2 - size/2, gp.screenHeight/2 - size/2, size, size, null);
			
			// LOOP RESTART
			messageCounter++;
			if (messageCounter > 300) {
				messageCounter = 0;
				getTreasure = false;
				treasureSoundPlayed = false;
				gp.playMusic(0);
			}

		} else {
			
			// KEY COUNTER
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			g2.drawString("x " + gp.player.hasKey, 74, 65);

			// MESSAGE
			if (messageOn == true) {
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
				messageCounter++;

				if (messageCounter > 120) {
					messageCounter = 0;
					messageOn = false;
				}
			}
			
		}
	}
}
