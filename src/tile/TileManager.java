package src.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import src.main.GamePanel;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    ArrayList<String> fileNames = new ArrayList<String>();
    ArrayList<String> collisionStatus = new ArrayList<String>();

    public TileManager(GamePanel gp) {
        this.gp = gp;

        InputStream is = getClass().getResourceAsStream("/res/maps/tileData.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;

        try{
            while((line = br.readLine()) != null) {
                fileNames.add(line);
                collisionStatus.add(br.readLine());
            }
            br.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        tile = new Tile[fileNames.size()];
        getTileImage();

        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        // load tile images
       // getTileImage();
        loadMap();
    
    }

    public void getTileImage() {

        try{
           
            for(int i = 0; i < fileNames.size(); i++) { 

                String fileName;
                boolean collision;

                fileName = fileNames.get(i);

                if(collisionStatus.get(i).equals("true")) {
                    collision = true;
                }
                else {
                    collision = false;
                }
            

                tile[i] = new Tile();
                tile[i].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + fileName));
                tile[i].collision = collision;
            }
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(){

        try{
            InputStream is = getClass().getResourceAsStream("/res/maps/TestMap.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while(col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;
   

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            worldCol++;
       

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
         
                worldRow++;
          
            }
        }


    
    }

}
