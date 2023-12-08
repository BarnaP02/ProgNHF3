package tile;

import entity.SuperStructure;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.lang.foreign.StructLayout;
import java.util.ArrayList;
import java.util.List;

public class TileManager {
    GamePanel gp;

    /***
     * constructor for TileManager
     * @param gp for context
     */
    public TileManager(GamePanel gp){
        this.gp = gp;

    }

    /***
     *returns if a tile at these coordinates would be valid or not
     * @param col x coordinate in the grid
     * @param row y coordinate in the grid
     * @return weather or not it is a valid spot
     */
    private boolean isValidHexagon(int col, int row) {
        return row >= 0 && row < gp.maxWorldRow && col >= 0 && col < gp.maxWorldCol;
    }

    /***
     * this reads the txt file of the map and creates the tiles, structures and units of the game
     * @param filePath the path to the map file
     */
    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = br.readLine();
            String[] init = line.split(",");
            gp.maxWorldCol = Integer.parseInt(init[0]);
            gp.maxWorldRow = Integer.parseInt(init[1]);
            int numOfUnits = Integer.parseInt(init[2]);
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
                    String type = getTileType(num);
                    gp.Grid[col][row] = new Tile(type);
                    gp.Grid[col][row].getCoords()[0] = col;
                    gp.Grid[col][row].getCoords()[1] = row;
                    gp.Grid[col][row].worldX = gp.getCoordsFromTile(gp.Grid[col][row])[0];
                    gp.Grid[col][row].worldY = gp.getCoordsFromTile(gp.Grid[col][row])[1];
                    BufferedImage image = null;
                    double randy = Math.random();
                    int validIndex = (int) (randy*gp.imagS.getTileGallery().get(type).size());
                    image = gp.imagS.getTileGallery().get(type).get(validIndex);
                    if (traverse == 1){
                        gp.Grid[col][row].setRoad(true);
                    }
                    if (traverse == 2){
                        gp.Grid[col][row].setObstacle(true);
                    }
                    gp.Grid[col][row].setImage(image);
                    gp.Grid[col][row].setRed(gp.imagS.getTileGallery().get("in_range").get(0));
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
                            newCol = c + GamePanel.getNeighborOffsetEven()[i][0];
                            newRow = r + GamePanel.getNeighborOffsetEven()[i][1];
                        } else {
                            newCol = c + GamePanel.getNeighborOffsetOdd()[i][0];
                            newRow = r + GamePanel.getNeighborOffsetOdd()[i][1];
                        }
                        if (isValidHexagon(newCol, newRow)) {
                            gp.Grid[c][r].setBorder(i, gp.Grid[newCol][newRow]);
                        }
                    }
                    if (gp.Grid[c][r].getType().equals("depot")){
                        gp.Grid[c][r].setStructureDoor(true);
                        gp.Grid[c][r].getBorder(0).setStructure(true);
                        gp.Grid[c][r].getBorder(5).setStructure(true);
                        gp.Grid[c][r].getBorder(0).getBorder(5).setStructure(true);
                        gp.structures.add(new SuperStructure(gp,"depot",gp.Grid[c][r], 2));
                    }
                    if (gp.Grid[c][r].getType().equals("harbor")){
                        gp.Grid[c][r].setStructureDoor(true);
                        gp.Grid[c][r].getBorder(0).setStructure(true);
                        gp.Grid[c][r].getBorder(5).setStructure(true);
                        gp.Grid[c][r].getBorder(0).getBorder(5).setStructure(true);
                        gp.structures.add(new SuperStructure(gp,"harbor",gp.Grid[c][r], 2));
                    }
                    if (gp.Grid[c][r].getType().equals("factory")){
                        gp.Grid[c][r].setStructureDoor(true);
                        gp.Grid[c][r].getBorder(0).setStructure(true);
                        gp.Grid[c][r].getBorder(5).setStructure(true);
                        gp.Grid[c][r].getBorder(0).getBorder(5).setStructure(true);
                        gp.structures.add(new SuperStructure(gp,"factory",gp.Grid[c][r], 2));
                    }
                    if (gp.Grid[c][r].getType().equals("water")){
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
                    if (gp.Grid[c][r].isObstacle()){
                        double randy = Math.random();
                        int validIndex = (int) (randy*gp.imagS.getObstacleGallery().get(gp.Grid[c][r].getType()).size());
                        BufferedImage obstacle = gp.imagS.getObstacleGallery().get(gp.Grid[c][r].getType()).get(validIndex);
                        gp.Grid[c][r].setImage(gp.imagS.combineImages(gp.Grid[c][r].getImage(), obstacle));
                    }
                    if (gp.Grid[c][r].isRoad()){
                        StringBuilder roadCode = new StringBuilder();
                        for (int i = 0; i < 6; i++) {
                            if (gp.Grid[c][r].borders()[i]==1 && gp.Grid[c][r].getBorder(i).isRoad()){
                                roadCode.append("1");
                            }
                            else {
                                roadCode.append("0");
                            }
                        }
                        BufferedImage road = null;
                        if (gp.Grid[c][r].getType().equals("water")){
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

    /***
     * this is a part of loadMap for when we don't need the map to be read again but the tiles need their images
     */
    public void loadMapFromSave(){

        for (int r = 0; r < gp.maxWorldRow; ++r) {
            for (int c = 0; c < gp.maxWorldCol;++c) {
                BufferedImage image = null;
                double randy = Math.random();
                int validIndex = (int) (randy*gp.imagS.getTileGallery().get(gp.Grid[c][r].getType()).size());
                image = gp.imagS.getTileGallery().get(gp.Grid[c][r].getType()).get(validIndex);
                gp.Grid[c][r].setImage(image);
                gp.Grid[c][r].setRed(gp.imagS.getTileGallery().get("in_range").get(0));

                if (gp.Grid[c][r].getType().equals("water")){
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
                if (gp.Grid[c][r].isObstacle()){
                    randy = Math.random();
                    validIndex = (int) (randy*gp.imagS.getObstacleGallery().get(gp.Grid[c][r].getType()).size());
                    BufferedImage obstacle = gp.imagS.getObstacleGallery().get(gp.Grid[c][r].getType()).get(validIndex);
                    gp.Grid[c][r].setImage(gp.imagS.combineImages(gp.Grid[c][r].getImage(), obstacle));
                }
                if (gp.Grid[c][r].isRoad()){
                    StringBuilder roadCode = new StringBuilder();
                    for (int i = 0; i < 6; i++) {
                        if (gp.Grid[c][r].borders()[i]==1 && gp.Grid[c][r].getBorder(i).isRoad()){
                            roadCode.append("1");
                        }
                        else {
                            roadCode.append("0");
                        }
                    }
                    BufferedImage road;
                    if (gp.Grid[c][r].getType().equals("water")){
                        road = gp.imagS.getRoadGallery().get(roadCode +"w");
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
    }

    /***
     * this is for drawing the tiles
     * @param g2 for drawing
     */
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;
        //int x = gp.tileWidth/2;
        //int y  =0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int worldX = gp.tileWidth * worldCol;
            int worldY = (gp.tileHeight * (worldRow)) / 4*3;
            if (worldRow % 2 == 0) {
                worldX += gp.tileWidth/2;
            }
            int screenX;
            int screenY;
            screenX = worldX - gp.cruser.getWorldX() + gp.cruser.getScreenX();
            screenY = worldY - gp.cruser.getWorldY() + gp.cruser.getScreenY();
            if (screenX > - gp.tileWidth &&
                    screenX <  gp.screenWidth + gp.tileWidth &&
                    screenY > - gp.tileHeight &&
                    screenY < gp.screenHeight + gp.tileHeight){
                if (gp.Grid[worldCol][worldRow].isHighlighted()){
                    g2.drawImage(gp.Grid[worldCol][worldRow].getImage(), screenX, screenY,null);
                }
                else {
                    g2.drawImage(gp.Grid[worldCol][worldRow].getImageShaded(), screenX, screenY,null);
                }
                if (gp.Grid[worldCol][worldRow].isInRange()){
                    g2.drawImage(gp.Grid[worldCol][worldRow].getRed(), screenX, screenY,null);
                }
                gp.Grid[worldCol][worldRow].setOnScreen(true);
            }
            else gp.Grid[worldCol][worldRow].setOnScreen(false);
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    /***
     * this is just a thing I thought would need more times
     * @param tileNum the tile number of this tile
     * @return the corresponding string key for that number
     */
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

    /***
     * the opposite of getTileType
     * @param key the string key that we can use for searching in image galleries
     * @return the corresponding tileNum
     */
    public int getTileNum(String key){
        return switch (key) {
            case "woods" -> 1;
            case "concrete" -> 2;
            case "water" -> 3;
            case "ocean" -> 4;
            case "depot" -> 5;
            case "harbor" -> 6;
            case "factory" -> 7;
            default -> 0;
        };
    }
}
