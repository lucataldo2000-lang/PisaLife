package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class BasicSword extends Entity{

    public BasicSword(GamePanel gp){
        super(gp);

        name = "Iron Sword";
        stackable = false;
        amount = 1;
        type = objectType;
        objType = weapon;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/ObjectsTextures/BasicSword.png"));
        }catch(IOException e){e.printStackTrace();}
    }
}
