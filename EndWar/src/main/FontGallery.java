package main;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FontGallery {
    private HashMap<Integer, ArrayList<Font>> fontMap = new HashMap<>();

    /***
     * this stores the fonts that have or would have been used in this project
     */
    public FontGallery(){
        int[] sizes = new int[]{12,16,20,30,40,50,60, 180};
        ArrayList<String> fontPaths = new ArrayList<>();
        fontPaths.add("res/fonts/Megrim-Regular.ttf");
        fontPaths.add("res/fonts/DotGothic16-Regular.ttf");
        fontPaths.add("res/fonts/PressStart2P-Regular.ttf");
        fontPaths.add("res/fonts/Orbitron-VariableFont_wght.ttf");
        int counter = 0;
        for (String path : fontPaths){
            ArrayList<Font> newFontList = new ArrayList<>();
            for (int i : sizes){
                newFontList.add(loadFont(path, i));
            }
            fontMap.put(counter, newFontList);
            counter++;
        }
    }

    /***
     * loads a font for fontFilePath with a size of size
     * @param fontFilePath the path to the font file
     * @param size the size of the expected font
     * @return the font with the correct size
     */
    private static Font loadFont(String fontFilePath, float size) {
        try {
            // Create a Font object from the TrueType Font file
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File(fontFilePath)).deriveFont(size);
            ge.registerFont(customFont);
            return customFont;
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception: Font file not found or unable to load font
            return null;
        }
    }

    public HashMap<Integer, ArrayList<Font>> getFontMap() {
        return fontMap;
    }

    public void setFontMap(HashMap<Integer, ArrayList<Font>> fontMap) {
        this.fontMap = fontMap;
    }

}
