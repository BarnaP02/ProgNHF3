package entity;

import main.GamePanel;
import main.Sound;
import tile.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperUnit extends Entity{
    protected BufferedImage lastImage;
    protected String type;
    protected Tile currentTile;
    protected Tile otherCurrentTile;
    //grass, structure, forest, concrete, water, ocean
    protected double[] traverseSpeed = new double[5];
    //ground, air, water
    protected int[] attackDamage = new int[2];
    protected int[] attackRange = new int[2];
    protected int hp = 6;
    protected int xp = 0;
    boolean acted = false;
    protected int currentImgIndex = 0;
    protected int lastImgIndex = 0;
    protected boolean isHeli = false;
    protected boolean isArtyAbleToFire = false;
    protected boolean isArtyNotAbleToFire = false;
    protected boolean isAvian = false;
    protected boolean isNavy = false;
    protected boolean is2tile = false;
    protected boolean isSub = false;

    protected boolean isInfantry = false;
    protected boolean isInRange = false;
    protected int movementRange;
    protected boolean wasHighlighted = true;
    Sound selectedSound = new Sound();
    Sound moveSound = new Sound();
    Sound attackSound = new Sound();

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
        } else if (isSub) {
            currentImgIndex = direction*2;
            if (currentTile.getType().equals("ocean")){
                currentImgIndex++;
            }
        } else {
            currentImgIndex = direction;
        }

        screenX = worldX - gp.cruser.worldX + gp.cruser.getScreenX();
        screenY = worldY - gp.cruser.worldY + gp.cruser.getScreenY();
        if (is2tile){
            int trueWorldX = worldX + gp.twoTileShift[direction][0];
            int trueWorldY = worldY + gp.twoTileShift[direction][1];
            screenX = trueWorldX - gp.cruser.worldX + gp.cruser.getScreenX();
            screenY = trueWorldY - gp.cruser.worldY + gp.cruser.getScreenY();
        }
        if (screenX > - gp.tileWidth &&
                screenX <  gp.screenWidth + gp.tileWidth &&
                screenY > - gp.tileHeight &&
                screenY < gp.screenHeight + gp.tileHeight){
            //gp.testUnit = this;
            if (currentImgIndex != lastImgIndex || wasHighlighted != getCurrentTile().isHighlighted()) {
                wasHighlighted = getCurrentTile().isHighlighted();
                if (getCurrentTile().isHighlighted()){
                    lastImage = gp.imagS.getGalleryTeam1().get(this.getClass()).get(currentImgIndex);
                }
                else{
                    lastImage = gp.imagS.getGalleryShaded().get(this.getClass()).get(currentImgIndex);
                }
            }
            if (isAvian){
                screenY -= Math.abs(2-superSlowCounter/30)-1;
            }
            if (isNavy){
                screenY -= Math.abs(1-superSlowCounter/60)-1;
            }
            g2.drawImage(lastImage, screenX, screenY,null);// gp.tileWidth, gp.tileHeight, null);
            lastImgIndex = currentImgIndex;
        }
    }
    public void setCurrentTile(GamePanel gp, int x, int y){
        currentTile = gp.Grid[x-1][y-1];
    }
    public void moveUnit(GamePanel gp, Tile t){
        currentTile = t;
        if (is2tile){
            setOtherCurrentTile(direction);
        }
        else {
            otherCurrentTile = currentTile;
        }
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

    public Tile getOtherCurrentTile() {
        return otherCurrentTile;
    }

    public void setOtherCurrentTile(Tile otherCurrentTile) {
        this.otherCurrentTile = otherCurrentTile;
    }
    public void setOtherCurrentTile(int direction) {
        int otherDirection = (direction+3)%6;
        //if ((otherDirection + 1) % 6 == 1){
        //    otherDirection -= 6;
        //}
        //otherDirection = (otherDirection + 1) % 6 - 1;
        setOtherCurrentTile(getCurrentTile().getBorder(otherDirection));
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

    public boolean isAvian() {
        return isAvian;
    }

    public boolean isNavy() {
        return isNavy;
    }
    public Sound getSelectedSound() {
        return selectedSound;
    }

    public Sound getMoveSound() {
        return moveSound;
    }

    public Sound getAttackSound() {
        return attackSound;
    }
}
