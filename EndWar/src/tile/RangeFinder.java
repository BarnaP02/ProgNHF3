package tile;

import entity.SuperStructure;
import entity.SuperUnit;
import entity.fixer;
import main.GamePanel;

import java.util.*;

public class RangeFinder {
    Tile[][] Grid;
    Tile[][] cameFromGrid;
    double[][] movementSpeedCost;
    public RangeFinder(GamePanel gp, Tile[][] grid){
        cameFromGrid = new Tile[gp.maxWorldCol][gp.maxWorldRow];
        movementSpeedCost = new double[gp.maxWorldCol][gp.maxWorldRow];
        this.Grid = grid;
    }
    public void findAttackRange(GamePanel gp, SuperUnit u){
        boolean[][] visitedGrid = new boolean[gp.maxWorldCol][gp.maxWorldRow];
        int[][] movementSpeedHere = new int[gp.maxWorldCol][gp.maxWorldRow];
        int[] currentIdx = u.getCurrentTile().coords;
        Queue<Tile> queue = new LinkedList<>();
        visitedGrid[currentIdx[0]][currentIdx[1]] = true;
        movementSpeedHere[currentIdx[0]][currentIdx[1]] = 0;//u.getMovementRange();
        queue.add(u.getCurrentTile());
        while (!queue.isEmpty()){
            Tile processedTile = queue.poll();
            for (int i = 0; i < 6; ++i){
                if (processedTile.borders()[i]!=0){
                    int[] neighborCoords = processedTile.border[i].coords;
                    if (!visitedGrid[neighborCoords[0]][neighborCoords[1]]){
                        visitedGrid[neighborCoords[0]][neighborCoords[1]] = true;
                        //cameFromGrid[neighborCoords[0]][neighborCoords[1]] = processedTile;
                        movementSpeedHere[neighborCoords[0]][neighborCoords[1]] = movementSpeedHere[processedTile.coords[0]][processedTile.coords[1]] + 1;
                        movementSpeedCost[neighborCoords[0]][neighborCoords[1]] = movementSpeedHere[neighborCoords[0]][neighborCoords[1]];
                        queue.add(processedTile.border[i]);
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
                other.getCurrentTile().isInRange = true;
                //other.getOtherCurrentTile().setIsHighlighted(true);
                //other.getOtherCurrentTile().isInRange = true;
                //other.getCurrentTile().setRed(gp.imagS.getTileGallery().get("in_range").get(0));
            }
            if (movementSpeedHere[other.getOtherCurrentTile().getCoords()[0]][other.getOtherCurrentTile().getCoords()[1]] >= u.getAttackRange()[conflictType * 2] &&
                    movementSpeedHere[other.getOtherCurrentTile().getCoords()[0]][other.getOtherCurrentTile().getCoords()[1]] <= u.getAttackRange()[conflictType * 2 + 1] &&
                    !other.isDestroyed()) {
                other.setInRange(true);
                other.getOtherCurrentTile().setIsHighlighted(true);
                other.getOtherCurrentTile().isInRange = true;
            }
            if (!other.isInRange() && !other.isDestroyed()){
                System.out.println("Unit: " + u +"Range: " + u.getAttackRange()[conflictType * 2]+":"+u.getAttackRange()[conflictType * 2 + 1]);
                other.setInRange(false);
                other.getCurrentTile().setIsHighlighted(false);
                other.getCurrentTile().isInRange = false;
                other.getOtherCurrentTile().setIsHighlighted(false);
                other.getOtherCurrentTile().isInRange = false;
            }
        }
        for (SuperUnit uni : side){
            if (!uni.isDestroyed()){
                uni.setInRange(false);
                uni.getCurrentTile().setIsHighlighted(false);
                uni.getCurrentTile().isInRange = false;
                uni.getOtherCurrentTile().setIsHighlighted(false);
                uni.getOtherCurrentTile().isInRange = false;
            }
        }
        /*for (SuperUnit uni : otherSide){
            if (!uni.isDestroyed()){
                uni.setInRange(false);
                uni.getCurrentTile().setIsHighlighted(false);
                uni.getCurrentTile().isInRange = false;
                uni.getOtherCurrentTile().setIsHighlighted(false);
                uni.getOtherCurrentTile().isInRange = false;
            }
        }*/
    }
    public void findMovementRange(GamePanel gp, SuperUnit u, boolean ai){

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        //boolean[][] distance = new boolean[gp.maxWorldCol][gp.maxWorldRow];
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
        movementSpeedCost[u.getCurrentTile().coords[0]][u.getCurrentTile().coords[1]] = 0;
        priorityQueue.add(new Node(u.getCurrentTile(), 0));

        while (!priorityQueue.isEmpty()) {
            Tile processedTile = priorityQueue.poll().vertex;
            for (int i = 0; i < 6; ++i) {
                if (processedTile.borders()[i] != 0) {
                    int[] neighborCoords = processedTile.border[i].coords;
                    Tile neighbor = Grid[neighborCoords[0]][neighborCoords[1]];
                    int traverseIndex;
                    switch (neighbor.getType()){
                        /*case "hill":
                            traverseIndex = 1;
                            break;*/
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
                    if (neighbor.isObstacle){
                        traverseIndex = 2;
                    }
                    if (neighbor.isStructure){
                        traverseIndex = 1;
                    }
                    if (u.getCurrentTile() != u.getOtherCurrentTile() && neighbor.isRoad) {
                        traverseIndex = 2;
                    }
                    double weight = u.getTraverseSpeed()[traverseIndex];
                    if (!u.isAvian() && !u.isNavy() && processedTile.isRoad && neighbor.isRoad){
                        weight = 0.5;
                    }

                    if (movementSpeedCost[processedTile.coords[0]][processedTile.coords[1]] != 100000.0 &&
                            movementSpeedCost[processedTile.coords[0]][processedTile.coords[1]] + weight <
                            movementSpeedCost[neighborCoords[0]][neighborCoords[1]] &&
                            (movementSpeedCost[neighborCoords[0]][neighborCoords[1]] != 100010.0 || ai)) {
                        cameFromGrid[neighborCoords[0]][neighborCoords[1]] = processedTile;
                        movementSpeedCost[neighborCoords[0]][neighborCoords[1]] = movementSpeedCost[processedTile.coords[0]][processedTile.coords[1]] + weight;
                        priorityQueue.add(new Node(neighbor, movementSpeedCost[neighborCoords[0]][neighborCoords[1]]));
                    }
                }
            }
        }

        for (int i = 0; i < gp.maxWorldCol; ++i){
            for (int j = 0; j < gp.maxWorldRow; j++) {
                //tile is in range or unit is navy but tile is not bridge
                Grid[i][j].isHighlighted = movementSpeedCost[i][j] <= u.getMovementRange() && (!u.isNavy() || (u.isNavy() && !Grid[i][j].isRoad));
                SuperStructure supture = gp.getStructureFromTile(Grid[i][j]);
                if (supture != null){
                    if (!supture.thereIsStillSpace()){
                        Grid[i][j].isHighlighted = false;
                    }
                }
            }
        }

        if (u.getClass() != fixer.class){
            for (int i = 0; i < gp.ally.size(); i++) {
                if (gp.ally.get(i) != u && !gp.ally.get(i).isDestroyed() && gp.ally.get(i).isVisible()) {
                    gp.ally.get(i).getCurrentTile().isHighlighted = false;
                    gp.ally.get(i).getOtherCurrentTile().isHighlighted = false;
                }
            }
            for (int i = 0; i < gp.enemy.size(); i++) {
                if (gp.enemy.get(i) != u && !gp.enemy.get(i).isDestroyed() && gp.enemy.get(i).isVisible()) {
                    gp.enemy.get(i).getCurrentTile().isHighlighted = false;
                    gp.enemy.get(i).getOtherCurrentTile().isHighlighted = false;
                }
            }

        }
    }

    public List<Tile> findMovementPath(GamePanel gp, SuperUnit u, Tile dst){
        List<Tile> result = new ArrayList<>();
        int currentCol = dst.getCoords()[0];
        int currentRow = dst.getCoords()[1];
        while (movementSpeedCost[currentCol][currentRow] > 0.0){
            //while (cameFromGrid[currentCol][currentRow] != u.getCurrentTile()){
            result.add(0,Grid[currentCol][currentRow]);
            //gp.Grid[currentCol][currentRow].setImage(gp.Grid[currentCol][currentRow].getImageShaded());
            int oldCol = Grid[currentCol][currentRow].getCoords()[0];
            int oldRow = Grid[currentCol][currentRow].getCoords()[1];
            currentCol = cameFromGrid[oldCol][oldRow].getCoords()[0];
            currentRow = cameFromGrid[oldCol][oldRow].getCoords()[1];
        }
        result.add(0,Grid[currentCol][currentRow]);
        if (u.is2tile()){
            result.add(0,u.getOtherCurrentTile());
        }
        //gp.Grid[currentCol][currentRow].setImage(gp.Grid[currentCol][currentRow].getImageShaded());
        return result;
    }

    public List<Tile> findMovementPathToUnit(GamePanel gp, SuperUnit u, SuperUnit target){
        if (target.getCurrentTile().isInRange()) return new ArrayList<>();
        if (target.getOtherCurrentTile().isInRange()) return new ArrayList<>();
        findMovementRange(gp, u, true);
        List<Tile> result = new ArrayList<>();
        int currentCol = target.getCurrentTile().getCoords()[0];
        int currentRow = target.getCurrentTile().getCoords()[1];
        while (movementSpeedCost[currentCol][currentRow] > 0.0){
            //if (movementSpeedCost[currentCol][currentRow] > u.getMovementRange()) break;
            if (gp.getUnitFromTile(Grid[currentCol][currentRow]) == null) {
                //gp.Grid[currentCol][currentRow].setImage(gp.Grid[currentCol][currentRow].getImageShaded());
                result.add(0,Grid[currentCol][currentRow]);

            }
            int oldCol = Grid[currentCol][currentRow].getCoords()[0];
            int oldRow = Grid[currentCol][currentRow].getCoords()[1];
            currentCol = cameFromGrid[oldCol][oldRow].getCoords()[0];
            currentRow = cameFromGrid[oldCol][oldRow].getCoords()[1];

        }
        result.add(0,Grid[currentCol][currentRow]);
        //gp.Grid[currentCol][currentRow].setImage(gp.Grid[currentCol][currentRow].getImageShaded());
        return result;
    }

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
