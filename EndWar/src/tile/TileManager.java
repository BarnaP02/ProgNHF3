package tile;

import entity.Structure;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TileManager {
    GamePanel gp;
    //Tile[] tile;
    //int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        //tile = new Tile[10];
        //getTileImage();
        loadMap("/maps/map03.txt");
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
            //mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
            gp.Grid = new Tile[gp.maxWorldCol][gp.maxWorldRow];
            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow){
                line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] pure = line.split(",");
                    String[] numbers = pure[1].split("-");
                    String[] cell = numbers[col].split("");
                    int num = Integer.parseInt(cell[0]);
                    int traverse = Integer.parseInt(cell[1]);
                    //mapTileNum[col][row] = num;
                    String type = getTileType(num);
                    gp.Grid[col][row] = new Tile(type);
                    gp.Grid[col][row].coords[0] = col;
                    gp.Grid[col][row].coords[1] = row;
                    gp.Grid[col][row].worldX = gp.getCoordsFromTile(gp.Grid[col][row])[0];
                    gp.Grid[col][row].worldY = gp.getCoordsFromTile(gp.Grid[col][row])[1];
                    BufferedImage image = null;
                    double randy = Math.random();
                    int validIndex = (int) (randy*gp.imagS.getTileGallery().get(type).size());
                    image = gp.imagS.getTileGallery().get(type).get(validIndex);
                    if (traverse == 1){
                        gp.Grid[col][row].isRoad = true;
                    }
                    if (traverse == 2){
                        gp.Grid[col][row].isObstacle = true;
                    }
                    gp.Grid[col][row].setImage(image);
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
                            newCol = c + GamePanel.neighborOffsetEven[i][0];
                            newRow = r + GamePanel.neighborOffsetEven[i][1];
                        } else {
                            newCol = c + GamePanel.neighborOffsetOdd[i][0];
                            newRow = r + GamePanel.neighborOffsetOdd[i][1];
                        }
                        if (isValidHexagon(newCol, newRow)) {
                            gp.Grid[c][r].setBorder(i, gp.Grid[newCol][newRow]);
                        }
                    }
                    if (gp.Grid[c][r].type.equals("depot")){
                        gp.Grid[c][r].isStructureDoor = true;
                        gp.Grid[c][r].getBorder(0).isStructure = true;
                        gp.Grid[c][r].getBorder(5).isStructure = true;
                        gp.Grid[c][r].getBorder(0).getBorder(5).isStructure = true;
                        gp.structures.add(new Structure(gp,"depot",gp.Grid[c][r], 0));
                    }
                    if (gp.Grid[c][r].type.equals("harbor")){
                        gp.Grid[c][r].isStructureDoor = true;
                        gp.Grid[c][r].getBorder(0).isStructure = true;
                        gp.Grid[c][r].getBorder(5).isStructure = true;
                        gp.Grid[c][r].getBorder(0).getBorder(5).isStructure = true;
                        gp.structures.add(new Structure(gp,"harbor",gp.Grid[c][r], 0));
                    }
                    if (gp.Grid[c][r].type.equals("factory")){
                        gp.Grid[c][r].isStructureDoor = true;
                        gp.Grid[c][r].getBorder(0).isStructure = true;
                        gp.Grid[c][r].getBorder(5).isStructure = true;
                        gp.Grid[c][r].getBorder(0).getBorder(5).isStructure = true;
                        gp.structures.add(new Structure(gp,"factory",gp.Grid[c][r], 0));
                    }
                    if (gp.Grid[c][r].type.equals("water")){
                        StringBuilder waterCode = new StringBuilder();
                        for (int i = 0; i < 6; i++) {
                            if (gp.Grid[c][r].borders()[i]==1 && (gp.Grid[c][r].getBorder(i).getType().equals("grass") ||
                                    gp.Grid[c][r].getBorder(i).getType().equals("woods"))){
                                waterCode.append("1");
                            }
                            else if (gp.Grid[c][r].borders()[i]==1 && (gp.Grid[c][r].getBorder(i).getType().equals("concrete") ||
                                    gp.Grid[c][r].getBorder(i).getType().equals("depot") ||
                                    gp.Grid[c][r].getBorder(i).getType().equals("factory"))){
                                waterCode.append("2");
                            } else if (gp.Grid[c][r].borders()[i]==1 && i > 0 && i < 5 && gp.Grid[c][r].getBorder(i).getType().equals("harbor")) {
                                waterCode.append("2");
                            } else {
                                waterCode.append("0");
                            }
                        }
                        gp.Grid[c][r].setImage(gp.imagS.getWaterGallery().get(waterCode.toString()));
                    }
                    if (gp.Grid[c][r].isObstacle){
                        double randy = Math.random();
                        int validIndex = (int) (randy*gp.imagS.getObstacleGallery().get(gp.Grid[c][r].getType()).size());
                        BufferedImage obstacle = gp.imagS.getObstacleGallery().get(gp.Grid[c][r].getType()).get(validIndex);
                        gp.Grid[c][r].setImage(gp.imagS.combineImages(gp.Grid[c][r].getImage(), obstacle));
                        //List<BufferedImage> shading = new ArrayList<>();
                        //shading.add(gp.Grid[c][r].getImage());
                        //gp.Grid[c][r].setImageShaded(gp.imagS.changeColor(shading,-100,-100,-100,true).get(0));
                    }
                    if (gp.Grid[c][r].isRoad){
                        StringBuilder roadCode = new StringBuilder();
                        for (int i = 0; i < 6; i++) {
                            if (gp.Grid[c][r].borders()[i]==1 && gp.Grid[c][r].getBorder(i).isRoad){
                                roadCode.append("1");
                            }
                            else {
                                roadCode.append("0");
                            }
                        }
                        BufferedImage road = null;
                        if (gp.Grid[c][r].type.equals("water")){
                            road = gp.imagS.getRoadGallery().get(roadCode.toString()+"w");
                        }
                        else {
                            road = gp.imagS.getRoadGallery().get(roadCode.toString());
                        }
                        gp.Grid[c][r].setImage(gp.imagS.combineImages(gp.Grid[c][r].getImage(), road));
                    }
                    List<BufferedImage> shading = new ArrayList<>();
                    shading.add(gp.Grid[c][r].getImage());
                    gp.Grid[c][r].setImageShaded(gp.imagS.changeColor(shading,-70,-70,-70,true).get(0));
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

            //int tileNum = mapTileNum[worldCol][worldRow];
            //String key = getTileType(tileNum);

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
                    g2.drawImage(gp.Grid[worldCol][worldRow].getImage(), screenX, screenY,null);// gp.tileWidth, gp.tileHeight, null);
                }
                else {
                    g2.drawImage(gp.Grid[worldCol][worldRow].getImageShaded(), screenX, screenY,null);// gp.tileWidth, gp.tileHeight, null);
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
                key+="woods";
                break;
            case 2:
                key+="concrete";
                break;
            case 3:
                key+="water";
                break;
            case 4:
                key+="ocean";
                break;
            case 5:
                key+="depot";
                break;
            case 6:
                key+="harbor";
                break;
            case 7:
                key+="factory";
                break;
            default:
                key+="grass";
        }
        return key;
    }
}
