package timeline;

import entity.SuperUnit;
import main.GamePanel;
import tile.Tile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class Order implements Serializable {
    protected List<SuperUnit> side;    //the switch the unit is on
    protected int index;    //index in unit array
    protected SuperUnit actor;
    private boolean completed = false;
    public void complete(GamePanel gp) {}
    public void reverse(GamePanel gp){}

    public void setSide(List<SuperUnit> side) {
        this.side = side;
    }

    public List<SuperUnit> getSide() {
        return side;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public SuperUnit getActor() {
        return actor;
    }

    public void setActor(SuperUnit actor) {
        this.actor = actor;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public void forceFinish(GamePanel gp){}
}
