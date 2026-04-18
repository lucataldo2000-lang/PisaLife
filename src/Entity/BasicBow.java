package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class BasicBow extends Entity {

    public BasicBow(GamePanel gp){
        super(gp);

        name = "Basic Bow";
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
                objImage[i] = ImageIO.read(getClass().getResourceAsStream("/ObjectsTextures/bow" + i + ".png"));
            }
        }catch(IOException e){e.printStackTrace();}
    }
}
