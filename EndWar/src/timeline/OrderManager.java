package timeline;

import main.GamePanel;
import main.KeyHandler;
import tile.RangeFinder;

import java.util.ArrayList;

public class OrderManager {
    GamePanel gp;
    ArrayList<Order>timeline;
    String newOrderUnitSide;
    int newOrderUnitIndex;
    int slowCounter = 0;
    int slowNum = 6;

    public OrderManager(GamePanel gp){
        newOrderUnitSide = "";
        this.gp = gp;
        timeline = new ArrayList<>();
    }
    public void giveOrder(Order o){
        o.complete(gp);
        timeline.add(o);
    }

    public void update(GamePanel gp, KeyHandler keyH){
        slowCounter++;
        if (slowCounter!=slowNum) { return; }
        slowCounter = 0;
        if (keyH.spacePressed) {
            if (newOrderUnitSide.isEmpty()) {
                for (int i = 0; i < gp.ally.size(); ++i) {
                    if (gp.getTileFromWorldCoords(gp.ally.get(i).worldX, gp.ally.get(i).worldY) == gp.cruser.getHover()){
                        newOrderUnitSide = "ally";
                        newOrderUnitIndex = i;
                        gp.rFinder.findMovementRange(gp, gp.ally.get(i));
                        slowCounter = -20;
                    }
                }
                //ez később nem kell
                for (int i = 0; i < gp.enemy.size(); ++i) {
                    if (gp.getTileFromWorldCoords(gp.enemy.get(i).worldX, gp.enemy.get(i).worldY) == gp.cruser.getHover()){
                        newOrderUnitSide = "enemy";
                        newOrderUnitIndex = i;
                        slowCounter = -20;
                    }
                }
                //slowCounter = 0;
            }
            else {
                if (newOrderUnitSide.equals("ally")) {
                    //if (gp.cruser.getHover() != gp.ally.get(newOrderUnitIndex).getCurrentTile()){
                        //gp.ally.get(newOrderUnitIndex).currentTile = gp.cruser.getHover();
                        //gp.ally.get(newOrderUnitIndex).worldX = gp.ally.get(newOrderUnitIndex).currentTile.worldX;
                        //gp.ally.get(newOrderUnitIndex).worldY = gp.ally.get(newOrderUnitIndex).currentTile.worldY;
                        timeline.add(new Move("ally", newOrderUnitIndex, gp.cruser.getHover()));
                        timeline.get(timeline.size()-1).complete(gp);
                        //end of selection
                        for (int i = 0; i < gp.maxWorldCol; ++i){
                            for (int j = 0; j < gp.maxWorldRow; j++) {
                                gp.Grid[i][j].setIsHighlighted(true);
                            }
                        }
                        slowCounter = -40;
                    //}
                    newOrderUnitSide = "";
                }
                /*
                for (int i = 0; i < gp.enemy.size(); ++i) {
                    if (gp.getTileFromWorldCoords(gp.enemy.get(i).worldX, gp.enemy.get(i).worldY) == gp.cruser.getHover()){
                        newOrderUnitSide = "enemy";
                        newOrderUnitIndex = i;
                    }
                }*/
            }
        }
    }

    public ArrayList<Order> getTimeline() {
        return timeline;
    }
}
