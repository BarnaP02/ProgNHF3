package entity;

import main.GamePanel;
import tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class U_arty_H extends SuperUnit implements SUInterface {

    @Override
    public void create(GamePanel gp, String[] params) {
        //String[] init = line.split(",");
        U_arty_H unit = new U_arty_H(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_arty_H(){}

    public U_arty_H(GamePanel gp, int tileX, int tileY){
        imgList = new ArrayList<>();
        type = "artyH";
        setCurrentTile(gp,tileX,tileY);
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        direction = 3;
        isArtyAbleToFire = true;
        movementRange = 2;
        traverseSpeed = new double[]{1.5,10,10,1,10,10};
    }
}
