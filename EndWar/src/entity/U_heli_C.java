package entity;

import main.GamePanel;
import tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class U_heli_C extends SuperUnit implements SUInterface {

    @Override
    public void create(GamePanel gp, String[] params) {
        direction = 3;
        //String[] init = line.split(",");
        U_heli_C unit = new U_heli_C(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_heli_C(){}

    public U_heli_C(GamePanel gp, int tileX, int tileY, int teamNum){
        //slowNum = 59;
        //imgList = new ArrayList<>();
        type = "heliC";
        this.teamNum = teamNum;
        setCurrentTile(gp,tileX,tileY);
        setOtherCurrentTile(getCurrentTile());
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        direction = (3+teamNum*4)%6;
        isHeli = true;
        isAvian = true;
        movementRange = 7;
        traverseSpeed = new double[]{1,20,1,1,1,1};
        attackRange = new int[]{1, 1, 1, 1, 1, 1};
        defense = 15;
        attackDamage = new int[]{25, 15, 40};
        selectedSound.setFile(13);
        moveSound.setFile(8);
        attackSound.setFile(0);
        fireSound.setFile(32);
    }
}
