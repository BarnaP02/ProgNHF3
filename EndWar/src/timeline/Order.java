package timeline;

import main.GamePanel;

public class Order{
    String side;    //the switch the unit is on
    int index;    //index in unit array

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
}
