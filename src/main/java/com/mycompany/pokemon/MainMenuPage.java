/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pokemon;

import com.mycompany.pokemon.Maps.City;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class MainMenuPage extends javax.swing.JFrame {

    private int saveNumber;
    Font font;

    public void setSaveNumber(int saveNumber) {
        this.saveNumber = saveNumber;
    }

    /**
     * Creates new form MainMenuPage
     */
    public MainMenuPage(int saveNumber) throws FontFormatException {
        this.saveNumber = saveNumber;
        initComponents();

        String playerName = ConnectDatabase.getName(saveNumber);

        String location = ConnectDatabase.getLocation(saveNumber);
        Location.setText(location);

        String commonPokemon = POKEMON.checkWildPokemon(location);

        String gymType = POKEMON.checkGymType(location);

        FightWildPokemonLT.setOpaque(false);
        FightWildPokemonLT.setContentAreaFilled(false);
        FightWildPokemonLT.setBorderPainted(false);

        ChallengeGymLeader.setOpaque(false);
        ChallengeGymLeader.setContentAreaFilled(false);
        ChallengeGymLeader.setBorderPainted(false);

        PokeMaze.setOpaque(false);
        PokeMaze.setContentAreaFilled(false);
        PokeMaze.setBorderPainted(false);

        SafariZone.setOpaque(false);
        SafariZone.setContentAreaFilled(false);
        SafariZone.setBorderPainted(false);

        RivalRace.setOpaque(false);
        RivalRace.setContentAreaFilled(false);
        RivalRace.setBorderPainted(false);

        SaveAndExit.setOpaque(false);
        SaveAndExit.setContentAreaFilled(false);
        SaveAndExit.setBorderPainted(false);

        ShowBadges.setOpaque(false);
        ShowBadges.setContentAreaFilled(false);
        ShowBadges.setBorderPainted(false);

        ShowMyPokemon.setOpaque(false);
        ShowMyPokemon.setContentAreaFilled(false);
        ShowMyPokemon.setBorderPainted(false);

        ShowMap.setOpaque(false);
        ShowMap.setContentAreaFilled(false);
        ShowMap.setBorderPainted(false);

        FightWildPokemon.setOpaque(false);
        FightWildPokemon.setContentAreaFilled(false);
        FightWildPokemon.setBorderPainted(false);

        TalkToMom.setOpaque(false);
        TalkToMom.setContentAreaFilled(false);
        TalkToMom.setBorderPainted(false);

        MoveToButton4.setOpaque(false);
        MoveToButton4.setContentAreaFilled(false);
        MoveToButton4.setBorderPainted(false);

        MoveToButton3.setOpaque(false);
        MoveToButton3.setContentAreaFilled(false);
        MoveToButton3.setBorderPainted(false);

        MoveToButton2.setOpaque(false);
        MoveToButton2.setContentAreaFilled(false);
        MoveToButton2.setBorderPainted(false);

        MoveToButton1.setOpaque(false);
        MoveToButton1.setContentAreaFilled(false);
        MoveToButton1.setBorderPainted(false);

        String palletTownPath = "C:\\Users\\User\\Downloads\\MainMenu (1).png";
        String saffronCityPath = "C:\\Users\\User\\Downloads\\MainMenu.png";
        String fuchsiaCityPath = "C:\\Users\\User\\Downloads\\MainMenu (2).png";
        String lavenderTownPath = "C:\\Users\\User\\Downloads\\MainMenu (3).png";
        String othersPath = "C:\\Users\\User\\Downloads\\MainMenu (4).png";
        ImageIcon palletTownImage = new ImageIcon(palletTownPath);
        ImageIcon saffronCityImage = new ImageIcon(saffronCityPath);
        ImageIcon fuchsiaCityImage = new ImageIcon(fuchsiaCityPath);
        ImageIcon lavenderTownImage = new ImageIcon(lavenderTownPath);
        ImageIcon othersImage = new ImageIcon(othersPath);

        if (Location.getText() != null && Location.getText().equals("PalletTown")) {

            MainMenuBackGround.setIcon(palletTownImage);
            PlayerName.setText(playerName);

            ChallengeGymLeader.setEnabled(false);
            FightWildPokemonLT.setEnabled(false);
            RivalRace.setEnabled(false);
            SafariZone.setEnabled(false);
            PokeMaze.setEnabled(false);

            try {
                File fontStyle = new File("C:/Users/User/Downloads/pokemon-classic/Pokemon Classic.ttf");
                if (!fontStyle.exists()) {
                    System.err.println("Font file not found.");
                    return;
                }
                Font font1 = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(20f);
                Font font2 = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(20f);
                Font font3 = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(24f);
                font = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(11f);
                PlayerName.setFont(font3);
            } catch (FileNotFoundException e) {
                System.err.println("Font file not found: " + e.getMessage());
            } catch (FontFormatException e) {
                System.err.println("Font format error: " + e.getMessage());
                throw e; // Re-throw the exception if needed
            } catch (IOException e) {
                System.err.println("I/O error: " + e.getMessage());
            }
        } else if (Location.getText() != null && Location.getText().equals("SaffronCity")) {

            MainMenuBackGround.setIcon(saffronCityImage);
            PlayerName.setText(playerName);

            TalkToMom.setEnabled(false);
            FightWildPokemonLT.setEnabled(false);
            PokeMaze.setEnabled(false);
            SafariZone.setEnabled(false);
            

            try {
                File fontStyle = new File("C:/Users/User/Downloads/pokemon-classic/Pokemon Classic.ttf");
                if (!fontStyle.exists()) {
                    System.err.println("Font file not found.");
                    return;
                }
                Font font1 = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(20f);
                Font font2 = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(20f);
                Font font3 = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(24f);
                font = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(11f);
                PlayerName.setFont(font3);
            } catch (FileNotFoundException e) {
                System.err.println("Font file not found: " + e.getMessage());
            } catch (FontFormatException e) {
                System.err.println("Font format error: " + e.getMessage());
                throw e; // Re-throw the exception if needed
            } catch (IOException e) {
                System.err.println("I/O error: " + e.getMessage());
            }
        } else if (Location.getText() != null && Location.getText().equals("FuchsiaCity")) {

            MainMenuBackGround.setIcon(fuchsiaCityImage);
            PlayerName.setText(playerName);

            TalkToMom.setEnabled(false);
            FightWildPokemonLT.setEnabled(false);
            PokeMaze.setEnabled(false);
            RivalRace.setEnabled(false);

            try {
                File fontStyle = new File("C:/Users/User/Downloads/pokemon-classic/Pokemon Classic.ttf");
                if (!fontStyle.exists()) {
                    System.err.println("Font file not found.");
                    return;
                }
                Font font1 = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(20f);
                Font font2 = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(20f);
                Font font3 = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(24f);
                font = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(11f);
                PlayerName.setFont(font3);
            } catch (FileNotFoundException e) {
                System.err.println("Font file not found: " + e.getMessage());
            } catch (FontFormatException e) {
                System.err.println("Font format error: " + e.getMessage());
                throw e; // Re-throw the exception if needed
            } catch (IOException e) {
                System.err.println("I/O error: " + e.getMessage());
            }
        } else if (Location.getText() != null && Location.getText().equals("LavenderTown")) {

            MainMenuBackGround.setIcon(lavenderTownImage);
            PlayerName.setText(playerName);

            TalkToMom.setEnabled(false);
            ChallengeGymLeader.setEnabled(false);
            FightWildPokemon.setEnabled(false);
            RivalRace.setEnabled(false);
            SafariZone.setEnabled(false);

            try {
                File fontStyle = new File("C:/Users/User/Downloads/pokemon-classic/Pokemon Classic.ttf");
                if (!fontStyle.exists()) {
                    System.err.println("Font file not found.");
                    return;
                }
                Font font1 = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(20f);
                Font font2 = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(20f);
                Font font3 = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(24f);
                font = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(11f);
                PlayerName.setFont(font3);

            } catch (FileNotFoundException e) {
                System.err.println("Font file not found: " + e.getMessage());
            } catch (FontFormatException e) {
                System.err.println("Font format error: " + e.getMessage());
                throw e; // Re-throw the exception if needed
            } catch (IOException e) {
                System.err.println("I/O error: " + e.getMessage());
            }
        } else {
            MainMenuBackGround.setIcon(othersImage);
            PlayerName.setText(playerName);
            RealLocation.setText(location);
            CommonPokemon.setText(commonPokemon);
            GymType.setText(gymType);

            TalkToMom.setEnabled(false);
            FightWildPokemonLT.setEnabled(false);
            PokeMaze.setEnabled(false);
            RivalRace.setEnabled(false);
            SafariZone.setEnabled(false);

            try {
                File fontStyle = new File("C:/Users/User/Downloads/pokemon-classic/Pokemon Classic.ttf");
                if (!fontStyle.exists()) {
                    System.err.println("Font file not found.");
                    return;
                }
                Font font1 = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(20f);
                Font font2 = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(20f);
                Font font3 = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(24f);
                font = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(11f);
                RealLocation.setFont(font1);
                CommonPokemon.setFont(font2);
                GymType.setFont(font2);
                PlayerName.setFont(font3);
            } catch (FileNotFoundException e) {
                System.err.println("Font file not found: " + e.getMessage());
            } catch (FontFormatException e) {
                System.err.println("Font format error: " + e.getMessage());
                throw e; // Re-throw the exception if needed
            } catch (IOException e) {
                System.err.println("I/O error: " + e.getMessage());
            }
        }

        Maps maps = new Maps(); // Create an instance of the Maps class
        List<Maps.City> connections = maps.checkConnection(location); // Call the method on the instance

        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i) != null) {
                switch (i) {
                    case 0:
                        Location1.setText(connections.get(i).name);
                        Location1.setFont(font);
                        break;
                    case 1:
                        Location2.setText(connections.get(i).name);
                        Location2.setFont(font);
                        break;
                    case 2:
                        Location3.setText(connections.get(i).name);
                        Location3.setFont(font);
                        break;
                    case 3:
                        Location4.setText(connections.get(i).name);
                        Location4.setFont(font);
                        break;
                    // Add more cases if needed
                    default:
                        break;
                }
            }
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

        CommonPokemon = new javax.swing.JLabel();
        GymType = new javax.swing.JLabel();
        PlayerName = new javax.swing.JLabel();
        RealLocation = new javax.swing.JLabel();
        Location4 = new javax.swing.JLabel();
        Location3 = new javax.swing.JLabel();
        Location2 = new javax.swing.JLabel();
        Location1 = new javax.swing.JLabel();
        FightWildPokemonLT = new javax.swing.JButton();
        ChallengeGymLeader = new javax.swing.JButton();
        PokeMaze = new javax.swing.JButton();
        SafariZone = new javax.swing.JButton();
        RivalRace = new javax.swing.JButton();
        SaveAndExit = new javax.swing.JButton();
        ShowBadges = new javax.swing.JButton();
        ShowMyPokemon = new javax.swing.JButton();
        ShowMap = new javax.swing.JButton();
        FightWildPokemon = new javax.swing.JButton();
        TalkToMom = new javax.swing.JButton();
        MoveToButton4 = new javax.swing.JButton();
        MoveToButton3 = new javax.swing.JButton();
        MoveToButton2 = new javax.swing.JButton();
        MoveToButton1 = new javax.swing.JButton();
        MainMenuBackGround = new javax.swing.JLabel();
        Location = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(CommonPokemon, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 500, 520, 30));
        getContentPane().add(GymType, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 430, 490, 40));
        getContentPane().add(PlayerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 300, 110));

        RealLocation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(RealLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, 310, 80));

        Location4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(Location4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 340, 150, 40));

        Location3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(Location3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 340, 150, 40));

        Location2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(Location2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, 150, 40));

        Location1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(Location1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 150, 40));

        FightWildPokemonLT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FightWildPokemonLTActionPerformed(evt);
            }
        });
        getContentPane().add(FightWildPokemonLT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 280, 60));

        ChallengeGymLeader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChallengeGymLeaderActionPerformed(evt);
            }
        });
        getContentPane().add(ChallengeGymLeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 520, 60));

        PokeMaze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PokeMazeActionPerformed(evt);
            }
        });
        getContentPane().add(PokeMaze, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 400, 50));

        SafariZone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SafariZoneActionPerformed(evt);
            }
        });
        getContentPane().add(SafariZone, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 733, 140, 50));

        RivalRace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RivalRaceActionPerformed(evt);
            }
        });
        getContentPane().add(RivalRace, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 733, 290, 50));

        SaveAndExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAndExitActionPerformed(evt);
            }
        });
        getContentPane().add(SaveAndExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 620, 210, 100));

        ShowBadges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowBadgesActionPerformed(evt);
            }
        });
        getContentPane().add(ShowBadges, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 620, 210, 100));

        ShowMyPokemon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowMyPokemonActionPerformed(evt);
            }
        });
        getContentPane().add(ShowMyPokemon, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 620, 210, 100));

        ShowMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowMapActionPerformed(evt);
            }
        });
        getContentPane().add(ShowMap, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 620, 210, 100));

        FightWildPokemon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FightWildPokemonActionPerformed(evt);
            }
        });
        getContentPane().add(FightWildPokemon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 600, 50));

        TalkToMom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TalkToMomActionPerformed(evt);
            }
        });
        getContentPane().add(TalkToMom, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 910, 60));

        MoveToButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MoveToButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(MoveToButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 310, 200, 100));

        MoveToButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MoveToButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(MoveToButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 310, 200, 100));

        MoveToButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MoveToButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(MoveToButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 200, 100));

        MoveToButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MoveToButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(MoveToButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 200, 100));
        getContentPane().add(MainMenuBackGround, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 860));

        Location.setText("jLabel2");
        getContentPane().add(Location, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 470, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MoveToButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MoveToButton4ActionPerformed
        try {
            String newLocation = Location4.getText();
            if (newLocation == null || newLocation.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid Choice. You must choose a city that you can travel to.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ConnectDatabase.updateLocation(saveNumber, newLocation);
            MainMenuPage mainMenuPageFrame = new MainMenuPage(saveNumber);
            this.dispose();
            mainMenuPageFrame.setVisible(true);
            mainMenuPageFrame.pack();
            mainMenuPageFrame.setLocationRelativeTo(null);
        } catch (FontFormatException ex) {
            Logger.getLogger(MainMenuPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_MoveToButton4ActionPerformed

    private void RivalRaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RivalRaceActionPerformed
        this.dispose();
        RivalRace rivalRace = new RivalRace(saveNumber);
        rivalRace.run();

        


    }//GEN-LAST:event_RivalRaceActionPerformed

    private void SafariZoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SafariZoneActionPerformed
        this.dispose();

        testSafariZoneGUI safariZone = new testSafariZoneGUI(saveNumber);
        safariZone.setVisible(true);
        safariZone.pack();
        safariZone.setLocationRelativeTo(null);
    }//GEN-LAST:event_SafariZoneActionPerformed

    private void TalkToMomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TalkToMomActionPerformed
       this.dispose();

        TalkToMomPage talkToMomPage = new TalkToMomPage(saveNumber);
        talkToMomPage.setVisible(true);
        talkToMomPage.pack();
        talkToMomPage.setLocationRelativeTo(null);
    }//GEN-LAST:event_TalkToMomActionPerformed

    private void ChallengeGymLeaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChallengeGymLeaderActionPerformed
        String location = ConnectDatabase.getLocation(saveNumber);
        this.dispose();

        ChallengeGymLeader challengeGymLeader = new ChallengeGymLeader(saveNumber,location);
        challengeGymLeader.setVisible(true);
        challengeGymLeader.pack();
        challengeGymLeader.setLocationRelativeTo(null);
    }//GEN-LAST:event_ChallengeGymLeaderActionPerformed

    private void FightWildPokemonLTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FightWildPokemonLTActionPerformed
        String location = ConnectDatabase.getLocation(saveNumber);
        this.dispose();

        FightWildPokemonPage fightWildPokemon = new FightWildPokemonPage(saveNumber,location);
        fightWildPokemon.setVisible(true);
        fightWildPokemon.pack();
        fightWildPokemon.setLocationRelativeTo(null);
    }//GEN-LAST:event_FightWildPokemonLTActionPerformed

    private void MoveToButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MoveToButton1ActionPerformed
        try {
            String newLocation = Location1.getText();
            ConnectDatabase.updateLocation(saveNumber, newLocation);

            MainMenuPage mainMenuPageFrame = new MainMenuPage(saveNumber);
            this.dispose();
            mainMenuPageFrame.setVisible(true);
            mainMenuPageFrame.pack();
            mainMenuPageFrame.setLocationRelativeTo(null);
        } catch (FontFormatException ex) {
            Logger.getLogger(MainMenuPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_MoveToButton1ActionPerformed

    private void MoveToButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MoveToButton2ActionPerformed
        try {
            String newLocation = Location2.getText();
            if (newLocation == null || newLocation.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid Choice. You must choose a city that you can travel to.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ConnectDatabase.updateLocation(saveNumber, newLocation);
            MainMenuPage mainMenuPageFrame = new MainMenuPage(saveNumber);
            this.dispose();
            mainMenuPageFrame.setVisible(true);
            mainMenuPageFrame.pack();
            mainMenuPageFrame.setLocationRelativeTo(null);
        } catch (FontFormatException ex) {
            Logger.getLogger(MainMenuPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_MoveToButton2ActionPerformed

    private void MoveToButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MoveToButton3ActionPerformed
        try {
            String newLocation = Location3.getText();
            if (newLocation == null || newLocation.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid Choice. You must choose a city that you can travel to.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ConnectDatabase.updateLocation(saveNumber, newLocation);
            MainMenuPage mainMenuPageFrame = new MainMenuPage(saveNumber);
            this.dispose();
            mainMenuPageFrame.setVisible(true);
            mainMenuPageFrame.pack();
            mainMenuPageFrame.setLocationRelativeTo(null);
        } catch (FontFormatException ex) {
            Logger.getLogger(MainMenuPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_MoveToButton3ActionPerformed

    private void PokeMazeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PokeMazeActionPerformed
        this.dispose();  // Close MainMenuPage when PokeMaze is opened

        MazeTile mazeTile = new MazeTile(saveNumber);
        mazeTile.setSaveNumber(saveNumber);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                testPokeMazeGUI pokeMazeGUI = new testPokeMazeGUI();
                pokeMazeGUI.setVisible(true);
                pokeMazeGUI.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        System.out.println("PokeMaze window is closing");  // Debug message
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                try {
                                    MainMenuPage mainMenuPageFrame = new MainMenuPage(saveNumber);
                                    mainMenuPageFrame.setVisible(true);
                                    mainMenuPageFrame.pack();
                                    mainMenuPageFrame.setLocationRelativeTo(null);
                                    System.out.println("MainMenuPage should be visible now");  // Debug message
                                } catch (FontFormatException ex) {
                                    Logger.getLogger(MainMenuPage.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                    }
                });
            }
        });
    }//GEN-LAST:event_PokeMazeActionPerformed

    private void SaveAndExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAndExitActionPerformed
        JOptionPane.showMessageDialog(null, "Your game have save to slot " + saveNumber + " successfully!", "Save", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }//GEN-LAST:event_SaveAndExitActionPerformed

    private void ShowMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowMapActionPerformed
        String location = ConnectDatabase.getLocation(saveNumber);
        Location.setText(location);

        this.dispose();

        ShowMapPage showMapPage = new ShowMapPage(saveNumber);
        showMapPage.setVisible(true);
        showMapPage.pack();
        showMapPage.setLocationRelativeTo(null);

    }//GEN-LAST:event_ShowMapActionPerformed

    private void ShowMyPokemonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowMyPokemonActionPerformed
        this.dispose();

        ShowMyPokemon showMyPokemon = new ShowMyPokemon(saveNumber);
        showMyPokemon.setVisible(true);
        showMyPokemon.pack();
        showMyPokemon.setLocationRelativeTo(null);
    }//GEN-LAST:event_ShowMyPokemonActionPerformed

    private void ShowBadgesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowBadgesActionPerformed
        this.dispose();

        ShowMyBadges showMyBadges = new ShowMyBadges(saveNumber);
        showMyBadges.setVisible(true);
        showMyBadges.pack();
        showMyBadges.setLocationRelativeTo(null);
    }//GEN-LAST:event_ShowBadgesActionPerformed

    private void FightWildPokemonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FightWildPokemonActionPerformed
        String location = ConnectDatabase.getLocation(saveNumber);
        this.dispose();

        FightWildPokemonPage fightWildPokemon = new FightWildPokemonPage(saveNumber,location);
        fightWildPokemon.setVisible(true);
        fightWildPokemon.pack();
        fightWildPokemon.setLocationRelativeTo(null);
    }//GEN-LAST:event_FightWildPokemonActionPerformed

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
            java.util.logging.Logger.getLogger(MainMenuPage.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenuPage.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenuPage.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenuPage.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new MainMenuPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ChallengeGymLeader;
    private javax.swing.JLabel CommonPokemon;
    private javax.swing.JButton FightWildPokemon;
    private javax.swing.JButton FightWildPokemonLT;
    private javax.swing.JLabel GymType;
    private javax.swing.JLabel Location;
    private javax.swing.JLabel Location1;
    private javax.swing.JLabel Location2;
    private javax.swing.JLabel Location3;
    private javax.swing.JLabel Location4;
    private javax.swing.JLabel MainMenuBackGround;
    private javax.swing.JButton MoveToButton1;
    private javax.swing.JButton MoveToButton2;
    private javax.swing.JButton MoveToButton3;
    private javax.swing.JButton MoveToButton4;
    private javax.swing.JLabel PlayerName;
    private javax.swing.JButton PokeMaze;
    private javax.swing.JLabel RealLocation;
    private javax.swing.JButton RivalRace;
    private javax.swing.JButton SafariZone;
    private javax.swing.JButton SaveAndExit;
    private javax.swing.JButton ShowBadges;
    private javax.swing.JButton ShowMap;
    private javax.swing.JButton ShowMyPokemon;
    private javax.swing.JButton TalkToMom;
    // End of variables declaration//GEN-END:variables
}
