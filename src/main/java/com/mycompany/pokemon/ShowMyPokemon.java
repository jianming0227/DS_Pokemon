/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pokemon;

import static com.mycompany.pokemon.ShowMapPage.saveNumber;
import java.awt.FontFormatException;
import java.awt.Image;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import java.awt.FontFormatException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
/**
 *
 * @author User
 */
public class ShowMyPokemon extends javax.swing.JFrame {

    static int saveNumber;

    /**
     * Creates new form ShowMyPokemon
     */
    public ShowMyPokemon(int saveNumber) {
        this.saveNumber = saveNumber;
        initComponents();

        ImageIcon weaknessImage = new ImageIcon("C:\\Users\\User\\Downloads\\weaknessIcon.png");
        Image resizedImage1 = weaknessImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1 = new ImageIcon(resizedImage1);
        Weakness.setIcon(resizedIcon1);

        ImageIcon strengthImage = new ImageIcon("C:\\Users\\User\\Downloads\\strengthIcon.png");
        Image resizedImage2 = strengthImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
        Strength.setIcon(resizedIcon2);

        ImageIcon HpImage = new ImageIcon("C:\\Users\\User\\Downloads\\hpIcon.png");
        Image resizedImage3 = HpImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon3 = new ImageIcon(resizedImage3);
        Hp.setIcon(resizedIcon3);

        ImageIcon XpImage = new ImageIcon("C:\\Users\\User\\Downloads\\xpIcon.png");
        Image resizedImage4 = XpImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon4 = new ImageIcon(resizedImage4);
        Xp.setIcon(resizedIcon4);

        ImageIcon LevelImage = new ImageIcon("C:\\Users\\User\\Downloads\\LevelIcon.png");
        Image resizedImage5 = LevelImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon5 = new ImageIcon(resizedImage5);
        LevelP.setIcon(resizedIcon5);

        String[] pokemonArray = ConnectDatabase.showMyPokemonGUI(saveNumber);

        String pokemonName = pokemonArray[0];
        int levelINT = Integer.parseInt(pokemonArray[1]);
        String level = pokemonArray[1];
        String type = pokemonArray[2];
        int hpINT = Integer.parseInt(pokemonArray[3]);
        String hp = pokemonArray[3];
        int xpINT = Integer.parseInt(pokemonArray[4]);
        String xp = pokemonArray[4];
        String move1 = pokemonArray[5];
        String move2 = pokemonArray[6];
        String move3 = pokemonArray[7];
        String move4 = pokemonArray[8];
        String strongAgainst = pokemonArray[9];
        String weakAgainst = pokemonArray[10];
        int dmg1INT = 0; // Default value if the element is null
        if (pokemonArray[11] != null && !pokemonArray[11].isEmpty()) {
            dmg1INT = Integer.parseInt(pokemonArray[11]);
        }
        String dmg1 = pokemonArray[11];

        int dmg2INT = 0; // Default value if the element is null
        if (pokemonArray[12] != null && !pokemonArray[12].isEmpty()) {
            dmg2INT = Integer.parseInt(pokemonArray[12]);
        }
        String dmg2 = pokemonArray[12];

        int dmg3INT = 0; // Default value if the element is null
        if (pokemonArray[13] != null && !pokemonArray[13].isEmpty()) {
            dmg3INT = Integer.parseInt(pokemonArray[13]);
        }
        String dmg3 = pokemonArray[13];

        int dmgINT = 0; // Default value if the element is null
        if (pokemonArray[14] != null && !pokemonArray[14].isEmpty()) {
            dmgINT = Integer.parseInt(pokemonArray[14]);
        }
        String dmg = pokemonArray[14];

        String[] strongAgainstTypePlayer = pokemonArray[9].split(" · ");
        StringBuilder strongAgainstT = new StringBuilder();

        for (String types : strongAgainstTypePlayer) {
            strongAgainstT.append("- ").append(types).append("\n");
        }
        String[] weakAgainstTypePlayer = pokemonArray[10].split(" · ");
        StringBuilder weakAgainstT = new StringBuilder();

        for (String types : weakAgainstTypePlayer) {
            weakAgainstT.append("- ").append(types).append("\n");
        }

        PokemonName.setText(pokemonName);

        ImageIcon UpperImage = null;
        ImageIcon PokemonImage = null;
        ImageIcon TypeImage = null;
        ImageIcon Move1Image = null;
        ImageIcon Move2Image = null;
        ImageIcon Move3Image = null;
        ImageIcon Move4Image = null;
        switch (pokemonName) {
            case "Bulbasaur":
                UpperImage = new ImageIcon("C:\\Users\\User\\Downloads\\MY POKEMON.png");
                UpperImg.setIcon(UpperImage);
                PokemonImage = new ImageIcon("C:\\Users\\User\\Downloads\\Bulbasaur.png");
                PokemonImg.setIcon(PokemonImage);
                TypeImage = new ImageIcon("C:\\Users\\User\\Downloads\\grassTypeIcon.png");
                Image resizedImage6 = TypeImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon6 = new ImageIcon(resizedImage6);
                Type.setIcon(resizedIcon6);
                Move1Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\3.png");
                Move1.setIcon(Move1Image);
                Move2Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\4.png");
                Move2.setIcon(Move2Image);
                if (move3 != null) {
                    Move3Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\2.png");
                    Move3.setIcon(Move3Image);
                    Move3T.setText(move3 + " [" + dmg3 + " damage]");
                } else {
                    Move3.setVisible(false);
                    Move3T.setVisible(false);
                }
                if (move4 != null) {
                    Move4Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\1.png");
                    Move4.setIcon(Move4Image);
                    Move4T.setText(move4 + " [" + dmg + " damage]");
                } else {
                    Move4.setVisible(false);
                    Move4T.setVisible(false);
                }
                LevelT.setText(level);
                TypeT.setText(type);
                HpT.setText(hp);
                XpT.setText(xp + "/" + ConnectDatabase.checkLevelLimit(levelINT));
                Move1T.setText(move1 + " [" + dmg1 + " damage]");
                Move2T.setText(move2 + " [" + dmg2 + " damage]");
                StrengthT.setText(strongAgainstT.toString());
                WeakT.setText(weakAgainstT.toString());
                break;
            case "Ivysaur":
                UpperImage = new ImageIcon("C:\\Users\\User\\Downloads\\MY POKEMON (1).png");
                UpperImg.setIcon(UpperImage);
                PokemonImage = new ImageIcon("C:\\Users\\User\\Downloads\\Untitled design\\1.png");
                PokemonImg.setIcon(PokemonImage);
                TypeImage = new ImageIcon("C:\\Users\\User\\Downloads\\grassTypeIcon.png");
                Image resizedImage7 = TypeImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon7 = new ImageIcon(resizedImage7);
                Type.setIcon(resizedIcon7);
                Move1Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\3.png");
                Move1.setIcon(Move1Image);
                Move2Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves4.png");
                Move2.setIcon(Move2Image);
                if (move3 != null) {
                    Move3Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\2.png");
                    Move3.setIcon(Move3Image);
                    Move3T.setText(move3 + " [" + dmg3 + " damage]");
                } else {
                    Move3.setVisible(false);
                    Move3T.setVisible(false);
                }
                if (move4 != null) {
                    Move4Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\1.png");
                    Move4.setIcon(Move4Image);
                    Move4T.setText(move4 + " [" + dmg + " damage]");
                } else {
                    Move4.setVisible(false);
                    Move4T.setVisible(false);
                }
                LevelT.setText(level);
                TypeT.setText(type);
                HpT.setText(hp);
                XpT.setText(xp + "/" + ConnectDatabase.checkLevelLimit(levelINT));
                Move1T.setText(move1 + " [" + dmg1 + " damage]");
                Move2T.setText(move2 + " [" + dmg2 + " damage]");
                StrengthT.setText(strongAgainstT.toString());
                WeakT.setText(weakAgainstT.toString());
                break;
            case "Venusaur":
                UpperImage = new ImageIcon("C:\\Users\\User\\Downloads\\MY POKEMON (2).png");
                UpperImg.setIcon(UpperImage);
                PokemonImage = new ImageIcon("C:\\Users\\User\\Downloads\\Untitled design\\2.png");
                PokemonImg.setIcon(PokemonImage);
                TypeImage = new ImageIcon("C:\\Users\\User\\Downloads\\grassTypeIcon.png");
                Image resizedImage8 = TypeImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon8 = new ImageIcon(resizedImage8);
                Type.setIcon(resizedIcon8);
                Move1Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\3.png");
                Move1.setIcon(Move1Image);
                Move2Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves4.png");
                Move2.setIcon(Move2Image);
                if (move3 != null) {
                    Move3Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\2.png");
                    Move3.setIcon(Move3Image);
                    Move3T.setText(move3 + " [" + dmg3 + " damage]");
                } else {
                    Move3.setVisible(false);
                    Move3T.setVisible(false);
                }
                if (move4 != null) {
                    Move4Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\1.png");
                    Move4.setIcon(Move4Image);
                    Move4T.setText(move4 + " [" + dmg + " damage]");
                } else {
                    Move4.setVisible(false);
                    Move4T.setVisible(false);
                }
                LevelT.setText(level);
                TypeT.setText(type);
                HpT.setText(hp);
                XpT.setText(xp + "/" + ConnectDatabase.checkLevelLimit(levelINT));
                Move1T.setText(move1 + " [" + dmg1 + " damage]");
                Move2T.setText(move2 + " [" + dmg2 + " damage]");
                StrengthT.setText(strongAgainstT.toString());
                WeakT.setText(weakAgainstT.toString());
                break;
            case "Charmander":
                UpperImage = new ImageIcon("C:\\Users\\User\\Downloads\\MY POKEMON (3).png");
                UpperImg.setIcon(UpperImage);
                PokemonImage = new ImageIcon("C:\\Users\\User\\Downloads\\Charmander.png");
                PokemonImg.setIcon(PokemonImage);
                TypeImage = new ImageIcon("C:\\Users\\User\\Downloads\\fireTypeIcon.png");
                Image resizedImage9 = TypeImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon9 = new ImageIcon(resizedImage9);
                Type.setIcon(resizedIcon9);
                Move1Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\5.png");
                Move1.setIcon(Move1Image);
                Move2Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\6.png");
                Move2.setIcon(Move2Image);
                if (move3 != null) {
                    Move3Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\2.png");
                    Move3.setIcon(Move3Image);
                    Move3T.setText(move3 + " [" + dmg3 + " damage]");
                } else {
                    Move3.setVisible(false);
                    Move3T.setVisible(false);
                }
                if (move4 != null) {
                    Move4Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\1.png");
                    Move4.setIcon(Move4Image);
                    Move4T.setText(move4 + " [" + dmg + " damage]");
                } else {
                    Move4.setVisible(false);
                    Move4T.setVisible(false);
                }
                LevelT.setText(level);
                TypeT.setText(type);
                HpT.setText(hp);
                XpT.setText(xp + "/" + ConnectDatabase.checkLevelLimit(levelINT));
                Move1T.setText(move1 + " [" + dmg1 + " damage]");
                Move2T.setText(move2 + " [" + dmg2 + " damage]");
                StrengthT.setText(strongAgainstT.toString());
                WeakT.setText(weakAgainstT.toString());
                break;
            case "Charmeleon":
                UpperImage = new ImageIcon("C:\\Users\\User\\Downloads\\MY POKEMON (4).png");
                UpperImg.setIcon(UpperImage);
                PokemonImage = new ImageIcon("C:\\Users\\User\\Downloads\\Untitled design\\3.png");
                PokemonImg.setIcon(PokemonImage);
                TypeImage = new ImageIcon("C:\\Users\\User\\Downloads\\fireTypeIcon.png");
                Image resizedImage10 = TypeImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon10 = new ImageIcon(resizedImage10);
                Type.setIcon(resizedIcon10);
                Move1Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\5.png");
                Move1.setIcon(Move1Image);
                Move2Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves6.png");
                Move2.setIcon(Move2Image);
                if (move3 != null) {
                    Move3Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\2.png");
                    Move3.setIcon(Move3Image);
                    Move3T.setText(move3 + " [" + dmg3 + " damage]");
                } else {
                    Move3.setVisible(false);
                    Move3T.setVisible(false);
                }
                if (move4 != null) {
                    Move4Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\1.png");
                    Move4.setIcon(Move4Image);
                    Move4T.setText(move4 + " [" + dmg + " damage]");
                } else {
                    Move4.setVisible(false);
                    Move4T.setVisible(false);
                }
                LevelT.setText(level);
                TypeT.setText(type);
                HpT.setText(hp);
                XpT.setText(xp + "/" + ConnectDatabase.checkLevelLimit(levelINT));
                Move1T.setText(move1 + " [" + dmg1 + " damage]");
                Move2T.setText(move2 + " [" + dmg2 + " damage]");
                StrengthT.setText(strongAgainstT.toString());
                WeakT.setText(weakAgainstT.toString());
                break;
            case "Charizard":
                UpperImage = new ImageIcon("C:\\Users\\User\\Downloads\\MY POKEMON (5).png");
                UpperImg.setIcon(UpperImage);
                PokemonImage = new ImageIcon("C:\\Users\\User\\Downloads\\Untitled design\\4.png");
                PokemonImg.setIcon(PokemonImage);
                TypeImage = new ImageIcon("C:\\Users\\User\\Downloads\\fireTypeIcon.png");
                Image resizedImage11 = TypeImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon11 = new ImageIcon(resizedImage11);
                Type.setIcon(resizedIcon11);
                Move1Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\5.png");
                Move1.setIcon(Move1Image);
                Move2Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves6.png");
                Move2.setIcon(Move2Image);
                if (move3 != null) {
                    Move3Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\2.png");
                    Move3.setIcon(Move3Image);
                    Move3T.setText(move3 + " [" + dmg3 + " damage]");
                } else {
                    Move3.setVisible(false);
                    Move3T.setVisible(false);
                }
                if (move4 != null) {
                    Move4Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\1.png");
                    Move4.setIcon(Move4Image);
                    Move4T.setText(move4 + " [" + dmg + " damage]");
                } else {
                    Move4.setVisible(false);
                    Move4T.setVisible(false);
                }
                LevelT.setText(level);
                TypeT.setText(type);
                HpT.setText(hp);
                XpT.setText(xp + "/" + ConnectDatabase.checkLevelLimit(levelINT));
                Move1T.setText(move1 + " [" + dmg1 + " damage]");
                Move2T.setText(move2 + " [" + dmg2 + " damage]");
                StrengthT.setText(strongAgainstT.toString());
                WeakT.setText(weakAgainstT.toString());
                break;
            case "Squirtle":
                UpperImage = new ImageIcon("C:\\Users\\User\\Downloads\\MY POKEMON (6).png");
                UpperImg.setIcon(UpperImage);
                PokemonImage = new ImageIcon("C:\\Users\\User\\Downloads\\Squirtle.png");
                PokemonImg.setIcon(PokemonImage);
                TypeImage = new ImageIcon("C:\\Users\\User\\Downloads\\waterTypeIcon.png");
                Image resizedImage12 = TypeImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon12 = new ImageIcon(resizedImage12);
                Type.setIcon(resizedIcon12);
                Move1Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\9.png");
                Move1.setIcon(Move1Image);
                Move2Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves10.png");
                Move2.setIcon(Move2Image);
                if (move3 != null) {
                    Move3Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\2.png");
                    Move3.setIcon(Move3Image);
                    Move3T.setText(move3 + " [" + dmg3 + " damage]");
                } else {
                    Move3.setVisible(false);
                    Move3T.setVisible(false);
                }
                if (move4 != null) {
                    Move4Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\1.png");
                    Move4.setIcon(Move4Image);
                    Move4T.setText(move4 + " [" + dmg + " damage]");
                } else {
                    Move4.setVisible(false);
                    Move4T.setVisible(false);
                }
                LevelT.setText(level);
                TypeT.setText(type);
                HpT.setText(hp);
                XpT.setText(xp + "/" + ConnectDatabase.checkLevelLimit(levelINT));
                Move1T.setText(move1 + " [" + dmg1 + " damage]");
                Move2T.setText(move2 + " [" + dmg2 + " damage]");
                StrengthT.setText(strongAgainstT.toString());
                WeakT.setText(weakAgainstT.toString());
                break;
            case "Wartortle":
                UpperImage = new ImageIcon("C:\\Users\\User\\Downloads\\MY POKEMON (7).png");
                UpperImg.setIcon(UpperImage);
                PokemonImage = new ImageIcon("C:\\Users\\User\\Downloads\\Untitled design\\5.png");
                PokemonImg.setIcon(PokemonImage);
                TypeImage = new ImageIcon("C:\\Users\\User\\Downloads\\waterTypeIcon.png");
                Image resizedImage13 = TypeImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon13 = new ImageIcon(resizedImage13);
                Type.setIcon(resizedIcon13);
                Move1Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\9.png");
                Move1.setIcon(Move1Image);
                Move2Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves10.png");
                Move2.setIcon(Move2Image);
                if (move3 != null) {
                    Move3Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\2.png");
                    Move3.setIcon(Move3Image);
                    Move3T.setText(move3 + " [" + dmg3 + " damage]");
                } else {
                    Move3.setVisible(false);
                    Move3T.setVisible(false);
                }
                if (move4 != null) {
                    Move4Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\1.png");
                    Move4.setIcon(Move4Image);
                    Move4T.setText(move4 + " [" + dmg + " damage]");
                } else {
                    Move4.setVisible(false);
                    Move4T.setVisible(false);
                }
                LevelT.setText(level);
                TypeT.setText(type);
                HpT.setText(hp);
                XpT.setText(xp + "/" + ConnectDatabase.checkLevelLimit(levelINT));
                Move1T.setText(move1 + " [" + dmg1 + " damage]");
                Move2T.setText(move2 + " [" + dmg2 + " damage]");
                StrengthT.setText(strongAgainstT.toString());
                WeakT.setText(weakAgainstT.toString());
                break;
            case "Blastoise":
                UpperImage = new ImageIcon("C:\\Users\\User\\Downloads\\MY POKEMON (8).png");
                UpperImg.setIcon(UpperImage);
                PokemonImage = new ImageIcon("C:\\Users\\User\\Downloads\\Untitled design\\6.png");
                PokemonImg.setIcon(PokemonImage);
                TypeImage = new ImageIcon("C:\\Users\\User\\Downloads\\waterTypeIcon.png");
                Image resizedImage14 = TypeImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon14 = new ImageIcon(resizedImage14);
                Type.setIcon(resizedIcon14);
                Move1Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\9.png");
                Move1.setIcon(Move1Image);
                Move2Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves10.png");
                Move2.setIcon(Move2Image);
                if (move3 != null) {
                    Move3Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\2.png");
                    Move3.setIcon(Move3Image);
                    Move3T.setText(move3 + " [" + dmg3 + " damage]");
                } else {
                    Move3.setVisible(false);
                    Move3T.setVisible(false);
                }
                if (move4 != null) {
                    Move4Image = new ImageIcon("C:\\Users\\User\\Downloads\\Moves\\1.png");
                    Move4.setIcon(Move4Image);
                    Move4T.setText(move4 + " [" + dmg + " damage]");
                } else {
                    Move4.setVisible(false);
                    Move4T.setVisible(false);
                }
                LevelT.setText(level);
                TypeT.setText(type);
                HpT.setText(hp);
                XpT.setText(xp + "/" + ConnectDatabase.checkLevelLimit(levelINT));
                Move1T.setText(move1 + " [" + dmg1 + " damage]");
                Move2T.setText(move2 + " [" + dmg2 + " damage]");
                StrengthT.setText(strongAgainstT.toString());
                WeakT.setText(weakAgainstT.toString());
                break;
            default:
                // Handle other Pokémon
                break;
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WeakT = new javax.swing.JLabel();
        StrengthT = new javax.swing.JLabel();
        Move4T = new javax.swing.JLabel();
        Move3T = new javax.swing.JLabel();
        Move2T = new javax.swing.JLabel();
        Move1T = new javax.swing.JLabel();
        XpT = new javax.swing.JLabel();
        HpT = new javax.swing.JLabel();
        TypeT = new javax.swing.JLabel();
        LevelT = new javax.swing.JLabel();
        UpperImg = new javax.swing.JLabel();
        PokemonName = new javax.swing.JLabel();
        LevelP = new javax.swing.JLabel();
        Type = new javax.swing.JLabel();
        PokemonImg = new javax.swing.JLabel();
        Hp = new javax.swing.JLabel();
        Xp = new javax.swing.JLabel();
        Move2 = new javax.swing.JLabel();
        Move1 = new javax.swing.JLabel();
        Move3 = new javax.swing.JLabel();
        Move4 = new javax.swing.JLabel();
        Strength = new javax.swing.JLabel();
        Weakness = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        LowerImg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        WeakT.setText("jLabel1");
        getContentPane().add(WeakT, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 560, 190, 50));

        StrengthT.setText("jLabel1");
        getContentPane().add(StrengthT, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 560, 210, 50));

        Move4T.setText("jLabel1");
        getContentPane().add(Move4T, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 460, 150, 50));

        Move3T.setText("jLabel1");
        getContentPane().add(Move3T, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 350, 160, 50));

        Move2T.setText("jLabel1");
        getContentPane().add(Move2T, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 460, 140, 50));

        Move1T.setText("jLabel1");
        getContentPane().add(Move1T, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 350, 150, 50));

        XpT.setText("jLabel1");
        getContentPane().add(XpT, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 240, 170, 60));

        HpT.setText("jLabel1");
        getContentPane().add(HpT, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, 150, 50));

        TypeT.setText("jLabel1");
        getContentPane().add(TypeT, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 140, 160, 50));

        LevelT.setText("jLabel1");
        getContentPane().add(LevelT, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 150, 50));

        UpperImg.setText("jLabel2");
        UpperImg.setPreferredSize(new java.awt.Dimension(1080, 100));
        getContentPane().add(UpperImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        PokemonName.setText("jLabel1");
        getContentPane().add(PokemonName, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, -1, -1));

        LevelP.setText("jLabel1");
        LevelP.setPreferredSize(new java.awt.Dimension(80, 80));
        getContentPane().add(LevelP, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, -1, -1));

        Type.setText("jLabel1");
        Type.setPreferredSize(new java.awt.Dimension(80, 80));
        getContentPane().add(Type, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 120, -1, -1));

        PokemonImg.setText("jLabel5");
        PokemonImg.setPreferredSize(new java.awt.Dimension(335, 470));
        getContentPane().add(PokemonImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 160, 440, -1));

        Hp.setText("jLabel1");
        Hp.setPreferredSize(new java.awt.Dimension(80, 80));
        getContentPane().add(Hp, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, -1, -1));

        Xp.setText("jLabel1");
        Xp.setPreferredSize(new java.awt.Dimension(80, 80));
        getContentPane().add(Xp, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 230, -1, -1));

        Move2.setText("jLabel1");
        Move2.setPreferredSize(new java.awt.Dimension(80, 80));
        getContentPane().add(Move2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 450, -1, -1));

        Move1.setText("jLabel1");
        Move1.setPreferredSize(new java.awt.Dimension(80, 80));
        getContentPane().add(Move1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 340, -1, -1));

        Move3.setText("jLabel1");
        Move3.setPreferredSize(new java.awt.Dimension(80, 80));
        getContentPane().add(Move3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 340, -1, -1));

        Move4.setText("jLabel1");
        Move4.setPreferredSize(new java.awt.Dimension(80, 80));
        getContentPane().add(Move4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 450, -1, -1));

        Strength.setText("jLabel1");
        Strength.setPreferredSize(new java.awt.Dimension(80, 80));
        getContentPane().add(Strength, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 550, -1, -1));

        Weakness.setText("jLabel1");
        Weakness.setPreferredSize(new java.awt.Dimension(80, 80));
        getContentPane().add(Weakness, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 550, -1, -1));

        jButton1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 660, 280, 50));

        LowerImg.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\Untitled design.png")); // NOI18N
        LowerImg.setText("jLabel1");
        getContentPane().add(LowerImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1080, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            MainMenuPage mainMenuPageFrame = new MainMenuPage(saveNumber);
            this.dispose();
            mainMenuPageFrame.setVisible(true);
            mainMenuPageFrame.pack();
            mainMenuPageFrame.setLocationRelativeTo(null);
        } catch (FontFormatException ex) {
            Logger.getLogger(ShowMyPokemon.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ShowMyPokemon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowMyPokemon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowMyPokemon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowMyPokemon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowMyPokemon(saveNumber).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Hp;
    private javax.swing.JLabel HpT;
    private javax.swing.JLabel LevelP;
    private javax.swing.JLabel LevelT;
    private javax.swing.JLabel LowerImg;
    private javax.swing.JLabel Move1;
    private javax.swing.JLabel Move1T;
    private javax.swing.JLabel Move2;
    private javax.swing.JLabel Move2T;
    private javax.swing.JLabel Move3;
    private javax.swing.JLabel Move3T;
    private javax.swing.JLabel Move4;
    private javax.swing.JLabel Move4T;
    private javax.swing.JLabel PokemonImg;
    private javax.swing.JLabel PokemonName;
    private javax.swing.JLabel Strength;
    private javax.swing.JLabel StrengthT;
    private javax.swing.JLabel Type;
    private javax.swing.JLabel TypeT;
    private javax.swing.JLabel UpperImg;
    private javax.swing.JLabel WeakT;
    private javax.swing.JLabel Weakness;
    private javax.swing.JLabel Xp;
    private javax.swing.JLabel XpT;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
