package Entity;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;

    public int worldX;
    public int worldY;

    public String name;
    public String direction;
    public int type;
    public final int playerType = 1;
    public final int npcType = 2;
    public final int monsterType = 3;
    public int life;
    public int maxLife;
    public int speed;
    public int attack;
    public int defence;
    public Rectangle solidArea;

    BufferedImage[] up = new BufferedImage[3];
    BufferedImage[] down = new BufferedImage[3];
    BufferedImage[] left = new BufferedImage[3];
    BufferedImage[] right = new BufferedImage[3];

    public Entity(GamePanel gp){

        this.gp = gp;
    }


}
