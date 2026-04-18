package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class BasicOrb extends Entity{

    public BasicOrb(GamePanel gp){
        super(gp);

        name = "Basic Orb";
        stackable = false;
        amount = 1;
        type = objectType;
        objType = shield;
        collisionOn = true;
        solidArea = new Rectangle(32,32);
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;

        try{
            for(int i = 0; i < objImage.length; i++){
                objImage[i] = ImageIO.read(getClass().getResourceAsStream("/ObjectsTextures/orbs" + i + ".png"));
            }
        }catch(IOException e){e.printStackTrace();}
    }
}
