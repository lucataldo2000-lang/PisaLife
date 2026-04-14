package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GUI {

    GamePanel gp;
    BufferedImage image1,image2,image3,inventoryImage,slotImage;

    public GUI(GamePanel gp){
        this.gp = gp;

        try{
            image1 = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/basewarrior0.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizard0.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer0.png"));
            inventoryImage = ImageIO.read(getClass().getResourceAsStream("/GUITextures/inventory.png"));
            slotImage = ImageIO.read(getClass().getResourceAsStream("/GUITextures/slot.png"));
        }catch(IOException e){e.printStackTrace();}
    }

    public void draw(Graphics2D g2){

        switch(gp.gameState){
            case 0 -> drawInventory(g2);
            case 1 -> drawTitle(g2);
            case 4 -> drawClassChooser(g2);
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

        int x = 15;
        int y = gp.screenHeight - 65;

        for(int i = 0; i < 2; i++){
            g2.drawImage(slotImage,x,y,slotImage.getWidth(),slotImage.getHeight(),null);
            x += slotImage.getWidth() + 5;
        }

        g2.drawImage(inventoryImage,x,y,slotImage.getWidth() * 3,slotImage.getHeight() + 2,null);

        x = 23;
        y = 263;

        for(int i = 0; i < gp.player.inventory.length; i++){
            if(gp.player.inventory[i] != null){

                switch(i){
                    case 1 -> x = 78;
                    case 2 -> {x = 139; y = 265;}
                    case 3 ->  {x = 183;y = 265;}
                    case 4 -> {x = 228;y = 265;}
                }

                g2.drawImage(gp.player.inventory[i].image,x,y,gp.player.inventory[i].image.getWidth(),gp.player.inventory[i].image.getHeight(),null);
            }
        }

    }

}
