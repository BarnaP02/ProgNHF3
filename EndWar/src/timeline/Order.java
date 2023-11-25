package timeline;

import main.GamePanel;

public class Order{
    String side;    //the switch the unit is on
    int index;    //index in unit array
    private boolean completed = false;

    //@Override
    public void complete(GamePanel gp) {}

    public void setSide(String side) {
        this.side = side;
    }

    public String getSide() {
        return side;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
