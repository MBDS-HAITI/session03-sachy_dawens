package org.example;

import java.util.*;

public class Main {
    private final Scanner in = new Scanner(System.in);
    private final Player player1;
    private final Player player2;
    private int turnCount = 0;
    private final Set<String> usedNames = new HashSet<>();

    public static void main(String[] args) {
        new Main().start();
    }

    public Main() {
        System.out.println("=== Battle Arena ===");
        System.out.print("Player 1 name: ");
        player1 = new Player(readText());
        System.out.print("Player 2 name: ");
        player2 = new Player(readText());
    }

    public void start() {
        createTeam(player1);
        createTeam(player2);
        battleLoop();
        endScreen();
    }

    private void createTeam(Player player) {
        System.out.println("\n-- " + player.getName() + ": create your team (3 different classes) --");
        List<String> available = new ArrayList<>(List.of("Warrior","Mage","Colossus","Dwarf"));

        while (player.getTeam().size() < 3) {
            System.out.println("Available classes: " + available);
            System.out.print("Choose a class: ");
            String clazz = capitalize(readText());
            if (!available.contains(clazz)) {
                System.out.println("Invalid class or already used.");
                continue;
            }

            System.out.print("Character name: ");
            String cname = readText();
            if (usedNames.contains(cname)) {
                System.out.println("This name is already taken.");
                continue;
            }

            player.addCharacter(makeCharacter(clazz, cname, player));
            available.remove(clazz);
            usedNames.add(cname);
        }
    }

    private Character makeCharacter(String clazz, String name, Player owner) {
        return switch (clazz) {
            case "Warrior"  -> new Warrior(name, owner);
            case "Mage"     -> new Magus(name, owner);
            case "Colossus" -> new Colossus(name, owner);
            case "Dwarf"    -> new Dwarf(name, owner);
            default -> throw new IllegalArgumentException("Unknown class: " + clazz);
        };
    }

    private void battleLoop() {
        Player current = player1;
        Player other   = player2;

        while (!player1.isDefeated() && !player2.isDefeated()) {
            turnCount++;
            System.out.println("\n=== Turn " + turnCount + " — " + current.getName() + " ===");
            printTeams();

            Character actor = chooseCharacter(current.getAlive(), "Choose your acting character");
            List<String> actions = new ArrayList<>();
            if (actor.canAttack()) actions.add("Attack");
            if (actor.canHeal())   actions.add("Heal");
            String action = chooseFrom(actions, "Choose action");

            if ("Attack".equals(action)) {
                Character target = chooseCharacter(other.getAlive(), "Choose a target to attack");
                int dmg = ((Attacker) actor).attackDamage();
                target.takeDamage(dmg);
                System.out.printf("%s attacks %s for %d damage.%n",
                        actor.getName(), target.getName(), dmg);
                if (!target.isAlive()) System.out.println("💀 " + target.getName() + " has fallen!");
            } else {
                Character ally = chooseCharacter(current.getAlive(), "Choose an ally to heal");
                int heal = ((Healer) actor).healPoints();
                ally.receiveHeal(heal);
                System.out.printf("%s heals %s for %d HP.%n",
                        actor.getName(), ally.getName(), heal);
            }

            Player tmp = current; current = other; other = tmp;
        }
    }

    private void endScreen() {
        Player winner = player1.isDefeated() ? player2 : player1;
        System.out.println("\n===== Game Over =====");
        System.out.println("🏆 Winner: " + winner.getName());
        System.out.println("Turns played: " + turnCount);
        printTeams();
    }

    private void printTeams() {
        for (Player p : List.of(player1, player2)) {
            System.out.println("\nTeam of " + p.getName() + ":");
            for (Character c : p.getTeam()) {
                String mark = c.isAlive() ? "🟢" : "⚰️";
                System.out.printf("  %s %-12s | %s | HP %3d/%3d%n",
                        mark, c.getName(), c.getType(), c.getHp(), c.getMaxHp());
            }
        }
    }

    private Character chooseCharacter(List<Character> options, String label) {
        while (true) {
            System.out.println(label + ":");
            for (int i = 0; i < options.size(); i++)
                System.out.printf("%d) %s%n", i + 1, options.get(i));
            System.out.print("> ");
            try {
                int idx = Integer.parseInt(in.nextLine()) - 1;
                if (0 <= idx && idx < options.size()) return options.get(idx);
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid choice, try again.");
        }
    }

    private String chooseFrom(List<String> options, String label) {
        while (true) {
            System.out.println(label + ":");
            for (int i = 0; i < options.size(); i++)
                System.out.printf("%d) %s%n", i + 1, options.get(i));
            System.out.print("> ");
            try {
                int idx = Integer.parseInt(in.nextLine()) - 1;
                if (0 <= idx && idx < options.size()) return options.get(idx);
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid choice, try again.");
        }
    }

    private String readText() {
        while (true) {
            String s = in.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.print("Empty input, try again: ");
        }
    }

    private static String capitalize(String s) {
        if (s.isEmpty()) return s;
        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
