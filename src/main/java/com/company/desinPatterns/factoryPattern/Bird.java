package com.company.desinPatterns.factoryPattern;

public abstract class Bird implements Animal {
    @Override
    public void run() {
        System.out.println("I am a Bird ,i can run");
    }

    protected  abstract void fly();
}
