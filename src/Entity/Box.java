package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Box extends Entity {
    public Box(GamePanel gp){
        super(gp);

        name = "Box";
        decoration = true;
        collisionOn = true;

        solidArea = new Rectangle(20,20);
        solidArea.x = 6;
        solidArea.y = 6;
        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;

        try{
            objImage[0] = ImageIO.read(getClass().getResourceAsStream("/DecorationsTextures/Box.png"));
        }catch(IOException e){e.printStackTrace();}
    }
}
