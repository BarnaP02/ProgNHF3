package tile;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Tile implements Serializable {
    private transient BufferedImage image;
    private transient BufferedImage imageShaded;
    private transient BufferedImage red;
    private String type;
    private int[] coords;
    private Tile[] border;
    private final int[] borders;
    public int worldX;
    public int worldY;
    private boolean isStructure;
    private boolean isObstacle;
    private boolean isStructureDoor;
    private boolean isRoad;
    private boolean isSelected;
    private boolean isHighlighted;
    private boolean isOnScreen;
    private boolean isVisited;
    private boolean isInRange;
    private int currentMoveRange;

    /***
     * the constructor for Tile
     * @param type the type of tile this is as a string
     */
    Tile(String type){
        image = null;
        this.type = type;
        coords = new int[2];
        border = new Tile[6];
        borders = new int[6];
        isStructure = false;
        isStructureDoor = false;
        isRoad = false;
        isSelected = false;
        isHighlighted = true;
        isVisited = false;
        isObstacle = false;
    }
    public void setBorder(int i, Tile t){
        border[i] = t;
    }

    public Tile getBorder(int i) {
        return border[i];
    }

    public int[] getCoords() {
        return coords;
    }

    /***
     * returns the number of bordering tiles
     * @return num of bordering tiles
     */
    public int numOfBorder(){
        int counter = 0;
        for(Tile b : border){
            if(b != null) counter++;
        }
        return counter;
    }

    /***
     * in the array 0 represents that there is no bordering tile and 1 that there is one
     * @return the int array representing the neighbors
     */
    public int[] borders(){
        for(int i = 0; i < 6; i++){
            if(border[i] != null) borders[i]=1;
            else borders[i]=0;
        }
        return borders;
    }

    public BufferedImage getImage() {
        return image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImageShaded() {
        return imageShaded;
    }

    public void setImageShaded(BufferedImage imageShaded) {
        this.imageShaded = imageShaded;
    }

    public BufferedImage getRed() {
        return red;
    }

    public void setRed(BufferedImage red) {
        this.red = red;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHighlighted() {
        return isHighlighted;
    }
    public void setIsHighlighted(boolean h) {
        isHighlighted = h;
    }

    public boolean isInRange() {
        return isInRange;
    }

    public void setInRange(boolean inRange) {
        isInRange = inRange;
    }

    public boolean isStructure() {
        return isStructure;
    }

    public boolean isObstacle() {
        return isObstacle;
    }

    public void setObstacle(boolean obstacle) {
        isObstacle = obstacle;
    }

    public void setStructure(boolean structure) {
        isStructure = structure;
    }

    public boolean isStructureDoor() {
        return isStructureDoor;
    }

    public void setStructureDoor(boolean structureDoor) {
        isStructureDoor = structureDoor;
    }

    public boolean isRoad() {
        return isRoad;
    }

    public void setRoad(boolean road) {
        isRoad = road;
    }

    public boolean isOnScreen() {
        return isOnScreen;
    }

    public void setOnScreen(boolean onScreen) {
        isOnScreen = onScreen;
    }
}
