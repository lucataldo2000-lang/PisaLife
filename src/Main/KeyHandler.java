package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public int titleSelector;
    public int classSelector;
    public boolean upPressed,downPressed,leftPressed,rightPressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e){

        int code = e.getKeyCode();

        if(gp.gameState == gp.playState){
            switch (code){
                case KeyEvent.VK_W -> upPressed = true;
                case KeyEvent.VK_S -> downPressed = true;
                case KeyEvent.VK_D -> rightPressed = true;
                case KeyEvent.VK_A -> leftPressed = true;
                case KeyEvent.VK_UP -> upPressed = true;
                case KeyEvent.VK_DOWN -> downPressed = true;
                case KeyEvent.VK_RIGHT -> rightPressed = true;
                case KeyEvent.VK_LEFT -> leftPressed = true;
            }
        }
        if(gp.gameState == gp.titleState){
            switch(code){
                case KeyEvent.VK_W -> {if(titleSelector - 1 == -1){titleSelector = 3;} else{titleSelector--;}}
                case KeyEvent.VK_S -> {if(titleSelector + 1 == 4){titleSelector = 0;} else{titleSelector++;}}
                case KeyEvent.VK_UP -> {if(titleSelector - 1 == -1){titleSelector = 3;} else{titleSelector--;}}
                case KeyEvent.VK_DOWN -> {if(titleSelector + 1 == 4){titleSelector = 0;} else{titleSelector++;}}
                case KeyEvent.VK_ENTER -> {
                    switch(titleSelector){
                        case 0 -> gp.gameState = gp.classChooserState;
                        case 3 -> System.exit(0);
                    }
                }
            }
        }
        if(gp.gameState == gp.classChooserState){
            switch(code){
                case KeyEvent.VK_A -> {if(classSelector - 1 == -1){classSelector = 2;} else{classSelector--;}}
                case KeyEvent.VK_D -> {if(classSelector + 1 == 3){classSelector = 0;} else{classSelector++;}}
                case KeyEvent.VK_LEFT -> {if(classSelector - 1 == -1){classSelector = 2;} else{classSelector--;}}
                case KeyEvent.VK_RIGHT -> {if(classSelector + 1 == 3){classSelector = 0;} else{classSelector++;}}
                /*case KeyEvent.VK_ENTER -> {
                    gp.gameState = gp.playState;
                    switch(classSelector){
                        case 0 -> gp.player.playerClass = gp.player.warriorClass;
                        case 1 -> gp.player.playerClass = gp.player.wizardClass;
                        case 2 -> gp.player.playerClass = gp.player.rangerClass;
                    }
                }

                 */
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();

        if(gp.gameState == gp.playState){
            switch (code){
                case KeyEvent.VK_W -> upPressed = false;
                case KeyEvent.VK_S -> downPressed = false;
                case KeyEvent.VK_D -> rightPressed = false;
                case KeyEvent.VK_A -> leftPressed = false;
                case KeyEvent.VK_UP -> upPressed = false;
                case KeyEvent.VK_DOWN -> downPressed = false;
                case KeyEvent.VK_RIGHT -> rightPressed = false;
                case KeyEvent.VK_LEFT -> leftPressed = false;
            }
        }
    }

}
