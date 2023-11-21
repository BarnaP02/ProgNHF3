package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    //Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        //tile = new Tile[10];
        //getTileImage();
        loadMap("/maps/map023.txt");
    }
/*
    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass2.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/concrete2.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water2v2.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/structure.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/structure_door.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }*/
    private boolean isValidHexagon(int col, int row) {
        return row >= 0 && row < gp.maxWorldRow && col >= 0 && col < gp.maxWorldCol;
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = br.readLine();
            String[] init = line.split(",");
            gp.maxWorldCol = Integer.parseInt(init[0]);
            gp.maxWorldRow = Integer.parseInt(init[1]);
            int numOfUnits = Integer.parseInt(init[2]);
            mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
            gp.Grid= new Tile[gp.maxWorldCol][gp.maxWorldRow];
            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow){
                line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] pure = line.split(",");
                    String[] numbers = pure[1].split("-");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    String type = getTileType(num);
                    gp.Grid[col][row] = new Tile(type);
                    gp.Grid[col][row].coords[0] = col;
                    gp.Grid[col][row].coords[1] = row;
                    ++col;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    ++row;
                }
            }
            for (int r = 0; r < gp.maxWorldRow; ++r) {
                for (int c = 0; c < gp.maxWorldCol;++c) {
                    for (int i = 0; i < 6; i++) {
                        int newCol;
                        int newRow;
                        if (r % 2 == 0) {
                            newCol = c + gp.neighborOffsetEven[i][0];
                            newRow = r + gp.neighborOffsetEven[i][1];
                        } else {
                            newCol = c + gp.neighborOffsetOdd[i][0];
                            newRow = r + gp.neighborOffsetOdd[i][1];
                        }
                        if (isValidHexagon(newCol, newRow)) {
                            gp.Grid[c][r].setBorder(i, gp.Grid[newCol][newRow]);
                        }
                    }
                }
            }
            for (int u = 0; u < numOfUnits; ++u){
                line = br.readLine();
                String[] unitInit = line.split(",");
                gp.aSetter.setUnit(gp, unitInit);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;
        //int x = gp.tileWidth/2;
        //int y  =0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];
            String key = getTileType(tileNum);

            int worldX = gp.tileWidth * worldCol;// + gp.tileWidth/2;
            int worldY = (gp.tileHeight * (worldRow)) / 4*3;// + gp.tileHeight;
            if (worldRow % 2 == 0) {
                worldX += gp.tileWidth/2;
            }
            int screenX;
            int screenY;
            screenX = worldX - gp.cruser.worldX + gp.cruser.getScreenX();
            screenY = worldY - gp.cruser.worldY + gp.cruser.getScreenY();
            if (screenX > - gp.tileWidth &&
                    screenX <  gp.screenWidth + gp.tileWidth &&
                    screenY > - gp.tileHeight &&
                    screenY < gp.screenHeight + gp.tileHeight){
                if (gp.Grid[worldCol][worldRow].isHighlighted){
                    g2.drawImage(gp.imagS.getTileGallery().get(key), screenX, screenY, gp.tileWidth, gp.tileHeight, null);
                }
                else {
                    g2.drawImage(gp.imagS.getTileOutOfRangeGallery().get(key), screenX, screenY, gp.tileWidth, gp.tileHeight, null);
                }
                //gp.Grid[worldCol][worldRow].worldX = worldX;
                //gp.Grid[worldCol][worldRow].worldY = worldY;
                //gp.Grid[worldCol][worldRow].screenX = screenX;
                //gp.Grid[worldCol][worldRow].screenY = screenY;
                gp.Grid[worldCol][worldRow].isOnScreen = true;
            }
            else gp.Grid[worldCol][worldRow].isOnScreen = false;
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
    public String getTileType(int tileNum){

        String key = "";
        switch (tileNum){
            case 1:
                key+="concrete";
                break;
            case 2:
                key+="water";
                break;
            case 3:
                key+="structure";
                break;
            case 4:
                key+="structure_door";
                break;
            default:
                key+="grass";
        }
        return key;
    }
}
