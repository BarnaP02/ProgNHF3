package entity;

import main.GamePanel;
import tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class U_transport_P extends SuperUnit implements SUInterface {

    @Override
    public void create(GamePanel gp, String[] params) {
        direction = 3;
        //String[] init = line.split(",");
        U_transport_P unit = new U_transport_P(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_transport_P(){}

    public U_transport_P(GamePanel gp, int tileX, int tileY, int teamNum){
        //imgList = new ArrayList<>();
        type = "transportP";
        this.teamNum = teamNum;
        setCurrentTile(gp,tileX,tileY);
        setOtherCurrentTile(getCurrentTile());
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        direction = (3+teamNum*4)%6;
        isAvian = true;
        traverseSpeed = new double[]{1,20,1,1,1,1};
        movementRange = 8;
        attackRange = new int[]{0, 0, 0, 0, 0, 0};
        defense = 10;
        attackDamage = new int[]{0, 0, 0};
        selectedSound.setFile(13);
        moveSound.setFile(29);
        attackSound.setFile(0);
        fireSound.setFile(30);
    }
}
