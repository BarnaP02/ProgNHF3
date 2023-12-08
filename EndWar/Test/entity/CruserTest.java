package entity;

import main.GamePanel;
import menu.PanelManager;
import org.junit.Test;
import tile.Tile;

import java.awt.*;

import static org.junit.Assert.*;

public class CruserTest {

    @Test
    public void moveLeft() {
        PanelManager panM = new PanelManager();
        GamePanel gp = new GamePanel(panM);
        Cruser cursor = new Cruser(gp, gp.keyH);
        cursor.moveRight();
        cursor.moveRight();
        cursor.moveDown();
        cursor.moveDown();
        Tile t = cursor.getHover();
        cursor.moveLeft();
        assertSame(t.getBorder(1), cursor.getHover());
    }

    @Test
    public void moveRight() {
        PanelManager panM = new PanelManager();
        GamePanel gp = new GamePanel(panM);
        Cruser cursor = new Cruser(gp, gp.keyH);
        Tile t = cursor.getHover();
        cursor.moveRight();
        assertSame(t.getBorder(4), cursor.getHover());
    }

    @Test
    public void getHover() {
        PanelManager panM = new PanelManager();
        GamePanel gp = new GamePanel(panM);
        Cruser cursor = new Cruser(gp, gp.keyH);
        Tile t = cursor.getHover();
        cursor.moveRight();
        cursor.moveRight();
        cursor.moveRight();
        assertSame(t.getBorder(4).getBorder(4).getBorder(4), cursor.getHover());
    }

    @Test
    public void getLast_hover() {
        PanelManager panM = new PanelManager();
        GamePanel gp = new GamePanel(panM);
        Cruser cursor = new Cruser(gp, gp.keyH);
        Tile t = cursor.getHover();
        cursor.moveRight();
        assertSame(t, cursor.getLast_hover());
    }
}