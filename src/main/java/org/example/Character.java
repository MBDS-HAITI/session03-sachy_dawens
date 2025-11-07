package org.example;

public abstract class Character {
    private String name;
    private int health;
    private Weapon weapon;


    public Character(String nameCharacter, Weapon weapon, int health) {
        this.name = nameCharacter;
        this.weapon = weapon;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
        System.out.println(name + " prend " + damage + " dégâts. HP restants: " + health);
    }

    public void heal(int amount) {
        health += amount;
        System.out.println(name + " est soigné de " + amount + " HP. HP actuels: " + health);
    }

    public boolean isAlive() {
        return health > 0;
    }
    @Override
    public String toString() {
        return name + " [" + getClass().getSimpleName() + "] - HP: " + health + " - " + weapon;
    }

    public abstract void action(Character target);
}

