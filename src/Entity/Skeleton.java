package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Skeleton extends Entity{

    GamePanel gp;
    Random random = new Random();

    public Skeleton(GamePanel gp){
        super(gp);

        this.gp = gp;

        speed = 3;
        maxLife = 100;
        life = maxLife;
        direction = "down";

        type = monsterType;
        solidArea = new Rectangle(40,40);
        solidArea.x = 12;
        solidArea.y = 22;

        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;
    }

    public void getImages(){
        try{

            upMonster[0] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeleton1.png"));
            downMonster[0] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeleton0.png"));
            leftMonster[0] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeleton3.png"));
            rightMonster[0] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeleton2.png"));

            upMonster[1] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeletonAnim4.png"));
            downMonster[1] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeletonAnim1.png"));
            leftMonster[1] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeletonAnim10.png"));
            rightMonster[1] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeletonAnim7.png"));

            upMonster[2] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeletonAnim5.png"));
            downMonster[2] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeletonAnim2.png"));
            leftMonster[2] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeletonAnim11.png"));
            rightMonster[2] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeletonAnim8.png"));


            upIdleMonster[0] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeleton1.png"));
            upIdleMonster[1] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/idleAnimations1.png"));
            downIdleMonster[0] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeleton0.png"));
            downIdleMonster[1] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/idleAnimations0.png"));
            leftIdleMonster[0] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeleton3.png"));
            leftIdleMonster[1] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/idleAnimations3.png"));
            rightIdleMonster[0] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/skeleton2.png"));
            rightIdleMonster[1] = ImageIO.read(getClass().getResourceAsStream("/SkeletonTextures/idleAnimations2.png"));


        }catch(IOException e){e.printStackTrace();}
    }

    public void update(){

        if(gp.gameThread != null && gp.gameState == gp.playState){

            int randomDirection = random.nextInt(1,13);
            int waitTime = random.nextInt(30,121);
            int num = 0;

            spriteCounter++;

            if(!attacking){
                if(randomDirection < 10){
                    collisionOn = false;

                    switch(randomDirection){
                        case 0 -> direction = "up";
                        case 1 -> direction = "down";
                        case 2 -> direction = "left";
                        case 3 -> direction = "right";
                        case 4 -> direction = "up-right";
                        case 5 -> direction = "up-left";
                        case 6 -> direction = "down-right";
                        case 7 -> direction = "down-left";
                    }

                    gp.checker.checkTile(this);

                    if(spriteCounter <= 4){
                        spriteNum = 0;
                    }
                    if(spriteCounter > 5 && spriteCounter <= 10){
                        spriteNum = 1;
                    }
                    if(spriteCounter > 10 && spriteCounter <= 15){
                        spriteNum = 0;
                    }
                    if(spriteCounter > 15 && spriteCounter <= 20){
                        spriteNum = 2;
                    }
                    if(spriteCounter > 20){
                        spriteCounter = 0;
                    }

                    if(!collisionOn){
                        while(num < waitTime){
                            switch(direction){
                                case "down" -> worldY += speed;
                                case "up" -> worldY -= speed;
                                case "left" ->  worldX -= speed;
                                case "right" -> worldX += speed;
                                case "down-left" -> {
                                    worldY += speed - 1;
                                    worldX -= speed - 1;
                                }
                                case "up-left" -> {
                                    worldY -= speed - 1;
                                    worldX -= speed - 1;
                                }
                                case "down-right" ->  {
                                    worldY += speed - 1;
                                    worldX += speed - 1;
                                }
                                case "up-right" -> {
                                    worldY -= speed - 1;
                                    worldX += speed - 1;
                                }
                            }

                            num++;
                        }
                    }
                }
                if(randomDirection > 9){
                    if(spriteCounter <= 15){
                        spriteNum = 0;
                    }
                    if(spriteCounter > 15 && spriteCounter <= 30){
                        spriteNum = 1;
                    }
                    if(spriteCounter > 30){
                        spriteCounter = 0;
                    }
                }
            }
        }
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

            if(gp.handler.upPressed || gp.handler.downPressed || gp.handler.leftPressed || gp.handler.rightPressed){
                switch(direction){
                    case "up" -> image = up[playerClass][spriteNum];
                    case "down" -> image = down[playerClass][spriteNum];
                    case "left","up-left","down-left" -> image = left[playerClass][spriteNum];
                    case "right","up-right","down-right" -> image = right[playerClass][spriteNum];
                }
            }
            else{
                switch(direction){
                    case "up" -> image = upIdle[playerClass][spriteNum];
                    case "down" -> image = downIdle[playerClass][spriteNum];
                    case "left","up-left","down-left" -> image = leftIdle[playerClass][spriteNum];
                    case "right","up-right","down-right" -> image = rightIdle[playerClass][spriteNum];
                }
            }
        }

        g2.drawImage(image,screenX,screenY,width,height,null);

    }
}
