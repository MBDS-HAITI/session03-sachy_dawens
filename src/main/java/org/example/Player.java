package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Player {
    private final String name;
    private final List<Character> team = new ArrayList<>();

    public Player(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void addCharacter(Character c) {
        if (c == null)
            return;
        team.add(c);
    }

    public boolean hasCharacter(String name) {
        for (Character c : team) {
            if (c.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public List<Character> getTeam() {

        return Collections.unmodifiableList(team);
    }

    public List<Character> alive() {
    //    return team.stream().filter(Character::isAlive).collect(Collectors.toList());
            List<Character> alive = new ArrayList<>();

            for (Character c : team) {
                if (c.isAlive()) {
                    alive.add(c);
                }
            }

            return alive;
    }

    public boolean isDefeated() {

        return alive().isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Team of ").append(name).append(":\n");
        for (Character c : team) {
            sb.append("  - ").append(c.toString()).append('\n');
        }
        return sb.toString();
    }
}
