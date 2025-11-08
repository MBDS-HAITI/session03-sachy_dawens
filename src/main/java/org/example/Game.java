package org.example;

import java.util.List;
import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private Player player1;
    private Player player2;

    public void startGame() {
        System.out.println("Welcome to our Battle Arena game!");

        // === Player 1 Name ===
        String name1;
        do {
            System.out.print("Player 1 Name: ");
            name1 = scanner.nextLine().trim();

            if (name1.isEmpty()) {
                System.out.println(" Name cannot be empty. Try again!");
            } else if (name1.contains(" ")) {
                System.out.println(" Name cannot contain spaces. Try again!");
                name1 = "";
            }
        } while (name1.isEmpty());

        // === Player 2 Name ===
        String name2;
        do {
            System.out.print("Player 2 Name: ");
            name2 = scanner.nextLine().trim();

            if (name2.isEmpty()) {
                System.out.println(" Name cannot be empty. Try again!");
            } else if (name2.equalsIgnoreCase(name1)) {
                System.out.println(" Players must have different names. Try again!");
                name2 = "";
            } else if (name2.contains(" ")) {
                System.out.println(" Name cannot contain spaces. Try again!");
                name2 = "";
            }

        } while (name2.isEmpty());

        player1 = new Player(name1);
        player2 = new Player(name2);

        // === Team creation ===
        System.out.println("\nTeam Creation...");
        createTeam(player1);
        createTeam(player2);

        // === Start battle ===
        System.out.println("\n--- The battle begins! ---");
        battle();
    }

    /**
     * Allows a player to create a team of 3 characters.
     */
    private void createTeam(Player player) {
        System.out.println("\n" + player.getName() + ", choose your 3 characters.");
        List<String> classes = List.of("Warrior", "Magus", "Dwarf", "Colossus");

        for (int i = 1; i <= 3; i++) {
            System.out.println("\nChoose the character type " + i + ":");
            for (int j = 0; j < classes.size(); j++) {
                System.out.println((j + 1) + ") " + classes.get(j));
            }
            System.out.print("> ");
            int choice = readIntBetween(1, 4);

            // Ask for a unique and valid character name
            String characterName;
            do {
                System.out.print("Please enter a name for your character: ");
                characterName = scanner.nextLine().trim();

                if (characterName.isEmpty()) {
                    System.out.println(" Character name cannot be empty!");
                } else if (characterName.contains(" ")) {
                    System.out.println(" Character name cannot contain spaces!");
                    characterName = "";
                } else if (player.hasCharacter(characterName)) {
                    System.out.println(" You already have a character with this name!");
                    characterName = "";
                }

            } while (characterName.isEmpty() || player.hasCharacter(characterName));

            // Create the character based on the chosen type
            Character character;
            switch (choice) {
                case 1 -> character = new Warrior(characterName);
                case 2 -> character = new Magus(characterName);
                case 3 -> character = new Dwarf(characterName);
                case 4 -> character = new Colossus(characterName);
                default -> throw new IllegalArgumentException("Invalid choice");
            }

            player.addCharacter(character);
            System.out.println(characterName + " has been added to your team!");
        }
    }

    /**
     * Manages the battle between the two players.
     */
    private void battle() {
        Player active = player1;
        Player rival = player2;
        int turn = 1;

        while (!player1.isDefeated() && !player2.isDefeated()) {
            System.out.println("\n--- Turn " + turn + ": " + active.getName() + " ---");
            displayTeams();

            Character activeCharacter = chooseCharacter(active.alive(), "Choose a character to act");

            if (activeCharacter instanceof Magus) {
                System.out.println("1) Attack  2) Heal");
                int choice = readIntBetween(1, 2);

                if (choice == 1) {
                    Character target = chooseCharacter(rival.alive(), "Choose a target to attack");
                    activeCharacter.action(target);
                } else {
                    Character target = chooseCharacter(active.alive(), "Choose an ally to heal");
                    activeCharacter.action(target);
                }
            } else {
                Character target = chooseCharacter(rival.alive(), "Choose a target to attack");
                activeCharacter.action(target);
            }

            if (rival.isDefeated()) break;

            // Switch turns
            Player temp = active;
            active = rival;
            rival = temp;
            turn++;
        }

        System.out.println("\n=== End of the battle ===");
        if (player1.isDefeated()) {
            System.out.println(" Victory for " + player2.getName() + "!");
        } else {
            System.out.println(" Victory for " + player1.getName() + "!");
        }
    }

    /**
     * Displays both teams' current status.
     */
    private void displayTeams() {
        System.out.println("\nTeam status:");
        System.out.println(player1);
        System.out.println(player2);
    }

    /**
     * Allows a player to select a character from a given list.
     */
    private Character chooseCharacter(List<Character> list, String message) {
        System.out.println("\n" + message + ":");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ") " + list.get(i));
        }
        System.out.print("> ");
        int choice = readIntBetween(1, list.size());
        return list.get(choice - 1);
    }

    /**
     * Ensures a valid integer input between given limits.
     */
    private int readIntBetween(int min, int max) {
        while (true) {
            try {
                int n = Integer.parseInt(scanner.nextLine().trim());
                if (n >= min && n <= max) return n;
            } catch (Exception ignored) {}
            System.out.print("Invalid entry, choose a number between " + min + " and " + max + ": ");
        }
    }
}
