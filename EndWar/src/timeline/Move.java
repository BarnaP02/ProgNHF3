package timeline;

import entity.SuperUnit;
import main.GamePanel;
import tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class Move extends Order{
    Tile destinationTile;
    List<Tile> path;
    int beginX = 0;
    int beginY = 0;
    int endX = 0;
    int endY = 0;
    int incrementX = 0;
    int incrementY = 0;
    int counter = 0;
    Tile current = null;

    Move(String side, int index, Tile destinationTile, List<Tile> path){
        this.side = side;
        this.index = index;
        this.destinationTile = destinationTile;
        this.path = path;
    }

    @Override
    public void complete(GamePanel gp) {
        /*
        if(side.equals("ally")){
            gp.ally.get(index).moveUnit(gp, destinationTile);
        }*/
        int next = 0;
        if (current == null){
            current = gp.ally.get(index).getCurrentTile();
        }
        for (int i = 0; i < path.size(); ++i){
            if (path.get(i) == current){
                //current = path.get(i);
                next = i + 1;
            }
        }
        if (next == path.size()){
            setCompleted(true);
            return;
        }
        for (int i = 0; i < current.borders().length; ++i){
            if (current.getBorder(i) == path.get(next)){
                //current = path.get(i);
                gp.ally.get(index).setDirection(i);
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
            gp.ally.get(index).moveUnit(gp, path.get(next));
            current = path.get(next);
        }
        else {
            counter++;
            gp.ally.get(index).worldX += incrementX;
            gp.ally.get(index).worldY += incrementY;
        }
    }

}
