package org.example;

public class Warrior extends Character {

    public Warrior(String name) {
        super(name, new Weapon("Épée d’acier", 25), 120);
    }

    @Override
    public void action(Character target) {
        if (target == null) {
            System.out.println(getName() + " n'a aucune cible à attaquer.");
            return;
        }
        int degats = getWeapon().getPower();
        System.out.println(getName() + " attaque " + target.getName() + " avec son épée !");
        target.takeDamage(degats);
    }
}
