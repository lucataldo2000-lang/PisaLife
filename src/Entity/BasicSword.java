package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class BasicSword extends Entity{

    public BasicSword(GamePanel gp){
        super(gp);

        name = "Iron Sword";
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
            image = ImageIO.read(getClass().getResourceAsStream("/ObjectsTextures/BasicSword.png"));
        }catch(IOException e){e.printStackTrace();}
    }
}
