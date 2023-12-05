package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class ImageStore {
    private GamePanel gp;
    private Map<Class<?>, List<BufferedImage>> gallery;
    private Map<Class<?>, List<BufferedImage>> galleryTeam1;
    private Map<Class<?>, List<BufferedImage>> galleryTeam2;
    private Map<Class<?>, List<BufferedImage>> galleryTeam1Shaded;
    private Map<Class<?>, List<BufferedImage>> galleryTeam2Shaded;
    private Map<String, List<BufferedImage>> tileGallery;
    private Map<String, List<BufferedImage>> tileOutOfRangeGallery;
    private Map<String, List<BufferedImage>> obstacleGallery;
    private Map<String, BufferedImage> waterGallery;
    private Map<String, BufferedImage> waterGalleryShaded;
    private Map<String, BufferedImage> roadGallery;
    private Map<String, BufferedImage> structureGallery;
    private Map<String, BufferedImage> structureGalleryNatural;
    private Map<String, BufferedImage> structureGalleryNaturalShaded;
    private Map<String, BufferedImage> structureGalleryTeam1;
    private Map<String, BufferedImage> structureGalleryTeam1Shaded;
    private Map<String, BufferedImage> structureGalleryTeam2;
    private Map<String, BufferedImage> structureGalleryTeam2Shaded;
    private Map<Class<?>, ImageIcon> galleryTeam1Icon;
    private Map<Class<?>, ImageIcon> galleryTeam2Icon;
    private Map<Class<?>, ImageIcon> galleryTeam1IconShaded;
    private Map<Class<?>, ImageIcon> galleryTeam2IconShaded;
    public ImageStore(GamePanel gp) {
        gallery = new HashMap<>();
        galleryTeam1Shaded = new HashMap<>();
        galleryTeam2Shaded = new HashMap<>();
        galleryTeam1 = new HashMap<>();
        galleryTeam2 = new HashMap<>();
        tileGallery = new HashMap<>();
        tileOutOfRangeGallery = new HashMap<>();
        obstacleGallery = new HashMap<>();
        waterGallery = new HashMap<>();
        waterGalleryShaded = new HashMap<>();
        roadGallery = new HashMap<>();
        structureGallery = new HashMap<>();
        structureGalleryNatural = new HashMap<>();
        structureGalleryNaturalShaded = new HashMap<>();
        structureGalleryTeam1 = new HashMap<>();
        structureGalleryTeam1Shaded = new HashMap<>();
        structureGalleryTeam2 = new HashMap<>();
        structureGalleryTeam2Shaded = new HashMap<>();
        galleryTeam1Icon = new HashMap<>();
        galleryTeam2Icon = new HashMap<>();
        galleryTeam1IconShaded = new HashMap<>();
        galleryTeam2IconShaded = new HashMap<>();
        this.gp = gp;
        //AAB
        List<BufferedImage> li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/AAB_"+i+".png"))));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_AA_B.class, li);

        //AAS
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/AAS_"+i+".png"))));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_AA_S.class, li);

        //amphibian
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/amphibian_"+i+".png"))));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_amphibian.class, li);

        //artyH
        li = new ArrayList<>();
        try {
            for (int i = 0; i < 6; ++i){
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/artyH_"+i+"r.png"))));
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/artyH_"+i+".png"))));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_arty_H.class, li);

        //artyL
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/artyL_"+i+".png"))));
            }} catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_arty_L.class, li);

        //battleship
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/battleship_"+i+".png"))));
            }} catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_battleship.class, li);

        //boat
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/boat_"+i+".png"))));
            }} catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_boat.class, li);

        //bomber
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/bomber_"+i+".png"))));
            }} catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_bomber.class, li);

        //cargo
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/cargo_"+i+".png"))));
            }} catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_cargo.class, li);

        //carrier
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/carrier_"+i+".png"))));
            }} catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_carrier.class, li);

        //fighter
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/fighter_"+i+".png"))));
            }} catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_fighter.class, li);

        //heliC
        li = new ArrayList<>();
        try {
            for (int i = 0; i < 6; ++i){
                for (int j = 0; j < 3; ++j){
                    li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/heliC_"+i+""+(j+1)+".png"))));
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
                    li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/heliT_"+i+""+(j+1)+".png"))));
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
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/infantryH_"+i+".png"))));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_infantry_H.class, li);

        //infantryL
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/infantryL_"+i+".png"))));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_infantry_L.class, li);

        //jeep
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/jeep_"+i+".png"))));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_jeep.class, li);

        //repair
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/repair_"+i+".png"))));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_repair.class, li);

        //sub
        li = new ArrayList<>();
        try {
            for (int i = 0; i < 6; ++i){
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/subA_"+i+".png"))));
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/subU_"+i+".png"))));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_sub.class, li);

        //tankH
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/tankH_"+i+".png"))));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_tank_H.class, li);

        //tankL
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/tankL_"+i+".png"))));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_tank_L.class, li);

        //transportP
        li = new ArrayList<>();
        try {
            for (int i = 0;i < 6; ++i){
                li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/units/transportP_"+i+".png"))));
            }} catch (IOException e){
            e.printStackTrace();
        }
        gallery.put(U_transport_P.class, li);

        for (Map.Entry<Class<?>, List<BufferedImage>> entry : gallery.entrySet()) {
            Class<?> key = entry.getKey();
            List<BufferedImage> value = entry.getValue();
            List<BufferedImage> newValue = changeColor(value,gp.team1Color[0], gp.team1Color[1], gp.team1Color[2],false);
            galleryTeam1.put(key,newValue);
        }
        for (Map.Entry<Class<?>, List<BufferedImage>> entry : gallery.entrySet()) {
            Class<?> key = entry.getKey();
            List<BufferedImage> value = entry.getValue();
            List<BufferedImage> newValue = changeColor(value, gp.team2Color[0], gp.team2Color[1], gp.team2Color[2], false);
            galleryTeam2.put(key,newValue);
        }

        for (Map.Entry<Class<?>, List<BufferedImage>> entry : galleryTeam1.entrySet()) {
            Class<?> key = entry.getKey();
            List<BufferedImage> value = entry.getValue();
            List<BufferedImage> newValue = changeColor(value,-100,-100,-100, true);
            galleryTeam1Shaded.put(key,newValue);
        }
        for (Map.Entry<Class<?>, List<BufferedImage>> entry : galleryTeam2.entrySet()) {
            Class<?> key = entry.getKey();
            List<BufferedImage> value = entry.getValue();
            List<BufferedImage> newValue = changeColor(value,-100,-100,-100, true);
            galleryTeam2Shaded.put(key,newValue);
        }

        //Grass
        li = new ArrayList<>();
        //BufferedImage li.add(null;
        try {
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass1.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass2.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass3.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass4.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass5.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass6.png"))));
        } catch (IOException e){
            e.printStackTrace();
        }
        tileGallery.put("grass", li);
        //Woods
        li = new ArrayList<>();
        try {
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/woods1.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/woods2.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/woods3.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/woods4.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/woods5.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/woods6.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/woods7.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/woods8.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/woods9.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/woods10.png"))));
        } catch (IOException e){
            e.printStackTrace();
        }
        tileGallery.put("woods", li);
        //Concrete
        li = new ArrayList<>();
        try {
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/concrete1.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/concrete2.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/concrete3.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/concrete4.png"))));
        } catch (IOException e){
            e.printStackTrace();
        }
        tileGallery.put("concrete", li);
        //PlaceHolder water
        li = new ArrayList<>();
        try {
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water000000.png"))));
        } catch (IOException e){
            e.printStackTrace();
        }
        tileGallery.put("water", li);

        //Water
        BufferedImage img = null;
        try {
            //basic
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water000000.png")));
            waterGallery.put("000000", img);
            //single grass
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water100000.png")));
            waterGallery.put("100000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water010000.png")));
            waterGallery.put("010000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water001000.png")));
            waterGallery.put("001000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water000100.png")));
            waterGallery.put("000100", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water000010.png")));
            waterGallery.put("000010", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water000001.png")));
            waterGallery.put("000001", img);
            //2 grass
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water110000.png")));
            waterGallery.put("110000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water011000.png")));
            waterGallery.put("011000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water001100.png")));
            waterGallery.put("001100", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water000110.png")));
            waterGallery.put("000110", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water000011.png")));
            waterGallery.put("000011", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water100001.png")));
            waterGallery.put("100001", img);
            //3 grass
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water111000.png")));
            waterGallery.put("111000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water011100.png")));
            waterGallery.put("011100", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water001110.png")));
            waterGallery.put("001110", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water000111.png")));
            waterGallery.put("000111", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water100011.png")));
            waterGallery.put("100011", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water110001.png")));
            waterGallery.put("110001", img);
            //opposing grass
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water100100.png")));
            waterGallery.put("100100", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water010010.png")));
            waterGallery.put("010010", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water001001.png")));
            waterGallery.put("001001", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water100100.png")));
            waterGallery.put("100100", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water010010.png")));
            waterGallery.put("010010", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water001001.png")));
            waterGallery.put("001001", img);
            //2 opposing grass
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water110110.png")));
            waterGallery.put("110110", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water011011.png")));
            waterGallery.put("011011", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water101101.png")));
            waterGallery.put("101101", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water110110.png")));
            waterGallery.put("110110", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water011011.png")));
            waterGallery.put("011011", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water101101.png")));
            waterGallery.put("101101", img);

            //single concrete
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water200000.png")));
            waterGallery.put("200000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water020000.png")));
            waterGallery.put("020000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water002000.png")));
            waterGallery.put("002000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water000200.png")));
            waterGallery.put("000200", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water000020.png")));
            waterGallery.put("000020", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water000002.png")));
            waterGallery.put("000002", img);
            //2 concrete
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water220000.png")));
            waterGallery.put("220000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water022000.png")));
            waterGallery.put("022000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water002200.png")));
            waterGallery.put("002200", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water000220.png")));
            waterGallery.put("000220", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water000022.png")));
            waterGallery.put("000022", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water200002.png")));
            waterGallery.put("200002", img);
            //3 concrete
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water222000.png")));
            waterGallery.put("222000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water022200.png")));
            waterGallery.put("022200", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water002220.png")));
            waterGallery.put("002220", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water000222.png")));
            waterGallery.put("000222", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water200022.png")));
            waterGallery.put("200022", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water220002.png")));
            waterGallery.put("220002", img);
            //opposing concrete
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water200200.png")));
            waterGallery.put("200200", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water020020.png")));
            waterGallery.put("020020", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water002002.png")));
            waterGallery.put("002002", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water200200.png")));
            waterGallery.put("200200", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water020020.png")));
            waterGallery.put("020020", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water002002.png")));
            waterGallery.put("002002", img);
            //2 opposing concrete
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water220220.png")));
            waterGallery.put("220220", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water022022.png")));
            waterGallery.put("022022", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water202202.png")));
            waterGallery.put("202202", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water220220.png")));
            waterGallery.put("220220", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water022022.png")));
            waterGallery.put("022022", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water202202.png")));
            waterGallery.put("202202", img);
            //grass follows concrete
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water120000.png")));
            waterGallery.put("120000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water012000.png")));
            waterGallery.put("012000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water001200.png")));
            waterGallery.put("001200", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water000120.png")));
            waterGallery.put("000120", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water000012.png")));
            waterGallery.put("000012", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water200001.png")));
            waterGallery.put("200001", img);
            //concrete follows grass
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water210000.png")));
            waterGallery.put("210000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water021000.png")));
            waterGallery.put("021000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water002100.png")));
            waterGallery.put("002100", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water000210.png")));
            waterGallery.put("000210", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water000021.png")));
            waterGallery.put("000021", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water100002.png")));
            waterGallery.put("100002", img);
            //grass oppose concrete
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water100200.png")));
            waterGallery.put("100200", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water010020.png")));
            waterGallery.put("010020", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water001002.png")));
            waterGallery.put("001002", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water200100.png")));
            waterGallery.put("200100", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water020010.png")));
            waterGallery.put("020010", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water002001.png")));
            waterGallery.put("002001", img);
        } catch (IOException e){
            e.printStackTrace();
        }
        //Ocean
        li = new ArrayList<>();
        try {
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/ocean1.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/ocean2.png"))));
        } catch (IOException e){
            e.printStackTrace();
        }
        tileGallery.put("ocean", li);
        //Depot
        li = new ArrayList<>();
        try {
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/depot_entrance.png"))));
        } catch (IOException e){
            e.printStackTrace();
        }
        tileGallery.put("depot", li);
        //Harbor
        li = new ArrayList<>();
        try {
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/harbor_entrance.png"))));
        } catch (IOException e){
            e.printStackTrace();
        }
        tileGallery.put("harbor", li);
        //Factory
        li = new ArrayList<>();
        try {
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/factory_entrance.png"))));
        } catch (IOException e){
            e.printStackTrace();
        }
        tileGallery.put("factory", li);
        //in_Range
        li = new ArrayList<>();
        try {
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/in_range.png"))));
        } catch (IOException e){
            e.printStackTrace();
        }
        tileGallery.put("in_range", li);

        for (Map.Entry<String, List<BufferedImage>> entry : tileGallery.entrySet()) {
            String key = entry.getKey();
            //List<BufferedImage> value = new ArrayList<>();
            //value.add(entry.getValue());
            //BufferedImage newValue = changeColor(value,-100,-100,-100,true).get(0);
            //tileOutOfRangeGallery.put(key,newValue);
            //BufferedImage newValue = changeColor(value,-100,-100,-100,true).get(0);
            tileOutOfRangeGallery.put(key,changeColor(entry.getValue(),-100,-100,-100,true));
        }

        //Grass obstacles
        li = new ArrayList<>();
        try {
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/obstacles/grass_obstacle1.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/obstacles/grass_obstacle2.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/obstacles/grass_obstacle3.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/obstacles/grass_obstacle4.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/obstacles/grass_obstacle5.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/obstacles/grass_obstacle6.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/obstacles/grass_obstacle7.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/obstacles/grass_obstacle8.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/obstacles/grass_obstacle9.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/obstacles/grass_obstacle10.png"))));
        } catch (IOException e){
            e.printStackTrace();
        }
        obstacleGallery.put("grass", li);
        //Concrete obstacles
        li = new ArrayList<>();
        try {
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/obstacles/concrete_obstacle1.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/obstacles/concrete_obstacle2.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/obstacles/concrete_obstacle3.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/obstacles/concrete_obstacle4.png"))));
        } catch (IOException e){
            e.printStackTrace();
        }
        obstacleGallery.put("concrete", li);
        //Water obstacles
        li = new ArrayList<>();
        try {
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/obstacles/water_obstacle1.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/obstacles/water_obstacle2.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/obstacles/water_obstacle3.png"))));
            li.add(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/obstacles/water_obstacle4.png"))));
        } catch (IOException e){
            e.printStackTrace();
        }
        obstacleGallery.put("water", li);
        obstacleGallery.put("ocean", li);


        //Roads
        try {
            //img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_000000.png"));
            //roadGallery.put("000000", img);
            //ending
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_100000.png")));
            roadGallery.put("100000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_010000.png")));
            roadGallery.put("010000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_001000.png")));
            roadGallery.put("001000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_000100.png")));
            roadGallery.put("000100", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_000010.png")));
            roadGallery.put("000010", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_000001.png")));
            roadGallery.put("000001", img);
            //|
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_100100.png")));
            roadGallery.put("100100", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_010010.png")));
            roadGallery.put("010010", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_001001.png")));
            roadGallery.put("001001", img);
            //bridge
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_100100w.png")));
            roadGallery.put("100100w", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_010010w.png")));
            roadGallery.put("010010w", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_001001w.png")));
            roadGallery.put("001001w", img);
            //v
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_101000.png")));
            roadGallery.put("101000", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_010100.png")));
            roadGallery.put("010100", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_001010.png")));
            roadGallery.put("001010", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_000101.png")));
            roadGallery.put("000101", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_100010.png")));
            roadGallery.put("100010", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_010001.png")));
            roadGallery.put("010001", img);
            //y
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_110100.png")));
            roadGallery.put("110100", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_101100.png")));
            roadGallery.put("101100", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_100110.png")));
            roadGallery.put("100110", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_100101.png")));
            roadGallery.put("100101", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_110010.png")));
            roadGallery.put("110010", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_011010.png")));
            roadGallery.put("011010", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_010110.png")));
            roadGallery.put("010110", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_010011.png")));
            roadGallery.put("010011", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_101001.png")));
            roadGallery.put("101001", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_011001.png")));
            roadGallery.put("011001", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_001101.png")));
            roadGallery.put("001101", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_001011.png")));
            roadGallery.put("001011", img);
            //x
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_011011.png")));
            roadGallery.put("011011", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_101101.png")));
            roadGallery.put("101101", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_110110.png")));
            roadGallery.put("110110", img);
            //Ultimate crossroad
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_111111.png")));
            roadGallery.put("111111", img);
            //Semi-Ultimate crossroad
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_101010.png")));
            roadGallery.put("101010", img);
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/roads/road_010101.png")));
            roadGallery.put("010101", img);
        } catch (IOException e){
            e.printStackTrace();
        }

        for (Map.Entry<String, BufferedImage> entry : waterGallery.entrySet()) {
            String key = entry.getKey();
            List<BufferedImage> value = new ArrayList<>();
            value.add(entry.getValue());
            List<BufferedImage> newValue = changeColor(value,-100,-100,-100, false);
            waterGalleryShaded.put(key,newValue.get(0));
        }

        //Depot
        img = null;
        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/structures/depot.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
        structureGallery.put("depot", img);
        //Harbor
        li = new ArrayList<>();
        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/structures/harbor.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
        structureGallery.put("harbor", img);
        //Factory
        li = new ArrayList<>();
        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/structures/factory.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
        structureGallery.put("factory", img);

        for (Map.Entry<String, BufferedImage> entry : structureGallery.entrySet()) {
            String key = entry.getKey();
            List<BufferedImage> value = new ArrayList<>();
            value.add(entry.getValue());
            List<BufferedImage> newValue = changeColor(value,-100,-100,+100, false);
            structureGalleryNatural.put(key,newValue.get(0));
        }

        for (Map.Entry<String, BufferedImage> entry : structureGalleryNatural.entrySet()) {
            String key = entry.getKey();
            List<BufferedImage> value = new ArrayList<>();
            value.add(entry.getValue());
            List<BufferedImage> newValue = changeColor(value,-100,-100,-100, true);
            structureGalleryNaturalShaded.put(key,newValue.get(0));
        }

        for (Map.Entry<String, BufferedImage> entry : structureGallery.entrySet()) {
            String key = entry.getKey();
            List<BufferedImage> value = new ArrayList<>();
            value.add(entry.getValue());
            List<BufferedImage> newValue = changeColor(value,gp.team1Color[0], gp.team1Color[1], gp.team1Color[2], false);
            structureGalleryTeam1.put(key,newValue.get(0));
        }
        for (Map.Entry<String, BufferedImage> entry : structureGalleryTeam1.entrySet()) {
            String key = entry.getKey();
            List<BufferedImage> value = new ArrayList<>();
            value.add(entry.getValue());
            List<BufferedImage> newValue = changeColor(value,-100,-100,-100, true);
            structureGalleryTeam1Shaded.put(key,newValue.get(0));
        }

        for (Map.Entry<String, BufferedImage> entry : structureGallery.entrySet()) {
            String key = entry.getKey();
            List<BufferedImage> value = new ArrayList<>();
            value.add(entry.getValue());
            List<BufferedImage> newValue = changeColor(value,gp.team2Color[0], gp.team2Color[1], gp.team2Color[2], false);
            structureGalleryTeam2.put(key,newValue.get(0));
        }
        for (Map.Entry<String, BufferedImage> entry : structureGalleryTeam2.entrySet()) {
            String key = entry.getKey();
            List<BufferedImage> value = new ArrayList<>();
            value.add(entry.getValue());
            List<BufferedImage> newValue = changeColor(value,-100,-100,-100, true);
            structureGalleryTeam2Shaded.put(key,newValue.get(0));
        }
        for (Map.Entry<Class<?>, List<BufferedImage>> entry : galleryTeam1.entrySet()) {
            Class<?> key = entry.getKey();
            ImageIcon value = new ImageIcon(entry.getValue().get(0));
            galleryTeam1Icon.put(key,value);
        }
        for (Map.Entry<Class<?>, List<BufferedImage>> entry : galleryTeam2.entrySet()) {
            Class<?> key = entry.getKey();
            ImageIcon value = new ImageIcon(entry.getValue().get(0));
            galleryTeam2Icon.put(key,value);
        }
        for (Map.Entry<Class<?>, List<BufferedImage>> entry : galleryTeam1Shaded.entrySet()) {
            Class<?> key = entry.getKey();
            ImageIcon value = new ImageIcon(entry.getValue().get(0));
            galleryTeam1IconShaded.put(key,value);
        }
        for (Map.Entry<Class<?>, List<BufferedImage>> entry : galleryTeam2Shaded.entrySet()) {
            Class<?> key = entry.getKey();
            ImageIcon value = new ImageIcon(entry.getValue().get(0));
            galleryTeam2IconShaded.put(key,value);
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

    public BufferedImage combineImages(BufferedImage base, BufferedImage upper){
        BufferedImage modifiedImage = new BufferedImage(base.getWidth(), base.getHeight(), BufferedImage.TYPE_INT_ARGB);
        // Apply a color change to pixels of base image
        for (int x = 0; x < base.getWidth(); x++) {
            for (int y = 0; y < base.getHeight(); y++) {
                int baseRgb = base.getRGB(x, y);
                int upperRgb = upper.getRGB(x, y);
                int alpha = (upperRgb >> 24) & 0xFF;
                //int baseRed = (baseRgb >> 16) & 0xFF;
                //int baseGreen = (baseRgb >> 8) & 0xFF;
                //int baseBlue = baseRgb & 0xFF;
                //int upperRed = (upperRgb >> 16) & 0xFF;
                //int upperGreen = (upperRgb >> 8) & 0xFF;
                //int upperBlue = upperRgb & 0xFF;
                if (alpha > 0){
                    // Combine modified color components with original alpha
                    //int modifiedRGB = (alpha << 24) | (upperRed << 16) | (upperGreen << 8) | upperBlue;
                    modifiedImage.setRGB(x, y, upperRgb);
                }
                else{
                    modifiedImage.setRGB(x, y, baseRgb);
                }
            }
        }
        return modifiedImage;
    }

    public Map<Class<?>, List<BufferedImage>> getGallery() {
        return gallery;
    }

    public void setGallery(Map<Class<?>, List<BufferedImage>> gallery) {
        this.gallery = gallery;
    }

    public Map<Class<?>, List<BufferedImage>> getGalleryTeam1Shaded() {
        return galleryTeam1Shaded;
    }

    public void setGalleryTeam1Shaded(Map<Class<?>, List<BufferedImage>> galleryShaded) {
        this.galleryTeam1Shaded = galleryShaded;
    }
    public Map<Class<?>, List<BufferedImage>> getGalleryTeam2Shaded() {
        return galleryTeam2Shaded;
    }
    public void setGalleryTeam2Shaded(Map<Class<?>, List<BufferedImage>> galleryShaded) {
        this.galleryTeam2Shaded = galleryShaded;
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

    public Map<String, List<BufferedImage>> getTileGallery() {
        return tileGallery;
    }

    public void setTileGallery(Map<String, List<BufferedImage>> tileGallery) {
        this.tileGallery = tileGallery;
    }

    public Map<String, List<BufferedImage>> getTileOutOfRangeGallery() {
        return tileOutOfRangeGallery;
    }

    public void setTileOutOfRangeGallery(Map<String, List<BufferedImage>> tileOutOfRangeGallery) {
        this.tileOutOfRangeGallery = tileOutOfRangeGallery;
    }

    public Map<String, List<BufferedImage>> getObstacleGallery() {
        return obstacleGallery;
    }

    public void setObstacleGallery(Map<String, List<BufferedImage>> obstacleGallery) {
        this.obstacleGallery = obstacleGallery;
    }

    public Map<String, BufferedImage> getWaterGallery() {
        return waterGallery;
    }

    public void setWaterGallery(Map<String, BufferedImage> waterGallery) {
        this.waterGallery = waterGallery;
    }

    public Map<String, BufferedImage> getRoadGallery() {
        return roadGallery;
    }

    public void setRoadGallery(Map<String, BufferedImage> roadGallery) {
        this.roadGallery = roadGallery;
    }

    public Map<String, BufferedImage> getStructureGallery() {
        return structureGallery;
    }

    public void setStructureGallery(Map<String, BufferedImage> structureGallery) {
        this.structureGallery = structureGallery;
    }

    public Map<String, BufferedImage> getStructureGalleryNatural() {
        return structureGalleryNatural;
    }

    public void setStructureGalleryNatural(Map<String, BufferedImage> structureGalleryNatural) {
        this.structureGalleryNatural = structureGalleryNatural;
    }

    public Map<String, BufferedImage> getStructureGalleryNaturalShaded() {
        return structureGalleryNaturalShaded;
    }

    public void setStructureGalleryNaturalShaded(Map<String, BufferedImage> structureGalleryNaturalShaded) {
        this.structureGalleryNaturalShaded = structureGalleryNaturalShaded;
    }

    public Map<String, BufferedImage> getStructureGalleryTeam1() {
        return structureGalleryTeam1;
    }

    public void setStructureGalleryTeam1(Map<String, BufferedImage> structureGalleryTeam1) {
        this.structureGalleryTeam1 = structureGalleryTeam1;
    }

    public Map<String, BufferedImage> getStructureGalleryTeam1Shaded() {
        return structureGalleryTeam1Shaded;
    }

    public void setStructureGalleryTeam1Shaded(Map<String, BufferedImage> structureGalleryTeam1Shaded) {
        this.structureGalleryTeam1Shaded = structureGalleryTeam1Shaded;
    }

    public Map<String, BufferedImage> getStructureGalleryTeam2() {
        return structureGalleryTeam2;
    }

    public void setStructureGalleryTeam2(Map<String, BufferedImage> structureGalleryTeam2) {
        this.structureGalleryTeam2 = structureGalleryTeam2;
    }

    public Map<String, BufferedImage> getStructureGalleryTeam2Shaded() {
        return structureGalleryTeam2Shaded;
    }

    public void setStructureGalleryTeam2Shaded(Map<String, BufferedImage> structureGalleryTeam2Shaded) {
        this.structureGalleryTeam2Shaded = structureGalleryTeam2Shaded;
    }

    public Map<Class<?>, ImageIcon> getGalleryTeam1Icon() {
        return galleryTeam1Icon;
    }

    public void setGalleryTeam1Icon(Map<Class<?>, ImageIcon> galleryTeam1Icon) {
        this.galleryTeam1Icon = galleryTeam1Icon;
    }

    public Map<Class<?>, ImageIcon> getGalleryTeam2Icon() {
        return galleryTeam2Icon;
    }

    public void setGalleryTeam2Icon(Map<Class<?>, ImageIcon> galleryTeam2Icon) {
        this.galleryTeam2Icon = galleryTeam2Icon;
    }
    public Map<Class<?>, ImageIcon> getGalleryTeam1IconShaded() {
        return galleryTeam1Icon;
    }

    public void setGalleryTeam1IconShaded(Map<Class<?>, ImageIcon> galleryTeam1Icon) {
        this.galleryTeam1Icon = galleryTeam1Icon;
    }

    public Map<Class<?>, ImageIcon> getGalleryTeam2IconShaded() {
        return galleryTeam2Icon;
    }

    public void setGalleryTeam2IconShaded(Map<Class<?>, ImageIcon> galleryTeam2Icon) {
        this.galleryTeam2Icon = galleryTeam2Icon;
    }
}
