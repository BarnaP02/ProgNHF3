package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;

public class U_jeep extends SuperUnit implements SUInterface {
    @Override
    public void create(GamePanel gp, String[] params) {
        direction = 3;
        //String[] init = line.split(",");
        U_jeep unit = new U_jeep(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        if (Integer.parseInt(params[3])==0) {
            gp.ally.add(unit);
        }
        else {
            gp.enemy.add(unit);
        }
    }
    public U_jeep(GamePanel gp, int tileX, int tileY){
        imgList = new ArrayList<>();
        type = "jeep";
        setCurrentTile(gp,tileX,tileY);
        worldX = gp.getCoordsFromTile(currentTile)[0];
        worldY = gp.getCoordsFromTile(currentTile)[1];
        direction = 4;
        movementRange = 3;
        traverseSpeed = new double[]{1,10,10,0.7,10,10};
    }
}
