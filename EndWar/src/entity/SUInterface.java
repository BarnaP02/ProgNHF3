package entity;

import main.GamePanel;

public interface SUInterface {
    /***
     * used for making a new unit with the parameters in the map file
     * @param gp needed for everything
     * @param line array of the parameters
     */
    void create(GamePanel gp, String[] line);
}
