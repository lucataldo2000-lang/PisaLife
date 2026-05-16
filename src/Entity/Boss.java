package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Boss extends Entity{

    GamePanel gp;
    Random random = new Random();

    public boolean canDamage;
    int phase;

    public Boss(GamePanel gp){
        super(gp);
        this.gp = gp;

        speed = 1;
        maxLife = 5000;
        life = maxLife;
        direction = "down";
        damage = 75;
        name = "Boss";
        onPath = true;

        type = monsterType;
        solidArea = new Rectangle(180,180);
        solidArea.x = 10;
        solidArea.y = 20;

        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;

        attackArea = new Rectangle(90,200);
        attackArea.x = 90;
        attackArea.y = 0;

        attackAreaX = attackArea.x;
        attackAreaY = attackArea.y;

        getImages();
    }

    public void getImages(){
        try{

            upMonster[0] = ImageIO.read(getClass().getResourceAsStream("/BossWalkingAnimations/bossAnimations03.png"));
            downMonster[0] = ImageIO.read(getClass().getResourceAsStream("/BossWalkingAnimations/bossAnimations00.png"));
            leftMonster[0] = ImageIO.read(getClass().getResourceAsStream("/BossWalkingAnimations/bossAnimations09.png"));
            rightMonster[0] = ImageIO.read(getClass().getResourceAsStream("/BossWalkingAnimations/bossAnimations06.png"));

            upMonster[1] = ImageIO.read(getClass().getResourceAsStream("/BossWalkingAnimations/bossAnimations04.png"));
            downMonster[1] = ImageIO.read(getClass().getResourceAsStream("/BossWalkingAnimations/bossAnimations01.png"));
            leftMonster[1] = ImageIO.read(getClass().getResourceAsStream("/BossWalkingAnimations/bossAnimations10.png"));
            rightMonster[1] = ImageIO.read(getClass().getResourceAsStream("/BossWalkingAnimations/bossAnimations07.png"));

            upMonster[2] = ImageIO.read(getClass().getResourceAsStream("/BossWalkingAnimations/bossAnimations05.png"));
            downMonster[2] = ImageIO.read(getClass().getResourceAsStream("/BossWalkingAnimations/bossAnimations02.png"));
            leftMonster[2] = ImageIO.read(getClass().getResourceAsStream("/BossWalkingAnimations/bossAnimations11.png"));
            rightMonster[2] = ImageIO.read(getClass().getResourceAsStream("/BossWalkingAnimations/bossAnimations08.png"));

            upAttack[0][0][0] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossUpDownAttack3.png"));
            downAttack[0][0][0] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossUpDownAttack0.png"));
            rightAttack[0][0][0] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossLeftRightAttack0.png"));
            leftAttack[0][0][0] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossLeftRightAttack3.png"));

            upAttack[0][0][1] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossUpDownAttack4.png"));
            downAttack[0][0][1] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossUpDownAttack1.png"));
            rightAttack[0][0][1] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossLeftRightAttack1.png"));
            leftAttack[0][0][1] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossLeftRightAttack4.png"));

            upAttack[0][0][2] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossUpDownAttack5.png"));
            downAttack[0][0][2] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossUpDownAttack2.png"));
            rightAttack[0][0][2] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossLeftRightAttack2.png"));
            leftAttack[0][0][2] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossLeftRightAttack5.png"));

            objImage[0] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossObj0.png"));
            objImage[1] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossObj1.png"));
            objImage[2] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossObj2.png"));
            objImage[3] = ImageIO.read(getClass().getResourceAsStream("/BossAttackAnimations/bossObj3.png"));


            frozenEffect = ImageIO.read(getClass().getResourceAsStream("/GUITextures/iceEffect1.png"));
            iceCube = ImageIO.read(getClass().getResourceAsStream("/GUITextures/iceEffect0.png"));


        }catch(IOException e){e.printStackTrace();}
    }

    int cooldown;
    int spawnCoolDown;

    public void update() {
        if(worldX / gp.tileSize >= gp.maxWorldCol){
            worldX = 100;
        }
        if(worldY / gp.tileSize >= gp.maxWorldRow){
            worldY = 100;
        }

        num++;
        cooldown++;
        spriteCounter++;

        if(life <= (maxLife / 100f) * 60 && phase != 1){
            phase = 1;
        }

        if (frozen) {
            frozenTimer++;
            if (frozenTimer >= frozenTime) {
                frozen = false;
                frozenTimer = 0;
            }
        }

        collisionOn = false;

        gp.checker.checkTile(this);
        canDamage = gp.checker.checkPlayer(this);

        if(phase == 1){
            spawnCoolDown++;

            if(spawnCoolDown >= 30){

                for(int i = 0; i < gp.objects[gp.currentLevel][gp.currentRoom].length; i++){
                    if(gp.objects[gp.currentLevel][gp.currentRoom][i] == null){

                        gp.objects[gp.currentLevel][gp.currentRoom][i] = new DamageArea(gp);
                        gp.objects[gp.currentLevel][gp.currentRoom][i].worldX = random.nextInt(64,544);
                        gp.objects[gp.currentLevel][gp.currentRoom][i].worldY = random.nextInt(64,480);

                        spawnCoolDown = 0;
                        return;
                    }
                }
            }
        }

        if (onPath && !frozen && !attacking) {
            isMoving = true;

            int goalCol = (gp.player.worldX + gp.player.solidAreaX + gp.player.solidArea.width - solidArea.width / 2) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidAreaY + gp.player.solidArea.height - solidArea.height / 2) / gp.tileSize;

            searchPath(goalCol, goalRow);

            if (!collisionOn) {
                switch (direction) {
                    case "down" -> worldY += speed;
                    case "up" -> worldY -= speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }

                if (spriteCounter <= 10) {
                    spriteNum = 0;
                }
                if (spriteCounter > 10 && spriteCounter <= 20) {
                    spriteNum = 1;
                }
                if (spriteCounter > 20 && spriteCounter <= 30) {
                    spriteNum = 0;
                }
                if (spriteCounter > 30 && spriteCounter <= 40) {
                    spriteNum = 2;
                }
                if (spriteCounter > 40) {
                    spriteCounter = 0;
                }
            }
        }
        if(canDamage && cooldown >= 60) {
            if(!attacking){
                attacking = true;
                spriteCounter = 0;
                spriteNum = 0;
            }

            onPath = false;

            if (spriteCounter <= 5) {
                spriteNum = 0;
            }
            if (spriteCounter > 5 && spriteCounter <= 10) {
                spriteNum = 1;
            }
            if (spriteCounter > 10 && spriteCounter <= 15) {
                spriteNum = 2;
            }

            if (spriteCounter > 15) {
                gp.player.life -= damage;
                spriteCounter = 0;
                spriteNum = 0;
                cooldown = 0;
                canDamage = false;
                onPath = true;
                attacking = false;
            }
        }
        if(!canDamage){
            onPath = true;
            attacking = false;
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        int x = 0;
        int y = 0;

        if(!attacking){
            y = screenY;
            x = screenX;
            switch(direction){
                case "up" -> image = upMonster[spriteNum];
                case "down" -> image = downMonster[spriteNum];
                case "left","up-left","down-left" -> image = leftMonster[spriteNum];
                case "right","up-right","down-right" -> image = rightMonster[spriteNum];
            }
        }
       else {
            switch (direction) {
                case "up" -> {
                    image = upAttack[0][0][spriteNum];
                    y = screenY - (gp.tileSize * 4 - gp.tileSize / 2);
                    x = screenX + 6;
                }
                case "down" -> {
                    image = downAttack[0][0][spriteNum];
                    y = screenY + gp.tileSize / 2 - 12;
                    x = screenX;
                }
                case "left"-> {
                    image = leftAttack[0][0][spriteNum];
                    x = screenX - (gp.tileSize * 4 + gp.tileSize / 2);
                    y = screenY;
                }
                case "right"-> {
                    image = rightAttack[0][0][spriteNum];
                    x = screenX + gp.tileSize - 14;
                    y = screenY;
                }
            }
        }

        int width = image.getWidth() * 2;
        int height = image.getHeight() * 2;

        g2.drawImage(image,x,y,width,height,null);

        if(frozen){

            Composite og = g2.getComposite();

            AlphaComposite transparency = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);

            g2.setComposite(transparency);

            g2.drawImage(iceCube,screenX - 15,screenY,230,250,null);

            g2.setComposite(og);

            g2.drawImage(frozenEffect,screenX + 70,screenY - 65,40,40,null);
        }

    }
}
