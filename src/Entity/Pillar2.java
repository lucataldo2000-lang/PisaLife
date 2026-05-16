package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Pillar2 extends Entity{

    public Pillar2(GamePanel gp){
        super(gp);

        name = "Pillar";
        decoration = true;
        collisionOn = false;

        solidArea = new Rectangle(20,20);
        solidArea.x = 6;
        solidArea.y = 6;

        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;
        try{
            objImage[0] = ImageIO.read(getClass().getResourceAsStream("/DecorationsTextures/pillar1.png"));
        }catch(IOException e){e.printStackTrace();}
    }
}
