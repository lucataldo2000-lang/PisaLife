package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Barrel extends Entity{

    public Barrel(GamePanel gp){
        super(gp);

        name = "Barrel";
        decoration = true;
        collisionOn = true;

        solidArea = new Rectangle(20,20);
        solidArea.x = 6;
        solidArea.y = 6;


        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;

        try{
            objImage[0] = ImageIO.read(getClass().getResourceAsStream("/DecorationsTextures/Barrel.png"));
        }catch(IOException e){e.printStackTrace();}
    }
}
