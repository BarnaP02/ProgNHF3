package timeline;

import entity.*;
import main.GamePanel;
import tile.Tile;

import java.util.List;

public class Move extends Order{
    private Tile destinationTile;
    private Tile startingTile;
    private List<Tile> path;
    private int beginX = 0;
    private int beginY = 0;
    private int endX = 0;
    private int endY = 0;
    private int incrementX = 0;
    private int incrementY = 0;
    private int counter = 0;
    private Tile current = null;
    protected Tile origin;
    protected int originalTeamNum = 0;

    /***
     * the constructor for move orders, this stores that a unit moves from tile a to tile b
     * @param gp for context
     * @param dancer the unit that is moving
     * @param destinationTile the tile that dancer is moving to
     * @param path the path on which dancer travels
     */

    Move(GamePanel gp, SuperUnit dancer, Tile destinationTile, List<Tile> path){
        if (dancer.getTeamNum()==0){
            side = gp.ally;
            originalTeamNum = 0;
        }
        else if (dancer.getTeamNum()==1){
            side = gp.enemy;
            originalTeamNum = 1;
        }
        else {
            for (SuperStructure ss : gp.structures){
                if (ss.getInventory().contains(dancer)) {
                    side = ss.getInventory();
                    originalTeamNum = 2;
                }
            }
        }
        for (int i = 0; i < side.size(); ++i){
            if (side.get(i) == dancer){
                index = i;
            }
        }
        this.destinationTile = destinationTile;
        this.path = path;
        this.startingTile = dancer.getCurrentTile();
        origin = side.get(index).getCurrentTile();
        current = side.get(index).getCurrentTile();
        actor = dancer;
        side.get(index).setCurrentTile(destinationTile);
        if (side.get(index).is2tile()){
            side.get(index).setOtherCurrentTile(path.get(path.size()-2));
        }
        else {
            side.get(index).setOtherCurrentTile(side.get(index).getCurrentTile());
        }
    }

    /***
     * this is what completes a move
     * @param gp for context
     */
    @Override
    public void complete(GamePanel gp) {
        if(actor.getClass() == U_arty_H.class){
            actor.setArtyAbleToFire(false);
        }
        int next = 0;
        for (int i = 0; i < path.size(); ++i){
            if (path.get(i) == current){
                next = i + 1;
            }
        }
        if (next == path.size()){
            setCompleted(true);
            actor.setActed(true);
            actor.setMoving(false);
            actor.setLastImgIndex(-1);
            SuperStructure sust = gp.getStructureFromTile(destinationTile);
            if (sust != null){
                sust.unitArrived(actor);
            }
            return;
        }
        for (int i = 0; i < current.borders().length; ++i){
            if (current.getBorder(i) == path.get(next)){
                actor.setDirection(i);
            }
        }
        if (current == null){
            System.out.println("ERROR: failed to complete Order");
            return;
        }
        beginX = current.worldX;
        beginY = current.worldY;
        endX = path.get(next).worldX;
        endY = path.get(next).worldY;
        incrementX = (endX - beginX)/10;
        incrementY = (endY - beginY)/10;
        if (counter == 10){
            counter = 0;
            actor.moveUnit(gp, path.get(next));
            current = path.get(next);
        }
        else {
            counter++;
            actor.setWorldX(actor.getWorldX() + incrementX);
            actor.setWorldY(actor.getWorldY() + incrementY);
        }
    }

    /***
     * this is for after a side moved, to reset the units to their original positions because the enemy gives the attack orders before the move orders are given
     * @param gp for context
     */
    @Override
    public void reverse(GamePanel gp){
        int newDirection = 0;
        for (int i = 0; i < 6; ++i){
            if (path.get(0).getBorder(i) == path.get(1)){
                newDirection = i;
            }
        }
        actor.setDirection(newDirection);
        actor.setCurrentTile(origin);
        actor.setOtherCurrentTile(path.get(0));
        actor.setWorldX(origin.worldX);
        actor.setWorldY(origin.worldY);
        actor.setActed(false);
        actor.setMoving(false);
        SuperStructure sust = gp.getStructureFromTile(destinationTile);
        if (sust != null){
            sust.deployUnit(actor, originalTeamNum);
        }
        SuperStructure sustru = gp.getStructureFromTile(startingTile);
        actor.setTeamNum(originalTeamNum);
        actor.setVisible(originalTeamNum != 2);

        actor.setLastImgIndex(-1);
    }

    /***
     * after the enemy attack phase ends or begins and units are still on the move, this forces the move orders to finish immediately
     * @param gp for context
     */
    @Override
    public void forceFinish(GamePanel gp){
        if (current == null){
            System.out.println("ERROR: Complete wasn't called yet.");
            return;
        }
        setCompleted(true);
        int newDirection = 0;
        for (int i = 0; i < 6; ++i){
            if (path.get(path.size()-1).getBorder(i) == path.get(path.size()-2)){
                newDirection = (i+3)%6;
            }
        }
        actor.setDirection(newDirection);
        actor.setWorldX(destinationTile.worldX);
        actor.setWorldY(destinationTile.worldY);
        actor.setCurrentTile(destinationTile);
        if (actor.is2tile()){

            actor.setOtherCurrentTile(path.get(path.size()-2));
        }
        else {
            actor.setOtherCurrentTile(destinationTile);
        }
        actor.setActed(false);
        actor.setMoving(false);
        SuperStructure sust = gp.getStructureFromTile(destinationTile);
        if (sust != null){
            sust.unitArrived(actor);
        }
        if (gp.getStructureFromTile(destinationTile) != null){
            actor.setVisible(false);
            actor.setTeamNum(2);

            //for when reverse deploys actor
            if (!gp.getStructureFromTile(destinationTile).getInventory().contains(actor)){
                gp.getStructureFromTile(destinationTile).storeUnit(actor);
            }
        }
        if (originalTeamNum == 2) {
            actor.setVisible(true);
        }
    }
}
