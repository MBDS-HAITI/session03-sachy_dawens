package org.example;

public class Magus extends Character {

    public Magus(String name) {
        // PV faibles (80) et puissance d’arme faible (10)
        super(name, 80, 10);
    }

    @Override
    public void actionParticuliere() {
        // Exemple : le magus se soigne lui-même de 20 PV
        int soin = 20;
        setHealth(getHealth() + soin);
        System.out.println(getName() + " utilise sa magie et se soigne de " + soin + " PV !");
    }
}
