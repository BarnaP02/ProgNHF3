package main;

import entity.*;
import menu.PanelManager;
import tile.RangeFinder;
import tile.Tile;
import tile.TileManager;
import timeline.Order;
import timeline.OrderManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel  extends JPanel implements Runnable{
    // SCREEN SETTINGS
    final int originalTileHeight = 40;
    final int originalTileWidth = 42;
    //final int scale = 2;
    public final int tileHeight = 40;//originalTileHeight * scale;
    public final int tileWidth = 42;//originalTileWidth * scale;
    public final int maxScreenCol = 21;
    public final int maxScreenRow = 19;
    public final int screenWidth = tileWidth * maxScreenCol + tileWidth/2;
    public final int screenHeight = (tileHeight * (maxScreenRow - 1)) / 4*3 + tileHeight;

    public CycleInfoPanel cycleInfoPanel;
    public InfoPanel infoPanel;
    private int cycleNumber = 1;
    private String team1Name = "Ferenciek";
    private String team2Name = "Apátok";


    //FPS
    int FPS = 60;

    public Tile[][] Grid;
    public static final int[][] neighborOffsetEvenOld = {                              //Offset in even rows
            {0, -1}, {-1, 0}, {0, 1}, {1, 1}, {1, 0}, {1, -1}
    };
    protected static final int[][] neighborOffsetEven = {                              //Offset in even rows
            {0, -1}, {-1, 0}, {0, 1}, {1, 1}, {1, 0}, {1, -1}
    };
    protected static final int[][] neighborOffsetOdd = {                              //Offset in odd rows
            {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 0}, {0, -1}
    };
    public final int[][] twoTileShift = {
            {0, -tileHeight*3/4}, {0, -tileHeight*3/4}, {0, -tileHeight*3/4},
            {-tileWidth, -tileHeight*3/4}, {-tileWidth, -tileHeight*3/4}, {-tileWidth, -tileHeight*3/4}
    };

    //WORLD SETTINGS
    public int maxWorldCol;
    public int maxWorldRow;
    //jo kis feheres
    //public int[] team1Color = new int[]{-50,-50,200};
    public int[] team2Color = new int[]{+250,-100,+200};
    //ez szep kek
    public int[] team1Color = new int[]{-250,-100,+200};

    //for easy access for info panel
    //public HashMap<SuperUnit, Tile> easyAccessUnitLocation = new HashMap<>();
    //public HashMap<SuperUnit, Tile> easyAccessUnitLocationOther = new HashMap<>();

    public ImageStore imagS = new ImageStore(this);
    public ArrayList<SuperUnit> ally = new ArrayList<>();
    public ArrayList<SuperUnit> enemy = new ArrayList<>();
    public ArrayList<SuperStructure> structures = new ArrayList<>();
    public AssetSetter aSetter = new AssetSetter(this);
    TileManager tileM = new TileManager(this);
    Sound music = new Sound();
    Sound sfx = new Sound();
    Sound pauseMenuMusic = new Sound();
    public final int worldWidth = tileWidth * maxWorldCol + tileWidth/2;
    public final int worldHeight = (tileHeight * (maxWorldRow - 1)) / 4*3 + tileHeight;
    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public Cruser cruser = new Cruser(this, keyH);
    public RangeFinder rFinder = new RangeFinder(this, Grid);
    public OrderManager timeL = new OrderManager(this);
    protected ArrayList<SuperUnit> recentlyDamaged = new ArrayList<>();
    //public SuperUnit testUnit;

    //GAME STATE
    public enum Cycle {
        T1MOVE,
        T2ATTACK,
        T2MOVE,
        T1ATTACK
    }
    protected Cycle cycleState = Cycle.T1MOVE;
    public boolean attacksNeedResolving = false;
    public int gameState;
    public static final int playState = 1;
    public static final int pauseState = 2;
    public static final int resolvingAttacksState = 3;


    //GAME SETTINGS
    public boolean isPvE = false;


    public GamePanel(PanelManager panM){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        cycleInfoPanel = new CycleInfoPanel(screenWidth, panM);
        cycleInfoPanel.update(1, team1Name, "MOVE");
        infoPanel = new InfoPanel(this, panM, screenHeight + cycleInfoPanel.getHeight());
        gameState = playState;
    }

    public void setupGame(){
        playMusic(1);
        playPauseMenuMusic(21);
        pauseMenuMusic.freeze();
        for (int i = 0; i < maxWorldRow; ++i){
            for (int j = 0; j < maxWorldCol; ++j){
                Grid[j][i].setIsHighlighted(false);
            }
        }

        update();
        repaint();
        for (int i = 0; i < maxWorldRow; ++i){
            for (int j = 0; j < maxWorldCol; ++j){
                Grid[j][i].setIsHighlighted(true);
            }
        }
        update();
        repaint();
        //rFinder.findMovementRange(this, ally.get(0));
        update();
        repaint();
        //SuperUnit superUnit = new fixer(this);
        //rFinder.findMovementRange(this,superUnit);
        update();
        repaint();
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

            if (gameState == playState){
                music.resume();
                pauseMenuMusic.freeze();

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
                    System.out.println("FPS:" + drawCount + " " + gameState);
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
                        //System.out.print(cruser.getHover().borders()[i]+" ");
                    }
                    for (Order o : timeL.getTimeline()){
                        //System.out.print(", "+o.getSide().get(o.getIndex()));
                    }
                    System.out.println();
                }
            }
            else if (gameState == pauseState){
                music.freeze();
                pauseMenuMusic.resume();
            }
            else if (gameState == resolvingAttacksState){

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
                if (!attacksNeedResolving){
                    for (Order o : timeL.getTempMove()){
                        o.forceFinish(this);
                    }
                    timeL.setTempMove(new ArrayList<>());
                    gameState = playState;
                }
            }

        }
    }
    public void update() {
        if (gameState == playState){
            cruser.update();
            timeL.update(this, keyH);
            infoPanel.update(this, getUnitFromTile(cruser.getHover()));
        }
        if (gameState == pauseState){
            //nothing yet
        }
        if (attacksNeedResolving){
            gameState = resolvingAttacksState;
            resolveAttackOrders();
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //DEBUG
        long drawStart = 0;
        if (keyH.checkDrawTime){
            drawStart = System.nanoTime();
        }

        tileM.draw(g2);
        for (SuperStructure struct : structures){
            if (struct != null) {
                struct.draw(g2, this);
            }
        }
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
            g2.setColor(Color.WHITE);
            g2.drawString("Draw Time: " + passed, 10, 400);
            g2.drawString("Cycle State: " + cycleState, 10, 360);
            g2.drawString("Ally size: " + ally.size(), 10, 440);
            g2.drawString("Enemy size: " + enemy.size(), 10, 480);
            for (SuperUnit u : recentlyDamaged){
                g2.setColor(Color.WHITE);
                g2.drawString("" + u.getHp(), u.getWorldX(), u.getWorldY());
                g2.setColor(Color.MAGENTA);
                g2.drawString("" + u.getXp(), u.getWorldX()+tileWidth/4*3, u.getWorldY());
            }
            //System.out.println("Draw Time: " + passed);
        }
        if (keyH.showTileCoords){
            for (Tile[] tiles : Grid) {
                for (Tile tile : tiles) {
                    g2.setColor(Color.WHITE);
                    g2.drawString((tile.getCoords()[0]+1) + ":" + (tile.getCoords()[1]+1), tile.worldX, tile.worldY+tileHeight/2);

                }
            }
        }

        g2.dispose();
    }
    public void recievedDamage(SuperUnit unit){
        recentlyDamaged.add(unit);
    }
    public void resolveAttackOrders(){
        //attacksNeedResolving
        boolean needTemp = false;
        for (int i = 0; i < timeL.getTempAttack().size(); ++i){
            if (!timeL.getTempAttack().get(i).isCompleted()){
                needTemp = true;
                timeL.getTempAttack().get(i).complete(this);
                break;
            }
            //System.out.println("UUUU");
        }
        //while (!timeL.getTempAttack().get(i).isCompleted()){
        //}
        attacksNeedResolving = needTemp;
        //phaseTransition();

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
    public SuperUnit getUnitFromTile(Tile t){
        for (SuperUnit su : ally){
            if (su.getCurrentTile() == t || su.getOtherCurrentTile() == t){
                return su;
            }
        }
        for (SuperUnit su : enemy){
            if (su.getCurrentTile() == t || su.getOtherCurrentTile() == t){
                return su;
            }
        }
        for (SuperStructure supstr : structures){
            for (SuperUnit suni : supstr.getInventory()){
                if (suni.getCurrentTile() == t){
                    return suni;
                }
            }
        }
        return null;
    }
    public SuperStructure getStructureFromTile(Tile t){
        for (SuperStructure ss : structures){
            if (ss.getTile() == t){
                return ss;
            }
        }
        return null;
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void playPauseMenuMusic(int i){
        pauseMenuMusic.setFile(i);
        pauseMenuMusic.play();
        pauseMenuMusic.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSFX(int i){
        sfx.setFile(i);
        sfx.play();
    }

    public static int[][] getNeighborOffsetEven() {
        return neighborOffsetEven;
    }

    public static int[][] getNeighborOffsetOdd() {
        return neighborOffsetOdd;
    }

    public Cycle getCycleState() {
        return cycleState;
    }
    public void nextCycleState(){
        boolean isSet = false;
        boolean isAttack = false;
        boolean isTeam1 = false;
        if (cycleState == Cycle.T1MOVE){
            cycleState = Cycle.T2ATTACK;
            playSFX(19);
            cycleInfoPanel.update(cycleNumber, team2Name, "ATTACK");
            recentlyDamaged = new ArrayList<>();
            return;
        }
        if (cycleState == Cycle.T2ATTACK){
            cycleState = Cycle.T2MOVE;
            playSFX(20);
            cycleInfoPanel.update(cycleNumber, team2Name, "MOVE");
            return;
        }
        if (cycleState == Cycle.T2MOVE){
            cycleState = Cycle.T1ATTACK;
            playSFX(19);
            cycleInfoPanel.update(cycleNumber, team1Name, "ATTACK");
            recentlyDamaged = new ArrayList<>();
            return;
        }
        if (cycleState == Cycle.T1ATTACK){
            cycleState = Cycle.T1MOVE;
            playSFX(20);
            cycleInfoPanel.update(cycleNumber, team1Name, "MOVE");
            cycleNumber++;
        }
        /*cycleNumberField.setText("" + gp.getCycleNumber());
        if (gp.cycleState == GamePanel.Cycle.T1MOVE || gp.cycleState == GamePanel.Cycle.T1ATTACK){
            currentTeamField.setText(gp.getTeam1Name());
        }
        else {
            currentTeamField.setText(gp.getTeam2Name());
        }
        if (gp.cycleState == GamePanel.Cycle.T1MOVE || gp.cycleState == GamePanel.Cycle.T2MOVE){
            currentPhaseField.setText("MOVE");
        }
        else {
            currentPhaseField.setText("ATTACK");
        }
        cycleInfoPanel.update(cycleNumber, );*/
    }

    public int getCycleNumber() {
        return cycleNumber;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }
}
