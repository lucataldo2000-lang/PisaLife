package Entity;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;

    public int worldX;
    public int worldY;

    public String name;
    public String direction;
    public int type;
    public final int playerType = 1;
    public final int npcType = 2;
    public final int monsterType = 3;
    public final int objectType = 4;
    public int life;
    public int maxLife;
    public int speed;
    public int attack;
    public int defence;
    public Rectangle solidArea;
    public boolean collisionOn = false;

    public int amount;
    public boolean stackable;
    public int objType;
    public final int weapon = 1;
    public final int shield = 2;
    public final int consumable = 3;

    public int playerClass;
    public final int warriorClass = 0;
    public final int wizardClass = 1;
    public final int rangerClass = 2;

    BufferedImage[][] up = new BufferedImage[3][3];
    BufferedImage[][] down = new BufferedImage[3][3];
    BufferedImage[][] left = new BufferedImage[3][3];
    BufferedImage[][] right = new BufferedImage[3][3];

    BufferedImage image,image2,image3;

    public Entity(GamePanel gp){

        this.gp = gp;
    }

    public void draw(Graphics2D g2){

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
    }


}
