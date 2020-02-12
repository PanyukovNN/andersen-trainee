package com.company.fouthtask;

public class Service {

    private PrintStrategy strategy;

    public Service(PrintStrategy strategy) {
        this.strategy = strategy;
    }

    public void process() {
        strategy.print();
    }
}
