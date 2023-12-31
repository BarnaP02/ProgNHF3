package entity;

import main.GamePanel;
import main.KeyHandler;
import tile.Tile;

import javax.imageio.ImageIO;
import javax.swing.plaf.ViewportUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cruser extends Entity implements Serializable {

    private GamePanel gp;
    private transient KeyHandler keyH;
    private boolean fromLeft;
    private Tile hover;
    private Tile last_hover;
    private int screenX;
    private int screenY;
    private transient List<BufferedImage> imgList;

    /***
     * Constructor for the Cruser
     * @param gp the GamePanel is needed for it's values
     * @param keyH a KeyHandler is needed to make the cursor to respond to keyboard input
     */
    public Cruser(GamePanel gp, KeyHandler keyH) {
        imgList = new ArrayList<>();
        this.gp = gp;
        this.keyH = keyH;
        fromLeft = false;
        hover = gp.Grid[0][0];
        last_hover = gp.Grid[0][0];

        screenX = gp.tileWidth/2;
        screenY = 0;

        setDefaultValues();
        getCruserImage();
    }

    /***
     * sets the default values for the cursor
     */
    public void setDefaultValues() {
        worldX = gp.tileWidth/2;
        worldY = 0;
        isVisible = true;
        direction = 4;
    }

    /***
     * loads and stores the different images for this cursor
     */
    public void getCruserImage() {
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/cruser/cursor1.png"));
            List<BufferedImage> imgls = new ArrayList<>();
            imgls.add(image);
            imgList.add(gp.imagS.changeColor(imgls,gp.team1Color[0], gp.team1Color[1], gp.team1Color[2], false).get(0));
            image = ImageIO.read(getClass().getResourceAsStream("/cruser/cursor2.png"));
            imgls = new ArrayList<>();
            imgls.add(image);
            imgList.add(gp.imagS.changeColor(imgls,gp.team1Color[0], gp.team1Color[1], gp.team1Color[2], false).get(0));

            image = ImageIO.read(getClass().getResourceAsStream("/cruser/cursor1.png"));
            imgls = new ArrayList<>();
            imgls.add(image);
            imgList.add(gp.imagS.changeColor(imgls,gp.team2Color[0], gp.team2Color[1], gp.team2Color[2], false).get(0));
            image = ImageIO.read(getClass().getResourceAsStream("/cruser/cursor2.png"));
            imgls = new ArrayList<>();
            imgls.add(image);
            imgList.add(gp.imagS.changeColor(imgls,gp.team2Color[0], gp.team2Color[1], gp.team2Color[2], false).get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * the method that is being called while the Thread is running
     * this is where cursor responds to keyboard input
     */
    public void update(){
        slowCounter++;
        if (slowCounter!=slowNum) { return; }
        slowCounter = 0;
        if (keyH.upPressed && (hover.getBorder(0)!=null || hover.getBorder(5) != null)) {
            moveUp();
        }
        if (keyH.downPressed && (hover.getBorder(2)!=null || hover.getBorder(3) != null)) {
            moveDown();
        }
        if (keyH.leftPressed && hover.getBorder(1)!=null) {
            moveLeft();
        }
        if (keyH.rightPressed && hover.getBorder(4)!=null) {
            moveRight();
        }
        if (keyH.spacePressed) {
            direction = 6;
        }
    }

    /***
     * makes the cursor to move up to the next row
     */
    public void moveUp(){
        worldY -= gp.tileHeight/4*3;
        if(fromLeft) {
            worldX += gp.tileWidth / 2;
            fromLeft = false;
            direction = 0;
            last_hover = hover;
            //hover = hover.getBorder(0);
        } else {
            worldX -= gp.tileWidth / 2;
            fromLeft = true;
            direction = 5;
            last_hover = hover;
            //hover = hover.getBorder(5);
        }
        hover = gp.Grid[hover.getCoords()[0]][hover.getCoords()[1]-1];
        gp.playSFX(4);
        if(fromLeft) {
            screenX -= gp.tileWidth / 2;
        }
        else {
            screenX += gp.tileWidth / 2;
        }
        if (!nearScreenTop() || nearTopEdge()) {      //ha nincs közel a palya szelehez akkor o mozog, egyebkent a palya
            screenY -= gp.tileHeight/4*3;
        }
    }

    /***
     * makes the cursor to move down to the next row
     */
    public void moveDown(){
        worldY += gp.tileHeight/4*3;
        if(fromLeft) {
            worldX += gp.tileWidth / 2;
            fromLeft = false;
            direction = 2;
            last_hover = hover;
            //hover = hover.getBorder(2);
        } else {
            worldX -= gp.tileWidth / 2;
            fromLeft = true;
            direction = 3;
            last_hover = hover;
            //hover = hover.getBorder(3);
        }
        hover = gp.Grid[hover.getCoords()[0]][hover.getCoords()[1]+1];
        gp.playSFX(4);
        if(fromLeft) {
            screenX -= gp.tileWidth / 2;
        }
        else {
            screenX += gp.tileWidth / 2;
        }
        if (!nearScreenBot() || nearBotEdge()) {      //ha nincs közel a palya szelehez akkor o mozog, egyebkent a palya
            screenY += gp.tileHeight/4*3;
        }
    }

    /***
     * makes the cursor move to the tile on the left
     */
    public void moveLeft(){
        worldX -= gp.tileWidth;
        direction = 1;
        last_hover = hover;
        hover = gp.Grid[hover.getCoords()[0]-1][hover.getCoords()[1]];
        gp.playSFX(4);
        if (!nearScreenLeft() || nearLeftEdge()){      //ha nincs közel a palya szelehez akkor o mozog, egyebkent a palya
            screenX -= gp.tileWidth;
        }
    }

    /***
     * makes the cursor move to the neighboring tile on the right
     */
    public void moveRight(){
        worldX += gp.tileWidth;
        direction = 4;
        last_hover = hover;
        hover = gp.Grid[hover.getCoords()[0]+1][hover.getCoords()[1]];
        gp.playSFX(4);
        if (!nearScreenRight() || nearRightEdge()){      //ha nincs közel a palya szelehez akkor o mozog, egyebkent a palya
            screenX += gp.tileWidth;
        }
    }

    /***
     * this is required for painting the changes on screen
     * @param g2 is needed because the movement of the cursor depends on
     *           how far it is from the sides of the screen or the map
     */
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int currentTeam;
        if (gp.getCycleState()== GamePanel.Cycle.T1MOVE || gp.getCycleState()== GamePanel.Cycle.T1ATTACK){
            currentTeam = 0;
        }
        else currentTeam = 1;

        if (gp.keyH.spacePressed){
            image = imgList.get(currentTeam * 2 + 1);
        }
        else {
            image = imgList.get(currentTeam * 2);
        }
        g2.drawImage(image, screenX, screenY, null);
    }

    /***
     * needed because cursor can't just be moved to a worldX,worldY coordinate,
     * it needs to move by simple movements
     * here the cursor gets moved to the coordinates of the given tile
     * @param destination the tile to which the cursor will be moved
     */
    public void moveTo(Tile destination){
        while (gp.cruser.getHover().getCoords()[0] < destination.getCoords()[0]){
            gp.cruser.moveRight();
        }
        while (gp.cruser.getHover().getCoords()[0] > destination.getCoords()[0]){
            gp.cruser.moveLeft();
        }
        while (gp.cruser.getHover().getCoords()[1] < destination.getCoords()[1]){
            gp.cruser.moveDown();
        }
        while (gp.cruser.getHover().getCoords()[1] > destination.getCoords()[1]){
            gp.cruser.moveUp();
        }
    }

    public void setHover(Tile hover) {
        this.hover = hover;
    }

    public Tile getHover() {
        return hover;
    }

    public int getScreenX() {
        return screenX;
    }
    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }

    public Tile getLast_hover() {
        return last_hover;
    }

    /***
     * if cursor is not close to the edge of the screen, it moves relative to the screen
     * @return returns if the cursor is close to the edge of the screen or not
     */
    public boolean isNotNearScreenEdge() {
        return screenY >= (3*gp.tileHeight/4*3+gp.tileHeight)
                && screenY < ((gp.maxScreenRow-3)*gp.tileHeight/4*3+gp.tileHeight)
                && screenX >= (3*gp.tileWidth)
                && screenX < ((gp.maxScreenCol-3)*gp.tileWidth);
    }

    /***
     * isNotNearScreenEdge but only to the left
     * @return
     */
    public boolean nearScreenLeft() {
        return screenX < (3*gp.tileWidth);
    }
    /***
     * isNotNearScreenEdge but only to the right
     * @return
     */
    public boolean nearScreenRight() {
        return screenX >= ((gp.maxScreenCol-3)*gp.tileWidth);
    }
    /***
     * isNotNearScreenEdge but only to the top
     * @return
     */
    public boolean nearScreenTop() {
        return screenY < (3*gp.tileHeight/4*3);//+gp.tileHeight);
    }
    /***
     * isNotNearScreenEdge but only to the bottom
     * @return
     */
    public boolean nearScreenBot() {
        return screenY >= ((gp.maxScreenRow-3)*gp.tileHeight/4*3);// - gp.tileHeight);
    }

    /***
     * similar to isNotNearScreenEdge, but instead of the screen edge, this looks if the map edge is close
     * @return
     */
    public boolean isNotNearEdge() {
        return worldY >= (3*gp.tileHeight/4*3 + gp.tileHeight)
                && worldY < (gp.worldHeight - (3*gp.tileHeight/4*3))
                && worldX >= (3*gp.tileWidth)
                && worldX < (gp.worldWidth - 3*gp.tileWidth);
    }

    /***
     * isNotNearEdge but only to the left
     * @return
     */
    public boolean nearLeftEdge() {
        return worldX < ((3-1)*gp.tileWidth);
    }
    /***
     * isNotNearEdge but only to the right
     * @return
     */
    public boolean nearRightEdge() {
        return worldX >= (gp.worldWidth - 2.5*gp.tileWidth);
    }
    /***
     * isNotNearEdge but only to the top
     * @return
     */
    public boolean nearTopEdge() {
        return worldY < ((3-1)*gp.tileHeight/4*3);// - gp.tileHeight;
    }
    /***
     * isNotNearEdge but only to the bottom
     * @return
     */
    public boolean nearBotEdge() {
        return worldY >= (gp.worldHeight - (3*gp.tileHeight/4*3));// - gp.tileHeight);
    }

}
