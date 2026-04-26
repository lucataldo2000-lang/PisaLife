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

                    waitTime = 0;
                }
                gp.currentRoom = room[2];

                setPlayerValues(100,380, "right");
                return;
            }
            if(checkTeleport(1,2,18,4)){
                if(room[3] != 4 && room[3] != 6){
                    int newRoom = 0;
                    int ran = random.nextInt(1,3);

                    switch (ran){
                        case 1 -> newRoom = 4;
                        case 2 -> newRoom = 6;
                    }
                    room[3] = newRoom;
                }

                waitTime = 0;

                gp.currentRoom = room[3];

                setPlayerValues(100,100, "right");
                return;
            }
            if(checkTeleport(1,3,18,4)){
                if(room[3] != 4 && room[3] != 6){
                    int newRoom = 0;
                    int ran = random.nextInt(1,3);

                    switch (ran){
                        case 1 -> newRoom = 4;
                        case 2 -> newRoom = 6;
                    }
                    room[3] = newRoom;
                }

                waitTime = 0;

                gp.currentRoom = room[3];

                setPlayerValues(100,100, "right");
                return;
            }
            if(checkTeleport(1,4,14,18)){
                int x = 0;
                int y = 0;
                if(room[4] != 8 && room[4] != 9){
                    int newRoom = 0;
                    int ran = random.nextInt(1,3);

                    switch (ran){
                        case 1 -> {
                            newRoom = 8;
                        }
                        case 2 -> {
                            newRoom = 9;
                        }
                    }
                    room[4] = newRoom;
                }

                switch(room[4]){
                    case 8 -> {
                        x = 465; y = 60;
                    }
                    case 9 -> {
                        x = 100; y = 100;
                    }
                }

                waitTime = 0;

                gp.currentRoom = room[4];


                setPlayerValues(x,y, "down");
                return;
            }
            if(checkTeleport(1,6,14,18)){
                int x = 0;
                int y = 0;
                if(room[4] != 8 && room[4] != 9){
                    int ran = random.nextInt(1,3);
                    int newRoom = 0;

                    switch (ran){
                        case 1 -> {
                            newRoom = 8;
                        }
                        case 2 -> {
                            newRoom = 9;
                        }
                    }

                    room[4] = newRoom;
                }

                switch(room[4]){
                    case 8 -> {
                        x = 465; y = 60;
                    }
                    case 9 -> {
                        x = 100; y = 100;
                    }
                }

                waitTime = 0;

                gp.currentRoom = room[4];


                setPlayerValues(x,y, "down");
                return;
            }
            if(checkTeleport(1,2,0,12)){gp.currentRoom = room[1]; waitTime = 0;setPlayerValues(470,380, "left");}
            if(checkTeleport(1,2,7,18)){gp.currentRoom = 5;waitTime = 0;setPlayerValues(210,90, "down");}
            if(checkTeleport(1,5,7,0)){gp.currentRoom = room[2];waitTime = 0;setPlayerValues(210,500, "up");}
            if(checkTeleport(1,3,0,12)){gp.currentRoom = room[1]; waitTime = 0;setPlayerValues(470,380, "left");}
            if(checkTeleport(1,4,2,1)){gp.currentRoom = room[2]; waitTime = 0;setPlayerValues(515,100, "left");}
            if(checkTeleport(1,4,0,12)){gp.currentRoom = 7; waitTime = 0;setPlayerValues(515,120, "left");}
            if(checkTeleport(1,8,14,0)){gp.currentRoom = room[3]; waitTime = 0;setPlayerValues(465,490, "up");}
            if(checkTeleport(1,9,1,0)){gp.currentRoom = room[3]; waitTime = 0;setPlayerValues(465,490, "up");}
            if(checkTeleport(1,8,0,13)){gp.currentRoom = 0; waitTime = 0;setPlayerValues(599,250, "left");}
            if(checkTeleport(1,9,0,14)){gp.currentRoom = 0; waitTime = 0;setPlayerValues(500,250, "left");}
            if(checkTeleport(1,7,18,3)){
                gp.currentRoom = room[3];
                waitTime = 0;

                int x = 0;
                int y = 0;

                if(room[3] == 4) {
                    x = 80;
                    y = 380;
                }
                if(room[3] == 6){
                    y = 504;
                    x = 210;
                }

                setPlayerValues(x,y, "left");
            }
            if(checkTeleport(1,6,2,1)){gp.currentRoom = room[2]; waitTime = 0;setPlayerValues(515,100, "left");}
            if(checkTeleport(1,6,3,17)){gp.currentRoom = 7; waitTime = 0;setPlayerValues(515,120, "left");}
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
