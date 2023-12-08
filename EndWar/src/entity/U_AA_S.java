package entity;

import main.GamePanel;
import main.Sound;
import tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class U_AA_S extends SuperUnit implements SUInterface {

    @Override
    public void create(GamePanel gp, String[] params) {
        direction = 3;
        U_AA_S unit = new U_AA_S(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_AA_S(){}

    public U_AA_S(GamePanel gp, int tileX, int tileY, int teamNum){
        type = "AAS";
        this.teamNum = teamNum;
        setCurrentTile(gp,tileX,tileY);
        setOtherCurrentTile(getCurrentTile());
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        direction = (3+teamNum*4)%6;
        movementRange = 3;
        traverseSpeed = new double[]{1,10,10,0.6,10,10};
        attackRange = new int[]{1, 1, 1, 1, 1, 1};
        defense = 15;
        attackDamage = new int[]{20, 25, 20};
        selectedSound.setFile(13);
        moveSound.setFile(2);
        attackSound.setFile(0);
        fireSound.setFile(26);
    }
    @Override
    public void reloadSounds(){
        selectedSound = new Sound();
        moveSound = new Sound();
        attackSound = new Sound();
        fireSound = new Sound();
        selectedSound.setFile(13);
        moveSound.setFile(2);
        attackSound.setFile(0);
        fireSound.setFile(26);
    }
}
