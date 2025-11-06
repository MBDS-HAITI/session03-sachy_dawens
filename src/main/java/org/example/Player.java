package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Player {
    private final String name;
    private final List<Character> team = new ArrayList<>();

    public Player(String name) { this.name = name; }

    public String getName() { return name; }

    public void addCharacter(Character c) { team.add(c); }

    public List<Character> getTeam() { return Collections.unmodifiableList(team); }

    public List<Character> getAlive() {
        return team.stream().filter(Character::isAlive).collect(Collectors.toList());
    }

    public boolean isDefeated() { return getAlive().isEmpty(); }
}
