package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    public int screenX;
    public int screenY;

    public Entity[] inventory = new Entity[5];
    public int objIndex,monsterIndex,weaponIndex;

    public Player(GamePanel gp){
        super(gp);

        this.gp = gp;

        type = playerType;
        solidArea = new Rectangle(40,40);
        solidArea.x = 12;
        solidArea.y = 22;

        attackArea = new Rectangle(50,50);
        attackArea.x = 40;
        attackArea.y = 7;

        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;

        attackAreaX = attackArea.x;
        attackAreaY = attackArea.y;

        screenX = gp.screenWidth / 2;
        screenY = gp.screenHeight / 2;

        setUp();
        getImages();
    }

    public void setUp(){
        gp.currentRoom = 1;
        gp.currentLevel = 1;
        maxLife = 100;
        life = maxLife;
        speed = 4;
        direction = "down";
        strength = 1;
        level = 1;
        maxExp = 25;

        worldX = 112;
        worldY = 434;
    }
    public void resetPlayer(){

        setUp();

        int level = 0;
        int room = 0;
        int obj = 0;

        while(level < gp.maxLevel){

            if(gp.objects[level][room][obj] != null){
                gp.objects[level][room][obj] = null;
            }

            obj++;

            if(obj == gp.maxObj){
                room++;
                obj = 0;
            }
            if(room == gp.maxRoom){
                room = 0;
                level++;
            }

        }

        for(int i = 0; i < inventory.length; i++){

            if(inventory[i] != null){
                inventory[i] = null;
            }
        }

        setStartLoadOut();
        gp.setter.setObj();
    }

    public void setStartLoadOut(){

        switch(playerClass){
            case 0 -> {
                gp.setter.setObject(new BasicSword(gp), gp.tileSize * 3, gp.tileSize * 12, 1);
                gp.setter.setObject(new BasicShield(gp), gp.tileSize * 5, gp.tileSize * 12, 1);
                gp.setter.setObject(new HealthPotion(gp), gp.tileSize * 4, gp.tileSize * 12, 1);
            }
            case 1 -> {
                gp.setter.setObject(new BasicStaff(gp), gp.tileSize * 3, gp.tileSize * 12, 1);
                gp.setter.setObject(new BasicOrb(gp), gp.tileSize * 5, gp.tileSize * 12, 1);
                gp.setter.setObject(new HealthPotion(gp), gp.tileSize * 4, gp.tileSize * 12, 1);
            }
            case 2 ->{
                gp.setter.setObject(new BasicBow(gp), gp.tileSize * 3, gp.tileSize * 12, 1);
                gp.setter.setObject(new HealthPotion(gp), gp.tileSize * 5, gp.tileSize * 12, 1);
            }
        }
    }

    public void getImages(){
        try{

            //WARRIOR ANIMATIONS

            up[0][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior3.png"));
            down[0][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior0.png"));
            left[0][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior9.png"));
            right[0][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior6.png"));

            up[0][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior4.png"));
            down[0][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior1.png"));
            left[0][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior10.png"));
            right[0][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior7.png"));

            up[0][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior5.png"));
            down[0][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior2.png"));
            left[0][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior11.png"));
            right[0][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior8.png"));

            upIdle[0][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior3.png"));
            upIdle[0][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/warrior1.png"));
            downIdle[0][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior0.png"));
            downIdle[0][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/warrior0.png"));
            leftIdle[0][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior9.png"));
            leftIdle[0][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/warrior3.png"));
            rightIdle[0][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/warrior6.png"));
            rightIdle[0][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/warrior2.png"));

            //BASIC SWORD

            upAttack[0][0][0] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/basicAttackUpDown3.png"));
            downAttack[0][0][0] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/basicAttackUpDown0.png"));
            rightAttack[0][0][0] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/basicAttackLeftRight0.png"));
            leftAttack[0][0][0] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/basicAttackLeftRight3.png"));

            upAttack[0][0][1] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/basicAttackUpDown4.png"));
            downAttack[0][0][1] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/basicAttackUpDown1.png"));
            rightAttack[0][0][1] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/basicAttackLeftRight1.png"));
            leftAttack[0][0][1] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/basicAttackLeftRight4.png"));

            upAttack[0][0][2] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/basicAttackUpDown5.png"));
            downAttack[0][0][2] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/basicAttackUpDown2.png"));
            rightAttack[0][0][2] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/basicAttackLeftRight2.png"));
            leftAttack[0][0][2] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/basicAttackLeftRight5.png"));

            //LONG SWORD

            upAttack[0][1][0] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/longAttackUpDown3.png"));
            downAttack[0][1][0] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/longAttackUpDown0.png"));
            rightAttack[0][1][0] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/longAttackLeftRight0.png"));
            leftAttack[0][1][0] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/longAttackLeftRight3.png"));

            upAttack[0][1][1] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/basicAttackUpDown4.png"));
            downAttack[0][1][1] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/longAttackUpDown1.png"));
            rightAttack[0][1][1] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/longAttackLeftRight1.png"));
            leftAttack[0][1][1] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/longAttackLeftRight4.png"));

            upAttack[0][1][2] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/longAttackUpDown4.png"));
            downAttack[0][1][2] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/longAttackUpDown2.png"));
            rightAttack[0][1][2] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/longAttackLeftRight2.png"));
            leftAttack[0][1][2] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/longAttackLeftRight5.png"));

            //ICE SWORD

            upAttack[0][2][0] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/iceAttackUpDown3.png"));
            downAttack[0][2][0] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/iceAttackUpDown0.png"));
            rightAttack[0][2][0] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/iceAttackLeftRight0.png"));
            leftAttack[0][2][0] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/iceAttackLeftRight3.png"));

            upAttack[0][2][1] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/basicAttackUpDown4.png"));
            downAttack[0][2][1] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/iceAttackUpDown1.png"));
            rightAttack[0][2][1] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/iceAttackLeftRight1.png"));
            leftAttack[0][2][1] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/iceAttackLeftRight4.png"));

            upAttack[0][2][2] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/iceAttackUpDown4.png"));
            downAttack[0][2][2] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/iceAttackUpDown2.png"));
            rightAttack[0][2][2] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/iceAttackLeftRight2.png"));
            leftAttack[0][2][2] = ImageIO.read(getClass().getResourceAsStream("/WarriorAttackAnimations/iceAttackLeftRight5.png"));

            //PARRY ANIMATIONS

            upParry[0] = ImageIO.read(getClass().getResourceAsStream("/WarriorPerryAnimations/parrying03.png"));
            downParry[0] = ImageIO.read(getClass().getResourceAsStream("/WarriorPerryAnimations/parrying00.png"));
            leftParry[0] = ImageIO.read(getClass().getResourceAsStream("/WarriorPerryAnimations/parrying09.png"));
            rightParry[0] = ImageIO.read(getClass().getResourceAsStream("/WarriorPerryAnimations/parrying06.png"));

            upParry[1] = ImageIO.read(getClass().getResourceAsStream("/WarriorPerryAnimations/parrying04.png"));
            downParry[1] = ImageIO.read(getClass().getResourceAsStream("/WarriorPerryAnimations/parrying01.png"));
            leftParry[1] = ImageIO.read(getClass().getResourceAsStream("/WarriorPerryAnimations/parrying10.png"));
            rightParry[1] = ImageIO.read(getClass().getResourceAsStream("/WarriorPerryAnimations/parrying07.png"));

            upParry[2] = ImageIO.read(getClass().getResourceAsStream("/WarriorPerryAnimations/parrying05.png"));
            downParry[2] = ImageIO.read(getClass().getResourceAsStream("/WarriorPerryAnimations/parrying02.png"));
            leftParry[2] = ImageIO.read(getClass().getResourceAsStream("/WarriorPerryAnimations/parrying11.png"));
            rightParry[2] = ImageIO.read(getClass().getResourceAsStream("/WarriorPerryAnimations/parrying08.png"));

            //WIZARD ANIMATIONS

            up[1][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizard1.png"));
            down[1][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizard0.png"));
            left[1][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizard3.png"));
            right[1][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizard2.png"));

            up[1][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizAnim2.png"));
            down[1][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizAnim0.png"));
            left[1][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizAnim6.png"));
            right[1][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizAnim4.png"));

            up[1][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizAnim3.png"));
            down[1][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizAnim1.png"));
            left[1][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizAnim7.png"));
            right[1][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizAnim5.png"));

            upIdle[1][0] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/wiz0.png"));
            upIdle[1][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/wiz1.png"));
            downIdle[1][0] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/wiz2.png"));
            downIdle[1][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/wiz3.png"));
            leftIdle[1][0] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/wiz4.png"));
            leftIdle[1][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/wiz5.png"));
            rightIdle[1][0] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/wiz6.png"));
            rightIdle[1][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/wiz7.png"));

            //BASIC STAFF

            upAttack[1][0][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizard1.png"));
            downAttack[1][0][0] = ImageIO.read(getClass().getResourceAsStream("/WizardAttackAnimations/sprite0.png"));
            rightAttack[1][0][0] = ImageIO.read(getClass().getResourceAsStream("/WizardAttackAnimations/sprite3.png"));
            leftAttack[1][0][0] = ImageIO.read(getClass().getResourceAsStream("/WizardAttackAnimations/sprite6.png"));

            upAttack[1][0][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizard1.png"));
            downAttack[1][0][1] = ImageIO.read(getClass().getResourceAsStream("/WizardAttackAnimations/sprite1.png"));
            rightAttack[1][0][1] = ImageIO.read(getClass().getResourceAsStream("/WizardAttackAnimations/sprite4.png"));
            leftAttack[1][0][1] = ImageIO.read(getClass().getResourceAsStream("/WizardAttackAnimations/sprite7.png"));

            upAttack[1][0][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/wizard1.png"));
            downAttack[1][0][2] = ImageIO.read(getClass().getResourceAsStream("/WizardAttackAnimations/sprite2.png"));
            rightAttack[1][0][2] = ImageIO.read(getClass().getResourceAsStream("/WizardAttackAnimations/sprite5.png"));
            leftAttack[1][0][2] = ImageIO.read(getClass().getResourceAsStream("/WizardAttackAnimations/sprite8.png"));

            //RANGER ANIMATIONS

            up[2][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer3.png"));
            down[2][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer0.png"));
            left[2][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer6.png"));
            right[2][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer9.png"));

            up[2][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer4.png"));
            down[2][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer1.png"));
            left[2][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer7.png"));
            right[2][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer10.png"));

            up[2][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer5.png"));
            down[2][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer2.png"));
            left[2][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer8.png"));
            right[2][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer11.png"));

            upIdle[2][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer3.png"));
            upIdle[2][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/idleRanger1.png"));
            downIdle[2][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer0.png"));
            downIdle[2][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/idleRanger0.png"));
            leftIdle[2][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer6.png"));
            leftIdle[2][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/idleRanger3.png"));
            rightIdle[2][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer9.png"));
            rightIdle[2][1] = ImageIO.read(getClass().getResourceAsStream("/IdlePlayerAnimations/idleRanger2.png"));

            //BASIC BOW

            upAttack[2][0][0] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer3.png"));
            downAttack[2][0][0] = ImageIO.read(getClass().getResourceAsStream("/RangerAttack/archerAttack0.png"));
            rightAttack[2][0][0] = ImageIO.read(getClass().getResourceAsStream("/RangerAttack/archerAttack3.png"));
            leftAttack[2][0][0] = ImageIO.read(getClass().getResourceAsStream("/RangerAttack/archerAttack6.png"));

            upAttack[2][0][1] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer3.png"));
            downAttack[2][0][1] = ImageIO.read(getClass().getResourceAsStream("/RangerAttack/archerAttack1.png"));
            rightAttack[2][0][1] = ImageIO.read(getClass().getResourceAsStream("/RangerAttack/archerAttack4.png"));
            leftAttack[2][0][1] = ImageIO.read(getClass().getResourceAsStream("/RangerAttack/archerAttack7.png"));

            upAttack[2][0][2] = ImageIO.read(getClass().getResourceAsStream("/PlayerTextures/archer3.png"));
            downAttack[2][0][2] = ImageIO.read(getClass().getResourceAsStream("/RangerAttack/archerAttack2.png"));
            rightAttack[2][0][2] = ImageIO.read(getClass().getResourceAsStream("/RangerAttack/archerAttack5.png"));
            leftAttack[2][0][2] = ImageIO.read(getClass().getResourceAsStream("/RangerAttack/archerAttack8.png"));

        }catch(IOException e){e.printStackTrace();}
    }

    public void update(){

        if(gp.gameThread != null && gp.gameState == gp.playState){

            if(life <= 0){
                life = 0;
                gp.gameState = gp.deathState;
            }

            if(exp >= maxExp){
                exp = 0;
                maxExp = (int)(maxExp * 1.3);
                level += 1;
                strength *= 1.25;
                maxLife = (int)(maxLife * 1.5);
                life *= 1.5;
            }

            waitTime++;
            waitParticle++;
            takeDamage();

            if(!attacking && !parrying){
                spriteCounter++;
                if(gp.handler.upPressed || gp.handler.downPressed || gp.handler.leftPressed || gp.handler.rightPressed){

                    collisionOn = false;
                    isMoving = true;

                    gp.checker.checkTile(this);
                    objIndex = gp.checker.checkObject(this);

                    if(gp.handler.upPressed){
                        direction = "up";
                    }
                    if(gp.handler.downPressed){
                        direction = "down";
                    }
                    if(gp.handler.leftPressed){
                        direction = "left";
                    }
                    if(gp.handler.rightPressed){
                        direction = "right";
                    }

                    if(spriteCounter <= 4){
                        spriteNum = 0;
                    }
                    if(spriteCounter > 5 && spriteCounter <= 10){
                        spriteNum = 1;
                    }
                    if(spriteCounter > 10 && spriteCounter <= 15){
                        spriteNum = 0;
                    }
                    if(spriteCounter > 15 && spriteCounter <= 20){
                        spriteNum = 2;
                    }
                    if(spriteCounter > 20){
                        spriteCounter = 0;
                    }

                    if(!collisionOn){
                        switch(direction){
                            case "down" -> worldY += speed;
                            case "up" -> worldY -= speed;
                            case "left" ->  worldX -= speed;
                            case "right" -> worldX += speed;
                        }
                    }

                }

                if(!gp.handler.upPressed && !gp.handler.downPressed && !gp.handler.leftPressed && !gp.handler.rightPressed){
                    if(spriteCounter <= 15){
                        spriteNum = 0;
                    }
                    if(spriteCounter > 15 && spriteCounter <= 30){
                        spriteNum = 1;
                    }
                    if(spriteCounter > 30){
                        spriteCounter = 0;
                    }
                }

                if(gp.handler.dropPressed){
                    dropObject();
                }

                pickUpObject(objIndex);
            }
            if(attacking){
                spriteCounter++;
                monsterIndex = gp.checker.checkDamage(this);
                attack();

                if(spriteCounter <= 4){
                    spriteNum = 0;
                }
                if(spriteCounter > 5 && spriteCounter <= 10){
                    spriteNum = 1;
                }
                if(spriteCounter > 10 && spriteCounter <= 15){
                    spriteNum = 2;
                }

                if(spriteCounter > 15){
                    spriteCounter = 0;
                    spriteNum = 0;
                    attacking = false;
                    if (playerClass == wizardClass && inventory[0] != null) {
                        switch(inventory[0].name){
                            case "Basic Staff" -> projectileAttack(30,3,1,70,70,-3,-3,20);
                            case "Ice Staff" -> projectileAttack(30,4,4,70,70,-3,-3,15);
                        }
                    }

                    if (playerClass == rangerClass && inventory[0] != null) {
                        projectileAttack(30,7,2,40,40,-4,-4,25);
                    }

                }

            }

            if(parrying){
                spriteCounter++;

                if(spriteCounter <= 4){
                    spriteNum = 0;
                }
                if(spriteCounter > 5 && spriteCounter <= 10){
                    spriteNum = 1;
                }
                if(spriteCounter > 10 && spriteCounter <= 15){
                    spriteNum = 2;
                }

                if(spriteCounter > 15){
                    spriteCounter = 0;
                    spriteNum = 0;
                    parrying = false;
                }

            }


            gp.events.checkEvent();
        }
    }

    public void projectileAttack(int lifeTime, int speed, int projType, int wid, int hei, int solidX, int solidY, int dam){
        for (int i = 0; i < gp.projectiles[gp.currentLevel][gp.currentRoom].length; i++) {
            if (gp.projectiles[gp.currentLevel][gp.currentRoom][i] == null) {

                int x = gp.player.worldX;
                int y = gp.player.worldY;

                switch (gp.player.direction) {
                    case "up" -> {
                        x += gp.player.solidArea.width / 2 - 4;
                        if(playerClass == wizardClass){
                            y -= gp.tileSize * 2;
                        }
                        else{
                            y -= gp.tileSize;
                        }
                    }
                    case "down" -> {
                        x += gp.player.solidArea.width / 2 - 4;
                        if(playerClass == wizardClass){
                            y += gp.tileSize * 2;
                        }
                        else{
                            y += gp.tileSize;
                        }
                    }
                    case "left" -> {
                        if(playerClass == wizardClass){
                            x -= gp.tileSize * 2;
                        }
                        else{
                            x -= gp.tileSize;
                        }
                        y += gp.player.solidArea.height / 2;
                    }
                    case "right" -> {
                        if(playerClass == wizardClass){
                            x += gp.tileSize * 2;
                        }
                        else{
                            x += gp.tileSize;
                        }
                        y += gp.player.solidArea.height / 2;
                    }
                }

                gp.projectiles[gp.currentLevel][gp.currentRoom][i] = new Projectile(gp,lifeTime, x, y, gp.player.direction, speed, projType,wid,hei,solidX,solidY,dam);
                gp.player.animFinished = false;

                return;
            }
        }
    }

    double totDamage;
    double totDefence;

    public void attack(){
        if(monsterIndex != 999){
            Entity monster = gp.monsters[gp.currentLevel][gp.currentRoom][monsterIndex];

            if(damageDone && playerClass != rangerClass){
                damageDone = false;
                totDamage = inventory[0].damage * strength;
                monster.life -= totDamage;

                if(inventory[0].frozen){
                    monster.frozen = true;
                    monster.frozenTime = inventory[0].frozenTime;
                }

                monster.stunned = true;
            }

            if(monster.life <= 0 && !monster.name.equals("Boss")){

                switch (monster.name){
                    case "Skeleton" -> exp += 5;
                    case "Slime" -> exp += 3;
                }

                gp.monsters[gp.currentLevel][gp.currentRoom][monsterIndex] = null;
            }

        }
    }

    int waitTime;

    public void takeDamage(){
        monsterIndex = gp.checker.checkMonster(this);

        if(monsterIndex != 999 && !gp.monsters[gp.currentLevel][gp.currentRoom][monsterIndex].name.equals("Boss") && !gp.monsters[gp.currentLevel][gp.currentRoom][monsterIndex].frozen && waitTime >= 30){
            waitTime = 0;

            Entity mob = gp.monsters[gp.currentLevel][gp.currentRoom][monsterIndex];

            if(!parrying){
                life -= mob.damage;
            }
            else{
                if((mob.direction.equals("up") && direction.equals("down")) || (mob.direction.equals("left") && direction.equals("right")) || (mob.direction.equals("down") && direction.equals("up")) || (mob.direction.equals("right") && direction.equals("left"))){
                    totDefence = inventory[1].defence * strength;
                    life -= (mob.damage - ((mob.damage / 100) * totDefence));
                }
            }
        }
    }

    public void pickUpObject(int index){

        if(index != 999 ){

            for(int i = 0; i < inventory.length; i++){

               if(gp.objects[gp.currentLevel][gp.currentRoom][index] != null && gp.handler.takePressed && !gp.objects[gp.currentLevel][gp.currentRoom][index].decoration){
                   gp.handler.takePressed = false;

                   switch(gp.objects[gp.currentLevel][gp.currentRoom][index].objType){
                       case 1 -> {

                           if(inventory[0] == null){
                               if(playerClass == warriorClass){
                                   switch(gp.objects[gp.currentLevel][gp.currentRoom][index].name){
                                       case "Iron Sword" -> weaponIndex = 0;
                                       case "Long Sword" -> weaponIndex = 1;
                                       case "Ice Sword" -> weaponIndex = 2;
                                   }
                               }
                               if(playerClass == wizardClass){
                                   switch(gp.objects[gp.currentLevel][gp.currentRoom][index].name){
                                       case "Basic Staff" -> weaponIndex = 0;
                                   }
                               }
                               if(playerClass == rangerClass){
                                   switch(gp.objects[gp.currentLevel][gp.currentRoom][index].name){
                                       case "Basic Bow" -> weaponIndex = 0;
                                   }
                               }

                               inventory[0] = gp.objects[gp.currentLevel][gp.currentRoom][index];
                               gp.objects[gp.currentLevel][gp.currentRoom][index] = null;
                           }
                       }
                       case 2 -> {
                           if(inventory[1] == null){
                               inventory[1] = gp.objects[gp.currentLevel][gp.currentRoom][index];
                               gp.objects[gp.currentLevel][gp.currentRoom][index] = null;
                           }
                       }
                       case 3 -> {

                           for(int j = 2; j < inventory.length; j++){

                               if(inventory[j] == null){
                                   inventory[j] = gp.objects[gp.currentLevel][gp.currentRoom][index];

                                   gp.objects[gp.currentLevel][gp.currentRoom][index] = null;

                                   return;
                               }
                           }
                       }
                   }

               }

            }
        }
    }

    public void dropObject(){

        gp.handler.dropPressed = false;

        if(inventory[gp.handler.inventorySelector] != null){
            for(int i = 0; i < gp.objects[gp.currentLevel][gp.currentRoom].length;i++){

                if(gp.objects[gp.currentLevel][gp.currentRoom][i] == null){

                    Entity newObject = cloneObject(inventory[gp.handler.inventorySelector]);
                    gp.objects[gp.currentLevel][gp.currentRoom][i] = newObject;

                    switch(direction){
                        case "up","up-left","up-right" -> {
                            newObject.worldX = gp.player.worldX + gp.tileSize / 2;
                            newObject.worldY = gp.player.worldY - gp.tileSize;
                        }
                        case "down","down-left","down-right" -> {
                            newObject.worldX = gp.player.worldX + gp.tileSize / 2;
                            newObject.worldY = gp.player.worldY + gp.tileSize * 2;
                        }
                        case "right" -> {
                            newObject.worldX = gp.player.worldX + gp.tileSize * 2;
                            newObject.worldY = gp.player.worldY + gp.tileSize / 2;
                        }
                        case "left" -> {
                            newObject.worldX = gp.player.worldX - gp.tileSize;
                            newObject.worldY = gp.player.worldY + gp.tileSize / 2;
                        }
                    }

                    inventory[gp.handler.inventorySelector] = null;
                    return;
                }
            }

        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        int x = screenX;
        int y = screenY;

        int width = 0;
        int height = 0;

        if(!attacking){
            width = gp.tileSize * 2;
            height = gp.tileSize * 2;

            if(gp.handler.upPressed || gp.handler.downPressed || gp.handler.leftPressed || gp.handler.rightPressed){
                switch(direction){
                    case "up" -> image = up[playerClass][spriteNum];
                    case "down" -> image = down[playerClass][spriteNum];
                    case "left","up-left","down-left" -> image = left[playerClass][spriteNum];
                    case "right","up-right","down-right" -> image = right[playerClass][spriteNum];
                }
            }
            else{
                if(spriteNum > 1){
                    spriteNum = 0;
                }

                switch(direction){
                    case "up" -> image = upIdle[playerClass][spriteNum];
                    case "down" -> image = downIdle[playerClass][spriteNum];
                    case "left","up-left","down-left" -> image = leftIdle[playerClass][spriteNum];
                    case "right","up-right","down-right" -> image = rightIdle[playerClass][spriteNum];
                }
            }
        }
        if(attacking){
            if(playerClass == warriorClass){
                switch(direction){
                    case "up" -> {
                        image = upAttack[playerClass][weaponIndex][spriteNum]; width = gp.tileSize * 2; height = gp.tileSize * 3; y = screenY - gp.tileSize;
                    }
                    case "down" -> {
                        image = downAttack[playerClass][weaponIndex][spriteNum]; width = gp.tileSize * 2; height = gp.tileSize * 3; y = screenY + gp.tileSize / 2 - 12;
                    }
                    case "left","up-left","down-left" -> {
                        image = leftAttack[playerClass][weaponIndex][spriteNum]; height = gp.tileSize * 2; width = gp.tileSize * 3; x = screenX - gp.tileSize * 2 + 14;
                    }
                    case "right","up-right","down-right" -> {
                        image = rightAttack[playerClass][weaponIndex][spriteNum]; height = gp.tileSize * 2; width = gp.tileSize * 3; x = screenX + gp.tileSize - 14;
                    }
                }
            }
            if(playerClass == wizardClass){
                switch(direction){
                    case "up" -> {
                        image = upAttack[playerClass][weaponIndex][spriteNum]; width = gp.tileSize * 2; height = gp.tileSize * 2; y = screenY;
                    }
                    case "down" -> {
                        image = downAttack[playerClass][weaponIndex][spriteNum]; width = gp.tileSize * 2; height = gp.tileSize * 3; y = screenY + gp.tileSize / 2 - 15;
                    }
                    case "left","up-left","down-left" -> {
                        image = leftAttack[playerClass][weaponIndex][spriteNum]; height = gp.tileSize * 2; width = gp.tileSize * 3; x = screenX - gp.tileSize - 6;
                    }
                    case "right","up-right","down-right" -> {
                        image = rightAttack[playerClass][weaponIndex][spriteNum]; height = gp.tileSize * 2; width = gp.tileSize * 3; x = screenX + 6;
                    }
                }
            }

            if(playerClass == rangerClass){
                width = gp.tileSize * 2;
                height = gp.tileSize * 2;

                switch(direction){
                    case "up" -> {
                        image = upAttack[playerClass][weaponIndex][spriteNum];
                    }
                    case "down" -> {
                        image = downAttack[playerClass][weaponIndex][spriteNum];
                    }
                    case "left","up-left","down-left" -> {
                        image = leftAttack[playerClass][weaponIndex][spriteNum];
                    }
                    case "right","up-right","down-right" -> {
                        image = rightAttack[playerClass][weaponIndex][spriteNum];
                    }
                }
            }

            if(monsterIndex != 999){

                if(gp.monsters[gp.currentLevel][gp.currentRoom][monsterIndex] != null && playerClass != rangerClass){
                    Entity monster = gp.monsters[gp.currentLevel][gp.currentRoom][monsterIndex];

                    int monsterX = monster.worldX - worldX + screenX;
                    int monsterY = monster.worldY - worldY + screenY;

                    g2.setFont(new Font("Arial", Font.BOLD,15));

                    g2.setColor(Color.white);
                    g2.drawString(String.valueOf(Math.round(inventory[0].damage * strength)),monsterX + gp.tileSize / 2,monsterY + 10);
                }


            }
        }

        if(parrying){
            width = gp.tileSize * 2;
            height = gp.tileSize * 2;

            switch(direction){
                case "up" -> image = upParry[spriteNum];
                case "down" -> image = downParry[spriteNum];
                case "left","up-left","down-left" -> image = leftParry[spriteNum];
                case "right","up-right","down-right" -> image = rightParry[spriteNum];
            }
        }

        g2.drawImage(image,x,y,width,height,null);

    }
}
