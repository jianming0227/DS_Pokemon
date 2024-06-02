/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pokemon;

import static com.mycompany.pokemon.ConnectDatabase.gainXp;
import static com.mycompany.pokemon.ConnectDatabase.getConnection;
import static com.mycompany.pokemon.ShowMyPokemon.saveNumber;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author User
 */
public class FightWildPokemonPage extends javax.swing.JFrame {

    private String opponentName;
    private String[] opponentInfo;
    private String[] playerInfo;
    private int rowNumber;
    
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

    static int saveNumber;
    static String location;
    static int idOpponent = -1;
    int turn = 1;
    private int maxHp = -1;
    int index = 1;

    /**
     * Creates new form FightWildPokemonPage
     */
    public FightWildPokemonPage(int saveNumber, String location) {
        this.saveNumber = saveNumber;
        this.location = location;
        initComponents();

        Move1Button.setOpaque(false);
        Move1Button.setContentAreaFilled(false);
        Move1Button.setBorderPainted(false);

        Move2Button.setOpaque(false);
        Move2Button.setContentAreaFilled(false);
        Move2Button.setBorderPainted(false);

        Move3Button.setOpaque(false);
        Move3Button.setContentAreaFilled(false);
        Move3Button.setBorderPainted(false);

        Move4Button.setOpaque(false);
        Move4Button.setContentAreaFilled(false);
        Move4Button.setBorderPainted(false);

        ImageIcon arrowImage = new ImageIcon("C:\\Users\\User\\Downloads\\Arrow.png");
        Arrow.setIcon(arrowImage);

        Random random = new Random();
        setupOpponent(location);

        String selectSql1;
        String selectSql2;
        opponentInfo = new String[13]; // Attributes of each Pokémon
        playerInfo = new String[16];

        if (idOpponent > 0) {
            selectSql1 = "SELECT Name, Level, Hp, MaxHp, Type, Move1, Dmg1, Move2, Dmg2, Move3, Dmg3, Move4, Dmg4 FROM " + location.toLowerCase() + " WHERE id <= ?";
        } else {
            // Handle invalid location
            // You can throw an exception, print an error message, or handle it in any other appropriate way
            System.out.println("Invalid location specified: " + location);
            return;
        }

        selectSql2 = "SELECT pokemon_name, Type, Hp, maxHp, Xp, Level, Move1, dmg1, Move2, dmg2, Move3, dmg3, Move4, dmg, StrongAgainst, WeakAgainst FROM player_pokemon WHERE player_id = ?";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(selectSql1)) {
            preparedStatement.setInt(1, idOpponent); // Set the parameter value
            ResultSet resultSet = preparedStatement.executeQuery();

             // Initialize row number to store Pokémon info

            // Loop through the result set
            while (resultSet.next() && idOpponent > 0) {
                opponentInfo[0] = resultSet.getString("Name"); // Pokémon name
                opponentInfo[1] = String.valueOf(resultSet.getInt("Level")); // Level
                opponentInfo[2] = String.valueOf(resultSet.getInt("Hp")); // HP
                opponentInfo[3] = String.valueOf(resultSet.getInt("MaxHp")); // HP
                opponentInfo[4] = resultSet.getString("Type"); // Type
                opponentInfo[5] = resultSet.getString("Move1"); // Move1
                opponentInfo[6] = String.valueOf(resultSet.getInt("Dmg1")); // Dmg1
                opponentInfo[7] = resultSet.getString("Move2"); // Move2
                opponentInfo[8] = String.valueOf(resultSet.getInt("Dmg2")); // Dmg2
                opponentInfo[9] = resultSet.getString("Move3"); // Move3
                opponentInfo[10] = String.valueOf(resultSet.getInt("Dmg3")); // Dmg3
                opponentInfo[11] = resultSet.getString("Move4"); // Move4
                opponentInfo[12] = String.valueOf(resultSet.getInt("Dmg4")); // Dmg4

                // Move to the next row
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

        setOpponentInfo(opponentInfo);
        setPlayerInfo(playerInfo);


        String[] strongAgainstTypePlayer;
        String[] weakAgainstTypePlayer;
        String[] typeLeader;

        if (playerInfo[14].contains(" · ")) {
            strongAgainstTypePlayer = playerInfo[14].split(" · ");
        } else {
            strongAgainstTypePlayer = new String[]{playerInfo[14]};
        }

        if (playerInfo[15].contains(" · ")) {
            weakAgainstTypePlayer = playerInfo[15].split(" · ");
        } else {
            weakAgainstTypePlayer = new String[]{playerInfo[15]};
        }
        if (opponentInfo[3].contains(" · ")) {
            typeLeader = opponentInfo[3].split(" · ");
        } else {
            // If " · " is not found, just use the original string
            typeLeader = new String[]{opponentInfo[3]};
        }

        boolean isSuperEffective;
        boolean isNotVeryEffective;
        for (String strongType : strongAgainstTypePlayer) {
            for (String leaderType : typeLeader) {
                if (strongType.equalsIgnoreCase(leaderType)) {
                    isSuperEffective = true;
                    // No need for the break here
                }
            }
        }
        for (String weakType : weakAgainstTypePlayer) {
            for (String leaderType : typeLeader) {
                if (weakType.equalsIgnoreCase(leaderType)) {
                    isNotVeryEffective = true;
                    // No need for the break here
                }
            }
        }

        HPOpponent.setText(opponentInfo[2]);
        LevelOpponent.setText(opponentInfo[1]);

        HPPlayer.setText(playerInfo[2]);
        NamePlayer.setText(playerInfo[0]);
        LevelPlayer.setText(playerInfo[5]);
        Move1.setText(playerInfo[6]);
        Move2.setText(playerInfo[8]);
        Move3.setText(playerInfo[10]);
        if (Move3.getText() == (null)) {
            Move3Button.setEnabled(false);
        }
        Move4.setText(playerInfo[12]);
        if (Move4.getText() == (null)) {
            Move4Button.setEnabled(false);
        }

        ImageIcon PokemonImage = null;

        switch (NamePlayer.getText()) {
            case "Bulbasaur":
                PokemonImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\ShowMyPokemon\\Bulbasaur.png");
                Image resizedImage1 = PokemonImage.getImage().getScaledInstance(283, 189, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon1 = new ImageIcon(resizedImage1);
                PokemonImg.setIcon(resizedIcon1);
                break;
            case "Ivysaur":
                PokemonImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Untitled design\\1.png");
                Image resizedImage2 = PokemonImage.getImage().getScaledInstance(283, 189, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
                PokemonImg.setIcon(resizedIcon2);
                break;
            case "Venusaur":
                PokemonImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Untitled design\\2.png");
                Image resizedImage3 = PokemonImage.getImage().getScaledInstance(283, 189, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon3 = new ImageIcon(resizedImage3);
                PokemonImg.setIcon(resizedIcon3);
                break;
            case "Charmander":
                PokemonImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\ShowMyPokemon\\Charmander.png");
                Image resizedImage4 = PokemonImage.getImage().getScaledInstance(283, 189, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon4 = new ImageIcon(resizedImage4);
                PokemonImg.setIcon(resizedIcon4);
                break;
            case "Charmeleon":
                PokemonImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Untitled design\\3.png");
                Image resizedImage5 = PokemonImage.getImage().getScaledInstance(283, 189, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon5 = new ImageIcon(resizedImage5);
                PokemonImg.setIcon(resizedIcon5);
                break;
            case "Charizard":
                PokemonImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Untitled design\\4.png");
                Image resizedImage6 = PokemonImage.getImage().getScaledInstance(283, 189, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon6 = new ImageIcon(resizedImage6);
                PokemonImg.setIcon(resizedIcon6);
                break;
            case "Squirtle":
                PokemonImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\ShowMyPokemon\\Squirtle.png");
                Image resizedImage7 = PokemonImage.getImage().getScaledInstance(283, 189, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon7 = new ImageIcon(resizedImage7);
                PokemonImg.setIcon(resizedIcon7);
                break;
            case "Wartortle":
                PokemonImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Untitled design\\5.png");
                Image resizedImage8 = PokemonImage.getImage().getScaledInstance(283, 189, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon8 = new ImageIcon(resizedImage8);
                PokemonImg.setIcon(resizedIcon8);
                break;
            case "Blastoise":
                PokemonImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Untitled design\\6.png");
                Image resizedImage9 = PokemonImage.getImage().getScaledInstance(283, 189, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon9 = new ImageIcon(resizedImage9);
                PokemonImg.setIcon(resizedIcon9);
                break;
            default:
                // Handle other Pokémon
                break;
        }

        ImageIcon backgroundImage = null;
        switch (opponentInfo[0]) {
            case "Doduo":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\1.png");
                Background.setIcon(backgroundImage);
                break;
            case "Rattata":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\2.png");
                Background.setIcon(backgroundImage);
                break;
            case "Ponyta":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\3.png");
                Background.setIcon(backgroundImage);
                break;
            case "Spearow":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\4.png");
                Background.setIcon(backgroundImage);
                break;
            case "Dodrio":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\5.png");
                Background.setIcon(backgroundImage);
                break;
            case "Raticate":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\6.png");
                Background.setIcon(backgroundImage);
                break;
            case "Ekans":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\7.png");
                Background.setIcon(backgroundImage);
                break;
            case "Tangela":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\8.png");
                Background.setIcon(backgroundImage);
                break;
            case "Staryu":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\9.png");
                Background.setIcon(backgroundImage);
                break;
            case "Goldeen":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\10.png");
                Background.setIcon(backgroundImage);
                break;
            case "Poliwag":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\11.png");
                Background.setIcon(backgroundImage);
                break;
            case "Tentacool":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\12.png");
                Background.setIcon(backgroundImage);
                break;
            case "Oddish":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\13.png");
                Background.setIcon(backgroundImage);
                break;
            case "Venonat":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\14.png");
                Background.setIcon(backgroundImage);
                break;
            case "Gloom":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\15.png");
                Background.setIcon(backgroundImage);
                break;
            case "Weepinbell":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\16.png");
                Background.setIcon(backgroundImage);
                break;
            case "Farfetchd":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\17.png");
                Background.setIcon(backgroundImage);
                break;
            case "Ditto":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\18.png");
                Background.setIcon(backgroundImage);
                break;
            case "Pidgey":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\19.png");
                Background.setIcon(backgroundImage);
                break;
            case "Pidgeotto":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\20.png");
                Background.setIcon(backgroundImage);
                break;
            case "Vulpix":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\21.png");
                Background.setIcon(backgroundImage);
                break;
            case "Jigglypuff":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\22.png");
                Background.setIcon(backgroundImage);
                break;
            case "Voltorb":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\23.png");
                Background.setIcon(backgroundImage);
                break;
            case "Machop":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\24.png");
                Background.setIcon(backgroundImage);
                break;
            case "Magikarp":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\25.png");
                Background.setIcon(backgroundImage);
                break;
            case "Bellsprout":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\26.png");
                Background.setIcon(backgroundImage);
                break;
            case "Sandshrew":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\27.png");
                Background.setIcon(backgroundImage);
                break;
            case "Mankey":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\28.png");
                Background.setIcon(backgroundImage);
                break;
            case "Abra":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\29.png");
                Background.setIcon(backgroundImage);
                break;
            case "Meowth":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\30.png");
                Background.setIcon(backgroundImage);
                break;
            case "Growlithe":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\31.png");
                Background.setIcon(backgroundImage);
                break;
            case "Kadabra":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\32.png");
                Background.setIcon(backgroundImage);
                break;
            case "Caterpie":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\33.png");
                Background.setIcon(backgroundImage);
                break;
            case "Weedle":
                backgroundImage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\Fight Wild Pokemon\\34.png");
                Background.setIcon(backgroundImage);
                break;
            default:
                // Handle the case when the Pokémon is not one of the listed ones
                break;

        }

    }

    public void setupOpponent(String location) {
        Random random = new Random();

        // Check if idOpponent has been set already
        if (idOpponent == -1) {
            switch (location.toLowerCase()) {
                case "pewtercity":
                    idOpponent = random.nextInt(5) + 3; // Generates a random number between 3 and 7 (inclusive)
                    break;
                case "ceruleancity":
                    idOpponent = random.nextInt(4) + 3; // Generates a random number between 3 and 6 (inclusive)
                    break;
                case "vermilioncity":
                case "celadoncity":
                    idOpponent = random.nextInt(5) + 4; // Generates a random number between 4 and 8 (inclusive)
                    break;
                case "fuchsiacity":
                case "saffroncity":
                    idOpponent = random.nextInt(6) + 5; // Generates a random number between 5 and 10 (inclusive)
                    break;
                case "cinnabarisland":
                case "viridiancity":
                    idOpponent = random.nextInt(4) + 5; // Generates a random number between 5 and 8 (inclusive)
                    break;
                case "pallettown":
                    idOpponent = random.nextInt(2) + 1; // Generates a random number between 1 and 2 (inclusive)
                    break;
                case "lavendertown":
                    idOpponent = random.nextInt(12) + 1; // Generates a random number between 1 and 11 (inclusive)
                    break;
                default:
                    // Handle invalid location
                    System.out.println("Invalid location specified: " + location);
                    idOpponent = 0;
                    break;
            }
        }
    }

    public void setupMaxHp(int index, int maxHpLeader) {
        if (index == 1 && maxHp == -1) { // Only set maxHp if it is not already set
            maxHp = maxHpLeader;
        }
    }

    public void updateWildPokemonHp(String Location, int IdOpponent, int opponentMaxHP) {
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

    private void updatePlayerPokemonHp(int SaveNumber, int PlayerMaxHp) {
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

    private void setOpponentInfo(String[] opponentInfo) {
        opponentName = opponentInfo[0];
        opponentLevel = (opponentInfo[1] != null) ? Integer.parseInt(opponentInfo[1]) : 0;
        opponentHp = (opponentInfo[2] != null) ? Integer.parseInt(opponentInfo[2]) : 0;
        opponentMaxHp = (opponentInfo[3] != null) ? Integer.parseInt(opponentInfo[3]) : 0; // Added MaxHp
        opponentType = opponentInfo[4];
        opponentMove1 = opponentInfo[5];
        opponentDmg1 = (opponentInfo[6] != null) ? Integer.parseInt(opponentInfo[6]) : 0;
        opponentMove2 = opponentInfo[7];
        opponentDmg2 = (opponentInfo[8] != null) ? Integer.parseInt(opponentInfo[8]) : 0;
        opponentMove3 = opponentInfo[9];
        opponentDmg3 = (opponentInfo[10] != null) ? Integer.parseInt(opponentInfo[10]) : 0;
        opponentMove4 = opponentInfo[11];
        opponentDmg4 = (opponentInfo[12] != null) ? Integer.parseInt(opponentInfo[12]) : 0;
    }

// Method to set player information
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Arrow = new javax.swing.JLabel();
        BattleMessage = new javax.swing.JLabel();
        NamePlayer = new javax.swing.JLabel();
        Move2Button = new javax.swing.JButton();
        Move3Button = new javax.swing.JButton();
        Move4Button = new javax.swing.JButton();
        Move1Button = new javax.swing.JButton();
        LevelOpponent = new javax.swing.JLabel();
        HPOpponent = new javax.swing.JLabel();
        Move2 = new javax.swing.JLabel();
        Move3 = new javax.swing.JLabel();
        Move4 = new javax.swing.JLabel();
        Move1 = new javax.swing.JLabel();
        LevelPlayer = new javax.swing.JLabel();
        HPPlayer = new javax.swing.JLabel();
        PokemonImg = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1080, 720));
        setMinimumSize(new java.awt.Dimension(1080, 720));
        setPreferredSize(new java.awt.Dimension(1080, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Arrow.setText("jLabel1");
        Arrow.setMaximumSize(new java.awt.Dimension(239, 130));
        Arrow.setMinimumSize(new java.awt.Dimension(239, 130));
        Arrow.setPreferredSize(new java.awt.Dimension(239, 130));
        getContentPane().add(Arrow, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, -1, -1));

        BattleMessage.setText("jLabel1");
        getContentPane().add(BattleMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 630, 460, 70));

        NamePlayer.setText("jLabel1");
        getContentPane().add(NamePlayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 140, 30));

        Move2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Move2ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(Move2Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 490, 110, 50));

        Move3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Move3ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(Move3Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 540, 110, 50));

        Move4Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Move4ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(Move4Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 540, 110, 50));

        Move1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Move1ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(Move1Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 490, 110, 50));

        LevelOpponent.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        LevelOpponent.setText("jLabel1");
        getContentPane().add(LevelOpponent, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 430, 120, 40));

        HPOpponent.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        HPOpponent.setText("jLabel1");
        getContentPane().add(HPOpponent, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 380, 120, 40));

        Move2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        Move2.setText("jLabel1");
        getContentPane().add(Move2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 490, 110, 50));

        Move3.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        Move3.setText("jLabel1");
        getContentPane().add(Move3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 540, 110, 50));

        Move4.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        Move4.setText("jLabel1");
        getContentPane().add(Move4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 540, 110, 50));

        Move1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        Move1.setText("jLabel1");
        getContentPane().add(Move1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 490, 110, 50));

        LevelPlayer.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        LevelPlayer.setText("jLabel1");
        getContentPane().add(LevelPlayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, 120, 30));

        HPPlayer.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        HPPlayer.setText("jLabel1");
        getContentPane().add(HPPlayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 120, 30));

        PokemonImg.setText("jLabel1");
        getContentPane().add(PokemonImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 280, 190));

        jButton1.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, 230, 60));

        Background.setText("jLabel2");
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 720));

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Move1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Move1ButtonActionPerformed
        String selectDmgSql = "SELECT dmg1 FROM player_pokemon WHERE player_id = ?";
        String selectHpSql = "SELECT Hp FROM " + location.toLowerCase() + " WHERE id = ?";
        String updateHpSql = "UPDATE " + location.toLowerCase() + " SET Hp = ? WHERE id = ?";
        String selectPlayerHpSql = "SELECT Hp FROM player_pokemon WHERE player_id = ?";
        String selectOpponentDmg1Sql = "SELECT Dmg1 FROM " + location.toLowerCase() + " WHERE id = ?";
        String updatePlayerHpSql = "UPDATE player_pokemon SET Hp = ? WHERE player_id = ?";
        
        System.out.println();

        int dmg1 = 0;
        int currentHp = 0;
        int playerHp = 0;
        int opponentDmg1 = 0;

        try (Connection connection = getConnection()) {
            // Fetch dmg1 from player_pokemon table
            try (PreparedStatement dmgStmt = connection.prepareStatement(selectDmgSql)) {
                dmgStmt.setInt(1, saveNumber);
                ResultSet dmgRs = dmgStmt.executeQuery();
                if (dmgRs.next()) {
                    dmg1 = dmgRs.getInt("dmg1");
                }
            }

            // Fetch current Hp from location table
            try (PreparedStatement hpStmt = connection.prepareStatement(selectHpSql)) {
                hpStmt.setInt(1, idOpponent);
                ResultSet hpRs = hpStmt.executeQuery();
                if (hpRs.next()) {
                    currentHp = hpRs.getInt("Hp");
                }
            }

            // Calculate remaining Hp for the opponent
            int opponentRemainingHp = currentHp - dmg1;

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
                opponentDmg1Stmt.setInt(1, idOpponent);
                ResultSet opponentDmg1Rs = opponentDmg1Stmt.executeQuery();
                if (opponentDmg1Rs.next()) {
                    opponentDmg1 = opponentDmg1Rs.getInt("Dmg1");
                }
            }

            // Calculate remaining Hp for the player
            int playerRemainingHp = playerHp - opponentDmg1;

            // Update player Hp in player_pokemon table
            try (PreparedStatement updatePlayerHpStmt = connection.prepareStatement(updatePlayerHpSql)) {
                updatePlayerHpStmt.setInt(1, playerRemainingHp);
                updatePlayerHpStmt.setInt(2, saveNumber);
                updatePlayerHpStmt.executeUpdate();
            }

            // Update Hp in location table
            try (PreparedStatement updateHpStmt = connection.prepareStatement(updateHpSql)) {
                updateHpStmt.setInt(1, opponentRemainingHp);
                updateHpStmt.setInt(2, idOpponent);
                updateHpStmt.executeUpdate();
            }

            if (playerRemainingHp <= 0) {
                JOptionPane.showMessageDialog(null, playerName + " fainted!\nYou lost the battle.", "Battle Lost", JOptionPane.INFORMATION_MESSAGE);
                updatePlayerPokemonHp(saveNumber, playerMaxHp); // Call the new method to restore player HP

                // Open the MainMenuPage
                try {
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
                    if (window instanceof FightWildPokemonPage) {
                        window.dispose();
                    }
                }

                return; // End the method here to prevent further execution
            }

            // Check if the battle ends
            if (opponentRemainingHp <= 0) {
                gainXp(saveNumber, (opponentLevel * 5));
                updateWildPokemonHp(location, idOpponent, maxHp);
                updatePlayerPokemonHp(saveNumber, playerMaxHp); // Call the new method to restore player HP
                BattleMessage.setText(opponentName + " faints!\n" + playerName + " gained " + (opponentLevel * 5) + "xp.\n" + playerName + "[XP: " + ConnectDatabase.getXP(saveNumber) + "/" + ConnectDatabase.checkLevelLimit(ConnectDatabase.getLevel(saveNumber)) + "]");
                ConnectDatabase.checkXp(saveNumber, playerName);
                ConnectDatabase.checkEvolve(saveNumber, playerName);
                JOptionPane.showMessageDialog(null, BattleMessage.getText(), "Battle End", JOptionPane.INFORMATION_MESSAGE);

                // Close all related FightWildPokemon GUIs
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof FightWildPokemonPage) {
                        window.dispose();
                    }
                }

                // Open the MainMenuPage
                try {
                    MainMenuPage mainMenuPageFrame = new MainMenuPage(saveNumber);
                    mainMenuPageFrame.setVisible(true);
                    mainMenuPageFrame.pack();
                    mainMenuPageFrame.setLocationRelativeTo(null);
                } catch (FontFormatException ex) {
                    Logger.getLogger(ShowMyPokemon.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Refresh FightWildPokemonPage
                this.dispose();
                FightWildPokemonPage fightWildPokemon = new FightWildPokemonPage(saveNumber, location);
                fightWildPokemon.setVisible(true);
                fightWildPokemon.pack();
                fightWildPokemon.setLocationRelativeTo(null);
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }//GEN-LAST:event_Move1ButtonActionPerformed

    private void Move2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Move2ButtonActionPerformed

        String selectDmgSql = "SELECT dmg2 FROM player_pokemon WHERE player_id = ?";
        String selectHpSql = "SELECT Hp FROM " + location.toLowerCase() + " WHERE id = ?";
        String updateHpSql = "UPDATE " + location.toLowerCase() + " SET Hp = ? WHERE id = ?";
        String selectPlayerHpSql = "SELECT Hp FROM player_pokemon WHERE player_id = ?";
        String selectOpponentDmg1Sql = "SELECT Dmg1 FROM " + location.toLowerCase() + " WHERE id = ?";
        String updatePlayerHpSql = "UPDATE player_pokemon SET Hp = ? WHERE player_id = ?";

        int dmg2 = 0;
        int currentHp = 0;
        int playerHp = 0;
        int opponentDmg1 = 0;

        try (Connection connection = getConnection()) {
            // Fetch dmg2 from player_pokemon table
            try (PreparedStatement dmgStmt = connection.prepareStatement(selectDmgSql)) {
                dmgStmt.setInt(1, saveNumber);
                ResultSet dmgRs = dmgStmt.executeQuery();
                if (dmgRs.next()) {
                    dmg2 = dmgRs.getInt("dmg2");
                }
            }

            // Fetch current Hp from location table
            try (PreparedStatement hpStmt = connection.prepareStatement(selectHpSql)) {
                hpStmt.setInt(1, idOpponent);
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
                opponentDmg1Stmt.setInt(1, idOpponent);
                ResultSet opponentDmg1Rs = opponentDmg1Stmt.executeQuery();
                if (opponentDmg1Rs.next()) {
                    opponentDmg1 = opponentDmg1Rs.getInt("Dmg1");
                }
            }

            // Calculate remaining Hp for the player
            int playerRemainingHp = playerHp - opponentDmg1;

            // Update player Hp in player_pokemon table
            try (PreparedStatement updatePlayerHpStmt = connection.prepareStatement(updatePlayerHpSql)) {
                updatePlayerHpStmt.setInt(1, playerRemainingHp);
                updatePlayerHpStmt.setInt(2, saveNumber);
                updatePlayerHpStmt.executeUpdate();
            }

            // Update Hp in location table
            try (PreparedStatement updateHpStmt = connection.prepareStatement(updateHpSql)) {
                updateHpStmt.setInt(1, opponentRemainingHp);
                updateHpStmt.setInt(2, idOpponent);
                updateHpStmt.executeUpdate();
            }

            if (playerRemainingHp <= 0) {
                JOptionPane.showMessageDialog(null, playerName + " fainted!\nYou lost the battle.", "Battle Lost", JOptionPane.INFORMATION_MESSAGE);

                // Open the MainMenuPage
                try {
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
                    if (window instanceof FightWildPokemonPage) {
                        window.dispose();
                    }
                }

                return; // End the method here to prevent further execution
            }

            // Check if the battle ends
            if (opponentRemainingHp <= 0) {
                gainXp(saveNumber, (opponentLevel * 5));
                updateWildPokemonHp(location, idOpponent, maxHp);
                updatePlayerPokemonHp(saveNumber, playerMaxHp); // Call the new method to restore player HP
                BattleMessage.setText(opponentName + " faints!\n" + playerName + " gained " + (opponentLevel * 5) + "xp.\n" + playerName + "[XP: " + ConnectDatabase.getXP(saveNumber) + "/" + ConnectDatabase.checkLevelLimit(ConnectDatabase.getLevel(saveNumber)) + "]");
                JOptionPane.showMessageDialog(null, BattleMessage.getText(), "Battle End", JOptionPane.INFORMATION_MESSAGE);

                // Close all related FightWildPokemon GUIs
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof FightWildPokemonPage) {
                        window.dispose();
                    }
                }

                // Open the MainMenuPage
                try {
                    MainMenuPage mainMenuPageFrame = new MainMenuPage(saveNumber);
                    mainMenuPageFrame.setVisible(true);
                    mainMenuPageFrame.pack();
                    mainMenuPageFrame.setLocationRelativeTo(null);
                } catch (FontFormatException ex) {
                    Logger.getLogger(ShowMyPokemon.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Refresh FightWildPokemonPage
                this.dispose();
                FightWildPokemonPage fightWildPokemon = new FightWildPokemonPage(saveNumber, location);
                fightWildPokemon.setVisible(true);
                fightWildPokemon.pack();
                fightWildPokemon.setLocationRelativeTo(null);
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }

    }//GEN-LAST:event_Move2ButtonActionPerformed

    private void Move3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Move3ButtonActionPerformed
        String selectDmgSql = "SELECT dmg3 FROM player_pokemon WHERE player_id = ?";
        String selectHpSql = "SELECT Hp FROM " + location.toLowerCase() + " WHERE id = ?";
        String updateHpSql = "UPDATE " + location.toLowerCase() + " SET Hp = ? WHERE id = ?";
        String selectPlayerHpSql = "SELECT Hp FROM player_pokemon WHERE player_id = ?";
        String selectOpponentDmg1Sql = "SELECT Dmg1 FROM " + location.toLowerCase() + " WHERE id = ?";
        String updatePlayerHpSql = "UPDATE player_pokemon SET Hp = ? WHERE player_id = ?";

        int dmg3 = 0;
        int currentHp = 0;
        int playerHp = 0;
        int opponentDmg1 = 0;

        try (Connection connection = getConnection()) {
            // Fetch dmg3 from player_pokemon table
            try (PreparedStatement dmgStmt = connection.prepareStatement(selectDmgSql)) {
                dmgStmt.setInt(1, saveNumber);
                ResultSet dmgRs = dmgStmt.executeQuery();
                if (dmgRs.next()) {
                    dmg3 = dmgRs.getInt("dmg3");
                }
            }

            // Fetch current Hp from location table
            try (PreparedStatement hpStmt = connection.prepareStatement(selectHpSql)) {
                hpStmt.setInt(1, idOpponent);
                ResultSet hpRs = hpStmt.executeQuery();
                if (hpRs.next()) {
                    currentHp = hpRs.getInt("Hp");
                }
            }

            // Calculate remaining Hp for the opponent
            int opponentRemainingHp = currentHp - dmg3;

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
                opponentDmg1Stmt.setInt(1, idOpponent);
                ResultSet opponentDmg1Rs = opponentDmg1Stmt.executeQuery();
                if (opponentDmg1Rs.next()) {
                    opponentDmg1 = opponentDmg1Rs.getInt("Dmg1");
                }
            }

            // Calculate remaining Hp for the player
            int playerRemainingHp = playerHp - opponentDmg1;

            // Update player Hp in player_pokemon table
            try (PreparedStatement updatePlayerHpStmt = connection.prepareStatement(updatePlayerHpSql)) {
                updatePlayerHpStmt.setInt(1, playerRemainingHp);
                updatePlayerHpStmt.setInt(2, saveNumber);
                updatePlayerHpStmt.executeUpdate();
            }

            // Update Hp in location table
            try (PreparedStatement updateHpStmt = connection.prepareStatement(updateHpSql)) {
                updateHpStmt.setInt(1, opponentRemainingHp);
                updateHpStmt.setInt(2, idOpponent);
                updateHpStmt.executeUpdate();
            }

            if (playerRemainingHp <= 0) {
                JOptionPane.showMessageDialog(null, playerName + " fainted!\nYou lost the battle.", "Battle Lost", JOptionPane.INFORMATION_MESSAGE);

                // Open the MainMenuPage
                try {
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
                    if (window instanceof FightWildPokemonPage) {
                        window.dispose();
                    }
                }

                return; // End the method here to prevent further execution
            }

            // Check if the battle ends
            if (opponentRemainingHp <= 0) {
                gainXp(saveNumber, (opponentLevel * 5));
                updateWildPokemonHp(location, idOpponent, maxHp);
                updatePlayerPokemonHp(saveNumber, playerMaxHp); // Call the new method to restore player HP
                BattleMessage.setText(opponentName + " faints!\n" + playerName + " gained " + (opponentLevel * 5) + "xp.\n" + playerName + "[XP: " + ConnectDatabase.getXP(saveNumber) + "/" + ConnectDatabase.checkLevelLimit(ConnectDatabase.getLevel(saveNumber)) + "]");
                JOptionPane.showMessageDialog(null, BattleMessage.getText(), "Battle End", JOptionPane.INFORMATION_MESSAGE);

                // Close all related FightWildPokemon GUIs
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof FightWildPokemonPage) {
                        window.dispose();
                    }
                }

                // Open the MainMenuPage
                try {
                    MainMenuPage mainMenuPageFrame = new MainMenuPage(saveNumber);
                    mainMenuPageFrame.setVisible(true);
                    mainMenuPageFrame.pack();
                    mainMenuPageFrame.setLocationRelativeTo(null);
                } catch (FontFormatException ex) {
                    Logger.getLogger(ShowMyPokemon.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Refresh FightWildPokemonPage
                this.dispose();
                FightWildPokemonPage fightWildPokemon = new FightWildPokemonPage(saveNumber, location);
                fightWildPokemon.setVisible(true);
                fightWildPokemon.pack();
                fightWildPokemon.setLocationRelativeTo(null);
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }//GEN-LAST:event_Move3ButtonActionPerformed

    private void Move4ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Move4ButtonActionPerformed
        String selectDmgSql = "SELECT dmg FROM player_pokemon WHERE player_id = ?";
        String selectHpSql = "SELECT Hp FROM " + location.toLowerCase() + " WHERE id = ?";
        String updateHpSql = "UPDATE " + location.toLowerCase() + " SET Hp = ? WHERE id = ?";
        String selectPlayerHpSql = "SELECT Hp FROM player_pokemon WHERE player_id = ?";
        String selectOpponentDmg1Sql = "SELECT Dmg1 FROM " + location.toLowerCase() + " WHERE id = ?";
        String updatePlayerHpSql = "UPDATE player_pokemon SET Hp = ? WHERE player_id = ?";

        int dmg = 0;
        int currentHp = 0;
        int playerHp = 0;
        int opponentDmg1 = 0;

        try (Connection connection = getConnection()) {
            // Fetch dmg from player_pokemon table
            try (PreparedStatement dmgStmt = connection.prepareStatement(selectDmgSql)) {
                dmgStmt.setInt(1, saveNumber);
                ResultSet dmgRs = dmgStmt.executeQuery();
                if (dmgRs.next()) {
                    dmg = dmgRs.getInt("dmg");
                }
            }

            // Fetch current Hp from location table
            try (PreparedStatement hpStmt = connection.prepareStatement(selectHpSql)) {
                hpStmt.setInt(1, idOpponent);
                ResultSet hpRs = hpStmt.executeQuery();
                if (hpRs.next()) {
                    currentHp = hpRs.getInt("Hp");
                }
            }

            // Calculate remaining Hp for the opponent
            int opponentRemainingHp = currentHp - dmg;

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
                opponentDmg1Stmt.setInt(1, idOpponent);
                ResultSet opponentDmg1Rs = opponentDmg1Stmt.executeQuery();
                if (opponentDmg1Rs.next()) {
                    opponentDmg1 = opponentDmg1Rs.getInt("Dmg1");
                }
            }

            // Calculate remaining Hp for the player
            int playerRemainingHp = playerHp - opponentDmg1;

            // Update player Hp in player_pokemon table
            try (PreparedStatement updatePlayerHpStmt = connection.prepareStatement(updatePlayerHpSql)) {
                updatePlayerHpStmt.setInt(1, playerRemainingHp);
                updatePlayerHpStmt.setInt(2, saveNumber);
                updatePlayerHpStmt.executeUpdate();
            }

            // Update Hp in location table
            try (PreparedStatement updateHpStmt = connection.prepareStatement(updateHpSql)) {
                updateHpStmt.setInt(1, opponentRemainingHp);
                updateHpStmt.setInt(2, idOpponent);
                updateHpStmt.executeUpdate();
            }

            if (playerRemainingHp <= 0) {
                JOptionPane.showMessageDialog(null, playerName + " fainted!\nYou lost the battle.", "Battle Lost", JOptionPane.INFORMATION_MESSAGE);

                // Open the MainMenuPage
                try {
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
                    if (window instanceof FightWildPokemonPage) {
                        window.dispose();
                    }
                }

                return; // End the method here to prevent further execution
            }

            // Check if the battle ends
            if (opponentRemainingHp <= 0) {
                gainXp(saveNumber, (opponentLevel * 5));
                updateWildPokemonHp(location, idOpponent, maxHp);
                updatePlayerPokemonHp(saveNumber, playerMaxHp); // Call the new method to restore player HP
                BattleMessage.setText(opponentName + " faints!\n" + playerName + " gained " + (opponentLevel * 5) + "xp.\n" + playerName + "[XP: " + ConnectDatabase.getXP(saveNumber) + "/" + ConnectDatabase.checkLevelLimit(ConnectDatabase.getLevel(saveNumber)) + "]");
                JOptionPane.showMessageDialog(null, BattleMessage.getText(), "Battle End", JOptionPane.INFORMATION_MESSAGE);

                // Close all related FightWildPokemon GUIs
                Window[] windows = Window.getWindows();
                for (Window window : windows) {
                    if (window instanceof FightWildPokemonPage) {
                        window.dispose();
                    }
                }

                // Open the MainMenuPage
                try {
                    MainMenuPage mainMenuPageFrame = new MainMenuPage(saveNumber);
                    mainMenuPageFrame.setVisible(true);
                    mainMenuPageFrame.pack();
                    mainMenuPageFrame.setLocationRelativeTo(null);
                } catch (FontFormatException ex) {
                    Logger.getLogger(ShowMyPokemon.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Refresh FightWildPokemonPage
                this.dispose();
                FightWildPokemonPage fightWildPokemon = new FightWildPokemonPage(saveNumber, location);
                fightWildPokemon.setVisible(true);
                fightWildPokemon.pack();
                fightWildPokemon.setLocationRelativeTo(null);
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }//GEN-LAST:event_Move4ButtonActionPerformed

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
            java.util.logging.Logger.getLogger(FightWildPokemonPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FightWildPokemonPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FightWildPokemonPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FightWildPokemonPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FightWildPokemonPage(saveNumber, location).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Arrow;
    private javax.swing.JLabel Background;
    private javax.swing.JLabel BattleMessage;
    private javax.swing.JLabel HPOpponent;
    private javax.swing.JLabel HPPlayer;
    private javax.swing.JLabel LevelOpponent;
    private javax.swing.JLabel LevelPlayer;
    private javax.swing.JLabel Move1;
    private javax.swing.JButton Move1Button;
    private javax.swing.JLabel Move2;
    private javax.swing.JButton Move2Button;
    private javax.swing.JLabel Move3;
    private javax.swing.JButton Move3Button;
    private javax.swing.JLabel Move4;
    private javax.swing.JButton Move4Button;
    private javax.swing.JLabel NamePlayer;
    private javax.swing.JLabel PokemonImg;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
