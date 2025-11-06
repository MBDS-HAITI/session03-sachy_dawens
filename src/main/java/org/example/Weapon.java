package org.example;

public class Weapon {
    private String name;
    private int power;


    public Weapon(String name, int power) {
        this.name = name;
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public String getName() {
        return name;
    }

    public String toString(String name) {
        return name + " (Power" + power + ")";
    }

}
