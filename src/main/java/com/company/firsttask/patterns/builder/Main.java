package com.company.firsttask.patterns.builder;

/**
 * Пример из java - StringBuilder
 */
public class Main {

    public static void main(String[] args) {
        Director director = new Director();

        CarBuilder builder = new CarBuilder();
        director.constructSportsCar(builder);

        Car car = builder.getResult();
        System.out.println("Car built:\n" + car.getType());
    }
}
