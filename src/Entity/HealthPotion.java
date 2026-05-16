package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class HealthPotion extends Entity {

    public HealthPotion(GamePanel gp){
        super(gp);

        name = "Health Potion";
        stackable = false;
        amount = 1;
        type = objectType;
        objType = consumable;
        collisionOn = true;
        solidArea = new Rectangle(32,32);
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;
        life = 75;
        animated = true;
        try{
            for(int i = 0; i < objImage.length; i++){
                objImage[i] = ImageIO.read(getClass().getResourceAsStream("/ObjectsTextures/healthPotion" + i + ".png"));
            }
        }catch(IOException e){e.printStackTrace();}
    }
}
