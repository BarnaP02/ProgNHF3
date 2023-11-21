package entity;

import main.GamePanel;

import java.util.ArrayList;

public class U_bomber extends SuperUnit implements SUInterface {

    @Override
    public void create(GamePanel gp, String[] params) {
        direction = 3;
        //String[] init = line.split(",");
        U_bomber unit = new U_bomber(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_bomber(){}

    public U_bomber(GamePanel gp, int tileX, int tileY){
        imgList = new ArrayList<>();
        type = "bomber";
        setCurrentTile(gp,tileX,tileY);
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        direction = 3;
        isAvian = true;
        movementRange = 6;
        traverseSpeed = new double[]{1,1,1,1,1,1};
    }
}
