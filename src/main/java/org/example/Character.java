package org.example;

public abstract class Character {
    private String name;
    private int health;
    private int weaponPower;

    // -- Constructeur des objets
    public Character(String name, int health, int weaponPower) {
        this.name = name;
        this.health = health;
        this.weaponPower = weaponPower;
    }

    // -- Getters et setters pour tous les attributs
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getWeaponPower() {
        return weaponPower;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    // -- Methodes cummunes
    public void attack(Character target) {
        System.out.println(name + " attaque " + target.getName() + " avec " + weaponPower + " points de dégâts ! ");
        target.setHealth(target.getHealth() - weaponPower);
    }

    public boolean isAlive() {
        return health > 0;
    }

    // -- Methodes abstraites
    public abstract void actionParticuliere();

}

