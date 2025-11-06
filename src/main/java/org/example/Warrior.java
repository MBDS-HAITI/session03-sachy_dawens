package org.example;

public class Warrior extends Character {
    public Warrior(String name) {
        super(name,100,30 );
    }
    public void actionParticuliere() {
        System.out.println(getName() + " se bat avec courage !");
    }
}