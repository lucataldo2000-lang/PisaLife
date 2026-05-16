package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Bone1 extends Entity {
    public Bone1(GamePanel gp){
        super(gp);

        name = "Bone1";
        decoration = true;

        try{
            objImage[0] = ImageIO.read(getClass().getResourceAsStream("/DecorationsTextures/Bone1.png"));
        }catch(IOException e){e.printStackTrace();}
    }
}
