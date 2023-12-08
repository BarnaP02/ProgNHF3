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
import java.io.*;
import java.util.ArrayList;

public class GamePanel  extends JPanel implements Runnable, Serializable {
    //SAVE
    private static final long serialVersionUID = 1123581321;

    // SCREEN SETTINGS
    final int originalTileHeight = 40;
    final int originalTileWidth = 42;
    public final int tileHeight = 40;
    public final int tileWidth = 42;
    public final int maxScreenCol = 21;
    public final int maxScreenRow = 19;
    public final int screenWidth = tileWidth * maxScreenCol + tileWidth/2;
    public final int screenHeight = (tileHeight * (maxScreenRow - 1)) / 4*3 + tileHeight;

    public transient CycleInfoPanel cycleInfoPanel;
    public transient InfoPanel infoPanel;
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
    public transient ImageStore imagS = new ImageStore(this);
    public ArrayList<SuperUnit> ally;
    public ArrayList<SuperUnit> enemy;
    public ArrayList<SuperStructure> structures;
    public transient AssetSetter aSetter = new AssetSetter(this);
    protected transient TileManager tileM;
    protected transient Sound music = new Sound();
    protected transient Sound sfx = new Sound();
    protected transient Sound pauseMenuMusic = new Sound();
    public final int worldWidth = tileWidth * maxWorldCol + tileWidth/2;
    public final int worldHeight = (tileHeight * (maxWorldRow - 1)) / 4*3 + tileHeight;
    public transient KeyHandler keyH;
    transient Thread gameThread;
    public Cruser cruser;
    public transient RangeFinder rFinder;
    public OrderManager timeL;
    protected ArrayList<SuperUnit> recentlyDamaged;

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

    /***
     * constructor for GamePanel
     * @param panM its methods are used in the info panels
     */
    public GamePanel(PanelManager panM){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        ally = new ArrayList<>();
        enemy = new ArrayList<>();
        structures = new ArrayList<>();
        tileM = new TileManager(this);
        tileM.loadMap("/maps/map03.txt");
        keyH = new KeyHandler(this);
        cruser = new Cruser(this, keyH);
        this.addKeyListener(keyH);
        rFinder = new RangeFinder(this, Grid);
        timeL = new OrderManager(this);
        recentlyDamaged = new ArrayList<>();
        cycleInfoPanel = new CycleInfoPanel(screenWidth, panM);
        cycleInfoPanel.update(1, team1Name, "MOVE");
        infoPanel = new InfoPanel(this, panM, screenHeight + 40);
        gameState = playState;
    }

    /***
     * this is called before starting the game
     * this starts the background music and sets every tile to not be dark
     */
    public void setupGame(){
        this.setFocusable(true);
        requestFocus();
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
    }

    /***
     * after loading a save, this needs to be called to initialise everything that couldn't be serialized like Sounds or BufferedImages
     * @param panM needed for the info panels
     */
    public void reloadGame(PanelManager panM){
        imagS = new ImageStore(this);
        cycleInfoPanel = new CycleInfoPanel(screenWidth, panM);
        infoPanel = new InfoPanel(this, panM, screenHeight + 40);
        aSetter = new AssetSetter(this);
        tileM = new TileManager(this);
        tileM.loadMapFromSave();
        music = new Sound();
        sfx = new Sound();
        pauseMenuMusic = new Sound();
        keyH = new KeyHandler(this);
        cruser = new Cruser(this, keyH);
        addKeyListener(keyH);
        rFinder = new RangeFinder(this, Grid);
        gameState = playState;
        for (SuperUnit su : ally){
            su.reloadSounds();
        }
        for (SuperUnit su : enemy){
            su.reloadSounds();
        }
        for (SuperStructure supstr : structures){
            for (SuperUnit su : supstr.getInventory()){
                su.reloadSounds();
            }
        }

        for (int i = 0; i < maxWorldRow; ++i){
            for (int j = 0; j < maxWorldCol; ++j){
                Grid[j][i].setIsHighlighted(true);
            }
        }

        update();
        repaint();
    }

    /***
     * starts the Thread what the game runs on
     */
    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    /***
     * needed for implementing Runnable
     * also this is what runs the game
     */
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        if (gameThread == null){
            System.out.println("UJUJ");
        }

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
                    drawCount = 0;
                    timer = 0;
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
                    SwingUtilities.invokeLater(this::repaint); // Trigger repaint on the EDT
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

    /***
     * updates the old values usually based on keyboard input
     */
    public void update() {
        if (gameState == playState){
            cruser.update();
            timeL.update(this, keyH);
            SuperUnit unit = getUnitFromTile(cruser.getHover());
            if (unit != null && unit.isVisible() && !unit.isDestroyed()){
                infoPanel.update(this, unit);
            }
            else {
                infoPanel.update(this, null);
            }
        }
        if (gameState == pauseState){
            //nothing yet
        }
        if (attacksNeedResolving){
            gameState = resolvingAttacksState;
            resolveAttackOrders();
        }
        if (keyH.ctrlPressed && keyH.sPressed){
            saveGame();
        }
    }

    /***
     * calls draw for the components
     * @param g the <code>Graphics</code> object to protect
     */
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

    /***
     * in debug mode (toggled by pressing t) shows the hp and xp for recently damaged units
     * @param unit unit that is put on the recently damaged list
     */
    public void recievedDamage(SuperUnit unit){
        recentlyDamaged.add(unit);
    }

    /***
     * takes the list of unresolved attacks and resolves the first one it finds
     * if no more attacks need resolving, it lets the game continue
     */
    public void resolveAttackOrders(){
        //attacksNeedResolving
        boolean needTemp = false;
        for (int i = 0; i < timeL.getTempAttack().size(); ++i){
            if (!timeL.getTempAttack().get(i).isCompleted()){
                needTemp = true;
                timeL.getTempAttack().get(i).complete(this);
                break;
            }
        }
        attacksNeedResolving = needTemp;

    }

    /***
     * gets world X and Y coordinates and if a tile has those coordinates, returns that tile
     * @param searchedWorldX the worldX coordinate of the searched tile
     * @param searchedWorldY the worldX coordinate of the searched tile
     * @return the searched tile if it exists, null otherwise
     */
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

    /***
     * the reverse of getTileFromCoords
     * @param t the tile that we want to find the coordinates for
     * @return the coordinates of that tile in the form of an int array
     */
    public int[] getCoordsFromTile(Tile t){
        int[] result = new int[2];

        result[0] = tileWidth * t.getCoords()[0];
            result[1] = (tileHeight * (t.getCoords()[1])) / 4*3;
        if (t.getCoords()[1] % 2 == 0) {
            result[0] += tileWidth/2;
        }
        return result;
    }

    /***
     * units store their tiles, but the tiles don't know if a unit stands on them or not
     * this returns the unit standing on t
     * @param t the tile of waht we want to find out if it has a unit
     * @return the unit standing on it, otherwise null
     */
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

    /***
     * returns the structure that is on tile t
     * @param t the tile that we want to find a structure on
     * @return the structure on that tile or null
     */
    public SuperStructure getStructureFromTile(Tile t){
        for (SuperStructure ss : structures){
            if (ss.getTile() == t){
                return ss;
            }
        }
        return null;
    }

    /***
     * plays the music with the index i
     * @param i the index of the sound we want to play
     */
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    /***
     * plays the pause menu music (it is great) with the index i
     * @param i the index of sound we want to play
     */
    public void playPauseMenuMusic(int i){
        pauseMenuMusic.setFile(i);
        pauseMenuMusic.play();
        pauseMenuMusic.loop();
    }

    /***
     * stops the music
     */
    public void stopMusic(){
        music.stop();
    }

    /***
     * plays the sound effect with the index i
     * @param i the index of the sound effect
     */
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

    /***
     * sets everything up for the next cycle
     * also refreshes the values in the cycle info panel
     */
    public void nextCycleState(){
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
    }

    /***
     * this saves the game to "res/last_save.ser" and outputs if it was successful or not
     */
    public void saveGame(){

        // File path to save serialized data
        String filePath = "res/last_save.ser";

        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            // Serialize and write the GamePanel object to the file
            objectOut.writeObject(this);
            System.out.println("GamePanel object has been serialized and saved.");

        } catch (IOException e) {
            System.out.println("An error occurred while saving the object: " + e.getMessage());
        }

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
