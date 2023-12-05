package entity;

import main.GamePanel;
import tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class U_fighter extends SuperUnit implements SUInterface {

    @Override
    public void create(GamePanel gp, String[] params) {
        direction = 3;
        //String[] init = line.split(",");
        U_fighter unit = new U_fighter(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_fighter(){}

    public U_fighter(GamePanel gp, int tileX, int tileY, int teamNum){
        //imgList = new ArrayList<>();
        type = "fighter";
        this.teamNum = teamNum;
        setCurrentTile(gp,tileX,tileY);
        setOtherCurrentTile(getCurrentTile());
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        direction = (3+teamNum*4)%6;
        isAvian = true;
        movementRange = 10;
        traverseSpeed = new double[]{1,20,1,1,1,1};
        attackRange = new int[]{0, 0, 1, 1, 0, 0};
        defense = 20;
        attackDamage = new int[]{0, 25, 0};
        selectedSound.setFile(13);
        moveSound.setFile(29);
        attackSound.setFile(0);
        fireSound.setFile(32);
    }
}
