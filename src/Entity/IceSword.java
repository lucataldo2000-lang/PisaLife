package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class IceSword extends Entity{

    public IceSword(GamePanel gp){
        super(gp);

        name = "Ice Sword";
        frozen = true;
        frozenTime = 60;
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
        damage = 20;
        animated = true;
        try{
            for(int i = 0; i < objImage.length; i++){
                objImage[i] = ImageIO.read(getClass().getResourceAsStream("/ObjectsTextures/iceSword" + i + ".png"));
            }
        }catch(IOException e){e.printStackTrace();}
    }
}
