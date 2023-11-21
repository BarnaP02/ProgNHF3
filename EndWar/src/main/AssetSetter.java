package main;

import entity.*;

import java.util.HashMap;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setUnit(GamePanel gp, String[] params){
        HashMap<String, SUInterface> map = new HashMap<>();
        SUInterface c = new U_infantry_L(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        map.put("0", c);
        c = new U_infantry_H(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        map.put("1", c);
        c = new U_jeep(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        map.put("2", c);
        c = new U_tank_L(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        map.put("3", c);
        c = new U_tank_H(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        map.put("4", c);
        c = new U_amphibian(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        map.put("5", c);
        c = new U_AA_S(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        map.put("6", c);
        c = new U_AA_B(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        map.put("7", c);
        c = new U_arty_L(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        map.put("8", c);
        c = new U_arty_H(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        map.put("9", c);
        c = new U_repair(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        map.put("10", c);
        c = new U_heli_T(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        map.put("11", c);
        c = new U_heli_C(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        map.put("12", c);
        c = new U_fighter(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        map.put("13", c);
        c = new U_bomber(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        map.put("14", c);
        c = new U_transport_P(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        map.put("15", c);
        c = new U_boat(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        map.put("16", c);
        c = new U_sub(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        map.put("17", c);
        //c = new U_infantry_L();
        //map.put("1", c);

        SUInterface com = map.get(params[2]);
        com.create(gp, params);
        /*        gp.army[0] = new U_infantry_L();
        gp.army[0].worldX = 22 * gp.tileWidth;
        gp.army[0].worldY = 7 * gp.tileHeight/4*3+gp.tileHeight;
        //8,22
        gp.army[1] = new U_infantry_L();
        gp.army[1].worldX = gp.tileWidth / 2;
        gp.army[1].worldY = 0;*/

        /*gp.army[0] = new U_infantry_L(-1);
        gp.army[0].setCurrentTile(gp,23,7);
        gp.army[0].worldX = 23 * gp.tileWidth;
        gp.army[0].worldY = 6 * gp.tileHeight/4*3+gp.tileHeight;

        gp.army[1] = new U_infantry_L(-1);
        gp.army[1].setCurrentTile(gp,1,1);
        gp.army[1].worldX = gp.tileWidth / 2;
        gp.army[1].worldY = gp.tileHeight/4;

        gp.army[2] = new U_infantry_L(0);
        gp.army[2].setCurrentTile(gp,22,7);
        gp.army[2].worldX = 22 * gp.tileWidth;
        gp.army[2].worldY = 6 * gp.tileHeight/4*3+gp.tileHeight;

        gp.army[3] = new U_infantry_L(1);
        gp.army[3].setCurrentTile(gp,21,7);
        gp.army[3].worldX = 21 * gp.tileWidth;
        gp.army[3].worldY = 6 * gp.tileHeight/4*3+gp.tileHeight;

        gp.army[4] = new U_infantry_L(2);
        gp.army[4].setCurrentTile(gp,20,7);
        gp.army[4].worldX = 20 * gp.tileWidth;
        gp.army[4].worldY = 6 * gp.tileHeight/4*3+gp.tileHeight;

        gp.army[5] = new U_infantry_L(3);
        gp.army[5].setCurrentTile(gp,19,7);
        gp.army[5].worldX = 19 * gp.tileWidth;
        gp.army[5].worldY = 6 * gp.tileHeight/4*3+gp.tileHeight;

        gp.army[6] = new U_infantry_L(4);
        gp.army[6].setCurrentTile(gp,22,5);
        gp.army[6].worldX = 22 * gp.tileWidth;
        gp.army[6].worldY = 4 * gp.tileHeight/4*3+gp.tileHeight;

        gp.army[7] = new U_infantry_L(5);
        gp.army[7].setCurrentTile(gp,20,5);
        gp.army[7].worldX = 20 * gp.tileWidth;
        gp.army[7].worldY = 4 * gp.tileHeight/4*3+gp.tileHeight;

        gp.army[8] = new U_infantry_L(1);
        gp.army[8].setCurrentTile(gp,18,6);
        gp.army[8].worldX = 18 * gp.tileWidth + gp.tileWidth;
        gp.army[8].worldY = 5 * gp.tileHeight/4*3+gp.tileHeight;

        gp.army[9] = new U_infantry_L(4);
        gp.army[9].setCurrentTile(gp,23,6);
        gp.army[9].worldX = 23 * gp.tileWidth + gp.tileWidth;
        gp.army[9].worldY = 5 * gp.tileHeight/4*3+gp.tileHeight;*/
    }
}
