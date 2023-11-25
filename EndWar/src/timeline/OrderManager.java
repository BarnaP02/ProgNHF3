package timeline;

import main.GamePanel;
import main.KeyHandler;
import main.Sound;
import tile.RangeFinder;
import tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    GamePanel gp;
    ArrayList<Order>timeline;
    ArrayList<Order>processing;
    String newOrderUnitSide;
    int newOrderUnitIndex;
    int slowCounter = 0;
    int slowNum = 6;

    public OrderManager(GamePanel gp){
        newOrderUnitSide = "";
        this.gp = gp;
        timeline = new ArrayList<>();
        processing = new ArrayList<>();
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
                        gp.ally.get(newOrderUnitIndex).getSelectedSound().play();
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
                    if (gp.cruser.getHover() != gp.ally.get(newOrderUnitIndex).getCurrentTile()){
                        //gp.ally.get(newOrderUnitIndex).currentTile = gp.cruser.getHover();
                        //gp.ally.get(newOrderUnitIndex).worldX = gp.ally.get(newOrderUnitIndex).currentTile.worldX;
                        //gp.ally.get(newOrderUnitIndex).worldY = gp.ally.get(newOrderUnitIndex).currentTile.worldY;
                        List<Tile> path = gp.rFinder.findMovementPath(gp,gp.ally.get(newOrderUnitIndex), gp.cruser.getHover());
                        for (Tile t : path){
                            System.out.println(t.getCoords()[0]+","+t.getCoords()[1]);
                        }
                        gp.ally.get(newOrderUnitIndex).getMoveSound().play();
                        timeline.add(new Move("ally", newOrderUnitIndex, gp.cruser.getHover(), path));
                        processing.add(new Move("ally", newOrderUnitIndex, gp.cruser.getHover(), path));
                        //timeline.get(timeline.size()-1).complete(gp);
                    }
                    //end of selection
                    for (int i = 0; i < gp.maxWorldCol; ++i){
                        for (int j = 0; j < gp.maxWorldRow; j++) {
                            gp.Grid[i][j].setIsHighlighted(true);
                        }
                    }
                    slowCounter = -40;
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
        for (Order o : processing){
            if (!o.isCompleted()){
                o.complete(gp);
            }
        }
    }

    public ArrayList<Order> getTimeline() {
        return timeline;
    }
}
