package org.example;

public abstract class Character {
    private final String name;
    private final Player owner;
    private final Weapon weapon;

    private final int maxHp;
    private int hp;

    protected Character(String name, Player owner, int maxHp, Weapon weapon) {
        this.name = name;
        this.owner = owner;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.weapon = weapon;
    }

    public String getName()  { return name; }
    public Player getOwner() { return owner; }
    public int getHp()       { return hp; }
    public int getMaxHp()    { return maxHp; }
    public Weapon getWeapon(){ return weapon; }

    public boolean isAlive() { return hp > 0; }

    public void takeDamage(int dmg) {
        if (dmg < 0) dmg = 0;
        hp = Math.max(0, hp - dmg);
    }

    public void receiveHeal(int amount) {
        if (amount < 0) amount = 0;
        hp = Math.min(maxHp, hp + amount);
    }

    public abstract String getType();

    public boolean canAttack() { return this instanceof Attacker; }
    public boolean canHeal()   { return this instanceof Healer; }

    @Override
    public String toString() {
        return String.format("%s %s [HP %d/%d, %s]",
                getType(), name, hp, maxHp, weapon);
    }
}
