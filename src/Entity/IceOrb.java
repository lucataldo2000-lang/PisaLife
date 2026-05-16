package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class IceOrb extends Entity{

    public IceOrb(GamePanel gp){
        super(gp);

        name = "Ice Orb";
        stackable = false;
        amount = 1;
        frozenTime = 40;
        type = objectType;
        objType = shield;
        collisionOn = true;
        damage = 15;
        solidArea = new Rectangle(32,32);
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;
        animated = true;
        try{
            for(int i = 0; i < objImage.length; i++){
                objImage[i] = ImageIO.read(getClass().getResourceAsStream("/ObjectsTextures/iceorb" + i + ".png"));
            }
        }catch(IOException e){e.printStackTrace();}
    }
}
