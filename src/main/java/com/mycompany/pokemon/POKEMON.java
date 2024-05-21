/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.pokemon;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class POKEMON {

    Scanner sc = new Scanner(System.in);

    public void startPage() {
        String[] outline = {
            "                                    ,'\\",
            "      _.----.        ____         ,'  _\\   ___    ___     ____",
            "  _,-'       `.     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`.",
            "  \\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |",
            "   \\.    \\ \\   |  __  |  |/    ,','_  `.  |          | __  |    \\|  |",
            "     \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |",
            "      \\     ,-/  /   \\    ,'   | \\/ / ,`.|         /  /   \\  |     |",
            "       \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |",
            "        \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |",
            "         \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |",
            "          \\_.-'       |__|    `-._ |              '-.|     '-.| |   |",
            "                                  `'                            '-._|",
            "+----------------------------------------------------------------------+",
            "                  Welcome to Pokemon - Kanto Adventures",
            "               **Press ENTER whenever a choice is entered!**",
            "+----------------------------------------------------------------------+"
        };

        // Print the outline
        for (String line : outline) {
            System.out.println(line);
        }

        System.out.println("[1] Load Game:");
        System.out.println(" a. Save 1 - " + ConnectDatabase.getName(1));
        System.out.println(" b. Save 2 - " + ConnectDatabase.getName(2));
        System.out.println(" c. Save 3 - " + ConnectDatabase.getName(3));
        System.out.println("[2] Start a new Adventure: ");
        System.out.println(" a. Save 1 - " + ConnectDatabase.checkGame(1));
        System.out.println(" b. Save 2 - " + ConnectDatabase.checkGame(2));
        System.out.println(" c. Save 3 - " + ConnectDatabase.checkGame(3));
        System.out.println("[3] Exit");
        System.out.println("+----------------------------------------------------------------------+");
        System.out.print("Your choice: ");
        String choice = sc.next();
        System.out.println("+----------------------------------------------------------------------+");

        int saveNumber = -1; // Initialize with a default value

        switch (choice) {
            case "1a":
            case "1b":
            case "1c":
                saveNumber = choice.charAt(1) - 'a' + 1; // Convert 'a', 'b', 'c' to 1, 2, 3
                loadGame(saveNumber);
                break;
            case "2a":
            case "2b":
            case "2c":
                saveNumber = choice.charAt(1) - 'a' + 1; // Convert 'a', 'b', 'c' to 1, 2, 3
                startANewAdventure(saveNumber);
                break;
            case "3":
                System.exit(0); // Exit the program
                break;
            default:
                System.out.println("Invalid choice!");
                startPage(); // Restart the startPage() method if the choice is invalid
        }
    }

    public void loadGame(int saveNumber) {
        mainMenu(saveNumber);
    }

    public void startANewAdventure(int saveNumber) {
        System.out.println("OAK: Hello there! Welcome to the world of Pokémon! My name is Oak!");
        System.out.println("People call me the Pokémon Prof! This world is inhabited by creatures called Pokémon!");
        System.out.println("For some people, Pokémon are pets. Others use them for fights. Myself... I study Pokémon as a profession.");
        System.out.println("OAK: First, what is your name?");
        System.out.println("+----------------------------------------------------------------------+");
        System.out.print("Enter your name: ");
        sc.nextLine(); // Consume newline left from previous next() call
        String playerName = sc.nextLine();
        System.out.println("+----------------------------------------------------------------------+");

        // Save the player's name to the database based on the chosen slot (2a-2c)
        try {
            ConnectDatabase.updateName(saveNumber, playerName);
            ConnectDatabase.defaultLocation(saveNumber); // Set default location to PalletTown
        } catch (SQLException e) {
            System.err.println("Failed to save player name to the database!");
            e.printStackTrace();
        }

        chooseStartingPokemon(saveNumber); // Start choosing the starting Pokemon
    }

    public void chooseStartingPokemon(int saveNumber) {
        System.out.println("OAK: It's time to choose your starting Pokémon.");
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("[1] Bulbasaur [Grass - Level 5]");
        System.out.println("[2] Squirtle [Water - Level 5]");
        System.out.println("[3] Charmander [Fire - Level 5]");
        System.out.println("+----------------------------------------------------------------------+");
        System.out.print("Your choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("+----------------------------------------------------------------------+");
                System.out.println("OAK: You chose Bulbasaur, an amazing choice. Best of luck!");
                System.out.println("+----------------------------------------------------------------------+");
                ConnectDatabase.savePokemon(saveNumber, "Bulbasaur");
                ConnectDatabase.updatePokemonStatsInPlayer(saveNumber, "Bulbasaur", 5); // Pass 5 as the level
                ConnectDatabase.updateMove(saveNumber, "Bulbasaur", "Tackle", "Vine Whip");
                ConnectDatabase.updateMoveDamage(saveNumber, "Bulbasaur");
                break;
            case 2:
                System.out.println("+----------------------------------------------------------------------+");
                System.out.println("OAK: You chose Squirtle, an amazing choice. Best of luck!");
                System.out.println("+----------------------------------------------------------------------+");
                ConnectDatabase.savePokemon(saveNumber, "Squirtle");
                ConnectDatabase.updatePokemonStatsInPlayer(saveNumber, "Squirtle", 5); // Pass 5 as the level
                ConnectDatabase.updateMove(saveNumber, "Squirtle", "Tail Whip", "Water Gun");
                ConnectDatabase.updateMoveDamage(saveNumber, "Squirtle");
                break;
            case 3:
                System.out.println("+----------------------------------------------------------------------+");
                System.out.println("OAK: You chose Charmander, an amazing choice. Best of luck!");
                System.out.println("+----------------------------------------------------------------------+");
                ConnectDatabase.savePokemon(saveNumber, "Charmander");
                ConnectDatabase.updatePokemonStatsInPlayer(saveNumber, "Charmander", 5); // Pass 5 as the level
                ConnectDatabase.updateMove(saveNumber, "Charmander", "Scratch", "Ember");
                ConnectDatabase.updateMoveDamage(saveNumber, "Charmander");
                break;
            default:
                System.out.println("Invalid choice!");
                chooseStartingPokemon(saveNumber); // Restart the chooseStartingPokemon() method if the choice is invalid
        }

        mainMenu(saveNumber);
    }

    public void mainMenu(int saveNumber) {
        LavenderTown pokeMaze = new LavenderTown();
        boolean exit = false;

        while (!exit) {
            String location = ConnectDatabase.getLocation(saveNumber);
            Maps maps = new Maps(); // Create an instance of the Maps class
            List<Maps.City> connections = maps.checkConnection(location); // Call the method on the instance

            System.out.println("+----------------------------------------------------------------------+");
            System.out.println(" You are currently in " + location + ":");
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println("[1] Move to:");
            int optionNum = 1;
            for (Maps.City connection : connections) {
                char optionLetter = (char) ('a' + optionNum - 1);
                System.out.println(" " + optionLetter + ". " + connection.name);
                optionNum++;
            }

            // Print specific formats for certain locations
            switch (location) {
                case "PalletTown":
                    System.out.println("[2] Talk to Mom " + checkGymType(location));
                    System.out.println("[3] Fight Wild Pokemon " + checkWildPokemon(location));
                    System.out.println("[4] Player Options");
                    System.out.println(" a. Show map");
                    System.out.println(" b. Show My Pokemon");
                    System.out.println(" c. Show My badges");
                    System.out.println(" d. Save and Exit");
                    break;
                case "FuchsiaCity":
                    System.out.println("[2] Challenge Gym Leader " + checkGymType(location));
                    System.out.println("[3] Fight Wild Pokemon " + checkWildPokemon(location));
                    System.out.println("[4] Player Options");
                    System.out.println(" a. Show map");
                    System.out.println(" b. Show My Pokemon");
                    System.out.println(" c. Show My badges");
                    System.out.println(" d. Save and Exit");
                    System.out.println("[5] Safari Zone");
                    break;
                case "SaffronCity":
                    System.out.println("[2] Challenge Gym Leader " + checkGymType(location));
                    System.out.println("[3] Fight Wild Pokemon " + checkWildPokemon(location));
                    System.out.println("[4] Player Options");
                    System.out.println(" a. Show map");
                    System.out.println(" b. Show My Pokemon");
                    System.out.println(" c. Show My badges");
                    System.out.println(" d. Save and Exit");
                    System.out.println("[5] Rival's Race");
                    break;
                case "LavenderTown":
                    System.out.println("[2] PokeMaze");
                    System.out.println("[3] Fight Wild Pokemon " + checkWildPokemon(location));
                    System.out.println("[4] Player Options");
                    System.out.println(" a. Show map");
                    System.out.println(" b. Show My Pokemon");
                    System.out.println(" c. Show My badges");
                    System.out.println(" d. Save and Exit");
                    break;
                default:
                    // Print normal options for other locations
                    System.out.println("[2] Challenge Gym Leader " + checkGymType(location));
                    System.out.println("[3] Fight Wild Pokemon " + checkWildPokemon(location));
                    System.out.println("[4] Player Options");
                    System.out.println(" a. Show map");
                    System.out.println(" b. Show My Pokemon");
                    System.out.println(" c. Show My badges");
                    System.out.println(" d. Save and Exit");
                    break;
            }
            System.out.println("+----------------------------------------------------------------------+");
            System.out.print("Your choice: ");
            sc.nextLine(); // Consume the newline character left in the buffer
            String choice = sc.nextLine();
            System.out.println("+----------------------------------------------------------------------+");

            switch (choice) {
                case "1":
                    System.out.print("Enter your choice (e.g., 1a, 1b): ");
                    String moveChoice = sc.nextLine().toLowerCase(); // Read the move choice
                    if (moveChoice.length() == 2 && moveChoice.charAt(0) == '1') {
                        char subChoice = moveChoice.charAt(1);
                        if (subChoice >= 'a' && subChoice <= 'z' && subChoice - 'a' < connections.size()) {
                            Maps.City selectedCity = connections.get(subChoice - 'a');
                            String newLocation = selectedCity.name;
                            moveToLocation(newLocation, saveNumber);
                        } else {
                            System.out.println("Invalid choice!");
                        }
                    } else {
                        System.out.println("Invalid choice!");
                    }
                    break;
                case "2":
                    if (location.equals("PalletTown")) {
                        System.out.println("MOM: \"Oh, " + ConnectDatabase.getName(saveNumber) + "! You're leaving on your adventure with Pokémon? How exciting! I know you've always dreamed of this day. Remember, the bond you share with your Pokémon is the most important thing. Take care of them, and they'll take care of you. Don't worry about me; I'll be just fine here. I can't wait to hear all about your adventures and the new friends you're going to make. Remember, no matter how far you go, I'm always here for you. Be brave, be kind, and everything will turn out just fine. I'm so proud of you already! Now, go on, your adventure awaits! Oh, and don’t forget to change your underwear every day! Safe travels, my dear!\"");
                    } else if (location.equals("LavenderTown")) {
                        LavenderTown lavenderTown = new LavenderTown();
                        lavenderTown.playPokeMaze();

                    } else {
                        ConnectDatabase.challengeGymLeader(location, saveNumber);
                    }
                    break;
                case "3":
                    ConnectDatabase.fightWildPokemon(saveNumber,location);

                    break;

                case "5":
                    // Handle option 5 based on the current location
                    switch (location) {
                        case "FuchsiaCity":
                            safariZone.playSafariZone();
                            break;
                        case "SaffronCity":
                            // Handle Rival's Race
                            RivalRace rr =new RivalRace(saveNumber);
                            rr.run();
                            break;
                        default:
                            System.out.println("Invalid choice!");
                            break;
                    }
                    break;
                case "4a":
                    maps.showMap(location);
                    break;
                case "4b":
                    ConnectDatabase.showMyPokemon(saveNumber);
                    break;
                case "4c":
                    ConnectDatabase.showMyBadges(saveNumber);
                    break;
                case "4d":
                    System.out.println("Saving and exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    public void moveToLocation(String newLocation, int saveNumber) {
        // Update the player's location in the database
        ConnectDatabase.updateLocation(saveNumber, newLocation);

    }

    public static String checkWildPokemon(String location) {
        switch (location) {
            case "ViridianCity":
                return "[Weddle, Kakuna, Caterpie]";
            case "PalletTown":
                return "[Pidgey, Rattata, Meowth]";
            case "CeruleanCity":
                return "[Weedle, Caterpie, Oddish]";
            case "SaffronCity":
                return "[Pidgey, Pidgeotto, Rattata]";
            case "VermilionCity":
                return "[Ekans, Sandshrew, Spearow]";
            case "CeladonCity":
                return "[Spearow, Rattata, Doduo]";
            case "FuchsiaCity":
                return "[Spearow, Doduo, Raticate]";
            case "LavenderTown":
                return "[Oddish, Bellsprout, Pidgey]";
            case "PewterCity":
                return "[Pidgey, Spearow, Rattata]";
            case "CinnabarIsland":
                return "[Pidgeotto, Ponyta, Koffing]";
            default:
                return "No information available for wild Pokémon in this location.";
        }
    }

    public static String checkGymType(String cityName) {
        String gymLeader = "";

        switch (cityName) {
            case "PewterCity":
                gymLeader = "[Brock - Rock type]";
                break;
            case "CeruleanCity":
                gymLeader = "[Misty - Water type]";
                break;
            case "VermilionCity":
                gymLeader = "[Lt. Surge - Electric type]";
                break;
            case "CeladonCity":
                gymLeader = "[Erika - Grass type]";
                break;
            case "FuchsiaCity":
                gymLeader = "[Koga - Poison type]";
                break;
            case "SaffronCity":
                gymLeader = "[Sabrina - Psychic type]";
                break;
            case "CinnabarIsland":
                gymLeader = "[Blaine - Fire type]";
                break;
            case "ViridianCity":
                gymLeader = "[Giovanni - Ground type]";
                break;
            case "PalletTown":
                gymLeader = "[Your hometown has no Gym]";
                break;
            default:
                gymLeader = "Unknown";
                break;
        }

        return gymLeader;
    }
}
