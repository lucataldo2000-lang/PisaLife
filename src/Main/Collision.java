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
                tileNum1 = gp.tileManager.tileNum[gp.currentLevel][gp.currentRoom][leftTile][topTile];
                tileNum2 = gp.tileManager.tileNum[gp.currentLevel][gp.currentRoom][rightTile][topTile];

                if(gp.tileManager.tiles[tileNum1] != null && gp.tileManager.tiles[tileNum2] != null){
                    if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision){
                        player.collisionOn = true;
                    }
                }

            }
            case "down","down-left","down-right" -> {

                bottomTile = (bottomY + player.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.tileNum[gp.currentLevel][gp.currentRoom][leftTile][bottomTile];
                tileNum2 = gp.tileManager.tileNum[gp.currentLevel][gp.currentRoom][rightTile][bottomTile];

                if(gp.tileManager.tiles[tileNum1] != null && gp.tileManager.tiles[tileNum2] != null){
                    if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision){
                        player.collisionOn = true;
                    }
                }

            }
            case "right" -> {

                rightTile = (rightX + player.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.tileNum[gp.currentLevel][gp.currentRoom][rightTile][topTile];
                tileNum2 = gp.tileManager.tileNum[gp.currentLevel][gp.currentRoom][rightTile][bottomTile];

                if(gp.tileManager.tiles[tileNum1] != null && gp.tileManager.tiles[tileNum2] != null){

                    if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision){
                        player.collisionOn = true;
                    }
                }

            }
            case "left" -> {

                leftTile = (leftX  - player.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.tileNum[gp.currentLevel][gp.currentRoom][leftTile][topTile];
                tileNum2 = gp.tileManager.tileNum[gp.currentLevel][gp.currentRoom][leftTile][bottomTile];

                if(gp.tileManager.tiles[tileNum1] != null && gp.tileManager.tiles[tileNum2] != null){

                    if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision){
                        player.collisionOn = true;
                    }
                }

            }
        }
    }

    public int checkObject(Entity player){

        int objIndex = 999;

        for(int i = 0; i < gp.objects[gp.currentLevel][gp.currentRoom].length; i++){

           if(gp.objects[gp.currentLevel][gp.currentRoom][i] != null && !gp.objects[gp.currentLevel][gp.currentRoom][i].decoration){
               player.solidArea.x = player.solidArea.x + player.worldX;
               player.solidArea.y = player.solidArea.y + player.worldY;

               gp.objects[gp.currentLevel][gp.currentRoom][i].solidArea.x = gp.objects[gp.currentLevel][gp.currentRoom][i].worldX + gp.objects[gp.currentLevel][gp.currentRoom][i].solidArea.x;
               gp.objects[gp.currentLevel][gp.currentRoom][i].solidArea.y = gp.objects[gp.currentLevel][gp.currentRoom][i].worldY + gp.objects[gp.currentLevel][gp.currentRoom][i].solidArea.y;

               switch (player.direction){
                   case "up","up-left","up-right" -> player.solidArea.y -= player.speed;
                   case "down","down-left","down-right" -> player.solidArea.y += player.speed;
                   case "right" -> player.solidArea.x += player.speed;
                   case "left" -> player.solidArea.x -= player.speed;
               }

               if(player.solidArea.intersects(gp.objects[gp.currentLevel][gp.currentRoom][i].solidArea) && gp.objects[gp.currentLevel][gp.currentRoom][i].collisionOn){
                   objIndex = i;
               }

               player.solidArea.x = player.solidAreaX;
               player.solidArea.y = player.solidAreaY;

               gp.objects[gp.currentLevel][gp.currentRoom][i].solidArea.x = gp.objects[gp.currentLevel][gp.currentRoom][i].solidAreaX;
               gp.objects[gp.currentLevel][gp.currentRoom][i].solidArea.y = gp.objects[gp.currentLevel][gp.currentRoom][i].solidAreaY;

           }
        }


        return objIndex;
    }
}
