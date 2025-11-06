package org.example;

public class Magus extends Character {
    private final int healPower = 30;

    public Magus(String name) {
        super(name, new Weapon("Bâton de chêne", 12), 80);
    }

    @Override
    public void action(Character target) {
        if (target == null) {
            System.out.println(getName() + " n'a aucune cible à soigner.");
            return;
        }
        System.out.println(getName() + " lance un soin sur " + target.getName() + ".");
        target.heal(healPower);
    }
}
