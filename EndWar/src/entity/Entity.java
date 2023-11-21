package entity;

import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.List;

public class Entity {
    public int worldX,worldY;
    boolean isVisible;

    //public BufferedImage sprite1;
    //public BufferedImage sprite2;
    protected List<BufferedImage> imgList;
    public int direction;
    public int slowCounter = 0;
    public int slowNum = 5;
    public int superSlowCounter = 0;
    public int superSlowNum = 120;
    public int spriteCounter = 0;
    public int spriteNum = 1;
}
