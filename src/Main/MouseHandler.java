package Main;

import Entity.Projectile;

import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MouseHandler implements MouseWheelListener, MouseListener{

    GamePanel gp;
    boolean done = false;

    public MouseHandler(GamePanel gp){this.gp = gp;}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        switch(gp.gameState){
            case 0 -> {

                if(e.getWheelRotation() < 0){
                    if(gp.handler.inventorySelector + 1 == 5){gp.handler.inventorySelector = 0;} else{gp.handler.inventorySelector++;}
                }
                else{
                    if(gp.handler.inventorySelector - 1 == -1){gp.handler.inventorySelector = 4;} else{gp.handler.inventorySelector--;}
                }
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int button = e.getButton();

        switch (button){
            case MouseEvent.BUTTON1 -> {
                if(!gp.player.attacking && gp.player.inventory[0] != null && !gp.player.parrying){
                    gp.player.attacking = true;
                    gp.player.damageDone = true;
                    gp.player.spriteNum = 0;
                    gp.player.spriteCounter = 0;
                }
            }
            case MouseEvent.BUTTON3 -> {

                if(gp.player.inventory[gp.handler.inventorySelector] != null && gp.player.inventory[gp.handler.inventorySelector].objType == gp.player.consumable){

                    if(gp.player.life < gp.player.maxLife){

                        gp.player.life += ((gp.player.inventory[gp.handler.inventorySelector].life / 100) * gp.player.maxLife);
                        gp.player.inventory[gp.handler.inventorySelector] = null;

                        if(gp.player.life > gp.player.maxLife){
                            gp.player.life = gp.player.maxLife;
                        }
                    }

                }

                if(gp.player.playerClass == gp.player.wizardClass && gp.player.inventory[1] != null && !gp.player.attacking && !gp.player.damageDone){
                    for (int i = 0; i < gp.projectiles[gp.currentLevel][gp.currentRoom].length; i++) {
                        if (gp.projectiles[gp.currentLevel][gp.currentRoom][i] == null) {

                            int x = gp.player.worldX;
                            int y = gp.player.worldY;

                            switch (gp.player.direction) {
                                case "up", "up-right", "down-right" -> x += gp.player.solidArea.width / 2;
                                case "down", "up-left", "down-left" -> {
                                    x += gp.player.solidArea.width / 2;
                                    y += gp.player.solidArea.height;
                                }
                                case "left" -> {
                                    y += gp.player.solidArea.height / 2;
                                }
                                case "right" -> {
                                    x += gp.player.solidArea.width;
                                    y += gp.player.solidArea.height / 2;
                                }
                            }



                            gp.player.damageDone = true;

                            switch(gp.player.inventory[1].name){
                                case "Basic Orb" -> gp.projectiles[gp.currentLevel][gp.currentRoom][i] = new Projectile(gp, 20, x, y, gp.player.direction, 7,0,40,40,-4,-4,20);
                                case "Ice Orb" -> gp.projectiles[gp.currentLevel][gp.currentRoom][i] = new Projectile(gp, 20, x, y, gp.player.direction, 8,3,40,40,-4,-4,15);
                            }

                            return;
                        }
                    }
                }

                if(gp.player.playerClass == gp.player.warriorClass && gp.player.inventory[1] != null && !gp.player.attacking && !gp.player.parrying){
                    gp.player.parrying = true;
                    gp.player.spriteNum = 0;
                    gp.player.spriteCounter = 0;
                }

            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
