package entity;

import main.GamePanel;
import tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class U_heli_T extends SuperUnit implements SUInterface {

    @Override
    public void create(GamePanel gp, String[] params) {
        direction = 3;
        //String[] init = line.split(",");
        U_heli_T unit = new U_heli_T(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_heli_T(){}

    public U_heli_T(GamePanel gp, int tileX, int tileY){
        //slowNum = 59;
        //imgList = new ArrayList<>();
        type = "heliT";
        setCurrentTile(gp,tileX,tileY);
        setOtherCurrentTile(getCurrentTile());
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        direction = 3;
        isHeli = true;
        isAvian = true;
        movementRange = 4;
        traverseSpeed = new double[]{1,1,1,1,1,1};
        selectedSound.setFile(13);
        moveSound.setFile(8);
        attackSound.setFile(0);
    }
}
