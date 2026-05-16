package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Chest extends Entity{

    int monstersKilled;
    int currentMonsters;
    int totalMonsters;
    int room;
    boolean opened;

    Random random = new Random();

    public Chest(GamePanel gp){
        super(gp);

        name = "Chest";
        decoration = true;
        collisionOn = true;

        solidArea = new Rectangle(30,30);
        solidArea.x = 5;
        solidArea.y = 5;

        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;

        room = gp.currentRoom;

        for(int i = 0; i < gp.monsters[gp.currentLevel][room].length; i++){
            if(gp.monsters[gp.currentLevel][room][i] != null){
                currentMonsters++;
            }
        }

        totalMonsters = currentMonsters;

        try{
            for(int i = 0; i < objImage.length; i++){
                objImage[i] = ImageIO.read(getClass().getResourceAsStream("/DecorationsTextures/chest" + i + ".png"));
            }
        }catch(IOException e){e.printStackTrace();}
    }

    public void update(){

        if(!opened){
            currentMonsters = 0;

            if(gp.currentRoom != room){
                room = gp.currentRoom;

                for(int i = 0; i < gp.monsters[gp.currentLevel][room].length; i++){
                    if(gp.monsters[gp.currentLevel][room][i] != null){
                        currentMonsters++;
                    }
                }

                totalMonsters = currentMonsters;
            }

            for(int i = 0; i < gp.monsters[gp.currentLevel][room].length; i++){
                if(gp.monsters[gp.currentLevel][room][i] != null){
                    currentMonsters++;
                }
            }

            if(!(monstersKilled + currentMonsters == totalMonsters)){

                monstersKilled = totalMonsters - currentMonsters;
            }
            if(monstersKilled == totalMonsters){

                int num = random.nextInt(1,5);
                Entity obj = null;

                if(gp.player.playerClass == gp.player.warriorClass){
                    switch(num){
                        case 1 -> obj = new LongSword(gp);
                        case 2,4 -> obj = new HealthPotion(gp);
                        case 3 -> obj = new IceSword(gp);
                    }
                }
                if(gp.player.playerClass == gp.player.wizardClass){
                    switch(num){
                        case 1 -> obj = new IceStaff(gp);
                        case 2,4 -> obj = new HealthPotion(gp);
                        case 3 -> obj = new IceOrb(gp);
                    }
                }
                if(gp.player.playerClass == gp.player.rangerClass){
                    switch(num){
                        case 1,2,3,4 -> obj = new HealthPotion(gp);
                    }
                }

                for(int i = 0; i < gp.objects[gp.currentLevel][gp.currentRoom].length;i++){
                    if(obj != null && gp.objects[gp.currentLevel][gp.currentRoom][i] == null){
                        gp.objects[gp.currentLevel][gp.currentRoom][i] = obj;
                        obj.worldX = this.worldX;
                        obj.worldY = this.worldY;
                        opened = true;

                        break;
                    }
                }
            }
        }
        else{
            spriteCounter++;

            if (spriteCounter <= 6) {
                spriteNum = 0;
            }
            if (spriteCounter > 6 && spriteCounter <= 12) {
                spriteNum = 1;
            }
            if (spriteCounter > 12 && spriteCounter <= 18) {
                spriteNum = 2;
            }
            if (spriteCounter > 18 && spriteCounter <= 24) {
                spriteNum = 3;
            }
            if (spriteCounter > 24 && spriteCounter <= 30) {
                spriteNum = 4;
            }
            if (spriteCounter > 30) {
                spriteCounter = 0;
                animFinished = true;
            }
        }

    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        int width = 40;
        int height = 40;

        if(totalMonsters == 10){
            screenX -= 6;
        }

        if(!opened){
            image = objImage[0];

            g2.setColor(new Color(181, 131, 55));
            g2.setFont(new Font("Arial",Font.BOLD,20));
            g2.drawString(monstersKilled + " / " + totalMonsters,screenX - 1, screenY - 11);

            g2.setColor(new Color(255, 190, 78));
            g2.setFont(new Font("Arial",Font.BOLD,20));
            g2.drawString(monstersKilled + " / " + totalMonsters,screenX, screenY - 10);
        }
        else{
            image = objImage[spriteNum];
        }

        if(totalMonsters == 10){
            screenX += 6;
        }

        g2.drawImage(image,screenX,screenY,width,height,null);

    }
}
