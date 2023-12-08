package entity;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.nio.Buffer;
import java.util.List;

public class Entity implements Serializable {
    protected int worldX;
    protected int worldY;
    boolean isVisible;
    public int direction;
    public int slowCounter = 0;
    public int slowNum = 5;
    public int superSlowCounter = 0;
    public int superSlowNum = 120;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }
}
