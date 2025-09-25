package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[20]; //numero de tiles diferentes 9/20
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/map_01.txt");
    }

    public void getTileImage(){

        setup(0, "/tiles/floor.png", true);
        setup(1, "/tiles/air.png", false);
        setup(2, "/tiles/ceiling.png", true);
        setup(3, "/tiles/spikes.png", true);
        setup(4, "/tiles/floor_left.png", true);
        setup(5, "/tiles/floor_right.png", true);
        setup(6, "/tiles/fundation.png", true);
        setup(7, "/tiles/sky.png", true);
        setup(8, "/tiles/ceiling_ramp_left.png", true);
        setup(9, "/tiles/ceiling_ramp_right.png", true);
    }

    public void setup(int index, String imagePath, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                
                String line = br.readLine();
                
                while (col < gp.maxWorldCol) {
                    
                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }   
    }

    public void draw(Graphics2D g2){

        // 0 - floor
        // 1 - air
        // 2 - ceiling
        // 3 - spikes
        // 4 - ramp left
        // 5 - ramp right
        // 6 - fundation
        // 7 - sky
        // 8 - ceiling ramp left
        // 9 - ceiling ramp right

        int WorldCol = 0;
        int WorldRow = 0;
        
        while (WorldCol < gp.maxWorldCol && WorldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[WorldCol][WorldRow];
            int worldX = WorldCol * gp.tileSize;
            int worldY = WorldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
               
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                
            }

            WorldCol++;

            if(WorldCol == gp.maxWorldCol){
                WorldCol = 0;
                WorldRow++;
            }
            
        }
    }
}
