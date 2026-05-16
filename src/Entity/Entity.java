package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Entity {

    GamePanel gp;
    Random random = new Random();

    public int worldX;
    public int worldY;

    public String name;
    public String direction;
    public int type;
    public final int playerType = 1;
    public final int npcType = 2;
    public final int monsterType = 3;
    public final int objectType = 4;
    public final int projectileType = 5;
    public double life;
    public int maxLife;
    public int speed;
    public double damage;
    public int defence;
    public double strength;
    public int exp;
    public int maxExp;
    public int level;
    public int frozenTime;
    public Rectangle solidArea;
    public Rectangle attackArea;
    public boolean collisionOn = false;
    public boolean attacking = false;
    public boolean parrying = false;
    public boolean isMoving = false;
    public boolean damageDone;
    public boolean stunned = false;
    public boolean animated = false;
    public boolean frozen;
    public boolean onPath;
    public boolean animFinished;
    public boolean effect;

    public int solidAreaX;
    public int solidAreaY;

    public int attackAreaX;
    public int attackAreaY;

    public int amount;
    public boolean stackable;
    public int objType;
    public final int weapon = 1;
    public final int shield = 2;
    public final int consumable = 3;

    public int playerClass;
    public final int warriorClass = 0;
    public final int wizardClass = 1;
    public final int rangerClass = 2;

    public BufferedImage[][] up = new BufferedImage[3][3];
    public  BufferedImage[][] down = new BufferedImage[3][3];
    public BufferedImage[][] left = new BufferedImage[3][3];
    public BufferedImage[][] right = new BufferedImage[3][3];

    public BufferedImage[][] upProj = new BufferedImage[5][3];
    public BufferedImage[][] downProj = new BufferedImage[5][3];
    public BufferedImage[][] leftProj = new BufferedImage[5][3];
    public BufferedImage[][] rightProj = new BufferedImage[5][3];

    public BufferedImage[][] upIdle = new BufferedImage[3][2];
    public BufferedImage[][] downIdle = new BufferedImage[3][2];
    public BufferedImage[][] leftIdle = new BufferedImage[3][2];
    public BufferedImage[][] rightIdle = new BufferedImage[3][2];

    public BufferedImage[] upMonster = new BufferedImage[3];
    public BufferedImage[] downMonster = new BufferedImage[3];
    public BufferedImage[] leftMonster = new BufferedImage[3];
    public BufferedImage[] rightMonster = new BufferedImage[3];

    public BufferedImage[] upIdleMonster = new BufferedImage[2];
    public BufferedImage[] downIdleMonster = new BufferedImage[2];
    public BufferedImage[] leftIdleMonster = new BufferedImage[2];
    public BufferedImage[] rightIdleMonster = new BufferedImage[2];

    public BufferedImage[][][] upAttack = new BufferedImage[3][3][3];
    public BufferedImage[][][] downAttack = new BufferedImage[3][3][3];
    public BufferedImage[][][] leftAttack = new BufferedImage[3][3][3];
    public BufferedImage[][][] rightAttack = new BufferedImage[3][3][3];

    public BufferedImage[] upParry = new BufferedImage[3];
    public BufferedImage[] downParry = new BufferedImage[3];
    public BufferedImage[] leftParry = new BufferedImage[3];
    public BufferedImage[] rightParry = new BufferedImage[3];

    BufferedImage frozenEffect, iceCube;

    public int spriteCounter;
    public int spriteNum;

    public boolean decoration;

    public BufferedImage[] objImage = new BufferedImage[5];

    public Entity(GamePanel gp) {

        this.gp = gp;
    }

    public Entity cloneObject(Entity entity) {

        Entity newObject = new Entity(gp);
        newObject.name = entity.name;
        newObject.direction = entity.direction;
        newObject.type = entity.type;
        newObject.objType = entity.objType;
        newObject.collisionOn = entity.collisionOn;
        newObject.solidArea = entity.solidArea;
        newObject.solidArea.x = entity.solidArea.x;
        newObject.solidArea.y = entity.solidArea.y;
        newObject.solidAreaX = entity.solidAreaX;
        newObject.solidAreaY = entity.solidAreaY;
        newObject.damage = entity.damage;
        newObject.animated = entity.animated;

        for (int i = 0; i < objImage.length; i++) {
            newObject.objImage[i] = entity.objImage[i];
        }

        return newObject;
    }

    int waitParticle = 0;
    int num = 0;
    int frozenTimer;
    int stunnedTimer;
    int randomDirection = 0;
    int distance;

    public void update() {

        if (waitParticle >= 9) {
            waitParticle = 0;
            Particles particles = new Particles(gp, this, 9);

            for (int i = 0; i < gp.particles[gp.currentLevel][gp.currentRoom].length; i++) {

                if (gp.particles[gp.currentLevel][gp.currentRoom][i] == null) {
                    gp.particles[gp.currentLevel][gp.currentRoom][i] = particles;
                    return;

                }
            }
        }

        if (this.type == monsterType) {
            num++;
            spriteCounter++;

            if (stunned) {
                stunnedTimer++;
                collisionOn = true;

                if (stunnedTimer >= 30) {
                    stunned = false;
                    stunnedTimer = 0;
                }
            }

            if (frozen) {
                frozenTimer++;
                if (frozenTimer >= frozenTime) {
                    frozen = false;
                    frozenTimer = 0;
                }
            }

            collisionOn = false;

            distance = Math.abs(worldX - gp.player.worldX) + Math.abs(worldY - gp.player.worldY);

            if (distance <= gp.tileSize * 4) {
                onPath = true;
            } else {
                onPath = false;
            }

            if (onPath && !frozen) {
                isMoving = true;
                int goalCol = gp.player.worldX / gp.tileSize;
                int goalRow = (gp.player.worldY + gp.tileSize * 2) / gp.tileSize;

                searchPath(goalCol, goalRow);

                gp.checker.checkTile(this);

                if (!collisionOn) {
                    switch (direction) {
                        case "down" -> worldY += speed;
                        case "up" -> worldY -= speed;
                        case "left" -> worldX -= speed;
                        case "right" -> worldX += speed;
                    }

                    if (spriteCounter <= 4) {
                        spriteNum = 0;
                    }
                    if (spriteCounter > 5 && spriteCounter <= 10) {
                        spriteNum = 1;
                    }
                    if (spriteCounter > 10 && spriteCounter <= 15) {
                        spriteNum = 0;
                    }
                    if (spriteCounter > 15 && spriteCounter <= 20) {
                        spriteNum = 2;
                    }
                    if (spriteCounter > 20) {
                        spriteCounter = 0;
                    }
                } else {
                    searchPath(goalCol, goalRow);
                }
            } else {
                if (!attacking && !stunned && !frozen) {

                    if (num >= 60) {
                        randomDirection = random.nextInt(1, 12);
                        num = 0;
                    }

                    switch (randomDirection) {
                        case 0 -> direction = "up";
                        case 1 -> direction = "down";
                        case 2 -> direction = "left";
                        case 3 -> direction = "right";
                        case 4 -> direction = "up-right";
                        case 5 -> direction = "up-left";
                        case 6 -> direction = "down-right";
                        case 7 -> direction = "down-left";
                    }

                    gp.checker.checkTile(this);
                    gp.checker.checkObject(this);

                    if (randomDirection < 10 && !collisionOn) {
                        isMoving = true;
                        waitParticle++;

                        if (spriteCounter <= 4) {
                            spriteNum = 0;
                        }
                        if (spriteCounter > 5 && spriteCounter <= 10) {
                            spriteNum = 1;
                        }
                        if (spriteCounter > 10 && spriteCounter <= 15) {
                            spriteNum = 0;
                        }
                        if (spriteCounter > 15 && spriteCounter <= 20) {
                            spriteNum = 2;
                        }
                        if (spriteCounter > 20) {
                            spriteCounter = 0;
                        }

                        switch (direction) {
                            case "down" -> worldY += speed;
                            case "up" -> worldY -= speed;
                            case "left" -> worldX -= speed;
                            case "right" -> worldX += speed;
                        }
                    }
                    if (randomDirection > 9 || collisionOn) {
                        isMoving = false;
                        if (spriteCounter <= 15) {
                            spriteNum = 0;
                        }
                        if (spriteCounter > 15 && spriteCounter <= 30) {
                            spriteNum = 1;
                        }
                        if (spriteCounter > 30) {
                            spriteCounter = 0;
                        }
                    }

                }
            }
        }
    }

    public void draw(Graphics2D g2) {

        spriteCounter++;
        BufferedImage drawImage;

        int width;
        int height;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (this.animated) {
            if (spriteCounter >= 2 && spriteNum < 5) {
                spriteNum = 0;

            }
            if (spriteCounter >= 5 && spriteNum < 8) {
                spriteNum = 1;
            }
            if (spriteCounter >= 8 && spriteNum < 11) {
                spriteNum = 2;
            }
            if (spriteCounter >= 11 && spriteNum < 14) {
                spriteNum = 3;
            }
            if (spriteCounter >= 14 && spriteNum < 17) {
                spriteNum = 4;
            }
            if (spriteCounter >= 17 && spriteNum < 20) {
                spriteNum = 3;
            }
            if (spriteCounter >= 20 && spriteNum < 23) {
                spriteNum = 2;
            }
            if (spriteCounter >= 26 && spriteNum < 29) {
                spriteNum = 1;
            }
            if (spriteCounter >= 29 && spriteNum < 32) {
                spriteNum = 0;
            }

            if (spriteCounter >= 32) {
                spriteCounter = 0;
            }

            drawImage = objImage[spriteNum];
        } else {
            drawImage = objImage[0];
        }

        if (this.name.equals("Chest Pillar")) {
            width = 50;
            height = 70;
        } else {
            width = drawImage.getWidth();
            height = drawImage.getHeight();
        }

        g2.drawImage(drawImage, screenX, screenY, width, height, null);
    }

    public void searchPath(int goalCol, int goalRow) {

        int startCol = (worldX + solidAreaX) / gp.tileSize;
        int startRow = (worldY + solidAreaY) / gp.tileSize;

        //setta i nodi se sono solidi o no e il loro fCost
        gp.pathFinding.setNodes(gp.currentRoom, startCol, startRow, goalCol, goalRow);

        //cerca i nodi migliori
        if (gp.pathFinding.search()) {

            int x = gp.pathFinding.pathList.getFirst().col * gp.tileSize;
            int y = gp.pathFinding.pathList.getFirst().row * gp.tileSize;

            int topY = worldY + solidAreaY;
            int leftX = worldX + solidAreaX;
            int rightX = leftX + solidArea.width;

            if (topY > y && leftX >= x && rightX < x + gp.tileSize) {
                direction = "up";
            } else if (topY < y && leftX >= x && rightX < x + gp.tileSize) {
                direction = "down";
            } else if (leftX >= x && leftX < x + gp.tileSize) {

                if (topY > y) {
                    direction = "up";
                }
                if (topY < y) {
                    direction = "down";
                }
            } else if (topY >= y && topY < y + gp.tileSize) {

                if (leftX > x) {
                    direction = "left";
                } else {
                    direction = "right";
                }
            } else {
                if (Math.abs(leftX - x) > Math.abs(topY - y)) {
                    if(leftX > x){
                        direction = "left";
                    }
                    else{
                        direction = "right";
                    }
                } else {
                    if(topY > y){
                        direction = "up";
                    }
                    else{
                        direction = "down";
                    }
                }
            }

            int nextCol = gp.pathFinding.pathList.getFirst().col;
            int nextRow = gp.pathFinding.pathList.getFirst().row;

            if (nextCol == goalCol && nextRow == goalRow) {
                if(!name.equals("Boss")){
                    onPath = false;
                }
            }

        }
    }
}
