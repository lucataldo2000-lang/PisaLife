package Main;

import Entity.Entity;

public class Collision {

    GamePanel gp;

    public Collision(GamePanel gp){this.gp = gp;}

    public void checkTile(Entity player){

        int leftX = player.worldX + player.solidArea.x;
        int rightX = player.worldX + player.solidArea.x + player.solidArea.width;
        int topY = player.worldY + player.solidArea.y;
        int bottomY = player.worldY + player.solidArea.y + player.solidArea.height;

        int leftTile = leftX / gp.tileSize;
        int rightTile = rightX / gp.tileSize;
        int topTile = topY / gp.tileSize;
        int bottomTile = bottomY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (player.direction){
            case "up","up-left","up-right" -> {

                topTile = (topY - player.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.tileNum[gp.currentLevel][leftTile][topTile];
                tileNum2 = gp.tileManager.tileNum[gp.currentLevel][rightTile][topTile];

                if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision){
                    player.collisionOn = true;
                }
            }
            case "down","down-left","down-right" -> {

                bottomTile = (bottomY + player.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.tileNum[gp.currentLevel][leftTile][bottomTile];
                tileNum2 = gp.tileManager.tileNum[gp.currentLevel][rightTile][bottomTile];

                if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision){
                    player.collisionOn = true;
                }
            }
            case "right" -> {

                rightTile = (rightX + player.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.tileNum[gp.currentLevel][rightTile][topTile];
                tileNum2 = gp.tileManager.tileNum[gp.currentLevel][rightTile][bottomTile];

                if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision){
                    player.collisionOn = true;
                }
            }
            case "left" -> {

                leftTile = (leftX  - player.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.tileNum[gp.currentLevel][leftTile][topTile];
                tileNum2 = gp.tileManager.tileNum[gp.currentLevel][leftTile][bottomTile];

                if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision){
                    player.collisionOn = true;
                }
            }
        }
    }
}
