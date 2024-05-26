/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pokemon;

import static com.mycompany.pokemon.ConnectDatabase.getConnection;
import java.awt.FontFormatException;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ChallengeGymLeader extends javax.swing.JFrame {

    static int saveNumber;
    static String location;

    private int numberOfPokemons;
    private int rowNum;
    private String[][] opponentInfo;
    private String[] playerInfo;
    private Queue<String> pokemonQueue;
    static int temp = 0;

    private String opponentName;
    private int opponentLevel;
    private int opponentHp;
    private int opponentMaxHp;
    private String opponentType;
    private String opponentMove1;
    private int opponentDmg1;
    private String opponentMove2;
    private int opponentDmg2;
    private String opponentMove3;
    private int opponentDmg3;
    private String opponentMove4;
    private int opponentDmg4;

    private String playerName;
    private String playerType;
    private int playerHp;
    private int playerMaxHp;
    private int playerXp;
    private int playerLevel;
    private String playerMove1;
    private int playerDmg1;
    private String playerMove2;
    private int playerDmg2;
    private String playerMove3;
    private int playerDmg3;
    private String playerMove4;
    private int playerDmg4;
    private String[] strongAgainstTypePlayer;
    private String[] weakAgainstTypePlayer;
    private String[] opponentTypeLeader;

    /**
     * Creates new form ChallengeGymLeader
     */
    public ChallengeGymLeader(int saveNumber, String location) {
        this.saveNumber = saveNumber;
        this.location = location;
        initComponents();

        JButton[] buttons = {
            PlayerMove1B,
            PlayerMove2B,
            PlayerMove3B,
            PlayerMove4B
        };

        setButtonInvisible(buttons);

        setBackground(location);

        setUpPokemonTeam(location);

        String selectSql1;
        String selectSql2;
        opponentInfo = new String[numberOfPokemons][13]; // Attributes of each Pokémon
        playerInfo = new String[16];

        if (numberOfPokemons > 0) {
            selectSql1 = "SELECT Name, Level, Hp, MaxHp, Type, Move1, Dmg1, Move2, Dmg2, Move3, Dmg3, Move4, Dmg4 FROM " + location.toLowerCase() + " WHERE id <= ?";
        } else {
            // Handle invalid location
            // You can throw an exception, print an error message, or handle it in any other appropriate way
            System.out.println("Invalid location specified: " + location);
            return;
        }

        selectSql2 = "SELECT pokemon_name, Type, Hp, maxHp, Xp, Level, Move1, dmg1, Move2, dmg2, Move3, dmg3, Move4, dmg, StrongAgainst, WeakAgainst FROM player_pokemon WHERE player_id = ?";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(selectSql1)) {
            preparedStatement.setInt(1, numberOfPokemons); // Set the parameter value
            ResultSet resultSet = preparedStatement.executeQuery();

            rowNum = 0; // Initialize row number to store Pokémon info

            // Loop through the result set
            while (resultSet.next() && rowNum < numberOfPokemons) {
                opponentInfo[rowNum][0] = resultSet.getString("Name"); // Pokémon name
                opponentInfo[rowNum][1] = String.valueOf(resultSet.getInt("Level")); // Level
                opponentInfo[rowNum][2] = String.valueOf(resultSet.getInt("Hp")); // HP
                opponentInfo[rowNum][3] = String.valueOf(resultSet.getInt("MaxHp")); // HP
                opponentInfo[rowNum][4] = resultSet.getString("Type"); // Type
                opponentInfo[rowNum][5] = resultSet.getString("Move1"); // Move1
                opponentInfo[rowNum][6] = String.valueOf(resultSet.getInt("Dmg1")); // Dmg1
                opponentInfo[rowNum][7] = resultSet.getString("Move2"); // Move2
                opponentInfo[rowNum][8] = String.valueOf(resultSet.getInt("Dmg2")); // Dmg2
                opponentInfo[rowNum][9] = resultSet.getString("Move3"); // Move3
                opponentInfo[rowNum][10] = String.valueOf(resultSet.getInt("Dmg3")); // Dmg3
                opponentInfo[rowNum][11] = resultSet.getString("Move4"); // Move4
                opponentInfo[rowNum][12] = String.valueOf(resultSet.getInt("Dmg4")); // Dmg4

                rowNum++; // Move to the next row
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(selectSql2)) {
            preparedStatement.setInt(1, saveNumber); // Set the parameter for id
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if there is a result
            if (resultSet.next()) {
                // Retrieve data from the result set and store it in the array
                playerInfo[0] = resultSet.getString("pokemon_name"); // Pokémon name
                playerInfo[1] = resultSet.getString("Type"); // Type
                playerInfo[2] = String.valueOf(resultSet.getInt("Hp")); // HP
                playerInfo[3] = String.valueOf(resultSet.getInt("maxHp")); // Max HP
                playerInfo[4] = String.valueOf(resultSet.getInt("Xp")); // Xp
                playerInfo[5] = String.valueOf(resultSet.getInt("Level")); // Level
                playerInfo[6] = resultSet.getString("Move1"); // Move1
                playerInfo[7] = String.valueOf(resultSet.getInt("dmg1")); // Dmg1
                playerInfo[8] = resultSet.getString("Move2"); // Move2
                playerInfo[9] = String.valueOf(resultSet.getInt("dmg2")); // Dmg2
                playerInfo[10] = resultSet.getString("Move3"); // Move3
                playerInfo[11] = String.valueOf(resultSet.getInt("dmg3")); // Dmg3
                playerInfo[12] = resultSet.getString("Move4"); // Move4
                playerInfo[13] = String.valueOf(resultSet.getInt("dmg")); // Dmg
                playerInfo[14] = resultSet.getString("StrongAgainst"); // StrongAgainst
                playerInfo[15] = resultSet.getString("WeakAgainst"); // WeakAgainst
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        pokemonQueue = new LinkedList<>();

        // Enqueue all Pokémon names from pokemonInfo
        for (String[] pokemon : opponentInfo) {
            pokemonQueue.add(pokemon[0]); // Assuming the Pokémon name is at index 0
        }

        setPlayerInfo(playerInfo);
        setOpponentInfo(opponentInfo);

        setUpPlayerInfo(playerInfo[0], playerInfo[2], playerInfo[1], playerInfo[5], playerInfo[6], playerInfo[8], playerInfo[10], playerInfo[12]);

        System.out.println(temp + "SAFASFAS");

        if (temp < opponentInfo.length) {
            System.out.println(temp + "Temp");
            System.out.println("FUCK THIS");
            OpponentPokemonName.setText(opponentInfo[temp][0]);
            OpponentHp.setText(opponentInfo[temp][2]);
            OpponentType.setText(opponentInfo[temp][4]);
            OpponentLevel.setText(opponentInfo[temp][1]);
            OpponentMove1.setText(opponentInfo[temp][5]);
            OpponentMove2.setText(opponentInfo[temp][7]);
            OpponentMove3.setText(opponentInfo[temp][9]);
            OpponentMove4.setText(opponentInfo[temp][11]);

        } else {
            System.out.println("No more opponents available in opponentInfo.");
        }
    }

    public void setButtonInvisible(JButton... buttons) {
        for (JButton button : buttons) {
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
        }
    }

    public void setBackground(String location) {
        ImageIcon backgroundImage = null;
        switch (location) {
            case "PewterCity":
                // Set the path to your image file here
                String pewterImagePath = "C:\\Users\\User\\Downloads\\GymLeader\\1.png"; // Add your image path here
                backgroundImage = new ImageIcon(pewterImagePath);
                break;
            case "ViridianCity":
                // Set the path to your image file here
                String viridianImagePath = "C:\\Users\\User\\Downloads\\GymLeader\\8.png"; // Add your image path here
                backgroundImage = new ImageIcon(viridianImagePath);
                break;
            case "VermilionCity":
                // Set the path to your image file here
                String vermilionImagePath = "C:\\Users\\User\\Downloads\\GymLeader\\3.png"; // Add your image path here
                backgroundImage = new ImageIcon(vermilionImagePath);
                break;
            case "SaffronCity":
                // Set the path to your image file here
                String saffronImagePath = "C:\\Users\\User\\Downloads\\GymLeader\\6.png"; // Add your image path here
                backgroundImage = new ImageIcon(saffronImagePath);
                break;
            case "FuchsiaCity":
                // Set the path to your image file here
                String fuchsiaImagePath = "C:\\Users\\User\\Downloads\\GymLeader\\5.png"; // Add your image path here
                backgroundImage = new ImageIcon(fuchsiaImagePath);
                break;
            case "CeladonCity":
                // Set the path to your image file here
                String celadonImagePath = "C:\\Users\\User\\Downloads\\GymLeader\\4.png"; // Add your image path here
                backgroundImage = new ImageIcon(celadonImagePath);
                break;
            case "CeruleanCity":
                // Set the path to your image file here
                String ceruleanImagePath = "C:\\Users\\User\\Downloads\\GymLeader\\2.png"; // Add your image path here
                backgroundImage = new ImageIcon(ceruleanImagePath);
                break;
            case "CinnabarIsland":
                // Set the path to your image file here
                String cinnabarImagePath = "C:\\Users\\User\\Downloads\\GymLeader\\7.png"; // Add your image path here
                backgroundImage = new ImageIcon(cinnabarImagePath);
                break;
            default:
                // Handle invalid location
                System.out.println("Invalid location specified: " + location);
                return;
        }
        Background.setIcon(backgroundImage);
    }

    public void setUpPokemonTeam(String location) {
        if (location == null) {
            numberOfPokemons = 0;
            return;
        }
        switch (location.toLowerCase()) {
            case "pewtercity":
                numberOfPokemons = 2;
                break;
            case "ceruleancity":
                numberOfPokemons = 2;
                break;
            case "vermilioncity":
                numberOfPokemons = 3;
                break;
            case "celadoncity":
                numberOfPokemons = 3;
                break;
            case "fuchsiacity":
                numberOfPokemons = 4;
                break;
            case "saffroncity":
                numberOfPokemons = 4;
                break;
            case "cinnabarisland":
                numberOfPokemons = 4;
                break;
            case "viridiancity":
                numberOfPokemons = 4;
                break;
            default:
                // Default to 0 if location is not recognized
                numberOfPokemons = 0;
                break;
        }
    }

    private void setOpponentInfo(String[][] opponentInfo) {
        opponentName = opponentInfo[0][0];
        opponentLevel = (opponentInfo[0][1] != null) ? Integer.parseInt(opponentInfo[0][1]) : 0;
        opponentHp = (opponentInfo[0][2] != null) ? Integer.parseInt(opponentInfo[0][2]) : 0;
        opponentMaxHp = (opponentInfo[0][3] != null) ? Integer.parseInt(opponentInfo[0][3]) : 0; // Added MaxHp
        opponentType = opponentInfo[0][4];
        opponentMove1 = opponentInfo[0][5];
        opponentDmg1 = (opponentInfo[0][6] != null) ? Integer.parseInt(opponentInfo[0][6]) : 0;
        opponentMove2 = opponentInfo[0][7];
        opponentDmg2 = (opponentInfo[0][8] != null) ? Integer.parseInt(opponentInfo[0][8]) : 0;
        opponentMove3 = opponentInfo[0][9];
        opponentDmg3 = (opponentInfo[0][10] != null) ? Integer.parseInt(opponentInfo[0][10]) : 0;
        opponentMove4 = opponentInfo[0][11];
        opponentDmg4 = (opponentInfo[0][12] != null) ? Integer.parseInt(opponentInfo[0][12]) : 0;
    }

    private void setPlayerInfo(String[] playerInfo) {
        playerName = playerInfo[0];
        playerType = playerInfo[1];
        playerHp = (playerInfo[2] != null) ? Integer.parseInt(playerInfo[2]) : 0;
        playerMaxHp = (playerInfo[3] != null) ? Integer.parseInt(playerInfo[3]) : 0;
        playerXp = (playerInfo[4] != null) ? Integer.parseInt(playerInfo[4]) : 0;
        playerLevel = (playerInfo[5] != null) ? Integer.parseInt(playerInfo[5]) : 0;
        playerMove1 = playerInfo[6];
        playerDmg1 = (playerInfo[7] != null) ? Integer.parseInt(playerInfo[7]) : 0;
        playerMove2 = playerInfo[8];
        playerDmg2 = (playerInfo[9] != null) ? Integer.parseInt(playerInfo[9]) : 0;
        playerMove3 = playerInfo[10];
        playerDmg3 = (playerInfo[11] != null) ? Integer.parseInt(playerInfo[11]) : 0;
        playerMove4 = playerInfo[12];
        playerDmg4 = (playerInfo[13] != null) ? Integer.parseInt(playerInfo[13]) : 0;

        // Split strong against and weak against types
        strongAgainstTypePlayer = (playerInfo[14].contains(" · ")) ? playerInfo[14].split(" · ") : new String[]{playerInfo[14]};
        weakAgainstTypePlayer = (playerInfo[15].contains(" · ")) ? playerInfo[15].split(" · ") : new String[]{playerInfo[15]};
    }

    public void setUpPlayerInfo(String playerName, String playerHp, String playerType, String playerLevel, String playerMove1, String playerMove2, String playerMove3, String playerMove4) {
        PlayerPokemonName.setText(playerName);
        PlayerName.setText(playerName);
        PlayerHp.setText(playerHp);
        PlayerType.setText(playerType);
        PlayerLevel.setText(playerLevel);
        PlayerMove1.setText(playerMove1);
        PlayerMove2.setText(playerMove2);
        PlayerMove3.setText(playerMove3);
        if (PlayerMove3.getText() == (null)) {
            PlayerMove3B.setEnabled(false);
        }
        PlayerMove4.setText(playerMove4);
        if (PlayerMove4.getText() == (null)) {
            PlayerMove4B.setEnabled(false);
        }
    }

    public void setUpOpponentInfo(String opponentName, String opponentHp, String opponentType, String opponentLevel, String opponentMove1, String opponentMove2, String opponentMove3, String opponentMove4) {
        OpponentPokemonName.setText(opponentName);
        OpponentHp.setText(opponentHp);
        OpponentType.setText(opponentType);
        OpponentLevel.setText(opponentLevel);
        OpponentMove1.setText(opponentMove1);
        OpponentMove2.setText(opponentMove2);
        OpponentMove3.setText(opponentMove3);
        OpponentMove4.setText(opponentMove4);
    }

    private void updatePlayerPokemonHp(int saveNumber, int playerMaxHp) {
        String updatePlayerHpSql = "UPDATE player_pokemon SET Hp = ? WHERE player_id = ?";

        try (Connection connection = getConnection()) {
            try (PreparedStatement updatePlayerHpStmt = connection.prepareStatement(updatePlayerHpSql)) {
                updatePlayerHpStmt.setInt(1, playerMaxHp);
                updatePlayerHpStmt.setInt(2, saveNumber);
                updatePlayerHpStmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }

    public void updateWildPokemonHp(String location, int idOpponent, int opponentMaxHp) {
        String updateSql = "UPDATE " + location.toLowerCase() + " SET Hp = ? WHERE id = ?";

        // Update the Hp value with the opponentMaxHp
        try (Connection connection = getConnection(); PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
            updateStmt.setInt(1, opponentMaxHp);
            updateStmt.setInt(2, idOpponent);
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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

        PlayerMove4B = new javax.swing.JButton();
        PlayerMove3B = new javax.swing.JButton();
        PlayerMove2B = new javax.swing.JButton();
        PlayerMove1B = new javax.swing.JButton();
        OpponentPokemonName = new javax.swing.JLabel();
        OpponentHp = new javax.swing.JLabel();
        OpponentType = new javax.swing.JLabel();
        OpponentLevel = new javax.swing.JLabel();
        OpponentMove1 = new javax.swing.JLabel();
        OpponentMove2 = new javax.swing.JLabel();
        OpponentMove3 = new javax.swing.JLabel();
        OpponentMove4 = new javax.swing.JLabel();
        PlayerMove1 = new javax.swing.JLabel();
        PlayerMove2 = new javax.swing.JLabel();
        PlayerMove3 = new javax.swing.JLabel();
        PlayerMove4 = new javax.swing.JLabel();
        PlayerHp = new javax.swing.JLabel();
        PlayerType = new javax.swing.JLabel();
        PlayerLevel = new javax.swing.JLabel();
        PlayerPokemonName = new javax.swing.JLabel();
        PlayerName = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1080, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PlayerMove4B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayerMove4BActionPerformed(evt);
            }
        });
        getContentPane().add(PlayerMove4B, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 590, 120, 60));

        PlayerMove3B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayerMove3BActionPerformed(evt);
            }
        });
        getContentPane().add(PlayerMove3B, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 590, 120, 60));

        PlayerMove2B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayerMove2BActionPerformed(evt);
            }
        });
        getContentPane().add(PlayerMove2B, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 530, 120, 60));

        PlayerMove1B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayerMove1BActionPerformed(evt);
            }
        });
        getContentPane().add(PlayerMove1B, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 530, 120, 60));

        OpponentPokemonName.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        OpponentPokemonName.setText("jLabel1");
        getContentPane().add(OpponentPokemonName, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 320, 140, 30));

        OpponentHp.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        OpponentHp.setText("jLabel1");
        getContentPane().add(OpponentHp, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 360, 140, 40));

        OpponentType.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        OpponentType.setText("jLabel1");
        getContentPane().add(OpponentType, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 400, 140, 40));

        OpponentLevel.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        OpponentLevel.setText("jLabel1");
        getContentPane().add(OpponentLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 450, 140, 30));

        OpponentMove1.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        OpponentMove1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpponentMove1.setText("jLabel1");
        getContentPane().add(OpponentMove1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 530, 120, 50));

        OpponentMove2.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        OpponentMove2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpponentMove2.setText("jLabel1");
        getContentPane().add(OpponentMove2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 530, 120, 50));

        OpponentMove3.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        OpponentMove3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpponentMove3.setText("jLabel1");
        getContentPane().add(OpponentMove3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 590, 120, 50));

        OpponentMove4.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        OpponentMove4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OpponentMove4.setText("jLabel1");
        getContentPane().add(OpponentMove4, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 590, 120, 50));

        PlayerMove1.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        PlayerMove1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlayerMove1.setText("jLabel1");
        getContentPane().add(PlayerMove1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 530, 120, 50));

        PlayerMove2.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        PlayerMove2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlayerMove2.setText("jLabel1");
        getContentPane().add(PlayerMove2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 530, 120, 60));

        PlayerMove3.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        PlayerMove3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlayerMove3.setText("jLabel1");
        getContentPane().add(PlayerMove3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 590, 120, 50));

        PlayerMove4.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        PlayerMove4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlayerMove4.setText("jLabel1");
        getContentPane().add(PlayerMove4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 590, 120, 50));

        PlayerHp.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        PlayerHp.setText("jLabel1");
        getContentPane().add(PlayerHp, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, 140, 40));

        PlayerType.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        PlayerType.setText("jLabel1");
        getContentPane().add(PlayerType, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, 140, 40));

        PlayerLevel.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        PlayerLevel.setText("jLabel1");
        getContentPane().add(PlayerLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, 140, 30));

        PlayerPokemonName.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        getContentPane().add(PlayerPokemonName, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, 140, 30));

        PlayerName.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        PlayerName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlayerName.setText("jLabel2");
        getContentPane().add(PlayerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 660, 260, 40));

        Background.setText("jLabel1");
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PlayerMove2BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayerMove2BActionPerformed
        int firstPokemon = 1;
        String selectDmgSql = "SELECT dmg2 FROM player_pokemon WHERE player_id = ?";
        String selectHpSql = "SELECT Hp FROM " + location.toLowerCase() + " WHERE id = ?";
        String updateHpSql = "UPDATE " + location.toLowerCase() + " SET Hp = ? WHERE id = ?";
        String selectPlayerHpSql = "SELECT Hp FROM player_pokemon WHERE player_id = ?";
        String selectOpponentDmg1Sql = "SELECT Dmg2 FROM " + location.toLowerCase() + " WHERE id = ?";
        String updatePlayerHpSql = "UPDATE player_pokemon SET Hp = ? WHERE player_id = ?";

        int dmg2 = 0;
        int currentHp = 0;
        int playerHp = 0;
        int opponentDmg2 = 0;
        try (Connection connection = getConnection()) {
            if (!pokemonQueue.isEmpty()) {
                // Fetch  from player_pokemon table
                try (PreparedStatement dmgStmt = connection.prepareStatement(selectDmgSql)) {
                    dmgStmt.setInt(1, saveNumber);
                    ResultSet dmgRs = dmgStmt.executeQuery();
                    if (dmgRs.next()) {
                        dmg2 = dmgRs.getInt("dmg2");
                    }
                }

                // Fetch current Hp from location table
                try (PreparedStatement hpStmt = connection.prepareStatement(selectHpSql)) {
                    hpStmt.setInt(1, firstPokemon);
                    ResultSet hpRs = hpStmt.executeQuery();
                    if (hpRs.next()) {
                        currentHp = hpRs.getInt("Hp");
                    }
                }

                // Calculate remaining Hp for the opponent
                int opponentRemainingHp = currentHp - dmg2;

                // Fetch current playerHp from player_pokemon table
                try (PreparedStatement playerHpStmt = connection.prepareStatement(selectPlayerHpSql)) {
                    playerHpStmt.setInt(1, saveNumber);
                    ResultSet playerHpRs = playerHpStmt.executeQuery();
                    if (playerHpRs.next()) {
                        playerHp = playerHpRs.getInt("Hp");
                    }
                }

                // Fetch Dmg1 from the opponent in location table
                try (PreparedStatement opponentDmg1Stmt = connection.prepareStatement(selectOpponentDmg1Sql)) {
                    opponentDmg1Stmt.setInt(1, firstPokemon);
                    ResultSet opponentDmg1Rs = opponentDmg1Stmt.executeQuery();
                    if (opponentDmg1Rs.next()) {
                        opponentDmg2 = opponentDmg1Rs.getInt("Dmg2");
                    }
                }

                // Calculate remaining Hp for the player
                int playerRemainingHp = playerHp - opponentDmg2;

                // Update player Hp in player_pokemon table
                try (PreparedStatement updatePlayerHpStmt = connection.prepareStatement(updatePlayerHpSql)) {
                    updatePlayerHpStmt.setInt(1, playerRemainingHp);
                    updatePlayerHpStmt.setInt(2, saveNumber);
                    updatePlayerHpStmt.executeUpdate();
                }

                // Update Hp in location table
                try (PreparedStatement updateHpStmt = connection.prepareStatement(updateHpSql)) {
                    updateHpStmt.setInt(1, opponentRemainingHp);
                    updateHpStmt.setInt(2, firstPokemon);
                    updateHpStmt.executeUpdate();
                }

                // Check if the player has fainted
                if (playerRemainingHp <= 0) {
                    JOptionPane.showMessageDialog(null, playerName + " fainted!\nSince you only have one pokemon, you can revive your pokemon and continue with the battle.", "Battle Lost", JOptionPane.INFORMATION_MESSAGE);
                    updatePlayerPokemonHp(saveNumber, playerMaxHp); // Call the new method to restore player HP
                    // Refresh ChallengeGymLeader page to continue battling
                    this.dispose();
                    ChallengeGymLeader challengeGymLeader = new ChallengeGymLeader(saveNumber, location);
                    challengeGymLeader.setVisible(true);
                    challengeGymLeader.pack();
                    challengeGymLeader.setLocationRelativeTo(null);
                    return; // End the method here
                }

                // Check if the battle ends (opponentRemainingHp is <= 0)
                if (opponentRemainingHp <= 0) {
                    // Poll the defeated opponent Pokémon
                    pokemonQueue.poll();

                    // Gain XP
                    ConnectDatabase.gainXp(saveNumber, (opponentLevel * 5));
                    // Update opponent's HP
                    updateWildPokemonHp(location, firstPokemon, opponentMaxHp);
                    // Restore player's HP
                    updatePlayerPokemonHp(saveNumber, playerMaxHp);
                    // Show battle message
                    String battleMessage = opponentInfo[temp][0] + " faints!\n" + playerName + " gained " + (opponentLevel * 5) + "xp.\n" + playerName + "[XP: " + ConnectDatabase.getXP(saveNumber) + "/" + ConnectDatabase.checkLevelLimit(ConnectDatabase.getLevel(saveNumber)) + "]";
                    JOptionPane.showMessageDialog(null, battleMessage, "Battle End", JOptionPane.INFORMATION_MESSAGE);
                    ConnectDatabase.checkXp(saveNumber, playerName);
                    ConnectDatabase.checkEvolve(saveNumber, playerName);

                    // Move to the next opponent Pokémon
                    temp++;
                    firstPokemon++; // Increment firstPokemon for the next iteration
                }

                // Check to ensure temp is within bounds
                if (temp < opponentInfo.length) {
                    // Refresh ChallengeGymLeader page to continue battling
                    this.dispose();
                    ChallengeGymLeader challengeGymLeader = new ChallengeGymLeader(saveNumber, location);
                    challengeGymLeader.setVisible(true);
                    challengeGymLeader.pack();
                    challengeGymLeader.setLocationRelativeTo(null);
                } else {
                    System.out.println("No more opponent Pokémon. Proceeding to update badges and return to the main menu.");
                    ConnectDatabase.updateBadges(saveNumber, ConnectDatabase.earnBadges(location));
                    try {
                        if (temp == numberOfPokemons) {
                            temp = 0;
                        }
                        MainMenuPage mainMenuPageFrame = new MainMenuPage(saveNumber);
                        mainMenuPageFrame.setVisible(true);
                        mainMenuPageFrame.pack();
                        mainMenuPageFrame.setLocationRelativeTo(null);
                    } catch (FontFormatException ex) {
                        Logger.getLogger(ShowMyPokemon.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // Close all related FightWildPokemon GUIs
                    Window[] windows = Window.getWindows();
                    for (Window window : windows) {
                        if (window instanceof ChallengeGymLeader) {
                            window.dispose();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An error occurred:");
            e.printStackTrace();
        }
    }//GEN-LAST:event_PlayerMove2BActionPerformed

    private void PlayerMove4BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayerMove4BActionPerformed
         if (!PlayerMove4.getText().equalsIgnoreCase(null)) {                                       
        int firstPokemon = 1;
        String selectDmgSql = "SELECT dmg FROM player_pokemon WHERE player_id = ?";
        String selectHpSql = "SELECT Hp FROM " + location.toLowerCase() + " WHERE id = ?";
        String updateHpSql = "UPDATE " + location.toLowerCase() + " SET Hp = ? WHERE id = ?";
        String selectPlayerHpSql = "SELECT Hp FROM player_pokemon WHERE player_id = ?";
        String selectOpponentDmg1Sql = "SELECT Dmg4 FROM " + location.toLowerCase() + " WHERE id = ?";
        String updatePlayerHpSql = "UPDATE player_pokemon SET Hp = ? WHERE player_id = ?";

        int dmg2 = 0;
        int currentHp = 0;
        int playerHp = 0;
        int opponentDmg2 = 0;
        try (Connection connection = getConnection()) {
            if (!pokemonQueue.isEmpty()) {
                // Fetch  from player_pokemon table
                try (PreparedStatement dmgStmt = connection.prepareStatement(selectDmgSql)) {
                    dmgStmt.setInt(1, saveNumber);
                    ResultSet dmgRs = dmgStmt.executeQuery();
                    if (dmgRs.next()) {
                        dmg2 = dmgRs.getInt("dmg");
                    }
                }

                // Fetch current Hp from location table
                try (PreparedStatement hpStmt = connection.prepareStatement(selectHpSql)) {
                    hpStmt.setInt(1, firstPokemon);
                    ResultSet hpRs = hpStmt.executeQuery();
                    if (hpRs.next()) {
                        currentHp = hpRs.getInt("Hp");
                    }
                }

                // Calculate remaining Hp for the opponent
                int opponentRemainingHp = currentHp - dmg2;

                // Fetch current playerHp from player_pokemon table
                try (PreparedStatement playerHpStmt = connection.prepareStatement(selectPlayerHpSql)) {
                    playerHpStmt.setInt(1, saveNumber);
                    ResultSet playerHpRs = playerHpStmt.executeQuery();
                    if (playerHpRs.next()) {
                        playerHp = playerHpRs.getInt("Hp");
                    }
                }

                // Fetch Dmg1 from the opponent in location table
                try (PreparedStatement opponentDmg1Stmt = connection.prepareStatement(selectOpponentDmg1Sql)) {
                    opponentDmg1Stmt.setInt(1, firstPokemon);
                    ResultSet opponentDmg1Rs = opponentDmg1Stmt.executeQuery();
                    if (opponentDmg1Rs.next()) {
                        opponentDmg2 = opponentDmg1Rs.getInt("Dmg4");
                    }
                }

                // Calculate remaining Hp for the player
                int playerRemainingHp = playerHp - opponentDmg2;

                // Update player Hp in player_pokemon table
                try (PreparedStatement updatePlayerHpStmt = connection.prepareStatement(updatePlayerHpSql)) {
                    updatePlayerHpStmt.setInt(1, playerRemainingHp);
                    updatePlayerHpStmt.setInt(2, saveNumber);
                    updatePlayerHpStmt.executeUpdate();
                }

                // Update Hp in location table
                try (PreparedStatement updateHpStmt = connection.prepareStatement(updateHpSql)) {
                    updateHpStmt.setInt(1, opponentRemainingHp);
                    updateHpStmt.setInt(2, firstPokemon);
                    updateHpStmt.executeUpdate();
                }

                // Check if the player has fainted
                if (playerRemainingHp <= 0) {
                    JOptionPane.showMessageDialog(null, playerName + " fainted!\nSince you only have one pokemon, you can revive your pokemon and continue with the battle.", "Battle Lost", JOptionPane.INFORMATION_MESSAGE);
                    updatePlayerPokemonHp(saveNumber, playerMaxHp); // Call the new method to restore player HP
                    // Refresh ChallengeGymLeader page to continue battling
                    this.dispose();
                    ChallengeGymLeader challengeGymLeader = new ChallengeGymLeader(saveNumber, location);
                    challengeGymLeader.setVisible(true);
                    challengeGymLeader.pack();
                    challengeGymLeader.setLocationRelativeTo(null);
                    return; // End the method here
                }

                // Check if the battle ends (opponentRemainingHp is <= 0)
                if (opponentRemainingHp <= 0) {
                    // Poll the defeated opponent Pokémon
                    pokemonQueue.poll();

                    // Gain XP
                    ConnectDatabase.gainXp(saveNumber, (opponentLevel * 5));
                    // Update opponent's HP
                    updateWildPokemonHp(location, firstPokemon, opponentMaxHp);
                    // Restore player's HP
                    updatePlayerPokemonHp(saveNumber, playerMaxHp);
                    // Show battle message
                    String battleMessage = opponentInfo[temp][0] + " faints!\n" + playerName + " gained " + (opponentLevel * 5) + "xp.\n" + playerName + "[XP: " + ConnectDatabase.getXP(saveNumber) + "/" + ConnectDatabase.checkLevelLimit(ConnectDatabase.getLevel(saveNumber)) + "]";
                    JOptionPane.showMessageDialog(null, battleMessage, "Battle End", JOptionPane.INFORMATION_MESSAGE);
                    ConnectDatabase.checkXp(saveNumber, playerName);
                    ConnectDatabase.checkEvolve(saveNumber, playerName);

                    // Move to the next opponent Pokémon
                    temp++;
                    firstPokemon++; // Increment firstPokemon for the next iteration
                }

                // Check to ensure temp is within bounds
                if (temp < opponentInfo.length) {
                    // Refresh ChallengeGymLeader page to continue battling
                    this.dispose();
                    ChallengeGymLeader challengeGymLeader = new ChallengeGymLeader(saveNumber, location);
                    challengeGymLeader.setVisible(true);
                    challengeGymLeader.pack();
                    challengeGymLeader.setLocationRelativeTo(null);
                } else {
                    System.out.println("No more opponent Pokémon. Proceeding to update badges and return to the main menu.");
                    ConnectDatabase.updateBadges(saveNumber, ConnectDatabase.earnBadges(location));
                    try {
                        if (temp == numberOfPokemons) {
                            temp = 0;
                        }
                        MainMenuPage mainMenuPageFrame = new MainMenuPage(saveNumber);
                        mainMenuPageFrame.setVisible(true);
                        mainMenuPageFrame.pack();
                        mainMenuPageFrame.setLocationRelativeTo(null);
                    } catch (FontFormatException ex) {
                        Logger.getLogger(ShowMyPokemon.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // Close all related FightWildPokemon GUIs
                    Window[] windows = Window.getWindows();
                    for (Window window : windows) {
                        if (window instanceof ChallengeGymLeader) {
                            window.dispose();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An error occurred:");
            e.printStackTrace();
        }
         }
    }//GEN-LAST:event_PlayerMove4BActionPerformed

    private void PlayerMove1BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayerMove1BActionPerformed
         int firstPokemon = 1;
        String selectDmgSql = "SELECT dmg1 FROM player_pokemon WHERE player_id = ?";
        String selectHpSql = "SELECT Hp FROM " + location.toLowerCase() + " WHERE id = ?";
        String updateHpSql = "UPDATE " + location.toLowerCase() + " SET Hp = ? WHERE id = ?";
        String selectPlayerHpSql = "SELECT Hp FROM player_pokemon WHERE player_id = ?";
        String selectOpponentDmg1Sql = "SELECT Dmg1 FROM " + location.toLowerCase() + " WHERE id = ?";
        String updatePlayerHpSql = "UPDATE player_pokemon SET Hp = ? WHERE player_id = ?";

        int dmg2 = 0;
        int currentHp = 0;
        int playerHp = 0;
        int opponentDmg2 = 0;
        try (Connection connection = getConnection()) {
            if (!pokemonQueue.isEmpty()) {
                // Fetch  from player_pokemon table
                try (PreparedStatement dmgStmt = connection.prepareStatement(selectDmgSql)) {
                    dmgStmt.setInt(1, saveNumber);
                    ResultSet dmgRs = dmgStmt.executeQuery();
                    if (dmgRs.next()) {
                        dmg2 = dmgRs.getInt("dmg1");
                    }
                }

                // Fetch current Hp from location table
                try (PreparedStatement hpStmt = connection.prepareStatement(selectHpSql)) {
                    hpStmt.setInt(1, firstPokemon);
                    ResultSet hpRs = hpStmt.executeQuery();
                    if (hpRs.next()) {
                        currentHp = hpRs.getInt("Hp");
                    }
                }

                // Calculate remaining Hp for the opponent
                int opponentRemainingHp = currentHp - dmg2;

                // Fetch current playerHp from player_pokemon table
                try (PreparedStatement playerHpStmt = connection.prepareStatement(selectPlayerHpSql)) {
                    playerHpStmt.setInt(1, saveNumber);
                    ResultSet playerHpRs = playerHpStmt.executeQuery();
                    if (playerHpRs.next()) {
                        playerHp = playerHpRs.getInt("Hp");
                    }
                }

                // Fetch Dmg1 from the opponent in location table
                try (PreparedStatement opponentDmg1Stmt = connection.prepareStatement(selectOpponentDmg1Sql)) {
                    opponentDmg1Stmt.setInt(1, firstPokemon);
                    ResultSet opponentDmg1Rs = opponentDmg1Stmt.executeQuery();
                    if (opponentDmg1Rs.next()) {
                        opponentDmg2 = opponentDmg1Rs.getInt("Dmg1");
                    }
                }

                // Calculate remaining Hp for the player
                int playerRemainingHp = playerHp - opponentDmg2;

                // Update player Hp in player_pokemon table
                try (PreparedStatement updatePlayerHpStmt = connection.prepareStatement(updatePlayerHpSql)) {
                    updatePlayerHpStmt.setInt(1, playerRemainingHp);
                    updatePlayerHpStmt.setInt(2, saveNumber);
                    updatePlayerHpStmt.executeUpdate();
                }

                // Update Hp in location table
                try (PreparedStatement updateHpStmt = connection.prepareStatement(updateHpSql)) {
                    updateHpStmt.setInt(1, opponentRemainingHp);
                    updateHpStmt.setInt(2, firstPokemon);
                    updateHpStmt.executeUpdate();
                }

                // Check if the player has fainted
                if (playerRemainingHp <= 0) {
                    JOptionPane.showMessageDialog(null, playerName + " fainted!\nSince you only have one pokemon, you can revive your pokemon and continue with the battle.", "Battle Lost", JOptionPane.INFORMATION_MESSAGE);
                    updatePlayerPokemonHp(saveNumber, playerMaxHp); // Call the new method to restore player HP
                    // Refresh ChallengeGymLeader page to continue battling
                    this.dispose();
                    ChallengeGymLeader challengeGymLeader = new ChallengeGymLeader(saveNumber, location);
                    challengeGymLeader.setVisible(true);
                    challengeGymLeader.pack();
                    challengeGymLeader.setLocationRelativeTo(null);
                    return; // End the method here
                }

                // Check if the battle ends (opponentRemainingHp is <= 0)
                if (opponentRemainingHp <= 0) {
                    // Poll the defeated opponent Pokémon
                    pokemonQueue.poll();

                    // Gain XP
                    ConnectDatabase.gainXp(saveNumber, (opponentLevel * 5));
                    // Update opponent's HP
                    updateWildPokemonHp(location, firstPokemon, opponentMaxHp);
                    // Restore player's HP
                    updatePlayerPokemonHp(saveNumber, playerMaxHp);
                    // Show battle message
                    String battleMessage = opponentInfo[temp][0] + " faints!\n" + playerName + " gained " + (opponentLevel * 5) + "xp.\n" + playerName + "[XP: " + ConnectDatabase.getXP(saveNumber) + "/" + ConnectDatabase.checkLevelLimit(ConnectDatabase.getLevel(saveNumber)) + "]";
                    JOptionPane.showMessageDialog(null, battleMessage, "Battle End", JOptionPane.INFORMATION_MESSAGE);
                    ConnectDatabase.checkXp(saveNumber, playerName);
                    ConnectDatabase.checkEvolve(saveNumber, playerName);

                    // Move to the next opponent Pokémon
                    temp++;
                    firstPokemon++; // Increment firstPokemon for the next iteration
                }

                // Check to ensure temp is within bounds
                if (temp < opponentInfo.length) {
                    // Refresh ChallengeGymLeader page to continue battling
                    this.dispose();
                    ChallengeGymLeader challengeGymLeader = new ChallengeGymLeader(saveNumber, location);
                    challengeGymLeader.setVisible(true);
                    challengeGymLeader.pack();
                    challengeGymLeader.setLocationRelativeTo(null);
                } else {
                    System.out.println("No more opponent Pokémon. Proceeding to update badges and return to the main menu.");
                    ConnectDatabase.updateBadges(saveNumber, ConnectDatabase.earnBadges(location));
                    try {
                        if (temp == numberOfPokemons) {
                            temp = 0;
                        }
                        MainMenuPage mainMenuPageFrame = new MainMenuPage(saveNumber);
                        mainMenuPageFrame.setVisible(true);
                        mainMenuPageFrame.pack();
                        mainMenuPageFrame.setLocationRelativeTo(null);
                    } catch (FontFormatException ex) {
                        Logger.getLogger(ShowMyPokemon.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // Close all related FightWildPokemon GUIs
                    Window[] windows = Window.getWindows();
                    for (Window window : windows) {
                        if (window instanceof ChallengeGymLeader) {
                            window.dispose();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An error occurred:");
            e.printStackTrace();
        } 
    }//GEN-LAST:event_PlayerMove1BActionPerformed

    private void PlayerMove3BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayerMove3BActionPerformed
        if (!PlayerMove4.getText().equalsIgnoreCase(null)) {                                       
        int firstPokemon = 1;
        String selectDmgSql = "SELECT dmg3 FROM player_pokemon WHERE player_id = ?";
        String selectHpSql = "SELECT Hp FROM " + location.toLowerCase() + " WHERE id = ?";
        String updateHpSql = "UPDATE " + location.toLowerCase() + " SET Hp = ? WHERE id = ?";
        String selectPlayerHpSql = "SELECT Hp FROM player_pokemon WHERE player_id = ?";
        String selectOpponentDmg1Sql = "SELECT Dmg3 FROM " + location.toLowerCase() + " WHERE id = ?";
        String updatePlayerHpSql = "UPDATE player_pokemon SET Hp = ? WHERE player_id = ?";

        int dmg2 = 0;
        int currentHp = 0;
        int playerHp = 0;
        int opponentDmg2 = 0;
        try (Connection connection = getConnection()) {
            if (!pokemonQueue.isEmpty()) {
                // Fetch  from player_pokemon table
                try (PreparedStatement dmgStmt = connection.prepareStatement(selectDmgSql)) {
                    dmgStmt.setInt(1, saveNumber);
                    ResultSet dmgRs = dmgStmt.executeQuery();
                    if (dmgRs.next()) {
                        dmg2 = dmgRs.getInt("dmg3");
                    }
                }

                // Fetch current Hp from location table
                try (PreparedStatement hpStmt = connection.prepareStatement(selectHpSql)) {
                    hpStmt.setInt(1, firstPokemon);
                    ResultSet hpRs = hpStmt.executeQuery();
                    if (hpRs.next()) {
                        currentHp = hpRs.getInt("Hp");
                    }
                }

                // Calculate remaining Hp for the opponent
                int opponentRemainingHp = currentHp - dmg2;

                // Fetch current playerHp from player_pokemon table
                try (PreparedStatement playerHpStmt = connection.prepareStatement(selectPlayerHpSql)) {
                    playerHpStmt.setInt(1, saveNumber);
                    ResultSet playerHpRs = playerHpStmt.executeQuery();
                    if (playerHpRs.next()) {
                        playerHp = playerHpRs.getInt("Hp");
                    }
                }

                // Fetch Dmg1 from the opponent in location table
                try (PreparedStatement opponentDmg1Stmt = connection.prepareStatement(selectOpponentDmg1Sql)) {
                    opponentDmg1Stmt.setInt(1, firstPokemon);
                    ResultSet opponentDmg1Rs = opponentDmg1Stmt.executeQuery();
                    if (opponentDmg1Rs.next()) {
                        opponentDmg2 = opponentDmg1Rs.getInt("Dmg3");
                    }
                }

                // Calculate remaining Hp for the player
                int playerRemainingHp = playerHp - opponentDmg2;

                // Update player Hp in player_pokemon table
                try (PreparedStatement updatePlayerHpStmt = connection.prepareStatement(updatePlayerHpSql)) {
                    updatePlayerHpStmt.setInt(1, playerRemainingHp);
                    updatePlayerHpStmt.setInt(2, saveNumber);
                    updatePlayerHpStmt.executeUpdate();
                }

                // Update Hp in location table
                try (PreparedStatement updateHpStmt = connection.prepareStatement(updateHpSql)) {
                    updateHpStmt.setInt(1, opponentRemainingHp);
                    updateHpStmt.setInt(2, firstPokemon);
                    updateHpStmt.executeUpdate();
                }

                // Check if the player has fainted
                if (playerRemainingHp <= 0) {
                    JOptionPane.showMessageDialog(null, playerName + " fainted!\nSince you only have one pokemon, you can revive your pokemon and continue with the battle.", "Battle Lost", JOptionPane.INFORMATION_MESSAGE);
                    updatePlayerPokemonHp(saveNumber, playerMaxHp); // Call the new method to restore player HP
                    // Refresh ChallengeGymLeader page to continue battling
                    this.dispose();
                    ChallengeGymLeader challengeGymLeader = new ChallengeGymLeader(saveNumber, location);
                    challengeGymLeader.setVisible(true);
                    challengeGymLeader.pack();
                    challengeGymLeader.setLocationRelativeTo(null);
                    return; // End the method here
                }

                // Check if the battle ends (opponentRemainingHp is <= 0)
                if (opponentRemainingHp <= 0) {
                    // Poll the defeated opponent Pokémon
                    pokemonQueue.poll();

                    // Gain XP
                    ConnectDatabase.gainXp(saveNumber, (opponentLevel * 5));
                    // Update opponent's HP
                    updateWildPokemonHp(location, firstPokemon, opponentMaxHp);
                    // Restore player's HP
                    updatePlayerPokemonHp(saveNumber, playerMaxHp);
                    // Show battle message
                    String battleMessage = opponentInfo[temp][0] + " faints!\n" + playerName + " gained " + (opponentLevel * 5) + "xp.\n" + playerName + "[XP: " + ConnectDatabase.getXP(saveNumber) + "/" + ConnectDatabase.checkLevelLimit(ConnectDatabase.getLevel(saveNumber)) + "]";
                    JOptionPane.showMessageDialog(null, battleMessage, "Battle End", JOptionPane.INFORMATION_MESSAGE);
                    ConnectDatabase.checkXp(saveNumber, playerName);
                    ConnectDatabase.checkEvolve(saveNumber, playerName);

                    // Move to the next opponent Pokémon
                    temp++;
                    firstPokemon++; // Increment firstPokemon for the next iteration
                }

                // Check to ensure temp is within bounds
                if (temp < opponentInfo.length) {
                    // Refresh ChallengeGymLeader page to continue battling
                    this.dispose();
                    ChallengeGymLeader challengeGymLeader = new ChallengeGymLeader(saveNumber, location);
                    challengeGymLeader.setVisible(true);
                    challengeGymLeader.pack();
                    challengeGymLeader.setLocationRelativeTo(null);
                } else {
                    System.out.println("No more opponent Pokémon. Proceeding to update badges and return to the main menu.");
                    ConnectDatabase.updateBadges(saveNumber, ConnectDatabase.earnBadges(location));
                    try {
                        if (temp == numberOfPokemons) {
                            temp = 0;
                        }
                        MainMenuPage mainMenuPageFrame = new MainMenuPage(saveNumber);
                        mainMenuPageFrame.setVisible(true);
                        mainMenuPageFrame.pack();
                        mainMenuPageFrame.setLocationRelativeTo(null);
                    } catch (FontFormatException ex) {
                        Logger.getLogger(ShowMyPokemon.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // Close all related FightWildPokemon GUIs
                    Window[] windows = Window.getWindows();
                    for (Window window : windows) {
                        if (window instanceof ChallengeGymLeader) {
                            window.dispose();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An error occurred:");
            e.printStackTrace();
        }
         }
    }//GEN-LAST:event_PlayerMove3BActionPerformed

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
            java.util.logging.Logger.getLogger(ChallengeGymLeader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChallengeGymLeader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChallengeGymLeader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChallengeGymLeader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChallengeGymLeader(saveNumber, location).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel OpponentHp;
    private javax.swing.JLabel OpponentLevel;
    private javax.swing.JLabel OpponentMove1;
    private javax.swing.JLabel OpponentMove2;
    private javax.swing.JLabel OpponentMove3;
    private javax.swing.JLabel OpponentMove4;
    private javax.swing.JLabel OpponentPokemonName;
    private javax.swing.JLabel OpponentType;
    private javax.swing.JLabel PlayerHp;
    private javax.swing.JLabel PlayerLevel;
    private javax.swing.JLabel PlayerMove1;
    private javax.swing.JButton PlayerMove1B;
    private javax.swing.JLabel PlayerMove2;
    private javax.swing.JButton PlayerMove2B;
    private javax.swing.JLabel PlayerMove3;
    private javax.swing.JButton PlayerMove3B;
    private javax.swing.JLabel PlayerMove4;
    private javax.swing.JButton PlayerMove4B;
    private javax.swing.JLabel PlayerName;
    private javax.swing.JLabel PlayerPokemonName;
    private javax.swing.JLabel PlayerType;
    // End of variables declaration//GEN-END:variables
}
