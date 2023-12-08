package entity;

import main.GamePanel;
import main.Sound;
import tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class U_AA_B extends SuperUnit implements SUInterface {

    @Override
    public void create(GamePanel gp, String[] params) {
        direction = 3;
        U_AA_B unit = new U_AA_B(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_AA_B(){}

    public U_AA_B(GamePanel gp, int tileX, int tileY, int teamNum){
        type = "AAB";
        this.teamNum = teamNum;
        setCurrentTile(gp,tileX,tileY);
        setOtherCurrentTile(getCurrentTile());
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        direction = (3+teamNum*4)%6;
        movementRange = 3;
        traverseSpeed = new double[]{1,10,10,0.6,10,10};
        attackRange = new int[]{0, 0, 2, 5, 0, 0};
        defense = 12;
        attackDamage = new int[]{0, 30, 0};
        selectedSound.setFile(13);
        moveSound.setFile(2);
        attackSound.setFile(0);
        fireSound.setFile(24);
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
        fireSound.setFile(24);
    }
}
