package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager extends Tile{

    public GamePanel gp;
    public int[][][] tileNum;
    Tile[] tiles;

    public TileManager(GamePanel gp){
        this.gp = gp;

        tiles = new Tile[50];

        tileNum = new int[gp.maxLevel][gp.maxWorldCol][gp.maxWorldRow];

        getTileImages();

        loadLevel("/Levels/Level1",1);
    }


    public void getTileImages() {
        try {
            tiles[11] = new Tile();
            tiles[11].image = ImageIO.read(getClass().getResourceAsStream("/TilesTextures/SBrick0.png"));
            tiles[11].collision = true;

            tiles[12] = new Tile();
            tiles[12].image = ImageIO.read(getClass().getResourceAsStream("/TilesTextures/woodplank.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadLevel(String file, int level){

        try{
            InputStream is = getClass().getResourceAsStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = reader.readLine();

                while(col < gp.maxWorldCol){

                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    tileNum[level][col][row] = num;
                    col++;

                }

                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }

            }

            reader.close();

        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void drawLevel(Graphics2D g2){

        int col = 0;
        int row = 0;


        while(col < gp.maxWorldCol && row < gp.maxWorldRow){


            while(col < gp.maxWorldCol){

                int num = tileNum[gp.currentLevel][col][row];

                int x = col * gp.tileSize;
                int y = row * gp.tileSize;

                int screenX = x - gp.player.worldX + gp.player.screenX;
                int screenY = y - gp.player.worldY +  gp.player.screenY;

                if(tiles[num] != null && x > gp.player.worldX - gp.player.screenX - gp.tileSize && x < gp.player.worldX + gp.player.screenX + gp.tileSize && y > gp.player.worldY - gp.player.screenY - gp.tileSize && y < gp.player.worldY + gp.player.screenY + gp.tileSize){
                    g2.drawImage(tiles[num].image,screenX,screenY,gp.tileSize,gp.tileSize,null);

                }

                col++;

            }

            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }

        }
    }

}
