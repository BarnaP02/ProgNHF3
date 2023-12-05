package timeline;

import entity.*;
import main.GamePanel;
import tile.Tile;

import java.util.List;

public class Move extends Order{
    Tile destinationTile;
    Tile startingTile;
    //Tile next;
    List<Tile> path;
    int beginX = 0;
    int beginY = 0;
    int endX = 0;
    int endY = 0;
    int incrementX = 0;
    int incrementY = 0;
    int counter = 0;
    Tile current = null;
    protected Tile origin;
    protected int originalTeamNum = 0;

    Move(GamePanel gp, SuperUnit dancer, Tile destinationTile, List<Tile> path){
        if (dancer.getTeamNum()==0){
            //otherSide = gp.enemy;
            side = gp.ally;
            originalTeamNum = 0;
        }
        else if (dancer.getTeamNum()==1){
            //otherSide = gp.ally;
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
        //this.side = side;
        //this.index = index;
        this.destinationTile = destinationTile;
        this.path = path;
        this.startingTile = dancer.getCurrentTile();
        origin = side.get(index).getCurrentTile();
        //previous = origin;
        current = side.get(index).getCurrentTile();
        actor = dancer;
        side.get(index).setCurrentTile(destinationTile);
        if (side.get(index).is2tile()){
            //side.get(index).setOtherCurrentTile(side.get(index).direction);
            side.get(index).setOtherCurrentTile(path.get(path.size()-2));
        }
        else {
            side.get(index).setOtherCurrentTile(side.get(index).getCurrentTile());
        }
        //side.get(index).setOtherCurrentTile(path.get(path.size()-1));
    }

    @Override
    public void complete(GamePanel gp) {
        /*
        if(side.equals("ally")){
            gp.ally.get(index).moveUnit(gp, destinationTile);
        }*/
        if(actor.getClass() == U_arty_H.class){
            actor.setArtyAbleToFire(false);
        }
        int next = 0;
        for (int i = 0; i < path.size(); ++i){
            if (path.get(i) == current){
                //current = path.get(i);
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
            //gp.paintComponent(gp.getGraphics());

            //gp.easyAccessUnitLocation.put(side.get(index),side.get(index).getCurrentTile());
            //gp.easyAccessUnitLocationOther.put(side.get(index),side.get(index).getOtherCurrentTile());
            return;
        }
        for (int i = 0; i < current.borders().length; ++i){
            if (current.getBorder(i) == path.get(next)){
                //current = path.get(i);
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
        /*if (side.get(index).is2tile()){

            side.get(index).setOtherCurrentTile(path.get(0));
        }
        else {
            side.get(index).setOtherCurrentTile(path.get(1));
        }*/
        actor.setWorldX(origin.worldX);
        actor.setWorldY(origin.worldY);
        actor.setActed(false);
        actor.setMoving(false);
        SuperStructure sust = gp.getStructureFromTile(destinationTile);
        if (sust != null){
            sust.deployUnit(actor, originalTeamNum);
        }
        SuperStructure sustru = gp.getStructureFromTile(startingTile);
        //if (sustru != null){
        //    sustru.storeUnit(actor);
        //}
        actor.setTeamNum(originalTeamNum);
        actor.setVisible(originalTeamNum != 2);

        actor.setLastImgIndex(-1);
    }
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
        /*if (gp.getStructureFromTile(startingTile) != null){
            actor.setVisible(false);
            actor.setTeamNum(2);

            //for when reverse stores actor
            //if (!gp.getStructureFromTile(startingTile).getInventory().contains(actor)){
            //    gp.getStructureFromTile(startingTile).deployUnit(actor, originalTeamNum);
            //}
        }*/
        if (originalTeamNum == 2) {
            actor.setVisible(true);
        }
        //gp.easyAccessUnitLocation.put(side.get(index),side.get(index).getCurrentTile());
        //gp.easyAccessUnitLocationOther.put(side.get(index),side.get(index).getOtherCurrentTile());
    }
}
