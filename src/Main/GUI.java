package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GUI {

    GamePanel gp;
    BufferedImage image1,image2,image3;

    public GUI(GamePanel gp){
        this.gp = gp;
    }

    public void draw(Graphics2D g2){

        switch(gp.gameState){
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

        try{
            image1 = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/basewarrior0.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizard0.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer0.png"));
        }catch(IOException e){e.printStackTrace();}

        BufferedImage[] images = {image1,image2,image3};

        y += 50;
        x -= 50;

        for(BufferedImage img : images){
            g2.drawImage(img,x,y,gp.tileSize * 4,gp.tileSize * 4,null);

            x += gp.tileSize * 4;
        }

        x = 75;

        g2.drawString("v",x + ((gp.tileSize * 5) * gp.handler.classSelector),y - 10);

        y += gp.tileSize * 5;

        g2.setFont(new Font("Arial",Font.BOLD, 20));

        g2.drawString("Warrior",x,y);

        x += gp.tileSize * 4 + 6;

        g2.drawString("Wizard",x,y);

        x += gp.tileSize * 4;

        g2.drawString("Ranger",x,y);

    }

}
