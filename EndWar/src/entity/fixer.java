package entity;

import main.GamePanel;
import tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class fixer extends SuperUnit {

    public fixer(GamePanel gp){
        setCurrentTile(gp,1, 1);
        direction = (3+teamNum*4)%6;
        movementRange = 999;
        traverseSpeed = new double[]{1,1,1,1,1,1};
    }

    public fixer(GamePanel gp, int tileX, int tileY, int teamNum){
        //imgList = new ArrayList<>();
        System.out.println("fffff");
        type = "fixer";
        this.teamNum = teamNum;
        setCurrentTile(gp,tileX,tileY);
        direction = (3+teamNum*4)%6;
        setOtherCurrentTile(direction);
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        isNavy = true;
        is2tile = true;
        movementRange = 6;
        traverseSpeed = new double[]{10,10,10,10,1,1};
        attackRange = new int[]{0, 0, 1, 2, 2, 4};
        defense = 40;
        attackDamage = new int[]{0, 30, 40};
        selectedSound.setFile(13);
        moveSound.setFile(2);
        attackSound.setFile(0);
    }
}
