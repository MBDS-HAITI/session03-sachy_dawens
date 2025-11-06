package org.example;

public class Magus extends Character implements Attacker, Healer {
    public Magus(String name, Player owner) {
        super(name, owner, 80, new Weapon("Oak Staff", 12));
    }
    @Override public String getType() { return "Mage"; }
    @Override public int attackDamage() { return getWeapon().getPower(); }
    @Override public int healPoints() { return 30; }
}
