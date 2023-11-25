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
        U_cargo unit = new U_cargo(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_cargo(){
    }

    public U_cargo(GamePanel gp, int tileX, int tileY){
        //imgList = new ArrayList<>();
        type = "cargo";
        setCurrentTile(gp,tileX,tileY);
        direction = 1;
        setOtherCurrentTile(direction);
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        isNavy = true;
        is2tile = true;
        movementRange = 3;
        traverseSpeed = new double[]{10,10,10,10,1,1};
        selectedSound.setFile(13);
        moveSound.setFile(2);
        attackSound.setFile(0);
    }
}
