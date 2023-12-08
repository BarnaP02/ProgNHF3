package tile;

import entity.SuperStructure;
import entity.SuperUnit;
import main.GamePanel;

import java.util.*;

/***
 * this beast handles everything that is path or target finding related
 */
public class RangeFinder {
    private Tile[][] Grid;
    private Tile[][] cameFromGrid;
    private double[][] movementSpeedCost;

    /***
     * the constructor for RangeFinder
     * @param gp for accessing info
     * @param grid the grid where the tiles are stored
     */
    public RangeFinder(GamePanel gp, Tile[][] grid){
        cameFromGrid = new Tile[gp.maxWorldCol][gp.maxWorldRow];
        movementSpeedCost = new double[gp.maxWorldCol][gp.maxWorldRow];
        this.Grid = grid;
    }

    /***
     * this basically runs a modified BFS that sets the tiles' highlighting according to their distance from u
     * @param gp the GamePanel for context
     * @param u the unit that we want to find the reached tiles for
     */
    public void findAttackRange(GamePanel gp, SuperUnit u){
        boolean[][] visitedGrid = new boolean[gp.maxWorldCol][gp.maxWorldRow];
        int[][] movementSpeedHere = new int[gp.maxWorldCol][gp.maxWorldRow];
        int[] currentIdx = u.getCurrentTile().getCoords();
        Queue<Tile> queue = new LinkedList<>();
        visitedGrid[currentIdx[0]][currentIdx[1]] = true;
        movementSpeedHere[currentIdx[0]][currentIdx[1]] = 0;
        queue.add(u.getCurrentTile());
        while (!queue.isEmpty()){
            Tile processedTile = queue.poll();
            for (int i = 0; i < 6; ++i){
                if (processedTile.borders()[i]!=0){
                    int[] neighborCoords = processedTile.getBorder(i).getCoords();
                    if (!visitedGrid[neighborCoords[0]][neighborCoords[1]]){
                        visitedGrid[neighborCoords[0]][neighborCoords[1]] = true;
                        movementSpeedHere[neighborCoords[0]][neighborCoords[1]] = movementSpeedHere[processedTile.getCoords()[0]][processedTile.getCoords()[1]] + 1;
                        movementSpeedCost[neighborCoords[0]][neighborCoords[1]] = movementSpeedHere[neighborCoords[0]][neighborCoords[1]];
                        queue.add(processedTile.getBorder(i));
                    }
                }
            }
        }
        ArrayList<SuperUnit> side;
        ArrayList<SuperUnit> otherSide;
        if (u.getTeamNum()==0){
            otherSide = gp.enemy;
            side = gp.ally;
        }
        else {
            otherSide = gp.ally;
            side = gp.enemy;
        }
        for (SuperUnit other : otherSide){
            int conflictType;
            other.setInRange(false);
            if (other.isAvian()){
                conflictType = 1;
            }
            else if (other.isNavy()){
                conflictType = 2;
            }
            else {
                conflictType = 0;
            }
            if (movementSpeedHere[other.getCurrentTile().getCoords()[0]][other.getCurrentTile().getCoords()[1]] >= u.getAttackRange()[conflictType * 2] &&
                    movementSpeedHere[other.getCurrentTile().getCoords()[0]][other.getCurrentTile().getCoords()[1]] <= u.getAttackRange()[conflictType * 2 + 1] &&
                    !other.isDestroyed()){
                other.setInRange(true);
                other.getCurrentTile().setIsHighlighted(true);
                other.getCurrentTile().setInRange(true);
            }
            if (movementSpeedHere[other.getOtherCurrentTile().getCoords()[0]][other.getOtherCurrentTile().getCoords()[1]] >= u.getAttackRange()[conflictType * 2] &&
                    movementSpeedHere[other.getOtherCurrentTile().getCoords()[0]][other.getOtherCurrentTile().getCoords()[1]] <= u.getAttackRange()[conflictType * 2 + 1] &&
                    !other.isDestroyed()) {
                other.setInRange(true);
                other.getOtherCurrentTile().setIsHighlighted(true);
                other.getOtherCurrentTile().setInRange(true);
            }
            if (!other.isInRange() && !other.isDestroyed()){
                other.setInRange(false);
                other.getCurrentTile().setIsHighlighted(false);
                other.getCurrentTile().setInRange(false);
                other.getOtherCurrentTile().setIsHighlighted(false);
                other.getOtherCurrentTile().setInRange(false);
            }
        }
        for (SuperUnit uni : side){
            if (!uni.isDestroyed()){
                uni.setInRange(false);
                uni.getCurrentTile().setIsHighlighted(false);
                uni.getCurrentTile().setInRange(false);
                uni.getOtherCurrentTile().setIsHighlighted(false);
                uni.getOtherCurrentTile().setInRange(false);
            }
        }
    }

    /***
     * this is a lot more complicated than findAttackRange as this is a modified dijkstra algorithm
     * sets the tiles their distance from u and set them visible if they are close enough
     * @param gp for context
     * @param u the unit that's stats we want to base this movement range calculation on
     * @param ai would have signaled if this was for a player or for the ai but
     *           that didn't happen
     */
    public void findMovementRange(GamePanel gp, SuperUnit u, boolean ai){

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < gp.maxWorldCol; ++i){
            for (int j = 0; j < gp.maxWorldRow; j++) {
                movementSpeedCost[i][j] = 100000.0;
            }
        }
        ArrayList<SuperUnit> side;
        ArrayList<SuperUnit> otherSide;
        if (u.getTeamNum()==0){
            otherSide = gp.enemy;
            side = gp.ally;
        }
        else {
            otherSide = gp.ally;
            side = gp.enemy;
        }
        for (SuperUnit enemi : otherSide){
            if (!enemi.isDestroyed()){
                movementSpeedCost[enemi.getCurrentTile().getCoords()[0]][enemi.getCurrentTile().getCoords()[1]] = 100010.0;
                movementSpeedCost[enemi.getOtherCurrentTile().getCoords()[0]][enemi.getOtherCurrentTile().getCoords()[1]] = 100010.0;
            }
        }
        movementSpeedCost[u.getCurrentTile().getCoords()[0]][u.getCurrentTile().getCoords()[1]] = 0;
        priorityQueue.add(new Node(u.getCurrentTile(), 0));

        while (!priorityQueue.isEmpty()) {
            Tile processedTile = priorityQueue.poll().vertex;
            for (int i = 0; i < 6; ++i) {
                if (processedTile.borders()[i] != 0) {
                    int[] neighborCoords = processedTile.getBorder(i).getCoords();
                    Tile neighbor = Grid[neighborCoords[0]][neighborCoords[1]];
                    int traverseIndex;
                    switch (neighbor.getType()){
                        case "woods":
                            traverseIndex = 2;
                            break;
                        case "concrete":
                            traverseIndex = 3;
                            break;
                        case "water":   //shallow
                            traverseIndex = 4;
                            break;
                        case "ocean":
                            traverseIndex = 5;
                            break;
                        case "depot", "factory":
                            if (u.isNavy() || u.isAvian()){
                                traverseIndex = 1;
                            }
                            else{
                                traverseIndex = 0;
                            }
                            break;
                        case "harbor":
                            if (!u.isNavy()){
                                traverseIndex = 1;
                            }
                            else{
                                traverseIndex = 4;
                            }
                            break;
                        default:
                            traverseIndex = 0;
                    }
                    if (neighbor.isObstacle()){
                        traverseIndex = 2;
                    }
                    if (neighbor.isStructure()){
                        traverseIndex = 1;
                    }
                    if (u.getCurrentTile() != u.getOtherCurrentTile() && neighbor.isRoad()) {
                        traverseIndex = 2;
                    }
                    double weight = u.getTraverseSpeed()[traverseIndex];
                    if (!u.isAvian() && !u.isNavy() && processedTile.isRoad() && neighbor.isRoad()){
                        weight = 0.5;
                    }

                    if (movementSpeedCost[processedTile.getCoords()[0]][processedTile.getCoords()[1]] != 100000.0 &&
                            movementSpeedCost[processedTile.getCoords()[0]][processedTile.getCoords()[1]] + weight <
                            movementSpeedCost[neighborCoords[0]][neighborCoords[1]] &&
                            (movementSpeedCost[neighborCoords[0]][neighborCoords[1]] != 100010.0 || ai)) {
                        cameFromGrid[neighborCoords[0]][neighborCoords[1]] = processedTile;
                        movementSpeedCost[neighborCoords[0]][neighborCoords[1]] = movementSpeedCost[processedTile.getCoords()[0]][processedTile.getCoords()[1]] + weight;
                        priorityQueue.add(new Node(neighbor, movementSpeedCost[neighborCoords[0]][neighborCoords[1]]));
                    }
                }
            }
        }

        for (int i = 0; i < gp.maxWorldCol; ++i){
            for (int j = 0; j < gp.maxWorldRow; j++) {
                //tile is in range or unit is navy but tile is not bridge
                Grid[i][j].setIsHighlighted(movementSpeedCost[i][j] <= u.getMovementRange() && (!u.isNavy() || (u.isNavy() && !Grid[i][j].isRoad())));
                SuperStructure supture = gp.getStructureFromTile(Grid[i][j]);
                if (supture != null){
                    if (!supture.thereIsStillSpace()){
                        Grid[i][j].setIsHighlighted(false);
                    }
                }
            }
        }

        for (int i = 0; i < gp.ally.size(); i++) {
            if (gp.ally.get(i) != u && !gp.ally.get(i).isDestroyed() && gp.ally.get(i).isVisible()) {
                gp.ally.get(i).getCurrentTile().setIsHighlighted(false);
                gp.ally.get(i).getOtherCurrentTile().setIsHighlighted(false);
            }
        }
        for (int i = 0; i < gp.enemy.size(); i++) {
            if (gp.enemy.get(i) != u && !gp.enemy.get(i).isDestroyed() && gp.enemy.get(i).isVisible()) {
                gp.enemy.get(i).getCurrentTile().setIsHighlighted(false);
                gp.enemy.get(i).getOtherCurrentTile().setIsHighlighted(false);
            }
        }
    }

    public List<Tile> findMovementPath(GamePanel gp, SuperUnit u, Tile dst){
        List<Tile> result = new ArrayList<>();
        int currentCol = dst.getCoords()[0];
        int currentRow = dst.getCoords()[1];
        while (movementSpeedCost[currentCol][currentRow] > 0.0){
            result.add(0,Grid[currentCol][currentRow]);
            int oldCol = Grid[currentCol][currentRow].getCoords()[0];
            int oldRow = Grid[currentCol][currentRow].getCoords()[1];
            currentCol = cameFromGrid[oldCol][oldRow].getCoords()[0];
            currentRow = cameFromGrid[oldCol][oldRow].getCoords()[1];
        }
        result.add(0,Grid[currentCol][currentRow]);
        if (u.is2tile()){
            result.add(0,u.getOtherCurrentTile());
        }
        return result;
    }

    /***
     * this would have been used for the ai to find the path to its targets, but it wouldn't have been able to access units in a structure, so I scrapped the concept
     * @param gp for context
     * @param u that unit that we want to find the path for
     * @param target the unit that this path goes to
     * @return that path that this unit can traverse this round
     */
    public List<Tile> findMovementPathToUnit(GamePanel gp, SuperUnit u, SuperUnit target){
        if (target.getCurrentTile().isInRange()) return new ArrayList<>();
        if (target.getOtherCurrentTile().isInRange()) return new ArrayList<>();
        findMovementRange(gp, u, true);
        List<Tile> result = new ArrayList<>();
        int currentCol = target.getCurrentTile().getCoords()[0];
        int currentRow = target.getCurrentTile().getCoords()[1];
        while (movementSpeedCost[currentCol][currentRow] > 0.0){
            if (gp.getUnitFromTile(Grid[currentCol][currentRow]) == null) {
                result.add(0,Grid[currentCol][currentRow]);

            }
            int oldCol = Grid[currentCol][currentRow].getCoords()[0];
            int oldRow = Grid[currentCol][currentRow].getCoords()[1];
            currentCol = cameFromGrid[oldCol][oldRow].getCoords()[0];
            currentRow = cameFromGrid[oldCol][oldRow].getCoords()[1];

        }
        result.add(0,Grid[currentCol][currentRow]);
        return result;
    }

    /***
     * returns the unit that is the closest to this unit
     * @param gp for context
     * @param allyOfThis the SuperUnit list that is on the same side as this unit
     * @param enemyOfThis the SuperUnit list that is on the opposite side as this unit
     * @param index the index of this unit in the allyOfThis list
     */
    public void findTargetUnit(GamePanel gp, List<SuperUnit> allyOfThis, List<SuperUnit> enemyOfThis, int index){
        findMovementRange(gp, allyOfThis.get(index), true);
        SuperUnit closestUnit = enemyOfThis.get(0);
        for (SuperUnit u : enemyOfThis){
            if (movementSpeedCost[u.getCurrentTile().getCoords()[0]][u.getCurrentTile().getCoords()[1]] <
                    movementSpeedCost[closestUnit.getCurrentTile().getCoords()[0]][closestUnit.getCurrentTile().getCoords()[1]] &&
                    !u.isDestroyed()){
                closestUnit = u;
            }
        }
        allyOfThis.get(index).setTargetUnit(closestUnit);
    }

    /***
     * this helps with the movement range finding
     */
    static class Node implements Comparable<Node> {
        Tile vertex;
        double weight;

        Node(Tile vertex, double weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.weight, other.weight);
        }
    }
}
