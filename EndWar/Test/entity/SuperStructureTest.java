package entity;

import main.GamePanel;
import menu.PanelManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class SuperStructureTest {

    @Test
    public void repairInventory() {
        PanelManager panM = new PanelManager();
        GamePanel gp = new GamePanel(panM);
        SuperUnit sunit = null;
        for (SuperUnit su : gp.ally){
            if (!su.isAvian && !su.isNavy){
                sunit = su;
                break;
            }
        }
        assert sunit != null;
        sunit.setHp(3);
        gp.structures.get(0).storeUnit(sunit);
        gp.structures.get(0).unitArrived(sunit);
        gp.structures.get(0).repairInventory();
        assertEquals(5,gp.structures.get(0).getInventory().get(0).getHp());
    }

    @Test
    public void getSide() {
        PanelManager panM = new PanelManager();
        GamePanel gp = new GamePanel(panM);
        SuperUnit sunit = null;
        for (SuperUnit su : gp.ally){
            if (!su.isAvian && !su.isNavy){
                sunit = su;
                break;
            }
        }
        assert sunit != null;
        assertNotEquals(sunit.getTeamNum() ,gp.structures.get(0).getSide());
        gp.structures.get(0).storeUnit(sunit);
        gp.structures.get(0).unitArrived(sunit);
        assertEquals(sunit.getTeamNum() ,gp.structures.get(0).getSide());

    }
}