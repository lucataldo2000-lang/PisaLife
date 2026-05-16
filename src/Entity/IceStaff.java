package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class IceStaff extends Entity{

    public IceStaff(GamePanel gp){
        super(gp);

        name = "Ice Staff";
        stackable = false;
        frozen = true;
        frozenTime = 20;
        amount = 1;
        damage = 10;
        type = objectType;
        objType = weapon;
        collisionOn = true;
        solidArea = new Rectangle(32,32);
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;
        animated = true;
        try{
            for(int i = 0; i < objImage.length; i++){
                objImage[i] = ImageIO.read(getClass().getResourceAsStream("/ObjectsTextures/iceStaff" + i + ".png"));
            }
        }catch(IOException e){e.printStackTrace();}
    }
}
