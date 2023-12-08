package main;

import entity.SuperUnit;
import menu.PanelManager;
import org.junit.Before;
import tile.Tile;

import static org.junit.Assert.*;

public class GamePanelTest {


    @org.junit.Test
    public void getUnitFromTile1() {
        PanelManager panM = new PanelManager();
        GamePanel gp = new GamePanel(panM);
        SuperUnit superU = gp.ally.get(0);
        Tile superTile = superU.getCurrentTile();
        assertEquals(superU, gp.getUnitFromTile(superTile));
    }

    @org.junit.Test
    public void getUnitFromTile2() {
        PanelManager panM = new PanelManager();
        GamePanel gp = new GamePanel(panM);
        SuperUnit superU = gp.ally.get(0);
        Tile superTile = superU.getOtherCurrentTile();
        assertEquals(superU, gp.getUnitFromTile(superTile));
    }

    @org.junit.Test
    public void getCycleState1() {
        PanelManager panM = new PanelManager();
        GamePanel gp = new GamePanel(panM);
        assertEquals(GamePanel.Cycle.T1MOVE,gp.getCycleState());
    }
    @org.junit.Test
    public void getCycleState2() {
        PanelManager panM = new PanelManager();
        GamePanel gp = new GamePanel(panM);
        gp.nextCycleState();
        assertEquals(GamePanel.Cycle.T2ATTACK,gp.getCycleState());
    }

    @org.junit.Test
    public void getCycleNumber1() {
        PanelManager panM = new PanelManager();
        GamePanel gp = new GamePanel(panM);
        assertEquals(1,gp.getCycleNumber());
    }
    @org.junit.Test

    public void getCycleNumber2() {
        PanelManager panM = new PanelManager();
        GamePanel gp = new GamePanel(panM);
        gp.nextCycleState();
        gp.nextCycleState();
        gp.nextCycleState();
        gp.nextCycleState();
        assertEquals(2,gp.getCycleNumber());
    }

    @org.junit.Test
    public void getTeam1Name() {
        PanelManager panM = new PanelManager();
        GamePanel gp = new GamePanel(panM);
        assertEquals("Ferenciek", gp.getTeam1Name());
    }

    @org.junit.Test
    public void getTeam2Name() {
        PanelManager panM = new PanelManager();
        GamePanel gp = new GamePanel(panM);
        assertEquals("Apátok", gp.getTeam2Name());
    }
}