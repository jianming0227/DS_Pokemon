/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pokemon;

/**
 *
 * @author User
 */
import java.util.LinkedList;
import java.util.Scanner;

public class safariZone {
    
    public static void playSafariZone() {
        Scanner scanner = new Scanner(System.in);
        LinkedList<String> pokemonList = new LinkedList<>();

        System.out.println("+----------------------------------------------------------------------+\n" +
                           " Welcome to the Safari Zone! Today's challenge: Sort the Pokémon!\n" +
                           "+----------------------------------------------------------------------+");

        System.out.print("Enter the Pokémon in your party (separated by a comma): ");
        String input = scanner.nextLine();
        String[] pokemons = input.split(", ");

        while (checkCondition1(pokemons) || checkCondition2(pokemons) || checkCondition3(pokemons) || checkCondition4(pokemons) || checkCondition5(pokemons)) {
        System.out.println("Invalid Combination!!");
        System.out.print("Enter the Pokémon in your party again (separated by a comma): ");
        input = scanner.nextLine();
        pokemons = input.split(", ");
        }


        for (String pokemon : pokemons) {
            pokemonList.add(pokemon);
        }

        System.out.println("\nYou entered: " + displayList(pokemonList));

        System.out.println("\nSorting your Pokémon according to their unique preferences...\n");
        sortPokemon(pokemonList);

        System.out.println("\n+----------------------------------------------------------------------+\n" +
                           " Your Pokémon are now sorted! Enjoy your adventure in the Safari Zone!\n" +
                           "+----------------------------------------------------------------------+");

    }

    public static String displayList(LinkedList<String> list) {
        StringBuilder Plist = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Plist.append(list.get(i));
            if (i != list.size() - 1) {
                Plist.append(", ");
            }
        }
        return Plist.toString();
    }

    public static void sortPokemon(LinkedList<String> list) {
        // Eevee insists on being positioned either at the beginning of the lineup to showcase its adaptability
        moveToFirst(list, "Eevee");
        System.out.println("Step 1: Eevee insists on being positioned either at the beginning of the lineup to showcase its adaptability." +
                           "\nSorted List: " + displayList(list));

        // Pikachu demands to be placed at the center of the arrangement
        moveToCenter(list, "Pikachu");
        System.out.println("\nStep 2: Pikachu demands to be placed at the center of the arrangement." +
                           "\nPartial Sort: " + displayList(list));

        // Snorlax insists on being positioned at the end of the lineup to ensure maximum relaxation
        moveToLast(list, "Snorlax");
        System.out.println("\nStep 3: Snorlax insists on being positioned at the end of the lineup to ensure maximum relaxation." +
                           "\nPartial Sort: " + displayList(list));

        // Jigglypuff prefers to be surrounded by other "cute" Pokémon for morale purposes
        moveToPikachu(list, "Jigglypuff");
        System.out.println("\nStep 4: Jigglypuff prefers to be surrounded by other \"cute\" Pokémon for morale purposes." +
                           "\nPartial Sort: " + displayList(list));

        // Bulbasaur refuses to be placed next to Charmander
        avoidCharmander(list, "Bulbasaur", "Charmander");
        System.out.println("\nStep 5: Bulbasaur refuses to be placed next to Charmander." +
                           "\nPartial Sort: " + displayList(list));

        // Machop demands to be placed next to the heaviest Pokemon in the lineup
        moveToSnorlax(list, "Machop");
        System.out.println("\nStep 6: Machop demands to be placed next to the heaviest Pokemon in the lineup." +
                           "\nFinal Sorted List: " + displayList(list));
    }

    private static void moveToFirst(LinkedList<String> list, String Eevee) {
        int index = list.indexOf(Eevee);
        if (index != -1) {
            list.remove(index);
            list.addFirst(Eevee);
        }
    }

    private static void moveToCenter(LinkedList<String> list, String Pikachu) {
        int index = list.indexOf(Pikachu);
        if (index != -1) {
            list.remove(index);
            list.add((list.size() + 1) / 2, Pikachu);
        }
    }

    private static void moveToLast(LinkedList<String> list, String Snorlax) {
        int index = list.indexOf(Snorlax);
        int indexOfPikachu = list.indexOf("Pikachu");
        String lastElement = list.getLast();
        if(lastElement.equals("Snorlax")) return;
        if (index != -1) {
            if(indexOfPikachu > index) {
                list.set(index, lastElement);
                list.set(list.size() - 1, Snorlax);
            } else {
                list.remove(index);
                list.addLast(Snorlax);
            }
        }
    }

    private static void moveToPikachu(LinkedList<String> list, String Jigglypuff) {
        int index = list.indexOf(Jigglypuff);
        int indexOfPikachu = list.indexOf("Pikachu");
        if(index == -1) return;
        if(indexOfPikachu == -1) {
            list.remove(index);
            list.add((list.size() + 1) / 2, Jigglypuff);
            return;
        }
        String elementBeforePika = list.get(indexOfPikachu - 1);
        int indexOfElementBeforePika = list.indexOf(elementBeforePika);
        if (index != -1) {
            if((indexOfPikachu - index) == 1) return;
            else {
                list.set(index, elementBeforePika);
                list.set(indexOfElementBeforePika, Jigglypuff);
            }
        }
    }

    private static void avoidCharmander(LinkedList<String> list, String Bulbasaur, String Charmander) {
        int index1 = list.indexOf(Bulbasaur);
        int index2 = list.indexOf(Charmander);
        if(index1 == -1 || index2 == -1) return;
        int indexOfMachop = list.indexOf("Machop");
        if (Math.abs(index1 - index2) == 1) {
            // Swap the positions of the Pokemon with Machop
            if(index1 == (list.size() - 2)) { 
                list.set(index1, "Machop");
                list.set(indexOfMachop, Bulbasaur);
            } else {
                list.set(index2, "Machop");
                list.set(indexOfMachop, Charmander);
            }
        }
    }

    private static void moveToSnorlax(LinkedList<String> list, String Machop) {
        int index = list.indexOf(Machop);
        int indexOfSnorlax = list.indexOf("Snorlax");
        if(indexOfSnorlax == -1) return;
        if((indexOfSnorlax - index) != 1) {
            if (index != -1) {
                list.remove(index);
                list.add(indexOfSnorlax-1, Machop);
            }
        }
    }

    // Method use for checking invalid condition condition 
    public static boolean checkCondition1(String[] arr) {
        if(arr.length != 2) return false;
        int bulbasaurCount = 0;
        int charmanderCount = 0;
        for (String element : arr) {
            if (element.equals("Bulbasaur")) {
                bulbasaurCount++;
            } else if(element.equals("Charmander")) {
                charmanderCount++;
            }
        }
        return bulbasaurCount == 1 && charmanderCount ==1;
    }

    public static boolean checkCondition2(String[] arr) {
        if (arr.length != 3) return false;
        int bulbasaurCount = 0;
        int charmanderCount = 0;
        int eeveeOrSnorlaxCount = 0;

        for (String element : arr) {
            if (element.equals("Bulbasaur")) {
                bulbasaurCount++;
            } else if (element.equals("Charmander")) {
                charmanderCount++;
            } else if (element.equals("Eevee") || element.equals("Snorlax")) {
                eeveeOrSnorlaxCount++;
            }
        }

        return bulbasaurCount == 1 && charmanderCount == 1 && eeveeOrSnorlaxCount == 1;
    }

    public static boolean checkCondition3(String[] arr) {
        if (arr.length != 4) return false;
        int bulbasaurCount = 0;
        int charmanderCount = 0;
        int eeveeCount = 0;
        int snorlaxCount = 0;

        for (String element : arr) {
            if (element.equals("Bulbasaur")) {
                bulbasaurCount++;
            } else if (element.equals("Charmander")) {
                charmanderCount++;
            } else if (element.equals("Eevee")) {
                eeveeCount++;
            } else if(element.equals("Snorlax")) {
                snorlaxCount++;
            }
        }

        return bulbasaurCount == 1 && charmanderCount == 1 && eeveeCount == 1 && snorlaxCount == 1;
    }

    public static boolean checkCondition4(String[] arr) {
        if (arr.length != 5) return false;
        int bulbasaurCount = 0;
        int charmanderCount = 0;
        int eeveeCount = 0;
        int machopCount = 0;
        int snorlaxCount = 0;

        for (String element : arr) {
            if (element.equals("Bulbasaur")) {
                bulbasaurCount++;
            } else if (element.equals("Charmander")) {
                charmanderCount++;
            } else if (element.equals("Eevee")) {
                eeveeCount++;
            } else if (element.equals("Machop")) {
                machopCount++;
            } else if (element.equals("Snorlax")) {
                snorlaxCount++;
            }
        }

        return bulbasaurCount == 1 && charmanderCount == 1 && eeveeCount == 1
                && machopCount == 1 && snorlaxCount == 1;
    }

    public static boolean checkCondition5(String[] arr) {
        if (arr.length != 4) return false;
        int bulbasaurCount = 0;
        int charmanderCount = 0;
        int machopCount = 0;
        int snorlaxCount = 0;

        for (String element : arr) {
            if (element.equals("Bulbasaur")) {
                bulbasaurCount++;
            } else if (element.equals("Charmander")) {
                charmanderCount++;
            } else if (element.equals("Machop")) {
                machopCount++;
            } else if (element.equals("Snorlax")) {
                snorlaxCount++;
            }
        }

        return bulbasaurCount == 1 && charmanderCount == 1 && machopCount == 1 && snorlaxCount == 1;
    }
}
