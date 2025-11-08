package org.example;

public abstract class Character {
    private String name;
    private int health;
    private Weapon weapon;

    // -- Object constructors
    public Character(String nameCharacter, Weapon weapon, int health) {
        this.name = nameCharacter;
        this.weapon = weapon;
        this.health = health;
    }

    // -- Common methods
    public void takeDamage(int damage) {
    //    health = Math.max(0, health - damage);
        health = health - damage;
        if (health < 0) {
            health = 0;
        }
        System.out.println(name + " takes " + damage + " damage. Remaining HP: " + health);
    }

    public void heal(int amount) {
        health += amount;
        System.out.println(name + " is healed for " + amount + " HP. Current HP: " + health);
    }

    public boolean isAlive() {

        return health > 0;
    }

   // -- Getters methods for all attributes
   public String getName() {

        return name;
   }

    public int getHealth() {

        return health;
    }

    public Weapon getWeapon() {

        return weapon;
    }

    // -- Setters methods for health
    public void setHealth(int health) {

        this.health = health;
    }

    @Override
    public String toString() {

        return name + " [" + getClass().getSimpleName() + "] - HP: " + health + " - " + weapon;
    }

    public abstract void action(Character target);
}

