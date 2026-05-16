package Main;

import Entity.Boss;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GUI {

    GamePanel gp;
    BufferedImage image1,image2,image3,inventoryImage,slotImage,takeImage,healthBar,background;
    BufferedImage[] selectedSlot = new BufferedImage[5];
    public boolean canTake;
    public boolean acceptTutorial;
    public int tutorialPhase = 0;

    public GUI(GamePanel gp){
        this.gp = gp;

        try{
            image1 = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior0.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizard0.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer0.png"));
            inventoryImage = ImageIO.read(getClass().getResourceAsStream("/GUITextures/inventory.png"));
            slotImage = ImageIO.read(getClass().getResourceAsStream("/GUITextures/slot.png"));
            takeImage = ImageIO.read(getClass().getResourceAsStream("/GUITextures/takeObject.png"));
            healthBar = ImageIO.read(getClass().getResourceAsStream("/GUITextures/HealthBar.png"));
            background = ImageIO.read(getClass().getResourceAsStream("/GUITextures/background.png"));

            for(int i = 0; i < selectedSlot.length;i++){
                selectedSlot[i] = ImageIO.read(getClass().getResourceAsStream("/GUITextures/selectedSlot" + i +".png"));
            }
        }catch(IOException e){e.printStackTrace();}
    }

    public void draw(Graphics2D g2){

        switch(gp.gameState){
            case 0 -> {
                drawInventory(g2);
                drawTakeObject(g2);
                drawPlayerGUI(g2);
            }
            case 1 -> drawTitle(g2);
            case 2 -> drawDeathScreen(g2);
            case 3 -> drawVictoryScreen(g2);
            case 4 -> drawClassChooser(g2);
            case 5 -> drawPauseMenu(g2);
            case 6 -> drawTutorialScreen(g2);
        }
    }

    public void drawTitle(Graphics2D g2){

        int x = gp.tileSize;
        int y = gp.tileSize * 2;

        g2.drawImage(background,0,0, 530,350,null);

        g2.setFont(new Font("Arial",Font.BOLD, 40));
        g2.setColor(new Color(124, 46, 46));

        g2.drawString("PIXEL", x, y - 20);

        g2.drawString("ADVENTURE", x + 60, y + 30);

        g2.setColor(new Color(189, 90, 90));

        g2.drawString("PIXEL", x + 2, y - 18);

        g2.drawString("ADVENTURE", x + 62, y + 32);

        g2.setFont(new Font("Arial",Font.BOLD, 30));

        y += 100;

        int ogY = y;

        String[] lines = {"New Game", "Continue", "Quit"};

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

            BufferedImage img;

            if(gp.handler.inventorySelector == i){
                img = selectedSlot[i];
            }
            else{
                img = slotImage;
            }

            g2.drawImage(img,x,y,img.getWidth(),img.getHeight(),null);
            x += img.getWidth() + 5;
        }

        BufferedImage img = null;

        if(gp.handler.inventorySelector > 1){
            switch (gp.handler.inventorySelector){
                case 2 -> img = selectedSlot[2];
                case 3 -> img = selectedSlot[3];
                case 4 -> img = selectedSlot[4];
            }
        }
        else{
            img = inventoryImage;
        }

        g2.drawImage(img,x,y,slotImage.getWidth() * 3,slotImage.getHeight() + 2,null);


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

                if(gp.player.inventory[i].objType == gp.player.consumable){
                    y -= 3;
                    x += 1;
                }

                g2.drawImage(gp.player.inventory[i].objImage[0],x,y,gp.player.inventory[i].objImage[0].getWidth(),gp.player.inventory[i].objImage[0].getHeight(),null);
            }
        }

    }

    public void drawTakeObject(Graphics2D g2){

        canTake = false;

        for(int i = 0; i <  gp.objects[gp.currentLevel][gp.currentRoom].length; i++){
            if(gp.objects[gp.currentLevel][gp.currentRoom][i] != null && !gp.objects[gp.currentLevel][gp.currentRoom][i].decoration && !gp.objects[gp.currentLevel][gp.currentRoom][i].effect){


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


        String[] lines = {"Continue","Controls","Quit"};

        for(int i = 0; i < lines.length; i++){

            g2.setColor(new Color(40,40,40));
            g2.fillRect(x - 2,y - 2,200,50);

            g2.setColor(Color.darkGray);
            g2.fillRect(x,y,200,50);

            g2.setColor(new Color(124, 46, 46));
            g2.drawString(lines[i],x + 13,y + 33);

            g2.setColor(new Color(189, 90, 90));
            g2.drawString(lines[i],x + 14,y + 34);

            y+= 75;

        }
        g2.drawString("<", x + 170, 60 + (75 * gp.handler.pauseSelector));

        g2.setFont(new Font("Arial",Font.BOLD,40));

        g2.setColor(new Color(124, 46, 46));

        g2.drawString("PIXEL", 240, 70);

        g2.drawString("ADVENTURE", 240, 120);

        g2.setColor(new Color(189, 90, 90));

        g2.drawString("PIXEL", 242, 72);

        g2.drawString("ADVENTURE", 242, 122);
    }

    public void drawPlayerGUI(Graphics2D g2){

        int x = 15;
        int y = 15;

        g2.setFont(new Font("Arial", Font.BOLD,15));

        g2.setColor(new Color(207, 69, 64));
        g2.fillRect(x + 60,y + 15,(int) Math.round((gp.player.life / gp.player.maxLife) * 113),30);

        g2.drawImage(healthBar,x,y,180,60,null);

        g2.setColor(Color.white);
        g2.drawString(Math.round(gp.player.life) + " / " + gp.player.maxLife, x + 85, y + 35);

        y += 90;

        g2.setColor(new Color(83, 182, 69));
        g2.setFont(new Font("Arial", Font.BOLD,20));

        g2.drawString("Level: " + gp.player.level,x,y);

        y += 30;

        g2.drawString(gp.player.exp + " / " + gp.player.maxExp,x,y);

        if(gp.currentRoom == 0 && gp.monsters[gp.currentLevel][gp.currentRoom][0] != null){

            g2.setColor(Color.red);
            g2.fillRoundRect(120,5,(int) Math.round(gp.monsters[gp.currentLevel][gp.currentRoom][0].life / 16.66),15,8,8);

            g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(3));

            g2.drawRoundRect(120,5,300,15,8,8);
        }
    }

    public void drawDeathScreen(Graphics2D g2){

        int x;
        int y;

        g2.setColor(Color.black);
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        g2.setFont(new Font("Arial",Font.BOLD, 50));

        g2.setColor(Color.red);
        g2.drawString("YOU DIED", 110, 60);

        g2.setFont(new Font("Arial",Font.BOLD, 30));

        g2.setColor(Color.white);

        y = 250;
        x = 85;
        g2.drawString("RETRY", x, y);

        x = 300;
        g2.drawString("QUIT",x,y);

        y = 210;

        switch (gp.handler.deathSelector){
            case 0 -> x = 120;
            case 1 -> x = 335;
        }

        g2.setColor(Color.white);

        g2.drawString("v", x,y);

    }

    public void drawVictoryScreen(Graphics2D g2){

        g2.setColor(new Color(100,100,100,180));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        g2.setColor(Color.yellow);
        g2.setFont(new Font("Arial",Font.BOLD,50));
        g2.drawString("YOU WON!", 140,160);
    }

    public void drawTutorialScreen(Graphics2D g2){
        g2.setColor(new Color(92, 92, 92,190));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        if(!acceptTutorial){
            g2.setColor(new Color(37, 37, 37));
            g2.setStroke(new BasicStroke(6));
            g2.drawRoundRect(185,100,150,100,8,8);

            g2.setColor(Color.white);
            g2.setFont(new Font("Arial",Font.BOLD,15));
            g2.drawString("Would you like to", 200,123);
            g2.drawString("follow the tutorial?", 195,145);

            g2.setFont(new Font("Arial",Font.BOLD,20));

            g2.drawString("YES",200,183);
            g2.drawString("NO",285,183);

            g2.setStroke(new BasicStroke(2));

            g2.drawRoundRect(195 + gp.handler.tutorialSelector * 80,160,50,30,8,8);
        }
        else{
           if(tutorialPhase == 1){
               g2.setStroke(new BasicStroke(8));
               g2.setColor(new Color(37, 37, 37));
               g2.drawRoundRect(160,40,200,240,16,16);

               g2.drawImage(gp.player.down[0][1], 190, 35, 150,150, null);

               g2.setColor(Color.white);
               g2.drawString("Use WASD to", 200,210);
               g2.drawString("move the player", 185,240);
           }
           if(tutorialPhase == 2){
               BufferedImage image = null;

               g2.setColor(new Color(37, 37, 37));
               g2.drawRoundRect(60,40,390,240,16,16);

               g2.drawRect(190,40,130,240);

               g2.drawImage(gp.player.downAttack[0][0][0], 65, 60, 130,170, null);

               g2.setFont(new Font("Arial",Font.BOLD,16));
               g2.setColor(Color.white);
               g2.drawString("LMB to use", 83,210);
               g2.drawString("your first", 88,235);
               g2.drawString("weapon", 95,260);

               try{
                   image = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/fireball06.png"));
               }catch(IOException e){}

               g2.drawImage(image, 195, 60, 120,120, null);

               g2.setFont(new Font("Arial",Font.BOLD,16));
               g2.setColor(Color.white);
               g2.drawString("RMB to use", 213,210);
               g2.drawString("your second", 208,235);
               g2.drawString("weapon", 225,260);

               try{
                   image = ImageIO.read(getClass().getResourceAsStream("/ObjectsTextures/healthPotion2.png"));
               }catch(IOException e){}

               g2.drawImage(image, 335, 60, 100,100, null);

               g2.setFont(new Font("Arial",Font.BOLD,16));
               g2.setColor(Color.white);
               g2.drawString("Press E to", 343,210);
               g2.drawString("use a ", 358,235);
               g2.drawString("consumable", 335,260);
           }
        }
    }

}
