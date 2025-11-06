package org.example;

public class Warrior extends Character implements Attacker {
    public Warrior(String name, Player owner) {
        super(name, owner, 120, new Weapon("Steel Sword", 25));
    }
    @Override public String getType() { return "Warrior"; }
    @Override public int attackDamage() { return getWeapon().getPower(); }
}
