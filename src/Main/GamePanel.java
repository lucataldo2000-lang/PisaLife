package Main;

import AI.PathFinding;
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
    public final int maxObj = 50;
    public final int maxMonsters = 10;
    public final int maxParticles = 50;

    public int maxWorldCol = 20;
    public int maxWorldRow = 20;

    public Thread gameThread;

    public final int fps = 30;

    public int gameState;
    public final int playState = 0;
    public final int titleState = 1;
    public final int deathState = 2;
    public final int victoryState = 3;
    public final int classChooserState = 4;
    public final int pauseState = 5;
    public final int tutorialState = 6;

    public int waitTime;

    BufferedImage tempScreen;

    public PathFinding pathFinding = new PathFinding(this);
    public TileManager tileManager = new TileManager(this);
    public KeyHandler handler = new KeyHandler(this);
    public Player player = new Player(this);
    public Collision checker = new Collision(this);
    public Assets setter = new Assets(this);
    public GUI gui = new GUI(this);
    public MouseHandler mouseHandler = new MouseHandler(this);
    public Entity[][][] objects = new Entity[maxLevel][maxRoom][maxObj];
    public Entity[][][] monsters = new Entity[maxLevel][maxRoom][maxMonsters];
    public Entity[][][] projectiles = new Entity[maxLevel][maxRoom][maxParticles];
    public Entity[][][] particles = new Entity[maxLevel][maxRoom][maxParticles];
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
        setter.setMonsters();
        setter.setObj();

        tempScreen = new BufferedImage(screenWidth, screenHeight,BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();
    }

    @Override
    public void run(){

        double drawInterval = 1000000000f / fps;
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

                        if(monsters[currentLevel][currentRoom][i].life <= 0 && monsters[currentLevel][currentRoom][i].name.equals("Boss")){
                            gameState = victoryState;
                            System.out.println("Victory");
                            System.out.println(gameState);
                            monsters[currentLevel][currentRoom][i] = null;
                        }
                    }
                }

                for(int i = 0; i < objects[currentLevel][currentRoom].length; i++){
                    if(objects[currentLevel][currentRoom][i] != null && (objects[currentLevel][currentRoom][i].name.equals("Chest") || objects[currentLevel][currentRoom][i].name.equals("damageArea") || objects[currentLevel][currentRoom][i].name.equals("FallingRock"))){
                        objects[currentLevel][currentRoom][i].update();

                        if(objects[currentLevel][currentRoom][i].animFinished){
                            objects[currentLevel][currentRoom][i] = null;
                        }

                    }
                }

                for(int i = 0; i < projectiles[currentLevel][currentRoom].length; i++){
                    if(projectiles[currentLevel][currentRoom][i] != null){
                        projectiles[currentLevel][currentRoom][i].update();

                        if(projectiles[currentLevel][currentRoom][i].collisionOn){
                            if(projectiles[currentLevel][currentRoom][i].life > 0){
                                player.damageDone = false;
                            }

                            projectiles[currentLevel][currentRoom][i] = null;
                        }
                        if(projectiles[currentLevel][currentRoom][i]!= null && projectiles[currentLevel][currentRoom][i].life <= 0){
                            projectiles[currentLevel][currentRoom][i] = null;
                        }
                    }
                }

                for(int i = 0; i < particles[currentLevel][currentRoom].length; i++){
                    if(particles[currentLevel][currentRoom][i] != null){
                        particles[currentLevel][currentRoom][i].update();

                        if(particles[currentLevel][currentRoom][i].life <= 0){
                            particles[currentLevel][currentRoom][i] = null;
                        }

                    }
                }

            }
            if(gameState == classChooserState){
                waitTime++;
            }
            if(gameState == pauseState){
                waitTime++;
            }
            if(gameState == tutorialState){
                waitTime++;
            }

            //System.out.println(player.worldX + " " + player.worldY);
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

            for(int i = 0; i < projectiles[currentLevel][currentRoom].length; i++){
                if(projectiles[currentLevel][currentRoom][i] != null){
                    projectiles[currentLevel][currentRoom][i].draw(g2);
                }
            }

            for(int i = 0; i < objects[currentLevel][currentRoom].length; i++){
                if(objects[currentLevel][currentRoom][i] != null && (objects[currentLevel][currentRoom][i].name.equals("Chest") || objects[currentLevel][currentRoom][i].name.equals("damageArea") || objects[currentLevel][currentRoom][i].name.equals("FallingRock"))){
                    objects[currentLevel][currentRoom][i].draw(g2);

                }
            }

            player.draw(g2);

            for(int i = 0; i < particles[currentLevel][currentRoom].length; i++){
                if(particles[currentLevel][currentRoom][i] != null){
                    particles[currentLevel][currentRoom][i].draw(g2);
                }
            }

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
