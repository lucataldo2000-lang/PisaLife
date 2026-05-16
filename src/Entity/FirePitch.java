package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class FirePitch extends Entity {
    public FirePitch(GamePanel gp){
        super(gp);

        name = "FirePitch";
        decoration = true;
        collisionOn = true;
        animated = true;

        solidArea = new Rectangle(40,40);
        solidArea.x = 10;
        solidArea.y = 10;

        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;
        try{
            for(int i = 0; i < objImage.length; i++){
                objImage[i] = ImageIO.read(getClass().getResourceAsStream("/DecorationsTextures/firepitch" + i + ".png"));
            }
        }catch(IOException e){e.printStackTrace();}
    }
}
