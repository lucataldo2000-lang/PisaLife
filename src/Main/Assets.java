package Main;

import Entity.BasicSword;
import Entity.TorchBlock;

public class Assets {

    GamePanel gp;

    public Assets(GamePanel gp){this.gp = gp;}

    public void setObj(){

        gp.objects[1][0] = new BasicSword(gp);
        gp.objects[1][0].worldX = gp.tileSize * 4;
        gp.objects[1][0].worldY = gp.tileSize * 3;

        gp.objects[1][4] = new TorchBlock(gp);
        gp.objects[1][4].worldX = gp.tileSize * 3;
        gp.objects[1][4].worldY = gp.tileSize;

        gp.objects[1][5] = new TorchBlock(gp);
        gp.objects[1][5].worldX = gp.tileSize * 8;
        gp.objects[1][5].worldY = gp.tileSize;
    }
}
