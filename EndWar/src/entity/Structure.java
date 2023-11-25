package entity;

import main.GamePanel;
import tile.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Structure {
    GamePanel gp;
    protected BufferedImage image;
    protected String type;
    protected Tile tile;
    protected int worldX,worldY,trueWorldX,trueWorldY;
    int side;
    ArrayList<SuperUnit> inventory = new ArrayList<>();
    public Structure(GamePanel gp, String type, Tile tile, int side){
        this.gp = gp;
        this.type = type;
        this.tile = tile;
        this.side = side;
        trueWorldX = gp.getCoordsFromTile(tile)[0];
        trueWorldY = gp.getCoordsFromTile(tile)[1];
        worldX = trueWorldX - gp.tileWidth/2;
        worldY = trueWorldY - (int)(gp.tileHeight*1.5);
    }

    public void draw(Graphics2D g2, GamePanel gp){

        int screenX;
        int screenY;

        screenX = worldX - gp.cruser.worldX + gp.cruser.getScreenX();
        screenY = worldY - gp.cruser.worldY + gp.cruser.getScreenY();
        if (screenX > - gp.tileWidth &&
                screenX <  gp.screenWidth + gp.tileWidth &&
                screenY > - gp.tileHeight &&
                screenY < gp.screenHeight + gp.tileHeight){
            if (tile.isHighlighted() && side == 0){
                image = gp.imagS.getStructureGallery().get(type);
            }
            else{
                //image = gp.imagS.getGalleryShaded().get(this.getClass()).get(currentImgIndex);
            }
            g2.drawImage(image, screenX, screenY,null);
        }
    }
}
