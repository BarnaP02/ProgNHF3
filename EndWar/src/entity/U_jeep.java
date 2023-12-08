package entity;

import main.GamePanel;
import main.Sound;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;

public class U_jeep extends SuperUnit implements SUInterface {
    @Override
    public void create(GamePanel gp, String[] params) {
        direction = 3;
        U_jeep unit = new U_jeep(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_jeep(GamePanel gp, int tileX, int tileY, int teamNum){
        type = "jeep";
        this.teamNum = teamNum;
        setCurrentTile(gp,tileX,tileY);
        setOtherCurrentTile(getCurrentTile());
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        direction = (3+teamNum*4)%6;
        movementRange = 4;
        traverseSpeed = new double[]{1,10,10,0.7,10,10};
        attackRange = new int[]{1, 2, 1, 1, 1, 2};
        defense = 6;
        attackDamage = new int[]{12, 10, 12};
        selectedSound.setFile(13);
        moveSound.setFile(5);
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
        moveSound.setFile(5);
        attackSound.setFile(0);
        fireSound.setFile(26);
    }
}
