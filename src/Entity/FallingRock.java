package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FallingRock extends Entity {

    public FallingRock(GamePanel gp){
        super(gp);

        name = "FallingRock";
        direction = "down";
        life = 60;
        speed = 5;
        damage = 15;
        effect = true;
        collisionOn = true;

        solidArea = new Rectangle(100,100);
        solidArea.x = 0;
        solidArea.y = 0;

        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;

        attackArea = new Rectangle(100,100);
        attackArea.x = 0;
        attackArea.y = 0;

        attackAreaX = attackArea.x;
        attackAreaY = attackArea.y;

        try{
            objImage[0] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossObj3.png"));
        }catch(IOException e){e.printStackTrace();}
    }

    int coolDown;

    public void update(){
        life--;
        coolDown++;
        worldY += speed;

        if(gp.checker.checkPlayer(this) && coolDown > 15){
            gp.player.life -= damage;
            coolDown = 0;
        }
        if(life <= 0){
            animFinished = true;
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = objImage[spriteNum];

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.drawImage(image,screenX,screenY,image.getWidth(),image.getHeight(),null);

    }
}
