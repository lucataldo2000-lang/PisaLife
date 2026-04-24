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


        }catch(IOException e){e.printStackTrace();}
    }

    int num = 0;
    int randomDirection = 0;

    public void update(){

        if(gp.gameThread != null && gp.gameState == gp.playState){
            num++;

            spriteCounter++;

            if(!attacking){
                collisionOn = false;
                gp.checker.checkTile(this);
                gp.checker.checkPlayer(this);

                if(num >= 60){
                    randomDirection = random.nextInt(1,12);
                    num = 0;
                }

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

                if(randomDirection < 10 && !collisionOn){
                    isMoving = true;


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
                }
                if(randomDirection > 9 || collisionOn){
                    isMoving = false;
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

            if(isMoving){
                switch(direction){
                    case "up" -> image = upMonster[spriteNum];
                    case "down" -> image = downMonster[spriteNum];
                    case "left","up-left","down-left" -> image = leftMonster[spriteNum];
                    case "right","up-right","down-right" -> image = rightMonster[spriteNum];
                }
            }
            else{
                switch(direction){
                    case "up" -> image = upIdleMonster[spriteNum];
                    case "down" -> image = downIdleMonster[spriteNum];
                    case "left","up-left","down-left" -> image = leftIdleMonster[spriteNum];
                    case "right","up-right","down-right" -> image = rightIdleMonster[spriteNum];
                }
            }
        }

        g2.drawImage(image,screenX,screenY,width,height,null);

    }
}
