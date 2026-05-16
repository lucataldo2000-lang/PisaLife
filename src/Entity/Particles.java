package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Particles extends Entity{

    Entity entity;

    public Particles(GamePanel gp, Entity ent, int lifeTime){
        super(gp);

        life = lifeTime;
        entity = ent;

        worldX = ent.worldX;
        worldY = ent.worldY;
        direction = ent.direction;

        getImages();
    }
    public void getImages(){
        try{

            up[0][0] = ImageIO.read(getClass().getResourceAsStream("/Particles/particle6.png"));
            right[0][0] = ImageIO.read(getClass().getResourceAsStream("/Particles/particle0.png"));
            left[0][0] = ImageIO.read(getClass().getResourceAsStream("/Particles/particle3.png"));

            up[0][1] = ImageIO.read(getClass().getResourceAsStream("/Particles/particle7.png"));
            right[0][1] = ImageIO.read(getClass().getResourceAsStream("/Particles/particle1.png"));
            left[0][1] = ImageIO.read(getClass().getResourceAsStream("/Particles/particle4.png"));

            up[0][2] = ImageIO.read(getClass().getResourceAsStream("/Particles/particle8.png"));
            right[0][2] = ImageIO.read(getClass().getResourceAsStream("/Particles/particle2.png"));
            left[0][2] = ImageIO.read(getClass().getResourceAsStream("/Particles/particle5.png"));

        }catch(IOException e){e.printStackTrace();}
    }

    public void update(){
        spriteCounter++;
        life--;

        if(spriteCounter >= 0 && spriteNum < 3){
            spriteNum = 0;

        }
        if(spriteCounter >= 3 && spriteNum < 6){
            spriteNum = 1;
        }
        if(spriteCounter >= 6 && spriteNum < 9){
            spriteNum = 2;
        }
        if(spriteCounter >= 9 && spriteCounter < 12){
            spriteNum = 3;
        }
        if(spriteCounter >= 12){
            spriteNum = 0;
        }
    }

    public int[] controlsPosition(int xChange, int yChange){

        int[] positions = new int[2];
        int x = 0;
        int y = 0;

        if(entity.type == playerType){
            x = gp.player.screenX + xChange;
            y = gp.player.screenY + 48 + yChange;
        }else{
            if(entity.name.equals("Skeleton")){
                y = worldY - gp.player.worldY + gp.player.screenY + 48 + yChange;
                x = worldX - gp.player.worldX + gp.player.screenX + xChange;
            }
            if(entity.name.equals("Slime")){

                if(direction.equals("left")){
                    x = worldX - gp.player.worldX + gp.player.screenX + xChange;
                }
                else{
                    x = worldX - gp.player.worldX + gp.player.screenX - 8 + xChange;
                }

                y = worldY - gp.player.worldY + gp.player.screenY + 28 + yChange;
            }
        }

        positions[0] = x;
        positions[1] = y;

        return positions;
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        int x = 0;
        int y = 0;

        if(spriteNum != 3){
            switch(direction){
                case "up" -> {
                    x = controlsPosition((entity.solidArea.width + entity.solidAreaX) / 2, entity.solidArea.height / 2)[0];
                    y = controlsPosition((entity.solidArea.width + entity.solidAreaY) / 2, entity.solidArea.height / 2)[1];

                    image = up[0][spriteNum];
                }
                case "left","up-left","down-left" -> {
                    x = controlsPosition(entity.solidArea.width + entity.solidAreaX,0)[0];
                    y = controlsPosition(entity.solidArea.width + entity.solidAreaX,0)[1];

                    image = left[0][spriteNum];
                }
                case "right","up-right","down-right" -> {

                    x = controlsPosition(0,0)[0];
                    y = controlsPosition(0,0)[1];

                    image = right[0][spriteNum];
                }
            }
        }
        else{
            image = null;
        }

        if(image != null && worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
            g2.drawImage(image,x,y,20,20,null);

        }
    }

}
