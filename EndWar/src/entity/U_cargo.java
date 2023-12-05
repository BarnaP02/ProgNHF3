package entity;

import main.GamePanel;
import tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class U_cargo extends SuperUnit implements SUInterface {

    @Override
    public void create(GamePanel gp, String[] params) {
        direction = 3;
        //String[] init = line.split(",");
        U_cargo unit = new U_cargo(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_cargo(){
    }

    public U_cargo(GamePanel gp, int tileX, int tileY, int teamNum){
        //imgList = new ArrayList<>();
        type = "cargo";
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
        attackRange = new int[]{1, 1, 0, 0, 1, 1};
        defense = 20;
        attackDamage = new int[]{15, 0, 15};
        selectedSound.setFile(13);
        moveSound.setFile(2);
        attackSound.setFile(0);
        fireSound.setFile(30);
    }
}
