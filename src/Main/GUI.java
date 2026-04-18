package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GUI {

    GamePanel gp;
    BufferedImage image1,image2,image3,inventoryImage,slotImage,takeImage;
    public boolean canTake;

    public GUI(GamePanel gp){
        this.gp = gp;

        try{
            image1 = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/basewarrior0.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizard0.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer0.png"));
            inventoryImage = ImageIO.read(getClass().getResourceAsStream("/GUITextures/inventory.png"));
            slotImage = ImageIO.read(getClass().getResourceAsStream("/GUITextures/slot.png"));
            takeImage = ImageIO.read(getClass().getResourceAsStream("/GUITextures/takeObject.png"));
        }catch(IOException e){e.printStackTrace();}
    }

    public void draw(Graphics2D g2){

        switch(gp.gameState){
            case 0 -> {
                drawInventory(g2);
                drawTakeObject(g2);
            }
            case 1 -> drawTitle(g2);
            case 4 -> drawClassChooser(g2);
            case 5 -> drawPauseMenu(g2);
        }
    }

    public void drawTitle(Graphics2D g2){

        int x = gp.tileSize;
        int y = gp.tileSize * 2;

        g2.setColor(Color.BLACK);
        g2.fillRect(0,0, gp.screenWidth,gp.screenHeight);

        g2.setFont(new Font("Arial",Font.BOLD, 40));
        g2.setColor(Color.white);

        g2.drawString("GAME", x, y);

        g2.setFont(new Font("Arial",Font.BOLD, 30));

        y += 70;

        int ogY = y;

        String[] lines = {"New Game", "Continue", "Options", "Quit"};

        for(String line : lines){
            g2.drawString(line,x,y);
            y+= 50;
        }

        g2.drawString(">", x - 25, ogY + (50 * gp.handler.titleSelector));

    }

    public void drawClassChooser(Graphics2D g2){

        g2.setFont(new Font("Arial",Font.BOLD, 40));
        g2.setColor(Color.darkGray);
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        g2.setColor(Color.white);

        int x = 100;
        int y = 50;

        g2.drawString("Choose a class", x,y);

        BufferedImage[] images = {image1,image2,image3};

        y = 115;
        x = 50;

        for(BufferedImage img : images){
            g2.drawImage(img,x,y,gp.tileSize * 4,gp.tileSize * 4,null);

            x += gp.tileSize * 4;
        }

        switch(gp.handler.classSelector){
            case 0 -> x = 100;
            case 1 -> x = 240;
            case 2 -> x = 360;
        }

        g2.drawString("v", x, y - 30);

        switch(gp.handler.classSelector){
            case 0 -> x = 50;
            case 1 -> x = 185;
            case 2 -> x = 310;
        }

        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x,y - 7,115,150,10,10);

        x = 75;

        y += gp.tileSize * 5 + gp.tileSize / 2;

        g2.setFont(new Font("Arial",Font.BOLD, 20));

        g2.drawString("Warrior",x,y);

        x += gp.tileSize * 4 + 6;

        g2.drawString("Wizard",x,y);

        x += gp.tileSize * 4;

        g2.drawString("Ranger",x,y);

    }

    public void drawInventory(Graphics2D g2){

        g2.setFont(new Font("Arial",Font.BOLD, 30));

        int x = 15;
        int y = 255;

        for(int i = 0; i < 2; i++){
            g2.drawImage(slotImage,x,y,slotImage.getWidth(),slotImage.getHeight(),null);
            x += slotImage.getWidth() + 5;
        }

        g2.drawImage(inventoryImage,x,y,slotImage.getWidth() * 3,slotImage.getHeight() + 2,null);

        y = 250;

        switch (gp.handler.inventorySelector){
            case 0 -> x = 34;
            case 1 -> x = 89;
            case 2 -> x = 150;
            case 3 -> x = 195;
            case 4 -> x = 240;
        }

        g2.setColor(Color.white);

        g2.drawString("v", x,y);

        x = 23;
        y = 263;

        for(int i = 0; i < gp.player.inventory.length; i++){
            if(gp.player.inventory[i] != null){

                switch(i){
                    case 1 -> {x = 81; y = 265;}
                    case 2 -> {x = 139; y = 265;}
                    case 3 ->  {x = 183;y = 265;}
                    case 4 -> {x = 228;y = 265;}
                }

                g2.drawImage(gp.player.inventory[i].objImage[0],x,y,gp.player.inventory[i].objImage[0].getWidth(),gp.player.inventory[i].objImage[0].getHeight(),null);
            }
        }

    }

    public void drawTakeObject(Graphics2D g2){

        canTake = false;

        for(int i = 0; i <  gp.objects[gp.currentLevel][gp.currentRoom].length; i++){
            if(gp.objects[gp.currentLevel][gp.currentRoom][i] != null && !gp.objects[gp.currentLevel][gp.currentRoom][i].decoration){


                int x = gp.objects[gp.currentLevel][gp.currentRoom][i].worldX - gp.player.worldX + gp.player.screenX;
                int y = gp.objects[gp.currentLevel][gp.currentRoom][i].worldY - gp.player.worldY + gp.player.screenY;

                double distance = Math.sqrt(Math.pow((gp.objects[gp.currentLevel][gp.currentRoom][i].worldX - gp.player.worldX - gp.player.solidArea.width / 2),2) + Math.pow(gp.objects[gp.currentLevel][gp.currentRoom][i].worldY - gp.player.worldY - gp.player.solidArea.height / 2,2));

                if(distance <= 40){
                    canTake = true;
                    g2.drawImage(takeImage,x, y - 20, takeImage.getWidth(), takeImage.getHeight(), null);

                }

            }
        }
    }

    public void drawPauseMenu(Graphics2D g2){

        g2.setColor(new Color(100,100,100,180));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        g2.setFont(new Font("Arial",Font.BOLD,30));

        int x = 0;
        int y = 25;

        String[] lines = {"Continue","Controls","Options","Quit"};

        for(int i = 0; i < lines.length; i++){

            g2.setColor(new Color(40,40,40));
            g2.fillRect(x - 2,y - 2,200,50);

            g2.setColor(Color.darkGray);
            g2.fillRect(x,y,200,50);

            g2.setColor(Color.black);
            g2.drawString(lines[i],x + 13,y + 33);

            g2.setColor(Color.white);
            g2.drawString(lines[i],x + 15,y + 35);

            y+= 75;

        }

        g2.drawString("<", x + 170, 60 + (75 * gp.handler.pauseSelector));

        g2.setFont(new Font("Arial",Font.BOLD,70));

        g2.drawString("GAME", 280, 100);

    }

}
