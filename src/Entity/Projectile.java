package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.IllegalFormatWidthException;

public class Projectile extends Entity{

    int monsterIndex;
    int types;
    public Projectile(GamePanel gp, int lifeTime, int x, int y, String dir, int sp, int projType, int width, int height, int solidX, int solidY, int totDamage){
        super(gp);

        speed = sp;
        life = lifeTime;
        worldX = x;
        worldY = y;
        damage = totDamage;

        type = projectileType;
        direction = dir;

        solidArea = new Rectangle(width,height);
        solidArea.x = solidX;
        solidArea.y = solidY;

        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;
        this.types = projType;

        getImages();
    }

    public void getImages(){
        try{
            //FIREBALL

            upProj[0][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/fireball00.png"));
            downProj[0][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/fireball06.png"));
            leftProj[0][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/fireball03.png"));
            rightProj[0][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/fireball09.png"));

            upProj[0][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/fireball01.png"));
            downProj[0][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/fireball07.png"));
            leftProj[0][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/fireball04.png"));
            rightProj[0][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/fireball10.png"));

            upProj[0][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/fireball02.png"));
            downProj[0][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/fireball08.png"));
            leftProj[0][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/fireball05.png"));
            rightProj[0][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/fireball11.png"));

            //EARTHQUAKE

            upProj[1][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/quakeBaseUpDown0.png"));
            downProj[1][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/quakeBaseUpDown3.png"));
            leftProj[1][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/quakeBaseLeftRIght3.png"));
            rightProj[1][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/quakeBaseLeftRIght0.png"));

            upProj[1][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/quakeBaseUpDown1.png"));
            downProj[1][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/quakeBaseUpDown4.png"));
            leftProj[1][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/quakeBaseLeftRIght4.png"));
            rightProj[1][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/quakeBaseLeftRIght1.png"));

            upProj[1][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/quakeBaseUpDown2.png"));
            downProj[1][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/quakeBaseUpDown5.png"));
            leftProj[1][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/quakeBaseLeftRIght5.png"));
            rightProj[1][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/quakeBaseLeftRIght2.png"));

            //ARROW

            upProj[2][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/arrow2.png"));
            downProj[2][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/arrow0.png"));
            leftProj[2][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/arrow3.png"));
            rightProj[2][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/arrow1.png"));

            upProj[2][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/arrow2.png"));
            downProj[2][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/arrow0.png"));
            leftProj[2][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/arrow3.png"));
            rightProj[2][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/arrow1.png"));

            upProj[2][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/arrow2.png"));
            downProj[2][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/arrow0.png"));
            leftProj[2][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/arrow3.png"));
            rightProj[2][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/arrow1.png"));

            //ICE BALL

            upProj[3][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/icycle00.png"));
            downProj[3][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/icycle06.png"));
            leftProj[3][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/icycle03.png"));
            rightProj[3][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/icycle09.png"));

            upProj[3][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/icycle01.png"));
            downProj[3][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/icycle07.png"));
            leftProj[3][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/icycle04.png"));
            rightProj[3][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/icycle10.png"));

            upProj[3][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/icycle02.png"));
            downProj[3][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/icycle08.png"));
            leftProj[3][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/icycle05.png"));
            rightProj[3][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/icycle11.png"));

            //ICE QUAKE

            upProj[4][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/iceQuakeUpDown3.png"));
            downProj[4][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/iceQuakeUpDown0.png"));
            leftProj[4][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/iceQuakeLeftRight3.png"));
            rightProj[4][0] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/iceQuakeLeftRight0.png"));

            upProj[4][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/iceQuakeUpDown4.png"));
            downProj[4][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/iceQuakeUpDown1.png"));
            leftProj[4][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/iceQuakeLeftRight4.png"));
            rightProj[4][1] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/iceQuakeLeftRight1.png"));

            upProj[4][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/iceQuakeUpDown5.png"));
            downProj[4][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/iceQuakeUpDown2.png"));
            leftProj[4][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/iceQuakeLeftRight5.png"));
            rightProj[4][2] = ImageIO.read(getClass().getResourceAsStream("/ProjectileTextures/iceQuakeLeftRight2.png"));

        }catch(IOException e){e.printStackTrace();}
    }

    public void update(){

        if(gp.gameThread != null && gp.gameState == gp.playState){
            spriteCounter++;
            life--;

            if(types != 1){
                gp.checker.checkTile(this);
            }
            monsterIndex = gp.checker.checkMonster(this);
            attack();

            if(spriteNum < 5){
                spriteNum = 0;

            }
            if(spriteCounter >= 5 && spriteCounter < 10){
                spriteNum = 1;

            }
            if(spriteCounter >= 10 && spriteCounter < 15){
                spriteNum = 2;

            }
            if(spriteCounter >= 15){
                spriteCounter = 0;
                damageDone = false;
                gp.player.damageDone = false;
            }


            switch(direction){
                case "down" -> worldY += speed;
                case "up" -> worldY -= speed;
                case "left" ->  worldX -= speed;
                case "right" -> worldX += speed;
            }
        }
    }
    double totDamage;

    public void attack(){
        if(monsterIndex != 999 && !damageDone){
            damageDone = true;
            totDamage = damage * gp.player.strength;
            Entity monster = gp.monsters[gp.currentLevel][gp.currentRoom][monsterIndex];
            monster.life -= totDamage;

            if(types == 4){
                monster.frozen = true;
                monster.frozenTime = gp.player.inventory[0].frozenTime;
            }
            if(types == 3){
                monster.frozen = true;
                monster.frozenTime = gp.player.inventory[1].frozenTime;
            }

            monster.stunned = true;

            if(monster.life <= 0){

                switch (monster.name){
                    case "Skeleton" -> gp.player.exp += 5;
                    case "Slime" -> gp.player.exp += 3;
                }

                gp.monsters[gp.currentLevel][gp.currentRoom][monsterIndex] = null;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        switch(direction){
            case "up" -> image = upProj[types][spriteNum];
            case "down" -> image = downProj[types][spriteNum];
            case "left" -> image = leftProj[types][spriteNum];
            case "right" -> image = rightProj[types][spriteNum];
        }

        if(monsterIndex != 999){

            if(gp.monsters[gp.currentLevel][gp.currentRoom][monsterIndex] != null){
                Entity monster = gp.monsters[gp.currentLevel][gp.currentRoom][monsterIndex];

                int monsterX = monster.worldX - worldX + screenX;
                int monsterY = monster.worldY - worldY + screenY;

                g2.setFont(new Font("Arial", Font.BOLD,15));

                g2.setColor(Color.white);
                g2.drawString(String.valueOf(Math.round(damage * gp.player.strength)),monsterX + gp.tileSize / 2,monsterY + 10);
            }


        }

        g2.drawImage(image,screenX,screenY,image.getWidth(),image.getHeight(),null);

    }

}