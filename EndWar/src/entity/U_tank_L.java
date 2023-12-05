package entity;

import main.GamePanel;
import tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class U_tank_L extends SuperUnit implements SUInterface {

    @Override
    public void create(GamePanel gp, String[] params) {
        direction = 3;
        //String[] init = line.split(",");
        U_tank_L unit = new U_tank_L(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_tank_L(){}

    public U_tank_L(GamePanel gp, int tileX, int tileY, int teamNum){
        //imgList = new ArrayList<>();
        type = "tankL";
        this.teamNum = teamNum;
        setCurrentTile(gp,tileX,tileY);
        setOtherCurrentTile(getCurrentTile());
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        direction = (3+teamNum*4)%6;
        movementRange = 3;
        traverseSpeed = new double[]{1,10,10,0.6,10,10};
        attackRange = new int[]{1, 1, 0, 0, 1, 1};
        defense = 20;
        attackDamage = new int[]{25, 0, 25};
        selectedSound.setFile(13);
        moveSound.setFile(2);
        attackSound.setFile(0);
        fireSound.setFile(28);
    }
}
