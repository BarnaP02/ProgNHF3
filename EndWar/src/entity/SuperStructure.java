package entity;

import main.GamePanel;
import tile.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class SuperStructure implements Serializable {
    private GamePanel gp;
    protected transient BufferedImage image;
    protected String type;
    protected Tile tile;
    protected int worldX,worldY,trueWorldX,trueWorldY;
    int side;
    private ArrayList<SuperUnit> inventory = new ArrayList<>();

    /***
     * this is the centralised structure, every kind of structure can be stored as this
     * @param gp needed for accessing information
     * @param type the type of the structure, in reality this changes what kind of units can be stored in it
     * @param tile the tile that this structure occupies
     * @param side this sets what side this structure is on
     */
    public SuperStructure(GamePanel gp, String type, Tile tile, int side){
        this.gp = gp;
        this.type = type;
        this.tile = tile;
        this.side = side;
        trueWorldX = gp.getCoordsFromTile(tile)[0];
        trueWorldY = gp.getCoordsFromTile(tile)[1];
        worldX = trueWorldX - gp.tileWidth/2;
        worldY = trueWorldY - (int)(gp.tileHeight*1.5);
    }

    /***
     * this paints this unit on the screen
     * @param g2 for drawing
     * @param gp to figure out the correct coordinates
     */
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
                image = gp.imagS.getStructureGalleryTeam1().get(type);
            }
            else if (tile.isHighlighted() && side == 1){
                image = gp.imagS.getStructureGalleryTeam2().get(type);
            }
            else if (tile.isHighlighted() && side == 2){
                image = gp.imagS.getStructureGalleryNatural().get(type);
            }
            else if (!tile.isHighlighted() && side == 0){
                image = gp.imagS.getStructureGalleryTeam1Shaded().get(type);
            }
            else if (!tile.isHighlighted() && side == 1){
                image = gp.imagS.getStructureGalleryTeam2Shaded().get(type);
            }
            else {
                image = gp.imagS.getStructureGalleryNaturalShaded().get(type);
            }
            g2.drawImage(image, screenX, screenY,null);
        }
    }

    /***
     * this puts a unit into the inventory of the structure
     * @param unitToStore this unit gets stored
     */
    public void storeUnit(SuperUnit unitToStore){
        for (SuperUnit sunit : inventory){
            sunit.setTeamNum(unitToStore.getTeamNum());
        }
        inventory.add(unitToStore);
    }

    /***
     * this visually makes the arrived unit stored and also removes it from its team list
     * @param arrivedUnit the unit that gets removed from a team list
     */
    public void unitArrived(SuperUnit arrivedUnit){
        ArrayList<SuperUnit> unitSide;
        arrivedUnit.setVisible(false);
        if (arrivedUnit.getTeamNum() == 0){
            unitSide = gp.ally;
        }
        else {
            unitSide = gp.enemy;
        }
        arrivedUnit.teamNum = 2;
        unitSide.remove(arrivedUnit);

    }

    /***
     * this makes the selected unit visible and also puts it on the team's list
     * @param unitToDeploy unit that is put back on a team list
     * @param sideNum the number of the side of which this unit is being deployed
     */
    public void deployUnit(SuperUnit unitToDeploy, int sideNum){
        unitToDeploy.setVisible(true);
        if (sideNum == 0){
            for (int i = 0; i <inventory.size(); ++i){
                if (inventory.get(i) == unitToDeploy){
                    gp.ally.add(unitToDeploy);
                    inventory.remove(unitToDeploy);
                    unitToDeploy.teamNum = sideNum;
                    break;
                }
            }
        }
        else {
            for (int i = 0; i <inventory.size(); ++i){
                if (inventory.get(i) == unitToDeploy){
                    gp.enemy.add(unitToDeploy);
                    inventory.remove(unitToDeploy);
                    unitToDeploy.teamNum = sideNum;
                    break;
                }
            }
        }
    }

    /***
     * this heals all the units in the inventory by 2 until that unit doesn't have 6 hp
     */
    public void repairInventory(){
        for (SuperUnit supu : inventory){
            //heal 2 until hp = 6
            if (supu.getHp() < 5){
                supu.setHp(supu.getHp() + 2);
            } else if (supu.getHp() < 6) {
                supu.setHp(supu.getHp() + 1);
            }
        }
    }

    /***
     * checks if there is still place for one more unit in the inventory
     * @return the answer
     */
    public boolean thereIsStillSpace(){
        return  inventory.size()<6;
    }
    public ArrayList<SuperUnit> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<SuperUnit> inventory) {
        this.inventory = inventory;
    }

    public Tile getTile() {
        return tile;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }
}
