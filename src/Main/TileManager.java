package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager extends Tile{

    public GamePanel gp;
    public int[][][][] tileNum;
    Tile[] tiles;

    public TileManager(GamePanel gp){
        this.gp = gp;

        tiles = new Tile[50];

        tileNum = new int[gp.maxLevel][gp.maxRoom][gp.maxWorldCol][gp.maxWorldRow];

        getTileImages();

        for(int i = 0; i <= 9; i++){
            loadRoom("/Levels/Level1Room" + i,i);
        }
    }


    public void getTileImages() {
        try {
            tiles[11] = new Tile();
            tiles[11].image = ImageIO.read(getClass().getResourceAsStream("/TilesTextures/SBrick0.png"));
            tiles[11].collision = true;

            tiles[13] = new Tile();
            tiles[13].image = ImageIO.read(getClass().getResourceAsStream("/TilesTextures/LightBrick.png"));
            tiles[13].collision = true;

            tiles[14] = new Tile();
            tiles[14].image = ImageIO.read(getClass().getResourceAsStream("/TilesTextures/LightCrackedBrick.png"));
            tiles[14].collision = true;

            tiles[12] = new Tile();
            tiles[12].image = ImageIO.read(getClass().getResourceAsStream("/TilesTextures/woodplank.png"));

            tiles[15] = new Tile();
            tiles[15].image = ImageIO.read(getClass().getResourceAsStream("/TilesTextures/woodplank.png"));
            tiles[15].collision = true;

            tiles[17] = new Tile();
            tiles[17].image = ImageIO.read(getClass().getResourceAsStream("/TilesTextures/woodplank.png"));
            tiles[17].mobCollision = true;

            tiles[16] = new Tile();
            tiles[16].image = ImageIO.read(getClass().getResourceAsStream("/TilesTextures/brokenWoodPlank.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadRoom(String file, int room){

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

                    tileNum[gp.currentLevel][room][col][row] = num;
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

                int num = tileNum[gp.currentLevel][gp.currentRoom][col][row];

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
