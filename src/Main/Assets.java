package Main;

import Entity.*;
import Entity.Box;

import javax.swing.*;
import java.util.Random;

public class Assets {

    GamePanel gp;
    Random random = new Random();
    int ogRoom;

    public Assets(GamePanel gp) {
        this.gp = gp;
        ogRoom = gp.currentRoom;
    }


    public void setObj() {

        setObject(new TorchBlock(gp), gp.tileSize * 17, gp.tileSize * 8, 1);

        setObject(new Barrel(gp), gp.tileSize * 8, gp.tileSize * 3 - gp.tileSize / 2, 1);

        setObject(new Barrel(gp), gp.tileSize * 9, gp.tileSize * 3 - gp.tileSize / 2, 1);

        setObject(new Box(gp), gp.tileSize * 3, gp.tileSize * 3 - gp.tileSize / 2, 1);

        setObject(new Box(gp), gp.tileSize * 2, gp.tileSize * 3 - gp.tileSize / 2, 1);

        setObject(new Box(gp), gp.tileSize * 3 - gp.tileSize / 2, gp.tileSize * 2 - 7, 1);

        setObject(new Barrel(gp), gp.tileSize * 2, gp.tileSize * 8, 1);

        setObject(new Barrel(gp), gp.tileSize * 2, gp.tileSize * 9, 1);

        setObject(new Barrel(gp), gp.tileSize * 11, gp.tileSize * 5 - gp.tileSize / 2, 1);

        setObject(new Box(gp), gp.tileSize * 12, gp.tileSize * 5 - gp.tileSize / 2, 1);

        setObject(new FirePitch(gp), gp.tileSize * 8, gp.tileSize * 9 + 7, 1);

        setObject(new Barrel(gp), gp.tileSize * 10, gp.tileSize * 10, 1);

        setObject(new FirePitch(gp), gp.tileSize * 14, gp.tileSize * 4 + 7, 1);

        setObject(new Bone1(gp), gp.tileSize * 4 - gp.tileSize / 2, gp.tileSize * 6 - 8, 1);

        setObject(new Bone2(gp), gp.tileSize * 10 - gp.tileSize / 2, gp.tileSize * 8 - 8, 1);

        setObject(new Bone1(gp), gp.tileSize * 13 - gp.tileSize / 2, gp.tileSize * 13 - 8, 1);

        setObject(new Bone2(gp), gp.tileSize * 17 - gp.tileSize / 2, gp.tileSize * 10 - 8, 1);

        setObject(new TorchBlock(gp), gp.tileSize * 3, gp.tileSize, 1);

        setObject(new TorchBlock(gp), gp.tileSize * 8, gp.tileSize, 1);

        setObject(new Barrel(gp), gp.tileSize * 15, gp.tileSize * 16 - 2, 1);

        setObject(new Barrel(gp), gp.tileSize * 16, gp.tileSize * 16 - 2, 1);

        setObject(new Pillar1(gp), gp.tileSize * 5 - 4, gp.tileSize * 3 + 5, 1);

        setObject(new Pillar2(gp), gp.tileSize * 8 - 4, gp.tileSize * 7 + 5, 1);

        setObject(new Pillar1(gp), gp.tileSize * 13 - 4, gp.tileSize * 15, 1);

        setObject(new chestPillar(gp), gp.tileSize * 17 - 9, gp.tileSize * 12, 1);

        setObject(new Chest(gp), gp.tileSize * 17 - 4, gp.tileSize * 11 - 2, 1);

        setObject(new TorchBlock(gp), gp.tileSize * 3, gp.tileSize * 8, 2);

        setObject(new TorchBlock(gp), gp.tileSize * 7, gp.tileSize, 2);

        setObject(new TorchBlock(gp), gp.tileSize * 16, gp.tileSize, 2);

        setObject(new TorchBlock(gp), gp.tileSize * 14, gp.tileSize * 11, 2);

        setObject(new Barrel(gp), gp.tileSize * 6, gp.tileSize * 2 + gp.tileSize / 2, 2);

        setObject(new Barrel(gp), gp.tileSize * 7, gp.tileSize * 2 + gp.tileSize / 2, 2);

        setObject(new FirePitch(gp), gp.tileSize * 11, gp.tileSize * 2, 2);

        setObject(new Box(gp), gp.tileSize * 13, gp.tileSize * 2 + gp.tileSize / 2, 2);

        setObject(new Bone2(gp), gp.tileSize * 6 - gp.tileSize / 2, gp.tileSize * 11, 2);

        setObject(new Barrel(gp), gp.tileSize * 16, gp.tileSize * 13, 2);

        setObject(new Barrel(gp), gp.tileSize * 16, gp.tileSize * 14, 2);

        setObject(new Box(gp), gp.tileSize * 16, gp.tileSize * 15, 2);

        setObject(new Box(gp), gp.tileSize * 12, gp.tileSize * 13 - gp.tileSize / 2, 2);

        setObject(new Barrel(gp), gp.tileSize * 4, gp.tileSize * 16, 2);

        setObject(new Bone1(gp), gp.tileSize * 9, gp.tileSize * 6, 2);

        setObject(new Bone2(gp), gp.tileSize * 13, gp.tileSize * 13, 2);

        setObject(new FirePitch(gp), gp.tileSize * 11, gp.tileSize * 15 + 7, 2);

        setObject(new Barrel(gp), gp.tileSize * 11, gp.tileSize * 8, 2);

        setObject(new Barrel(gp), gp.tileSize * 11, gp.tileSize * 9, 2);

        setObject(new Box(gp), gp.tileSize, gp.tileSize * 11 - gp.tileSize / 2, 2);

        setObject(new Pillar1(gp), gp.tileSize * 5, gp.tileSize * 5 + 5, 2);

        setObject(new Pillar1(gp), gp.tileSize * 3, gp.tileSize * 9 + 5, 2);

        setObject(new chestPillar(gp), gp.tileSize * 7 - 9, gp.tileSize * 12 + gp.tileSize / 2, 2);

        setObject(new Chest(gp), gp.tileSize * 7 - 4, gp.tileSize * 12 - gp.tileSize / 2, 2);

        setObject(new chestPillar(gp), gp.tileSize * 15 - 9, gp.tileSize * 12 + gp.tileSize / 2, 3);

        setObject(new Chest(gp), gp.tileSize * 15 - 4, gp.tileSize * 12 - gp.tileSize / 2, 3);

        setObject(new FirePitch(gp), gp.tileSize * 7, gp.tileSize * 2, 3);

        setObject(new Barrel(gp), gp.tileSize * 3, gp.tileSize * 3 - gp.tileSize / 2, 3);

        setObject(new Box(gp), gp.tileSize * 4, gp.tileSize * 3 - gp.tileSize / 2, 3);

        setObject(new Bone1(gp), gp.tileSize * 5, gp.tileSize * 8, 3);

        setObject(new Barrel(gp), gp.tileSize * 5, gp.tileSize * 7, 3);

        setObject(new Pillar1(gp), gp.tileSize * 8, gp.tileSize * 11 + 5, 3);

        setObject(new TorchBlock(gp), gp.tileSize * 2, gp.tileSize * 10, 3);

        setObject(new TorchBlock(gp), gp.tileSize * 10, gp.tileSize * 8, 3);

        setObject(new TorchBlock(gp), gp.tileSize * 13, gp.tileSize * 8, 3);

        setObject(new Pillar2(gp), gp.tileSize * 16 - 4, gp.tileSize * 7, 3);

        setObject(new FirePitch(gp), gp.tileSize * 16, gp.tileSize * 2, 3);

        setObject(new Barrel(gp), gp.tileSize * 3, gp.tileSize * 16, 3);

        setObject(new Box(gp), gp.tileSize * 5, gp.tileSize * 16, 3);

        setObject(new Barrel(gp), gp.tileSize * 6, gp.tileSize * 16, 3);

        setObject(new chestPillar(gp), gp.tileSize * 8 - 9, gp.tileSize * 13 + gp.tileSize / 2, 4);

        setObject(new Chest(gp), gp.tileSize * 8 - 4, gp.tileSize * 13 - gp.tileSize / 2, 4);

        setObject(new FirePitch(gp), gp.tileSize, gp.tileSize * 2, 4);

        setObject(new FirePitch(gp), gp.tileSize * 11, gp.tileSize * 9, 4);

        setObject(new Barrel(gp), gp.tileSize * 4, gp.tileSize * 3 - gp.tileSize / 2, 4);

        setObject(new Barrel(gp), gp.tileSize * 5, gp.tileSize * 3 - gp.tileSize / 2, 4);

        setObject(new Pillar1(gp), gp.tileSize * 2 - 4, gp.tileSize * 7, 4);

        setObject(new Bone1(gp), gp.tileSize * 2, gp.tileSize * 10, 4);

        setObject(new Barrel(gp), gp.tileSize * 7, gp.tileSize * 9 + gp.tileSize / 2, 4);

        setObject(new Box(gp), gp.tileSize * 8, gp.tileSize * 9 + gp.tileSize / 2, 4);

        setObject(new Box(gp), gp.tileSize * 9, gp.tileSize * 9 + gp.tileSize / 2, 4);

        setObject(new Pillar2(gp), gp.tileSize * 15 - 4, gp.tileSize * 12, 4);

        setObject(new Barrel(gp), gp.tileSize * 14, gp.tileSize * 9 + gp.tileSize / 2, 4);

        setObject(new Bone2(gp), gp.tileSize * 13, gp.tileSize * 12, 4);

        setObject(new TorchBlock(gp), gp.tileSize, gp.tileSize * 10, 4);

        setObject(new Barrel(gp), gp.tileSize * 11, gp.tileSize * 16, 4);

        setObject(new Barrel(gp), gp.tileSize * 12, gp.tileSize * 16, 4);

        setObject(new Bone2(gp), gp.tileSize * 4, gp.tileSize * 13, 4);

        setObject(new TorchBlock(gp), gp.tileSize * 4, gp.tileSize * 3, 5);

        setObject(new TorchBlock(gp), gp.tileSize * 10, gp.tileSize * 3, 5);

        setObject(new FirePitch(gp), gp.tileSize * 16, gp.tileSize * 4, 5);

        setObject(new Barrel(gp), gp.tileSize * 11, gp.tileSize * 4 + gp.tileSize / 2, 5);

        setObject(new Barrel(gp), gp.tileSize * 12, gp.tileSize * 4 + gp.tileSize / 2, 5);

        setObject(new Box(gp), gp.tileSize * 13, gp.tileSize * 4 + gp.tileSize / 2, 5);

        setObject(new Bone1(gp), gp.tileSize * 6, gp.tileSize * 6, 5);

        setObject(new Pillar1(gp), gp.tileSize * 11 - 4, gp.tileSize * 8, 5);

        setObject(new Pillar2(gp), gp.tileSize * 16 - 4, gp.tileSize * 11, 5);

        setObject(new Pillar2(gp), gp.tileSize * 4 - 4, gp.tileSize * 6, 5);

        setObject(new FirePitch(gp), gp.tileSize * 14, gp.tileSize * 14 + 10, 5);

        setObject(new Barrel(gp), gp.tileSize * 10, gp.tileSize * 15, 5);

        setObject(new Box(gp), gp.tileSize * 11, gp.tileSize * 15, 5);

        setObject(new Box(gp), gp.tileSize * 12, gp.tileSize * 15, 5);

        setObject(new Barrel(gp), gp.tileSize * 10, gp.tileSize * 11, 5);

        setObject(new Barrel(gp), gp.tileSize * 10, gp.tileSize * 12, 5);

        setObject(new Bone1(gp), gp.tileSize * 14, gp.tileSize * 12, 5);

        setObject(new Bone2(gp), gp.tileSize * 17, gp.tileSize * 8, 5);

        setObject(new TorchBlock(gp), gp.tileSize, gp.tileSize, 6);

        setObject(new TorchBlock(gp), gp.tileSize * 9, gp.tileSize, 6);

        setObject(new FirePitch(gp), gp.tileSize * 5, gp.tileSize * 2, 6);

        setObject(new Barrel(gp), gp.tileSize * 3, gp.tileSize * 8 - 2, 6);

        setObject(new Box(gp), gp.tileSize * 4, gp.tileSize * 8 - 2, 6);

        setObject(new Box(gp), gp.tileSize * 5, gp.tileSize * 8 - 2, 6);

        setObject(new Bone1(gp), gp.tileSize * 7, gp.tileSize * 5, 6);

        setObject(new Pillar1(gp), gp.tileSize * 9 - 4, gp.tileSize * 4, 6);

        setObject(new TorchBlock(gp), gp.tileSize * 12, gp.tileSize * 5, 6);

        setObject(new Barrel(gp), gp.tileSize * 13, gp.tileSize * 6  + gp.tileSize / 2, 6);

        setObject(new FirePitch(gp), gp.tileSize * 16, gp.tileSize * 9, 6);

        setObject(new Barrel(gp), gp.tileSize * 7, gp.tileSize * 11, 6);

        setObject(new Barrel(gp), gp.tileSize * 7, gp.tileSize * 12, 6);

        setObject(new Pillar2(gp), gp.tileSize * 16 - 4, gp.tileSize * 11, 6);

        setObject(new chestPillar(gp), gp.tileSize * 11 - 9 + gp.tileSize / 2, gp.tileSize * 12, 6);

        setObject(new Chest(gp), gp.tileSize * 11 - 4 + gp.tileSize / 2, gp.tileSize * 11, 6);

        setObject(new Box(gp), gp.tileSize * 18, gp.tileSize * 15, 6);

        setObject(new Box(gp), gp.tileSize * 18, gp.tileSize * 14, 6);

        setObject(new Bone2(gp), gp.tileSize * 15, gp.tileSize * 13, 6);

        setObject(new Bone1(gp), gp.tileSize * 8, gp.tileSize * 16, 6);

        setObject(new TorchBlock(gp), gp.tileSize * 5, gp.tileSize * 14, 6);

        setObject(new Barrel(gp), gp.tileSize * 7, gp.tileSize * 18 - 2, 6);

        setObject(new Box(gp), gp.tileSize * 6, gp.tileSize * 18 - 2, 6);

        setObject(new Barrel(gp), gp.tileSize * 13, gp.tileSize * 2 + gp.tileSize / 2, 7);

        setObject(new FirePitch(gp), gp.tileSize * 16, gp.tileSize * 2, 7);

        setObject(new Box(gp), gp.tileSize * 13, gp.tileSize * 6, 7);

        setObject(new Box(gp), gp.tileSize * 13, gp.tileSize * 7, 7);

        setObject(new Pillar1(gp), gp.tileSize * 17 - 4, gp.tileSize * 9, 7);

        setObject(new FirePitch(gp), gp.tileSize * 14, gp.tileSize * 15, 7);

        setObject(new Barrel(gp), gp.tileSize * 11, gp.tileSize * 16 - 2, 7);

        setObject(new Barrel(gp), gp.tileSize * 10, gp.tileSize * 16 - 2, 7);

        setObject(new Box(gp), gp.tileSize * 8, gp.tileSize * 16 - 2, 7);

        setObject(new Box(gp), gp.tileSize * 7, gp.tileSize * 16 - 2, 7);

        setObject(new Pillar2(gp), gp.tileSize * 11 - 4, gp.tileSize * 12, 7);

        setObject(new FirePitch(gp), gp.tileSize * 4 + gp.tileSize / 2, gp.tileSize * 7, 7);

        setObject(new Barrel(gp), gp.tileSize * 2, gp.tileSize * 9 - 2, 7);

        setObject(new Barrel(gp), gp.tileSize * 2, gp.tileSize * 10 - 2, 7);

        setObject(new Bone1(gp), gp.tileSize * 5, gp.tileSize * 10, 7);

        setObject(new Bone1(gp), gp.tileSize * 17, gp.tileSize * 13, 7);

        setObject(new Bone2(gp), gp.tileSize * 13, gp.tileSize * 11, 7);

        setObject(new Bone2(gp), gp.tileSize * 17, gp.tileSize * 5, 7);

        setObject(new Pillar1(gp), gp.tileSize * 4 - 4, gp.tileSize * 11, 7);

        setObject(new TorchBlock(gp), gp.tileSize * 10, gp.tileSize * 10, 7);

        setObject(new TorchBlock(gp), gp.tileSize * 2, gp.tileSize * 7, 8);

        setObject(new TorchBlock(gp), gp.tileSize * 7, gp.tileSize * 7, 8);

        setObject(new chestPillar(gp), gp.tileSize * 5 - 9 + gp.tileSize / 2, gp.tileSize * 12, 8);

        setObject(new Chest(gp), gp.tileSize * 5 - 4 + gp.tileSize / 2, gp.tileSize * 11, 8);

        setObject(new FirePitch(gp), gp.tileSize * 11, gp.tileSize * 4, 8);

        setObject(new Barrel(gp), gp.tileSize * 16, gp.tileSize * 8, 8);

        setObject(new Barrel(gp), gp.tileSize * 16, gp.tileSize * 9, 8);

        setObject(new Pillar1(gp), gp.tileSize * 14 - 4, gp.tileSize * 11, 8);

        setObject(new Pillar2(gp), gp.tileSize * 8 - 4, gp.tileSize * 9, 8);

        setObject(new Pillar1(gp), gp.tileSize * 4 - 4, gp.tileSize * 15, 8);

        setObject(new FirePitch(gp), gp.tileSize * 7, gp.tileSize * 15 + 7, 8);

        setObject(new Box(gp), gp.tileSize, gp.tileSize * 11, 8);

        setObject(new Box(gp), gp.tileSize, gp.tileSize * 10, 8);

        setObject(new Barrel(gp), gp.tileSize * 9, gp.tileSize * 6, 8);

        setObject(new Box(gp), gp.tileSize * 9, gp.tileSize * 7, 8);

        setObject(new Bone1(gp), gp.tileSize * 14, gp.tileSize * 6, 8);

        setObject(new Bone2(gp), gp.tileSize * 11, gp.tileSize * 13, 8);

        setObject(new Bone1(gp), gp.tileSize * 3, gp.tileSize * 11, 8);

        setObject(new chestPillar(gp), gp.tileSize * 16 - 9 + gp.tileSize / 2, gp.tileSize * 14, 9);

        setObject(new Chest(gp), gp.tileSize * 16 - 4 + gp.tileSize / 2, gp.tileSize * 13, 9);

        setObject(new FirePitch(gp), gp.tileSize * 5 + gp.tileSize / 2, gp.tileSize * 2, 9);

        setObject(new Barrel(gp), gp.tileSize, gp.tileSize * 3, 9);

        setObject(new Barrel(gp), gp.tileSize, gp.tileSize * 4, 9);

        setObject(new Box(gp), gp.tileSize, gp.tileSize * 5,9);

        setObject(new Pillar1(gp), gp.tileSize * 5 - 4, gp.tileSize * 6, 9);

        setObject(new FirePitch(gp), gp.tileSize * 16, gp.tileSize * 4,9);

        setObject(new Box(gp), gp.tileSize * 11, gp.tileSize * 4 + gp.tileSize / 2,9);

        setObject(new Box(gp), gp.tileSize * 12, gp.tileSize * 4 + gp.tileSize / 2,9);

        setObject(new Pillar1(gp), gp.tileSize * 17 - 4, gp.tileSize * 9, 9);

        setObject(new Barrel(gp), gp.tileSize * 13, gp.tileSize * 11,9);

        setObject(new FirePitch(gp), gp.tileSize * 8, gp.tileSize * 15 + 7,9);

        setObject(new TorchBlock(gp), gp.tileSize * 2, gp.tileSize * 12,9);

        setObject(new Barrel(gp), gp.tileSize * 4, gp.tileSize * 13 + gp.tileSize / 2,9);

        setObject(new Barrel(gp), gp.tileSize * 3, gp.tileSize * 13 + gp.tileSize / 2,9);

        setObject(new Bone1(gp), gp.tileSize * 4, gp.tileSize * 3,9);

        setObject(new Bone2(gp), gp.tileSize * 10, gp.tileSize * 6,9);

        setObject(new Bone1(gp), gp.tileSize * 14, gp.tileSize * 9,9);

        setObject(new Bone2(gp), gp.tileSize * 15, gp.tileSize * 14,9);

        setObject(new Bone1(gp), gp.tileSize * 5, gp.tileSize * 15,9);

    }

    public void setObject(Entity Object, int X, int Y, int Room) {
        for (int i = 0; i < gp.objects[gp.currentLevel][Room].length; i++) {
            if (gp.objects[gp.currentLevel][Room][i] == null) {
                gp.objects[gp.currentLevel][Room][i] = Object;
                gp.objects[gp.currentLevel][Room][i].worldX = X;
                gp.objects[gp.currentLevel][Room][i].worldY = Y;
                break;
            }
        }
    }


    public void setMonsters() {

        gp.monsters[1][0][0] = new  Boss(gp);
        gp.monsters[1][0][0].worldX = 3 * gp.tileSize;
        gp.monsters[1][0][0].worldY = 10 * gp.tileSize;

        for (int i = 0; i < gp.maxRoom; i++) {

            int col = 0;
            int row = 0;
            int index = 0;

            while (col < gp.maxWorldCol - 1 && row < gp.maxWorldRow - 1 && index < gp.monsters[1][i].length) {

                int num = gp.tileManager.tileNum[1][i][col][row];

                if (gp.tileManager.tiles[num] != null && i != 0 && num != 10 && num != 17 && !gp.tileManager.tiles[num].collision && !gp.tileManager.tiles[num].mobCollision) {

                    int ran = random.nextInt(1, 101);

                    if (ran >= 95) {

                        if (gp.monsters[1][i][index] == null) {
                            Entity mob = null;

                            int type = random.nextInt(1, 3);

                            switch (type) {
                                case 1 -> mob = new Skeleton(gp);
                                case 2 -> mob = new Slime(gp);
                            }

                            mob.direction = "up";
                            mob.worldX = col * gp.tileSize;
                            mob.worldY = row * gp.tileSize;
                            gp.currentRoom = i;

                            boolean check = gp.checker.checkTile(mob);
                            int obj = gp.checker.checkObject(mob);

                            if (!check && obj == 999) {
                                gp.monsters[1][i][index] = mob;
                            }
                        }

                        index++;

                    }

                }

                col++;

                if (col == gp.maxWorldCol - 1) {
                    col = 0;
                    row++;
                }
            }
        }

        gp.currentRoom = ogRoom;
    }
}

