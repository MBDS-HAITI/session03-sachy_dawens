package org.example;

public class Dwarf extends Character implements Attacker {
    public Dwarf(String name, Player owner) {
        super(name, owner, 70, new Weapon("Runic Axe", 34));
    }
    @Override public String getType() { return "Dwarf"; }
    @Override public int attackDamage() { return getWeapon().getPower(); }
}
