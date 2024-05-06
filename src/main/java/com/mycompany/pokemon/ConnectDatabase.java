/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pokemon;

/**
 *
 * @author User
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class ConnectDatabase {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/pokemon";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "8888";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public static String getName(int saveNumber) {
        String name = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT name FROM users WHERE id = ?");
            statement.setInt(1, saveNumber);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return name;
    }

    public static String checkGame(int saveNumber) {
        String name = getName(saveNumber);
        if (name == null) {
            return "new";
        } else {
            return "override";
        }
    }

    public static void updateName(int saveNumber, String playerName) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            String sql = "UPDATE users SET name = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, playerName);
            statement.setInt(2, saveNumber);
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void defaultLocation(int saveNumber) {
        String sql = "UPDATE users SET last_location = 'PalletTown' WHERE id = ?";

        try (
                Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, saveNumber);
            preparedStatement.executeUpdate();
            System.out.println("Default location set to PalletTown for Save " + saveNumber);
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }

    public static void savePokemon(int saveNumber, String pokemonName) {
        String sql = "INSERT INTO player_pokemon (player_id, pokemon_name) VALUES (?, ?)";

        try (
                Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, saveNumber);
            preparedStatement.setString(2, pokemonName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }

    public static void updatePokemonStatsInPlayer(int saveNumber, String pokemonName, int level) {
        // Query to fetch data from the pokemon table based on the pokemonName
        String selectSql = "SELECT Hp, Type, StrongAgainst, WeakAgainst FROM pokemon WHERE Name = ?";

        // SQL statement to update the player_pokemon table
        String updateSql = "UPDATE player_pokemon SET Hp = ?, MaxHp = ?, Type = ?, StrongAgainst = ?, WeakAgainst = ?, Level = ?, Xp = 0 WHERE player_id = ? AND pokemon_name = ?";

        try (
                Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(selectSql); PreparedStatement updateStatement = connection.prepareStatement(updateSql);) {
            // Set parameter for the SELECT statement
            selectStatement.setString(1, pokemonName);

            // Execute the SELECT statement
            ResultSet resultSet = selectStatement.executeQuery();

            // Check if the result set has data
            if (resultSet.next()) {
                // Retrieve data from the result set
                int hp = resultSet.getInt("Hp");
                // Add the maxHP retrieval from the result set
                int maxHp = resultSet.getInt("Hp");
                String type = resultSet.getString("Type");
                String strongAgainst = resultSet.getString("StrongAgainst");
                String weakAgainst = resultSet.getString("WeakAgainst");

                // Set parameters for the UPDATE statement
                updateStatement.setInt(1, hp);
                // Set maxHp value in the update statement
                updateStatement.setInt(2, maxHp);
                updateStatement.setString(3, type);
                updateStatement.setString(4, strongAgainst);
                updateStatement.setString(5, weakAgainst);
                updateStatement.setInt(6, level);
                updateStatement.setInt(7, saveNumber);
                updateStatement.setString(8, pokemonName);

                // Execute the UPDATE statement
                updateStatement.executeUpdate();
            } else {
                System.out.println("No data found for pokemon: " + pokemonName);
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }

    private static void updatePlayerPokemonStats(int saveNumber, String pokemonName, int hp, String type, String strongAgainst, String weakAgainst, int level) throws SQLException {
        String sql = "UPDATE player_pokemon SET Hp = ?, Type = ?, StrongAgainst = ?, WeakAgainst = ?, Level = ? WHERE player_id = ? AND pokemon_name = ?";

        try (
                Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, hp);
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, strongAgainst);
            preparedStatement.setString(4, weakAgainst);
            preparedStatement.setInt(5, level);
            preparedStatement.setInt(6, saveNumber);
            preparedStatement.setString(7, pokemonName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }

    public static String getLocation(int saveNumber) {
        String location = null;
        String sql = "SELECT last_location FROM users WHERE id = ?";

        try (
                Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, saveNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                location = resultSet.getString("last_location");
            } else {
                System.out.println("User with saveNumber " + saveNumber + " not found.");
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }

        return location;
    }

    public static void updateLocation(int saveNumber, String newLocation) {
        String sql = "UPDATE users SET last_location = ? WHERE id = ?";

        try (
                Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, newLocation);
            preparedStatement.setInt(2, saveNumber);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Location updated successfully!");
            } else {
                System.out.println("Failed to update location. User with saveNumber " + saveNumber + " not found.");
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }

    public static void updateMove(int saveNumber, String pokemonName, String move1, String move2) {
        String sql = "UPDATE player_pokemon SET Move1 = ?, Move2 = ? WHERE player_id = ? AND pokemon_name = ?";

        try (
                Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, move1);
            preparedStatement.setString(2, move2);
            preparedStatement.setInt(3, saveNumber);
            preparedStatement.setString(4, pokemonName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }

    public static void showMyPokemon(int saveNumber) {
        String sql = "SELECT pokemon_name, Level, Type, Hp, Xp, Move1, Move2, Move3, Move4, StrongAgainst, WeakAgainst, dmg1, dmg2, dmg3, dmg FROM player_pokemon WHERE player_id = ?";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setInt(1, saveNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Your Pokémon:");
            while (resultSet.next()) {
                String pokemonName = resultSet.getString("pokemon_name");
                int level = resultSet.getInt("Level");
                String type = resultSet.getString("Type");
                int hp = resultSet.getInt("Hp");
                int xp = resultSet.getInt("Xp");
                String move1 = resultSet.getString("Move1");
                String move2 = resultSet.getString("Move2");
                String move3 = resultSet.getString("Move3");
                String move4 = resultSet.getString("Move4");
                String strongAgainst = resultSet.getString("StrongAgainst");
                String weakAgainst = resultSet.getString("WeakAgainst");
                int dmg1 = resultSet.getInt("dmg1");
                int dmg2 = resultSet.getInt("dmg2");
                int dmg3 = resultSet.getInt("dmg3");
                int dmg = resultSet.getInt("dmg");

                int xpLimit = checkLevelLimit(level);

                System.out.println(pokemonName + " - Level: " + level);
                System.out.println("Type: " + type);
                System.out.println("HP: " + hp);
                System.out.println("XP: " + xp + "/" + xpLimit);
                System.out.println("Moves:");
                if (move1 != null) {
                    System.out.print("- " + move1);
                    if (dmg1 > 0) {
                        System.out.print(" [" + dmg1 + " damage]");
                    }
                    System.out.println();
                }
                if (move2 != null) {
                    System.out.print("- " + move2);
                    if (dmg2 > 0) {
                        System.out.print(" [" + dmg2 + " damage]");
                    }
                    System.out.println();
                }
                if (move3 != null) {
                    System.out.print("- " + move3);
                    if (dmg3 > 0) {
                        System.out.print(" [" + dmg3 + " damage]");
                    }
                    System.out.println();
                }
                if (move4 != null) {
                    System.out.print("- " + move4);
                    if (dmg > 0) {
                        System.out.print(" [" + dmg + " damage]");
                    }
                    System.out.println();
                }

                System.out.println("Strong Against: " + strongAgainst);
                System.out.println("Weak Against: " + weakAgainst);
                System.out.println();

            }

        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }

    public static void showMyBadges(int saveNumber) {
        String sql = "SELECT badges1, badges2, badges3, badges4, badges5, badges6, badges7, badges8 FROM users WHERE id = ?";

        try (
                Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, saveNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("+----------------------------------------------------------------------+");
            System.out.println(" Your Badges:");
            boolean foundBadge = false;
            if (resultSet.next()) {
                for (int i = 1; i <= 8; i++) {
                    String badge = resultSet.getString("badges" + i);
                    if (badge != null && !badge.isEmpty()) {
                        System.out.println(" - " + badge);
                        foundBadge = true;
                    }
                }
            }

            if (!foundBadge) {
                System.out.println(" - None");
            }
            System.out.println("+----------------------------------------------------------------------+");
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }

    public static void updateBadges(int saveNumber, String badgeName) {
        String sql = "UPDATE users SET badges1 = ?, badges2 = ?, badges3 = ?, badges4 = ?, badges5 = ?, badges6 = ?, badges7 = ?, badges8 = ? WHERE id = ?";

        try (
                Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            // Fetch the current badges data
            String[] currentBadges = new String[8];
            String selectSql = "SELECT badges1, badges2, badges3, badges4, badges5, badges6, badges7, badges8 FROM users WHERE id = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectSql);
            selectStatement.setInt(1, saveNumber);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                for (int i = 0; i < 8; i++) {
                    currentBadges[i] = resultSet.getString("badges" + (i + 1));
                }
            }

            // Find the first empty slot for the new badge
            int emptySlot = -1;
            for (int i = 0; i < 8; i++) {
                if (currentBadges[i] == null || currentBadges[i].isEmpty()) {
                    emptySlot = i;
                    break;
                }
            }

            // Update the badges
            if (emptySlot != -1) {
                currentBadges[emptySlot] = badgeName;
                for (int i = 0; i < 8; i++) {
                    preparedStatement.setString(i + 1, currentBadges[i]);
                }
                preparedStatement.setInt(9, saveNumber);
                preparedStatement.executeUpdate();
                System.out.println("Badge '" + badgeName + "' has been added to your collection!");
            } else {
                System.out.println("You have already collected the maximum number of badges.");
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }

    public static String getPokemonName(int saveNumber) {
        String selectSql = "SELECT pokemon_name FROM player_pokemon WHERE player_id = ?";
        String pokemonName = null;

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setInt(1, saveNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if there is a result
            if (resultSet.next()) {
                pokemonName = resultSet.getString("pokemon_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pokemonName;
    }

    public static void checkXp(int saveNumber, String pokemonName) {
        String selectSql = "SELECT Level, Xp FROM player_pokemon WHERE player_id = ? AND pokemon_name = ?";

        try (
                Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(selectSql);) {
            // Set parameters for the SELECT statement
            selectStatement.setInt(1, saveNumber);
            selectStatement.setString(2, pokemonName);

            // Execute the SELECT statement
            ResultSet resultSet = selectStatement.executeQuery();

            // Check if the result set has data
            if (resultSet.next()) {
                int level = resultSet.getInt("Level");
                int xp = resultSet.getInt("Xp");

                // Check if the level is within the specified range
                if (level > 0 && level <= 10) {
                    if (xp >= 100) {
                        int currentLevel = getLevel(saveNumber);
                        // Call the levelUp method
                        levelUp(saveNumber, pokemonName);
                        int updatedLevel = getLevel(saveNumber);
                        System.out.println(getPokemonName(saveNumber) + " leveled up.");
                        System.out.println(getPokemonName(saveNumber) + " [Level " + currentLevel + " --> Level " + updatedLevel + "]");

                        // Reset XP to 0
                        resetXp(saveNumber, pokemonName);
                    }
                } else if (level > 10 && level <= 30) {
                    if (xp >= 200) {
                        int currentLevel = getLevel(saveNumber);
                        // Call the levelUp method
                        levelUp(saveNumber, pokemonName);
                        int updatedLevel = getLevel(saveNumber);
                        System.out.println(getPokemonName(saveNumber) + " leveled up.");
                        System.out.println(getPokemonName(saveNumber) + " [Level " + currentLevel + " --> Level " + updatedLevel + "]");

                        // Reset XP to 0
                        resetXp(saveNumber, pokemonName);
                    }
                } else if (level > 30) {
                    if (xp >= 300) {
                        int currentLevel = getLevel(saveNumber);
                        // Call the levelUp method
                        levelUp(saveNumber, pokemonName);
                        int updatedLevel = getLevel(saveNumber);
                        System.out.println(getPokemonName(saveNumber) + " leveled up.");
                        System.out.println(getPokemonName(saveNumber) + " [Level " + currentLevel + " --> Level " + updatedLevel + "]");

                        // Reset XP to 0
                        resetXp(saveNumber, pokemonName);
                    }
                }
            } else {
                System.out.println("No data found for saveNumber: " + saveNumber + " and pokemonName: " + pokemonName);
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }

    public static void levelUp(int saveNumber, String pokemonName) {
        String selectSql = "SELECT Level, Hp, dmg1, dmg2, dmg3, dmg FROM player_pokemon WHERE player_id = ? AND pokemon_name = ?";
        String updateSql = "UPDATE player_pokemon SET Level = ?, Hp = ?, dmg1 = ?, dmg2 = ?, dmg3 = ?, dmg = ? WHERE player_id = ? AND pokemon_name = ?";

        try (
                Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(selectSql); PreparedStatement updateStatement = connection.prepareStatement(updateSql);) {
            // Set parameters for the SELECT statement
            selectStatement.setInt(1, saveNumber);
            selectStatement.setString(2, pokemonName);

            // Execute the SELECT statement
            ResultSet resultSet = selectStatement.executeQuery();

            // Check if the result set has data
            if (resultSet.next()) {
                int currentLevel = resultSet.getInt("Level");
                int newLevel = currentLevel + 1;

                // Calculate new HP and damage values
                int currentHp = resultSet.getInt("Hp");
                int newHp = currentHp + 2; // Increase HP by 2

                int dmg1 = resultSet.getInt("dmg1");
                int dmg2 = resultSet.getInt("dmg2");
                int dmg3 = resultSet.getInt("dmg3");
                int dmg = resultSet.getInt("dmg");

                if (dmg1 != 0) {
                    dmg1 += 2; // Increase dmg1 by 2
                }
                if (dmg2 != 0) {
                    dmg2 += 2; // Increase dmg2 by 2
                }
                if (dmg3 != 0) {
                    dmg3 += 2; // Increase dmg3 by 2
                }
                if (dmg != 0) {
                    dmg += 2; // Increase dmg4 by 2
                }

                // Set parameters for the UPDATE statement
                updateStatement.setInt(1, newLevel);
                updateStatement.setInt(2, newHp);
                updateStatement.setInt(3, dmg1);
                updateStatement.setInt(4, dmg2);
                updateStatement.setInt(5, dmg3);
                updateStatement.setInt(6, dmg);
                updateStatement.setInt(7, saveNumber);
                updateStatement.setString(8, pokemonName);

                // Execute the UPDATE statement
                int rowsAffected = updateStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Level up for Pokemon " + pokemonName);
                } else {
                    System.out.println("Failed to update level for saveNumber: " + saveNumber + " and pokemonName: " + pokemonName);
                }
            } else {
                System.out.println("No data found for saveNumber: " + saveNumber + " and pokemonName: " + pokemonName);
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }

    public static void resetXp(int saveNumber, String pokemonName) {
        String updateSql = "UPDATE player_pokemon SET Xp = 0 WHERE player_id = ? AND pokemon_name = ?";

        try (
                Connection connection = getConnection(); PreparedStatement updateStatement = connection.prepareStatement(updateSql);) {
            // Set parameters for the UPDATE statement
            updateStatement.setInt(1, saveNumber);
            updateStatement.setString(2, pokemonName);

            // Execute the UPDATE statement
            int rowsUpdated = updateStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("XP reset successfully for pokemon: " + pokemonName);
            } else {
                System.out.println("Failed to reset XP for pokemon: " + pokemonName);
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }

    public static int getXP(int saveNumber) {
        String selectSql = "SELECT Xp FROM player_pokemon WHERE player_id = ?";
        int xp = 0;

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setInt(1, saveNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if there is a result
            if (resultSet.next()) {
                xp = resultSet.getInt("Xp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return xp;
    }

    public static int getLevel(int saveNumber) {
        // Query the database to get the level of the Pokémon for the specific user
        String query = "SELECT level FROM player_pokemon WHERE player_id = ?";

        try (Connection connection = ConnectDatabase.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, saveNumber);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // If a Pokémon is found for the specific user, return its level
                    return resultSet.getInt("level");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If no Pokémon is found for the specific user, return a default value or handle it as needed
        return -1; // Or any other default value indicating that no Pokémon was found
    }

    public static int checkLevelLimit(int level) {
        if (level >= 1 && level <= 10) {
            return 100;
        } else if (level >= 11 && level <= 30) {
            return 200;
        } else if (level > 30) {
            return 300;
        } else {
            // Handle invalid level values (less than 1)
            return -1; // Or any other appropriate value indicating an error
        }
    }

    public static void gainXp(int saveNumber, int xp) {
        // SQL statement to update the XP for the player's Pokémon
        String sql = "UPDATE player_pokemon SET Xp = Xp + ? WHERE player_id = ?";

        try (
                Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, xp);
            preparedStatement.setInt(2, saveNumber);

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("XP gained successfully.");
            } else {
                System.out.println("Failed to gain XP. No matching player found.");
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }

    public static void updateMoveDamage(int saveNumber, String pokemonName) {
        // Query to fetch data from the player_pokemon table based on the pokemonName
        String selectSql = "SELECT Move1, Move2, Move3, Move4 FROM player_pokemon WHERE player_id = ? AND pokemon_name = ?";

        // SQL statement to update the player_pokemon table
        String updateSql = "UPDATE player_pokemon SET dmg1 = ?, dmg2 = ?, dmg3 = ?, dmg = ? WHERE player_id = ? AND pokemon_name = ?";

        // Variables to hold the damage values
        int dmg1 = 0;
        int dmg2 = 0;
        int dmg3 = 0;
        int dmg = 0;

        try (
                Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(selectSql); PreparedStatement updateStatement = connection.prepareStatement(updateSql);) {
            // Set parameters for the SELECT statement
            selectStatement.setInt(1, saveNumber);
            selectStatement.setString(2, pokemonName);

            // Execute the SELECT statement
            ResultSet resultSet = selectStatement.executeQuery();

            // Check if the result set has data
            if (resultSet.next()) {
                // Retrieve data from the result set
                String move1 = resultSet.getString("Move1");
                String move2 = resultSet.getString("Move2");
                String move3 = resultSet.getString("Move3");
                String move4 = resultSet.getString("Move4");

                // Determine the damage values based on the moves
                switch (move1) {
                    case "Tackle":
                        dmg1 = 25;
                        break;
                    case "Tail Whip":
                        dmg1 = 0;
                        break;
                    case "Scratch":
                        dmg1 = 30;
                        break;
                    // Add more cases for other moves if needed
                    default:
                        // Set a default value if move1 doesn't match any of the specified moves
                        dmg1 = 0;
                        break;
                }

                switch (move2) {
                    case "Vine Whip":
                        dmg2 = 35;
                        break;
                    case "Water Gun":
                        dmg2 = 30;
                        break;
                    case "Ember":
                        dmg2 = 15;
                        break;
                    // Add more cases for other moves if needed
                    default:
                        // Set a default value if move2 doesn't match any of the specified moves
                        dmg2 = 0;
                        break;
                }

                if (move3 != null) {
                    switch (move3) {
                        case "Fire Fang":
                            dmg3 = 65;
                            break;
                        case "Water Pulse":
                            dmg3 = 60;
                            break;
                        case "Razor Leaf":
                            dmg3 = 55;
                            break;
                        default:
                            dmg3 = 0;
                            break;
                    }
                }

                if (move4 != null) {
                    switch (move4) {
                        case "Solar Beam":
                            dmg = 120;
                            break;
                        case "Wave Crash":
                            dmg = 120;
                            break;
                        case "Inferno":
                            dmg = 100;
                            break;
                        default:
                            dmg = 0;
                            break;
                    }
                }

                // Set parameters for the UPDATE statement
                updateStatement.setInt(1, dmg1);
                updateStatement.setInt(2, dmg2);
                updateStatement.setInt(3, dmg3);
                updateStatement.setInt(4, dmg);
                updateStatement.setInt(5, saveNumber);
                updateStatement.setString(6, pokemonName);

                // Execute the UPDATE statement
                int rowsAffected = updateStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Move damage updated successfully for Pokemon: " + pokemonName);
                } else {
                    System.out.println("Failed to update move damage for Pokemon: " + pokemonName);
                }
            } else {
                System.out.println("No data found for Pokemon: " + pokemonName);
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }

    }

    public static void checkEvolve(int saveNumber, String pokemonName) {
        // Query to fetch the level of the pokemon
        String selectSql = "SELECT Level FROM player_pokemon WHERE player_id = ? AND pokemon_name = ?";

        try (
                Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(selectSql);) {
            // Set parameters for the SELECT statement
            selectStatement.setInt(1, saveNumber);
            selectStatement.setString(2, pokemonName);

            // Execute the SELECT statement
            ResultSet resultSet = selectStatement.executeQuery();

            // Check if the result set has data
            if (resultSet.next()) {
                // Retrieve the level of the pokemon
                int level = resultSet.getInt("Level");

                // Check for evolution based on the pokemon name and level
                switch (pokemonName) {
                    case "Bulbasaur":
                        if (level == 16) {
                            System.out.println("Oh? Your Bulbasaur is evolving......");
                            System.out.println("Your Bulbasaur is evolving into Ivysaur.");
                            // Update pokemon column and Move3 in the player_pokemon table
                            updateEvolution(saveNumber, "Ivysaur", "Razor Leaf", null);
                        } else if (level == 32) {
                            System.out.println("Oh? Your Ivysaur is evolving......");
                            System.out.println("Your Ivysaur is evolving into Venusaur.");
                            // Update pokemon column and Move4 in the player_pokemon table
                            updateEvolution(saveNumber, "Venusaur", null, "Solar Beam");
                        }
                        break;
                    case "Charmander":
                        if (level == 16) {
                            System.out.println("Oh? Your Charmander is evolving......");
                            System.out.println("Your Charmander is evolving into Charmeleon.");
                            // Update pokemon column and Move3 in the player_pokemon table
                            updateEvolution(saveNumber, "Charmeleon", "Fire Fang", null);
                        } else if (level == 36) {
                            System.out.println("Oh? Your Charmeleon is evolving......");
                            System.out.println("Your Charmeleon is evolving into Charizard.");
                            // Update pokemon column and Move4 in the player_pokemon table
                            updateEvolution(saveNumber, "Charizard", null, "Inferno");
                        }
                        break;
                    case "Squirtle":
                        if (level == 16) {
                            System.out.println("Oh? Your Squirtle is evolving......");
                            System.out.println("Your Squirtle is evolving into Wartortle.");
                            // Update pokemon column and Move3 in the player_pokemon table
                            updateEvolution(saveNumber, "Wartortle", "Water Pulse", null);
                        } else if (level == 36) {
                            System.out.println("Oh? Your Wartortle is evolving......");
                            System.out.println("Your Wartortle is evolving into Blastoise.");
                            // Update pokemon column and Move4 in the player_pokemon table
                            updateEvolution(saveNumber, "Blastoise", null, "Wave Crash");
                        }
                        break;
                    default:
                        // If the pokemon is not in the specified list, do nothing
                        break;
                }
            } else {
                System.out.println("No data found for Pokemon: " + pokemonName);
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }

    // Method to update the evolution in the player_pokemon table
    private static void updateEvolution(int saveNumber, String evolvedPokemon, String move3, String move4) {
        // SQL statement to update the pokemon column, Move3, and Move4 in the player_pokemon table
        String updateSql = "UPDATE player_pokemon SET pokemon_name = ?, Move3 = COALESCE(?, Move3), Move4 = COALESCE(?, Move4) WHERE player_id = ? AND pokemon_name = ?";

        try (
                Connection connection = getConnection(); PreparedStatement updateStatement = connection.prepareStatement(updateSql);) {
            // Set parameters for the UPDATE statement
            updateStatement.setString(1, evolvedPokemon);
            updateStatement.setString(2, move3);
            updateStatement.setString(3, move4);
            updateStatement.setInt(4, saveNumber);
            updateStatement.setString(5, evolvedPokemon); // For the WHERE clause

            // Execute the UPDATE statement
            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Evolution updated successfully for Pokemon: " + evolvedPokemon);
            } else {
                System.out.println("Failed to update evolution for Pokemon: " + evolvedPokemon);
            }
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }

    public static String earnBadges(String location) {
        String badge;

        switch (location.toLowerCase()) {
            case "pewtercity":
                badge = "Boulder Badge";
                break;
            case "ceruleancity":
                badge = "Cascade Badge";
                break;
            case "vermilioncity":
                badge = "Thunder Badge";
                break;
            case "celadoncity":
                badge = "Rainbow Badge";
                break;
            case "fuchsiacity":
                badge = "Soul Badge";
                break;
            case "saffroncity":
                badge = "Marsh Badge";
                break;
            case "cinnabarisland":
                badge = "Volcano Badge";
                break;
            case "viridiancity":
                badge = "Earth Badge";
                break;
            default:
                badge = "No Badge Earned"; // Default if location is not recognized
                break;
        }

        return badge;
    }

    public static void challengeGymLeader(String location, int saveNumber) {
        String selectSql;
        int maxPokemons;

        switch (location.toLowerCase()) {
            case "pewtercity":
                maxPokemons = 2;
                break;
            case "ceruleancity":
                maxPokemons = 2;
                break;
            case "vermilioncity":
                maxPokemons = 3;
                break;
            case "celadoncity":
                maxPokemons = 3;
                break;
            case "fuchsiacity":
                maxPokemons = 4;
                break;
            case "saffroncity":
                maxPokemons = 4;
                break;
            case "cinnabarisland":
                maxPokemons = 4;
                break;
            case "viridiancity":
                maxPokemons = 4;
                break;
            default:
                // Default to 0 if location is not recognized
                maxPokemons = 0;
                break;
        }

        if (maxPokemons > 0) {

            selectSql = "SELECT Name, Level, Hp, Type, Move1, Dmg1, Move2, Dmg2, Move3, Dmg3, Move4, Dmg4 FROM " + location.toLowerCase() + " WHERE id <= ?";
        } else {
            // Handle invalid location
            // You can throw an exception, print an error message, or handle it in any other appropriate way
            System.out.println("Invalid location specified: " + location);
            return;
        }

        String selectSql2 = "SELECT pokemon_name, Type, Hp, maxHp, Xp, Level, Move1, dmg1, Move2, dmg2, Move3, dmg3, Move4, dmg, StrongAgainst, WeakAgainst FROM player_pokemon WHERE player_id = ?";

        // Retrieve Pokémon information from the database
        String[][] pokemonInfo = new String[maxPokemons][12]; // Attributes of each Pokémon
        String[] playerPokemonInfo = new String[16];

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setInt(1, maxPokemons); // Set the parameter value
            ResultSet resultSet = preparedStatement.executeQuery();

            int rowNum = 0; // Initialize row number to store Pokémon info

            // Loop through the result set
            while (resultSet.next() && rowNum < maxPokemons) {
                pokemonInfo[rowNum][0] = resultSet.getString("Name"); // Pokémon name
                pokemonInfo[rowNum][1] = String.valueOf(resultSet.getInt("Level")); // Level
                pokemonInfo[rowNum][2] = String.valueOf(resultSet.getInt("Hp")); // HP
                pokemonInfo[rowNum][3] = resultSet.getString("Type"); // Type
                pokemonInfo[rowNum][4] = resultSet.getString("Move1"); // Move1
                pokemonInfo[rowNum][5] = String.valueOf(resultSet.getInt("Dmg1")); // Dmg1
                pokemonInfo[rowNum][6] = resultSet.getString("Move2"); // Move2
                pokemonInfo[rowNum][7] = String.valueOf(resultSet.getInt("Dmg2")); // Dmg2
                pokemonInfo[rowNum][8] = resultSet.getString("Move3"); // Move3
                pokemonInfo[rowNum][9] = String.valueOf(resultSet.getInt("Dmg3")); // Dmg3
                pokemonInfo[rowNum][10] = resultSet.getString("Move4"); // Move4
                pokemonInfo[rowNum][11] = String.valueOf(resultSet.getInt("Dmg4")); // Dmg4

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
                playerPokemonInfo[0] = resultSet.getString("pokemon_name"); // Pokémon name
                playerPokemonInfo[1] = resultSet.getString("Type"); // Type
                playerPokemonInfo[2] = String.valueOf(resultSet.getInt("Hp")); // HP
                playerPokemonInfo[3] = String.valueOf(resultSet.getInt("maxHp")); // Max HP
                playerPokemonInfo[4] = String.valueOf(resultSet.getInt("Xp")); // Xp
                playerPokemonInfo[5] = String.valueOf(resultSet.getInt("Level")); // Level
                playerPokemonInfo[6] = resultSet.getString("Move1"); // Move1
                playerPokemonInfo[7] = String.valueOf(resultSet.getInt("dmg1")); // Dmg1
                playerPokemonInfo[8] = resultSet.getString("Move2"); // Move2
                playerPokemonInfo[9] = String.valueOf(resultSet.getInt("dmg2")); // Dmg2
                playerPokemonInfo[10] = resultSet.getString("Move3"); // Move3
                playerPokemonInfo[11] = String.valueOf(resultSet.getInt("dmg3")); // Dmg3
                playerPokemonInfo[12] = resultSet.getString("Move4"); // Move4
                playerPokemonInfo[13] = String.valueOf(resultSet.getInt("dmg")); // Dmg
                playerPokemonInfo[14] = resultSet.getString("StrongAgainst"); // StrongAgainst
                playerPokemonInfo[15] = resultSet.getString("WeakAgainst"); // WeakAgainst
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create a queue to store Pokémon names
        Queue<String> pokemonQueue = new LinkedList<>();

        // Enqueue all Pokémon names from pokemonInfo
        for (String[] pokemon : pokemonInfo) {
            pokemonQueue.add(pokemon[0]); // Assuming the Pokémon name is at index 0
        }

        int hpPlayer = Integer.parseInt(playerPokemonInfo[2]);
        int currentHpPlayer = hpPlayer;
        int levelPlayer = Integer.parseInt(playerPokemonInfo[5]); // Level
        int maxHpPlayer = Integer.parseInt(playerPokemonInfo[3]); // Max HP
        int xpPlayer = Integer.parseInt(playerPokemonInfo[4]); // XP
        int dmg1Player = Integer.parseInt(playerPokemonInfo[7]); // Dmg1
        int dmg2Player = Integer.parseInt(playerPokemonInfo[9]); // Dmg2
        int dmg3Player = Integer.parseInt(playerPokemonInfo[11]); // Dmg3
        int dmg4Player = Integer.parseInt(playerPokemonInfo[13]); // Dmg4

        String[] strongAgainstTypePlayer = playerPokemonInfo[14].split(" · ");

        System.out.println("You are about to challenge Gym Leader " + POKEMON.checkGymType(location));
        System.out.println("Prepare tourself for an intense battle!");
        System.out.println("Your pokemon: ");
        System.out.println(playerPokemonInfo[0] + " - Level: " + levelPlayer);
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("Battle Start: " + getName(saveNumber) + " vs. Gym Leader " + POKEMON.checkGymType(location));
        int firstPokemon = 0;
        while (!pokemonQueue.isEmpty()) {
            int levelLeader = Integer.parseInt(pokemonInfo[firstPokemon][1]);
            int hpLeader = Integer.parseInt(pokemonInfo[firstPokemon][2]);
            int dmg1Leader = Integer.parseInt(pokemonInfo[firstPokemon][5]);
            int dmg2Leader = Integer.parseInt(pokemonInfo[firstPokemon][7]);
            int dmg3Leader = Integer.parseInt(pokemonInfo[firstPokemon][9]);
            int dmg4Leader = Integer.parseInt(pokemonInfo[firstPokemon][11]);

            String[] typeLeader = pokemonInfo[firstPokemon][3].split(" · ");
            System.out.println(POKEMON.checkGymType(location) + " sends out " + pokemonInfo[firstPokemon][0] + " [Level " + levelLeader + "]!");
            System.out.println();
            System.out.print(playerPokemonInfo[0] + " is sent out!");

            boolean isStrongAgainst = false;
            for (String strongType : strongAgainstTypePlayer) {
                for (String leaderType : typeLeader) {
                    if (strongType.equalsIgnoreCase(leaderType)) {
                        isStrongAgainst = true;
                        // No need for the break here
                    }
                }
            }

            if (isStrongAgainst) {
                System.out.println(" Its " + playerPokemonInfo[1] + " type is strong against the opponent's " + typeLeader[0] + " type.");
            } else {
                System.out.println();
            }

            System.out.println();
            int round = 1;

            while (hpLeader > 0) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Round " + round + ": ");
                System.out.println(playerPokemonInfo[0] + "'s Moves: ");
                if (playerPokemonInfo[6] != null) {
                    System.out.println("1. " + playerPokemonInfo[6]);
                }
                if (playerPokemonInfo[8] != null) {
                    System.out.println("2. " + playerPokemonInfo[8]);
                }
                if (playerPokemonInfo[10] != null) {
                    System.out.println("3. " + playerPokemonInfo[10]);
                }
                if (playerPokemonInfo[12] != null) {
                    System.out.println("4. " + playerPokemonInfo[12]);
                }
                System.out.println("Which move will " + playerPokemonInfo[0] + " use?");
                System.out.print("Your choice: ");
                int moveChoice = sc.nextInt();
                System.out.println("+----------------------------------------------------------------------+");

                switch (moveChoice) {
                    case 1:
                        if (playerPokemonInfo[6] != null) {

                            System.out.println(playerPokemonInfo[0] + " uses " + playerPokemonInfo[6]);
                            System.out.println(pokemonInfo[firstPokemon][0] + "'s HP drops slightly. [" + pokemonInfo[firstPokemon][0] + " HP: " + (hpLeader -= dmg1Player) + "/" + pokemonInfo[firstPokemon][2] + "]");
                            System.out.println();
                        }
                        break;
                    case 2:
                        if (playerPokemonInfo[8] != null) {
                            System.out.println(playerPokemonInfo[0] + " uses " + playerPokemonInfo[8]);
                            System.out.println(pokemonInfo[firstPokemon][0] + "'s HP drops slightly. [" + pokemonInfo[firstPokemon][0] + " HP: " + (hpLeader -= dmg2Player) + "/" + pokemonInfo[firstPokemon][2] + "]");
                            System.out.println();
                        }
                        break;
                    case 3:
                        if (playerPokemonInfo[10] != null) {
                            System.out.println(playerPokemonInfo[0] + " uses " + playerPokemonInfo[10]);
                            System.out.println(pokemonInfo[firstPokemon][0] + "'s HP drops slightly. [" + pokemonInfo[firstPokemon][0] + " HP: " + (hpLeader -= dmg3Player) + "/" + pokemonInfo[firstPokemon][2] + "]");
                            System.out.println();
                        }
                        break;
                    case 4:
                        if (playerPokemonInfo[12] != null) {
                            System.out.println(playerPokemonInfo[0] + " uses " + playerPokemonInfo[12]);
                            System.out.println(pokemonInfo[firstPokemon][0] + "'s HP drops slightly. [" + pokemonInfo[firstPokemon][0] + " HP: " + (hpLeader -= dmg4Player) + "/" + pokemonInfo[firstPokemon][2] + "]");
                            System.out.println();
                        }
                        break;
                    default:
                        System.out.println("Invalid move choice. Please select a move between 1 and 4.");
                        break;
                }

                Random random = new Random();

                int opponentMoveIndex;
                String moveName;
                int moveDamage;

                while (true) {
                    opponentMoveIndex = random.nextInt(4) + 1; // Generate random number between 1 and 4

                    switch (opponentMoveIndex) {
                        case 1:
                            moveName = pokemonInfo[firstPokemon][4];
                            moveDamage = dmg1Leader;
                            break;
                        case 2:
                            moveName = pokemonInfo[firstPokemon][6];
                            moveDamage = dmg2Leader;
                            break;
                        case 3:
                            moveName = pokemonInfo[firstPokemon][8];
                            moveDamage = dmg3Leader;
                            break;
                        case 4:
                            moveName = pokemonInfo[firstPokemon][10];
                            moveDamage = dmg4Leader;
                            break;
                        default:
                            System.out.println("Invalid opponent move.");
                            continue; // Generate a new random number if the move index is invalid
                    }

                    if (moveName != null) {
                        // A valid move is found
                        currentHpPlayer -= moveDamage;
                        System.out.println(pokemonInfo[firstPokemon][0] + " uses " + moveName);
                        // Handle damage calculation for the move
                        System.out.println(playerPokemonInfo[0] + " takes some damage. [" + playerPokemonInfo[0] + " HP : " + currentHpPlayer + "/" + maxHpPlayer + "]");
                        System.out.println();

                        if (currentHpPlayer < 0) {
                            System.out.println("Since you have one pokemon, and for fair purpose. You can revive up to the number of opponent pokemons.");
                            currentHpPlayer = maxHpPlayer;
                        }
                        break; // Exit the loop since a move is found
                    }
                }
                System.out.println();
                round++;

            }

            System.out.println(pokemonInfo[firstPokemon][0] + " faints!");
            System.out.println();
            gainXp(saveNumber, (levelLeader * 5));
            System.out.println(playerPokemonInfo[0] + " gained " + (levelLeader * 5) + "xp.");
            System.out.println(playerPokemonInfo[0] + " [XP: " + getXP(saveNumber) + "/" + checkLevelLimit(getLevel(saveNumber)) + "]");
            System.out.println();
            checkXp(saveNumber, playerPokemonInfo[0]);
            checkEvolve(saveNumber, playerPokemonInfo[0]);
            pokemonQueue.poll();
            firstPokemon++;
        }
        System.out.println();
        System.out.println("You have defeat all his Pokemon.");
        System.out.println("You earn " + earnBadges(location));
        updateBadges(saveNumber, earnBadges(location));

    }

    public static void fightWildPokemon(int saveNumber, String location) {
        Random random = new Random();
        int idOfWildPokemon;
        String selectSql1;
        String selectSql2;

        switch (location.toLowerCase()) {
            case "pewtercity":
                idOfWildPokemon = random.nextInt(3, 8);
                break;
            case "ceruleancity":
                idOfWildPokemon = random.nextInt(3, 7);
                break;
            case "vermilioncity":
                idOfWildPokemon = random.nextInt(4, 9);
                break;
            case "celadoncity":
                idOfWildPokemon = random.nextInt(4, 9);
                break;
            case "fuchsiacity":
                idOfWildPokemon = random.nextInt(5, 11);
                break;
            case "saffroncity":
                idOfWildPokemon = random.nextInt(5, 11);
                break;
            case "cinnabarisland":
                idOfWildPokemon = random.nextInt(5, 9);
                break;
            case "viridiancity":
                idOfWildPokemon = random.nextInt(5, 9);
                break;
            case "pallettown":
                idOfWildPokemon = random.nextInt(1, 3);
                break;
            case "lavendertown":
                idOfWildPokemon = random.nextInt(1, 12);
                break;
            default:
                // Default to 0 if location is not recognized
                idOfWildPokemon = 0;
                break;
        }

        if (idOfWildPokemon > 0) {

            selectSql1 = "SELECT Name, Level, Hp, Type, Move1, Dmg1, Move2, Dmg2, Move3, Dmg3, Move4, Dmg4 FROM " + location.toLowerCase() + " WHERE id = ?";
        } else {
            // Handle invalid location
            // You can throw an exception, print an error message, or handle it in any other appropriate way
            System.out.println("Invalid location specified: " + location);
            return;
        }

        selectSql2 = "SELECT pokemon_name, Type, Hp, maxHp, Xp, Level, Move1, dmg1, Move2, dmg2, Move3, dmg3, Move4, dmg, StrongAgainst, WeakAgainst FROM player_pokemon WHERE player_id = ?";

        String[] pokemonInfo = new String[12];
        String[] playerPokemonInfo = new String[16];

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(selectSql1)) {
            preparedStatement.setInt(1, idOfWildPokemon); // Set the parameter value
            ResultSet resultSet = preparedStatement.executeQuery();

            // Loop through the result set
            while (resultSet.next()) {
                pokemonInfo[0] = resultSet.getString("Name"); // Pokémon name
                pokemonInfo[1] = String.valueOf(resultSet.getInt("Level")); // Level
                pokemonInfo[2] = String.valueOf(resultSet.getInt("Hp")); // HP
                pokemonInfo[3] = resultSet.getString("Type"); // Type
                pokemonInfo[4] = resultSet.getString("Move1"); // Move1
                pokemonInfo[5] = String.valueOf(resultSet.getInt("Dmg1")); // Dmg1
                pokemonInfo[6] = resultSet.getString("Move2"); // Move2
                pokemonInfo[7] = String.valueOf(resultSet.getInt("Dmg2")); // Dmg2
                pokemonInfo[8] = resultSet.getString("Move3"); // Move3
                pokemonInfo[9] = String.valueOf(resultSet.getInt("Dmg3")); // Dmg3
                pokemonInfo[10] = resultSet.getString("Move4"); // Move4
                pokemonInfo[11] = String.valueOf(resultSet.getInt("Dmg4")); // Dmg4

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
                playerPokemonInfo[0] = resultSet.getString("pokemon_name"); // Pokémon name
                playerPokemonInfo[1] = resultSet.getString("Type"); // Type
                playerPokemonInfo[2] = String.valueOf(resultSet.getInt("Hp")); // HP
                playerPokemonInfo[3] = String.valueOf(resultSet.getInt("maxHp")); // Max HP
                playerPokemonInfo[4] = String.valueOf(resultSet.getInt("Xp")); // Xp
                playerPokemonInfo[5] = String.valueOf(resultSet.getInt("Level")); // Level
                playerPokemonInfo[6] = resultSet.getString("Move1"); // Move1
                playerPokemonInfo[7] = String.valueOf(resultSet.getInt("dmg1")); // Dmg1
                playerPokemonInfo[8] = resultSet.getString("Move2"); // Move2
                playerPokemonInfo[9] = String.valueOf(resultSet.getInt("dmg2")); // Dmg2
                playerPokemonInfo[10] = resultSet.getString("Move3"); // Move3
                playerPokemonInfo[11] = String.valueOf(resultSet.getInt("dmg3")); // Dmg3
                playerPokemonInfo[12] = resultSet.getString("Move4"); // Move4
                playerPokemonInfo[13] = String.valueOf(resultSet.getInt("dmg")); // Dmg
                playerPokemonInfo[14] = resultSet.getString("StrongAgainst"); // StrongAgainst
                playerPokemonInfo[15] = resultSet.getString("WeakAgainst"); // WeakAgainst
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int hpPlayer = Integer.parseInt(playerPokemonInfo[2]);
        int currentHpPlayer = hpPlayer;
        int levelPlayer = Integer.parseInt(playerPokemonInfo[5]); // Level
        int maxHpPlayer = Integer.parseInt(playerPokemonInfo[3]); // Max HP
        int xpPlayer = Integer.parseInt(playerPokemonInfo[4]); // XP
        int dmg1Player = Integer.parseInt(playerPokemonInfo[7]); // Dmg1
        int dmg2Player = Integer.parseInt(playerPokemonInfo[9]); // Dmg2
        int dmg3Player = Integer.parseInt(playerPokemonInfo[11]); // Dmg3
        int dmg4Player = Integer.parseInt(playerPokemonInfo[13]); // Dmg4

        int levelLeader = Integer.parseInt(pokemonInfo[1]);
        int hpLeader = Integer.parseInt(pokemonInfo[2]);
        int dmg1Leader = Integer.parseInt(pokemonInfo[5]);
        int dmg2Leader = Integer.parseInt(pokemonInfo[7]);
        int dmg3Leader = Integer.parseInt(pokemonInfo[9]);
        int dmg4Leader = Integer.parseInt(pokemonInfo[11]);

        String[] strongAgainstTypePlayer = playerPokemonInfo[14].split(" · ");
        String[] typeLeader = pokemonInfo[3].split(" · ");

        System.out.println("Wild " + pokemonInfo[0] + " appear!");
        System.out.println("Go " + playerPokemonInfo[0] + "!");

        boolean isStrongAgainst = false;
        for (String strongType : strongAgainstTypePlayer) {
            for (String leaderType : typeLeader) {
                if (strongType.equalsIgnoreCase(leaderType)) {
                    isStrongAgainst = true;
                    // No need for the break here
                }
            }
        }

        if (isStrongAgainst) {
            System.out.println("Its " + playerPokemonInfo[1] + " type is strong against the opponent's " + typeLeader[0] + " type.");
        } else {
            System.out.println();
        }

        int round = 1;
        while (hpLeader > 0) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Round " + round + ": ");
            System.out.println(playerPokemonInfo[0] + "'s Moves: ");
            if (playerPokemonInfo[6] != null) {
                System.out.println("1. " + playerPokemonInfo[6]);
            }
            if (playerPokemonInfo[8] != null) {
                System.out.println("2. " + playerPokemonInfo[8]);
            }
            if (playerPokemonInfo[10] != null) {
                System.out.println("3. " + playerPokemonInfo[10]);
            }
            if (playerPokemonInfo[12] != null) {
                System.out.println("4. " + playerPokemonInfo[12]);
            }
            System.out.println("Which move will " + playerPokemonInfo[0] + " use?");
            System.out.print("Your choice: ");
            int moveChoice = sc.nextInt();
            System.out.println("+----------------------------------------------------------------------+");

            switch (moveChoice) {
                case 1:
                    if (playerPokemonInfo[6] != null) {

                        System.out.println(playerPokemonInfo[0] + " uses " + playerPokemonInfo[6]);
                        System.out.println(pokemonInfo[0] + "'s HP drops slightly. [" + pokemonInfo[0] + " HP: " + (hpLeader -= dmg1Player) + "/" + pokemonInfo[2] + "]");
                        System.out.println();
                    }
                    break;
                case 2:
                    if (playerPokemonInfo[8] != null) {
                        System.out.println(playerPokemonInfo[0] + " uses " + playerPokemonInfo[8]);
                        System.out.println(pokemonInfo[0] + "'s HP drops slightly. [" + pokemonInfo[0] + " HP: " + (hpLeader -= dmg2Player) + "/" + pokemonInfo[2] + "]");
                        System.out.println();
                    }
                    break;
                case 3:
                    if (playerPokemonInfo[10] != null) {
                        System.out.println(playerPokemonInfo[0] + " uses " + playerPokemonInfo[10]);
                        System.out.println(pokemonInfo[0] + "'s HP drops slightly. [" + pokemonInfo[0] + " HP: " + (hpLeader -= dmg3Player) + "/" + pokemonInfo[2] + "]");
                        System.out.println();
                    }
                    break;
                case 4:
                    if (playerPokemonInfo[12] != null) {
                        System.out.println(playerPokemonInfo[0] + " uses " + playerPokemonInfo[12]);
                        System.out.println(pokemonInfo[0] + "'s HP drops slightly. [" + pokemonInfo[0] + " HP: " + (hpLeader -= dmg4Player) + "/" + pokemonInfo[2] + "]");
                        System.out.println();
                    }
                    break;
                default:
                    System.out.println("Invalid move choice. Please select a move between 1 and 4.");
                    break;
            }

            int opponentMoveIndex;
            String moveName;
            int moveDamage;

            while (true) {
                opponentMoveIndex = 1; // Generate random number between 1 and 4

                switch (opponentMoveIndex) {
                    case 1:
                        moveName = pokemonInfo[4];
                        moveDamage = dmg1Leader;
                        break;
                    case 2:
                        moveName = pokemonInfo[6];
                        moveDamage = dmg2Leader;
                        break;
                    case 3:
                        moveName = pokemonInfo[8];
                        moveDamage = dmg3Leader;
                        break;
                    case 4:
                        moveName = pokemonInfo[10];
                        moveDamage = dmg4Leader;
                        break;
                    default:
                        System.out.println("Invalid opponent move.");
                        continue; // Generate a new random number if the move index is invalid
                    }

                if (moveName != null) {
                    // A valid move is found
                    currentHpPlayer -= moveDamage;
                    System.out.println(pokemonInfo[0] + " uses " + moveName);
                    // Handle damage calculation for the move
                    System.out.println(playerPokemonInfo[0] + " takes some damage. [" + playerPokemonInfo[0] + " HP : " + currentHpPlayer + "/" + maxHpPlayer + "]");
                    System.out.println();

                    if (currentHpPlayer < 0) {
                        System.out.println("Since you have one pokemon, and for fair purpose. You can revive up to the number of opponent pokemons.");
                        currentHpPlayer = maxHpPlayer;
                    }
                    break; // Exit the loop since a move is found
                }
            }
            System.out.println();
            round++;
        }

        System.out.println(pokemonInfo[0] + " faints!");
        System.out.println();
        gainXp(saveNumber, (levelLeader * 5));
        System.out.println(playerPokemonInfo[0] + " gained " + (levelLeader * 5) + "xp.");
        System.out.println(playerPokemonInfo[0] + " [XP: " + getXP(saveNumber) + "/" + checkLevelLimit(getLevel(saveNumber)) + "]");
        System.out.println();
        checkXp(saveNumber, playerPokemonInfo[0]);
        checkEvolve(saveNumber, playerPokemonInfo[0]);

    }

}
