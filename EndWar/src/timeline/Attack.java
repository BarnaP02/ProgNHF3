package timeline;

import entity.SuperUnit;
import main.GamePanel;

public class Attack extends Order{
    private SuperUnit targetUnit;

    public Attack(String side, int index, SuperUnit targetUnit){
        this.side = side;
        this.index = index;
        this.targetUnit = targetUnit;
    }

    @Override
    public void complete(GamePanel gp) {

    }
}
