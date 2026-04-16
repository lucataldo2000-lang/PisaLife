package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class BasicStaff extends Entity{

    public BasicStaff(GamePanel gp){
        super(gp);

        name = "Basic Staff";
        stackable = false;
        amount = 1;
        type = objectType;
        objType = weapon;
        collisionOn = true;
        solidArea = new Rectangle(32,32);
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;

        try{
            for(int i = 0; i < objImage.length; i++){
                objImage[i] = ImageIO.read(getClass().getResourceAsStream("/ObjectsTextures/BasicStaff" + i + ".png"));
            }
        }catch(IOException e){e.printStackTrace();}
    }
}
