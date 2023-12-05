package entity;

import main.GamePanel;

import java.util.ArrayList;

public class U_bomber extends SuperUnit implements SUInterface {

    @Override
    public void create(GamePanel gp, String[] params) {
        direction = 3;
        //String[] init = line.split(",");
        U_bomber unit = new U_bomber(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_bomber(){}

    public U_bomber(GamePanel gp, int tileX, int tileY, int teamNum){
        //imgList = new ArrayList<>();
        type = "bomber";
        this.teamNum = teamNum;
        setCurrentTile(gp,tileX,tileY);
        setOtherCurrentTile(getCurrentTile());
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        direction = (3+teamNum*4)%6;
        isAvian = true;
        movementRange = 8;
        traverseSpeed = new double[]{1,20,1,1,1,1};
        attackRange = new int[]{1, 1, 0, 0, 1, 1};
        defense = 20;
        attackDamage = new int[]{40, 0, 55};
        selectedSound.setFile(13);
        moveSound.setFile(29);
        attackSound.setFile(0);
        fireSound.setFile(27);
    }
}
