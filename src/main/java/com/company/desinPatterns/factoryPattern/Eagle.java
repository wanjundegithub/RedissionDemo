package com.company.desinPatterns.factoryPattern;

public class Eagle extends Bird{

    @Override
    public void run() {
        System.out.println("I am a Eagle,i can run");
    }

    @Override
    protected void fly() {

    }
}
