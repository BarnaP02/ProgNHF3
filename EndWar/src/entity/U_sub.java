package entity;

import main.GamePanel;
import tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class U_sub extends SuperUnit implements SUInterface {

    @Override
    public void create(GamePanel gp, String[] params) {
        direction = 3;
        //String[] init = line.split(",");
        U_sub unit = new U_sub(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_sub(){}

    public U_sub(GamePanel gp, int tileX, int tileY, int teamNum){
        //imgList = new ArrayList<>();
        type = "sub";
        this.teamNum = teamNum;
        setCurrentTile(gp,tileX,tileY);
        setOtherCurrentTile(getCurrentTile());
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        direction = (3+teamNum*4)%6;
        isNavy = true;
        isSub = true;
        movementRange = 6;
        traverseSpeed = new double[]{10,10,10,10,1,1};
        attackRange = new int[]{1, 3, 1, 1, 1, 2};
        defense = 30;
        attackDamage = new int[]{20, 20, 50};
        selectedSound.setFile(13);
        moveSound.setFile(16);
        attackSound.setFile(0);
        fireSound.setFile(16);
    }
}
