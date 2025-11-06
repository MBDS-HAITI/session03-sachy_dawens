package org.example;

public class Dwarf extends Character {
    public Dwarf(String name) {
        super(name, new Weapon("Hache runique", 34), 70);
    }

    @Override
    public void action(Character target) {
        if (target == null) {
            System.out.println(getName() + " n'a aucune cible à attaquer.");
            return;
        }
        int dmg = getWeapon().getPower();
        System.out.println(getName() + " frappe " + target.getName() + " avec sa hâche !");
        target.takeDamage(dmg);
    }
}
