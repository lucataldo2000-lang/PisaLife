package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DamageArea extends Entity {

    int ogY;

    public DamageArea(GamePanel gp){
        super(gp);

        name = "damageArea";
        life = 90;
        ogY = worldY;
        speed = 1;
        effect = true;
        try{
            objImage[0] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossObj0.png"));
            objImage[1] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossObj1.png"));
            objImage[2] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossObj2.png"));
        }catch(IOException e){e.printStackTrace();}
    }
    public void update(){
        life--;
        spriteCounter++;

        if (spriteCounter >= 0 && spriteCounter < 5) {
            spriteNum = 0;
        }
        if (spriteCounter >= 5 && spriteCounter < 10) {
            spriteNum = 1;
        }
        if (spriteCounter >= 10 && spriteCounter < 15) {
            spriteNum = 2;
        }
        if (spriteCounter >= 15 && spriteCounter < 20) {
            spriteNum = 1;
        }
        if (spriteCounter >= 20){
            spriteCounter = 0;

            for(int i = 0; i < gp.objects[gp.currentLevel][gp.currentRoom].length; i++){
                if(gp.objects[gp.currentLevel][gp.currentRoom][i] == null){

                    gp.objects[gp.currentLevel][gp.currentRoom][i] = new FallingRock(gp);
                    gp.objects[gp.currentLevel][gp.currentRoom][i].worldX = worldX;
                    gp.objects[gp.currentLevel][gp.currentRoom][i].worldY = worldY - gp.tileSize * 3;

                    animFinished = true;
                    return;
                }
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = objImage[spriteNum];

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.drawImage(image,screenX,screenY,image.getWidth(),image.getHeight(),null);

    }
}
