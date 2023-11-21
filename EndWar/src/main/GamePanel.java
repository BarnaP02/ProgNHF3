package main;

import entity.Cruser;
import entity.ImageStore;
import tile.RangeFinder;
import tile.Tile;
import tile.TileManager;
import entity.SuperUnit;
import timeline.Order;
import timeline.OrderManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel  extends JPanel implements Runnable{
    // SCREEN SETTINGS
    final int originalTileHeight = 20;
    final int originalTileWidth = 21;
    final int scale = 2;
    public final int tileHeight = originalTileHeight * scale;
    public final int tileWidth = originalTileWidth * scale;
    public final int maxScreenCol = 21;
    public final int maxScreenRow = 19;
    public final int screenWidth = tileWidth * maxScreenCol + tileWidth/2;
    public final int screenHeight = (tileHeight * (maxScreenRow - 1)) / 4*3 + tileHeight;

    //FPS
    int FPS = 60;

    public Tile[][] Grid;
    public int[][] neighborOffsetEven = {                              //Offset in even rows
            {0, -1}, {-1, 0}, {0, 1}, {1, 1}, {1, 0}, {1, -1}
    };
    public int[][] neighborOffsetOdd = {                              //Offset in odd rows
            {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 0}, {0, -1}
    };

    //WORLD SETTINGS
    public int maxWorldCol;
    public int maxWorldRow;
    public ImageStore imagS = new ImageStore();
    public ArrayList<SuperUnit> ally = new ArrayList<>();
    public ArrayList<SuperUnit> enemy = new ArrayList<>();
    public AssetSetter aSetter = new AssetSetter(this);
    TileManager tileM = new TileManager(this);
    public final int worldWidth = tileWidth * maxWorldCol + tileWidth/2;
    public final int worldHeight = (tileHeight * (maxWorldRow - 1)) / 4*3 + tileHeight;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Cruser cruser = new Cruser(this, keyH);
    public RangeFinder rFinder = new RangeFinder(Grid);
    public OrderManager timeL = new OrderManager(this);
    public SuperUnit testUnit;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        //aSetter.setUnit();
    }
    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;


        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                //System.out.println("x:" + cursorX + " y:" + cursorY);
                drawCount = 0;
                timer = 0;

                //DEBUG PRINTF
                /*for (int i = 0; i < maxScreenRow;++i){
                    for (int j = 0; j < maxScreenCol;++j){
                        System.out.print(Grid[j][i].numOfBorder()+Grid[j][i].getType()+" ");
                    }
                    System.out.print("..........");
                    for (int j = 0; j < maxScreenCol;++j) {
                        for (int m = 0; m < 6; ++m){
                            if (Grid[j][i].getBorder(m)!=null){
                                System.out.print(Grid[j][i].getBorder(m).getCoords()[0]+","+Grid[j][i].getBorder(m).getCoords()[1]+" ");
                            }
                            System.out.print(" | ");
                        }
                        System.out.print("/|/ ");
                    }
                    System.out.println();
                }
                System.out.println();*/
                for (int i = 0; i < 6; ++i){
                    System.out.print(cruser.getHover().borders()[i]+" ");
                }
                /*System.out.println("["+cruser.getHover().getCoords()[0]+":"+cruser.getHover().getCoords()[1]+"]  ["+
                        cruser.getLast_hover().getCoords()[0]+":"+cruser.getLast_hover().getCoords()[1]+"]  [" +
                        cruser.getScreenX()+":"+cruser.getScreenY()+"]  s:"+cruser.isNotNearScreenEdge()+" e:"+cruser.isNotNearEdge()+"  t:"+
                        cruser.nearTopEdge()+" b:"+cruser.nearBotEdge()+" l:"+cruser.nearLeftEdge()+" r:"+cruser.nearRightEdge()+"  st:"+
                        cruser.nearScreenTop()+" sb:"+cruser.nearScreenBot()+" sl:"+cruser.nearScreenLeft()+" sr:"+cruser.nearScreenRight()+" ["+
                        getCoordsFromTile(cruser.getHover())[0]+","+getCoordsFromTile(cruser.getHover())[1]+"]   ["+
                        getCoordsFromTile(getTileFromWorldCoords(cruser.worldX,cruser.worldY))[0]+","+
                        getCoordsFromTile(getTileFromWorldCoords(cruser.worldX,cruser.worldY))[1]+"]  ["+
                        cruser.getHover().worldX+":"+
                        cruser.getHover().worldY+"]  ["+
                        cruser.getHover().screenX+":"+
                        cruser.getHover().screenY+"] ");*/
                for (SuperUnit su : ally){
                    //System.out.print("["+su.worldX+":"+su.worldY+"] ");
                }
                //System.out.println();
                for (Order o : timeL.getTimeline()){
                    System.out.print(", "+o.getSide()+"["+o.getIndex()+"] ");
                }
                System.out.println();
            }
        }
    }
    public void update() {
        cruser.update();
        timeL.update(this, keyH);
    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //DEBUG
        long drawStart = 0;
        if (keyH.checkDrawTime){
            drawStart = System.nanoTime();
        }

        tileM.draw(g2);
        cruser.draw(g2);
        for (SuperUnit superUnit : ally) {
            if (superUnit != null) {
                superUnit.draw(g2, this);
            }
        }
        for (SuperUnit superUnit : enemy) {
            if (superUnit != null) {
                superUnit.draw(g2, this);
            }
        }

        if (keyH.checkDrawTime){
            long drawEnd = System.nanoTime();
            long passed = drawEnd-drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose();
    }
    public Tile getTileFromWorldCoords(int searchedWorldX, int searchedWorldY){
        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < maxWorldCol && worldRow < maxWorldRow){

            int worldX = tileWidth * worldCol;
            int worldY; // = (tileHeight * (worldRow - 1)) / 4*3 + tileHeight;
            if (worldRow > 0){
                worldY = (tileHeight * (worldRow )) / 4*3;// + tileHeight;
            }
            else{
                worldY = (tileHeight * (worldRow));
            }
            if (worldRow % 2 == 0) {
                worldX += tileWidth/2;
            }
            if (worldX==searchedWorldX && worldY==searchedWorldY) { return Grid[worldCol][worldRow]; }
            worldCol++;
            if (worldCol == maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
        return null;
    }
    public int[] getCoordsFromTile(Tile t){
        int[] result = new int[2];

        result[0] = tileWidth * t.getCoords()[0];
            result[1] = (tileHeight * (t.getCoords()[1])) / 4*3;
        if (t.getCoords()[1] % 2 == 0) {
            result[0] += tileWidth/2;
        }
        return result;
    }
}
