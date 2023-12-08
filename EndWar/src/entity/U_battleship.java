package entity;

import main.GamePanel;
import main.Sound;
import tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class U_battleship extends SuperUnit implements SUInterface {

    @Override
    public void create(GamePanel gp, String[] params) {
        direction = 3;
        U_battleship unit = new U_battleship(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_battleship(){
    }

    public U_battleship(GamePanel gp, int tileX, int tileY, int teamNum){
        type = "battleship";
        this.teamNum = teamNum;
        setCurrentTile(gp,tileX,tileY);
        direction = (3+teamNum*4)%6;
        setOtherCurrentTile(direction);
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        isNavy = true;
        is2tile = true;
        movementRange = 7;
        traverseSpeed = new double[]{10,10,10,10,1,1};
        attackRange = new int[]{3, 6, 2, 5, 3, 6};
        defense = 45;
        attackDamage = new int[]{60, 30, 60};
        selectedSound.setFile(13);
        moveSound.setFile(2);
        attackSound.setFile(0);
        fireSound.setFile(31);
    }
    @Override
    public void reloadSounds(){
        selectedSound = new Sound();
        moveSound = new Sound();
        attackSound = new Sound();
        fireSound = new Sound();
        selectedSound.setFile(13);
        moveSound.setFile(2);
        attackSound.setFile(0);
        fireSound.setFile(31);
    }
}
