package Main;

import java.awt.event.*;

public class MouseHandler implements MouseWheelListener {

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
}
