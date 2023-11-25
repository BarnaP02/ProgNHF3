package entity;

import main.GamePanel;
import tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class U_boat extends SuperUnit implements SUInterface {

    @Override
    public void create(GamePanel gp, String[] params) {
        direction = 3;
        //String[] init = line.split(",");
        U_boat unit = new U_boat(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_boat(){
        isNavy = true;
    }

    public U_boat(GamePanel gp, int tileX, int tileY){
        //imgList = new ArrayList<>();
        type = "boat";
        setCurrentTile(gp,tileX,tileY);
        setOtherCurrentTile(getCurrentTile());
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        direction = 3;
        isNavy = true;
        movementRange = 4;
        traverseSpeed = new double[]{10,10,10,10,1,1};
        selectedSound.setFile(13);
        moveSound.setFile(5);
        attackSound.setFile(0);
    }
}
