package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    public int screenX;
    public int screenY;

    public Entity[] inventory = new Entity[5];
    public int objIndex;

    public Player(GamePanel gp){
        super(gp);

        this.gp = gp;

        type = playerType;
        solidArea = new Rectangle(40,40);
        solidArea.x = 12;
        solidArea.y = 22;

        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;

        screenX = gp.screenWidth / 2;
        screenY = gp.screenHeight / 2;

        setUp();
        getImages();
    }

    public void setUp(){
        gp.currentRoom = 1;
        gp.currentLevel = 1;
        maxLife = 100;
        life = maxLife;
        speed = 4;
        direction = "down";

        worldX = 112;
        worldY = 434;
    }

    public void resetPlayer(){

        setUp();

        int level = 0;
        int room = 0;
        int obj = 0;

        while(level < gp.maxLevel){

            if(gp.objects[level][room][obj] != null){
                gp.objects[level][room][obj] = null;
            }

            obj++;

            if(obj == gp.maxObj){
                room++;
                obj = 0;
            }
            if(room == gp.maxRoom){
                room = 0;
                level++;
            }

        }

        for(int i = 0; i < inventory.length; i++){

            if(inventory[i] != null){
                inventory[i] = null;
            }
        }

        setStartLoadOut();
        gp.setter.setObj();
    }

    public void setStartLoadOut(){

        switch(playerClass){
            case 0 -> {
                gp.objects[1][1][0] = new BasicSword(gp);
                gp.objects[1][1][0].worldX = gp.tileSize * 3;
                gp.objects[1][1][0].worldY = gp.tileSize * 12;

                gp.objects[1][1][1] = new BasicShield(gp);
                gp.objects[1][1][1].worldX = gp.tileSize * 5;
                gp.objects[1][1][1].worldY = gp.tileSize * 12;
            }
            case 1 -> {
                gp.objects[1][1][1] = new BasicStaff(gp);
                gp.objects[1][1][1].worldX = gp.tileSize * 3;
                gp.objects[1][1][1].worldY = gp.tileSize * 12;

                gp.objects[1][1][0] = new BasicOrb(gp);
                gp.objects[1][1][0].worldX = gp.tileSize * 5;
                gp.objects[1][1][0].worldY = gp.tileSize * 12;
            }
            case 2 ->{
                gp.objects[1][1][0] = new BasicBow(gp);
                gp.objects[1][1][0].worldX = gp.tileSize * 4;
                gp.objects[1][1][0].worldY = gp.tileSize * 12;
            }
        }
    }

    public void getImages(){
        try{

            up[0][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior3.png"));
            down[0][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior0.png"));
            left[0][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior9.png"));
            right[0][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior6.png"));

            up[0][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior4.png"));
            down[0][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior1.png"));
            left[0][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior10.png"));
            right[0][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior7.png"));

            up[0][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior5.png"));
            down[0][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior2.png"));
            left[0][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior11.png"));
            right[0][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior8.png"));

            up[1][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizard1.png"));
            down[1][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizard0.png"));
            left[1][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizard3.png"));
            right[1][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizard2.png"));

            up[1][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizAnim2.png"));
            down[1][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizAnim0.png"));
            left[1][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizAnim6.png"));
            right[1][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizAnim4.png"));

            up[1][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizAnim3.png"));
            down[1][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizAnim1.png"));
            left[1][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizAnim7.png"));
            right[1][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizAnim5.png"));

            up[2][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer1.png"));
            down[2][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer0.png"));
            left[2][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer0.png"));
            right[2][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer0.png"));

            upIdle[1][0] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/wiz0.png"));
            upIdle[1][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/wiz1.png"));
            downIdle[1][0] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/wiz2.png"));
            downIdle[1][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/wiz3.png"));
            leftIdle[1][0] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/wiz4.png"));
            leftIdle[1][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/wiz5.png"));
            rightIdle[1][0] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/wiz6.png"));
            rightIdle[1][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/wiz7.png"));

            upIdle[0][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior3.png"));
            upIdle[0][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/warrior1.png"));
            downIdle[0][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior0.png"));
            downIdle[0][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/warrior0.png"));
            leftIdle[0][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior9.png"));
            leftIdle[0][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/warrior3.png"));
            rightIdle[0][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior6.png"));
            rightIdle[0][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/warrior2.png"));


        }catch(IOException e){e.printStackTrace();}
    }

    public void update(){

        if(gp.gameThread != null && gp.gameState == gp.playState){

            spriteCounter++;

            if(gp.handler.upPressed || gp.handler.downPressed || gp.handler.leftPressed || gp.handler.rightPressed){

                collisionOn = false;

                gp.checker.checkTile(this);

                if(gp.handler.upPressed){
                    direction = "up";
                }
                if(gp.handler.downPressed){
                    direction = "down";
                }
                if(gp.handler.leftPressed){
                    direction = "left";
                }
                if(gp.handler.rightPressed){
                    direction = "right";
                }

                if(gp.handler.upPressed && gp.handler.rightPressed){
                    direction = "up-right";
                }
                if(gp.handler.upPressed && gp.handler.leftPressed){
                    direction = "up-left";
                }
                if(gp.handler.downPressed && gp.handler.rightPressed){
                    direction = "down-right";
                }
                if(gp.handler.downPressed && gp.handler.leftPressed){
                    direction = "down-left";
                }

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

            }

            if(!gp.handler.upPressed && !gp.handler.downPressed && !gp.handler.leftPressed && !gp.handler.rightPressed){
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

            if(gp.handler.dropPressed){
                dropObject();
            }

            objIndex = gp.checker.checkObject(this);

            pickUpObject(objIndex);

            gp.events.checkEvent();
        }
    }

    public void pickUpObject(int index){

        if(index != 999){

            for(int i = 0; i < inventory.length; i++){

               if(gp.objects[gp.currentLevel][gp.currentRoom][index] != null && gp.handler.takePressed){
                   gp.handler.takePressed = false;

                   switch(gp.objects[gp.currentLevel][gp.currentRoom][index].objType){
                       case 1 -> {

                           if(inventory[0] == null){
                               inventory[0] = gp.objects[gp.currentLevel][gp.currentRoom][index];
                               gp.objects[gp.currentLevel][gp.currentRoom][index] = null;
                           }
                       }
                       case 2 -> {
                           if(inventory[1] == null){
                               inventory[1] = gp.objects[gp.currentLevel][gp.currentRoom][index];
                               gp.objects[gp.currentLevel][gp.currentRoom][index] = null;
                           }
                       }
                       case 3 -> {

                           for(int j = 2; j < inventory.length; j++){

                               if(inventory[j] == null){
                                   inventory[j] = gp.objects[gp.currentLevel][gp.currentRoom][index];

                                   gp.objects[gp.currentLevel][gp.currentRoom][index] = null;

                                   return;
                               }
                           }
                       }
                   }

               }

            }
        }
    }

    public void dropObject(){

        gp.handler.dropPressed = false;

        if(inventory[gp.handler.inventorySelector] != null){
            for(int i = 0; i < gp.objects[gp.currentLevel][gp.currentRoom].length;i++){

                if(gp.objects[gp.currentLevel][gp.currentRoom][i] == null){

                    Entity newObject = cloneObject(inventory[gp.handler.inventorySelector]);
                    gp.objects[gp.currentLevel][gp.currentRoom][i] = newObject;

                    switch(direction){
                        case "up","up-left","up-right" -> {
                            newObject.worldX = gp.player.worldX + gp.tileSize / 2;
                            newObject.worldY = gp.player.worldY - gp.tileSize;
                        }
                        case "down","down-left","down-right" -> {
                            newObject.worldX = gp.player.worldX + gp.tileSize / 2;
                            newObject.worldY = gp.player.worldY + gp.tileSize * 2;
                        }
                        case "right" -> {
                            newObject.worldX = gp.player.worldX + gp.tileSize * 2;
                            newObject.worldY = gp.player.worldY + gp.tileSize / 2;
                        }
                        case "left" -> {
                            newObject.worldX = gp.player.worldX - gp.tileSize;
                            newObject.worldY = gp.player.worldY + gp.tileSize / 2;
                        }
                    }

                    inventory[gp.handler.inventorySelector] = null;
                    return;
                }
            }

        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        int x = screenX;
        int y = screenY;

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

        g2.drawImage(image,x,y,gp.tileSize * 2,gp.tileSize * 2,null);

    }
}
