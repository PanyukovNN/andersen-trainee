package com.company.firsttask.patterns.builder;

public class Car {

    private final Type type;

    private final int seats;

    private final Engine engine;

    private final Transmission transmission;

    public Car(Type type, int seats, Engine engine, Transmission transmission) {
        this.type = type;
        this.seats = seats;
        this.engine = engine;
        this.transmission = transmission;
    }

    public Type getType() {
        return type;
    }

    public int getSeats() {
        return seats;
    }

    public Engine getEngine() {
        return engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }
}
