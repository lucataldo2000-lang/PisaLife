package Main;

import Entity.*;

public class Assets {

    GamePanel gp;

    public Assets(GamePanel gp){this.gp = gp;}

    public void setObj(){

        gp.objects[1][1][3] = new TorchBlock(gp);
        gp.objects[1][1][3].worldX = gp.tileSize * 17;
        gp.objects[1][1][3].worldY = gp.tileSize * 8;

        gp.objects[1][1][4] = new TorchBlock(gp);
        gp.objects[1][1][4].worldX = gp.tileSize * 3;
        gp.objects[1][1][4].worldY = gp.tileSize;

        gp.objects[1][1][5] = new TorchBlock(gp);
        gp.objects[1][1][5].worldX = gp.tileSize * 8;
        gp.objects[1][1][5].worldY = gp.tileSize;

        gp.objects[1][2][0] = new TorchBlock(gp);
        gp.objects[1][2][0].worldX = gp.tileSize * 3;
        gp.objects[1][2][0].worldY = gp.tileSize * 8;

        gp.objects[1][2][1] = new TorchBlock(gp);
        gp.objects[1][2][1].worldX = gp.tileSize * 7;
        gp.objects[1][2][1].worldY = gp.tileSize;

        gp.objects[1][2][2] = new TorchBlock(gp);
        gp.objects[1][2][2].worldX = gp.tileSize * 16;
        gp.objects[1][2][2].worldY = gp.tileSize;

        gp.objects[1][2][3] = new TorchBlock(gp);
        gp.objects[1][2][3].worldX = gp.tileSize * 14;
        gp.objects[1][2][3].worldY = gp.tileSize * 11;
    }
}
