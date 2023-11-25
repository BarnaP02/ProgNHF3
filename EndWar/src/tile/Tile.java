package tile;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage image;
    private BufferedImage imageShaded;
    String type;
    int coords[];
    Tile border[];
    private int borders[];
    public int worldX;
    public int worldY;
    //public int screenX;
    //public int screenY;
    boolean isStructure;
    boolean isObstacle;
    boolean isStructureDoor;
    boolean isRoad;
    boolean isSelected;
    boolean isHighlighted;
    boolean isOnScreen;
    boolean isVisited;
    int currentMoveRange;
    Tile cameFrom;
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

    public int numOfBorder(){
        int counter = 0;
        for(Tile b : border){
            if(b != null) counter++;
        }
        return counter;
    }

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

}
