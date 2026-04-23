package Main;

import java.awt.event.*;

public class MouseHandler implements MouseWheelListener, MouseListener{

    GamePanel gp;

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
                if(!gp.player.attacking){
                    gp.player.attacking = true;
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
