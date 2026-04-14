package Main;

import Entity.BasicSword;

public class Assets {

    GamePanel gp;

    public Assets(GamePanel gp){this.gp = gp;}

    public void setObj(){

        gp.objects[1][0] = new BasicSword(gp);
        gp.objects[1][0].worldX = gp.tileSize * 4;
        gp.objects[1][0].worldY = gp.tileSize * 3;

        gp.objects[1][1] = new BasicSword(gp);
        gp.objects[1][1].worldX = gp.tileSize * 7;
        gp.objects[1][1].worldY = gp.tileSize * 5;

        gp.objects[1][2] = new BasicSword(gp);
        gp.objects[1][2].worldX = gp.tileSize * 6;
        gp.objects[1][2].worldY = gp.tileSize * 8;

        gp.objects[1][3] = new BasicSword(gp);
        gp.objects[1][3].worldX = gp.tileSize * 9;
        gp.objects[1][3].worldY = gp.tileSize * 5;
    }
}
