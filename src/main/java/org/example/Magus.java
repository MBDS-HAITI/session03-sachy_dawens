package org.example;

public class Magus extends Character {
    public Magus(String name) { super(name, 120, 10);
    }
    @Override public void specialAction() {
        System.out.println(getName() + " lance un sort de soin !"); } }