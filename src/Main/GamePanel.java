package Main;

import Entity.Entity;
import Entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{

    Graphics2D g2;

    public int tileSize = 32;
    public boolean fullScreen = false;

    public int currentLevel = 1;
    public int currentRoom = 1;
    public int screenCol = 16;
    public int screenRow = 10;
    public int screenWidth = screenCol * tileSize;
    public int screenHeight = screenRow * tileSize;
    public final int maxLevel = 3;
    public final int maxRoom = 10;
    public final int maxObj = 20;
    public final int maxMonsters = 5;

    public int maxWorldCol = 20;
    public int maxWorldRow = 20;

    public Thread gameThread;

    public final int fps = 30;

    public int gameState;
    public final int playState = 0;
    public final int titleState = 1;
    public final int deathState = 2;
    public final int dialogueState = 3;
    public final int classChooserState = 4;
    public final int pauseState = 5;

    public int waitTime;

    BufferedImage tempScreen;

    public TileManager tileManager = new TileManager(this);
    public KeyHandler handler = new KeyHandler(this);
    public Player player = new Player(this);
    public Collision checker = new Collision(this);
    public Assets setter = new Assets(this);
    public GUI gui = new GUI(this);
    public MouseHandler mouseHandler = new MouseHandler(this);
    public Entity[][][] objects = new Entity[maxLevel][maxRoom][maxObj];
    public Entity[][][] monsters = new Entity[maxLevel][maxRoom][maxMonsters];
    public EventChecker events = new EventChecker(this);

    public GamePanel(){
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setFocusable(true);
        this.addKeyListener(handler);
        this.addMouseWheelListener(mouseHandler);
        this.addMouseListener(mouseHandler);
    }

    public void startThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setUpGame(){
        gameState = titleState;
        setter.setObj();
        setter.setMonsters();

        tempScreen = new BufferedImage(screenWidth, screenHeight,BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();
    }

    @Override
    public void run(){

        double drawInterval = 1000000000 / fps;
        long currentTime = System.nanoTime();
        long lastTime;
        double delta = 0;

        while(gameThread != null){

            lastTime = System.nanoTime();

            delta += (lastTime - currentTime) / drawInterval;

            currentTime = lastTime;

            if(delta >= 1){

                update();

                draw();

                drawScreen();

                delta--;

            }
        }

    }

    public void update(){

        if(gameThread != null){

            if(gameState == playState){

                if(waitTime != 0){
                    waitTime = 0;
                }

                player.update();

                for(int i = 0; i < monsters[currentLevel][currentRoom].length; i++){
                    if(monsters[currentLevel][currentRoom][i] != null){
                        monsters[currentLevel][currentRoom][i].update();

                    }
                }

            }
            if(gameState == classChooserState){
                waitTime++;
            }
            if(gameState == pauseState){
                waitTime++;
            }

            System.out.println(player.worldX + " " + player.worldY);
            //System.out.println(player.worldX / tileSize + " " + player.worldY / tileSize);

        }

    }

    public void draw(){

        g2.setColor(Color.black);
        g2.fillRect(0,0, screenWidth,screenHeight);

        if(gameThread != null){

            tileManager.drawLevel(g2);

            for(int i = 0; i < objects[currentLevel][currentRoom].length; i++){
                if(objects[currentLevel][currentRoom][i] != null){
                    objects[currentLevel][currentRoom][i].draw(g2);
                }
            }

            for(int i = 0; i < monsters[currentLevel][currentRoom].length; i++){
                if(monsters[currentLevel][currentRoom][i] != null){
                    monsters[currentLevel][currentRoom][i].draw(g2);
                }
            }

            player.draw(g2);

            gui.draw(g2);

        }
    }

    public void drawScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempScreen,0,0,screenWidth,screenHeight,null);
        g.dispose();
    }

    public void setFullScreen(){

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        screenWidth = Main.window.getWidth();
        screenHeight = Main.window.getHeight();

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

}
