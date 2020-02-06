package com.company.firsttask.patterns.builder;

public class CarBuilder implements Builder {

    private Type type;

    private int seats;

    private Engine engine;

    private Transmission transmission;

    public void setType(Type type) {
        this.type = type;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Car getResult() {
        return new Car(type, seats, engine, transmission);
    }
}
