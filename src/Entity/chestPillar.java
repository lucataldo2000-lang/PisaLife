package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class chestPillar extends Entity{

    public chestPillar(GamePanel gp){
        super(gp);

        name = "Chest Pillar";
        decoration = true;
        collisionOn = true;

        solidArea = new Rectangle(20,20);
        solidArea.x = 6;
        solidArea.y = 6;
        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;

        try{
            objImage[0] = ImageIO.read(getClass().getResourceAsStream("/DecorationsTextures/chestPillar.png"));
        }catch(IOException e){e.printStackTrace();}
    }
}
