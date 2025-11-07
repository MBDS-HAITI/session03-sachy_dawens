package org.example;

public class Colossus extends Character {
    public Colossus(String name) {
        super(name, new Weapon("Marteau de pierre", 22), 180); // très résistant, dégâts moyens
    }

    @Override
    public void action(Character target) {
        if (target == null) {
            System.out.println(getName() + " n'a aucune cible à attaquer.");
            return;
        }
        int dmg = getWeapon().getPower();
        System.out.println(getName() + " écrase " + target.getName() + " avec son marteau !");
        target.takeDamage(dmg);
    }
}
