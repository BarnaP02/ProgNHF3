package entity;

import main.GamePanel;
import tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class U_infantry_H extends SuperUnit implements SUInterface {

    @Override
    public void create(GamePanel gp, String[] params) {
        direction = 3;
        //String[] init = line.split(",");
        U_infantry_H unit = new U_infantry_H(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_infantry_H(){}

    public U_infantry_H(GamePanel gp, int tileX, int tileY, int teamNum){
        //imgList = new ArrayList<>();
        type = "infantryH";
        this.teamNum = teamNum;
        setCurrentTile(gp,tileX,tileY);
        setOtherCurrentTile(getCurrentTile());
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        direction = (3+teamNum*4)%6;
        isInfantry = true;
        movementRange = 2;
        traverseSpeed = new double[]{1,1.5,1,1,10,10};
        attackRange = new int[]{1, 1, 1, 1, 1, 1};
        defense = 7;
        attackDamage = new int[]{20, 30, 15};
        selectedSound.setFile(13);
        moveSound.setFile(12);
        attackSound.setFile(11);
        fireSound.setFile(24);
    }
}
