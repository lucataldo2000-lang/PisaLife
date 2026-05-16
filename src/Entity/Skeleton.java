package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Skeleton extends Entity{

    GamePanel gp;

    public Skeleton(GamePanel gp){
        super(gp);

        this.gp = gp;

        speed = 2;
        maxLife = 100;
        life = maxLife;
        direction = "down";
        damage = 20;
        name = "Skeleton";
        type = monsterType;
        solidArea = new Rectangle(20,40);
        solidArea.x = 22;
        solidArea.y = 22;

        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;

        getImages();
    }

    public void getImages(){
        try{

            upMonster[0] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeleton1.png"));
            downMonster[0] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeleton0.png"));
            leftMonster[0] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeleton3.png"));
            rightMonster[0] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeleton2.png"));

            upMonster[1] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeletonAnim4.png"));
            downMonster[1] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeletonAnim1.png"));
            leftMonster[1] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeletonAnim7.png"));
            rightMonster[1] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeletonAnim10.png"));

            upMonster[2] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeletonAnim5.png"));
            downMonster[2] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeletonAnim2.png"));
            leftMonster[2] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeletonAnim8.png"));
            rightMonster[2] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeletonAnim11.png"));


            upIdleMonster[0] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeleton1.png"));
            upIdleMonster[1] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/idleAnimations1.png"));
            downIdleMonster[0] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeleton0.png"));
            downIdleMonster[1] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/idleAnimations0.png"));
            leftIdleMonster[0] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeleton3.png"));
            leftIdleMonster[1] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/idleAnimations3.png"));
            rightIdleMonster[0] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeleton2.png"));
            rightIdleMonster[1] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/idleAnimations2.png"));

            frozenEffect = ImageIO.read(getClass().getResourceAsStream("/GUITextures/iceEffect1.png"));
            iceCube = ImageIO.read(getClass().getResourceAsStream("/GUITextures/iceEffect0.png"));


        }catch(IOException e){e.printStackTrace();}
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        int width = 0;
        int height = 0;

        if(!attacking){
            width = gp.tileSize * 2;
            height = gp.tileSize * 2;

            if(isMoving){
                switch(direction){
                    case "up" -> image = upMonster[spriteNum];
                    case "down" -> image = downMonster[spriteNum];
                    case "left","up-left","down-left" -> image = leftMonster[spriteNum];
                    case "right","up-right","down-right" -> image = rightMonster[spriteNum];
                }
            }
            else{
                if(spriteNum > 1){
                    spriteNum = 1;
                }
                switch(direction){
                    case "up" -> image = upIdleMonster[spriteNum];
                    case "down" -> image = downIdleMonster[spriteNum];
                    case "left","up-left","down-left" -> image = leftIdleMonster[spriteNum];
                    case "right","up-right","down-right" -> image = rightIdleMonster[spriteNum];
                }
            }
        }


        g2.drawImage(image,screenX,screenY,width,height,null);

        if(frozen){

            Composite og = g2.getComposite();

            AlphaComposite transparency = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);

            g2.setComposite(transparency);

            g2.drawImage(iceCube,screenX + 6,screenY,gp.tileSize + 20,gp.tileSize * 2 + 5,null);

            g2.setComposite(og);

            g2.drawImage(frozenEffect,screenX + 23,screenY - 20,20,20,null);
        }

    }
}
