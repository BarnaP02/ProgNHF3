package main;

import entity.*;

import java.util.HashMap;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    /***
     * this is used for when the four parameters for a unit gets read from the map file
     * this fills a hashmap with every type of unit and creates the correct type of unit
     * @param gp because unit constructor needs it
     * @param params these are the parameters of a new unit read from the map file
     *               [0]:x coordinate on the grid
     *               [1]:y coordinate on the grid
     *               [2]: unit type
     *               [3]: the team of the unit
     */
    public void setUnit(GamePanel gp, String[] params){
        HashMap<String, SUInterface> map = new HashMap<>();
        SUInterface c = new U_infantry_L(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("0", c);
        c = new U_infantry_H(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("1", c);
        c = new U_jeep(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("2", c);
        c = new U_tank_L(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("3", c);
        c = new U_tank_H(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("4", c);
        c = new U_amphibian(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("5", c);
        c = new U_AA_S(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("6", c);
        c = new U_AA_B(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("7", c);
        c = new U_arty_L(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("8", c);
        c = new U_arty_H(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("9", c);
        c = new U_repair(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("10", c);
        c = new U_heli_T(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("11", c);
        c = new U_heli_C(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("12", c);
        c = new U_fighter(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("13", c);
        c = new U_bomber(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("14", c);
        c = new U_transport_P(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("15", c);
        c = new U_boat(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("16", c);
        c = new U_sub(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("17", c);
        c = new U_carrier(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("18", c);
        c = new U_battleship(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("19", c);
        c = new U_cargo(gp, Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[3]));
        map.put("20", c);

        SUInterface com = map.get(params[2]);
        com.create(gp, params);
    }
}
