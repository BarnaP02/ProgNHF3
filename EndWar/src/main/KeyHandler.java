package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, enterPressed, ctrlPressed, sPressed;
    //DEBUG
    boolean checkDrawTime;
    private boolean u = false,n = false,d = false,ee = false;
    private boolean t = false,a = false,l = false;
    public boolean showTileCoords;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            if (gp.gameState == GamePanel.playState){
                gp.infoPanel.setSlotsFocusable(false);
                gp.gameState = GamePanel.pauseState;
            }
            else if (gp.gameState == GamePanel.pauseState){
                gp.infoPanel.setSlotsFocusable(true);
                gp.gameState = GamePanel.playState;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_CONTROL) {
            ctrlPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            sPressed = true;
        }

        if (code == KeyEvent.VK_UP) {
            gp.music.volumeUp();
        }
        if (code == KeyEvent.VK_DOWN) {
            gp.music.volumeDown();
        }
        if (code == KeyEvent.VK_M) {
            gp.music.volumeMute();
        }

        if (code == KeyEvent.VK_U){
            u = true;
        }
        if (code == KeyEvent.VK_N){
            if (u){
                n = true;
                u = false;
            }
        }
        if (code == KeyEvent.VK_D){
            if (n){
                d = true;
                n = false;
            }
        }
        if (code == KeyEvent.VK_E){
            if (d){
                ee = true;
                d = false;
            }
            if (l){
                gp.music.stop();
                gp.music.setFile(1);
                gp.music.loop();
                l = false;
            }
        }
        if (code == KeyEvent.VK_R){
            if (ee){
                gp.music.stop();
                gp.music.setFile(17);
                gp.music.loop();
                ee = false;
            }
        }
        /*if (code == KeyEvent.VK_C){
            cPressed = true;
        }*/
        if (code == KeyEvent.VK_T){
            t = true;
        }
        if (code == KeyEvent.VK_A){
            if (t){
                a = true;
                t = false;
            }
        }
        if (code == KeyEvent.VK_L){
            if (a){
                l = true;
                a = false;
            }
        }
        if (code == KeyEvent.VK_U && code == KeyEvent.VK_N && code == KeyEvent.VK_D && code == KeyEvent.VK_E && code == KeyEvent.VK_R) {
        }

        //DEBUG
        if (code == KeyEvent.VK_T) {
            if (checkDrawTime){
                checkDrawTime = false;
            }
            else {
                checkDrawTime = true;
            }
        }
        //TILE COORDS
        if (code == KeyEvent.VK_C) {
            if (showTileCoords){
                showTileCoords = false;
            }
            else {
                showTileCoords = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if (code == KeyEvent.VK_CONTROL) {
            ctrlPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            sPressed = false;
        }
    }
}
