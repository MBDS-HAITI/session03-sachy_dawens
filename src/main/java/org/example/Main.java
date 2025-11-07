package org.example;

import java.util.*;


public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private Player joueur1;
    private Player joueur2;

    public static void main(String[] args) {
        Main jeu = new Main();
        jeu.demarrer();
    }

    public void demarrer() {
        System.out.println(" Bienvenue a notre jeu Battle Arena  ");
        System.out.print("Nom du Joueur 1 : ");
        joueur1 = new Player(scanner.nextLine().trim());
        System.out.print("Nom du Joueur 2 : ");
        joueur2 = new Player(scanner.nextLine().trim());

        System.out.println("\nCréation des équipes SVP...");
        creerEquipe(joueur1);
        creerEquipe(joueur2);

        System.out.println("\n--- Le combat commence ! ---");
        combat();
    }

    private void creerEquipe(Player joueur) {
        System.out.println("\n" + joueur.getNom() + ", choisis tes 3 personnages.");
        List<String> classes = List.of("Warrior", "Magus", "Dwarf", "Colossus");

        for (int i = 1; i <= 3; i++) {
            System.out.println("\nChoisis le type du personnage " + i + " :");
            for (int j = 0; j < classes.size(); j++) {
                System.out.println((j + 1) + ") " + classes.get(j));
            }
            System.out.print("> ");
            int choix = lireEntierEntre(1, 4);

            System.out.print("Donne un nom à ton personnage SVP : ");
            String nomPerso = scanner.nextLine().trim();

            Character perso;
            switch (choix) {
                case 1 -> perso = new Warrior(nomPerso);
                case 2 -> perso = new Magus(nomPerso);
                case 3 -> perso = new Dwarf(nomPerso);
                case 4 -> perso = new Colossus(nomPerso);
                default -> throw new IllegalArgumentException(" Ce Choix invalide");
            }

            joueur.ajouterPersonnage(perso);
            System.out.println( perso + "est  ajouté à l'équipe !");
        }
    }

    private void combat() {
        Player actif = joueur1;
        Player adverse = joueur2;

        int tour = 1;
        while (!joueur1.estVaincu() && !joueur2.estVaincu()) {
            System.out.println("\n--- Tour " + tour + " : " + actif.getNom() + " ---");
            afficherEquipes();

            Character persoActif = choisirPersonnage(actif.vivants(), "Choisis un personnage pour agir ");

            if (persoActif instanceof Magus) {
                System.out.println("1) Attaquer  2) Soigner");
                int choix = lireEntierEntre(1, 2);
                if (choix == 1) {
                    Character cible = choisirPersonnage(adverse.vivants(), "Choisis une cible à attaquer");
                    persoActif.action(cible);
                } else {
                    Character cible = choisirPersonnage(actif.vivants(), "Choisis un allié à soigner");
                    persoActif.action(cible);
                }
            } else {
                Character cible = choisirPersonnage(adverse.vivants(), "Choisis une cible à attaquer");
                persoActif.action(cible);
            }

            if (adverse.estVaincu()) break;

            Player temp = actif;
            actif = adverse;
            adverse = temp;
            tour++;
        }

        System.out.println("\n===  Fin du combat ===");
        if (joueur1.estVaincu()) {
            System.out.println("Victoire de " + joueur2.getNom() + " !!!!");
        } else {
            System.out.println("Victoire de " + joueur1.getNom() + " !!!!");
        }
    }

    private void afficherEquipes() {
        System.out.println("\nÉtat des équipes :");
        System.out.println(joueur1);
        System.out.println(joueur2);
    }

    private Character choisirPersonnage(List<Character> liste, String message) {
        System.out.println("\n" + message + " :");
        for (int i = 0; i < liste.size(); i++) {
            System.out.println((i + 1) + ") " + liste.get(i));
        }
        System.out.print("> ");
        int choix = lireEntierEntre(1, liste.size());
        return liste.get(choix - 1);
    }

    private int lireEntierEntre(int min, int max) {
        while (true) {
            try {
                int n = Integer.parseInt(scanner.nextLine().trim());
                if (n >= min && n <= max) return n;
            } catch (Exception ignored) {}
            System.out.print("Entrée invalide, choisis un nombre entre " + min + " et " + max + " : ");
        }
    }
}



/*

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        IO.println(String.format("Hello and welcome!"));

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            IO.println("i = " + i);
        }
    }
}*/
