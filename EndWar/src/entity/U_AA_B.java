package entity;

import main.GamePanel;
import tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class U_AA_B extends SuperUnit implements SUInterface {

    @Override
    public void create(GamePanel gp, String[] params) {
        direction = 3;
        //String[] init = line.split(",");
        U_AA_B unit = new U_AA_B(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_AA_B(){}

    public U_AA_B(GamePanel gp, int tileX, int tileY){
        imgList = new ArrayList<>();
        type = "AAB";
        setCurrentTile(gp,tileX,tileY);
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        direction = 3;
        movementRange = 2;
        traverseSpeed = new double[]{1,10,10,0.6,10,10};
    }
}
