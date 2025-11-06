package org.example;

public class Colossus extends Character implements Attacker {
    public Colossus(String name, Player owner) {
        super(name, owner, 180, new Weapon("Stone Hammer", 22));
    }
    @Override public String getType() { return "Colossus"; }
    @Override public int attackDamage() { return getWeapon().getPower(); }
}
