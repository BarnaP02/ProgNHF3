package main;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FontGallery {
    private HashMap<Integer, ArrayList<Font>> fontMap = new HashMap<>();
    public FontGallery(){
        int[] sizes = new int[]{12,16,20,30,40,50,60};
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
/*
        fonts = new Font[20];
        fonts[0]=loadFont("res/fonts/Megrim-Regular.ttf",12);
        fonts[1]=loadFont("res/fonts/Megrim-Regular.ttf",20);
        fonts[2]=loadFont("res/fonts/Megrim-Regular.ttf",30);
        fonts[3]=loadFont("res/fonts/DotGothic16-Regular.ttf",12);
        fonts[4]=loadFont("res/fonts/DotGothic16-Regular.ttf",20);
        fonts[5]=loadFont("res/fonts/DotGothic16-Regular.ttf",30);
        fonts[6]=loadFont("res/fonts/PressStart2P-Regular.ttf",12);
        fonts[7]=loadFont("res/fonts/PressStart2P-Regular.ttf",20);
        fonts[8]=loadFont("res/fonts/PressStart2P-Regular.ttf",30);
        fonts[9]=loadFont("res/fonts/Orbitron-VariableFont_wght.ttf",12);
        fonts[10]=loadFont("res/fonts/Orbitron-VariableFont_wght.ttf",20);
        fonts[11]=loadFont("res/fonts/Orbitron-VariableFont_wght.ttf",30);
        */

        /*try {
            // Load the custom pixelated font from the extracted folder
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("Megrim-Regular.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);

            fonts[0] = pixelFont;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // If there's an error loading the font, use the default font
            fonts[0] = new Font("Times New Roman", Font.BOLD, 20);
        }
        try {
            // Load the custom pixelated font from the extracted folder
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("DotGothic16-Regular.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);

            fonts[1] = pixelFont;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // If there's an error loading the font, use the default font
            fonts[1] = new Font("Times New Roman", Font.BOLD, 20);
        }
        try {
            // Load the custom pixelated font from the extracted folder
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("PressStart2P-Regular.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);

            fonts[2] = pixelFont;
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // If there's an error loading the font, use the default font
            fonts[2] = new Font("Times New Roman", Font.BOLD, 20);
        }*/
    }
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
