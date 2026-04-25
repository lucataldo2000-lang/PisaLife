package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
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
    public int damage;
    public int defence;
    public int strength;
    public Rectangle solidArea;
    public Rectangle attackArea;
    public boolean collisionOn = false;
    public boolean attacking = false;
    public boolean canTakeDamage = false;
    public boolean isMoving = false;
    public boolean damageDone = false;

    public int solidAreaX;
    public int solidAreaY;

    public int attackAreaX;
    public int attackAreaY;

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

    BufferedImage[][] upIdle = new BufferedImage[3][2];
    BufferedImage[][] downIdle = new BufferedImage[3][2];
    BufferedImage[][] leftIdle = new BufferedImage[3][2];
    BufferedImage[][] rightIdle = new BufferedImage[3][2];

    BufferedImage[] upMonster = new BufferedImage[3];
    BufferedImage[] downMonster = new BufferedImage[3];
    BufferedImage[] leftMonster = new BufferedImage[3];
    BufferedImage[] rightMonster = new BufferedImage[3];

    BufferedImage[] upIdleMonster = new BufferedImage[2];
    BufferedImage[] downIdleMonster = new BufferedImage[2];
    BufferedImage[] leftIdleMonster = new BufferedImage[2];
    BufferedImage[] rightIdleMonster = new BufferedImage[2];

    BufferedImage[][][] upAttack = new BufferedImage[3][3][3];
    BufferedImage[][][] downAttack = new BufferedImage[3][3][3];
    BufferedImage[][][] leftAttack = new BufferedImage[3][3][3];
    BufferedImage[][][] rightAttack = new BufferedImage[3][3][3];

    public int spriteCounter;
    public int spriteNum;

    public boolean decoration;

    public BufferedImage[] objImage = new BufferedImage[5];

    public Entity(GamePanel gp){

        this.gp = gp;
    }

    public Entity cloneObject(Entity entity){

        Entity newObject = new Entity(gp);
        newObject.name = entity.name;
        newObject.direction = entity.direction;
        newObject.type = entity.type;
        newObject.objType = entity.objType;
        newObject.collisionOn = entity.collisionOn;
        newObject.solidArea = entity.solidArea;
        newObject.solidArea.x = entity.solidArea.x;
        newObject.solidArea.y = entity.solidArea.y;
        newObject.solidAreaX = entity.solidAreaX;
        newObject.solidAreaY = entity.solidAreaY;

        for(int i = 0; i < objImage.length; i++){
            newObject.objImage[i] = entity.objImage[i];
        }

        return newObject;
    }

    public void update(){}

    public void draw(Graphics2D g2){

        spriteCounter++;

        BufferedImage drawImage = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        for(int i = 0; i < gp.objects[gp.currentLevel][gp.currentRoom].length; i++){

           if(gp.objects[gp.currentLevel][gp.currentRoom][i] != null){

               if(spriteCounter >= 2 && spriteNum < 5){
                   spriteNum = 0;
               }
               if(spriteCounter >= 5 && spriteNum < 8){
                   spriteNum = 1;
               }
               if(spriteCounter >= 8 && spriteNum < 11){
                   spriteNum = 2;
               }
               if(spriteCounter >= 11 && spriteNum < 14){
                   spriteNum = 3;
               }
               if(spriteCounter >= 14 && spriteNum < 17){
                   spriteNum = 4;
               }
               if(spriteCounter >= 17 && spriteNum < 20){
                   spriteNum = 3;
               }
               if(spriteCounter >= 20 && spriteNum < 23){
                   spriteNum = 2;
               }
               if(spriteCounter >= 26 && spriteNum < 29){
                   spriteNum = 1;
               }
               if(spriteCounter >= 29 && spriteNum < 32){
                   spriteNum = 0;
               }

               if(spriteCounter >= 32){
                   spriteCounter = 0;
               }

               drawImage = objImage[spriteNum];
           }
        }

        g2.drawImage(drawImage,screenX,screenY,gp.tileSize,gp.tileSize,null);
    }


}
