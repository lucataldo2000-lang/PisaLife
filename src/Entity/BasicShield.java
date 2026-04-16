package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class BasicShield extends Entity{

    public BasicShield(GamePanel gp){
        super(gp);

        name = "Basic Staff";
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
                objImage[i] = ImageIO.read(getClass().getResourceAsStream("/ObjectsTextures/BasicShield" + i + ".png"));
            }
        }catch(IOException e){e.printStackTrace();}
    }
}
