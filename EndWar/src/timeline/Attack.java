package timeline;

import entity.SuperUnit;
import main.GamePanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Attack extends Order{
    private SuperUnit targetUnit;
    boolean isRangedAttack;
    private int slowcounter = 20;
    private int slowNum = 0;
    private int iter = 0;
    private int bonus = 0;
    //ArrayList<SuperUnit> side;
    ArrayList<SuperUnit> otherSide;
    private int ogAttackerHp = 0;
    private int ogDefenderHp = 0;

    public Attack(GamePanel gp, SuperUnit striker, SuperUnit targetUnit){
        //this.side = side;
        //this.index = index;
        this.targetUnit = targetUnit;
        isRangedAttack = true;
        actor = striker;
        if (striker.getTeamNum()==0){
            otherSide = gp.enemy;
            side = gp.ally;
        }
        else {
            otherSide = gp.ally;
            side = gp.enemy;
        }
        for (int i = 0; i < side.size(); ++i){
            if (side.get(i) == striker){
                index = i;
            }
        }
        for (int i = 0; i < 6; ++i){
            if (side.get(index).getCurrentTile().getBorder(i) == targetUnit.getCurrentTile() ||
                    side.get(index).getCurrentTile().getBorder(i) == targetUnit.getOtherCurrentTile()){
                isRangedAttack = false;
            }
        }
        ogAttackerHp = side.get(index).getHp();
        ogDefenderHp = targetUnit.getHp();
        striker.setActed(true);
    }

    @Override
    public void complete(GamePanel gp) {
        slowNum++;
        if (slowcounter != slowNum){
            return;
        }
        else{
            slowNum = 0;
            iter++;
        }
        if (iter < 3){
            gp.cruser.moveTo(side.get(index).getCurrentTile());
            return;
        }
        if (!isRangedAttack){
            if (iter < 9){
                int defaultDirection = 0;
                for (int i = 0; i < 6; ++i) {
                    if (targetUnit.getCurrentTile().getBorder(i) == side.get(index).getCurrentTile() ||
                            targetUnit.getOtherCurrentTile().getBorder(i) == side.get(index).getCurrentTile()){
                        defaultDirection = i;
                    }
                }
                side.get(index).setDirection((defaultDirection+3)%6);
                if (targetUnit.getCurrentTile().borders()[(defaultDirection+iter-3)%6] != 0) {
                    //if (targetUnit.getCurrentTile().borders()[iter-3] != 0) {
                    //System.out.println(Arrays.toString(targetUnit.getCurrentTile().borders()) +" so target: "+);
                    gp.cruser.moveTo(targetUnit.getCurrentTile().getBorder((defaultDirection+iter-3)%6));
                }
                return;
            }
            if(iter < 10){
                gp.cruser.moveTo(targetUnit.getCurrentTile());
                side.get(index).getFireSound().play();
                resolveConflict(side.get(index), targetUnit);
                if (targetUnit.isDestroyed()) gp.playSFX(27);
                gp.rFinder.findAttackRange(gp,targetUnit);
                if (side.get(index).isInRange()){
                    targetUnit.getFireSound().play();
                    resolveConflict(targetUnit, side.get(index));
                    if (side.get(index).isDestroyed()) gp.playSFX(27);
                }
                //end of selection
                for (int i = 0; i < gp.maxWorldCol; ++i){
                    for (int j = 0; j < gp.maxWorldRow; j++) {
                        gp.Grid[i][j].setIsHighlighted(true);
                        gp.Grid[i][j].setInRange(false);
                    }
                }
                gp.recievedDamage(side.get(index));
                gp.recievedDamage(targetUnit);

            }
            if (iter > 11){
                setCompleted(true);
                side.get(index).setActed(false);
                side.get(index).setLastImgIndex(-1);

                if (ogDefenderHp > targetUnit.getHp() && side.get(index).getXp() < 6){
                    side.get(index).setXp(side.get(index).getXp()+1);
                }
                if (targetUnit.getHp() < 1 && side.get(index).getXp() < 6){
                    side.get(index).setXp(side.get(index).getXp()+1);
                }

                if (ogAttackerHp > side.get(index).getHp() && targetUnit.getXp() < 6){
                    targetUnit.setXp(targetUnit.getXp()+1);
                }
                if (side.get(index).getHp() < 1 && targetUnit.getXp() < 6){
                    targetUnit.setXp(targetUnit.getXp()+1);
                }
                if (side.get(index).getHp() < 1){
                    side.get(index).setDestroyed(true);
                }
                if (targetUnit.getHp() < 1){
                    targetUnit.setDestroyed(true);
                }
                if (side.get(index).isDestroyed()) gp.playSFX(27);
                if (targetUnit.isDestroyed()) gp.playSFX(27);
            }
        }
        else {
            if (iter < 4){
                side.get(index).getFireSound().play();
                resolveConflict(side.get(index), targetUnit);
                gp.recievedDamage(targetUnit);
                return;
            }
            if (iter < 5){
                //side.get(index).getFireSound().play();
                //resolveConflict(side.get(index), targetUnit);
                //gp.recievedDamage(targetUnit);
            }
            if (iter > 6){
                gp.cruser.moveTo(targetUnit.getCurrentTile());
                setCompleted(true);
                side.get(index).setActed(false);
                side.get(index).setLastImgIndex(-1);
                if (ogDefenderHp > targetUnit.getHp() && side.get(index).getXp() < 6){
                    side.get(index).setXp(side.get(index).getXp()+1);
                }
                if (targetUnit.getHp() < 1 && side.get(index).getXp() < 6){
                    side.get(index).setXp(side.get(index).getXp()+1);
                }
                if (targetUnit.getHp() < 1){
                    targetUnit.setDestroyed(true);
                }
                if (targetUnit.isDestroyed()) gp.playSFX(27);

                /*if (ogAttackerHp > side.get(index).getHp() && targetUnit.getXp() < 6){
                    targetUnit.setXp(targetUnit.getXp()+1);
                }
                if (side.get(index).getHp() < 1 && targetUnit.getXp() < 6){
                    targetUnit.setXp(targetUnit.getXp()+1);
                }*/
            }
        }


        //System.out.println(side.get(index)+" attacked " + targetUnit + " for " + (ogDefenderHp - targetUnit.getHp()));
    }
    public void resolveConflict(SuperUnit attacker, SuperUnit defender){
        int conflictType;
        if (defender.isAvian()){
            conflictType = 1;
        }
        else if (defender.isNavy()){
            conflictType = 2;
        }
        else {
            conflictType = 0;
        }
        if (defender.getHp() > 0){
            int damage = attacker.getHp() * attacker.getAttackDamage()[conflictType] + attacker.getXp()/2 * attacker.getAttackDamage()[conflictType];
            int defense = defender.getHp() * defender.getDefense() + defender.getXp()/2 * defender.getDefense();
            int damageInHP = (damage-defense)/defender.getDefense();
            System.out.println(side.get(index)+" attacks "+defender+" for "+damageInHP);
            if (damageInHP > 0) defender.setHp(defender.getHp()-damageInHP);
        }
    }
}
