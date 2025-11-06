package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Player {
    private final String nom;
    private final List<Character> equipe = new ArrayList<>();

    public Player(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void ajouterPersonnage(Character c) {
        if (c == null) return;
        equipe.add(c);
    }

    public List<Character> getEquipe() {
        return Collections.unmodifiableList(equipe);
    }

    public List<Character> vivants() {
        return equipe.stream().filter(Character::isAlive).collect(Collectors.toList());
    }

    public boolean estVaincu() {
        return vivants().isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Équipe de ").append(nom).append(":\n");
        for (Character c : equipe) {
            sb.append("  - ").append(c.toString()).append('\n');
        }
        return sb.toString();
    }
}
