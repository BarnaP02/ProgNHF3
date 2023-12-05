package entity;

import main.GamePanel;
import tile.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SuperStructure {
    GamePanel gp;
    protected BufferedImage image;
    protected String type;
    protected Tile tile;
    protected int worldX,worldY,trueWorldX,trueWorldY;
    int side;
    private ArrayList<SuperUnit> inventory = new ArrayList<>();
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
    public void storeUnit(SuperUnit unitToStore){
        for (SuperUnit sunit : inventory){
            sunit.setTeamNum(unitToStore.getTeamNum());
        }
        inventory.add(unitToStore);
        System.out.println("STORED: "+unitToStore);
    }
    public void unitArrived(SuperUnit arrivedUnit){
        ArrayList<SuperUnit> unitSide;
        arrivedUnit.setVisible(false);
        if (arrivedUnit.getTeamNum() == 0){
            unitSide = gp.ally;
            System.out.println("ARRIVED: "+arrivedUnit +" from: ally");
        }
        else {
            unitSide = gp.enemy;
            System.out.println("ARRIVED: "+arrivedUnit +" from: enemy");
        }
        arrivedUnit.teamNum = 2;
        unitSide.remove(arrivedUnit);
        //System.out.println("REMOVED: "+arrivedUnit +" from: "+unitSide);

    }
    public void deployUnit(SuperUnit unitToDeploy, int sideNum){
        unitToDeploy.setVisible(true);
        //ArrayList<SuperUnit> unitSide;
        if (sideNum == 0){
            //unitSide = gp.ally;
            for (int i = 0; i <inventory.size(); ++i){
                if (inventory.get(i) == unitToDeploy){
                    gp.ally.add(unitToDeploy);
                    System.out.println("DEPLOYED: "+unitToDeploy +" to: ally");
                    inventory.remove(unitToDeploy);
                    System.out.println("REMOVED: "+unitToDeploy +" from: "+type);
                    unitToDeploy.teamNum = sideNum;
                    break;
                }
            }
        }
        else {
            //unitSide = gp.enemy;
            for (int i = 0; i <inventory.size(); ++i){
                if (inventory.get(i) == unitToDeploy){
                    gp.enemy.add(unitToDeploy);
                    System.out.println("DEPLOYED: "+unitToDeploy +" to: enemy");
                    inventory.remove(unitToDeploy);
                    System.out.println("REMOVED: "+unitToDeploy +" from: "+type);
                    unitToDeploy.teamNum = sideNum;
                    break;
                }
            }
        }
    }
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
