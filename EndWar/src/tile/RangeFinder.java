package tile;

import entity.SuperUnit;
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
        movementSpeedHere[currentIdx[0]][currentIdx[1]] = u.getMovementRange();
        queue.add(u.getCurrentTile());
        while (!queue.isEmpty()){
            Tile processedTile = queue.poll();
            for (int i = 0; i < 6; ++i){
                if (processedTile.borders()[i]!=0){
                    int[] neighborCoords = processedTile.border[i].coords;
                    if (!visitedGrid[neighborCoords[0]][neighborCoords[1]]){
                        visitedGrid[neighborCoords[0]][neighborCoords[1]] = true;
                        //cameFromGrid[neighborCoords[0]][neighborCoords[1]] = processedTile;
                        movementSpeedHere[neighborCoords[0]][neighborCoords[1]] = movementSpeedHere[processedTile.coords[0]][processedTile.coords[1]] - 1;
                        queue.add(processedTile.border[i]);
                    }
                }
            }
        }
        for (int i = 0; i < gp.maxWorldCol; ++i){
            for (int j = 0; j < gp.maxWorldRow; j++) {
                if (movementSpeedHere[i][j] >= 0){
                    Grid[i][j].isHighlighted = true;
                }
                else {
                    Grid[i][j].isHighlighted = false;
                }
            }
        }
    }
    public void findMovementRange(GamePanel gp, SuperUnit u){

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        //boolean[][] distance = new boolean[gp.maxWorldCol][gp.maxWorldRow];
        for (int i = 0; i < gp.maxWorldCol; ++i){
            for (int j = 0; j < gp.maxWorldRow; j++) {
                movementSpeedCost[i][j] = 100000.0;
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
                        case "hill":
                            traverseIndex = 1;
                            break;
                        case "forest":
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
                        default:
                            traverseIndex = 0;
                    }
                    if (neighbor.isObstacle){
                        traverseIndex = 2;
                    }
                    double weight = u.getTraverseSpeed()[traverseIndex];
                    if (!u.isAvian() && !u.isNavy() && processedTile.isRoad && neighbor.isRoad){
                        weight = 0.5;
                    }

                    if (movementSpeedCost[processedTile.coords[0]][processedTile.coords[1]] != 100000.0 &&
                            movementSpeedCost[processedTile.coords[0]][processedTile.coords[1]] + weight <
                            movementSpeedCost[neighborCoords[0]][neighborCoords[1]]) {
                        cameFromGrid[neighborCoords[0]][neighborCoords[1]] = processedTile;
                        movementSpeedCost[neighborCoords[0]][neighborCoords[1]] = movementSpeedCost[processedTile.coords[0]][processedTile.coords[1]] + weight;
                        priorityQueue.add(new Node(neighbor, movementSpeedCost[neighborCoords[0]][neighborCoords[1]]));
                    }
                }
            }
        }
        List<Tile> route = new ArrayList<>();


        for (int i = 0; i < gp.maxWorldCol; ++i){
            for (int j = 0; j < gp.maxWorldRow; j++) {
                if (movementSpeedCost[i][j] <= u.getMovementRange()){
                    Grid[i][j].isHighlighted = true;
                }
                else {
                    Grid[i][j].isHighlighted = false;
                }
            }
        }
        for (int i = 0; i < gp.ally.size(); i++) {
            if (gp.ally.get(i)!=u) {
                gp.ally.get(i).getCurrentTile().isHighlighted = false;
                gp.ally.get(i).getOtherCurrentTile().isHighlighted = false;
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
        //gp.Grid[currentCol][currentRow].setImage(gp.Grid[currentCol][currentRow].getImageShaded());
        return result;
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
