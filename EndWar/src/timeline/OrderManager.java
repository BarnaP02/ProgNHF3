package timeline;

import entity.SuperStructure;
import entity.SuperUnit;
import entity.U_arty_H;
import main.GamePanel;
import main.KeyHandler;
import main.Sound;
import tile.RangeFinder;
import tile.Tile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderManager implements Serializable {
    GamePanel gp;
    private ArrayList<Order>timeline;
    private ArrayList<Order>processing;
    private ArrayList<Order>tempMove;
    private ArrayList<Order>tempAttack;
    private List<SuperUnit> newOrderUnitSide;
    private int newOrderUnitIndex;
    private SuperUnit ordered;
    private int slowCounter = 0;
    private int slowNum = 6;
    private boolean gotNewIndex = false;
    private int newIndex = 0;
    public boolean unitFromStructure = false;

    /***
     * the purpose of this class is to create and manage all the orders
     * @param gp for context
     */
    public OrderManager(GamePanel gp){
        newOrderUnitSide = null;
        this.gp = gp;
        timeline = new ArrayList<>();
        processing = new ArrayList<>();
        tempMove = new ArrayList<>();
        tempAttack = new ArrayList<>();
    }

    /***
     * this gets called every time update() is called in gp, this is what creates the orders that move or destroy units
     * @param gp for context
     * @param keyH the KeyHandler that is used to create the orders
     */
    public void update(GamePanel gp, KeyHandler keyH){
        slowCounter++;
        if (slowCounter!=slowNum) { return; }
        slowCounter = 0;
        if (keyH.spacePressed || gotNewIndex) {
            if (newOrderUnitSide == null) {
                if (gp.getCycleState() == GamePanel.Cycle.T1MOVE){
                    for (int i = 0; i < gp.structures.size();++i){
                        if (gp.structures.get(i).getTile() == gp.cruser.getHover() && gp.structures.get(i).getSide() == 0 &&
                                !gp.structures.get(i).getInventory().isEmpty() && gotNewIndex && !gp.structures.get(i).getInventory().get(newIndex).isActed() &&
                                !gp.structures.get(i).getInventory().get(newIndex).isMoving()){
                            unitFromStructure = true;
                            gp.structures.get(i).deployUnit(gp.structures.get(i).getInventory().get(newIndex), 0);
                        }
                    }
                    gotNewIndex = false;
                    for (int i = 0; i < gp.ally.size(); ++i) {
                        if ((gp.ally.get(i).getCurrentTile() == gp.cruser.getHover() || gp.ally.get(i).getOtherCurrentTile() == gp.cruser.getHover()) &&
                                (!gp.ally.get(i).isActed() && !gp.ally.get(i).isDestroyed() && !gp.ally.get(i).isMoving() && gp.ally.get(i).isVisible())){
                            newOrderUnitSide = gp.ally;
                            newOrderUnitIndex = i;
                            ordered = newOrderUnitSide.get(newOrderUnitIndex);
                        }
                    }
                    if (newOrderUnitSide != null){
                        gp.rFinder.findMovementRange(gp, newOrderUnitSide.get(newOrderUnitIndex), false);
                        slowCounter = -20;
                        newOrderUnitSide.get(newOrderUnitIndex).getSelectedSound().play();
                    }
                }
                if (gp.getCycleState() == GamePanel.Cycle.T2MOVE && !gp.isPvE){
                    for (int i = 0; i < gp.structures.size();++i){
                        if (gp.structures.get(i).getTile() == gp.cruser.getHover() && gp.structures.get(i).getSide() == 1 &&
                                !gp.structures.get(i).getInventory().isEmpty() && gotNewIndex && !gp.structures.get(i).getInventory().get(newIndex).isActed() &&
                                !gp.structures.get(i).getInventory().get(newIndex).isMoving()){
                            unitFromStructure = true;
                            gp.structures.get(i).deployUnit(gp.structures.get(i).getInventory().get(newIndex), 1);
                        }
                    }
                    gotNewIndex = false;
                    for (int i = 0; i < gp.enemy.size(); ++i) {
                        if ((gp.enemy.get(i).getCurrentTile() == gp.cruser.getHover() || gp.enemy.get(i).getOtherCurrentTile() == gp.cruser.getHover()) &&
                                (!gp.enemy.get(i).isActed() && !gp.enemy.get(i).isDestroyed() && !gp.enemy.get(i).isMoving() && gp.enemy.get(i).isVisible())){
                            newOrderUnitSide = gp.enemy;
                            newOrderUnitIndex = i;
                            ordered = newOrderUnitSide.get(newOrderUnitIndex);
                        }
                    }
                    if (newOrderUnitSide != null){
                        gp.rFinder.findMovementRange(gp, newOrderUnitSide.get(newOrderUnitIndex), false);
                        slowCounter = -20;
                        newOrderUnitSide.get(newOrderUnitIndex).getSelectedSound().play();
                    }
                }
                if (gp.getCycleState() == GamePanel.Cycle.T1ATTACK){
                    for (int i = 0; i < gp.ally.size(); ++i) {
                        boolean artyCantFire = gp.ally.get(i).getClass() == U_arty_H.class && !gp.ally.get(i).isArtyAbleToFire();
                        if ((gp.ally.get(i).getCurrentTile() == gp.cruser.getHover() || gp.ally.get(i).getOtherCurrentTile() == gp.cruser.getHover()) &&
                                (!gp.ally.get(i).isActed() && !gp.ally.get(i).isDestroyed() && !gp.ally.get(i).isMoving() && !artyCantFire && gp.ally.get(i).isVisible())){
                            newOrderUnitSide = gp.ally;
                            newOrderUnitIndex = i;
                            ordered = newOrderUnitSide.get(newOrderUnitIndex);
                        }
                    }
                    if (newOrderUnitSide != null){
                        gp.rFinder.findAttackRange(gp, newOrderUnitSide.get(newOrderUnitIndex));
                        slowCounter = -20;
                        newOrderUnitSide.get(newOrderUnitIndex).getSelectedSound().play();
                    }
                }
                if ((gp.getCycleState() == GamePanel.Cycle.T2ATTACK && !gp.isPvE)){
                    for (int i = 0; i < gp.enemy.size(); ++i) {
                        boolean artyCantFire = gp.enemy.get(i).getClass() == U_arty_H.class && !gp.enemy.get(i).isArtyAbleToFire();
                        if ((gp.enemy.get(i).getCurrentTile() == gp.cruser.getHover() || gp.enemy.get(i).getOtherCurrentTile() == gp.cruser.getHover()) &&
                                (!gp.enemy.get(i).isActed() && !gp.enemy.get(i).isDestroyed() && !gp.enemy.get(i).isMoving()) && !artyCantFire && gp.enemy.get(i).isVisible()){
                            newOrderUnitSide = gp.enemy;
                            newOrderUnitIndex = i;
                            ordered = newOrderUnitSide.get(newOrderUnitIndex);
                        }
                    }
                    if (newOrderUnitSide != null){
                        gp.rFinder.findAttackRange(gp, newOrderUnitSide.get(newOrderUnitIndex));
                        slowCounter = -20;
                        newOrderUnitSide.get(newOrderUnitIndex).getSelectedSound().play();
                    }
                }
            }
            else {
                if (gp.getCycleState() == GamePanel.Cycle.T1MOVE || (gp.getCycleState() == GamePanel.Cycle.T2MOVE && !gp.isPvE)){
                    if (gp.cruser.getHover() != ordered.getCurrentTile() &&
                            gp.cruser.getHover() != ordered.getOtherCurrentTile() &&
                            gp.cruser.getHover().isHighlighted() && ordered.isVisible()){
                        if (!unitFromStructure){
                            //capture or store in structure
                            for (int i = 0; i < gp.structures.size();++i){
                                if (gp.structures.get(i).getTile() == gp.cruser.getHover()){
                                    //capture structure
                                    if (newOrderUnitSide == gp.ally) gp.structures.get(i).setSide(0);
                                    if (newOrderUnitSide == gp.enemy) gp.structures.get(i).setSide(1);
                                    gp.structures.get(i).storeUnit(ordered);
                                }
                            }

                        }
                        unitFromStructure = false;
                        List<Tile> path = gp.rFinder.findMovementPath(gp,ordered, gp.cruser.getHover());
                        for (Tile t : path){
                            System.out.println(t.getCoords()[0]+","+t.getCoords()[1]);
                        }
                        ordered.getMoveSound().play();
                        ordered.setMoving(true);
                        timeline.add(new Move(gp, ordered, gp.cruser.getHover(), path));
                        tempMove.add(timeline.get(timeline.size()-1));
                        processing.add(timeline.get(timeline.size()-1));
                    }
                    //end of selection
                    if (unitFromStructure){
                        gp.getStructureFromTile(gp.cruser.getHover()).storeUnit(ordered);
                        gp.getStructureFromTile(gp.cruser.getHover()).unitArrived(ordered);
                        unitFromStructure = false;
                    }
                    for (int i = 0; i < gp.maxWorldCol; ++i){
                        for (int j = 0; j < gp.maxWorldRow; j++) {
                            gp.Grid[i][j].setIsHighlighted(true);
                        }
                    }
                    slowCounter = -40;
                    newOrderUnitSide = null;
                }
                else {
                    if (gp.cruser.getHover() != ordered.getCurrentTile() &&
                            gp.cruser.getHover() != ordered.getOtherCurrentTile() &&
                            ((gp.getCycleState() == GamePanel.Cycle.T2ATTACK && !gp.isPvE) || gp.getCycleState() == GamePanel.Cycle.T1ATTACK) &&
                            ordered.isVisible()){
                        ArrayList<SuperUnit> otherSide;
                        if (ordered.getTeamNum()==0){
                            otherSide = gp.enemy;
                        }
                        else {
                            otherSide = gp.ally;
                        }
                        for (SuperUnit target : otherSide){
                            if(target.getCurrentTile() == gp.cruser.getHover() && target.isInRange() || target.getOtherCurrentTile() == gp.cruser.getHover() &&
                                    target.isInRange() && target.isVisible()){
                                ordered.getAttackSound().play();
                                timeline.add(new Attack(gp, ordered, target));
                                tempAttack.add(timeline.get(timeline.size()-1));
                            }
                        }
                    }
                    //end of selection
                    for (int i = 0; i < gp.maxWorldCol; ++i){
                        for (int j = 0; j < gp.maxWorldRow; j++) {
                            gp.Grid[i][j].setIsHighlighted(true);
                            gp.Grid[i][j].setInRange(false);
                        }
                    }
                    slowCounter = -40;
                    newOrderUnitSide = null;
                }
            }
        }
        else if (gp.getCycleState() == GamePanel.Cycle.T2MOVE && gp.isPvE){
            for (int i = 0; i < gp.enemy.size(); ++i){
                gp.rFinder.findTargetUnit(gp, gp.enemy, gp.ally, i);
                gp.rFinder.findAttackRange(gp, gp.enemy.get(i));
                List<Tile> aiPath = gp.rFinder.findMovementPathToUnit(gp, gp.enemy.get(i), gp.enemy.get(i).getTargetUnit());
                if (!aiPath.isEmpty()){
                    timeline.add(new Move(gp, gp.enemy.get(i), aiPath.get(0), aiPath));
                    tempMove.add(timeline.get(timeline.size()-1));
                    processing.add(timeline.get(timeline.size()-1));
                }
            }
        }
        else if (gp.getCycleState() == GamePanel.Cycle.T2ATTACK && gp.isPvE){
            for (int i = 0; i < gp.enemy.size(); ++i){
                //first round
                if (gp.enemy.get(i).getTargetUnit() == null){
                    gp.rFinder.findTargetUnit(gp, gp.enemy, gp.ally, i);
                }
                if(gp.enemy.get(i).getTargetUnit().isInRange()){
                    timeline.add(new Attack(gp, gp.enemy.get(i), gp.enemy.get(i).getTargetUnit()));
                    tempAttack.add(timeline.get(timeline.size()-1));
                }
            }

        }
        gotNewIndex = false;
        if (keyH.enterPressed || (gp.isPvE && (gp.getCycleState() == GamePanel.Cycle.T2MOVE || gp.getCycleState() == GamePanel.Cycle.T2ATTACK))){// && keyH.ctrlPressed){
            //end of selection
            for (int i = 0; i < gp.maxWorldCol; ++i){
                for (int j = 0; j < gp.maxWorldRow; j++) {
                    gp.Grid[i][j].setIsHighlighted(true);
                    gp.Grid[i][j].setInRange(false);
                }
            }
            slowCounter = -40;
            newOrderUnitSide = null;
            if (gp.getCycleState() == GamePanel.Cycle.T1MOVE || (gp.getCycleState() == GamePanel.Cycle.T2MOVE && !gp.isPvE) ||
                    (gp.getCycleState() == GamePanel.Cycle.T2ATTACK && gp.isPvE)){
                for (SuperStructure supture : gp.structures){
                    //repair stored units at the end of their movement round
                    if (supture.getSide() == 0 && gp.getCycleState() == GamePanel.Cycle.T1MOVE){
                        supture.repairInventory();
                    }
                    if (supture.getSide() == 1 && gp.getCycleState() == GamePanel.Cycle.T2MOVE){
                        supture.repairInventory();
                    }
                }
                for (Order o : processing){
                    if (!o.isCompleted()){
                        o.forceFinish(gp);
                    }
                }
                for (Order o : tempMove){
                    o.reverse(gp);
                }
                tempAttack = new ArrayList<>();
                gp.nextCycleState();
            }
            else {
                for (SuperUnit un : gp.ally){
                    if (!un.isArtyAbleToFire() && un.getClass() == U_arty_H.class){
                        un.setArtyCounter(un.getArtyCounter()+1);
                        if (un.getArtyCounter()==2){
                            un.setArtyAbleToFire(true);
                            un.setArtyCounter(0);
                        }
                    }
                }
                for (SuperUnit un : gp.enemy){
                    if (!un.isArtyAbleToFire() && un.getClass() == U_arty_H.class){
                        un.setArtyCounter(un.getArtyCounter()+1);
                        if (un.getArtyCounter()==2){
                            un.setArtyAbleToFire(true);
                            un.setArtyCounter(0);
                        }
                    }
                }
                gp.attacksNeedResolving = true;
                gp.nextCycleState();
            }
            slowCounter = -30;
            return;
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

    public ArrayList<Order> getTempMove() {
        return tempMove;
    }

    public void setTempMove(ArrayList<Order> tempMove) {
        this.tempMove = tempMove;
    }

    public ArrayList<Order> getTempAttack() {
        return tempAttack;
    }

    public boolean isGotNewIndex() {
        return gotNewIndex;
    }

    public void setGotNewIndex(boolean gotNewIndex) {
        this.gotNewIndex = gotNewIndex;
    }

    public int getNewIndex() {
        return newIndex;
    }

    public void setNewIndex(int newIndex) {
        this.newIndex = newIndex;
    }
}
