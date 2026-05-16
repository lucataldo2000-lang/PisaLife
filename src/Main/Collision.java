package Main;

import Entity.Entity;

public class Collision {

    GamePanel gp;

    public Collision(GamePanel gp){this.gp = gp;}

    public boolean checkTile(Entity player){

        boolean check = false;

        int leftX = player.worldX + player.solidArea.x;
        int rightX = player.worldX + player.solidArea.x + player.solidArea.width;
        int topY = player.worldY + player.solidArea.y;
        int bottomY = player.worldY + player.solidArea.y + player.solidArea.height;

        int leftTile = leftX / gp.tileSize;
        int rightTile = rightX / gp.tileSize;
        int topTile = topY / gp.tileSize;
        int bottomTile = bottomY / gp.tileSize;

        if (rightTile >= gp.maxWorldCol) rightTile = 5;
        if (bottomTile >= gp.maxWorldRow) bottomTile = 5;

        int tileNum1, tileNum2;

        switch (player.direction){
            case "up","up-left","up-right" -> {

                topTile = (topY - player.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.tileNum[gp.currentLevel][gp.currentRoom][leftTile][topTile];
                tileNum2 = gp.tileManager.tileNum[gp.currentLevel][gp.currentRoom][rightTile][topTile];

                if(gp.tileManager.tiles[tileNum1] != null && gp.tileManager.tiles[tileNum2] != null){
                    if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision){
                        player.collisionOn = true;
                        check = true;

                    }
                    if((gp.tileManager.tiles[tileNum1].mobCollision || gp.tileManager.tiles[tileNum2].mobCollision) && player.type == player.monsterType){
                        player.collisionOn = true;
                        check = true;
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
                        check = true;
                    }
                    if((gp.tileManager.tiles[tileNum1].mobCollision || gp.tileManager.tiles[tileNum2].mobCollision) && player.type == player.monsterType){
                        player.collisionOn = true;
                        check = true;
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
                        check = true;
                    }
                    if((gp.tileManager.tiles[tileNum1].mobCollision || gp.tileManager.tiles[tileNum2].mobCollision) && player.type == player.monsterType){
                        player.collisionOn = true;
                        check = true;
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
                        check = true;
                    }
                    if((gp.tileManager.tiles[tileNum1].mobCollision || gp.tileManager.tiles[tileNum2].mobCollision) && player.type == player.monsterType){
                        player.collisionOn = true;
                        check = true;
                    }
                }

            }
        }

        return check;
    }

    public int checkObject(Entity player){

        int objIndex = 999;

        for(int i = 0; i < gp.objects[gp.currentLevel][gp.currentRoom].length; i++){

           if(gp.objects[gp.currentLevel][gp.currentRoom][i] != null && gp.objects[gp.currentLevel][gp.currentRoom][i].collisionOn && !gp.objects[gp.currentLevel][gp.currentRoom][i].effect){
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

               if(player.solidArea.intersects(gp.objects[gp.currentLevel][gp.currentRoom][i].solidArea)){
                   objIndex = i;

                   if(gp.objects[gp.currentLevel][gp.currentRoom][i].decoration){
                       player.collisionOn = true;
                   }
               }

               player.solidArea.x = player.solidAreaX;
               player.solidArea.y = player.solidAreaY;

               gp.objects[gp.currentLevel][gp.currentRoom][i].solidArea.x = gp.objects[gp.currentLevel][gp.currentRoom][i].solidAreaX;
               gp.objects[gp.currentLevel][gp.currentRoom][i].solidArea.y = gp.objects[gp.currentLevel][gp.currentRoom][i].solidAreaY;

           }
        }


        return objIndex;
    }

    public int checkMonster(Entity entity){

        int Index = 999;

        for(int i = 0; i < gp.monsters[gp.currentLevel][gp.currentRoom].length; i++){

            if(gp.monsters[gp.currentLevel][gp.currentRoom][i] != null){
                entity.solidArea.x = entity.solidArea.x + entity.worldX;
                entity.solidArea.y = entity.solidArea.y + entity.worldY;

                gp.monsters[gp.currentLevel][gp.currentRoom][i].solidArea.x = gp.monsters[gp.currentLevel][gp.currentRoom][i].worldX + gp.monsters[gp.currentLevel][gp.currentRoom][i].solidArea.x;
                gp.monsters[gp.currentLevel][gp.currentRoom][i].solidArea.y = gp.monsters[gp.currentLevel][gp.currentRoom][i].worldY + gp.monsters[gp.currentLevel][gp.currentRoom][i].solidArea.y;

                switch (entity.direction){
                    case "up","up-left","up-right" -> entity.solidArea.y -= entity.speed;
                    case "down","down-left","down-right" -> entity.solidArea.y += entity.speed;
                    case "right" -> entity.solidArea.x += entity.speed;
                    case "left" -> entity.solidArea.x -= entity.speed;
                }

                if(entity.solidArea.intersects(gp.monsters[gp.currentLevel][gp.currentRoom][i].solidArea)){
                    Index = i;
                }

                entity.solidArea.x = entity.solidAreaX;
                entity.solidArea.y = entity.solidAreaY;

                gp.monsters[gp.currentLevel][gp.currentRoom][i].solidArea.x = gp.monsters[gp.currentLevel][gp.currentRoom][i].solidAreaX;
                gp.monsters[gp.currentLevel][gp.currentRoom][i].solidArea.y = gp.monsters[gp.currentLevel][gp.currentRoom][i].solidAreaY;

            }
        }


        return Index;
    }
    public boolean checkPlayer(Entity entity){

        boolean canDamage = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction){
            case "up" -> {
                entity.attackArea.x = entity.worldX + entity.attackAreaX + entity.solidAreaX;
                entity.attackArea.y = entity.worldY;
            }
            case "down" -> {
                entity.attackArea.x = entity.worldX + entity.attackAreaX;
                entity.attackArea.y = entity.worldY + entity.attackAreaY;
            }
            case "left" -> {
                entity.attackArea.x = entity.worldX - entity.attackAreaX;
                entity.attackArea.y = entity.worldY + entity.attackAreaY;
            }
            case "right" -> {
                entity.attackArea.x = entity.worldX + entity.attackAreaX +  entity.solidArea.width;
                entity.attackArea.y = entity.worldY + entity.attackAreaY;
            }
        }
        if(entity.attackArea.intersects(gp.player.solidArea)){
            canDamage = true;
        }

        entity.attackArea.x = entity.attackAreaX;
        entity.attackArea.y = entity.attackAreaY;

        gp.player.solidArea.x = gp.player.solidAreaX;
        gp.player.solidArea.y = gp.player.solidAreaY;

        return canDamage;

    }

    public int checkDamage(Entity player){

        int monsterIndex = 999;

        for(int i = 0; i < gp.monsters[gp.currentLevel][gp.currentRoom].length; i++){

            if(gp.monsters[gp.currentLevel][gp.currentRoom][i] != null){
                Entity monsters = gp.monsters[gp.currentLevel][gp.currentRoom][i];

                monsters.solidArea.x = monsters.worldX + monsters.solidArea.x;
                monsters.solidArea.y = monsters.worldY + monsters.solidArea.y;

                switch (player.direction){
                    case "up","up-left","up-right" -> {
                        player.attackArea.x = player.worldX + player.attackAreaY;
                        player.attackArea.y = player.worldY;
                    }
                    case "down","down-left","down-right" -> {
                        player.attackArea.x = player.worldX + player.attackAreaY;
                        player.attackArea.y = player.worldY + player.attackAreaX + player.solidArea.width;
                    }
                    case "left" -> {
                        player.attackArea.x = player.worldX - player.attackAreaX;
                        player.attackArea.y = player.worldY + player.attackAreaY;
                    }
                    case "right" -> {
                        player.attackArea.x = player.worldX + player.attackAreaX;
                        player.attackArea.y = player.worldY + player.attackAreaY;
                    }
                }

                if(player.attackArea.intersects(monsters.solidArea)){
                    monsterIndex = i;
                }

                monsters.solidArea.x = monsters.solidAreaX;
                monsters.solidArea.y = monsters.solidAreaY;

                player.attackArea.x = player.attackAreaX;
                player.attackArea.y = player.attackAreaY;
            }
        }

        return monsterIndex;
    }
}
