package entity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class ImageStore {
    private Map<Class<?>, List<BufferedImage>> gallery;
    private Map<Class<?>, List<BufferedImage>> galleryShaded;
    private Map<Class<?>, List<BufferedImage>> galleryTeam1;
    private Map<Class<?>, List<BufferedImage>> galleryTeam2;
    private Map<String, BufferedImage> tileGallery;
    private Map<String, BufferedImage> tileOutOfRangeGallery;
    public ImageStore() {
        gallery = new HashMap<>();
        galleryShaded = new HashMap<>();
        galleryTeam1 = new HashMap<>();
        galleryTeam2 = new HashMap<>();
        tileGallery = new HashMap<>();
        tileOutOfRangeGallery = new HashMap<>();
        //AAB
        List<BufferedImage> li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(getClass().getResourceAsStream("/units/AAB_"+i+".png")));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_AA_B.class, li);

        //AAS
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(getClass().getResourceAsStream("/units/AAS_"+i+".png")));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_AA_S.class, li);

        //amphibian
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(getClass().getResourceAsStream("/units/amphibian_"+i+".png")));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_amphibian.class, li);

        //artyH
        li = new ArrayList<>();
        try {
            for (int i = 0; i < 6; ++i){
                li.add(ImageIO.read(getClass().getResourceAsStream("/units/artyH_"+i+"r.png")));
                li.add(ImageIO.read(getClass().getResourceAsStream("/units/artyH_"+i+".png")));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_arty_H.class, li);

        //artyL
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(getClass().getResourceAsStream("/units/artyL_"+i+".png")));
            }} catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_arty_L.class, li);

        //boat
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(getClass().getResourceAsStream("/units/boat_"+i+".png")));
            }} catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_boat.class, li);

        //bomber
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(getClass().getResourceAsStream("/units/bomber_"+i+".png")));
            }} catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_bomber.class, li);

        //fighter
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(getClass().getResourceAsStream("/units/fighter_"+i+".png")));
            }} catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_fighter.class, li);

        //heliC
        li = new ArrayList<>();
        try {
            for (int i = 0; i < 6; ++i){
                for (int j = 0; j < 3; ++j){
                    li.add(ImageIO.read(getClass().getResourceAsStream("/units/heliC_"+i+""+(j+1)+".png")));
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_heli_C.class, li);

        //heliT
        li = new ArrayList<>();
        try {
            for (int i = 0; i < 6; ++i){
                for (int j = 0; j < 3; ++j){
                    li.add(ImageIO.read(getClass().getResourceAsStream("/units/heliT_"+i+""+(j+1)+".png")));
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_heli_T.class, li);

        //infantryH
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(getClass().getResourceAsStream("/units/infantryH_"+i+".png")));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_infantry_H.class, li);

        //infantryL
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(getClass().getResourceAsStream("/units/infantryL_"+i+".png")));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_infantry_L.class, li);

        //jeep
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(getClass().getResourceAsStream("/units/jeep_"+i+".png")));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_jeep.class, li);

        //repair
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(getClass().getResourceAsStream("/units/repair_"+i+".png")));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_repair.class, li);

        //sub
        li = new ArrayList<>();
        try {
            for (int i = 0; i < 6; ++i){
                li.add(ImageIO.read(getClass().getResourceAsStream("/units/subA_"+i+".png")));
                li.add(ImageIO.read(getClass().getResourceAsStream("/units/subU_"+i+".png")));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_sub.class, li);

        //tankH
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(getClass().getResourceAsStream("/units/tankH_"+i+".png")));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_tank_H.class, li);

        //tankL
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(getClass().getResourceAsStream("/units/tankL_"+i+".png")));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_tank_L.class, li);

        //transportP
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(getClass().getResourceAsStream("/units/transportP_"+i+".png")));
            }} catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_transport_P.class, li);

        for (Map.Entry<Class<?>, List<BufferedImage>> entry : gallery.entrySet()) {
            Class<?> key = entry.getKey();
            List<BufferedImage> value = entry.getValue();
            List<BufferedImage> newValue = changeColor(value,-100,-100,-100, true);
            galleryShaded.put(key,newValue);
        }

        for (Map.Entry<Class<?>, List<BufferedImage>> entry : gallery.entrySet()) {
            Class<?> key = entry.getKey();
            List<BufferedImage> value = entry.getValue();
            List<BufferedImage> newValue = changeColor(value,-50,-50,200,false);
            galleryTeam1.put(key,newValue);
        }
        for (Map.Entry<Class<?>, List<BufferedImage>> entry : gallery.entrySet()) {
            Class<?> key = entry.getKey();
            List<BufferedImage> value = entry.getValue();
            List<BufferedImage> newValue = changeColor(value,+250,-100,+200, false);
            galleryTeam2.put(key,newValue);
        }

        BufferedImage img;
        //Grass
        img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/tiles/grass1.png"));
            img = ImageIO.read(getClass().getResourceAsStream("/tiles/grass2.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        tileGallery.put("grass", img);
        img = null;
        //Concrete
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/tiles/concrete1.png"));
            img = ImageIO.read(getClass().getResourceAsStream("/tiles/concrete2.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        tileGallery.put("concrete", img);
        //Water
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/tiles/water1v2.png"));
            img = ImageIO.read(getClass().getResourceAsStream("/tiles/water2v2.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        tileGallery.put("water", img);
        //Structure
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/tiles/structure.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        tileGallery.put("structure", img);
        //Structure door
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/tiles/structure_door.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        tileGallery.put("structure_door", img);

        for (Map.Entry<String, BufferedImage> entry : tileGallery.entrySet()) {
            String key = entry.getKey();
            List<BufferedImage> value = new ArrayList<>();
            value.add(entry.getValue());
            BufferedImage newValue = changeColor(value,-100,-100,-100,true).get(0);
            tileOutOfRangeGallery.put(key,newValue);
        }
    }
    public List<BufferedImage> changeColor(List<BufferedImage> original, int r, int g, int b, boolean isSelection){
        List<BufferedImage> result = new ArrayList<>();
        //ImageIO.read(getClass().getResource("/tankH_4.png")); // Load your PNG image here
        for (BufferedImage originalImage : original) {
            // Create a copy of the original image
            BufferedImage modifiedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

            // Apply a color change to non-transparent pixels
            for (int x = 0; x < originalImage.getWidth(); x++) {
                for (int y = 0; y < originalImage.getHeight(); y++) {
                    int rgb = originalImage.getRGB(x, y);
                    int alpha = (rgb >> 24) & 0xFF;
                    boolean isGray = false;

                    if (alpha > 0) { // Check if the pixel is not transparent
                        int red = (rgb >> 16) & 0xFF;
                        int green = (rgb >> 8) & 0xFF;
                        int blue = rgb & 0xFF;

                        // Define thresholds for black and white in terms of grayscale values
                        if (Math.abs(red - blue) < 20 && Math.abs(blue - green) < 20 && Math.abs(green - red) < 20 && !isSelection) {
                            isGray = true;
                        }

                        if (isGray) {
                            // For pixels that are the shades of gray, maintain them
                            modifiedImage.setRGB(x, y, rgb);
                        } else {
                            // Modify the color components
                            if (r < 0){
                                red = Math.max(0, red + r);
                            }
                            else {
                                red = Math.min(255, red + r);
                            }
                            if (g < 0){
                                green = Math.max(0, green + g);
                            }
                            else {
                                green = Math.min(255, green + g);
                            }
                            if (b < 0){
                                blue = Math.max(0, blue + b);
                            }
                            else {
                                blue = Math.min(255, blue + b);
                            }

                            // Combine modified color components with original alpha
                            int modifiedRGB = (alpha << 24) | (red << 16) | (green << 8) | blue;
                            modifiedImage.setRGB(x, y, modifiedRGB);
                        }
                    } else {
                        // Preserve transparency for transparent pixels
                        modifiedImage.setRGB(x, y, rgb);
                    }
                }
            }
            result.add(modifiedImage);
        }

        return result;
    }

    public Map<Class<?>, List<BufferedImage>> getGallery() {
        return gallery;
    }

    public void setGallery(Map<Class<?>, List<BufferedImage>> gallery) {
        this.gallery = gallery;
    }

    public Map<Class<?>, List<BufferedImage>> getGalleryShaded() {
        return galleryShaded;
    }

    public void setGalleryShaded(Map<Class<?>, List<BufferedImage>> galleryShaded) {
        this.galleryShaded = galleryShaded;
    }

    public Map<Class<?>, List<BufferedImage>> getGalleryTeam1() {
        return galleryTeam1;
    }

    public void setGalleryTeam1(Map<Class<?>, List<BufferedImage>> galleryTeam1) {
        this.galleryTeam1 = galleryTeam1;
    }

    public Map<Class<?>, List<BufferedImage>> getGalleryTeam2() {
        return galleryTeam2;
    }

    public void setGalleryTeam2(Map<Class<?>, List<BufferedImage>> galleryTeam2) {
        this.galleryTeam2 = galleryTeam2;
    }

    public Map<String, BufferedImage> getTileGallery() {
        return tileGallery;
    }

    public void setTileGallery(Map<String, BufferedImage> tileGallery) {
        this.tileGallery = tileGallery;
    }

    public Map<String, BufferedImage> getTileOutOfRangeGallery() {
        return tileOutOfRangeGallery;
    }

    public void setTileOutOfRangeGallery(Map<String, BufferedImage> tileOutOfRangeGallery) {
        this.tileOutOfRangeGallery = tileOutOfRangeGallery;
    }
}
