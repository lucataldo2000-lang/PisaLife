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

    public Player(GamePanel gp){
        super(gp);

        this.gp = gp;

        type = playerType;
        solidArea = new Rectangle(20,20);

        screenX = gp.screenWidth / 2;
        screenY = gp.screenHeight / 2;

        setUp();
        getImages();
    }

    public void setUp(){
        maxLife = 100;
        life = maxLife;
        speed = 4;
        direction = "down";

        worldX = 0;
        worldY = 0;
    }

    public void getImages(){
        try{

            up[0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/basewarrior1.png"));
            down[0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/basewarrior0.png"));
            left[0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/basewarrior3.png"));
            right[0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/basewarrior2.png"));

        }catch(IOException e){e.printStackTrace();}
    }

    public void update(){

        if(gp.gameThread != null && gp.gameState == gp.playState){

            if(gp.handler.upPressed || gp.handler.downPressed || gp.handler.leftPressed || gp.handler.rightPressed){

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
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        int x = screenX;
        int y = screenY;

        switch(direction){
            case "up" -> image = up[0];
            case "down" -> image = down[0];
            case "left","up-left","down-left" -> image = left[0];
            case "right","up-right","down-right" -> image = right[0];
        }

        g2.drawImage(image,x,y,gp.tileSize * 2,gp.tileSize * 2,null);

    }
}
