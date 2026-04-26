package Main;

import Entity.*;

import java.util.Random;

public class Assets {

    GamePanel gp;
    Random random = new Random();

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

        for(int i = 0; i < gp.maxRoom; i++){

            int col = 0;
            int row = 0;
            int index = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow && index < gp.maxMonsters){

                int num = gp.tileManager.tileNum[1][i][col][row];

                if(gp.tileManager.tiles[num] != null && i != 0  && num != 10 && num != 17 && !gp.tileManager.tiles[num].collision && !gp.tileManager.tiles[num].mobCollision){

                    int ran = random.nextInt(1,101);

                    if(ran >= 95){
                        Entity skeleton = new Skeleton(gp);
                        skeleton.direction = "down";
                        skeleton.worldX = col * gp.tileSize;
                        skeleton.worldY = row * gp.tileSize;

                        gp.checker.checkTile(skeleton);

                        if(!skeleton.collisionOn){
                            gp.monsters[1][i][index] = skeleton;
                            index++;
                        }
                    }

                }

                col++;

                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
        }
    }
}
