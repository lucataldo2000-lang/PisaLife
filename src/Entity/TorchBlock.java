package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class TorchBlock extends Entity{

    public TorchBlock(GamePanel gp){
        super(gp);

        decoration = true;

        try{
            for(int i = 0; i < objImage.length; i++){
                objImage[i] = ImageIO.read(getClass().getResourceAsStream("/TilesTextures/Torch" + i + ".png"));
            }
        }catch(IOException e){e.printStackTrace();}
    }
}
