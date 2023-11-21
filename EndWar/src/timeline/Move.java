package timeline;

import entity.SuperUnit;
import main.GamePanel;
import tile.Tile;

public class Move extends Order{
    Tile destinationTile;

    Move(String side, int index, Tile destinationTile){
        this.side = side;
        this.index = index;
        this.destinationTile = destinationTile;
    }

    @Override
    public void complete(GamePanel gp) {
        if(side.equals("ally")){
            gp.ally.get(index).moveUnit(gp, destinationTile);
        }
    }
}
