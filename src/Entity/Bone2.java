package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Bone2 extends Entity {
    public Bone2(GamePanel gp){
        super(gp);

        name = "Bone2";
        decoration = true;

        try{
            objImage[0] = ImageIO.read(getClass().getResourceAsStream("/DecorationsTextures/Bone2.png"));
        }catch(IOException e){e.printStackTrace();}
    }
}
