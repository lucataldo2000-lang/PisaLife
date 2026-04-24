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

    public void setMonsters(){
        gp.monsters[1][1][0] = new Skeleton(gp);
        gp.monsters[1][1][0].worldX = gp.tileSize * 7;
        gp.monsters[1][1][0].worldY = gp.tileSize * 3;

        gp.monsters[1][1][1] = new Skeleton(gp);
        gp.monsters[1][1][1].worldX = gp.tileSize * 10;
        gp.monsters[1][1][1].worldY = gp.tileSize * 7;

        gp.monsters[1][1][2] = new Skeleton(gp);
        gp.monsters[1][1][2].worldX = gp.tileSize * 15;
        gp.monsters[1][1][2].worldY = gp.tileSize * 9;

        gp.monsters[1][1][3] = new Skeleton(gp);
        gp.monsters[1][1][3].worldX = gp.tileSize * 4;
        gp.monsters[1][1][3].worldY = gp.tileSize * 4;
    }
}
