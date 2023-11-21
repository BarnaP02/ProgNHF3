package entity;

import main.GamePanel;
import tile.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperUnit extends Entity{
    protected BufferedImage lastImage;
    protected String type;
    protected Tile currentTile;
    //grass, hill, forest, concrete, shallow water, deep water
    protected double[] traverseSpeed = new double[5];
    protected int currentImgIndex = 0;
    protected int lastImgIndex = 0;
    protected boolean isHeli = false;
    protected boolean isArtyAbleToFire = false;
    protected boolean isArtyNotAbleToFire = false;
    protected boolean isAvian = false;
    protected boolean isInRange = false;
    protected int movementRange;
    protected boolean wasHighlighted = true;
    boolean acted = false;

    public void draw(Graphics2D g2, GamePanel gp){

        int screenX;
        int screenY;
        ++slowCounter;
        ++superSlowCounter;
        if (slowCounter==slowNum) slowCounter = 0;
        if (superSlowCounter==superSlowNum) superSlowCounter = 0;
        if(isHeli){
            currentImgIndex = direction*3+(slowCounter)/2;
        }
        else if (isArtyAbleToFire){
            currentImgIndex = direction*2;
        }
        else if(isArtyNotAbleToFire){
            currentImgIndex = direction*2+1;
        } else if (this.getClass()== U_sub.class) {
            currentImgIndex = direction*2;
        } else {
            currentImgIndex = direction;
        }

        screenX = worldX - gp.cruser.worldX + gp.cruser.getScreenX();
        screenY = worldY - gp.cruser.worldY + gp.cruser.getScreenY();
        if (screenX > - gp.tileWidth &&
                screenX <  gp.screenWidth + gp.tileWidth &&
                screenY > - gp.tileHeight &&
                screenY < gp.screenHeight + gp.tileHeight){
            gp.testUnit = this;
            if (currentImgIndex != lastImgIndex || wasHighlighted != getCurrentTile().isHighlighted()) {
                wasHighlighted = getCurrentTile().isHighlighted();
                if (getCurrentTile().isHighlighted()){
                    lastImage = gp.imagS.getGallery().get(this.getClass()).get(currentImgIndex);
                }
                else{
                    lastImage = gp.imagS.getGalleryShaded().get(this.getClass()).get(currentImgIndex);
                }
            }
            if (isAvian){
                screenY -= Math.abs(2-superSlowCounter/30)-1;
            }
            g2.drawImage(lastImage, screenX, screenY, gp.tileWidth, gp.tileHeight, null);
            lastImgIndex = currentImgIndex;
        }
    }
    public void setCurrentTile(GamePanel gp, int x, int y){
        currentTile = gp.Grid[x-1][y-1];
    }
    public void moveUnit(GamePanel gp, Tile t){
        currentTile = t;
        worldX = gp.getCoordsFromTile(t)[0];
        worldY = gp.getCoordsFromTile(t)[1];
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }
    public int getMovementRange() {
        return movementRange;
    }
    public void setMovementRange(int movementRange) {
        this.movementRange = movementRange;
    }

    public double[] getTraverseSpeed() {
        return traverseSpeed;
    }

    public void setTraverseSpeed(double[] traverseSpeed) {
        this.traverseSpeed = traverseSpeed;
    }
}
