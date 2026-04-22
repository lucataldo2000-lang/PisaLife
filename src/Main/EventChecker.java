package Main;

import java.awt.*;
import java.util.Random;

public class EventChecker {

    Random random = new Random();

    GamePanel gp;
    EventZone[][] teleportEvent;
    int waitTime;
    int[] room;
    int randomNumber;

    EventChecker(GamePanel gp){
        this.gp = gp;
        room = new int[gp.maxRoom];
        room[0] = 1;
        room[1] = 1;

        teleportEvent = new EventZone[gp.maxLevel][gp.maxRoom];

        setTeleport();
    }

    public void checkEvent(){

        waitTime++;

        if(waitTime >= 30){
            if(checkTeleport(1,1,17,12)){
                if(room[2] != 2 && room[2] != 3){
                    int newRoom = random.nextInt(2,4);
                    room[2] = newRoom;
                }

                waitTime = 0;

                gp.currentRoom = room[2];

                setPlayerValues(100,380, "right");
                return;
            }
            if(checkTeleport(1,2,0,12)){gp.currentRoom = room[1]; waitTime = 0;setPlayerValues(470,380, "left");}
            if(checkTeleport(1,3,0,12)){gp.currentRoom = room[1]; waitTime = 0;setPlayerValues(470,380, "left");}
        }
    }

    public void setPlayerValues(int x, int y, String direction){
        gp.player.worldX = x;
        gp.player.worldY = y;
        gp.player.direction = direction;
    }

    public boolean checkTeleport(int map, int room, int worldCol, int WorldRow){



        boolean hit = false;

        if(teleportEvent[map][room] != null && gp.currentRoom == room){
            gp.player.solidArea.x = gp.player.solidArea.x + gp.player.worldX;
            gp.player.solidArea.y = gp.player.solidArea.y + gp.player.worldY;

            teleportEvent[map][room].x = worldCol * gp.tileSize;
            teleportEvent[map][room].y = WorldRow * gp.tileSize;

            if(gp.player.solidArea.intersects(teleportEvent[map][room])){
                hit = true;
            }

            gp.player.solidArea.x = gp.player.solidAreaX;
            gp.player.solidArea.y = gp.player.solidAreaY;

            teleportEvent[map][room].x = teleportEvent[map][room].zoneX;
            teleportEvent[map][room].y = teleportEvent[map][room].zoneY;
        }

        return hit;

    }

    public void setTeleport(){

        for(int i = 0; i < gp.maxLevel; i++){

            for(int j = 0; j < gp.maxRoom; j++){

                teleportEvent[i][j] = new EventZone();
                teleportEvent[i][j].height = gp.tileSize * 3;
                teleportEvent[i][j].width = gp.tileSize * 3;
                teleportEvent[i][j].x = 0;
                teleportEvent[i][j].y = 0;
                teleportEvent[i][j].zoneX = teleportEvent[i][j].x;
                teleportEvent[i][j].zoneY = teleportEvent[i][j].y;
            }

        }
    }
}
