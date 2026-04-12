package Main;

import Entity.BasicSword;

public class Assets {

    GamePanel gp;

    public Assets(GamePanel gp){this.gp = gp;}

    public void setObj(){

        gp.objects[1][0] = new BasicSword(gp);
        gp.objects[1][0].worldX = gp.tileSize * 4;
        gp.objects[1][0].worldY = gp.tileSize * 3;
    }
}
