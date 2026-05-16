package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class LongSword extends Entity{

    public LongSword(GamePanel gp){
        super(gp);

        name = "Long Sword";
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
        damage = 35;
        animated = true;
        try{
            for(int i = 0; i < objImage.length; i++){
                objImage[i] = ImageIO.read(getClass().getResourceAsStream("/ObjectsTextures/longSword" + i + ".png"));
            }
        }catch(IOException e){e.printStackTrace();}
    }
}
